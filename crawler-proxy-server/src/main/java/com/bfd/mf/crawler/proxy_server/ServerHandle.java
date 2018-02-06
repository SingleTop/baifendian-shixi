package com.bfd.mf.crawler.proxy_server;

import com.bfd.crawler.utils.basicutil.json.JsonUtils;
import com.bfd.mf.crawler.config.ConfigCache;
import com.bfd.mf.crawler.download.entity.CrawlRs;
import com.bfd.mf.crawler.download.entity.CrawlTask;
import com.bfd.mf.crawler.download.entity.Proxy;
import com.bfd.mf.crawler.download.utils.HttpClientUtil;
import com.bfd.mf.crawler.process.JudgeProcess;
import com.bfm.mf.crawler.proxyserver.*;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 15:35 2018/1/30
 * @Modified_By:
 */
public
class ServerHandle implements requestProxyGrpc.requestProxy{
    public static final Log log = LogFactory.getLog(com.bfd.mf.crawler.proxy_server.ServerHandle.class);

    @Override
    public
    void crawl(CrawlRequest request, StreamObserver<CrawlResponse> responseObserver) {
        System.out.println("hhh");
        CrawlTask task = getCrawlTask(request);
        CrawlResponse rs = Crawl(task);
        System.out.println(rs);
        responseObserver.onNext(rs);
        responseObserver.onCompleted();

    }

    @Override
    public
    void isHasResource(ask request, StreamObserver<reply> responseObserver) {
        log.info("get request " + request);
        boolean isHasRe = processRequest(request);
        reply re = getReply(isHasRe);
        log.info("send message " + re);
        responseObserver.onNext(re);
        responseObserver.onCompleted();
    }

    public boolean processRequest(ask request){
        return JudgeProcess.isHasResource();
    }
    public reply getReply(boolean isHasRe){
        Map<String, String> message = new LinkedHashMap<>();
        reply re ;
        if(isHasRe){
            message.put("code", "0");
            message.put("data", "yes");
            String mapToStr = JsonUtils.compressMap(message);
            re = reply.newBuilder().setReplyMessage(mapToStr).build();
        }else {
            message.put("code", "1");
            message.put("data", "no");
            String mapToStr = JsonUtils.compressMap(message);
            re = reply.newBuilder().setReplyMessage(mapToStr).build();
        }
        return re;
    }





    private CrawlTask getCrawlTask(CrawlRequest request){
        com.bfd.mf.crawler.download.entity.CrawlTask task = new com.bfd.mf.crawler.download.entity.CrawlTask();
        task.setUrl(request.getUrl());
        if(request.getIp() != null && request.getPort() != null){
            task.setProxyOrLocal(new Proxy().setIp(ConfigCache.PROXY_IP).setPort(ConfigCache.PROXY_PORT));
        }
        if(request.getHeader() != null){
            task.setHeaders(request.getHeader());
        }
        System.out.println("header " + request.getHeader());

        return task;
    }

    private CrawlResponse Crawl(CrawlTask task){
        CrawlRs rs = HttpClientUtil.crawl(task);
        CrawlResponse  response = CrawlResponse.newBuilder()
            .setHtml(rs.getHtml())
            .setHttpCode(rs.getHttpCode())
            .setUrl(rs.getUrl())
            .setCharset(rs.getCharset())
            .build();
        return response;
    }


}
