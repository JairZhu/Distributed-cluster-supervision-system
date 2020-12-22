package org.jairzhu.client.domain;

import com.alibaba.fastjson.JSON;

public class Client {
    private boolean isOnline;
    private String ip;
    private String name;
    private String os;
    private int cpunum;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getCpu() {
        return cpunum;
    }

    public void setCpu(int cpu) {
        this.cpunum = cpu;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String IP) {
        this.ip = IP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
