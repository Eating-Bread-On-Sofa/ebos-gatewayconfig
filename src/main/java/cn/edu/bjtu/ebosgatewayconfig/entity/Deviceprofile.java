package cn.edu.bjtu.ebosgatewayconfig.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="profileBackUp")
public class Deviceprofile {
    private String gwname;
    private JSONArray info;
    private String version;

    public Deviceprofile(String gwname, JSONArray info, String version) {
        this.gwname = gwname;
        this.info = info;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Deviceprofile{" +
                "gwname='" + gwname + '\'' +
                ", info=" + info +
                '}';
    }
}
