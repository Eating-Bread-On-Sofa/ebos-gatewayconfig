package cn.edu.bjtu.ebosgatewayconfig.controller;

import cn.edu.bjtu.ebosgatewayconfig.entity.*;
import cn.edu.bjtu.ebosgatewayconfig.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Api(tags = "网关管理")
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
    @Autowired
    LogService logService;

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
    @CrossOrigin
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
        logService.info(null,"备份成功，version="+version);
        return "备份成功";
    }

    @CrossOrigin
    @PostMapping("/recover/ip/{ip}/version/{version}")
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
            logService.info(null,"向网关-"+gwname+"恢复 版本为"+version+"的备份 成功，结果为"+deviceResult);
            return deviceResult.toString();
        } else {
            return "此网关 ："+ ip + "未创建";
        }
//        }
    }
//    [
//        {"version":"1"},
//        {"version":"2"},
//        {"version":"3"}
//    ]
    @CrossOrigin
    @GetMapping("/version/{ip}")
    public JSONArray listVersion(@PathVariable String ip){
        JSONArray jsonArray = new JSONArray();
        String gwname = gatewayService.findGatewayByIp(ip).getName();
        List<Command> commandVersion = commandService.findCommandVersion(gwname);
        for (int i = 0; i < commandVersion.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            Command command = commandVersion.get(i);
            jsonObject.put("version", command.getVersuon());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @CrossOrigin
    @GetMapping("/state/{name}")
    public JSONObject listStateOne(@PathVariable String name) {
        Gateway gateway = gatewayService.findGatewayByName(name);
        String ip = gateway.getIp();
        String url =  "http://"+ ip +":8090/api/instance/state";
        try{
            JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);
            jsonObject.put("gatewayIP",ip);
            jsonObject.put("gatewayName",gateway.getName());
            return jsonObject;
        }catch (Exception e){
            logService.error(null,"无法连接至网关"+gateway.getName()+":"+ip+" 异常:"+e.toString());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("gatewayIP","无法连接 "+ip);
            jsonObject.put("gatewayName",gateway.getName()+" 已离线");
            return jsonObject;
        }
    }

    @CrossOrigin
    @GetMapping("/state")
    public JSONArray listState() {
        JSONArray jsonArray = new JSONArray();
        List<Gateway> allGateway = gatewayService.findAllGateway();
        for (Gateway gateway : allGateway) {
            String ip = gateway.getIp();
            String url =  "http://"+ ip +":8090/api/instance/state";
            try{
                JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);
                jsonObject.put("gatewayIP",ip);
                jsonObject.put("gatewayName",gateway.getName());
                jsonArray.add(jsonObject);
            }catch (Exception e){
                logService.error(null,"无法连接至网关"+gateway.getName()+":"+ip+" 异常:"+e.toString());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("gatewayIP","无法连接 "+ip);
                jsonObject.put("gatewayName",gateway.getName()+" 已离线");
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }

    @CrossOrigin
    @GetMapping()
    public List<Gateway> list() {
        return gatewayService.findAllGateway();
    }

    @CrossOrigin
    @PostMapping()
    public String addOne(@RequestBody Gateway gateway){
        gateway.setCreated(new Date());
        boolean flag = gatewayService.addGateway(gateway);
        if(flag){
            return "添加成功！";
        }else {
            return "添加失败！";
        }
    }

    @CrossOrigin
    @PutMapping()
    public void updateOne(@RequestBody Gateway gateway) {
        gatewayService.changeGatewayStatus(gateway);
    }

    @CrossOrigin
    @GetMapping("/{name}")
    public Gateway listOne(@PathVariable String name) {
        return gatewayService.findGatewayByName(name);
    }

    @CrossOrigin
    @DeleteMapping("/{name}")
    public boolean deleteOne(@PathVariable String name){
        boolean flag = gatewayService.deleteByGatewayName(name);
        return flag;
    }

    @ApiOperation(value = "查看指定创建时间范围的网关",notes = "范围 天数 int days")
    @ApiImplicitParam(name = "days",value = "查询天数范围,int类型",required = true, paramType = "query")
    @CrossOrigin
    @GetMapping("/days")
    public List<GwGroup> getRecentGateways(@RequestParam int days){
        List<GwGroup> gwGroups = new LinkedList<>();
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        for (int i = 0; i < days; i++) {
            calendar.add(Calendar.DATE, -1);
            Date start = calendar.getTime();
            List<Gateway> gws = gatewayService.findByCreatedTime(start,end);
            GwGroup gwGroup = new GwGroup();
            gwGroup.setCount(gws.size());
            gwGroup.setEnd(end);
            gwGroup.setStart(start);
            gwGroup.setGatewayList(gws);
            gwGroups.add(gwGroup);
            end = start;
        }
        return gwGroups;
    }
}
