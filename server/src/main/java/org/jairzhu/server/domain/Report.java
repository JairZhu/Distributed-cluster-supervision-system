package org.jairzhu.server.domain;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

public class Report {
    private String name;
    private String OS;
    private double load;
    private int cpus;

    @Override
    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Double.compare(report.load, load) == 0 && cpus == report.cpus && Objects.equals(name, report.name) && Objects.equals(OS, report.OS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, OS, load, cpus);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public int getCpus() {
        return cpus;
    }

    public void setCpus(int cpus) {
        this.cpus = cpus;
    }
}
