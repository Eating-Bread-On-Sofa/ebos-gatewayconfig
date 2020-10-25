package cn.edu.bjtu.ebosgatewayconfig.model;

import java.util.List;

public class GwServState {
    private String ip;
    private String name;
    private boolean state;
    private List<FileDescriptor> fileDescriptorLists;

    public GwServState(String ip, String name, boolean state, List<FileDescriptor> fileDescriptorLists) {
        this.ip = ip;
        this.name = name;
        this.state = state;
        this.fileDescriptorLists = fileDescriptorLists;
    }

    public String getIp() { return ip; }

    public void setIp(String ip) { this.ip = ip; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean getState() { return state; }

    public void setState(boolean state) { this.state = state; }

    public List<FileDescriptor> getFileDescriptorLists() { return fileDescriptorLists; }

    public void setFileDescriptorLists(List<FileDescriptor> fileDescriptorLists) { this.fileDescriptorLists = fileDescriptorLists; }
}
