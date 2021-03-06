package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Command;
import cn.edu.bjtu.ebosgatewayconfig.entity.GatewayConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommandService {
    public boolean addCommand(Command command);
    public Command findCommandByName(String name);
    public Page<Command> findAllCommand(Pageable pageable);
    boolean deleteByCommandName(String name);
    public List<Command> findAllCommand();
    public void changeCommandStatus(Command command);
}
