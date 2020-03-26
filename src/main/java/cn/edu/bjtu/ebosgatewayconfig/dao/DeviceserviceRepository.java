package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Device;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeviceserviceRepository extends MongoRepository<Deviceservice,String> {
    public Deviceservice findDeviceserviceByName(String name);
    @Override
    public Page<Deviceservice> findAll(Pageable pageable);
    void deleteByDeviceserviceName(String name);
    @Override
    List<Deviceservice> findAll();
}
