package com.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by xugenli on 2016/12/26.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server active");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //把msg写出去 需要对msg简单解析
        ByteBuf in = (ByteBuf)msg;
        System.out.println("server received " + in.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //选择是否关闭通道


    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }


}
