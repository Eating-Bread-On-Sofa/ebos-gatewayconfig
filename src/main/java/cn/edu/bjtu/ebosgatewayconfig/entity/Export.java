package cn.edu.bjtu.ebosgatewayconfig.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="exportBackUp")
public class Export {
    private String gwname;
    private JSONArray info;
<<<<<<< HEAD
    private String version;

    public Export(String gwname, JSONArray info, String version) {
        this.gwname = gwname;
        this.info = info;
        this.version = version;
=======
    private String versuon;

    public Export(String gwname, JSONArray info, String versuon) {
        this.gwname = gwname;
        this.info = info;
        this.versuon = versuon;
>>>>>>> 67fcb4651680482e2ad7177cb9ec9104faad5f23
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

<<<<<<< HEAD
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
=======
    public String getVersuon() {
        return versuon;
    }

    public void setVersuon(String versuon) {
        this.versuon = versuon;
>>>>>>> 67fcb4651680482e2ad7177cb9ec9104faad5f23
    }

    @Override
    public String toString() {
        return "Export{" +
                "gwname='" + gwname + '\'' +
                ", info=" + info +
                '}';
    }
}
