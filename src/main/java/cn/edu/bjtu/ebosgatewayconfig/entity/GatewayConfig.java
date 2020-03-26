package cn.edu.bjtu.ebosgatewayconfig.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GatewayConfig {
    private String name;

    @Id
    private String ip;

    private Command command;
    private Device device;
    private Deviceprofile deviceprofile;
    private Deviceservice deviceservice;
    private Export export;
    private Gateway gateway;
    private Rule rule;

    public GatewayConfig(String name, String ip, Command command, Device device, Deviceprofile deviceprofile, Deviceservice deviceservice, Export export, Gateway gateway, Rule rule) {
        this.name = name;
        this.ip = ip;
        this.command = command;
        this.device = device;
        this.deviceprofile = deviceprofile;
        this.deviceservice = deviceservice;
        this.export = export;
        this.gateway = gateway;
        this.rule = rule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Deviceprofile getDeviceprofile() {
        return deviceprofile;
    }

    public void setDeviceprofile(Deviceprofile deviceprofile) {
        this.deviceprofile = deviceprofile;
    }

    public Deviceservice getDeviceservice() {
        return deviceservice;
    }

    public void setDeviceservice(Deviceservice deviceservice) {
        this.deviceservice = deviceservice;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "GatewayConfig{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", command=" + command +
                ", device=" + device +
                ", deviceprofile=" + deviceprofile +
                ", deviceservice=" + deviceservice +
                ", export=" + export +
                ", gateway=" + gateway +
                ", rule=" + rule +
                '}';
    }
}
