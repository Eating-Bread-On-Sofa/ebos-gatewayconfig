package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Subscribe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscribeService {
    void save(String subTopic);
    void delete(String subTopic);
    List<Subscribe> findAll();
    List<Subscribe> findByServiceName();
}
