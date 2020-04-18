package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Command;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
import jdk.nashorn.internal.runtime.doubleconv.DoubleConversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceprofileRepository extends MongoRepository<Deviceprofile,String> {
    public Deviceprofile findDeviceprofileByGwname(String name);
    @Override
    public Page<Deviceprofile> findAll(Pageable pageable);
    void deleteDeviceprofileByGwname(String name);
    @Override
    List<Deviceprofile> findAll();
    Deviceprofile findDeviceprofileByGwnameAndVersuon(String name, String version);
}
