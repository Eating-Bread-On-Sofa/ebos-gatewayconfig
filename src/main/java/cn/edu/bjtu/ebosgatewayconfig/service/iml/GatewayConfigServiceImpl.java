package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.GatewayConfigRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.GatewayConfig;
import cn.edu.bjtu.ebosgatewayconfig.service.GatewayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayConfigServiceImpl implements GatewayConfigService {

    @Autowired
    GatewayConfigRepository gatewayConfigRepository;

    @Override
    public boolean addGatewayConfig(GatewayConfig gatewayConfig) {
        GatewayConfig findGatewayConfig = gatewayConfigRepository.findGatewayConfigByIp(gatewayConfig.getIp());
        if (findGatewayConfig == null) {
            gatewayConfigRepository.save(gatewayConfig);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public GatewayConfig findGatewayConfigByName(String name) {
        return gatewayConfigRepository.findGatewayConfigByName(name);
    }

    @Override
    public Page<GatewayConfig> findAllGatewayConfig(Pageable pageable) {
        return gatewayConfigRepository.findAll(pageable);
    }

    @Override
    public GatewayConfig findGatewayConfigByIp(String ip) {

        return gatewayConfigRepository.findGatewayConfigByIp(ip);
    }

    @Override
    public boolean deleteByGatewayConfigIp(String ip) {

        GatewayConfig gatewayConfig = gatewayConfigRepository.findGatewayConfigByIp(ip);
        if(gatewayConfig == null ){
            return false;
        }else{
            gatewayConfigRepository.deleteGatewayConfigByIp(ip);
            return true;
        }
    }

    @Override
    public boolean deleteByGatewayConfigName(String name) {

        GatewayConfig gatewayConfig = gatewayConfigRepository.findGatewayConfigByName(name);
        if(gatewayConfig == null ){
            return false;
        }else{
            gatewayConfigRepository.deleteGatewayConfigByName(name);
            return true;
        }
    }

    @Override
    public List<GatewayConfig> findAllGatewayConfig() {

        return gatewayConfigRepository.findAll();
    }

    @Override
    public void changeGatewayConfigStatus(GatewayConfig gatewayConfig) {
        gatewayConfigRepository.save(gatewayConfig);
    }
}
