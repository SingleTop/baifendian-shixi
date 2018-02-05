package com.bfd.mf.monitor.service;

import com.bfd.mf.monitor.StartMain;
import com.bfd.mf.monitor.config.ConfigCache;
import com.bfd.mf.monitor.entity.TaskEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.CORBA.OBJ_ADAPTER;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.service.MFMailService;
import org.simplejavamail.service.conf.MailConfig;
import scala.collection.SeqLike;
import scala.util.parsing.combinator.testing.Str;

import javax.mail.internet.MimeMessage;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author: chengwei.wang
 * @Description: 子类用到的所有向上抽象的方法都在里面。
 * @Date: Created in 16:07 2017/11/1
 * @Modified_By: wang
 */
public abstract  class AbstractJudge implements IJudge {
	/**
	*格式，尽量保留四位小数，第一位没有则默认为0
	*/
	private static DecimalFormat rateIllustate = new DecimalFormat("0.####");
	/**
	*int类型的大数，每三位以‘，’分隔
	*/
	private static DecimalFormat intIllustate = new DecimalFormat("#,###");
	/**
	*对发送信件必要配置加载和初始化的类实例
	*/
	private static MFMailService mfMailService = null;
	private static final Log log = LogFactory.getLog(AbstractJudge.class);
	/**
	*@params: 开始时间 ， 选定的数量， 总数量 ，任务， 要发的描述类型， 返回的sql-方便直接排查
	*@Description: 如果指定数量或者总数量其中有一个是0，那么就会给chengwei.wang发送邮件报错去排查
	*@Date: 19:54 2017/12/1
	*/
	protected void sendSqlErrorMail( String beginTime, long pointNum, double allNum, TaskEntity taskEntity ,String detail, String errorSql){
		StringBuilder message = new StringBuilder();
		message.append("<系统自动通知>\n<查询出现异常> " +detail + "\n--------------------------------------------------------------------------------------\n");
		message.append(">事件： " + taskEntity.getName() + "\n");
		message.append(">描述：\n");
		message.append("预警时间跨度: " + taskEntity.getStepSize() + "分钟\n");
		message.append("时间：" + beginTime + " ---> " + taskEntity.getNextTime().substring(0, taskEntity.getNextTime().length() - 2) + "\n");
		message.append("配置条件对应code的数量：" + pointNum + "\n");
		message.append("所有的数量： " + allNum + "\n-----------------------**\n\n");
		message.append("错误的sql是： " + errorSql);
		String[] usermail = new String[1];
		usermail[0] = "chengwei.wang@baifendian.com";
		sendMail(usermail, message.toString(), taskEntity);
	}
/**
*@params: 需要查几张表， 开始时间， 结束时间， 实际的占比， 任务
*@Description: 概率预警就使用这个方法完成所有调用
*@Date: 19:57 2017/12/1
*/

	protected
	void sendRateWarningMail(List<String> tables, double realRate, Long pointNum, TaskEntity taskEntity, List<String> fCondition) {

		String beginTime = taskEntity.getBegingTime();
		String endTime = taskEntity.getEndTime();

		StringBuffer message = new StringBuffer();
		message.append("事件： " + taskEntity.getName() + "\n");
		message.append("时间：" + beginTime + " ---> " + taskEntity.getNextTime().substring(0, taskEntity.getNextTime().length() - 2) + "\n");
		message.append("阈值： " + rateIllustate.format(taskEntity.getThreshold() * 100d) + "%\n");
		message.append("实际： " + rateIllustate.format(realRate * 100d) + "%  " + pointNum + "\n");
		message.append("\n-----------------------**\n\n");
		message.append("codesMessage:" + "\n");
		List<Map<String, Object>> codeList = codesMessageForMail(tables, taskEntity , taskEntity.getNewCondition());
		List<Map<String, Object>> failedCodeList = getFaildCodeList(tables, taskEntity, fCondition);
        List<Map<String, Object>> finalCodeList = failedCodeReduce(failedCodeList, codeList);

		message.append("codetype   " + "  number" + "\n");
		for (Map<String, Object> oneLine : finalCodeList) {
			log.info("warning info of codes"  + oneLine.get("keyvalue"));
			message.append(oneLine.get("keyvalue"));
			if (ConfigCache.codeBack.get(oneLine.get("keyvalue").toString()) != null) {
			    message.append("（"+ ConfigCache.codeBack.get(oneLine.get("keyvalue").toString()) + "）");
            }
            message.append("-----" + intIllustate.format(Integer.parseInt(oneLine.get("allnum").toString())) +  "\n");
		}
        message.append("-----------------------\n\n");
		String pagesMessageAllSql = pagesCodeMessageForMail(taskEntity.getNewCondition(), tables, taskEntity);
		List<Map<String, Object>>  pageTypeListOfAll = StartMain.dbUtil.query(pagesMessageAllSql);

        String detailMessageSql = allPageCodeDetailMessageForMail(taskEntity.getNewCondition(), tables, taskEntity);
        List<Map<String, Object>> detailMessage = StartMain.dbUtil.query(detailMessageSql);

        List<Map<String, Object>> rectifyDetailMessage = getRectifyDetailMessage(fCondition, tables, taskEntity);
        List<Map<String, Object>> lastDetailMessage = processDetailMessage(detailMessage, rectifyDetailMessage);


		//第三个参数没有用到,为未来拓展做准备。
        Map<String, Map<String, List<String>>> devideByCode = procseeForDetailMessage(detailMessage, codeList, pageTypeListOfAll);
        Map<String, Map<String, List<String>>> devideByCode2 = procseeForDetailMessage(lastDetailMessage, finalCodeList, pageTypeListOfAll);

        Iterator< Map.Entry<String, Map<String, List<String>>>> it2 = devideByCode2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<String, Map<String, List<String>>> entry = it2.next();
            Map<String, List<String>> map = entry.getValue();
            message.append("code: " + entry.getKey() + "\n");
            for(Map.Entry<String, List<String>> minentry : map.entrySet()) {
                List<String> list = minentry.getValue();
                message.append(minentry.getKey() + " " + list.get(2) + "%  " + list.get(1) + "\n");

            }
            message.append("\n");
        }
        /*
        message.append("-----------------------------------\n");

        Iterator< Map.Entry<String, Map<String, List<String>>>> it = devideByCode.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Map<String, List<String>>> entry = it.next();
            Map<String, List<String>> map = entry.getValue();
            message.append("code: " + entry.getKey() + "\n");
            for(Map.Entry<String, List<String>> minentry : map.entrySet()) {
                List<String> list = minentry.getValue();
                message.append(minentry.getKey() + " " + list.get(2) + "%  " + list.get(1) + "\n");

            }
            message.append("\n");
        }
*/
        //System.out.println(message.toString());
		sendMail(taskEntity.getReceivers(), message.toString(), taskEntity);

	}
	/**
	 *@params: 需要查几张表， 开始时间， 结束时间， 实际的占比， 任务
	 *@Description: 数量预警就使用这个方法所有调用
	 *@Date: 19:57 2017/12/1
	 */
	protected
	void sendNumWarningMail(TaskEntity taskEntity, long realNum, String beginTime) {
		StringBuffer message = new StringBuffer();
		message.append("事件：" + taskEntity.getName() + "\n");
		message.append("开始时间：" + beginTime + "\n");
		message.append("结束时间：" + taskEntity.getNextTime().substring(0, taskEntity.getNextTime().length() - 2) + "\n");
		message.append("阈值: " + intIllustate.format(taskEntity.getThreshold()) + "\n实际: " + intIllustate.format(realNum));
		message.append("\n-----------------------**\n\n");
		sendMail(taskEntity.getReceivers(), message.toString(), taskEntity);

	}

	private
	void sendMail(String[] recipients, String message, TaskEntity taskEntity) {
		Email email = new Email();
		for (int i = 0; i < recipients.length; i++) {
			email.addRecipient(recipients[i], recipients[i], MimeMessage.RecipientType.TO);
			log.info("has added one recipients " + recipients[i]);
		}
		email.setFromAddress("CRAWL预警通知-" + taskEntity.getAlarmType(), "mediaforce_product@baifendian.com");
		email.setSubject("【境内线上预警-警告】" + taskEntity.getAlarmType() + "warning");
		email.setText(message);
		log.info("one mail's messages " + "【境内线上预警-警告】" + taskEntity.getAlarmType() + "warning\n" + message.substring(0, message.indexOf("**")));
		//System.out.println(message.substring(0, message.indexOf("**")));
		try {
			if (getMfMailService().sendMail(email)) {
				log.info("email has sended successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/**
*@params:
*@Description: 所有页面的code情况，按照code类型划分，求出总量（code等于1 也就是成功的信息，不做统计）
*@Date: 10:07 2017/12/4
*/

	protected
	List<Map<String, Object>> codesMessageForMail(List<String> tables, TaskEntity taskEntity, String condition) {
		List<Map<String, Object>> result;
		String sqlForCode = ConfigCache.NUM_FIR_CODE_SQL;

		for (String table : tables) {
			String oneTable = ConfigCache.NUM_PRE_CODE_SQL + table + ConfigCache.NUM_MID1_SQL + condition + " and d05<>1 " + ConfigCache.NUM_MID2_SQL + "'" + taskEntity.getBegingTime() + "' and '" + taskEntity.getEndTime() + "'  group by d05";
			sqlForCode = sqlForCode + oneTable + "   union ";
		}
		sqlForCode = sqlForCode.substring(0, sqlForCode.length() - 6) + ConfigCache.NUM_LAST_CODE_SQL;
		log.info("sql of codesMessageForMail: " + sqlForCode);
		result = StartMain.dbUtil.query(sqlForCode);
        //System.out.println("code: " + sqlForCode);

		return result;
	}

	protected
	List<Map<String, Object>> pagesMessageForMail(List<String> tables, TaskEntity taskEntity, String beginTime, String endTime) {
		List<Map<String, Object>> result;
		String sqlForCode = ConfigCache.NUM_FIR_PAGE_SQL;

		for (String table : tables) {
			String oneTable = ConfigCache.NUM_PRE_PAGE_SQL + table + ConfigCache.NUM_MID1_SQL + taskEntity.getCondition() + ConfigCache.NUM_MID2_SQL + "'" + beginTime + "' and '" + endTime + "' group by D04,d05=1 ";
			sqlForCode = sqlForCode + oneTable + "   union ";
		}
		sqlForCode = sqlForCode.substring(0, sqlForCode.length() - 6) + ConfigCache.NUM_LAST_PAGE_SQL;
		log.info("sql of pagesMessageForMail: " + sqlForCode);
		result = StartMain.dbUtil.query(sqlForCode);
		return result;
	}
/**
*@params:
*@Description: 符合condition约束条件的条目，都在其中
*@Date: 10:11 2017/12/4
*/
	protected String pagesCodeMessageForMail(String condition, List<String> tables, TaskEntity taskEntity){
		List<Map<String, Object>> result;
		StringBuilder sqlForPages = new StringBuilder();
		sqlForPages.append(ConfigCache.NUM_FIRST_PAGE_CODE_SQL);
		for (String table : tables) {
			sqlForPages.append(ConfigCache.NUM_PRE_PAGE_CODE_SQL + table
                + ConfigCache.NUM_MID1_SQL + condition
                + ConfigCache.NUM_MID2_SQL + "'" + taskEntity.getBegingTime() + "' and '"
                + taskEntity.getEndTime() + "' group by D04 ");
			sqlForPages.append(" union ");
		}
		sqlForPages.delete(sqlForPages.length()-6, sqlForPages.length());
		sqlForPages.append( ConfigCache.NUM_LAST_PAGE_CODE_SQL);
		log.info("-------------------sql of pagesCodeMessageForMail: " + sqlForPages);
		return sqlForPages.toString();

	}

	/**
	*@params:
	*@Description: 查询每一种页面类型的每一种code类型，按照code类型和每一种页面类型的code数量排序
	*@Date: 10:14 2017/12/4
	*/
	protected String allPageCodeDetailMessageForMail(String condition, List<String> tables, TaskEntity taskEntity) {
	    List<Map<String, Object>> result;
	    StringBuilder sqlForPages = new StringBuilder();
	    sqlForPages.append(ConfigCache.DETAIL_FIRST_All_PAGE_CODE_SQL);
	    for (String table : tables) {
	        sqlForPages.append(ConfigCache.DETAIL_PRE_ALL_PAGE_CODE_SQL + table
                + ConfigCache.NUM_MID1_SQL + condition
            + ConfigCache.NUM_MID2_SQL + "'" +taskEntity.getBegingTime() + "' and '" + taskEntity.getEndTime()
                + "' group by d04, d05" );
            sqlForPages.append(" union ");
        }
        sqlForPages.delete(sqlForPages.length()-6, sqlForPages.length());
	    sqlForPages.append(ConfigCache.DETAIL_LAST_PAGE_CODE_SQL);
	    //System.out.println("allPageCodeDetailMessageForMail: " + sqlForPages.toString());
	    return sqlForPages.toString();
     }
     /**
     *@params: detailMessage 查到的详情表，每一种页面类型的每一种code类型； allcodenummessage， 符合条件的code的总量
     *@Description: 计算每一种code的不同页面类型占比，按照占比大小从大到小排序。
     *@Date: 10:15 2017/12/4
     */
//                                                                                                       查到的详情表                     code的所有表                                         页面类型的code总量
     private  Map<String, Map<String, List<String>>> procseeForDetailMessage (List<Map<String ,Object>> detailMessage, List<Map<String, Object>> allCodeNumMessage, List<Map<String, Object>> pageAllNumMessage) {
         Map<String, Map<String, List<String>>> result = new HashMap<String, Map<String, List<String>>>();
         Map<String, String> midData = new HashMap<String ,String>();
         //创建以code为类别的初始map key为code类型，value为对应的map，现在为空
         for (Map<String, Object> oneItem : allCodeNumMessage) {
             result.put(oneItem.get("keyvalue").toString(), new LinkedHashMap<String, List<String>>());
         }
         //获得每一个页面的总量，key为页面类型 value 为总数量
         for (Map<String, Object> oneItem : allCodeNumMessage) {
             midData.put(oneItem.get("keyvalue").toString(), oneItem.get("allnum").toString());
         }
         //将result的map类型的value值填补上去
         for (Map<String, Object> oneItem : detailMessage) {
             List<String> list = new ArrayList<String>(4);
             //第一个是
            list.add(midData.get(oneItem.get("keyvalued05").toString()));
            list.add(oneItem.get("allnum").toString());
            //System.out.println("d05: "  + oneItem.get("keyvalued05") + "d04: " + oneItem.get("keyvalued04")
            //    + "one: " + list.get(1) + " two: " + list.get(0));
            double rate = Double.parseDouble(list.get(1)) / Double.parseDouble(list.get(0));
            list.add(rateIllustate.format(rate * 100D));
            list.set(0, intIllustate.format(Integer.parseInt(list.get(0))));
            result.get(oneItem.get("keyvalued05").toString()).put(oneItem.get("keyvalued04").toString(), list);
         }
         return result;
     }


/**
*@params: begintime  开始时间 ， endtime 结束时间
*@Description: 通过字符串计算开始时间和结束时间之间所需要使用的表，得到的数据名，和表名仅仅结合。stat_201711
*@Date: 10:19 2017/12/4
*/
	protected
	List<String> tableUsed(String beginTime, String endTime) {
		List<String> tables = new ArrayList<String>();
		int beginYearLength = beginTime.indexOf("-");
		int endYearlength = endTime.indexOf("-");
		String beginYear = beginTime.substring(0, beginYearLength);
		String beginMonth = beginTime.substring(beginYearLength + 1, beginYearLength + 3);
		String beginDay = beginTime.substring(beginYearLength + 4, beginYearLength + 6);
		String endYear = endTime.substring(0, endYearlength);
		String endMonth = endTime.substring(endYearlength + 1, endYearlength + 3);
		String endDay = endTime.substring(endYearlength + 4, endYearlength + 6);

		log.info("time of alarm condition-begin: " + beginYear + "-" + beginMonth + "-" + beginDay);
		log.info("time of alarm condition-end: " + endYear + "-" + endMonth + "-" + endDay);
		//System.out.println(beginYear+"-"+beginMonth+"-"+beginDay);
		//System.out.println(endYear+"-"+endMonth+"-"+endDay);

		if (!beginYear.equals(endYear)) {
			int difference = Integer.parseInt(endYear) - Integer.parseInt(beginYear);
			for (int i = 0; i <= difference; i++) {
				if (i == 0) {
					for (int k = Integer.parseInt(beginMonth); k <= 12; k++) {
						tables.add(((Integer) (Integer.parseInt(beginYear) + i)).toString() + (k < 10 ? ("0" + ((Integer) k).toString()) : ((Integer) k).toString()));

					}
				} else
					if (i == difference) {
						//需要当月第一天便生成月表
						for (int k = 0; k <= Integer.parseInt(endMonth); k--) {
							tables.add(((Integer) (Integer.parseInt(beginYear) + i)).toString() + (k < 10 ? ("0" + ((Integer) k).toString()) : ((Integer) k).toString()));
						}
					} else {
						for (int k = 0; k <= 12; k++) {
							tables.add(((Integer) (Integer.parseInt(beginYear) + i)).toString() + (k < 10 ? ("0" + ((Integer) k).toString()) : ((Integer) k).toString()));
						}
					}

			}
			tables.add(endYear);
			return tables;
		}
		int monthDifference = Integer.parseInt(endMonth) - Integer.parseInt(beginMonth);
		if (!endMonth.equals(beginMonth)) {
			for (int i = Integer.parseInt(beginMonth); i < Integer.parseInt(endMonth); i++) {
				tables.add(endYear + (i < 10 ? ("0" + ((Integer) i).toString()) : ((Integer) i).toString()));
			}
			tables.add(endYear);
			return tables;
		}
		if (!beginDay.equals(endDay)) {
			tables.add(endYear + endMonth);
			tables.add(endYear);
			return tables;
		}

		tables.add(endYear);
		return tables;

	}
/**
*@params:
*@Description: 获得发送信件的资源，资源存储在全局变量中，一次加载处处使用
*@Date: 10:21 2017/12/4
*/
	private synchronized
	MFMailService getMfMailService() {
		if (mfMailService == null) {
			mfMailService = mfMailService();
			return mfMailService;
		}
		return mfMailService;

	}

	private
	MFMailService mfMailService() {
		try {
			MailConfig mailConfig = new MailConfig();
			mailConfig.setServerConfig(new ServerConfig("smtp.baifendian.com", 25, "mediaforce_product@baifendian.com", "R8pRjqK889"));
			mailConfig.setThreadPoolSize(10);
			mailConfig.setConnectionTimeout(10000);
			mailConfig.setTimeout(10000);
			return new MFMailService(mailConfig);
		} catch (Exception e) {
			log.error("failure of initializing MFMailService  ", e);
		}
		log.error("get return null of initializing MFMAILService ");
		return null;
	}

	/**
	*@params: test 表里定义的condition字符串
	*@Description: 将condition 字符串里的关于d05的描述全都删除。返回删除后的。
	*@Date: 10:22 2017/12/4
	*/

	protected
	String SubstringD05(String test) {
		//System.out.println(test.toLowerCase());
		test = test.toLowerCase().trim();
		int index = test.indexOf("d05");
		int indexAfter;
		int lenth = 0;
		String pretest = "";
		String afttest = "";
		if(index < 0){
			return " " + test + " ";
		}
		if(index > 0) {
			//找到了d05
			pretest = test.substring(0, index);
			pretest = pretest.trim();
			lenth = pretest.length();
			if(lenth>0 && pretest.substring(lenth-1,lenth).equals("(")){
				pretest = pretest.substring(0,lenth-1).trim();
			}
			lenth = pretest.length();
			if(lenth>0 &&pretest.substring(lenth-3,lenth).equals("and")){
				pretest = pretest.substring(0,lenth-3);
			}
			//System.out.println("this is pretest " + pretest);

		}

		// d05 以前字符串已经处理，下面是以后的

		indexAfter = test.indexOf(")",index + 3);

		//indexAfter 不可能等于0
		if(indexAfter > 0){
			afttest = test.substring(indexAfter+1, test.length()).trim();
		}
		lenth = afttest.length();
		if(lenth>0&&afttest.substring(0,3).equals("and")) {

			afttest = afttest.substring(3, lenth);

		}
		return pretest + " " + afttest;


	}
	/**
	*@params: 字符串形式的初级条件， 失败的条件list， 成功的条件list
	*@Description: 按照 -f 还是-s 分别加入到失败条件list 和成功条件list  代表 failed successed。
	*@Date: 16:16 2017/12/22
	*/
	protected String subCondition (String conditions, List<String> fList, List<String> sList) {
		int begin = conditions.indexOf("{");
		if(begin != -1) {
			String subStr = conditions.substring(conditions.indexOf("{") + 1, conditions.indexOf("}"));
			conditions = conditions.substring(0, begin);
			String conArray[] = subStr.split("],|]\\s+,");
			int length = conArray.length;
			for (int i = 0; i < length; i++) {
				String item = conArray[i];
				//System.out.println(item);
				if (item.contains("-f") || item.contains("-F")) {
					fList.add(item.substring(item.indexOf("[") + 1, item.length()-1));
					continue;
				}
				if (item.contains("-s") || item.contains("-S")) {
					sList.add(item.substring(item.indexOf("[") + 1, item.length()-1));
					continue;
				}
				System.out.println("fatal error for conditions");
			}
			return conditions.substring(0, begin);
		}
		return conditions;
	}

/**
*@params: 所用到的tables,  任务， 附加条件
*@Description: 获得附加条件下的内容信息。
*@Date: 16:05 2017/12/22
*/
  private List<Map<String, Object>> getFaildCodeList(List<String>tables, TaskEntity taskEntity, List<String>fCondition){
      if (fCondition.size() == 0) {
          return null;
      }
	  List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	  for(int i = 0 ; i < fCondition.size() ; i++ ) {
		  List<Map<String, Object>> get = codesMessageForMail(tables, taskEntity, fCondition.get(i));
		  result.addAll(get);
	}
		return result;
  }
/**
*@params: 要求去掉的失败的list，总的codeList
*@Description: 相同code 两个列表里的数量数据相减，得到修正过的数据
*@Date: 16:07 2017/12/22
*/
public List<Map<String, Object>> failedCodeReduce(List<Map<String, Object>> failList, List<Map<String, Object>> codeList){
    if (failList == null) {
        return codeList;
    }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> midMap = new HashMap<String, Object>();
		//中间存储map ， 《code的key值， 数量值 》
		for (Map<String, Object> oneCodeMap : codeList) {
             midMap.put(oneCodeMap.get("keyvalue").toString(), oneCodeMap.get("allnum"));
		}
		// 改变中间存储map对应 code的key值 的数量值
        for (Map<String, Object> oneFailedMap : failList) {
			Integer modified = Integer.parseInt(midMap.get(oneFailedMap.get("keyvalue").toString()).toString()) - Integer.parseInt(oneFailedMap.get("allnum").toString());
			if (modified < 0) {
			    midMap.remove(oneFailedMap.get("keyvalue").toString());
			    continue;
            }
 			midMap.put(oneFailedMap.get("keyvalue").toString(), (Object) modified);
		}
		List<String> primeList = new ArrayList<String>();
		Iterator<Map.Entry<String, Object>> iterator = midMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			primeList.add(entry.getKey() + "!" +entry.getValue().toString());
		}
        Collections.sort(primeList, new sortByNum());
		for (String item : primeList) {
		    HashMap<String, Object> map = new HashMap<String, Object>();
		    map.put("allnum", item.substring(item.indexOf("!")+1, item.length()));
		    map.put("keyvalue", item.substring(0, item.indexOf("!")));
            result.add(map);
			//System.out.println(item);
		}


return result;
}
/**
*@params:
*@Description: 内部比较类，交给collection.sort 函数完成比较功能，从大到小排序。
*@Date: 16:15 2017/12/22
*/
class sortByNum implements Comparator<String> {
	@Override
	public
	int compare(String primeItem1, String primeItem2) {
		return  Integer.parseInt(primeItem2.substring(primeItem2.indexOf("!")+1,primeItem2.length()))
			  - Integer.parseInt(primeItem1.substring(primeItem1.indexOf("!")+1,primeItem1.length()));
	}
}
/**
*@params: 错误条件list， 所用到的表， 任务
*@Description: 得到需要在总数据里删除的额外条件的数据
*@Date: 16:09 2017/12/22
*/
private List<Map<String, Object>> getRectifyDetailMessage(List<String> fConditions, List<String> tables, TaskEntity taskEntity) {
    if (fConditions.size() == 0) {
        return null;
    }
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for (String oneCon: fConditions) {
        String rectifyDetailMessageSql = allPageCodeDetailMessageForMail(oneCon, tables, taskEntity);
        List<Map<String, Object>> rectifyDetailMessage = StartMain.dbUtil.query(rectifyDetailMessageSql);
        result.addAll(rectifyDetailMessage);

    }
    return result;
}

/**
*@params: 总数据表， 需要删除的额外失败条件数据表
*@Description: 删除额外失败条件数据表的处理过程。
*@Date: 16:10 2017/12/22
*/
private List<Map<String, Object>> processDetailMessage(List<Map<String, Object>> detailMessage, List<Map<String, Object>> rectifyDetailMessage) {
    if (rectifyDetailMessage == null) {
        return detailMessage;
    }
    Map<String, Object> combinationMap = getCombinationMessageMap(rectifyDetailMessage);
    Map<String, Object> detailMap = getCombinationMessageMap(detailMessage);
    Iterator <Map.Entry<String, Object>> iterForCombine = combinationMap.entrySet().iterator();
    while(iterForCombine.hasNext()) {
        Map.Entry<String, Object> entry = iterForCombine.next();
        if (detailMap.containsKey(entry.getKey())) {
            detailMap.remove(entry.getKey());
        }
    }
    List <Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    Iterator<Map.Entry<String, Object>> iterForDetail = detailMap.entrySet().iterator();
    while(iterForDetail.hasNext()) {
        Map.Entry<String, Object> entry = iterForDetail.next();
        Map<String, Object> midMap = new HashMap<String, Object>();
        midMap.put("keyvalued04",  entry.getKey().substring(0, entry.getKey().indexOf("!")));
        midMap.put("keyvalued05", entry.getKey().substring(entry.getKey().indexOf("!") +1, entry.getKey().length()));
        midMap.put("allnum", entry.getValue());
        result.add(midMap);
    }
    return result;

}
/**
*@params: 数据表
*@Description: 处理过的数据表的另一种数据结构 以map《d04的值！d05的值，个数总量》表示
*@Date: 16:12 2017/12/22
*/
private Map<String, Object> getCombinationMessageMap(List<Map<String, Object>> originData) {
    Map<String, Object> combinationMap = new HashMap<String, Object>();
    for(Map<String, Object> oneMap : originData) {
        combinationMap.put(oneMap.get("keyvalued04").toString() + "!" + oneMap.get("keyvalued05").toString(), oneMap.get("allnum"));
    }

    return combinationMap;
}


/**
*@params:
*@Description: 如果这世界可以有两个太阳，应该会好很多，一个显得太孤独。
*@Date: 16:13 2017/12/22
*/
public static void main(String args[]) {
		System.out.println("what are you doing");
}

}


