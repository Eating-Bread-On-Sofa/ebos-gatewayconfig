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
//        Command command1 = commandRepository.findCommandByGwname(command.getGwname());
//        if (command1 == null) {
        commandRepository.save(command);
        return true;
//        } else {
//            commandRepository.save(command);
//            return false;
//        }
    }

    @Override
    public Command findCommandByName(String name) {
        return commandRepository.findCommandByGwname(name);
    }

    @Override
    public Page<Command> findAllCommand(Pageable pageable) {
        return commandRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByCommandName(String name) {
        Command command = commandRepository.findCommandByGwname(name);
        if (command == null) {
            return false;
        } else {
            commandRepository.deleteCommandByGwname(name);
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

    @Override
    public Command findByNameAndVersion(String name, String version) {
<<<<<<< HEAD
        return commandRepository.findCommandByGwnameAndVersion(name,version);
=======
        return commandRepository.findCommandByGwnameAndVersuon(name,version);
>>>>>>> 67fcb4651680482e2ad7177cb9ec9104faad5f23
    }

    @Override
    public List<Command> findCommandVersion(String name) {
        return commandRepository.findCommandsByGwname(name);
    }
}
