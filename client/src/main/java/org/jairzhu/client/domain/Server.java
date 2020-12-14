package org.jairzhu.client.domain;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

public class Server {
    private String ip;
    private int id;

    @Override
    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Server)) return false;
        Server server = (Server) o;
        return id == server.id && Objects.equals(ip, server.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, id);
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String IP) {
        this.ip = IP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
