package util;

import com.bfd.crawler.utils.basicutil.db.DBUtil;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import profile.ConfigCache;
import profile.SqlList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 17:38 2018/1/11
 * @Modified_By:
 */
public
class MyDbUtil {
static {

}
private static final Log log = LogFactory.getLog(util.MyDbUtil.class);
public static  DBUtil dbUtil = DBUtil.getInstance(ConfigCache.MY_DB_POOR);



    public static boolean isHasShop(String id){
    String query = SqlList.SHOPBASEINFO_IS_HAS.replace("#id#", id);
    List<Map<String, Object>> result = dbUtil.query(query);
    if(result.size() > 0){
        return true;
    }
    return false;
}
public static void insertShopUrl(Map<String, String> shopBaseInfo) {
    StringBuffer insertSql = new StringBuffer(SqlList.INSERT_TO_SHOPBASEINFO);
    String url = ConfigCache.SHOP_URL.replace("#shopId#", shopBaseInfo.get("shop_id"));
    insertSql.replace(insertSql.indexOf("#shop_id#"),insertSql.indexOf("#shop_id#") + 9, shopBaseInfo.get("shop_id"));
    insertSql.replace(insertSql.indexOf("#shop_name#"),insertSql.indexOf("#shop_name#") + 11, shopBaseInfo.get("shop_name"));
    insertSql.replace(insertSql.indexOf("#url#"),insertSql.indexOf("#url#") + 5, url);
    insertSql.replace(insertSql.indexOf("#city#"),insertSql.indexOf("#city#") + 6, shopBaseInfo.get("city"));
    insertSql.replace(insertSql.indexOf("#type#"), insertSql.indexOf("#type#") + 6, shopBaseInfo.get("type"));
    log.info(insertSql.toString());
    Map<String, Object> result = dbUtil.add(insertSql.toString());
    if(result.get("err") != null){
        log.info("get an err" + result.get("err") + " sql is: " + insertSql.toString());
    }else {
        log.info("insert successfully: ");
    }


}
public static boolean  insertShopInfo(Map<String, String> shopInfo){
    StringBuffer insertSql = new StringBuffer(SqlList.INSERT_TO_SHOPINFO);
    insertSql.replace(insertSql.indexOf("#shopid#"),insertSql.indexOf("#shopid#") + 8, shopInfo.get("shop_id"));
    insertSql.replace(insertSql.indexOf("#shopname#"),insertSql.indexOf("#shopname#") + 10, shopInfo.get("shopName"));
    insertSql.replace(insertSql.indexOf("#shopurl#"),insertSql.indexOf("#shopurl#") + 9, shopInfo.get("url"));
    insertSql.replace(insertSql.indexOf("#city#"),insertSql.indexOf("#city#") + 6, shopInfo.get("city"));
    insertSql.replace(insertSql.indexOf("#shopdetaillocation#"), insertSql.indexOf("#shopdetaillocation#") + 20, shopInfo.get("shopDetailLocation"));
    insertSql.replace(insertSql.indexOf("#commentnum#"), insertSql.indexOf("#commentnum#") + 12, shopInfo.get("commentNum"));
    insertSql.replace(insertSql.indexOf("#shopstar#"), insertSql.indexOf("#shopstar#") + 10, shopInfo.get("shopStar"));
    insertSql.replace(insertSql.indexOf("#shopholescore#"), insertSql.indexOf("#shopholescore#") + 15, shopInfo.get("shopHoleScore"));
    insertSql.replace(insertSql.indexOf("#deliveryfee#"), insertSql.indexOf("#deliveryfee#") + 13, shopInfo.get("deliveryFee"));
    insertSql.replace(insertSql.indexOf("#deliverythreshold#"), insertSql.indexOf("#deliverythreshold#") + 19, shopInfo.get("deliveryThreshold"));
    insertSql.replace(insertSql.indexOf("#deliveryaverwaiting#"), insertSql.indexOf("#deliveryaverwaiting#") + 21, shopInfo.get("deliverAverwaiting"));
    if(shopInfo.containsKey("province")) {
        insertSql.replace(insertSql.indexOf("#shopprovince#"), insertSql.indexOf("#shopprovince#") + 14, shopInfo.get("province"));
    }else{
        insertSql.replace(insertSql.indexOf("#shopprovince#"), insertSql.indexOf("#shopprovince#") + 14, "null");

    }
    log.info(insertSql.toString());
    Map<String, Object> result = dbUtil.add(insertSql.toString());
    if(result.containsKey("err")){
        log.error(result.get("err") + "insertShopInfo sql get error-----------------" + " shopId: " + shopInfo.get("shop_id") + " sql: " + insertSql);
        return false;
    }
    return true;
}
public static boolean insertNoInfoShop(Map<String, String> shopInfo){
    StringBuffer insertSql = new StringBuffer(SqlList.INSERT_TO_SHOPINFO_WITH_NO_INFO);
    insertSql.replace(insertSql.indexOf("#shopid#"),insertSql.indexOf("#shopid#") + 8, shopInfo.get("shop_id"));
    insertSql.replace(insertSql.indexOf("#shopname#"),insertSql.indexOf("#shopname#") + 10, shopInfo.get("shopName"));
    insertSql.replace(insertSql.indexOf("#shopurl#"),insertSql.indexOf("#shopurl#") + 9, shopInfo.get("url"));
    insertSql.replace(insertSql.indexOf("#city#"),insertSql.indexOf("#city#") + 6, shopInfo.get("city"));
    if(shopInfo.containsKey("province")) {
        insertSql.replace(insertSql.indexOf("#shopprovince#"), insertSql.indexOf("#shopprovince#") + 14, shopInfo.get("province"));
    }else{
        insertSql.replace(insertSql.indexOf("#shopprovince#"), insertSql.indexOf("#shopprovince#") + 14, "null");
    }

    log.info(insertSql.toString());
    Map<String, Object> result = dbUtil.add(insertSql.toString());
    if(result.containsKey("err")){
        log.error(result.get("err") + "insertNoShopInfo sql get error-----------------" + shopInfo.get("shop_id"));
        return false;
    }
    return true;
}

public static String insertFoodInfo (Map<String, String> foodInfo){
    StringBuffer insertSql = new StringBuffer(SqlList.INSERT_TO_FOODINFO);
    insertSql.replace(insertSql.indexOf("#foodname#"),insertSql.indexOf("#foodname#") + 10, foodInfo.get("foodName"));
    insertSql.replace(insertSql.indexOf("#shopid#"),insertSql.indexOf("#shopid#") + 8, foodInfo.get("shop_id"));
    insertSql.replace(insertSql.indexOf("#classifyinfo#"),insertSql.indexOf("#classifyinfo#") + 14, foodInfo.get("classifyInfo"));
    insertSql.replace(insertSql.indexOf("#shopname#"),insertSql.indexOf("#shopname#") + 10, foodInfo.get("shopName"));
    insertSql.replace(insertSql.indexOf("#nomalprice#"),insertSql.indexOf("#nomalprice#") + 12, foodInfo.get("nomalPrice"));
    insertSql.replace(insertSql.indexOf("#salecount#"),insertSql.indexOf("#salecount#") + 11, foodInfo.get("saleCount"));
    insertSql.replace(insertSql.indexOf("#recommendtimes#"),insertSql.indexOf("#recommendtimes#") + 16, foodInfo.get("recommentTiems"));
    if(foodInfo.get("discountPrice") == null){
        insertSql.replace(insertSql.indexOf("#discountprice#"),insertSql.indexOf("#discountprice#") + 15, "null");
    }else {
        insertSql.replace(insertSql.indexOf("#discountprice#"), insertSql.indexOf("#discountprice#") + 15, foodInfo.get("discountPrice"));
    }
    log.info(insertSql);
    return insertSql.toString();
    //dbUtil.add(insertSql.toString());

}
public static String insertReputationInfo (Map<String, String> repuInfo){
    StringBuffer insertSql = new StringBuffer(SqlList.INSERT_TO_REPUTATIONINFO);
    insertSql.replace(insertSql.indexOf("#userid#"),insertSql.indexOf("#userid#") + 8, repuInfo.get("userId"));
    insertSql.replace(insertSql.indexOf("#shopid#"),insertSql.indexOf("#shopid#") + 8, repuInfo.get("shop_id"));
    insertSql.replace(insertSql.indexOf("#shopname#"),insertSql.indexOf("#shopname#") + 10, repuInfo.get("shopName"));
    insertSql.replace(insertSql.indexOf("#deliverycost#"),insertSql.indexOf("#deliverycost#") + 14, repuInfo.get("deliveryCost"));
    insertSql.replace(insertSql.indexOf("#time#"),insertSql.indexOf("#time#") + 6, repuInfo.get("repuTime"));
    if(repuInfo.get("repuText") == null || repuInfo.get("repuText").equals("")) {
        insertSql.replace(insertSql.indexOf("#reputationdetail#"),insertSql.indexOf("#reputationdetail#") + 18, "null");

    }else{
        insertSql.replace(insertSql.indexOf("#reputationdetail#"),insertSql.indexOf("#reputationdetail#") + 18, repuInfo.get("repuText"));

    }
    insertSql.replace(insertSql.indexOf("#stargrade#"),insertSql.indexOf("#stargrade#") + 11, repuInfo.get("repuStar"));
    insertSql.replace(insertSql.indexOf("#username#"),insertSql.indexOf("#username#") + 10, repuInfo.get("userName"));
    log.info(insertSql.toString());
    return insertSql.toString();

}

public static List<Map<String, Object>> query(String query){
 return dbUtil.query(query);
}

public static void updateSuccess(String id){
        String sql = SqlList.UPDATE_SIGN_SUCCESS.replace("#id#", id);
        sql = sql.replace("#grade#", SqlList.GRADE_SUCCESSFUL);
        dbUtil.update(sql);
    }
    public static void updateFailed(String id, String grade){
        String sql = SqlList.UPDATE_SIGN_FAILED.replace("#id#", id);
        sql = sql.replace("#grade#", grade);
        dbUtil.update(sql);
    }

    public static boolean addList(List<String> addListSql){
        log.info("addList" + addListSql);
        Map<String, Object> result = dbUtil.addBatchDiff(addListSql);
        if(result.containsKey("err")){
            log.error(result.get("err") + "insertaddList sql get error-----------------" + addListSql);
            return false;
        }
        return true;
    }
    public static boolean addReadTimeOut(String url){
        String sql = SqlList.READ_TIME_OUT_URL_INSERT;
        sql = sql.replace("#url#", url);
        Map<String, Object> result = dbUtil.add(sql);
        if(result.containsKey("err")){
            log.error(result.get("err") + "insertreadTimeout sql get error-----------------" + sql);
            return false;
        }
        return true;
    }


public static void main(String[] args){
    Map<String, String> testMap = new HashMap<>();
    testMap.put("shop_id", "132354321");
    testMap.put("shop_name", "肯德基");
    testMap.put("city", "北京");
    testMap.put("type", "kendeji");
    //insertShopUrl(testMap);
    for(Map.Entry<String, String> entry : testMap.entrySet()){
        System.out.println(entry.getKey());
    }
}


}

