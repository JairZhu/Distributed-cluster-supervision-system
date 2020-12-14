package org.jairzhu.server.domain;

import java.util.Date;

public class Record  {
    private int id;
    private String name;
    private double avgload;
    private String os;
    private int cpunum;
    private Date timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgload() {
        return avgload;
    }

    public void setAvgload(double avgload) {
        this.avgload = avgload;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getCpunum() {
        return cpunum;
    }

    public void setCpunum(int cpunum) {
        this.cpunum = cpunum;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
