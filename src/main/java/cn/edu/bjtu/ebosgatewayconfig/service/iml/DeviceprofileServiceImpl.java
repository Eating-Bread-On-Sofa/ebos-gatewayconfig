package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.DeviceprofileRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
import cn.edu.bjtu.ebosgatewayconfig.service.DeviceprofileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceprofileServiceImpl implements DeviceprofileService {

    @Autowired
    DeviceprofileRepository deviceprofileRepository;
    @Override
    public boolean addDeviceprofile(Deviceprofile deviceprofile) {
//        Deviceprofile deviceprofile1 = deviceprofileRepository.findDeviceprofileByGwname(deviceprofile.getGwname());
//        if (deviceprofile1 == null) {
        deviceprofileRepository.save(deviceprofile);
        return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public Deviceprofile findDeviceprofileByName(String name) {
        return deviceprofileRepository.findDeviceprofileByGwname(name);
    }

    @Override
    public Page<Deviceprofile> findAllDeviceprofile(Pageable pageable) {

        return deviceprofileRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByDeviceprofileName(String name) {
        Deviceprofile deviceprofile = deviceprofileRepository.findDeviceprofileByGwname(name);
        if (deviceprofile == null) {
            return false;
        } else {
            deviceprofileRepository.deleteDeviceprofileByGwname(name);
            return true;
        }
    }

    @Override
    public List<Deviceprofile> findAllDeviceprofile() {

        return deviceprofileRepository.findAll();
    }

    @Override
    public void changeDeviceprofileStatus(Deviceprofile deviceprofile) {
        deviceprofileRepository.save(deviceprofile);
    }

    @Override
    public Deviceprofile findByNameAndVersion(String name, String version) {
        return deviceprofileRepository.findDeviceprofileByGwnameAndVersion(name,version);
    }
}
