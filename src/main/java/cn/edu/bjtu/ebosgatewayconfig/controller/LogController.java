package cn.edu.bjtu.ebosgatewayconfig.controller;

import cn.edu.bjtu.ebosgatewayconfig.entity.Gateway;
import cn.edu.bjtu.ebosgatewayconfig.entity.Log;
import cn.edu.bjtu.ebosgatewayconfig.service.GatewayService;
import cn.edu.bjtu.ebosgatewayconfig.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Api(tags = "日志")
@RequestMapping("/api")
@RestController
public class LogController {
    @Autowired
    LogService logService;
    @Autowired
    GatewayService gatewayService;
    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "测试用API", notes = "写一堆乱七八糟的日志进去")
    @CrossOrigin
    @PostMapping ("/logtest")
    public String logTest(){
        logService.debug("create","gwinst1");
        logService.info("delete","gwinst2");
        logService.warn("update","gwinst3");
        logService.error("retrieve","gwinst4");
        logService.debug("retrieve","增");
        logService.info("update","删");
        logService.warn("delete","改");
        logService.error("create","查");
        return "成功";
    }

    @ApiOperation(value = "测试用API", notes = "返回所有日志，爆卡警告")
    @CrossOrigin
    @GetMapping("/logFindAll")
    public List<Log> loggerTest(){
        return logService.findAll();
    }

    @ApiOperation(value = "显示最近100条日志", notes = "前端每次刚打开日志界面调用")
    @CrossOrigin
    @GetMapping("/logRecent")
    public List<Log> getRecentLog(){
        return logService.findRecent();
    }

    @ApiOperation(value = "按条件筛选日志")
    @CrossOrigin
    @RequestMapping(value = "/log",method = RequestMethod.GET)
    public List<Log> getLog(Date firstDate, Date lastDate, String source, String category, String operation) throws ParseException {
        SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat ds =  new SimpleDateFormat("yyyy-MM-dd ");
        Date startDate = df.parse(ds.format(firstDate)+"00:00:00");
        Date endDate = df.parse(ds.format(lastDate)+"23:59:59");
        logService.info("retrieve","根据起始日期"+firstDate+",终止日期"+lastDate+"，查询微服务"+source+"日志等级为"+category+"且操作为"+operation+"的本地运维日志");
        return logService.find(startDate, endDate, source, category,operation);
    }

    @ApiOperation(value = "显示指定网关最近100条日志")
    @CrossOrigin
    @GetMapping("/logRecent/{name}")
    public List<Log> getGatewayRecentLog(@PathVariable String name){
        Gateway gateway = gatewayService.findGatewayByName(name);
        String url =  "http://"+ gateway.getIp() +":8090/api/logRecent";
        List<Log> logs = new LinkedList<>();
        try{
            logs= restTemplate.getForObject(url,List.class);
        }catch (Exception e){
            logService.error("retrieve","无法连接至网关"+gateway.getName()+":"+gateway.getIp()+" 异常:"+e.toString());
        }
        return logs;
    }

    @ApiOperation(value = "指定网关按条件筛选日志")
    @CrossOrigin
    @RequestMapping(value = "/log/{name}",method = RequestMethod.GET)
    public List<Log> getGatewayLog(@PathVariable String name, @RequestParam Date firstDate, @RequestParam Date lastDate, @RequestParam String source, @PathVariable String category,@RequestParam String operation){
        Gateway gateway = gatewayService.findGatewayByName(name);
        String url =  "http://"+ gateway.getIp() +":8090/api/log?firstDate="+firstDate+"&lastDate="+lastDate+"&source="+source+"&category="+category+"&operation="+operation;
        List<Log> logs = new LinkedList<>();
        try{
            logs= restTemplate.getForObject(url,List.class);
        }catch (Exception e){
            logService.error("retrieve","无法连接至网关"+gateway.getName()+":"+gateway.getIp()+ "异常:"+e.toString());
        }
        return logs;
    }
}
