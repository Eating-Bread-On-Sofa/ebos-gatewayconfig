package cn.edu.bjtu.ebosgatewayconfig.controller;

import cn.edu.bjtu.ebosgatewayconfig.entity.*;
import cn.edu.bjtu.ebosgatewayconfig.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/gateway")
@RestController
public class GatewayConfigController {
    @Autowired
    GatewayConfigService gatewayConfigService;
    @Autowired
    CommandService commandService;
    @Autowired
    DeviceserviceService deviceserviceService;
    @Autowired
    DeviceprofileService deviceprofileService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    ExportService exportService;
    @Autowired
    RuleService ruleService;




    @PostMapping("/gatewayconfig")
    public String addGatewayConfig(@RequestBody GatewayConfig gatewayConfig){
        boolean flag = gatewayConfigService.addGatewayConfig(gatewayConfig);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @DeleteMapping("/gatewayconfig")
    public boolean deleteGwConfig(@RequestParam String name){
        boolean flag = gatewayConfigService.deleteByGatewayConfigName(name);
        return flag;
    }



    @PostMapping("/command")
    public String addCommand(@RequestBody Command command){
        boolean flag = commandService.addCommand(command);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @DeleteMapping("/command")
    public boolean deleteCommand(@RequestParam String name){
        boolean flag = commandService.deleteByCommandName(name);
        return flag;
    }

    @PostMapping("/deviceprofile")
    public String addDeviceprofile(@RequestBody Deviceprofile deviceprofile){
        boolean flag =deviceprofileService.addDeviceprofile(deviceprofile);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @DeleteMapping("/deviceprofile")
    public boolean deleteDeviceprofile(@RequestParam String name){
        boolean flag = deviceprofileService.deleteByDeviceprofileName(name);
        return flag;
    }
    @PostMapping("/deviceservice")
    public String addDeviceservice(@RequestBody Deviceservice deviceservice){
        boolean flag =deviceserviceService.addDeviceservice(deviceservice);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @DeleteMapping("/deviceservice")
    public boolean deleteDeviceservice(@RequestParam String name){
        boolean flag = deviceserviceService.deleteByDeviceserviceName(name);
        return flag;
    }

    @PostMapping("/device")
    public String addDevice(@RequestBody Device device){
        boolean flag =deviceService.addDevice(device);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @DeleteMapping("/device")
    public boolean deleteDevice(@RequestParam String name){
        boolean flag = deviceService.deleteByDeviceName(name);
        return flag;
    }

    @PostMapping("/export")
    public String addExport(@RequestBody Export export){
        boolean flag = exportService.addExport(export);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @DeleteMapping("/export")
    public boolean deleteExport(@RequestParam String name){
        boolean flag = exportService.deleteByExportName(name);
        return flag;
    }

    @PostMapping("/rule")
    public String addRule(@RequestBody Rule rule){
        boolean flag = ruleService.addRule(rule);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @DeleteMapping("/rule")
    public boolean deleteRule(@RequestParam String name){
        boolean flag = ruleService.deleteByRuleName(name);
        return flag;
    }
}
