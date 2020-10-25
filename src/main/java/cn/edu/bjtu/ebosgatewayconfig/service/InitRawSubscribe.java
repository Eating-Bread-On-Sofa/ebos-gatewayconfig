package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.controller.GatewayConfigController;
import cn.edu.bjtu.ebosgatewayconfig.entity.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class InitRawSubscribe implements ApplicationRunner {

    @Autowired
    SubscribeService subscribeService;
    @Autowired
    LogService logService;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 50,3, TimeUnit.SECONDS,new SynchronousQueue<>());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Subscribe> subscribes = subscribeService.findByServiceName();

        for (Subscribe subscribe : subscribes){
            RawSubscribe rawSubscribe = new RawSubscribe(subscribe.getSubTopic());
            GatewayConfigController.status.add(rawSubscribe);
            logService.info("update","网关管理重启后，初始化启动订阅："+rawSubscribe);
            threadPoolExecutor.execute(rawSubscribe);
        }
    }
}
