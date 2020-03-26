package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceserviceService {
    public boolean addDeviceservice(Deviceservice deviceservice);
    public Deviceservice findDeviceserviceByName(String name);
    public Page<Deviceservice> findAllDeviceservice(Pageable pageable);
    boolean deleteByDeviceserviceName(String name);
    public List<Deviceservice> findAllDeviceservice();
    public void changeDeviceserviceStatus(Deviceservice deviceservice);
}
