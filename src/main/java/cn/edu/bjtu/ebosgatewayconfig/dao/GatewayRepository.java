package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import cn.edu.bjtu.ebosgatewayconfig.entity.Gateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GatewayRepository extends MongoRepository<Gateway,String> {
    public Gateway findGatewayByName(String name);
    @Override
    public Page<Gateway> findAll(Pageable pageable);
    void deleteByGatewayName(String name);
    @Override
    List<Gateway> findAll();
}
