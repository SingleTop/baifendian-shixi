package com.bfd.mf.crawler.proxy_server;

import com.bfd.mf.crawler.config.ConfigCache;
import com.bfd.mf.crawler.config.ConfigLoad;
import com.bfd.mf.crawler.process.PreProcess;
import com.bfm.mf.crawler.proxyserver.requestProxyGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import sun.security.krb5.Config;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 14:38 2018/1/30
 * @Modified_By:
 */
public
class ProxyServer {
    public static final Log log = LogFactory.getLog(com.bfd.mf.crawler.proxy_server.ProxyServer.class);
    static {
        PropertyConfigurator.configureAndWatch(ConfigCache.PATH_LOG4J);
        ConfigLoad.initConfigs();
    }


    private Server server;

    private void start() throws IOException {
        server = NettyServerBuilder.forAddress(new InetSocketAddress(ConfigCache.SERVER_IP, ConfigCache.SERVER_PORT))
            .addService(requestProxyGrpc.bindService(new ServerHandle()))
            .build()
            .start();
        System.out.println("Server started, Listening on " + ConfigCache.SERVER_IP  + ":" + ConfigCache.SERVER_PORT);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run(){
                System.out.println("*** shutting down gRPC server since JVM is shutting down");
                ProxyServer.this.stop();
                System.err.println("*** server shut down");
            }

        });
    }
    private void stop(){
        if(server != null){
            server.shutdown();
        }
    }
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null){
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PreProcess.init();
        final ProxyServer server = new ProxyServer();
        server.start();
        System.out.println(ConfigCache.INTERVAL + " this is interval ");
        server.blockUntilShutdown();
    }
}
