package cn.edu.bjtu.ebosgatewayconfig.controller;

import cn.edu.bjtu.ebosgatewayconfig.entity.*;
import cn.edu.bjtu.ebosgatewayconfig.model.GwGroup;
import cn.edu.bjtu.ebosgatewayconfig.model.GwState;
import cn.edu.bjtu.ebosgatewayconfig.model.RestoreResult;
import cn.edu.bjtu.ebosgatewayconfig.model.Version;
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

    @ApiOperation(value = "备份指定网关")
    @CrossOrigin
    @GetMapping("/copy/{ip}")
    public String copyInfo(@PathVariable String ip) {
        String version = new Date().toString();
        String url = "http://" + ip + ":8090/api/instance";
        Gateway gateway = gatewayService.findGatewayByIp(ip);
        if (gateway == null) {
            return "此网关 ：" + ip + "未创建";
        }
        String gwname = gateway.getName();
        try {
            JSONObject res = restTemplate.getForObject(url, JSONObject.class);
            JSONArray commands = res.getJSONArray("command");
            JSONArray devices = res.getJSONArray("edgeXDevice");
            JSONArray deviceprofiles = res.getJSONArray("edgeXProfile");
            JSONArray deviceservices = res.getJSONArray("edgeXService");
            JSONArray exports = res.getJSONArray("edgeXExport");
            commandService.addCommand(new Command(gwname, commands, version));
            deviceService.addDevice(new Device(gwname, devices, version));
            deviceprofileService.addDeviceprofile(new Deviceprofile(gwname, deviceprofiles, version));
            deviceserviceService.addDeviceservice(new Deviceservice(gwname, deviceservices, version));
            exportService.addExport(new Export(gwname, exports, version));
            logService.info(null, "备份成功，version=" + version);
            return "备份成功，version=" + version;
        }catch (Exception e){
            logService.error(null, "备份失败："+e.toString());
            return "备份失败："+e.getMessage();
        }

    }

    @ApiOperation(value = "恢复指定网关")
    @CrossOrigin
    @PostMapping("/recover/ip/{ip}/version/{version}")
    public RestoreResult recoverInfo(@PathVariable("ip") String ip, @PathVariable("version") String version) {
        RestoreResult restoreResult = new RestoreResult();
        if (gatewayService.findGatewayByIp(ip) != null) {
            String gwname = gatewayService.findGatewayByIp(ip).getName();

            JSONObject postToGateway = new JSONObject();

            JSONArray commandArray = commandService.findByNameAndVersion(gwname, version).getInfo();
            postToGateway.put("command", commandArray);

            JSONArray deviceProfileArr = deviceprofileService.findByNameAndVersion(gwname, version).getInfo();
            postToGateway.put("edgeXProfile", deviceProfileArr);

            JSONArray deviceServiceArr = deviceserviceService.findByNameAndVersion(gwname, version).getInfo();
            postToGateway.put("edgeXService", deviceServiceArr);

            JSONArray exportArr = exportService.findByNameAndVersion(gwname, version).getInfo();
            postToGateway.put("edgeXExport", exportArr);

            String url = "http://" + ip + ":8090/api/instance";
            try {
                JSONObject jsonObject = restTemplate.postForObject(url, postToGateway, JSONObject.class);
                restoreResult.setCommand(jsonObject.getString("command"));
                restoreResult.setEdgeXExport(jsonObject.getJSONObject("edgeXExport"));
                restoreResult.setEdgeXService(jsonObject.getJSONObject("edgeXService"));
            } catch (Exception e) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("exception", e.getMessage());
                restoreResult.setCommand(e.getMessage());
                restoreResult.setEdgeXExport(jsonObject);
                restoreResult.setEdgeXService(jsonObject);
            }

            JSONArray deviceArr = deviceService.findByNameAndVersion(gwname, version).getInfo();
            String deviceUrl = "http://localhost:8081/api/device/recover/" + ip;
            try {
                JSONObject deviceRes = restTemplate.postForObject(deviceUrl, deviceArr, JSONObject.class);
                restoreResult.setEdgeXDevice(deviceRes);
            } catch (Exception e) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("exception", e.getMessage());
                restoreResult.setEdgeXDevice(jsonObject);
            }

            logService.info(null, "向网关-" + gwname + "恢复 版本为" + version + "的备份，结果为" + restoreResult);
            return restoreResult;
        } else {
            return new RestoreResult();
        }
    }

    @ApiOperation(value = "获取指定网关备份版本列表")
    @CrossOrigin
    @GetMapping("/version/{ip}")
    public List<Version> listVersion(@PathVariable String ip){
        List<Version> versionList = new LinkedList<>();
        String gwname = gatewayService.findGatewayByIp(ip).getName();
        List<Command> commandVersion = commandService.findCommandVersion(gwname);
        for (int i = 0; i < commandVersion.size(); i++) {
            Command command = commandVersion.get(i);
            versionList.add(new Version(command.getVersuon()));
        }
        return versionList;
    }

    @ApiOperation(value = "检测指定网关状态")
    @CrossOrigin
    @GetMapping("/state/{name}")
    public GwState listStateOne(@PathVariable String name) {
        Gateway gateway = gatewayService.findGatewayByName(name);
        String url =  "http://"+ gateway.getIp() +":8090/api/instance/state";
        GwState gwState = new GwState(gateway.getIp(),gateway.getName());
        try{
            JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);
            gwState.setState(jsonObject);
        }catch (Exception e){
            logService.error(null,"无法连接至网关"+gateway.getName()+":"+gateway.getIp()+" 异常:"+e.toString());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("无法连接",gateway.getIp());
            jsonObject.put("已离线",gateway.getName());
            gwState.setState(jsonObject);
        }
        return gwState;
    }

    @ApiOperation(value = "检测所有网关状态")
    @CrossOrigin
    @GetMapping("/state")
    public List<GwState> listState() {
        List<GwState> gwStateList = new LinkedList<>();
        List<Gateway> allGateway = gatewayService.findAllGateway();
        for (Gateway gateway : allGateway) {
            String ip = gateway.getIp();
            String url =  "http://"+ ip +":8090/api/instance/state";
            GwState gwState = new GwState(ip,gateway.getName());
            try{
                JSONObject jsonObject = restTemplate.getForObject(url, JSONObject.class);
                gwState.setState(jsonObject);
            }catch (Exception e){
                logService.error(null,"无法连接至网关"+gateway.getName()+":"+ip+" 异常:"+e.toString());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("无法连接",ip);
                jsonObject.put("已离线",gateway.getName());
                gwState.setState(jsonObject);
            }
            gwStateList.add(gwState);
        }
        return gwStateList;
    }

    @ApiOperation(value = "查看所有网关")
    @CrossOrigin
    @GetMapping()
    public List<Gateway> list() {
        return gatewayService.findAllGateway();
    }

    @ApiOperation(value = "添加网关")
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

    @ApiOperation(value = "更改网关")
    @CrossOrigin
    @PutMapping()
    public void updateOne(@RequestBody Gateway gateway) {
        gatewayService.changeGatewayStatus(gateway);
    }

    @ApiOperation(value = "查看指定网关")
    @CrossOrigin
    @GetMapping("/{name}")
    public Gateway listOne(@PathVariable String name) {
        return gatewayService.findGatewayByName(name);
    }

    @ApiOperation(value = "删除指定网关")
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

    @ApiOperation(value = "微服务健康检测")
    @CrossOrigin
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
