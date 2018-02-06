package com.bfd.mf.crawler;

import com.bfd.mf.crawler.config.ConfigCache;
import com.bfd.mf.crawler.download.entity.CrawlRs;
import com.bfd.mf.crawler.download.entity.CrawlTask;
import com.bfd.mf.crawler.download.entity.Proxy;
import com.bfd.mf.crawler.download.utils.HttpClientUtil;
import com.bfd.mf.crawler.process.PreProcess;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 18:25 2018/1/31
 * @Modified_By:
 */
public
class test {
    public static void main(String[] args){
        PreProcess.init();
        CrawlTask task = new CrawlTask();
        task.setUrl("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E7%BF%BB%E8%AF%91&oq=iso%25E7%25BC%2596%25E7%25A0%2581&rsv_pq=af1bf16400035f7c&rsv_t=e66bxy44kKzlmcb2Tl7h3%2FYH6u34F8qfbDDbRaK77Flt0ZPsdVQ0YjD7pgc&rqlang=cn&rsv_enter=1&inputT=11934&rsv_sug3=71&rsv_sug1=74&rsv_sug7=100&rsv_sug2=0&rsv_sug4=11934");
        task.setProxyOrLocal(new Proxy().setIp(ConfigCache.PROXY_IP).setPort(ConfigCache.PROXY_PORT));
        System.out.println("task " + task);
        CrawlRs rs = HttpClientUtil.crawl(task);
        System.out.println(rs);
    }
}
