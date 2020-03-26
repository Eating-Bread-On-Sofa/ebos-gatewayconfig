package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Command;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommandRepository extends MongoRepository<Command,String> {
    public Command findCommandByName(String name);
    @Override
    public Page<Command> findAll(Pageable pageable);
    void deleteByCommandName(String name);
    @Override
    List<Command> findAll();
}
