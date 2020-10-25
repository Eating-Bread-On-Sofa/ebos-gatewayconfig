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
//        Device device1 = deviceRepository.findDeviceByGwname(device.getGwname());
//        if (device1 == null) {
        deviceRepository.save(device);
        return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public Device findDeviceByName(String name) {
        return deviceRepository.findDeviceByGwname(name);
    }

    @Override
    public Page<Device> findAllDevice(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByDeviceName(String name) {
        Device device = deviceRepository.findDeviceByGwname(name);
        if (device == null) {
            return false;
        } else {
            deviceRepository.deleteDeviceByGwname(name);
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

    @Override
    public Device findByNameAndVersion(String name, String version) {
<<<<<<< HEAD
        return deviceRepository.findDeviceByGwnameAndVersion(name, version);
=======
        return deviceRepository.findDeviceByGwnameAndVersuon(name, version);
>>>>>>> 67fcb4651680482e2ad7177cb9ec9104faad5f23
    }
}
