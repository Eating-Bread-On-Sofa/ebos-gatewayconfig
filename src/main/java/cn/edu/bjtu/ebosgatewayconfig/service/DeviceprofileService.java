package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceprofileService {
    public boolean addDeviceprofile(Deviceprofile deviceprofile);
    public Deviceprofile findDeviceprofileByName(String name);
    public Page<Deviceprofile> findAllDeviceprofile(Pageable pageable);
    boolean deleteByDeviceprofileName(String name);
    public List<Deviceprofile> findAllDeviceprofile();
    public void changeDeviceprofileStatus(Deviceprofile deviceprofile);
    public Deviceprofile findByNameAndVersion(String name, String version);
}
