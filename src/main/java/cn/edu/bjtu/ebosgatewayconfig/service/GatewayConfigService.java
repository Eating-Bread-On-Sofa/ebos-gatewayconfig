package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.GatewayConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GatewayConfigService {
    public boolean addGatewayConfig(GatewayConfig device);
    public GatewayConfig findGatewayConfigByName(String name);
    public Page<GatewayConfig> findAllGatewayConfig(Pageable pageable);
    public GatewayConfig findGatewayConfigByIp(String ip);
    boolean deleteByGatewayConfigIp(String ip);
    boolean deleteByGatewayConfigName(String name);
    public List<GatewayConfig> findAllGatewayConfig();
    public void changeGatewayConfigStatus(GatewayConfig gatewayConfig);
}
