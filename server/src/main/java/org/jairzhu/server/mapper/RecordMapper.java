package org.jairzhu.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.jairzhu.server.domain.Report;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Select("select * from record")
    public List<Report> findAll();

    @Insert("insert into record(name, avgload, os, timestamp, cpunum) values(#{name}, #{avgload}, #{os}, #{timestamp}, #{cpunum})")
    public void saveRecord(Report record);

    @Select("select * from record where name = #{name}")
    public Report getRecordByName(String name);
}
