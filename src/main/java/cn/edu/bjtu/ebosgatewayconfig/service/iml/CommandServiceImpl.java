package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.CommandRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.Command;
import cn.edu.bjtu.ebosgatewayconfig.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandServiceImpl implements CommandService {

    @Autowired
    CommandRepository commandRepository;

    @Override
    public boolean addCommand(Command command) {
        Command command1 = commandRepository.findCommandByName(command.getGwname());
        if (command1 == null) {
            commandRepository.save(command1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Command findCommandByName(String name) {
        return commandRepository.findCommandByName(name);
    }

    @Override
    public Page<Command> findAllCommand(Pageable pageable) {
        return commandRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByCommandName(String name) {
        Command command = commandRepository.findCommandByName(name);
        if (command == null) {
            return false;
        } else {
            commandRepository.deleteByCommandName(name);
            return true;
        }
    }

    @Override
    public List<Command> findAllCommand() {
        return commandRepository.findAll();
    }

    @Override
    public void changeCommandStatus(Command command) {
        commandRepository.save(command);
    }
}
