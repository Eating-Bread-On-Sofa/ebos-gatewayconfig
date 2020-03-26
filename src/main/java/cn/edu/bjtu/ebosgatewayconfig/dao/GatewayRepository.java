package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import cn.edu.bjtu.ebosgatewayconfig.entity.Gateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends MongoRepository<Gateway,String> {
    public Gateway findGatewayByName(String name);
    public Gateway findGatewayByIp(String ip);
    @Override
    public Page<Gateway> findAll(Pageable pageable);
    void deleteGatewayByName(String name);
    void deleteGatewayByIp(String ip);
    @Override
    List<Gateway> findAll();
}
