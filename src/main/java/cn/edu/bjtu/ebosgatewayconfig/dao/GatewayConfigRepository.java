package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.GatewayConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayConfigRepository extends MongoRepository<GatewayConfig,String> {
    public GatewayConfig findGatewayConfigByName(String name);
    @Override
    public Page<GatewayConfig> findAll(Pageable pageable);
    public GatewayConfig findGatewayConfigByIp(String ip);
    void deleteGatewayConfigByIp(String ip);
    void deleteGatewayConfigByName(String name);
    @Override
    List<GatewayConfig> findAll();
}
