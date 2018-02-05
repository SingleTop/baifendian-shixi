package com.bfd.mf.monitor.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description: 预警任务的实体类，各种类型的转化等等。
 * @Date: Created in 14:06 2017/11/2
 * @Modified_By: wang
 */
public
class TaskEntity {
	private final Integer id;
	private final String condition ;
	private final Integer interval;
	private final String name;
	private final Integer stepSize;
	private final String nextTime;
	private final Double threshold;
	private final String alarmType;
	private final String[] receivers;
	private static final Log log = LogFactory.getLog(TaskEntity.class);
	private String newCondition;
	private final String begingTime;
	private final String endTime;

	public
	String getNewCondition() {
		return newCondition;
	}

	public
	void setNewCondition(String newCondition) {
		this.newCondition = newCondition;
	}

	public TaskEntity(Map<String, Object>temp) {
		this.id=(Integer)temp.get("id");
		this.condition = (String)temp.get("condition");
		this.interval = (Integer)temp.get("interval");
		this.name = (String)temp.get("name");
		this.stepSize = (Integer)temp.get("step_size");
		this.nextTime = temp.get("next_time").toString();
		this.threshold = (Double)temp.get("threshold");
		this.alarmType = (String)temp.get("alarm_type");
		this.receivers = parseReceivers(temp.get("receivers").toString());
		this.begingTime = parseBeginTime(nextTime, stepSize);
		this.endTime = parseEndTime(nextTime);
		log.info("init ok");
	}

	public String parseBeginTime(String nextTime, int stepSize){
		SimpleDateFormat yMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar endCal = Calendar.getInstance();
		try {
			endCal.setTime(yMDHMS.parse(nextTime));
		} catch (ParseException e) {
			log.warn("error of parsing", e);
		}
		long beginMillis = endCal.getTimeInMillis();
		long decreaseMillis = (beginMillis - (long) stepSize * 60000L);
		log.info("millis" + beginMillis + "  " + decreaseMillis + "phase" + stepSize);
		Date backDate = new Date(decreaseMillis);
		String beginTime = yMDHMS.format(backDate);
		return beginTime;

	}
	public String parseEndTime(String nextTime) {
		SimpleDateFormat yMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar endCal = Calendar.getInstance();
		try {
			endCal.setTime(yMDHMS.parse(nextTime));
		} catch (ParseException e) {
			log.warn("error of parsing", e);
		}
		String endTime = yMDHMS.format(endCal.getTime());
		return endTime;
	}

	public
	String getBegingTime() {
		return begingTime;
	}

	public
	String getEndTime() {
		return endTime;
	}

	public
	String[] getReceivers() {
		return receivers;
	}

	public
	String getCondition() {
		return condition;
	}

	public
	Integer getInterval() {
		return interval;
	}

	public
	String getName() {
		return name;
	}

	public
	Integer getStepSize() {
		return stepSize;
	}

	public
	String getNextTime() {
		return nextTime;
	}

	public
	Double getThreshold() {
		return threshold;
	}

	public
	String getAlarmType() {
		return alarmType;
	}

	public
	Integer getId() {
		return id;
	}

	@Override
	public
	String toString() {
		return "TaskEntity{" + "id=" + id + ", condition='" + condition + '\'' + ", interval=" + interval + ", name='" + name + '\'' + ", stepSize=" + stepSize + ", nextTime='" + nextTime + '\'' + ", threshold=" + threshold + ", alarmType='" + alarmType + '\'' + ", receivers=" + Arrays.toString(receivers) + '}';
	}
	/**
	*@params:  receivers 收取信件人的地址 String类型，以‘，’分割
	*@Description: 把String 分割转化成String【】类型，
	*@Date: 19:50 2017/12/1
	*/
	private  String[] parseReceivers(String receivers){
		receivers.replace("，", ",");
		if(receivers.charAt(receivers.length()-1) ==','){
			receivers = receivers.substring(0,receivers.length()-1);
		}
		String[] getReceviers = receivers.trim().split(",");
		log.info("the number of receivers" + getReceviers.length);
		for(int i = 0 ; i < getReceviers.length ;i ++){
			getReceviers[i]=getReceviers[i].trim();
		}
		log.info("receivers" + Arrays.toString(getReceviers));

		return getReceviers;

	}
}
