package com.bfd.mf.crawler.proxy_server;

import com.bfd.mf.crawler.download.entity.CrawlTask;
import com.bfm.mf.crawler.proxyserver.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 14:38 2018/1/30
 * @Modified_By:
 */
public
class ProxyClient {
    private final ManagedChannel channel;
    private final requestProxyGrpc.requestProxyBlockingStub blockingStub;

    public ProxyClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext(true)
            .build();
        blockingStub = requestProxyGrpc.newBlockingStub(channel);
    }
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    public void crawl(){
        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E7%BF%BB%E8%AF%91&oq=iso%25E7%25BC%2596%25E7%25A0%2581&rsv_pq=af1bf16400035f7c&rsv_t=e66bxy44kKzlmcb2Tl7h3%2FYH6u34F8qfbDDbRaK77Flt0ZPsdVQ0YjD7pgc&rqlang=cn&rsv_enter=1&inputT=11934&rsv_sug3=71&rsv_sug1=74&rsv_sug7=100&rsv_sug2=0&rsv_sug4=11934";
        com.bfd.mf.crawler.download.entity.CrawlTask task = new com.bfd.mf.crawler.download.entity.CrawlTask();
        task.setUrl(url);
        CrawlRequest request = CrawlRequest.newBuilder().setUrl(task.getUrl())
            .build();
        CrawlResponse response;
        try{
            response = blockingStub.crawl(request);
        }catch(StatusRuntimeException e){
            e.printStackTrace();
            return;
        }
        System.out.println("Greeting: " + response.toString());
    }

    public static void main (String[] args) throws Exception{
        ArrayList<Long> arr = new ArrayList();
        ProxyClient  client = new ProxyClient("localhost", 50001);
        try{
            long time = System.currentTimeMillis();
           for(int i = 0; i < 200; i ++) {
               client.sendAsk();
               long gapTime = System.currentTimeMillis() - time;
               System.out.println(gapTime);
               arr.add(gapTime);
               time = System.currentTimeMillis();
           }
        }finally {
            long x = 0;
            int i = 0;
            for(long f : arr){
                if(i > 3) {
                    x += f;
                }
                i ++;
            }
            System.out.println("average time " + x/arr.size());

            client.shutdown();
        }
    }
public void sendAsk(){
    ask oneAsk = ask.newBuilder().setAskMessage("haha").build();
    reply re = blockingStub.isHasResource(oneAsk);
    System.out.println(re);
}


}
