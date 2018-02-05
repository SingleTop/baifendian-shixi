package com.bfd.mf.monitor.schedule;

import com.bfd.mf.monitor.config.ConfigCache;
import com.bfd.mf.monitor.service.IJudge;
import com.bfd.mf.monitor.entity.TaskEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description: 初始化反射加载预警类，放在map中，反复调用
 * @Date: Created in 16:04 2017/11/1
 * @Modified_By: wang
 */
public
class WorkThread extends Thread{
	private static Map<String, Class> classService = new HashMap<String, Class>();
	private static final Log log = LogFactory.getLog(WorkThread.class);
	private IJudge judgeFunction;
	/**
	*@params: TaskEntity 任务实体类
	*@Description: 每一个工作线程都有一个属于自己的任务实体类，在这里初始化
	*@Date: 19:32 2017/12/1
	*/
	public WorkThread(TaskEntity taskEntity){
		init(taskEntity);
	}
    /**
    *@params:
    *@Description: run 方法，执行judge里的process方法。
    *@Date: 19:46 2017/12/1
    */
    @Override
	public void run(){
		judgeFunction.process();

	}

	private void init(TaskEntity taskEntity){
		Class cls = null;
		synchronized (classService) {
			if (!classService.containsKey(taskEntity.getAlarmType())) {
				String classvice = ConfigCache.CLASSNAMEROOT + taskEntity.getAlarmType();
				try {
					cls = Class.forName(classvice);

					classService.put(taskEntity.getAlarmType(), cls);
				} catch (ClassNotFoundException e) {
					log.error("error to get service class - classNotFound",e);
				} catch (Exception e) {
					log.error("error of getting service class",e);
				}
			} else {
				cls = classService.get(taskEntity.getAlarmType());
			}
		}
		log.info("get service class successfully");
		try {
			judgeFunction = (IJudge) cls.newInstance();
			judgeFunction.setTaskEntity(taskEntity);
		}catch(InstantiationException e){
			log.info("error of getting service instance -InstantiontionE",e);
		}catch (IllegalAccessException e){
			log.info("error of getting service instance -IllegalAccessE",e);
		}


	}
}
