package cn.edu.bjtu.ebosgatewayconfig.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Export {
    private String gwname;
    private JSONArray info;

    public Export(String gwname, JSONArray info) {
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
        return "Export{" +
                "gwname='" + gwname + '\'' +
                ", info=" + info +
                '}';
    }
}
