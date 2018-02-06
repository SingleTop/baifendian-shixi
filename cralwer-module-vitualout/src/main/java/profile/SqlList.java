package profile;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 18:24 2018/1/11
 * @Modified_By:
 */
public
class SqlList {
    public final static String SHOPBASEINFO_IS_HAS = "select * from shopbaseinfo where id = #id#";
    public final static String INSERT_TO_SHOPBASEINFO = "insert into shopbaseinfo (shopname, url, city, id, type) values (\"#shop_name#\", \"#url#\", \"#city#\", \"#shop_id#\", \"#type#\")";
    public final static String GET_ALL_SHOP_URL = "select * from shopbaseinfo  where status <> 8 ";
    public final static String INSERT_TO_SHOPINFO = "insert into shopinfo (shopid,shopname, shopurl, shopdetaillocation, commentnum, shopstar, shopholescore,  deliveryfee, deliverythreshold,deliveryaverwaiting, city, shopprovince) values (#shopid#,\"#shopname#\", \"#shopurl#\", \"#shopdetaillocation#\", \"#commentnum#\", \"#shopstar#\", \"#shopholescore#\",  #deliveryfee#, #deliverythreshold#,\"#deliveryaverwaiting#\", \"#city#\", \"#shopprovince#\")";
    public final static String INSERT_TO_FOODINFO = "insert into foodinfo (foodname, classifyinfo, shopname, nomalprice, discountprice, salecount, recommendtimes, shopid) values(\"#foodname#\", \"#classifyinfo#\", \"#shopname#\", \"#nomalprice#\", \"#discountprice#\", \"#salecount#\", \"#recommendtimes#\", #shopid#)";
    public final static String INSERT_TO_REPUTATIONINFO = "insert into reputationinfo (username, stargrade, reputationdetail,time, deliverycost, shopname, userid, shopid) values(\"#username#\", #stargrade#, \"#reputationdetail#\",\"#time#\", #deliverycost#, \"#shopname#\",  #userid#, #shopid#)";
    public final static String UPDATE_SIGN_SUCCESS = "update shopbaseinfo set status = #grade# where id = #id#";
    public final static String UPDATE_SIGN_FAILED = "update shopbaseinfo set status = #grade# where id = #id#";
    public final static String INSERT_TO_SHOPINFO_WITH_NO_INFO = "insert into shopinfo (shopid,shopname, shopurl,city,shopprovince) values (#shopid#,\"#shopname#\", \"#shopurl#\",\"#city#\",\"#shopprovince#\")";
    public final static String READ_TIME_OUT_URL_INSERT = "insert into readtimeout (url) values (\"#url#\")";

    public final static String GRADE_SHOP_AND_FOOD_HTML_NULL = "3";
    public final static String GRADE_SHOPINFO_ERROR = "1";
    public final static String GRADE_FOODINFO_ERROR = "2";
    public final static String GRADE_REPUINFO_ERROR = "4";
    public final static String GRADE_SHOP_MESSAGE_NULL = "5";
    public final static String GRADE_SUCCESSFUL = "8";
    public final static String GRADE_ORIGINAL = "0";





    public final static String TEST = "select * from test.shopbaseinfo where id = 1436183777";

}
