package test;

import com.bfd.crawler.utils.basicutil.json.JsonUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 14:04 2017/12/21
 * @Modified_By:
 */
public
class Crawl {
public static void main (String[] args) {
    String a= "";
    Map<String, Object> map = new LinkedHashMap<>();
    try {
        map = (LinkedHashMap<String, Object>)JsonUtils.parseObject(a);
    }catch (Exception e) {
        e.printStackTrace();
    }
    Iterator<Map.Entry<String, Object>> iter = map.entrySet().iterator();
    while(iter.hasNext()) {
        Map.Entry<String, Object> entry = iter.next();
        System.out.println("key: " + entry.getKey() + " value： " + entry.getValue() );
    }
}


}
