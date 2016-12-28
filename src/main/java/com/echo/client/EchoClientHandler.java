package com.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by xugenli on 2016/12/28.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active");
        ctx.writeAndFlush(Unpooled.copiedBuffer("this is a song",CharsetUtil.UTF_8));
    }


    //都用String的话可以解析出来么？
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received :" + byteBuf.toString(CharsetUtil.UTF_8));
    }


}
