package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.DeviceRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.Device;
import cn.edu.bjtu.ebosgatewayconfig.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;
    @Override
    public boolean addDevice(Device device) {
        Device device1 = deviceRepository.findDeviceByName(device.getGwname());
        if (device1 == null) {
            deviceRepository.save(device);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Device findDeviceByName(String name) {
        return deviceRepository.findDeviceByName(name);
    }

    @Override
    public Page<Device> findAllDevice(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByDeviceName(String name) {
        Device device = deviceRepository.findDeviceByName(name);
        if (device == null) {
            return false;
        } else {
            deviceRepository.deleteByDeviceName(name);
            return true;
        }
    }

    @Override
    public List<Device> findAllDevice() {
        return deviceRepository.findAll();
    }

    @Override
    public void changeDeviceStatus(Device device) {
        deviceRepository.save(device);
    }
}
