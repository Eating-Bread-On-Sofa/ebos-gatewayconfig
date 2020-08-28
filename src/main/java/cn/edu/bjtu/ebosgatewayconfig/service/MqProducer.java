package cn.edu.bjtu.ebosgatewayconfig.service;

public interface MqProducer {
    void publish(String topic, String message);
}
