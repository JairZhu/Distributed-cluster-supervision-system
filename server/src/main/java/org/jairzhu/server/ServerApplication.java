package org.jairzhu.server;

import org.jairzhu.server.netty.MyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyServer.class, args);
    }

}
