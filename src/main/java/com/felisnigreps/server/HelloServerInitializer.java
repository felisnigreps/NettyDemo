package com.felisnigreps.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by xugenli on 2016/12/8.
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //解码器
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(10240,Delimiters.lineDelimiter()));


        pipeline.addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));

        //自定义的handler
        pipeline.addLast("handler", new HelloServerHandler());


    }
}
