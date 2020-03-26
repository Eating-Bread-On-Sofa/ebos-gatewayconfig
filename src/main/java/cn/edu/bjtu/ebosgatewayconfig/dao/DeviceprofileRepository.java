package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Command;
import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeviceprofileRepository extends MongoRepository<Deviceprofile,String> {
    public Deviceprofile findDeviceprofileByName(String name);
    @Override
    public Page<Deviceprofile> findAll(Pageable pageable);
    void deleteByDeviceprofileName(String name);
    @Override
    List<Deviceprofile> findAll();
}
