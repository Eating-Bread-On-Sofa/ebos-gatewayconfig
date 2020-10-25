package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
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
<<<<<<< HEAD
    Deviceprofile findDeviceprofileByGwnameAndVersion(String name, String version);
=======
    Deviceprofile findDeviceprofileByGwnameAndVersuon(String name, String version);
>>>>>>> 67fcb4651680482e2ad7177cb9ec9104faad5f23
}
