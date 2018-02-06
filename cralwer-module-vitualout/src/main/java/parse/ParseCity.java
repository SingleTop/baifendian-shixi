package parse;

import com.bfd.crawler.utils.basicutil.json.JsonUtils;

import java.util.*;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 11:20 2018/1/2
 * @Modified_By:
 */
public
class ParseCity {
    public static
    Map<String , String> parseCity(String html) {
        Map<String, String> allCitys = new HashMap<>();
        Map<String, Object> cityMap = new LinkedHashMap<String, Object>();
        cityMap = JsonUtils.extractObject(html, cityMap.getClass());
        cityMap = (LinkedHashMap<String, Object>) cityMap.get("result");
        cityMap = (LinkedHashMap<String, Object>) cityMap.get("city_list");
        //cityMap = (LinkedHashMap<String, Object>)cityMap.get("ABCDE");
        Iterator<Map.Entry<String, Object>> iter = cityMap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            //System.out.println("key: " + entry.getKey() + " value： " + entry.getValue() );
            List<Map<String, Object>> list = (List<Map<String, Object>>)entry.getValue();
            for (int i = 0; i < list.size(); i ++) {
                Map<String, Object> oneCity = list.get(i);
                allCitys.put((String)oneCity.get("name"), (String)oneCity.get("code"));
            }
        }
        return allCitys;
    }


}
