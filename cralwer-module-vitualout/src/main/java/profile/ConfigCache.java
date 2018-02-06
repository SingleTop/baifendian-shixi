package profile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 12:33 2018/1/2
 * @Modified_By:
 */
public
class ConfigCache {
    public static String PATH_ROOT = "../etc/";

    public final static String CITY_ID = "allCity.txt";
    public final static String REALM_TO_CODE = "realmToCode.txt";
    public final static String CODE_TO_REALM = "codeToRealm.txt";
    public final static String DETAIL_STREET_INFO = "detailStreetInfo.txt";
    public final static String CITY_REALM_CODE = "cityRealmCode.txt";
    public final static String CITY_REALM_STREET = "cityRealmStreet.txt";
    public final static String BAIDU_CITY_REAML_STREET = "baiduCityRealmStreet.txt";
    public final static String SHOP_URL_DOWN_CONTROL = "shopUrlDownControl.txt";
    public final static String URL_CODE_REALMS = "http://passer-by.com/data_location/list.json";
    public final static String NO_FIND_CITY = "noFindCity.txt";
    public final static String CITY_TO_PROVINCE = PATH_ROOT + "cityToProvince.txt";


    public final static String STREET_DIRECTION = "cid=#cityId#&type=0&wd=#realmStreet#&from=pc";
    public final static String STREET_DIRECITON_URL = "http://waimai.baidu.com/waimai?qt=poisug";
    public final static String STREET_GROUP_URL = "http://waimai.baidu.com/waimai?qt=poisearch&from=pc&ie=utf-8&sug=0&tn=B_NORMAL_MAP&oue=1&res=1&c=#cid#&lat=#latitude#&lng=#longitude#&wd=#word#";
    public final static String STREET_GROUP_CODE_URL = "http://waimai.baidu.com/waimai?qt=shoplist&lat=#latitude#&lng=#longitude#&address=#name#&city_id=#cid#";
    public final static String SHOP_URL = "http://waimai.baidu.com/waimai/shop/#shopId#";
    public final static String SEARCH_SHOP_URL = "http://waimai.baidu.com/waimai/shoplist/#id#?wd=#style#";
    public final static String REPUTATION_URL = "http://waimai.baidu.com/waimai/comment/getshop?display=json&shop_id=#shopId#&page=#pageNum#&count=60";

    public final static String MY_DB_PROPERTIES = "D:\\vitualou\\properties\\myDb.properties";
    public final static String MY_DB_POOR = "baidu";
    public final static String MY_LOG_PROPERTIE = "D:\\vitualou\\properties\\myLog.properties";

    public static String PATH_DB = PATH_ROOT + "baiduDb.properties";
    public static String PATH_LOACLIP = PATH_ROOT + "ips";
    public static String PATH_LOG4J = PATH_ROOT + "baiduLog.properties";
    public static String PATH_CONFIG = PATH_ROOT + "baidu-config.properties";


    public final static String JIYEJIA = "吉野家";
    public final static String MAIDANGLAO= "麦当劳";
    public final static String KENDEJI = "肯德基";
    public final static String ZHENGONGFU = "真功夫";
    public final static String YONGHEDAWANG = "永和大王";
    public final static String DASHIXIONGWAIMAI = "大师兄";

    //public final static String START_TIME = "2017-11-01 00:00:00";
    //public final static String END_TIME = "2018-01-01 00:00:00";
    public static String START_TIME = "";
    public static String END_TIME = "";
    public static String IS_ONLY_REPU = "";
    public  static List<String> IP  = new ArrayList<>();
//    static {
//        IP.add("192.168.174.48");
//    }



    //public final Static
}
