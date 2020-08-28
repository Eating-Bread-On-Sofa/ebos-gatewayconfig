package cn.edu.bjtu.ebosgatewayconfig.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="profileBackUp")
public class Deviceprofile {
    private String gwname;
    private JSONArray info;
    private String versuon;

    public Deviceprofile(String gwname, JSONArray info, String versuon) {
        this.gwname = gwname;
        this.info = info;
        this.versuon = versuon;
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

    public String getVersuon() {
        return versuon;
    }

    public void setVersuon(String versuon) {
        this.versuon = versuon;
    }

    @Override
    public String toString() {
        return "Deviceprofile{" +
                "gwname='" + gwname + '\'' +
                ", info=" + info +
                '}';
    }
}
