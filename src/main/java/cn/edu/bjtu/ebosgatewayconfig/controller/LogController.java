package cn.edu.bjtu.ebosgatewayconfig.controller;

import cn.edu.bjtu.ebosgatewayconfig.entity.Log;
import cn.edu.bjtu.ebosgatewayconfig.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Api(tags = "日志")
@RequestMapping("/api/log")
@RestController
public class LogController {
    @Autowired
    LogService logService;
    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "显示最近100条日志", notes = "前端每次刚打开日志界面调用")
    @CrossOrigin
    @GetMapping("/recent")
    public List<Log> getRecentLog(String ip){
        String api = "http://" + ip + ":8090/api/logRecent";
        return restTemplate.getForObject(api,List.class);
    }

    @ApiOperation(value = "按条件筛选日志")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public List<Log> getLog(String ip, Date firstDate, Date lastDate, String source, String category, String operation) {
        String api = "http://" + ip + ":8090/api/log/" + firstDate + "/" + lastDate + "/" + source + "/" + category + "/" + operation;
        logService.info("retrieve","根据起始日期"+firstDate+",终止日期"+lastDate+"，查询微服务"+source+"日志等级为"+category+"且操作为"+operation+"的网关日志");
        return restTemplate.getForObject(api,List.class);
    }
}