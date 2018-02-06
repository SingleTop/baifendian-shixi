package start;

import com.bfd.crawler.utils.basicutil.FileUtil;
import com.bfd.crawler.utils.basicutil.configutil.PropertiesUtil;
import com.bfd.crawler.utils.basicutil.db.DBUtil;
import down.UnSerialise;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import parse.ParseWaimai;
import profile.ConfigCache;
import profile.LoadConfig;
import profile.SqlList;
import util.DownUtil;
import util.MyDbUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 14:04 2017/12/21
 * @Modified_By:
 */
public
class StartMain {


    static {
        PropertyConfigurator.configureAndWatch(ConfigCache.PATH_LOG4J);
        ConfigCache.IP = FileUtil.readFromFile2StrSplitByLine(ConfigCache.PATH_LOACLIP);
        DBUtil.init(ConfigCache.PATH_DB, false);

    }

//
//    static{
//        PropertyConfigurator.configureAndWatch(ConfigCache.MY_LOG_PROPERTIE);
//        ConfigCache.IP.add("192.168.174.48");
//        DBUtil.init(ConfigCache.MY_DB_PROPERTIES, false);
//
//
//    }

    public static final Log log = LogFactory.getLog(start.StartMain.class);
    public static List<Map<String, Object>> taskSlot;
    public static List<Thread> threadList = new LinkedList<>();
    public static boolean loop = true;

    public static void main(String[] args){
        List<Map<String, Object>> alltask =  MyDbUtil.query(SqlList.GET_ALL_SHOP_URL);
        log.info(alltask);
        LoadConfig.initConfigs();
        log.info("start " + ConfigCache.START_TIME);
        log.info("end " + ConfigCache.END_TIME);
        log.info("is only repu " + ConfigCache.IS_ONLY_REPU);

        init();
        int times = 0;
        for(Map<String, Object> oneTesk : alltask){
//            if(times > 5) {
//                break;
//            }
            int point=0;
            while (loop){
                log.info(point + " point");
            if(taskSlot.get(point) == null){
                taskSlot.set(point, oneTesk);
                break;
            }
                point ++;
            if(point >= ConfigCache.IP.size()){
                point = 0;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }

        times ++;
        }

    }


  public static void  init(){
      int size = ConfigCache.IP.size();
      taskSlot = new ArrayList<>(size);
      log.info("size" + size);
      for(int i = 0; i < size; i ++){
          taskSlot.add(null);
          WorkThread workThread = new WorkThread(ConfigCache.IP.get(i), i, taskSlot);
          Thread thread = new Thread(workThread);
          threadList.add(thread);
          thread.start();
      }
    }

}
