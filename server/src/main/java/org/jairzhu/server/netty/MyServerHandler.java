package org.jairzhu.server.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.jairzhu.server.dao.RecordDao;
import org.jairzhu.server.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class MyServerHandler extends ChannelInboundHandlerAdapter {
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
                Record record = new Record();
                record.setAvgload(report.getLoad());
                record.setName(report.getName());
                record.setOs(report.getOS());
                record.setCpunum(report.getCpus());
                record.setTimestamp(new Date());
                break;
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
