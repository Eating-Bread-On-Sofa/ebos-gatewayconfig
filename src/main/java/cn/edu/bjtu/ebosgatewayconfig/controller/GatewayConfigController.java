package cn.edu.bjtu.ebosgatewayconfig.controller;

import cn.edu.bjtu.ebosgatewayconfig.entity.*;
import cn.edu.bjtu.ebosgatewayconfig.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Set;

@RequestMapping("/api/gateway")
@RestController
public class GatewayConfigController {
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
    @Autowired
    GatewayService gatewayService;
    @Autowired
    RestTemplate restTemplate;

    /**
     * {
     *     ip1 : {
     *         command : 0 or 1; String
     *         device : 0 or 1;
     *         deviceprofile : 0 or 1;
     *         deviceservice : 0 or 1;
     *         export : 0 or 1;
     *     },
     *     ip2 : {
     *
     *     }
     * }
     */
    @PostMapping("/copy")
    public void copyInfo(@RequestBody JSONObject jsonObject){
        Set<String> strings = jsonObject.keySet();
        for (String ip : strings) {
            String url = "http://"+ ip +":8082/api/command";
            String gwname = gatewayService.findGatewayByIp(ip).getName();
            JSONObject res = restTemplate.getForObject(url, JSONObject.class);
            JSONArray commands = res.getJSONArray("command");
            JSONArray devices =  res.getJSONArray("device");
            JSONArray deviceprofiles = res.getJSONArray("deviceprofile");
            JSONArray deviceservices = res.getJSONArray("deviceservice");
            JSONArray exports = res.getJSONArray("export");
            if (jsonObject.getJSONObject(ip).getString("command").equals("1")) {
                commandService.addCommand(new Command(gwname , commands));
            }
            if (jsonObject.getJSONObject(ip).getString("device").equals("1")) {
                deviceService.addDevice(new Device(gwname, devices));
            }
            if (jsonObject.getJSONObject(ip).getString("deviceprofile").equals("1")) {
                deviceprofileService.addDeviceprofile(new Deviceprofile(gwname, deviceprofiles));
            }
            if (jsonObject.getJSONObject(ip).getString("deviceservice").equals("1")) {
                deviceserviceService.addDeviceservice(new Deviceservice(gwname, deviceservices));
            }
            if (jsonObject.getJSONObject(ip).getString("device").equals("1")) {
                exportService.addExport(new Export(gwname, exports));
            }
        }
    }

    @PostMapping("/recover")
    public void recoverInfo(@RequestBody JSONObject jsonObject){
        Set<String> strings = jsonObject.keySet();
        for (String ip : strings) {
            if (gatewayService.findGatewayByIp(ip) != null) {
                JSONObject result = new JSONObject();
                String gwname = gatewayService.findGatewayByIp(ip).getName();
                if (jsonObject.getJSONObject(ip).getString("command").equals("1")) {
                    JSONArray commandArray = commandService.findCommandByName(gwname).getInfo();
                    result.put("command", commandArray);
                }
//            if (jsonObject.getJSONObject(ip).getString("device").equals("1")) {
//                JSONArray  = deviceService.findDeviceByName(gwname).getInfo();
//            }
                if (jsonObject.getJSONObject(ip).getString("deviceprofile").equals("1")) {
                    JSONArray deviceProfileArr = deviceprofileService.findDeviceprofileByName(gwname).getInfo();
                    result.put("deviceprofile", deviceProfileArr);
                }
                if (jsonObject.getJSONObject(ip).getString("deviceservice").equals("1")) {
                    JSONArray deviceserviceArr = deviceserviceService.findDeviceserviceByName(gwname).getInfo();
                    result.put("deviceservice", deviceserviceArr);
                }
                if (jsonObject.getJSONObject(ip).getString("export").equals("1")) {
                    JSONArray exportArr = exportService.findExportByName(gwname).getInfo();
                    result.put("export", exportArr);
                }
                String url = "http://" + ip + ":8082/api/instance";
                restTemplate.postForObject(url, result, JSONObject.class);
            } else {
                System.out.println("gateway : " + ip +"不存在");
            }
        }
    }

    @GetMapping()
    public JSONArray list() {
        return new JSONArray(new ArrayList<Object>( gatewayService.findAllGateway()));
    }

    @PostMapping()
    public String addOne(@RequestBody JSONObject jsonObject){
        Gateway gateway = JSONObject.toJavaObject(jsonObject, Gateway.class);
        boolean flag = gatewayService.addGateway(gateway);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }
    @PutMapping()
    public void updateOne(@RequestBody JSONObject jsonObject) {
        Gateway gateway = JSONObject.toJavaObject(jsonObject, Gateway.class);
        gatewayService.changeGatewayStatus(gateway);
    }

    @GetMapping("/{name}")
    public JSONObject listOne(@PathVariable String name) {
        return (JSONObject) JSONObject.toJSON(gatewayService.findGatewayByName(name));
    }
    @DeleteMapping("/{name}")
    public boolean deleteOne(@PathVariable String name){
        boolean flag = gatewayService.deleteByGatewayName(name);
        return flag;
    }

}
