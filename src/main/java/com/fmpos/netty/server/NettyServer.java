package com.fmpos.netty.server;
  
import org.jboss.netty.bootstrap.ServerBootstrap;   
import org.jboss.netty.channel.*;   
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;   
import org.jboss.netty.handler.codec.string.StringDecoder;   
import org.jboss.netty.handler.codec.string.StringEncoder;   
  
import java.net.InetSocketAddress;   
import java.util.concurrent.Executors;   
  
public class NettyServer {   
    public static void main(String[] args) {   
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));   
        // Set up the default event pipeline.   
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {   
            public ChannelPipeline getPipeline() throws Exception {   
                return Channels.pipeline(new StringDecoder(), new StringEncoder(), new ServerHandler());   
//                return Channels.pipeline(new StringDecoder(), new StringEncoder(), new TimeHandler());   
            }   
        });   
  
        // Bind and start to accept incoming connections.  
        Channel bind = bootstrap.bind(new InetSocketAddress(8000));   
        System.out.println("Server已经启动，监听端口: " + bind.getLocalAddress() + "， 等待客户端注册。。。");   
    }   
  
}  