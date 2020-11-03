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
@RequestMapping("/api/log")
@RestController
public class LogController {
    @Autowired
    LogService logService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GatewayService gatewayService;

    @ApiOperation(value = "显示最近100条日志", notes = "前端每次刚打开日志界面调用")
    @CrossOrigin
    @GetMapping("/logRecent")
    public List<Log> getRecentLog(){
        return logService.findRecent();
    }

    @ApiOperation(value = "按条件筛选日志")
    @CrossOrigin
    @RequestMapping(value = "/log/{firstDate}/{lastDate}/{source}/{category}/{operation}",method = RequestMethod.GET)
    public List<Log> getLog(@PathVariable Date firstDate, @PathVariable Date lastDate, @PathVariable String source, @PathVariable String category, @PathVariable String operation) throws ParseException {
        SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat ds =  new SimpleDateFormat("yyyy-MM-dd ");
        Date startDate = df.parse(ds.format(firstDate)+"00:00:00");
        Date endDate = df.parse(ds.format(lastDate)+"23:59:59");
        logService.info("retrieve","根据起始日期"+firstDate+",终止日期"+lastDate+"，查询微服务"+source+"日志等级为"+category+"且操作为"+operation+"的网关日志");
        return logService.find(startDate, endDate, source, category, operation);
    }

    @ApiOperation(value = "显示指定网关最近100条日志")
    @CrossOrigin
    @GetMapping("/logRecent/{name}")
    public List<Log> getRecentLog(@PathVariable String name){
        Gateway gateway = gatewayService.findGatewayByName(name);
        String url =  "http://"+ gateway.getIp() +":8090/api/logRecent";
        List<Log> logs;
        try{
            logs = restTemplate.getForObject(url,List.class);
        }catch (Exception e){
            logService.error("retrieve","无法连接至网关"+gateway.getName()+":"+gateway.getIp()+" 异常:"+e.toString());
            System.out.println(e);
            logs = new LinkedList<>();
        }
        return logs;
    }

    @ApiOperation(value = "对指定网关按条件筛选日志")
    @CrossOrigin
    @RequestMapping(value = "/test/{name}/{firstDate}/{lastDate}/{source}/{category}/{operation}",method = RequestMethod.GET)
    public List<Log> getLog(@PathVariable String name,@PathVariable Date firstDate, @PathVariable Date lastDate, @PathVariable String source, @PathVariable String category, @PathVariable String operation) throws ParseException {
        Gateway gateway = gatewayService.findGatewayByName(name);
        String url =  "http://"+ gateway.getIp() +":8090/api/log/" + firstDate + "/" + lastDate + "/" + source + "/" + category + "/" + operation;
        List<Log> logs;
        try{
            logs = restTemplate.getForObject(url,List.class);
        }catch (Exception e){
            logService.error("retrieve","无法连接至网关"+gateway.getName()+":"+gateway.getIp()+" 异常:"+e.toString());
            System.out.println(e);
            logs = new LinkedList<>();
        }
        return logs;
    }
}
