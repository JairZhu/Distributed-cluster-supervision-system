package org.jairzhu.server;

import org.jairzhu.server.mapper.RecordMapper;
import org.jairzhu.server.netty.MyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@MapperScan(basePackages = {"org.jairzhu.server.mapper"})
public class ServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyServer.class, args);
    }

}
