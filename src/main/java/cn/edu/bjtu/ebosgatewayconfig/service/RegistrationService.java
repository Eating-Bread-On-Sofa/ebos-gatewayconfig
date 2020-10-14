package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Registration;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    String registration(Registration registration ,String ip);
}
