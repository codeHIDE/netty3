package com.fmpos.netty.client;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;   
  
public class NettyClient {   
  
    public static void main(String[] args) {   
        // Configure the client.   
        ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));   
  
        // Set up the default event pipeline.   
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {   
            public ChannelPipeline getPipeline() throws Exception {   
                return Channels.pipeline(new StringDecoder(), new StringEncoder(), new ClientHandler());   
//                return Channels.pipeline(new StringDecoder(), new StringEncoder(), new TimeHandler());   
            }   
        });   
  
        // Start the connection attempt.   
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 8000));   
  
        // Wait until the connection is closed or the connection attempt fails.  
        future.getChannel().getCloseFuture().awaitUninterruptibly();   
  
        // Shut down thread pools to exit.   
        bootstrap.releaseExternalResources();   
    }   
}  