package com.bfd.mf.monitor;

import com.bfd.crawler.utils.basicutil.db.DBUtil;
import com.bfd.mf.monitor.config.ConfigCache;
import com.bfd.mf.monitor.entity.TaskEntity;
import com.bfd.mf.monitor.schedule.WorkThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import sun.security.krb5.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 21:55 2017/11/1
 * @Modified_By:
 */
public
class StartMain {
//	static{
//		DBUtil.init(ConfigCache.MY_DB_PROPERTIES, false);
//		PropertyConfigurator.configureAndWatch(ConfigCache.MY_LOG_PROPERTIES);
//
//	}

	static{
		DBUtil.init(ConfigCache.ONLINE_DB_PROPERTIES, false);
		PropertyConfigurator.configureAndWatch(ConfigCache.ONLINE_LOG_PROPERTIES);

	}




	private static final Log log = LogFactory.getLog(StartMain.class);
	public  static DBUtil dbUtil = DBUtil.getInstance(ConfigCache.DB_CONN_NAME);
	private  static List<Map<String, Object>> result;
	private static SimpleDateFormat yMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	*@params:
	*@Description: 初始化另一张表里的关于code描述内容的信息。存在一个全局的HashMap中，load interpretation of code
	*@Date: 10:32 2017/12/4
	*/
static {
    List<Map<String, Object>> list = dbUtil.query(ConfigCache.CODE_INTERPRET);
    for(Map<String, Object> oneIterm : list) {
        ConfigCache.codeBack.put(oneIterm.get("code").toString(), oneIterm.get("interpret").toString());
    }
}

	public static void main(String[] args){
		log.info("Main function  start");
		System.out.println("start");
		start();
	}
	/**
	 *@params:
	 *@Description: 开始页面，对任务表每五秒做一次巡回检查，如果到达下一次执行时间，就将任务提出，开始执行任务内容（主要是检测）
	 *@Date:
	 */

	private static  void start(){
		while (true){
			try {
			Calendar calCurrent = Calendar.getInstance();
			SimpleDateFormat yMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = yMDHMS.format(calCurrent.getTime());
			StringBuilder taskSql = new StringBuilder(ConfigCache.TASK_PRE_SQL).append(currentTime).append( ConfigCache.TASK_AFTER_SQL);
			log.info("sql of searching  task" + taskSql);
			result = dbUtil.query(taskSql.toString());
			for(Map<String, Object> temp : result){
				//System.out.println("get one task" + temp.get("name"));
				log.info("get one task" + temp.get("name"));
				TaskEntity taskEntity = new TaskEntity(temp);
				updatTime(getNewTime(taskEntity), taskEntity);
				WorkThread workThread = new WorkThread(taskEntity);
				workThread.start();

			}
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.warn("error of starting this task", e);
			}catch(Exception e){
				e.printStackTrace();
				log.warn("error of starting this task ", e);
			}
		}

	}
/**
*@params:
*@Description: 根据页面的间息（interval）时间计算该任务下一次执行的时间
*@Date: 10:28 2017/12/4
*/
	private static String getNewTime(TaskEntity taskEntity){
		Calendar beginCal = Calendar.getInstance();
		try {
			beginCal.setTime(yMDHMS.parse(taskEntity.getNextTime()));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		long beginMillis = beginCal.getTimeInMillis();
		long decreaseMillis = (beginMillis + (long) taskEntity.getInterval() * 60000L);
		Date newNextDate = new Date(decreaseMillis);
		String newNextTime = yMDHMS.format(newNextDate);
		return newNextTime;
	}
	private static void updatTime (String newNextTime, TaskEntity taskEntity){
        StringBuilder updateSql = new StringBuilder(ConfigCache.UPDATE_TASK_PRE_SQL + newNextTime + ConfigCache.UPDATE_TASK_AFTER_SQL + taskEntity.getId());
		dbUtil.update(updateSql.toString());
		log.info("the update sql " + updateSql);
	}

}
