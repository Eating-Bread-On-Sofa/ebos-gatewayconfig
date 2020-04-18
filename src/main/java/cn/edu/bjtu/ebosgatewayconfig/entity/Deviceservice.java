package cn.edu.bjtu.ebosgatewayconfig.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;

@Document
public class Deviceservice {
    private String gwname;
    private JSONArray info;
    private String versuon;


    public Deviceservice(String gwname, JSONArray info, String versuon) {
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
        return "Deviceservice{" +
                "gwname='" + gwname + '\'' +
                ", info=" + info +
                '}';
    }
}
