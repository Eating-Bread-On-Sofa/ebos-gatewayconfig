package cn.edu.bjtu.ebosgatewayconfig.model;

import com.alibaba.fastjson.JSONObject;

public class GwState {
    private String ip;
    private String name;
    private JSONObject state;

    public GwState(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getState() {
        return state;
    }

    public void setState(JSONObject state) {
        this.state = state;
    }
}
