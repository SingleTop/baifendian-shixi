package down;

import com.bfd.crawler.utils.basicutil.json.JsonUtils;
import profile.ConfigCache;
import scala.util.parsing.combinator.testing.Str;
import util.DownUtil;

import java.util.*;

import static util.DownUtil.getHtmlGET;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 12:28 2018/1/2
 * @Modified_By:
 */
public
class CityDown {
    public static void main(String args[]) {
/*
    //nomal_save(profile.ConfigCache.URL_CODE_REALMS, profile.ConfigCache.CODE_TO_REALM);
    Map<String, String> map = UnSerialise.reader(profile.ConfigCache.CODE_TO_REALM);
    System.out.println(map);
        Map<String, Map<String, String>> detail =  cityRealmCode(map);
    System.out.println(detail);
    Serialise.write(detail, profile.ConfigCache.CITY_REALM_CODE);

*/
        //Map<String, Map<String, String>> readDetail = UnSerialise.reader(profile.ConfigCache.CITY_REALM_CODE);
        //System.out.println(UnSerialise.reader(profile.ConfigCache.CITY_REALM_CODE));

        //System.out.println(getStreet(null));
        //getCityRealmStreet();

/*
        Map<String, Map<String, List<String>>> mapDetail = UnSerialise.reader(profile.ConfigCache.CITY_REALM_STREET);
        Iterator<Map.Entry<String, Map<String, List<String>>>> iter = mapDetail.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Map<String, List<String>>> entry = iter.next();
            //System.out.println(entry.getKey());

        }
        System.out.println(mapDetail.containsKey("临川区"));
        Map<String, Map<String, String>> cityRealmCode = UnSerialise.reader(profile.ConfigCache.CITY_REALM_CODE);
        Iterator<Map.Entry<String, Map<String, String>>> cityRealmIter = cityRealmCode.entrySet().iterator();
        while(cityRealmIter.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = cityRealmIter.next();
            System.out.println(entry.getKey());

        }
        System.out.println(cityRealmCode);
*/
/*
String jsonHtml = DownUtil.getHtmlGET("http://passer-by.com/data_location/list.json");
Map<String, String> RawMap = new LinkedHashMap<>();
    RawMap = JsonUtils.extractMap(jsonHtml,RawMap);
    Map<String, String> CityToProvince = getCityToProvince(RawMap);
    Serialise.write(CityToProvince, ConfigCache.CITY_TO_PROVINCE);
    System.out.println(UnSerialise.reader(ConfigCache.CITY_TO_PROVINCE));
*/
    }

    public static Map<String, Map<String, List<String>>> getCityRealmStreet () {
        Map<String, Map<String, List<String>>> cityRealmStreet = new LinkedHashMap<>();
        Map<String, Map<String, String>> cityRealmCode = UnSerialise.reader(profile.ConfigCache.CITY_REALM_CODE);
        Iterator<Map.Entry<String, Map<String, String>>> iter = cityRealmCode.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = iter.next();
            Iterator<Map.Entry<String, String>> iterRealm = entry.getValue().entrySet().iterator();
            Map<String, List<String>> realmStreet = new LinkedHashMap<>();
            while(iterRealm.hasNext()) {
                Map.Entry<String, String> entryOne = iterRealm.next();
                List<String> StreetsList = getStreet(getHtmlGET(getUrl(entryOne.getValue())));
                realmStreet.put(entryOne.getKey(), StreetsList);
            }
            cityRealmStreet.put(entry.getKey(), realmStreet);
            Serialise.write(cityRealmStreet, profile.ConfigCache.CITY_REALM_STREET);

        }

return null;
    }
public static String getUrl(String code) {
        String url = "http://passer-by.com/data_location/town/" + code + ".json";
        return url;


}
public static List<String> getStreet (String html) {
        if (html == null || html .length() == 0) {
          return null;
        }
       // html = getHtmlGET("http://passer-by.com/data_location/town/110115.json");
        Map<String, String> oneRealm = new LinkedHashMap<String , String>(25);
        oneRealm = JsonUtils.extractMap(html, oneRealm);
        ArrayList<String> oneArray = new ArrayList<String>(25);
        Iterator<Map.Entry<String, String>> iter = oneRealm.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, String > entry = iter.next();
            oneArray.add(entry.getValue());
        }
return  oneArray;


}

public static Map<String, Map<String, String>> cityRealmCode(Map<String, String> rawDetail) {
    Map<String, Map<String, String>> detail = new LinkedHashMap<String, Map<String, String>>(100);
    Iterator<Map.Entry<String, String>> iter = rawDetail.entrySet().iterator();
    String nowCity = null;
    String nowProvince = null;
    int flag = 0;
    while(iter.hasNext()) {
        Map.Entry<String, String> entry = iter.next();
        if (entry.getKey().substring(2, 6).equals("0000")) {
            nowProvince = entry.getValue();
            flag = 0;
        }else {
            if(entry.getKey().substring(2, 4).equals("90")){
               Map<String, String> subcity = new LinkedHashMap<String, String>();
               subcity.put(entry.getValue(), entry.getKey());
                detail.put(entry.getValue(), subcity);
                flag = 0;
            }else {

                if (entry.getKey().substring(4, 6).equals("00")) {
                    Map<String, String> preCityPose = new LinkedHashMap<String, String>();
                    preCityPose.put(entry.getValue(), entry.getKey());
                    detail.put(entry.getValue(), preCityPose);
                    nowCity = entry.getValue();
                } else {
                    if (flag == 1) {
                        nowCity = nowProvince;
                        detail.put(nowCity, new LinkedHashMap<String, String>(20));
                    }
                   // System.out.println(flag);
                   // System.out.println(nowCity);
                    //System.out.println(nowProvince);
                    detail.get(nowCity).put(entry.getValue(), entry.getKey());
                    if(detail.get(nowCity).containsKey(nowCity)){
                        detail.get(nowCity).remove(nowCity);
                    }
                }
            }
        }
        flag ++;

    }
    return detail;

}

public static Map<String, String> getCityToProvince(Map<String, String> rawMap){
   Map<String, String> result = new HashMap<>(100);
    Iterator<Map.Entry<String, String>> iter = rawMap.entrySet().iterator();
    String province = null;
    while(iter.hasNext()) {
        Map.Entry<String, String> entry = iter.next();
        if(entry.getKey().substring(2, 6).equals("0000")){
            province = entry.getValue();
            result.put(province, province);
        }else {
            if (entry.getKey().substring(4, 6).equals("00") || entry.getKey().substring(2, 4).equals("90")){
                if(result.containsKey(BaiduWaimaiDown.reduceStreet(BaiduWaimaiDown.reduceTail(entry.getValue())))){
                    System.out.println(entry.getKey() + "  " + entry.getValue());
                }
                result.put(BaiduWaimaiDown.reduceStreet(BaiduWaimaiDown.reduceTail(entry.getValue())), province);

            }
        }

    }
    return result;
}





    private static void nomal_save(String url, String filename) {
       String html = getHtmlGET(url);
        //html = DownUtil.decodeUnicode(html);
        //Map<String, String> allCitys = ParseCity.parseCity(html);
        Map<String, String> realmsCode = new LinkedHashMap<>(5000);
        realmsCode = JsonUtils.extractMap(html, realmsCode);
        Iterator<Map.Entry<String, String>> iter = realmsCode.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            System.out.println(entry.getKey() + " value: " + entry.getValue());

        }
        System.out.println(realmsCode);
        Serialise.write(realmsCode, filename);
    }


    }
