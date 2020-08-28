package cn.edu.bjtu.ebosgatewayconfig.service;

public interface MqFactory {
    MqProducer createProducer();
    MqConsumer createConsumer(String topic);
}
