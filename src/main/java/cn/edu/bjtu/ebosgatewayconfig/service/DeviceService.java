package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceService {
    public boolean addDevice(Device device);
    public Device findDeviceByName(String name);
    public Page<Device> findAllDevice(Pageable pageable);
    boolean deleteByDeviceName(String name);
    public List<Device> findAllDevice();
    public void changeDeviceStatus(Device device);
    public Device findByNameAndVersion(String name, String version);
}
