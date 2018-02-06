package parse;

import java.io.*;
import java.util.*;

import com.bfd.crawler.utils.basicutil.json.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import util.DownUtil;


/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 16:33 2018/1/10
 * @Modified_By:
 */
public
class ParseWaimai {
    public static final Log log = LogFactory.getLog(parse.ParseWaimai.class);
    public static void main(String[] args){
String html = DownUtil.getHtmlGET("http://waimai.baidu.com/waimai/comment/getshop?display=json&shop_id=1640937336&page=2&count=60");

System.out.println(getReputationJsonInfo(html));

    }

    public static void testShopAndFood(String url){
if(url == null) {
    File to = new File("to");
    Reader reader = null;
    String str = null;
    int size = 0;
    try {
        FileInputStream in = new FileInputStream(to);
        size = in.available();
        byte[] buffer = new byte[size];
        in.read(buffer);
        in.close();
        str = new String(buffer, "UTF-8");
        getShopInfo(str);
        //log.info(str);

    } catch (Exception e) {
        e.printStackTrace();
    }
}else {
    String html = DownUtil.getHtmlGET(url);
    //getShopInfo(html , null, null, null);
    getShopId(html);
}
    }

    public static void testReputation(){
        String url = "http://waimai.baidu.com/waimai/comment/getshop?display=json&shop_id=1743244204&page=2&count=60";
        String html = DownUtil.getHtmlGET(url);
        getReputationJsonInfo(html);
    }

public static
    Map<String, String> getShopInfo(String html){
        try {
            Map<String, String> shopMap = new LinkedHashMap<String, String>();
            Document doc = Jsoup.parse(html);
            Element el = doc.body();
            Element y = el.getElementsByAttributeValue("class", "basicinfo").first();
            Element shopName = y.getElementsByAttributeValue("class", "all-show").first();
            Element shopDetailLocation = y.getElementsByTag("dd").last();
            Element commentNum = y.getElementsByAttributeValue("class", "rate-info").first().getElementsByTag("span").last();
            Element shopDownList = y.getElementsByAttributeValue("class", "overall").first();
            if(shopDownList.text().contains("评价人数不足")){
                shopMap.put("shopHoleScore", "评价人数不足");
                shopMap.put("shopStar","评价人数不足");
            }else {
                Element shopHoleScore = shopDownList.getElementsByAttributeValue("class", "rate-num").first();
                //log.info("-----------" + shopHoleScore);
                shopMap.put("shopStar", shopHoleScore.text());
                shopMap.put("shopHoleScore", shopHoleScore.text());
            }



            Element deliveryThreshold = y.getElementsByAttributeValue("class", "b-price fr").first().getElementsByAttributeValue("class", "b-num").first();
            Element deliveryFee = y.getElementsByAttributeValue("class", "b-cost fr").first().getElementsByAttributeValue("class", "b-num").first();
            Elements deliveryAverWaitingList = y.getElementsByAttributeValue("class", "b-totime fr");
            if(deliveryAverWaitingList.size() == 0) {
                shopMap.put("deliverAverwaiting", "无信息");
            }else {
                Element deliveryAverWaiting = deliveryAverWaitingList.first().getElementsByAttributeValue("class", "b-num").first();
                shopMap.put("deliverAverwaiting", deliveryAverWaiting.text());
            }
            shopMap.put("shopName", shopName.text());
            shopMap.put("shopDetailLocation", processForQuotation(shopDetailLocation.text()));
            shopMap.put("commentNum", commentNum.text());
            shopMap.put("deliveryFee", deliveryFee.text());
            shopMap.put("deliveryThreshold", deliveryThreshold.text());

            //System.out.println(shopMap);
            return shopMap;
        }catch (Exception e){
            log.error("shopInfoparse error: " + html, e);
            e.printStackTrace();
            return null;
        }
}
public static Map<String, Map<String, String>> getFoodsInfo(String html){
        try {
            Map<String, Map<String, String>> allFoodMap = new LinkedHashMap<>();
            Document doc = Jsoup.parse(html);
            Element body = doc.body();
            Element foodList = body.getElementsByAttributeValue("class", "menu-list").first();
            Elements foodInfoList = foodList.getElementsByAttributeValue("class", "list-wrap");
            for (Element oneClass : foodInfoList) {
                Elements oneLiInfo = oneClass.getElementsByTag("li");
                Element classifyInfo = oneClass.getElementsByAttributeValue("class", "title").first();
                for (Element oneFood : oneLiInfo) {
                    Map<String, String> foodMap = new LinkedHashMap<>();
                    Element foodName = oneFood.getElementsByTag("h3").first();
                    Elements salesInfo = oneFood.getElementsByAttributeValue("class", "sales-count");
                    Element recommendTimes = salesInfo.first();
                    Element saleCount = salesInfo.last();
                    Element priceInfo = null;
                    Element nomalPrice = null;
                    Element discountPrice = null;
                    //log.info(oneFood);
                    if (oneFood.getElementsByAttributeValue("class", "m-price").first() != null) {
                        priceInfo = oneFood.getElementsByAttributeValue("class", "m-price").first();
                        if (!priceInfo.getElementsByAttributeValue("class", "activityprice").toString().equals("")
                            && priceInfo.getElementsByAttributeValue("class", "activityprice").toString() != null) {
                            discountPrice = priceInfo.getElementsByClass("activityprice").first();
                            nomalPrice = priceInfo.getElementsByTag("del").first();
                        } else {
                            nomalPrice = priceInfo.getElementsByTag("strong").first();
                        }
                    } else {
                        priceInfo = oneFood.getElementsByAttributeValue("class", "m-break").first();
                        if(!priceInfo.getElementsByAttributeValue("class", "activityprice").toString().equals("")
                            && priceInfo.getElementsByAttributeValue("class", "activityprice").toString() != null ){
                            discountPrice = priceInfo.getElementsByClass("activityprice").first();
                            nomalPrice = priceInfo.getElementsByTag("del").first();
                        }else {
                            nomalPrice = priceInfo.getElementsByTag("strong").first();
                        }
                    }

//                private String classifyInfo;
//                private String shopName;
//                private int nomalPrice;
//                private int discountPrice;
//                private int sellCount;
//                private int recommendTimes;
                    if (allFoodMap.containsKey(foodName.text())) {
                        Map<String, String> oldFoodMap = allFoodMap.get(foodName.text());
                        String classifyStr = oldFoodMap.get("classifyInfo").toString();
                        classifyStr = classifyStr + "~" + classifyInfo.text();
                        oldFoodMap.put("classifyInfo", classifyStr);
                    } else {
                        foodMap.put("foodName", processForQuotation(foodName.text()));
                        foodMap.put("classifyInfo", classifyInfo.text());
                        foodMap.put("nomalPrice", nomalPrice.text());
                        foodMap.put("discountPrice", discountPrice == null ? null : discountPrice.text());
                        foodMap.put("saleCount", saleCount.text());
                        foodMap.put("recommentTiems", recommendTimes.text());
                        allFoodMap.put(foodName.text(), foodMap);
                        //log.info(foodMap);
                    }

                }

            }
//    log.info(allFoodMap);
//    for(String oneFoodMap : allFoodMap.keySet()){
//        Map<String, String> oneFoodMapVlaue = allFoodMap.get(oneFoodMap);
//        log.info(oneFoodMapVlaue);
//    }

            return allFoodMap;
        }catch(Exception e){
            log.error("foodInfoParse error " + html, e);
            e.printStackTrace();
            return null;
        }
}

public static List<Map<String, String>> getReputationJsonInfo(String html){
    List<Map<String, String>> repuResult = new ArrayList<>(100);
    Map<String, Object> reInfo = null;
    try {
        reInfo = (Map<String, Object>)JsonUtils.parseObject(html);
    }catch (Exception e){
        log.error("repuParse error: " + html, e);
        e.printStackTrace();
    }
    if(reInfo == null ){
        return null;
    }
    String errorMessage = (String) reInfo.get("error_msg");
    if (!errorMessage.equals("") || errorMessage == null) {
        return null;
    }
    if(!reInfo.containsKey("result")) {
        return null;
    }
    Map<String, Object> result = (Map<String, Object>) reInfo.get("result");

    if(!result.containsKey("content")){
        Map<String, String> error = new HashMap<>();
        error.put("lastest", "");
        repuResult.add(error);
        return repuResult;
    }
    List<Map<String, Object>> content = null;
    Map<String, Map<String, Object>> linkMapContent = null;
    try {
    if(html.contains("\"content\":{")){
    linkMapContent = (Map<String, Map<String, Object>>)result.get("content");
        content = new ArrayList<>();
        for(Map.Entry<String, Map<String, Object>>  oneContent : linkMapContent.entrySet()){
            content.add(oneContent.getValue());
        }
    }else {
            content = (List<Map<String, Object>>) result.get("content");
    }
    } catch (Exception e) {
        e.printStackTrace();
        log.error("parse reputation error" + html, e);
        System.out.println(html + e);
    }
    if(content == null || content.size() == 0){
        return null;
    }
    String userId = null;
    String userName = null;
    String repuStar = null;
    String repuText = null;
    String repuTime = null;
    String deliveryCost = null;
    String shopName = null;
    for(Map<String, Object> oneRepu : content) {
        Map<String, String> repuMap = new LinkedHashMap<String, String>();
        userId = (String) oneRepu.get("pass_uid");
        userName = (String) oneRepu.get("pass_name");
        repuStar = (String)oneRepu.get("score");

        repuText = (String)oneRepu.get("content");
        if(repuText == null) {
            repuText = "";
        }
        repuTime = (String)oneRepu.get("create_time");
        deliveryCost = Integer.toString((int)oneRepu.get("cost_time"));
        shopName = (String)oneRepu.get("shop_name");
        repuMap.put("userId", userId);
        repuMap.put("userName", userName);
        repuMap.put("repuStar", repuStar);
        repuMap.put("repuText", processForQuotation(repuText));
        repuMap.put("repuTime", repuTime);
        repuMap.put("deliveryCost", deliveryCost);
        repuMap.put("shopName", shopName);
        repuResult.add(repuMap);
        //System.out.println(repuMap);
        //log.info(repuMap);

    }

return repuResult;
}
    public static List<Map<String, String>> getShopId(String html){
    //log.info(html);
    if(html.contains("抱歉，没有找到相关商户和菜品")) {
        return null;
    }
        List<Map<String,String>> getShopList = new LinkedList<>();
        //log.info(html);
        int jsonBegin = html.indexOf("createWidget(");
        int jsonEnd = html.indexOf("displayNewShop:",jsonBegin + 13);
        if(jsonEnd == -1){
            return null;
        }
        String choseJson = html.substring(jsonBegin + 13, jsonEnd);
        choseJson = choseJson.substring(0, choseJson.lastIndexOf(','));
        choseJson = choseJson.replace("shops", "\"shops\"");
        choseJson = choseJson + "}";
        //log.info("choseJson   " + choseJson);

        Map<String, Object> shopList = null;
        try {
            shopList = (Map<String, Object>)JsonUtils.parseObject(choseJson);
        } catch (Exception e) {
            log.error("shopIdParse error: " + html, e);
            e.printStackTrace();
            return null;
        }
        List<Map<String, Object>> shops = (List<Map<String, Object>>)shopList.get("shops");
        String shopName;
        String shopId;
        for(Map<String, Object> oneShop : shops){
            shopName = (String) oneShop.get("shop_name");
            shopId = (String) oneShop.get("shop_id");
            log.info(shopId);
            log.info(shopName);
            Map<String, String> oneShopMap = new HashMap<>();
            oneShopMap.put("shop_name", shopName);
            oneShopMap.put("shop_id", shopId);
            getShopList.add(oneShopMap);
        }



        return getShopList;
    }

    public static String processForQuotation(String originStr){
        if(originStr.contains("\"")) {
            originStr = originStr.replaceAll("\"", "\\\\\"");
        }
        //System.out.println(originStr);
        return originStr;
    }


    public static boolean isOpened(String html){
        Document doc = Jsoup.parse(html);
        Element body = doc.body();
        Element clerfix = body.getElementsByAttributeValue("class", "clearfix").first();
        //log.info("----------" + clerfix + "------------");

        if(clerfix.text() != null) {
            boolean openOrNot = !clerfix.text().equals("该商家无相应菜单");
            log.info("openOrNot； " + openOrNot );
            return openOrNot;
        }
        return true;
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GB2312
                String s = encode;
                return s;      //是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {   //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";

    }


    }
