package com.bfd.mf.monitor.service;

import com.bfd.crawler.utils.basicutil.db.DBUtil;
import com.bfd.mf.monitor.config.ConfigCache;
import com.bfd.mf.monitor.entity.TaskEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 16:10 2017/11/1
 * @Modified_By:
 */
public
class JudgeByNum extends AbstractJudge {
	/**
	 * 一条任务的描述
	 */
	private TaskEntity taskEntity;
	private static DBUtil dbUtil = DBUtil.getInstance(ConfigCache.DB_CONN_NAME);
	private static final Log log = LogFactory.getLog(JudgeByNum.class);
	SimpleDateFormat yMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public
	JudgeByNum(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
		log.info("detail of task" + taskEntity);
	}

	public
	JudgeByNum() {
	}

	/**
	 * @params:
	 * @Description: 获得实际数量和总数量的比较。
	 * @Date:
	 */
	@Override
	public
	void  process() {
		Integer phrase = taskEntity.getStepSize();
		SimpleDateFormat yMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar beginCal = Calendar.getInstance();
		try {
			beginCal.setTime(yMDHMS.parse(taskEntity.getNextTime()));
		}catch (ParseException e) {
			log.error("judgebynum bigin time parse occur a error", e);
			return;
		}
			long beginMillis = beginCal.getTimeInMillis();
			long decreaseMillis = (beginMillis - (long) taskEntity.getStepSize() * 60000L);
			log.info("millis: " + beginMillis + "  " + decreaseMillis + " stepSize: " + taskEntity.getStepSize());
			Date backDate = new Date(decreaseMillis);
			String strBackDate = yMDHMS.format(backDate);
			log.info("beginningtime: " + strBackDate);
			log.info("endingtime: " + taskEntity.getNextTime());
			List<String> tables = tableUsed(strBackDate, taskEntity.getNextTime());
			for (String table : tables) {
				log.info("used table" + table);
			}
			int length = tables.size();
			String searchSql = ConfigCache.NUM_FIR_SQL;

			for (String table : tables) {
				String oneTable = ConfigCache.NUM_PRE_SQL + table + ConfigCache.NUM_MID1_SQL + taskEntity.getCondition() + ConfigCache.NUM_MID2_SQL + "'" + strBackDate + "' and '" + yMDHMS.format(beginCal.getTime()) + "'";
				searchSql = searchSql + oneTable + "   union ";
			}
			searchSql = searchSql.substring(0, searchSql.length() - 6) + ConfigCache.NUM_LAST_SQL;
			log.info("searchSql of JudgeByNum: " + searchSql);
			List<Map<String, Object>> resultList = dbUtil.query(searchSql);
			String pointStr = null;
			Long pointNum = null;

				if(resultList.get(0).get("allnum") != null) {
					pointStr = resultList.get(0).get("allnum").toString();
				}else{
					pointStr = "0";
				}
			if(pointStr == null){
				sendSqlErrorMail(strBackDate, 0L, taskEntity.getThreshold(), taskEntity, "查询得到数量为null", searchSql );
				return ;
			}else {
				try{
					pointNum = Long.parseLong(pointStr);
				}catch (Exception e){
					log.error("judgeByNum error: pointStr get a error when parsing to Long", e);
					sendSqlErrorMail(strBackDate, 0L, taskEntity.getThreshold(), taskEntity, "when parse pintCode to long get error", searchSql);
					return;
				}
			}
		if (pointNum < taskEntity.getThreshold()){
			log.warn("JudgeByNum warning: the threshold value is " + taskEntity.getThreshold() + " but the real value is" + resultList.get(0).get("allnum"));
			sendNumWarningMail(taskEntity, pointNum, strBackDate);
		}else {
			log.info("good situation " + " info: the threshold value is " + taskEntity.getThreshold() + " and the real value is" + resultList.get(0).get("allnum"));
		}
		log.info(" num judging is over ");

	}


	@Override
	public
	void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
		log.info("detail of task" + taskEntity);

	}

}