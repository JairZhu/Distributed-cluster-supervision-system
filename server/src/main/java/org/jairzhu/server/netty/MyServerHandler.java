package org.jairzhu.server.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jairzhu.server.domain.Client;
import org.jairzhu.server.domain.Command;
import org.jairzhu.server.domain.CommandType;
import org.jairzhu.server.domain.Common;
import org.jairzhu.server.domain.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

public class  MyServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(MyServerHandler.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Command command = JSON.parseObject(msg.toString(), Command.class);
        Client client = JSON.parseObject(command.getContents().get("client"), Client.class);
        switch (command.getType()) {
            case CommandType.CLIENT_ONLINE: {
                System.out.println(command);
                logger.info(client.getName() + " online");
                Common.clients.getClients().put(client.getName(), client);
                break;
            }
            case CommandType.CLIENT_REPORT: {
                Report report = JSON.parseObject(command.getContents().get("report"), Report.class);
                System.out.println(report);
                Common.recordMapper.saveRecord(report);
                break;
            }
        }
        if (!Common.clients.getClients().containsKey(client.getName())
                || !Common.clients.getClients().get(client.getName()).isOnline()) {
            ctx.close();
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String address = ctx.channel().remoteAddress().toString();
        address = address.substring(1);
        logger.info(address + " 主机offline");
        for (String key: Common.clients.getClients().keySet()) {
            Client client = Common.clients.getClients().get(key);
            if (client.getIP().equals(address)) {
//                logger.info(address + "主机offline");
                Common.clients.getClients().get(key).setOnline(false);
            }
        }
        cause.printStackTrace();
        ctx.close();
    }
}
