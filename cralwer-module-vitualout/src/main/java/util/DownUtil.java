package util;

import com.bfd.mf.crawler.download.entity.CrawlTask;
import com.bfd.mf.crawler.download.entity.Proxy;
import com.bfd.mf.crawler.download.utils.HttpClientUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.handler.timeout.ReadTimeoutException;
import profile.ConfigCache;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.bfd.crawler.parse.Constants.url;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 10:57 2017/12/29
 * @Modified_By:
 */
public
class DownUtil {
    public static final Log log = LogFactory.getLog(util.DownUtil.class);
    public static String getHtmlPOST (String url, List postInfo) {
        System.out.println("postUrl: " + url);

        CrawlTask task = new CrawlTask(url);
        task.setRequestType("post");
        task.setPostParams(postInfo);
        String html = null;
        try {
            html = HttpClientUtil.crawl(task).getHtml();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        //System.out.println(html);
        if (html != null) {
            //System.out.println(html.length());
        }
        return  html;


    }
    public  static String getHtmlGET(String url) {
        System.out.println("getUrl: " + url);
        CrawlTask task = new CrawlTask(url);
        //task.setRequestType("post");
        //task.setPostParams("cid=340&type=0&wd=%E9%BE%99%E5%B2%97%E5%8C%BA&from=pc");
        String html = null;
        try {
            html = HttpClientUtil.crawl(task).getHtml();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        //System.out.println(html);
//        if (html != null) {
//            //System.out.println(html.length());
//        }
        return  html;
    }

    public static String htmlGetPointIp(String ip, String url){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("getUrl: " + url);
        CrawlTask task = new CrawlTask(url);
        task.setProxyOrLocal(new Proxy(ip));
        //task.setRequestType("post");
        //task.setPostParams("cid=340&type=0&wd=%E9%BE%99%E5%B2%97%E5%8C%BA&from=pc");
        String html = null;
        try {
            html = HttpClientUtil.crawl(task).getHtml();

        }catch (Exception e){
            log.error("getHtmlPointIp error " + "url: " + url + " ip: " + ip, e);
            e.printStackTrace();
            return null;
        }

        return  html;
    }

public static String downWityDupliPointId(String ip, String url){
        int i = 0;
      String html = htmlGetPointIp(ip, url);
        while(html == null && i < 4){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            html = htmlGetPointIp(ip, url);
            i ++;
        }
        return html;
}




    public static String decodeUnicode( String str) {
        StringBuffer string = new StringBuffer();

        //String[] hex = str.split("\\\\u");
        int len = str.length();
        int index = 0;
        int old_index = 0;
        for (int i = 0 ; i < len; ) {
            index = str.indexOf("\\u", old_index + 1 );
            if (index >= 0) {
                if(index-old_index > 6 || old_index == 0) {
                    string.append(str.substring(old_index == 0 ? 0 : old_index + 6, index));
                }
                // 转换出每一个代码点
                //System.out.println(str.substring(index + 2, index + 6));
                int data = Integer.parseInt(str.substring(index + 2, index + 6), 16);
                string.append((char) data);
                i = index + 6;
                old_index = index;
            }else{
                if (len - old_index > 6) {
                    string.append(str.substring(old_index + 6));
                }
                i = len;
            }
            //System.out.println(i);
        }
        return string.toString();
    }

    public static Map<String, Object> getHeader (String questUrl) throws IOException {
        URL url = new URL(questUrl);
        //URL url = new URL("http://waimai.baidu.com/waimai?qt=shoplist&lat=4834738&lng=12963407.3&address=%E5%8C%97%E4%BA%AC%E5%B8%82%E6%9C%9D%E9%98%B3%E5%8C%BA%E5%A4%AA%E9%98%B3%E5%AE%AB%E5%9C%B0%E5%8C%BA%E5%8A%9E%E4%BA%8B%E5%A4%84%E5%8D%81%E5%AD%97%E5%8F%A3%E7%A4%BE%E5%8C%BA%E5%B1%85%E6%B0%91%E5%A7%94%E5%91%98%E4%BC%9A&city_id=131");
        URLConnection conn = url.openConnection();

        Map headers = conn.getHeaderFields();
        Set<String> keys = headers.keySet();

        //System.out.println( conn.getLastModified() );
        //System.out.println( headers);
        return headers;
    }
    public static boolean elderTime(String repuTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStart = new Date();
        Date dateJudged = new Date();
        try {
            dateStart = formatter.parse(ConfigCache.START_TIME);
            dateJudged = formatter.parse(repuTime);
        }catch (Exception e){
            log.error("elderTime error " + "reputime: " + repuTime + " startTime: " + ConfigCache.START_TIME, e);
            e.printStackTrace();
        }
        if(dateJudged.getTime() < dateStart.getTime()){
            return true;
        }
        return false;
    }

    public static boolean judgeBetweenTime(String repuTime){
        return isTimeEnough(repuTime, ConfigCache.START_TIME, ConfigCache.END_TIME);
    }
public static boolean isTimeEnough(String judgedTime, String startTime, String endTime){
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String judged = "2018-01-11 21:03:33";
//    String startTime = "2018-02-01 00:00:00";
    Date dateStart = new Date();
    Date dateEnd = new Date();
    Date dateJudged = new Date();
    try {
        dateEnd = formatter.parse(endTime);
        dateStart = formatter.parse(startTime);
        dateJudged = formatter.parse(judgedTime);
}catch (Exception e){
        log.error("isTimeEnough error " + "reputime: " + judgedTime + " startTime: " + startTime + " endTime: " + endTime, e);
        e.printStackTrace();
}

    if (dateJudged.getTime() > dateStart.getTime() && dateJudged.getTime() < dateEnd.getTime()) {
    return true;
    }
return false;

}



    public static String getRepuUrl(String shopId, int pageNum){
        String url = ConfigCache.REPUTATION_URL.replace("#shopId#", shopId);
        url = url.replace("#pageNum#", Integer.toString(pageNum));
        return url;
    }




    public static void main(String[] args) {
        String html = htmlGetPointIp("192.168.174.48", "https://www.baidu.com/");
        System.out.println(html);
    }





}
