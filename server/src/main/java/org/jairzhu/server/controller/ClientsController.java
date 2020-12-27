package org.jairzhu.server.controller;

import com.alibaba.fastjson.JSON;
import org.jairzhu.server.domain.Client;
import org.jairzhu.server.domain.Clients;
import org.jairzhu.server.domain.Common;
import org.jairzhu.server.domain.Report;
import org.jairzhu.server.mapper.RecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@EnableAutoConfiguration
public class ClientsController {

    private final Logger logger = LoggerFactory.getLogger(ClientsController.class);

    @RequestMapping(value = "/clients")
    @ResponseBody
    public String getClientsList() {
        String response = JSON.toJSONString(Common.clients.getClients().values());
        return response;
    }

    @PostMapping("/saveClient")
    public void saveClient(@RequestBody Client client) throws Exception{
        client.setOnline(true);
        Common.clients.getClients().put(client.getName(), client);
        //TODO: 开辟新线程运行client代码
    }

    @PostMapping("/editClient")
    public void editClient(@RequestBody Client client) throws Exception {
        String name = client.getName();
        Common.clients.getClients().get(name).setOnline(false);
    }

    @PostMapping("/deleteClient")
    public void deleteClient(@RequestBody Client client) throws Exception {
        String name = client.getName();
        Common.clients.getClients().remove(name);
        Common.recordMapper.deleteRecordByName(name);
    }

    @RequestMapping("/historyClientsReport")
    @ResponseBody
    public String getHistoryClientReport() {
        List<Report> reports = Common.recordMapper.findAll();
        return JSON.toJSONString(reports);
    }

    @RequestMapping("/realTimeClientsReport")
    @ResponseBody
    public String getRealTimeClientsReport() {
        List<Report> reports = new ArrayList<>();
        for (String key: Common.clients.getClients().keySet()) {
            if (Common.clients.getClients().get(key).isOnline())
               reports.addAll(Common.recordMapper.getRecordByName(Common.clients.getClients().get(key).getName()));
        }
        return JSON.toJSONString(reports);
    }
}
