package com.mkm.yroki;

/**
 * Created by papa on 30.03.2022
 */

import io.netty.buffer.ByteBuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        System.out.println(msg);
        ((ByteBuf) msg).release(); // (3)
//        ctx.channel().writeAndFlush(ctx.alloc().buffer().writeBytes("ok".getBytes(StandardCharsets.UTF_8)));
        ctx.channel().writeAndFlush("ok");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}