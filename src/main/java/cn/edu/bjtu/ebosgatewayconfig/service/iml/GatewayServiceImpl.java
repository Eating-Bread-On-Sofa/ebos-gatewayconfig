package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.GatewayRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.Gateway;
import cn.edu.bjtu.ebosgatewayconfig.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    GatewayRepository gatewayRepository;
    @Override
    public boolean addGateway(Gateway gateway) {
        Gateway gateway1 = gatewayRepository.findGatewayByIp(gateway.getIp());
        if (gateway1 == null) {
            gatewayRepository.save(gateway);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Gateway findGatewayByName(String name) {
        return gatewayRepository.findGatewayByName(name);
    }

    @Override
    public Gateway findGatewayByIp(String ip) {
        return gatewayRepository.findGatewayByIp(ip);
    }

    @Override
    public Page<Gateway> findAllGateway(Pageable pageable) {
        return gatewayRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByGatewayName(String name) {
        Gateway gateway = gatewayRepository.findGatewayByName(name);
        if (gateway == null) {
            return false;
        } else {
            gatewayRepository.deleteGatewayByName(name);
            return true;
        }
    }

    @Override
    public boolean deleteByGatewayIp(String ip) {
        Gateway gateway = gatewayRepository.findGatewayByIp(ip);
        if (gateway == null) {
            return false;
        } else {
            gatewayRepository.deleteGatewayByIp(ip);
            return true;
        }
    }

    @Override
    public List<Gateway> findAllGateway() {
        return gatewayRepository.findAll();
    }

    @Override
    public void changeGatewayStatus(Gateway gateway) {
        gatewayRepository.save(gateway);
    }
}
