package com.felisnigreps.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xugenli on 2016/12/8.
 */
public class HelloClient {

    public static String host = "127.0.0.1";

    public static int portNumber = 7878;


    public static void main(String[] args) throws IOException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(loopGroup).channel(NioSocketChannel.class).handler(new HelloClientInitializer());
        //连接服务端
        Channel channel = null;
        //键盘输入
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            channel = b.connect(host, portNumber).sync().channel();
            for (; ; ) {
                String line = reader.readLine();
                if (line == null) {
                    continue;
                }
                channel.writeAndFlush(line + "\r\n");

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();

        }
    }
}
