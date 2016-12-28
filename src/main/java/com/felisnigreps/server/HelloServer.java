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

    public static void main(String[] args) {
        //创建server监听
        ServerBootstrap b = new ServerBootstrap();
        //创建eventLoopGroup 他是一个用于处理多线程I/O的循环器
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        b.group(boss, worker);
        //告诉ServerBootstrap 需要用什么channel来实现 这块如果不写的话，
        // 会报java.lang.IllegalStateException: channel or channelFactory not set
        //这块会返回一个abstractBootStrap类型的实例
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new HelloServerInitializer());

        //服务器绑定端口并启动去接受链接
        //一直监听直到socket关闭
        try {
            ChannelFuture future = b.bind(portNumber).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }


    }


}
