package test;

import com.bfd.crawler.utils.basicutil.json.JsonUtils;
import down.UnSerialise;
import profile.ConfigCache;

import java.util.*;
import java.util.jar.JarEntry;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 11:35 2018/1/2
 * @Modified_By:
 */
public
class UtilTest {
    public static void main (String[] args) {
     String test = "aaa\"\"";

     System.out.println(test);
     processForQuotation(test);
    }
    public static String processForQuotation(String originStr){
        if(originStr.contains("\"")) {
            originStr = originStr.replaceAll("\"", "\\\\\"");
        }
        if(originStr.contains("")) {
            originStr = originStr.replaceAll("\"", "\\\\\"");
        }
        System.out.println(originStr);
        return originStr;
    }

    public  void compressTest (){
        Map<String, String > map1 = new LinkedHashMap<>();
        map1.put("aa","11");
        map1.put("bb","11");
        Map<String, String > map2 = new LinkedHashMap<>();
        map2.put("aa","22");
        map2.put("bb","33");
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        System.out.println(JsonUtils.compressList(list));
        System.out.println(JsonUtils.compressMap(map2));
    }
}
