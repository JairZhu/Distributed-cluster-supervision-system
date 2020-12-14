package org.jairzhu.server.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.jairzhu.server.domain.Record;

import java.util.List;

public interface RecordDao {
    @Select("select * from record")
    List<Record> findAll();

    @Insert("insert into record(name, avgload, os, timestamp, cpunum) values(#{name}, #{avgload}, #{os}, #{timestamp}, #{cpunum})")
    void saveRecord(Record record);
}
