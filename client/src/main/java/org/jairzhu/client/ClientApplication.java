package org.jairzhu.client;

import com.alibaba.fastjson.JSON;
import org.jairzhu.client.domain.*;
import org.jairzhu.client.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.*;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(ClientApplication.class.getName());
    @Value("${serverPort}")
    private int serverPort;
    @Value("${serverIP}")
    private String serverIP;
    private Client client = new Client();

    @Override
    public void run(String... arg) throws Exception {
        client.setName(Utils.getRandomName());
        client.setOnline(true);
        client.setCpu(Utils.getCPUNumber());
        client.setOs(Utils.getOS());

        logger.info("Connect to: " + serverIP + ":" + serverPort);
        Socket reportSocket = new Socket(serverIP, serverPort);

        String address = reportSocket.getLocalAddress().toString();
        address = address.substring(1) + ':' + reportSocket.getLocalPort();
        client.setIP(address);
        logger.info("local host: " + address);

        final BufferedWriter reportWriter = new BufferedWriter(new OutputStreamWriter(reportSocket.getOutputStream()));

        Command onlineCommand = new Command();
        onlineCommand.setType(CommandType.CLIENT_ONLINE);
        HashMap<String, String> onlineContents = new HashMap<>();
        onlineContents.put("client", client.toString());
        onlineCommand.setContents(onlineContents);

        reportWriter.write(onlineCommand.toString());
        reportWriter.flush();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Report report = new Report();
                report.setName(client.getName());
                report.setCpunum(Utils.getCPUNumber());
                report.setAvgload(Utils.getAverageLoad());
                report.setOs(Utils.getOS());
                report.setIp(client.getIP());
                report.setTimestamp(new Date());

                Command reportCommand = new Command();
                reportCommand.setType(CommandType.CLIENT_REPORT);
                HashMap<String, String> reportContents = new HashMap<>();
                reportContents.put("report", report.toString());
                reportContents.put("client", client.toString());
                reportCommand.setContents(reportContents);

                try {
                    reportWriter.write(reportCommand.toString());
                    reportWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
