package com.bfd.mf.monitor.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description: 所有配置文件和目录还有sql拼接定义，都在其中
 * @Date: Created in 16:53 2017/11/1
 * @Modified_By: wang
 */
public
class ConfigCache {
	public final static String classRoot = "class com.bfd.mf.wang.monitor.dao.JudgeBy";
	public static String DB_CONN_NAME = "monitor";
	public static String MAINSQL="select * from bfd_mf_crawl_stat.crawl_monitor_config";
	public static final String NUM_FIR_SQL ="select sum(num) as allnum from (";
	public static final String NUM_FIR_CODE_SQL="select allnum ,keyvalue from (select sum(num) as allnum , D05 from ( ";
	public static final String NUM_FIR_PAGE_SQL="select * from( select * from (select sum(num) as allnum , D04 ,d05 from (  ";
	public static final String NUM_PRE_SQL ="select sum(num) as num from bfd_mf_crawl_stat.statdata_";
	public static final String NUM_PRE_CODE_SQL= "select sum(num) as num ,d05 from bfd_mf_crawl_stat.statdata_";
	public static final String NUM_PRE_PAGE_SQL= "select sum(num) as num ,d04 ,d05 from bfd_mf_crawl_stat.statdata_";
	public static final String NUM_MID1_SQL =" where ";
	public static final String NUM_MID2_SQL =" and datacreatetime between";
	public static final String NUM_LAST_SQL = ")as c";
	public static final String NUM_LAST_CODE_SQL=")as c  group by D05 ) as B inner join (select keyvalue ,fieldvalue from bfd_mf_crawl_stat.statcontrol where fieldname = 'd05') as control on b.D05 = control.fieldvalue order by allnum desc ";
	public static final String NUM_LAST_PAGE_SQL=" )as c  group by D04 ,d05=1) as B  inner join (select keyvalue ,fieldvalue from bfd_mf_crawl_stat.statcontrol where fieldname = 'd04' ) as control on b.D04 = control.fieldvalue )  as y where d05 is not null order by allnum desc ";
	public static final String TASK_PRE_SQL = "select * from mf_crawler.crawl_monitor_config where next_time <= '";
	public static final String TASK_AFTER_SQL = "' and status<>0 and receivers is not null";
	public static final String UPDATE_TASK_PRE_SQL = "update mf_crawler.crawl_monitor_config set next_time='";
	public static final String UPDATE_TASK_AFTER_SQL = "' where id =";

	public static final String CLASSNAMEROOT = "com.bfd.mf.monitor.service.JudgeBy";


	public static final String MY_DB_PROPERTIES = "D:\\wang-baifendian\\properties\\db.properties";
	public static final String MY_LOG_PROPERTIES = "D:\\wang-baifendian\\properties\\log.properties";
	public static final String PATH_ROOT = "../etc/";
	public static final String ONLINE_DB_PROPERTIES = PATH_ROOT + "db.properties";
	public static final String ONLINE_LOG_PROPERTIES = PATH_ROOT + "monitorLog.properties";
	public static final String AZURE_DB_PROPERTIES = PATH_ROOT + "monitorDb.properties";
	public static final String AZURE_LOG_PROPERTIES = PATH_ROOT + "log4j.properties";



	public static final String NUM_FIRST_PAGE_CODE_SQL = "select * from( select * from (select sum(num) as allnum , D04 ,d05 from (  ";
	public static final String NUM_PRE_PAGE_CODE_SQL= "select sum(num) as num ,d04 ,d05 from bfd_mf_crawl_stat.statdata_";
	public static final String NUM_LAST_PAGE_CODE_SQL= ")as c  group by D04 ) as B  inner join (select keyvalue ,fieldvalue from bfd_mf_crawl_stat.statcontrol where fieldname = 'd04' ) as control on b.D04 = control.fieldvalue )  as y where d05 is not null order by allnum desc ";

	public static final String DETAIL_FIRST_All_PAGE_CODE_SQL = " select * from ( select * from ( select sum(num) as allnum, D04, d05 from  ( ";
	public static final String DETAIL_PRE_ALL_PAGE_CODE_SQL = " select sum(num) as num, d04, d05 from bfd_mf_crawl_stat.statdata_";
	public static final String DETAIL_LAST_PAGE_CODE_SQL = ") as c group by D04,d05 ) as B inner join  ( select keyvalue as keyvalued04 , fieldvalue as fieldvalued04 from bfd_mf_crawl_stat.statcontrol where fieldname = 'd04' ) as control4 on b.D04 = control4.fieldvalued04 inner join (select keyvalue as keyvalued05 ,fieldvalue as fieldvalued05 from bfd_mf_crawl_stat.statcontrol where fieldname = 'd05') as control5 on b.d05 = control5.fieldvalued05) as x where (d05 is not null and d05<>1) order by d05 , allnum DESC";

    public static final String CODE_INTERPRET = "select * from mf_crawler.code_interpret";

 	/**
 	*存储每个code所对应的解释，从数据库中读取。
 	*/
	public static Map<String, String> codeBack = new HashMap<String, String>();




}
