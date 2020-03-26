package cn.edu.bjtu.ebosgatewayconfig.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document
public class Rule {
    private String gwname;
    private JSONArray info;

    public Rule(String gwname, JSONArray info) {
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
        return "Rule{" +
                "gwname='" + gwname + '\'' +
                ", info=" + info +
                '}';
    }
}
