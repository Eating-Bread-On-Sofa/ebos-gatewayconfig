package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.controller.GatewayConfigController;
import cn.edu.bjtu.ebosgatewayconfig.entity.Subscribe;
import cn.edu.bjtu.ebosgatewayconfig.util.ApplicationContextProvider;

public class RawSubscribe implements Runnable{
    private String subTopic;

    private MqFactory mqFactory = ApplicationContextProvider.getBean(MqFactory.class);

    public RawSubscribe(String subTopic){
        this.subTopic = subTopic;
    }

    @Override
    public void run() {
        try{
            MqConsumer mqConsumer = mqFactory.createConsumer(subTopic);

            while (true){
                try {
                    String msg = mqConsumer.subscribe();
                    if(!GatewayConfigController.check(subTopic)){
                        break;
                    }
                    System.out.println("收到"+subTopic+msg);
                }catch (Exception e){e.printStackTrace();break;}
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

}
