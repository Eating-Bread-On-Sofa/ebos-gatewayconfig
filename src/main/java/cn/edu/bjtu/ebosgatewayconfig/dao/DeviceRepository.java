package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Device;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeviceRepository extends MongoRepository<Device,String> {
    public Device findDeviceByName(String name);
    @Override
    public Page<Device> findAll(Pageable pageable);
    void deleteByDeviceName(String name);
    @Override
    List<Device> findAll();
}
