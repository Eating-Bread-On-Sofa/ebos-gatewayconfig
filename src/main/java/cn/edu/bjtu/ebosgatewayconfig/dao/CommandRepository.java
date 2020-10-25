package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Command;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends MongoRepository<Command,String> {
    public Command findCommandByGwname(String name);
    @Override
    public Page<Command> findAll(Pageable pageable);
    void deleteCommandByGwname(String name);
    @Override
    List<Command> findAll();
<<<<<<< HEAD
    Command findCommandByGwnameAndVersion(String name, String version);
=======
    Command findCommandByGwnameAndVersuon(String name, String version);
>>>>>>> 67fcb4651680482e2ad7177cb9ec9104faad5f23

    List<Command> findCommandsByGwname(String name);
}
