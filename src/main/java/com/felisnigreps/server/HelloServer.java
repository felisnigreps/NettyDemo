package com.felisnigreps.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by xugenli on 2016/12/8.
 */
public class HelloServer {
    //创建端口
    final static int portNumber = 7878;

    public static void main(String[] args) throws InterruptedException {
        //创建server监听
        ServerBootstrap b = new ServerBootstrap();
        //创建eventLoopGroup
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();


        b.group(boss, worker);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new HelloServerInitializer());

        //服务器绑定端口监听
        ChannelFuture future = b.bind(portNumber).sync();
        future.channel().closeFuture().sync();

        boss.shutdownGracefully();
        worker.shutdownGracefully();

    }


}
