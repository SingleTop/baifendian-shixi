package start;

import down.UnSerialise;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import parse.ParseWaimai;
import profile.ConfigCache;
import profile.SqlList;
import util.DownUtil;
import util.MyDbUtil;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 10:56 2018/1/16
 * @Modified_By:
 */



public
class WorkThread implements Runnable {
    public static final Log log = LogFactory.getLog(start.WorkThread.class);
    public static Map<String, String> CityToProvince = UnSerialise.reader(ConfigCache.CITY_TO_PROVINCE);

    private String ip;
    private int boxId;
    private List<Map<String, Object>> tasks;
    private boolean loop = true;


    public
    WorkThread(String ip, int boxId, List<Map<String, Object>> tasks) {
        this.ip = ip;
        this.boxId = boxId;
        this.tasks = tasks;
    }

    @Override
    public
    String toString() {
        return "WorkThread{" + "ip='" + ip + '\'' + ", boxId=" + boxId + ", tasks=" + tasks + '}';
    }

    @Override

    public
    void run() {
        while (loop) {

            Map<String, Object> oneTask = null;
            if ( tasks.get(boxId) == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("wait" + this.toString());
                continue;
            } else {
                oneTask = tasks.get(boxId);
                log.info(oneTask);
            }

            String url = (String) oneTask.get("url");
            String shopId = ((BigDecimal) oneTask.get("id")).toString();
            log.info(shopId);
            String city = (String) oneTask.get("city");
            String province = CityToProvince.get(city);
            String shopName = (String) oneTask.get("shopname");

                String html = DownUtil.downWityDupliPointId(ip, url);
                if (html == null) {
                    MyDbUtil.updateFailed(shopId, SqlList.GRADE_SHOP_AND_FOOD_HTML_NULL);
                    setTaskNull();
                    log.info("null html -------------------" + url);
                    continue;
                }
                if(!ParseWaimai.isOpened(html)){
                    Map<String, String> shopInfo = new LinkedHashMap<>();
                    if(shopId != null){
                        shopInfo.put("shop_id",shopId);
                    }
                    if(city != null){
                        shopInfo.put("city", city);
                    }
                    if(shopName != null){
                        shopInfo.put("shopName", shopName);
                    }
                    if(url != null){
                        shopInfo.put("url", url);
                    }
                    if(province != null) {
                        shopInfo.put("province", province);
                    }
                    if(!MyDbUtil.insertNoInfoShop(shopInfo)) {
                        MyDbUtil.updateFailed(shopId, SqlList.GRADE_SHOP_MESSAGE_NULL);
                        log.info("该商家无相应菜单" + url);
                        setTaskNull();
                        continue;
                    }
                    MyDbUtil.updateSuccess(shopId);
                    log.info("该商家无相应菜单" + url);
                    setTaskNull();
                    continue;

                }
if(!ConfigCache.IS_ONLY_REPU.equals("yes")) {
    log.info("excute shopId");

    if (oneTask.get("status").toString().equals(SqlList.GRADE_SHOPINFO_ERROR) || oneTask.get("status").toString().equals(SqlList.GRADE_ORIGINAL)) {
        Map<String, String> shopInfo = ParseWaimai.getShopInfo(html);
        if (shopInfo == null) {
            MyDbUtil.updateFailed(shopId, SqlList.GRADE_SHOPINFO_ERROR);
            setTaskNull();
            log.info("shopInfo err " + url);
            continue;
        } else {

            shopInfo.put("shop_id", shopId);
            shopInfo.put("city", city);
            shopInfo.put("url", url);
            if (province != null) {
                shopInfo.put("province", province);
            }
            log.info("shopInfo" + shopInfo);
            if (!MyDbUtil.insertShopInfo(shopInfo)) {
                MyDbUtil.updateFailed(shopId, SqlList.GRADE_SHOPINFO_ERROR);
                setTaskNull();
                log.info("shopInfo sql err " + url);
                continue;
            }
        }
    }

    log.info("excute shopFood");

    if (oneTask.get("status").toString().equals(SqlList.GRADE_SHOPINFO_ERROR) || oneTask.get("status").toString().equals(SqlList.GRADE_FOODINFO_ERROR) || oneTask.get("status").toString().equals(SqlList.GRADE_ORIGINAL)) {

        Map<String, Map<String, String>> foodInfo = ParseWaimai.getFoodsInfo(html);
        if (foodInfo == null) {
            MyDbUtil.updateFailed(shopId, SqlList.GRADE_FOODINFO_ERROR);
            setTaskNull();
            log.info("foodInfo err " + url);
            continue;
        }
        List<String> foodInsertSqlList = new LinkedList<>();
        for (Map.Entry<String, Map<String, String>> oneFoodEntry : foodInfo.entrySet()) {
            Map<String, String> oneFoodMap = oneFoodEntry.getValue();
            oneFoodMap.put("shop_id", shopId);
            oneFoodMap.put("shopName", shopName);
            foodInsertSqlList.add(MyDbUtil.insertFoodInfo(oneFoodMap));
        }
        if (!MyDbUtil.addList(foodInsertSqlList)) {
            MyDbUtil.updateFailed(shopId, SqlList.GRADE_FOODINFO_ERROR);
            setTaskNull();
            log.info("foodInfo sql err " + url);
            continue;
        }
    }
}

            log.info("excute shopRepu");

            if(oneTask.get("status").toString().equals(SqlList.GRADE_SHOPINFO_ERROR)
                ||oneTask.get("status").toString().equals(SqlList.GRADE_FOODINFO_ERROR)
                ||oneTask.get("status").toString().equals(SqlList.GRADE_REPUINFO_ERROR)
                || oneTask.get("status").toString().equals(SqlList.GRADE_ORIGINAL)) {

                int pageNum = 0;
                String repuUrl = DownUtil.getRepuUrl(shopId, pageNum);
                String repuJson = DownUtil.downWityDupliPointId(ip, repuUrl);
                if (repuJson == null) {
                    MyDbUtil.updateFailed(shopId, SqlList.GRADE_REPUINFO_ERROR);
                    log.info("shopRepu err " + url);
                    setTaskNull();
                    continue;
                }
                List<Map<String, String>> repuInfo = ParseWaimai.getReputationJsonInfo(repuJson);
                if(repuInfo != null){
                    Map<String, String> testMap = repuInfo.get(0);
                    if(testMap.containsKey("lastest")){
                        log.info("the lastest");
                        MyDbUtil.updateSuccess(shopId);
                        setTaskNull();
                        continue;
                    }
                }
                List<String> repuInsertSqlList = new LinkedList<>();
                while (repuInfo != null) {
                    for (Map<String, String> repuMap : repuInfo) {
                        if (DownUtil.elderTime(repuMap.get("repuTime"))) {
                            pageNum = 10000000;
                            log.info("elder");
                            break;
                        }
                        if (!DownUtil.judgeBetweenTime(repuMap.get("repuTime"))) {
                            log.info("younger");
                            continue;

                        }

                        repuMap.put("shop_id", shopId);
                        repuInsertSqlList.add(MyDbUtil.insertReputationInfo(repuMap));
                    }
                    pageNum++;
                    if (pageNum > 10000000) {
                        break;
                    }
                    repuUrl = DownUtil.getRepuUrl(shopId, pageNum);
                    repuJson = DownUtil.downWityDupliPointId(ip, repuUrl);
                    repuInfo = ParseWaimai.getReputationJsonInfo(repuJson);
                }
                if(!MyDbUtil.addList(repuInsertSqlList)){
                    MyDbUtil.updateFailed(shopId, SqlList.GRADE_REPUINFO_ERROR);
                    log.info("shopRepu sql err " + url);
                    setTaskNull();
                    continue;
                }
            }

            MyDbUtil.updateSuccess(shopId);
            setTaskNull();
        }
    }

public void setTaskNull (){
        tasks.set(boxId, null);
}




}
