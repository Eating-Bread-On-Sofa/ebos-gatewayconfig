package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Gateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface GatewayService {
    public boolean addGateway(Gateway gateway);
    public Gateway findGatewayByName(String name);
    public Gateway findGatewayByIp(String ip);
    public Page<Gateway> findAllGateway(Pageable pageable);
    boolean deleteByGatewayName(String name);
    boolean deleteByGatewayIp(String ip);
    public List<Gateway> findAllGateway();
    public void changeGatewayStatus(Gateway gateway);
    List<Gateway> findByCreatedTime(Date start, Date end);
}
