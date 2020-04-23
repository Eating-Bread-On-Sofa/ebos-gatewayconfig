package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.DeviceserviceRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import cn.edu.bjtu.ebosgatewayconfig.service.DeviceserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceserviceServiceImpl implements DeviceserviceService {

    @Autowired
    DeviceserviceRepository deviceserviceRepository;

    @Override
    public boolean addDeviceservice(Deviceservice deviceservice) {
//        Deviceservice deviceservice1 = deviceserviceRepository.findDeviceserviceByGwname(deviceservice.getGwname());
//        if (deviceservice1 == null) {
            deviceserviceRepository.save(deviceservice);
            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public Deviceservice findDeviceserviceByName(String name) {
        return deviceserviceRepository.findDeviceserviceByGwname(name);
    }

    @Override
    public Page<Deviceservice> findAllDeviceservice(Pageable pageable) {
        return deviceserviceRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByDeviceserviceName(String name) {
        Deviceservice deviceservice = deviceserviceRepository.findDeviceserviceByGwname(name);
        if (deviceservice == null) {
            return false;
        } else {
            deviceserviceRepository.deleteDeviceserviceByGwname(name);
            return true;
        }
    }

    @Override
    public List<Deviceservice> findAllDeviceservice() {
        return deviceserviceRepository.findAll();
    }

    @Override
    public void changeDeviceserviceStatus(Deviceservice deviceservice) {
        deviceserviceRepository.save(deviceservice);
    }

    @Override
    public Deviceservice findByNameAndVersion(String name, String version) {
        return deviceserviceRepository.findDeviceserviceByGwnameAndVersuon(name, version);
    }
}
