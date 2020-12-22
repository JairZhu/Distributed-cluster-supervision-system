package org.jairzhu.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.jairzhu.server.domain.Client;
import org.jairzhu.server.domain.Report;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Select("select name, os, cpunum from record")
    public List<Client> findAllClients();

    @Select("select * from record")
    public List<Report> findAll();

    @Insert("insert into record(name, avgload, os, timestamp, cpunum) values(#{name}, #{avgload}, #{os}, #{timestamp}, #{cpunum})")
    public void saveRecord(Report record);

    @Select("select * from record where name = #{name}")
    public List<Report> getRecordByName(String name);
}
