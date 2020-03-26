package cn.edu.bjtu.ebosgatewayconfig.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Deviceservice {
    private String gwname;
    private JSONArray info;

    public Deviceservice(String gwname, JSONArray info) {
        this.gwname = gwname;
        this.info = info;
    }

    public String getGwname() {
        return gwname;
    }

    public void setGwname(String gwname) {
        this.gwname = gwname;
    }

    public JSONArray getInfo() {
        return info;
    }

    public void setInfo(JSONArray info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Deviceservice{" +
                "gwname='" + gwname + '\'' +
                ", info=" + info +
                '}';
    }
}
