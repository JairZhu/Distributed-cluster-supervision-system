package org.jairzhu.server.domain;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Objects;

public class Command {
    private int type;
    private HashMap<String, String> contents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        Command command = (Command) o;
        return type == command.type && contents.equals(command.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, contents);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public HashMap<String, String> getContents() {
        return contents;
    }

    public void setContents(HashMap<String, String> contents) {
        this.contents = contents;
    }
}
