package org.jairzhu.client.domain;

import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.Objects;

public class Report {
    private String name;
    private String os;
    private Date timestamp;
    private double avgload;
    private int cpunum;
    private String ip;

    @Override
    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Double.compare(report.avgload, avgload) == 0 && cpunum == report.cpunum && Objects.equals(name, report.name) && Objects.equals(os, report.os) && Objects.equals(timestamp, report.timestamp) && Objects.equals(ip, report.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, os, timestamp, avgload, cpunum, ip);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getAvgload() {
        return avgload;
    }

    public void setAvgload(double avgload) {
        this.avgload = avgload;
    }

    public int getCpunum() {
        return cpunum;
    }

    public void setCpunum(int cpunum) {
        this.cpunum = cpunum;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
