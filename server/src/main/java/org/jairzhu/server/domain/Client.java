package org.jairzhu.server.domain;

import com.alibaba.fastjson.JSON;

public class Client {
    private boolean isOnline;
    private String ip;
    private String name;

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
