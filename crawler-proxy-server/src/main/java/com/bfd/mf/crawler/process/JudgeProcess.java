package com.bfd.mf.crawler.process;

import com.bfd.mf.crawler.config.ConfigCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 14:20 2018/2/1
 * @Modified_By:
 */
public
class JudgeProcess {
    public static final Log log = LogFactory.getLog(com.bfd.mf.crawler.process.JudgeProcess.class);

    public static  boolean isHasResource(){
        log.info("entry the gainResource" + " ThreadName " + Thread.currentThread().getName() );
        long[] fiveStepInfo = null;
        long now = System.currentTimeMillis();
        log.info("time1" + now + " ThreadName " + Thread.currentThread().getName() );
        try {
            synchronized (ConfigCache.lock) {
                log.info("get write competence" + System.currentTimeMillis() + " ThreadName " + Thread.currentThread().getName());
                if (!isExistsResourceInfo(ConfigCache.resourceInfo)) {
                    if (!initFiveStep()) {
                        log.error("initFiveStep get an error" + " ThreadName " + Thread.currentThread().getName());
                        return false;
                    }
                }
                fiveStepInfo = getFiveStep();
                if (fiveStepInfo == null) {
                    log.error("get an error when get FiveStep for function" + " ThreadName " + Thread.currentThread().getName());
                    return false;
                }
                if (System.currentTimeMillis() < ConfigCache.INTERVAL + fiveStepInfo[(int) fiveStepInfo[ConfigCache.TIME_CONFINED]]) {
                    return false;
                }else {
                    if (!setFiveStep(fiveStepInfo, System.currentTimeMillis())) {
                        log.error("when set fiveStep get error" + " ThreadName " + Thread.currentThread().getName());
                        return false;
                        }
                    log.info("add one resource " + "replace " + fiveStepInfo[(int) fiveStepInfo[ConfigCache.TIME_CONFINED]] + " now " + System.currentTimeMillis() + " ThreadName " + Thread.currentThread().getName());
                    return true;
                }
            }
        }catch (Exception e){
            log.error("get Exception unKnown in gainingProxy " + " ThreadName " + Thread.currentThread().getName() , e);
            //lock.unLock(lockValue);
            return false;
        }
    }

    public static boolean isExistsResourceInfo(String key){
        long begin = System.currentTimeMillis();
        boolean isExist = !ConfigCache.resourceInfo.equals("");
       // log.info("isExist cost " + (System.currentTimeMillis() - begin) + " ThreadName " + Thread.currentThread().getName());
        return  isExist;
    }
    public static boolean initFiveStep(){
        ConfigCache.resourceInfo = ConfigCache.FIVE_STEP_INIT;
        return true;
    }
    public static long[] getFiveStep(){
        long begin = System.currentTimeMillis();
        long[] reLong = new long[ConfigCache.TIME_CONFINED + 1];
        String re = ConfigCache.resourceInfo;
        if(re == null){
            return null;
        }
        String[] reString = String.valueOf(re).split(",");
        for(int i = 0; i < ConfigCache.TIME_CONFINED + 1; i++){
            reLong[i] = Long.parseLong(reString[i]);
        }
        //log.info("getFive Step cost " + (System.currentTimeMillis() - begin) + " ThreadName " + Thread.currentThread().getName());
        return reLong;
    }
    public static boolean setFiveStep( long[] fiveStep, long newstep ){
        long begin = System.currentTimeMillis();
        int point = (int) fiveStep[ConfigCache.TIME_CONFINED];
        fiveStep[point] = newstep;
        point ++ ;
        if(point == ConfigCache.TIME_CONFINED){
            point = 0;
        }
        fiveStep[ConfigCache.TIME_CONFINED] = point;
        StringBuilder fiveStepValue = new StringBuilder();
        for(int i = 0; i < ConfigCache.TIME_CONFINED + 1; i ++){
            fiveStepValue.append(fiveStep[i]);
            fiveStepValue.append(",");
        }
        ConfigCache.resourceInfo = fiveStepValue.toString();
        //log.info("setFive Step Cost " + (System.currentTimeMillis() - begin)  + " ThreadName " + Thread.currentThread().getName());
        return true;
    }

}
