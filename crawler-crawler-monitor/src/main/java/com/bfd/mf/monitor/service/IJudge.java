package com.bfd.mf.monitor.service;

import com.bfd.mf.monitor.entity.TaskEntity;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 16:07 2017/11/1
 * @Modified_By:
 */
public
interface IJudge {
	void process();
	void setTaskEntity(TaskEntity taskEntity);

}
