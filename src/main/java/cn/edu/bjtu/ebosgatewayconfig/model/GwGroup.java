package cn.edu.bjtu.ebosgatewayconfig.model;

import cn.edu.bjtu.ebosgatewayconfig.entity.Gateway;

import java.util.Date;
import java.util.List;

public class GwGroup {
    private Date start;
    private Date end;
    private List<Gateway> gatewayList;
    private int count;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<Gateway> getGatewayList() {
        return gatewayList;
    }

    public void setGatewayList(List<Gateway> gatewayList) {
        this.gatewayList = gatewayList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
