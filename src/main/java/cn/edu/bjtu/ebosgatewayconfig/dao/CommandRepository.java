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
    Command findCommandByGwnameAndVersuon(String name, String version);

    List<Command> findCommandsByGwname(String name);
}
