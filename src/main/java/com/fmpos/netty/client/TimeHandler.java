package com.fmpos.netty.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TimeHandler extends SimpleChannelHandler {

@Override
public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
{
				 Channel ch = e.getChannel();
				ChannelBuffer time = ChannelBuffers.buffer(4);
				time.writeInt((int) (System.currentTimeMillis() / 1000));
				ChannelFuture f = ch.write(time.toString());
				
				
				f.addListener(new ChannelFutureListener() {
				 public void operationComplete(ChannelFuture future) {
						Channel ch = future.getChannel();
						ch.close();
						System.out.println("channel close");
						}
				 });
	}

	@Override  
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {   
	    super.exceptionCaught(ctx, e);   
	}   
	
}