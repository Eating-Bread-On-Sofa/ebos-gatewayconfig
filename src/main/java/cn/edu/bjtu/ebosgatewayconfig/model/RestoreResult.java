package cn.edu.bjtu.ebosgatewayconfig.model;

import com.alibaba.fastjson.JSONObject;

public class RestoreResult {
    private JSONObject edgeXDevice;
    private String command;
    private JSONObject edgeXService;
    private JSONObject edgeXExport;

    public JSONObject getEdgeXDevice() {
        return edgeXDevice;
    }

    public void setEdgeXDevice(JSONObject edgeXDevice) {
        this.edgeXDevice = edgeXDevice;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public JSONObject getEdgeXService() {
        return edgeXService;
    }

    public void setEdgeXService(JSONObject edgeXService) {
        this.edgeXService = edgeXService;
    }

    public JSONObject getEdgeXExport() {
        return edgeXExport;
    }

    public void setEdgeXExport(JSONObject edgeXExport) {
        this.edgeXExport = edgeXExport;
    }
}
