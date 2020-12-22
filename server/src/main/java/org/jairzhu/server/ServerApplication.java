package org.jairzhu.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.jairzhu.server.domain.Client;
import org.jairzhu.server.domain.Clients;
import org.jairzhu.server.domain.Common;
import org.jairzhu.server.mapper.RecordMapper;
import org.jairzhu.server.netty.MyServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    @Value("${serverPort}")
    private int port;


    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void run(String... args) throws Exception {
        Common.recordMapper = recordMapper;

        List<Client> clientList = recordMapper.findAllClients();

        for (Client client: clientList) {
            client.setOnline(false);
            Common.clients.getClients().put(client.getName(), client);
        }

        logger.info("start server at " + port);
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()))
                                    .addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast("register", new MyServerHandler())
                                    .addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                        }
                    });
            logger.info("server listening on port: " + port);
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
