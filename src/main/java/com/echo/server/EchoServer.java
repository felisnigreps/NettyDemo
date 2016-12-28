package com.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by xugenli on 2016/12/26.
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void run() {
        //EventLoopGroup 是rector的线程池
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //serverBootstrap是netty启动的辅助类
            ServerBootstrap b = new ServerBootstrap();
            b.channel(NioServerSocketChannel.class)
                    .group(group)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //绑定端口 阻塞直到绑定结束
            ChannelFuture future = b.bind(port).sync();
            System.out.println(future.channel().getClass().getName() + "is started and listen to" + port);
            //监听直到channel关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        EchoServer server = new EchoServer(7878);
        System.out.println(server);
        server.run();
    }

}
