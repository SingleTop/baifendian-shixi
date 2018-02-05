package com.bfd.mf.monitor.service;

import com.bfd.crawler.utils.basicutil.db.DBUtil;
import com.bfd.mf.monitor.entity.TaskEntity;
import com.bfd.mf.monitor.config.ConfigCache;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.impl.dv.xs.YearMonthDV;
import scala.util.parsing.combinator.testing.Str;

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
class JudgeByRate extends AbstractJudge {
    /**
     * 一条任务的描述
     */
    private TaskEntity taskEntity;
    private static DBUtil dbUtil = DBUtil.getInstance(ConfigCache.DB_CONN_NAME);
    private static final Log log = LogFactory.getLog(JudgeByRate.class);

    public JudgeByRate(TaskEntity taskEntity){
        this.taskEntity = taskEntity;
        log.info("detail of task" + taskEntity);
    }
    private List<String> fConditions = new ArrayList<String>();
    private List<String> sConditions = new ArrayList<String>();

    public JudgeByRate(){

    }



    /**
     *@params:
     *@Description: 获得实际占比和总占比的大小比较。
     *@Date:
     */
    @Override
    public
    void process() {
        taskEntity.setNewCondition(subCondition(taskEntity.getCondition(), fConditions, sConditions));
        //System.out.println(taskEntity.getNewCondition());
        //System.out.println(sConditions);
        //System.out.println(fConditions);

        Double realRate=0D;
            String beginTime = taskEntity.getBegingTime();
            String endTime = taskEntity.getEndTime();


            log.info("beginningtime: " + beginTime);
            log.info("endingtime: " + taskEntity.getNextTime());
            List<String> tables = tableUsed(beginTime, taskEntity.getNextTime());
            for (String table : tables) {
                log.info("used table" + table);
            }

        String allCodeSql = conditionSql(SubstringD05(taskEntity.getNewCondition()), tables, endTime, beginTime );
        String pointCodeSql = conditionSql(taskEntity.getNewCondition(), tables, endTime, beginTime);
            Long allCodeNum = conditionNum(dbUtil.query(allCodeSql));
            Long pointCodeNum = conditionNum(dbUtil.query(pointCodeSql));

            //System.out.println(allCodeNum);
            //System.out.println(pointCodeNum);

            Long[] data = dataModify(pointCodeNum, allCodeNum, fConditions, sConditions, tables, endTime, beginTime);
            allCodeNum = data[1];
            pointCodeNum = data[0];
            if (pointCodeNum < 0L) {
                pointCodeNum = 0L;
            }
            //System.out.println(allCodeNum);
            //System.out.println(pointCodeNum);

            if(allCodeNum!=0&&pointCodeNum!=0) {
                 realRate = ((double) pointCodeNum / (double)allCodeNum);
            }else{
                sendSqlErrorMail(beginTime, pointCodeNum, allCodeNum, taskEntity, "查询得到数量为null，出错",
                        "sql point: " + pointCodeSql + "\nsql all:  " + allCodeSql);
                return;
            }
            log.info("JudgeByRate: realRate:" + realRate +"%");
            //System.out.println(realRate + "  " + taskEntity.getThreshold());
            if(realRate > taskEntity.getThreshold()){

                log.warn("warning: " + "successnum is: "+pointCodeNum + " failednum is: "
                        + allCodeNum+" the all: " + (pointCodeNum+allCodeNum) + " but the expected rate is: "
                        + taskEntity.getThreshold() + "% but the real rate is " + realRate +"%");
                sendRateWarningMail(tables, realRate, pointCodeNum, taskEntity, fConditions);
            }else{
                log.info("good situation info: " + "successnum is: "+pointCodeNum + " failednum is: "
                        + allCodeNum+" the all: " + (pointCodeNum+allCodeNum) + " but the expected rate is: "
                        + taskEntity.getThreshold() + "% and the real rate is " + realRate +"%");
            }
            log.info(" rate is over\n");



    }

    @Override
    public void setTaskEntity(TaskEntity taskEntity){
        this.taskEntity=taskEntity;
        log.info("detail of task" + taskEntity);

    }
  /**
  *@params: 条件字符串， 所需要使用的tables， 结束时间， 开始时间。
  *@Description: 获得查询总量的sql， 条件自己传入。
  *@Date: 16:18 2017/12/22
  */
    private String conditionSql (String fCon, List<String> tables, String endTime, String beginTime ) {
        StringBuilder fsql = new StringBuilder(ConfigCache.NUM_FIR_SQL);
        for (String table : tables) {
            String onePointTable = ConfigCache.NUM_PRE_SQL + table
                + ConfigCache.NUM_MID1_SQL + fCon
                + ConfigCache.NUM_MID2_SQL + "'" + beginTime + "' and '"
                + endTime + "'";
            fsql.append(onePointTable);
            fsql.append("   union ");
        }
        fsql.delete(fsql.length() - 6, fsql.length());
        fsql.append(ConfigCache.NUM_LAST_SQL);
        //System.out.println(fsql.toString());
        return fsql.toString();
    }
/**
*@params: 结果列表， 应该只有一列，而且map里只有一个元数据
*@Description: 安全获得数据，以后应该会复用。
*@Date: 16:20 2017/12/22
*/
    private Long conditionNum (List<Map<String, Object>> result){
        Long getNum = 0L;
        String getNumStr=null;
        try {
            getNumStr = result.get(0).get("allnum").toString();
        }catch (Exception e) {

        }
        if(getNumStr == null) {
            return 0L;
        } else {
            try {
                getNum = Long.parseLong(getNumStr);

            }catch (Exception e) {

            }
        }
        return getNum;
        }
/**
*@params: 所指定d05类型的总量， 没有指定d05类型的总量， 失败的条件列表，成功的条件列表， 所用到的表，  开始时间，结束时间。
*@Description:
*@Date: 16:22 2017/12/22
*/
        private  Long[] dataModify(Long point, Long all, List<String> fList, List<String> sList, List<String> tables, String endTime, String beginTime) {
        for (int i = fList.size()-1 ; i>=0 ; i--) {
            long get = conditionNum(dbUtil.query(conditionSql(fList.get(i), tables, endTime, beginTime)));
            all -= get;
            point -= get;
        }
        for (int i = sList.size()-1 ; i>=0 ; i--) {
                long get = conditionNum(dbUtil.query(conditionSql(sList.get(i), tables, endTime, beginTime)));
                all -= get;
            }
        Long[] longs = new Long[2];
        longs[0] = point;
        longs[1] = all;
        return longs;
        }
}
