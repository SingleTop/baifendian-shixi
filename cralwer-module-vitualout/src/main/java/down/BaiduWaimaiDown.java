package down;

import com.bfd.crawler.utils.basicutil.db.DBUtil;
import com.bfd.crawler.utils.basicutil.json.JsonUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.PropertyConfigurator;
import parse.ParseWaimai;
import profile.ConfigCache;
import util.DownUtil;
import util.MyDbUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 11:00 2017/12/29
 * @Modified_By:
 */
public
class BaiduWaimaiDown {
    public static  DecimalFormat df = new DecimalFormat("#.##");
    static {
        PropertyConfigurator.configureAndWatch("D:\\vitualou\\properties\\baiduLog.properties");
        DBUtil.init(ConfigCache.MY_DB_PROPERTIES, false);


    }
    public static void main(String args[]) {



    //downStreetInfo("131","朝阳区", "奥运村街道asfsadf");
    //Map<String, String> map = UnSerialise.reader(ConfigCache.CITY_ID);
   // System.out.println(map);

//getCityRealmStreetDirection();
//getStreetGroup("北京市$东城区$$东华门$131$4826842.77,12958595.57$", "131");
        getAllShopId();


     }



    private static String downCity (String url) {

        return null;
}

public static shopSerialise readShopDownCtr(){
        return (shopSerialise)UnSerialise.readerObject(ConfigCache.SHOP_URL_DOWN_CONTROL);
}
public static void writeShopDownCtr(shopSerialise shopDownCtr){
    Serialise.writeObject(shopDownCtr, ConfigCache.SHOP_URL_DOWN_CONTROL);
}
public static void getAllShopId(){
    Map<String, String> cityToCode = UnSerialise.reader(ConfigCache.CITY_ID);
    //System.out.println(cityCode);
    List<String> noContainCity = (List<String>) UnSerialise.readerObject(ConfigCache.NO_FIND_CITY);

    shopSerialise shopDownCtr = readShopDownCtr();
    int shopTime0 = shopDownCtr.getTime0();
    int shopTime1 = shopDownCtr.getTime1();
    int shopTime2 = shopDownCtr.getTime2();
    int shopTime3 = shopDownCtr.getTime3();
    System.out.println(shopDownCtr);
    Map<String,Map<String, List<String>>> baiduCityRealmStreet =  UnSerialise.reader(ConfigCache.BAIDU_CITY_REAML_STREET);
    Iterator  <Map.Entry<String, Map<String, List<String>>>> iterBaiduCityRealmStreet = baiduCityRealmStreet.entrySet().iterator();
    int time0 = 0;
    String cityCode = "";
    while(iterBaiduCityRealmStreet.hasNext() ) {
        Map.Entry<String, Map<String, List<String>>>  entryCity = iterBaiduCityRealmStreet.next();
        if (time0 < shopTime0) {
            time0 ++ ;
            continue;
        }

        cityCode = cityToCode.get(entryCity.getKey());
        if (cityCode == null) {
            cityCode = cityToCode.get(reduceTail(entryCity.getKey()));
            if (cityCode == null) {
                cityCode = cityToCode.get(reduceBlockTail(entryCity.getKey()));
            }
            if (cityCode == null) {
                continue;
            }
        }


        Map<String, Map<String, List<String>>> realmResult = UnSerialise.reader(entryCity.getKey());
        if(realmResult == null){
        noContainCity.add(entryCity.getKey());
        Serialise.writeObject(noContainCity, ConfigCache.NO_FIND_CITY);
        time0 ++;
        shopDownCtr.setTime0(time0);
        writeShopDownCtr(shopDownCtr);
        System.out.println(shopDownCtr);
        continue;
        }
        System.out.println(realmResult);
        Iterator<Map.Entry<String, Map<String, List<String>>>> realmIter = realmResult.entrySet().iterator();
        int time1 = 0;
        while (realmIter.hasNext()){
            Map.Entry<String, Map<String, List<String>>> realmEntry = realmIter.next();
            if (time0 == shopTime0 && time1 < shopTime1){
                time1 ++;
                continue;
            }
            Iterator<Map.Entry<String, List<String>>> streetIter = realmEntry.getValue().entrySet().iterator();
            int time2 = 0;
            while(streetIter.hasNext()){
                Map.Entry<String, List<String>> streetEntry = streetIter.next();
                if(time0 == shopTime0 && time1 == shopTime1 && time2 < shopTime2){
                    time2 ++;
                    continue;
                }
                System.out.println(entryCity.getKey() + " : " + realmEntry.getKey() + " : " + streetEntry.getKey() );
                List<String> groupIdList = streetEntry.getValue();
                int time3 = 0;
                for(String id : groupIdList){
                    if(time0 == shopTime0 && time1 == shopTime1 && time2 == shopTime2 && time3 < shopTime3 ){
                        time3 ++;
                        continue;
                    }
                    System.out.println(id);
                 Map<String, String> shopUrlMap = getSearchShopUrl(id);
                 for(Map.Entry<String, String> onegroupUrl : shopUrlMap.entrySet()) {
                     String html = DownUtil.getHtmlGET(onegroupUrl.getValue());
                     if(html == null) {
                         MyDbUtil.addReadTimeOut(onegroupUrl.getValue());
                         continue;
                     }
//                     try {
//                         Thread.sleep(1000);
//                     }catch (Exception e){
//                         e.printStackTrace();
//                     }
                     List<Map<String, String>> shopList = ParseWaimai.getShopId(html);
                     if(shopList != null){
                     //System.out.println(shopList);
                     for(Map<String, String> oneShopInfo : shopList) {
                         if(MyDbUtil.isHasShop(oneShopInfo.get("shop_id"))){
                             System.out.println("have");
                             continue;
                         }
                         oneShopInfo.put("city", entryCity.getKey());
                         oneShopInfo.put("type", onegroupUrl.getKey());
                         System.out.println(oneShopInfo);
                         MyDbUtil.insertShopUrl(oneShopInfo);

                     }
                     }
                 }
                 time3 ++ ;
                 shopDownCtr.setTime3(time3);
                 writeShopDownCtr(shopDownCtr);
                    System.out.println(shopDownCtr);

                }
                time2 ++;
                shopDownCtr.setTime2(time2);
                writeShopDownCtr(shopDownCtr);
                System.out.println(shopDownCtr);

            }
            time1 ++;
            shopDownCtr.setTime1(time1);
            writeShopDownCtr(shopDownCtr);
            System.out.println(shopDownCtr);

        }


        time0 ++;
        shopDownCtr.setTime0(time0);
        writeShopDownCtr(shopDownCtr);


    }



}
static
class shopSerialise implements Serializable {
    private int time0 = 0;
    private int time1 = 0;
    private int time2 = 0;
    private int time3 = 0;

    @Override
    public
    String toString() {
        return "shopSerialise{" + "time0=" + time0 + ", time1=" + time1 + ", time2=" + time2 + ", time3=" + time3 + '}';
    }

    public
    int getTime3() {
        return time3;
    }

    public
    void setTime3(int time3) {
        this.time3 = time3;
    }

    public
    int getTime0() {
        return time0;
    }

    public
    void setTime0(int time0) {
        this.time0 = time0;
    }

    public
    int getTime1() {
        return time1;
    }

    public
    void setTime1(int time1) {
        this.time1 = time1;
    }

    public
    int getTime2() {
        return time2;
    }

    public
    void setTime2(int time2) {
        this.time2 = time2;
    }
}

public  static Map<String, String> getSearchShopUrl(String id){
    Map<String ,String> urlMap = new HashMap<>(4);
    //urlMap.put(ConfigCache.ZHENGONGFU, replaceShopUrl(id, ConfigCache.ZHENGONGFU));
    //urlMap.put(ConfigCache.JIYEJIA, replaceShopUrl(id, ConfigCache.JIYEJIA));
    //urlMap.put(ConfigCache.MAIDANGLAO, replaceShopUrl(id, ConfigCache.MAIDANGLAO));
    //urlMap.put(ConfigCache.KENDEJI, replaceShopUrl(id, ConfigCache.KENDEJI));

    urlMap.put(ConfigCache.YONGHEDAWANG, replaceShopUrl(id, ConfigCache.YONGHEDAWANG));
    urlMap.put(ConfigCache.DASHIXIONGWAIMAI, replaceShopUrl(id, ConfigCache.DASHIXIONGWAIMAI));


    return urlMap;
}
public static String replaceShopUrl(String id, String style) {
    String url = ConfigCache.SEARCH_SHOP_URL.replace("#id#", id);
    return url.replace("#style#", style);
}


private static String getStreetGroupCode(Map<String, Object>content, String cityCode){
        String url = ConfigCache.STREET_GROUP_CODE_URL;
    url = url.replace("#cid#", cityCode);
    url = url.replace("#latitude#", df.format(content.get("latitude")).toString());
    url = url.replace("#longitude#", df.format(content.get("longitude")).toString());
    url = url.replace("#name#", content.get("name").toString());
    //System.out.println(url);
    Map<String, Object> headers = null;
    try {
        headers = DownUtil.getHeader(url);
    }catch (IOException e){
        e.printStackTrace();
        return null;

    }
    if(headers == null) {
        return null;
    }

    Iterator<Map.Entry<String, Object>> oneGroupCode = headers.entrySet().iterator();
//    while(oneGroupCode.hasNext()){
//        Map.Entry<String, Object> segmentEntry = oneGroupCode.next();
//        System.out.println("key: " + segmentEntry.getKey() + " value: " + segmentEntry.getValue());
//    }
    List<String> cookie =(List<String>) headers.get("Set-Cookie");
//    for(String one : cookie) {
//        System.out.println(one);
//    }
    String chosenHeader = null;
    try {
        if (cookie.size() >= 3) {
        chosenHeader = URLDecoder.decode(cookie.get(3), "UTF-8");
    }
    }catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    //System.out.println(chosenHeader);
    if (chosenHeader != null && chosenHeader.contains("\"__id\":\"")) {
        int idBegin = chosenHeader.indexOf("\"__id\":\"");
        int idEnd = chosenHeader.indexOf("\"}", idBegin);
        String getId = chosenHeader.substring(idBegin + 8, idEnd);
        System.out.println(getId);
        return getId;
    }

    return null;



}



//获得店铺集群查询的url
    private static String getStreetGroupUrl (String chosenStreet, String cityCode){
    int begin = chosenStreet.indexOf("$$");
    int streetEnd = chosenStreet.indexOf("$", begin + 2);
    int LLbegin = chosenStreet.indexOf("$", streetEnd + 1);
    String [] LL = chosenStreet.substring(LLbegin + 1, chosenStreet.length()-1).split(",");
    String latitude = LL[0];
    String longitude = LL[1];
    String url = ConfigCache.STREET_GROUP_URL;
    //System.out.println(chosenStreet.substring(begin + 2, streetEnd) + "  " + latitude + "  " + longitude);
    url = url.replace("#word#", chosenStreet.substring(begin + 2, streetEnd));
    url = url.replace("#cid#", cityCode);
    url = url.replace("#latitude#", latitude);
    url = url.replace("#longitude#", longitude);
    System.out.println(url);
    return url;
    }

// 获得街道店铺集群List
    private static List<Map<String, Object>> getStreetGroup (String chosenStreet, String cityCode){
        String url = getStreetGroupUrl(chosenStreet, cityCode);
        String html = DownUtil.getHtmlGET(url);
        if(html == null) {
            return null;
        }
        Map<String, Object> oriResult = new HashMap<String, Object>();
            oriResult = JsonUtils.extractObject(html, oriResult.getClass());
            System.out.println(oriResult.get("error_msg"));
            if(!oriResult.get("error_msg").equals("")) {
                System.out.println("this url is null" + url);
                return null;
            }
            Map<String, Object> mesResult = (Map<String, Object>) oriResult.get("result");
            List<Map<String, Object>> contentResult = (List<Map<String, Object>>) mesResult.get("content");
            //System.out.println(contentResult);


return contentResult;
    }


// 分析出最接近的地址
private static String parseNearestLL(List<String> streets, String street){
        if (streets == null){
            return null;
        }
        int flag = 0;
        String newStreet = reduceStreet(street);
        int secondChose = -1;
        for(int i = 0; i < streets.size(); i ++ ) {
            String oneStreet = streets.get(i);
            int begin = oneStreet.indexOf("$$");
            String direction = oneStreet.substring(begin + 2 , oneStreet.indexOf("$", begin + 2));
            if(newStreet.equals(direction)) {
                return streets.get(i);
            }
            if (street.equals(direction)) {
                secondChose = i;
            }
        }
        if(secondChose != -1){
            return streets.get(secondChose);
        }
        if (streets.size() > 0) {
            return streets.get(0);
        }else {
            return null;
        }


}
public  static String reduceStreet (String oriStreet) {
    int x;
    if (oriStreet.contains("街道") && oriStreet.indexOf("街道") == oriStreet.length() -2){
        return oriStreet.substring(0, oriStreet.indexOf("街道"));
    }
    if (oriStreet.contains("林区") && oriStreet.indexOf("林区") == oriStreet.length() -2){
        return oriStreet.substring(0, oriStreet.indexOf("林区"));
    }

    if (oriStreet.contains("自治区") && oriStreet.indexOf("自治区") == oriStreet.length() -3){
        return oriStreet.substring(0, oriStreet.indexOf("自治区"));
    }
    if (oriStreet.contains("区") && oriStreet.indexOf("区") == oriStreet.length() -1){
        return oriStreet.substring(0, oriStreet.indexOf("区"));
    }

    if (oriStreet.contains("自治乡") && oriStreet.indexOf("自治乡") == oriStreet.length() -3){
        return oriStreet.substring(0, oriStreet.indexOf("自治乡"));
    }
    if (oriStreet.contains("乡") && oriStreet.indexOf("乡") == oriStreet.length() -1){
        return oriStreet.substring(0, oriStreet.indexOf("乡"));
    }

    if (oriStreet.contains("自治县") && oriStreet.indexOf("自治县") == oriStreet.length() -3){
        return oriStreet.substring(0, oriStreet.indexOf("自治县"));
    }
    if (oriStreet.contains("县") && oriStreet.indexOf("县") == oriStreet.length() -1){
        return oriStreet.substring(0, oriStreet.indexOf("县"));
    }

    if (oriStreet.contains("自治州") && oriStreet.indexOf("自治州") == oriStreet.length() -3){
        return oriStreet.substring(0, oriStreet.indexOf("自治州"));
    }
    if (oriStreet.contains("州") && oriStreet.indexOf("州") == oriStreet.length() -2){
        return oriStreet.substring(0, oriStreet.indexOf("州"));
    }
    return oriStreet;


}


// 通过城市，城区，街道获得街道经纬度
private static List<String> downStreetInfo(String cid, String realm, String street) {
        List postInfo = new ArrayList();
        postInfo.add(new BasicNameValuePair("cid", cid));
        postInfo.add(new BasicNameValuePair("wd",  street));
        postInfo.add(new BasicNameValuePair("type", "0"));
        postInfo.add(new BasicNameValuePair("prom", "pc"));

        String html = DownUtil.getHtmlPOST(ConfigCache.STREET_DIRECITON_URL, postInfo);
        if(html == null) {
            return null;
        }
        Map<String, Object> directionMap = new HashMap<String, Object>();
        directionMap = JsonUtils.extractObject(html,directionMap.getClass());
        if(!(boolean)directionMap.get("p")){
        System.out.println("error");
        return null;
    }
    List<String> directionList = (List<String>)directionMap.get("s");
    //System.out.println(directionMap.get("p"));
    //System.out.println(directionList);
return directionList;
}


public  static String reduceTail(String city) {
        //System.out.println(city.length());
        if(city.equals("铜仁市")){
        return "铜仁地区";
        }
        if (city.charAt(city.length()-1) == '县' || city.charAt(city.length()-1) == '市') {
            return city.substring(0, city.length()-1);
        }

        return city;
}


    public  static String reduceBlockTail(String city) {
        //System.out.println(city.length());
        if (city.charAt(city.length()-1) == '县' || city.charAt(city.length()-1) == '市' || city.charAt(city.length()-1) == '区') {
            return city.substring(0, city.length()-1);
        }
        return city;
    }

//通过城市，市区，街道定位到街道经纬度。获得Map<城市，map《地区，map《街道， list《code》》》》
public static Map<String, Map<String, Map<String, List<String>>>> getCityRealmStreetDirection() {
    //result
    Map<String, Map<String, Map<String, List<String>>>> result = new LinkedHashMap<>();

    Map<String, String> cityToCode = UnSerialise.reader(ConfigCache.CITY_ID);
        //System.out.println(cityCode);
    Map<String,Map<String, List<String>>> baiduCityRealmStreet =  UnSerialise.reader(ConfigCache.BAIDU_CITY_REAML_STREET);
    System.out.println(baiduCityRealmStreet);
    Iterator  <Map.Entry<String, Map<String, List<String>>>> iterBaiduCityRealmStreet = baiduCityRealmStreet.entrySet().iterator();
    int times0 = 0;
    String cityCode = "";
    while(iterBaiduCityRealmStreet.hasNext() ) {
        Map.Entry<String, Map<String, List<String>>>entryCity = iterBaiduCityRealmStreet.next();
        if(times0 <= 536) {
            times0++;
            continue;
        }

        //result-地区
        Map<String, Map<String, List<String>>> realmResult = new LinkedHashMap<>();

        cityCode = cityToCode.get(entryCity.getKey());
        if (cityCode == null) {
            cityCode = cityToCode.get(reduceTail(entryCity.getKey()));
            if(cityCode ==null){
                cityCode = cityToCode.get(reduceBlockTail(entryCity.getKey()));
            }
            if(cityCode == null){
                continue;
            }
        }
        System.out.println(" city : " + entryCity.getKey() + " Code: " + cityCode);
        Iterator<Map.Entry<String, List<String>>> iterRealm =  entryCity.getValue().entrySet().iterator();
        int times1 = 0;

        while(iterRealm.hasNext() ) {
            Map.Entry<String, List<String>> entryRealm = iterRealm.next();
            //result-街道
            Map<String, List<String>> streetResult = new LinkedHashMap<>();

            System.out.println("Realm: " + entryRealm.getKey());
            List<String> streets = entryRealm.getValue();
            if(streets == null) {
                continue;
            }
            for (int i = 0 ; i < streets.size(); i++) {
                //result - 集群
                List<String> groupResult = new ArrayList<>();

                //System.out.println(streets.get(i));
                List<String> getStreets1 = downStreetInfo(cityCode, entryRealm.getKey(), reduceStreet(streets.get(i)));
                List<String> getStreets2 = downStreetInfo(cityCode, entryRealm.getKey(), streets.get(i));
                if(getStreets1 != null && getStreets2 != null) {
                    getStreets1.addAll(getStreets2);
                }else{
                    if(getStreets1 == null && getStreets2 == null) {
                        continue;
                    }
                    if(getStreets1 == null){
                        getStreets1 = getStreets2;
                    }
                }
                String choseStreet = parseNearestLL(getStreets1, streets.get(i));
                if(choseStreet == null){
                    continue;
                }
                System.out.println(choseStreet);
                //获得每一条街道的店铺集群
                List<Map<String, Object>> streetGroup = getStreetGroup(choseStreet, cityCode);
                if(streetGroup == null) {
                    continue;
                }
                //获得
                for(Map<String, Object> oneGroup : streetGroup){
                        String git__id = getStreetGroupCode(oneGroup, cityCode);
                        if(git__id == null){
                            continue;
                        }
                        groupResult.add(git__id);
                }

//                try {
//                    Thread.sleep(500);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                streetResult.put(streets.get(i), groupResult);

            }
        realmResult.put(entryRealm.getKey(), streetResult);
            times1 ++ ;
        }
        Serialise.write(realmResult, entryCity.getKey());
result.put(entryCity.getKey(), realmResult);
        times0 ++ ;
    }
    System.out.println(result);

    return null;
    }

public static Map<String, String> getCityId (){
        //String html = getHtmlGET();
        //System.out.println(html);
        return  null;
}
//从所得的地图map信息 城市-区-街道 和 百度外卖的城市（区）列表相匹配 获得 城市（区）- 区- 街道 Map信息。
public static Map<String,Map<String, List<String>>> machingStreet(Map<String, Map<String, List<String>>> cityRealmStreet, Map<String, String> cityId) {
    System.out.println(cityRealmStreet);
    Map<String, Map<String, List<String>>> machingMap = new LinkedHashMap<>();
    Map<String, String> defect = new HashMap<String, String>();
    Iterator<Map.Entry<String, Map<String, List<String>>>> iter = cityRealmStreet.entrySet().iterator();
    while (iter.hasNext()) {
        Map.Entry<String, Map<String, List<String>>> entryCity = iter.next();
        if (cityId.containsKey(reduceTail(entryCity.getKey()))) {
            System.out.println(entryCity.getKey());
            machingMap.put(reduceTail(entryCity.getKey()), entryCity.getValue());
            defect.put(reduceTail(entryCity.getKey()), "");
        }
        Iterator<Map.Entry<String, List<String>>> iterCounty = entryCity.getValue().entrySet().iterator();
        while (iterCounty.hasNext()) {
            Map.Entry<String, List<String>> entryCounty = iterCounty.next();
            if (cityId.containsKey(reduceBlockTail(entryCounty.getKey()))) {
                Map<String, List<String>> oneCounty = new LinkedHashMap<>();
                oneCounty.put(reduceBlockTail(entryCounty.getKey()), entryCounty.getValue());
                machingMap.put(reduceBlockTail(entryCounty.getKey()), oneCounty);
                defect.put(reduceBlockTail(entryCounty.getKey()), "");
            }
            if (cityId.containsKey(reduceTail(entryCounty.getKey()))) {
                Map<String, List<String>> oneCounty = new LinkedHashMap<>();
                oneCounty.put(reduceTail(entryCounty.getKey()), entryCounty.getValue());
                machingMap.put(reduceTail(entryCounty.getKey()), oneCounty);
                defect.put(reduceTail(entryCounty.getKey()), "");

            }
        }
    }
        Iterator<Map.Entry<String, String>> iterCheck = cityId.entrySet().iterator();
    while(iterCheck.hasNext()) {
        Map.Entry<String, String> oneMap = iterCheck.next();
        if(!defect.containsKey(oneMap.getKey())){
            System.out.println("defect: " + oneMap.getKey());
        }
    }

    System.out.println(machingMap);

    return machingMap;
}



public static Map <String, String> getHeader(){
    Map<String, String> headers = new HashMap<String, String>(16);
    headers.put("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
    headers.put("Cookie", "BAIDUID=CA3D86063C4A2834F14869F14DD6A8D8:FG=1; BIDUPSID=CA3D86063C4A2834F14869F14DD6A8D8; PSTM=1514455145; H_PS_PSSID=1464_21080_25439_25178_22072; PSINO=1; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; WMID=1b7ff26e575135e83dbdca3541b18dfc; WMRT=1514455157; WMREFERID=1b7ff26e575135e83dbdca3541b18dfc; Hm_lvt_e0401ea6bbde08becd704794fb788176=1514455431; Hm_lpvt_e0401ea6bbde08becd704794fb788176=1514455431; wm_city=%7B%22name%22%3A%22%E6%B7%B1%E5%9C%B3%22%2C%22code%22%3A340%2C%22hasaoi%22%3Afalse%7D; WMST=1514455193");
    headers.put("Connection", "keep-alive");
    headers.put("Host", "waimai.baidu.com");
    headers.put("Accept-Encoding", "gzip, deflate");
    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    headers.put("Referer", "http://waimai.baidu.com/waimai?qt=find");
    headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
    headers.put("Origin", "http://waimai.baidu.com");
    headers.put("X-Requested-With", "XMLHttpRequest");
    return headers;

}

//public static



}
