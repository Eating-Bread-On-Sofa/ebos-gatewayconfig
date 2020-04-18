package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Device;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceserviceRepository extends MongoRepository<Deviceservice,String> {
    public Deviceservice findDeviceserviceByGwname(String name);
    @Override
    public Page<Deviceservice> findAll(Pageable pageable);
    void deleteDeviceserviceByGwname(String name);
    @Override
    List<Deviceservice> findAll();
    Deviceservice findDeviceserviceByGwnameAndVersuon(String name,String version);
}
