package cn.edu.bjtu.ebosgatewayconfig.controller;

import cn.edu.bjtu.ebosgatewayconfig.entity.*;
import cn.edu.bjtu.ebosgatewayconfig.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     *      *         command : 0 or 1; String
     *      *         device : { deviceIp : 0 or 设备管理ip};
     *      *         deviceprofile : 0 or 1;
     *      *         deviceservice : 0 or 1;
     *      *         export : 0 or 1;
     *      *     },
     *     ip2 : {
     *
     *     }
     * }
     */
    @PostMapping("/copy/{ip}")
    public String copyInfo(@PathVariable String ip,@RequestBody JSONObject jsonObject){
//        Set<String> strings = jsonObject.keySet();
//        for (String ip : strings) {
        String version = new Date().toString();
        String url = "http://"+ ip +":8090/api/instance";
        Gateway gateway = gatewayService.findGatewayByIp(ip);
        if (gateway == null) {
            return "此网关 ："+ ip + "未创建";
        }
        String gwname = gateway.getName();
        JSONObject res = restTemplate.getForObject(url, JSONObject.class);
        JSONArray commands = res.getJSONArray("command");
        JSONArray devices =  res.getJSONArray("device");
        JSONArray deviceprofiles = res.getJSONArray("deviceprofile");
        JSONArray deviceservices = res.getJSONArray("deviceservice");
        JSONArray exports = res.getJSONArray("export");
        if (jsonObject.getString("command").equals("1")) {
            commandService.addCommand(new Command(gwname , commands,version));
        }
        if (jsonObject.getString("device").equals("1")) {
            deviceService.addDevice(new Device(gwname, devices,version));
        }
        if (jsonObject.getString("deviceprofile").equals("1")) {
            deviceprofileService.addDeviceprofile(new Deviceprofile(gwname, deviceprofiles,version));
        }
        if (jsonObject.getString("deviceservice").equals("1")) {
            deviceserviceService.addDeviceservice(new Deviceservice(gwname, deviceservices,version));
        }
        if (jsonObject.getString("export").equals("1")) {
            exportService.addExport(new Export(gwname, exports,version));
        }
//        }
        return "备份成功";
    }

    @PostMapping("/recover/{ip}/{version}")
    public String recoverInfo(@PathVariable("ip") String ip,@PathVariable("version") String version,@RequestBody JSONObject jsonObject){
//        Set<String> strings = jsonObject.keySet();
        JSONArray deviceResult= new JSONArray();
//        for (String ip : strings) {
        if (gatewayService.findGatewayByIp(ip) != null) {
            JSONObject result = new JSONObject();
            String gwname = gatewayService.findGatewayByIp(ip).getName();
            if (jsonObject.getString("command").equals("1")) {
                JSONArray commandArray = commandService.findByNameAndVersion(gwname, version).getInfo();
                result.put("command", commandArray);
            }
            if (!jsonObject.getJSONObject("device").getString("deviceIp").equals("0")) {
                JSONArray deviceArr = deviceService.findByNameAndVersion(gwname, version).getInfo();
                String deviceIp = jsonObject.getJSONObject("device").getString("deviceIp");
                String deviceUrl = "http://" + deviceIp + ":8081/api/device/recover/"+ ip;
                JSONObject jsonObject1 = restTemplate.postForObject(deviceUrl, deviceArr, JSONObject.class);
                deviceResult.add(jsonObject1);
            }
            if (jsonObject.getString("deviceprofile").equals("1")) {
                JSONArray deviceProfileArr = deviceprofileService.findByNameAndVersion(gwname, version).getInfo();
                result.put("deviceprofile", deviceProfileArr);
            }
            if (jsonObject.getString("deviceservice").equals("1")) {
                JSONArray deviceserviceArr = deviceserviceService.findByNameAndVersion(gwname, version).getInfo();
                result.put("deviceservice", deviceserviceArr);
            }
            if (jsonObject.getString("export").equals("1")) {
                JSONArray exportArr = exportService.findByNameAndVersion(gwname, version).getInfo();
                result.put("export", exportArr);
            }
            String url = "http://" + ip + ":8090/api/instance";
            deviceResult.add(restTemplate.postForObject(url, result, JSONObject.class));
            return deviceResult.toString();
        } else {
            return "此网关 ："+ ip + "未创建";
        }
//        }
    }
//    {
//        "version1":"",
//        "version2":"",
//        "version3":""
//    }
    @GetMapping("/version/{ip}")
    public JSONObject listVersion(@PathVariable String ip){
        JSONObject jsonObject = new JSONObject();
        String gwname = gatewayService.findGatewayByIp(ip).getName();
        List<Command> commandVersion = commandService.findCommandVersion(gwname);
        for (int i = 0; i < commandVersion.size(); i++) {
            Command command = commandVersion.get(i);
            jsonObject.put("version" + i, command.getVersuon());
        }
        return jsonObject;
    }


    @GetMapping("/state/{name}")
    public JSONObject listStateOne(@PathVariable String name) {
        JSONObject jsonObject = new JSONObject();
        Gateway gateway = gatewayService.findGatewayByName(name);
        if (gateway == null) {
            return (JSONObject) jsonObject.put("Erro", "No Such gateway");
        } else {
            String ip = gateway.getIp();
            String url =  "http://"+ ip +":8090/api/instance/state";
            return restTemplate.getForObject(url, JSONObject.class);
        }
    }

    @GetMapping("/state")
    public JSONArray listState() {
        JSONArray jsonArray = new JSONArray();
        List<Gateway> allGateway = gatewayService.findAllGateway();
        for (Gateway gateway : allGateway) {
            String ip = gateway.getIp();
            String url =  "http://"+ ip +":8090/api/instance/state";
            JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);
            if (jsonObject == null) {
                jsonObject = new JSONObject();
                jsonObject.put("网关 ip:" + ip, "不存在,请确认");
                jsonArray.add(jsonObject);
            } else {
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
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
