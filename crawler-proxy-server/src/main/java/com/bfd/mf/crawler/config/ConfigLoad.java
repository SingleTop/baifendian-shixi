package com.bfd.mf.crawler.config;

import com.bfd.crawler.utils.basicutil.configutil.PropertiesUtil;
import com.bfd.crawler.utils.basicutil.json.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 16:42 2018/2/1
 * @Modified_By:
 */
public
class ConfigLoad {
    public static final String KEY_INTERVAL = "interval";
    public static final String KEY_TIME_CONFINED = "time_confined";
    public static final String KEY_SERVER_PORT = "server_port";
    public static final String KEY_SERVER_IP = "server_ip";

    public static final String VALUE_INTERVAL = "INTERVAL";
    public static final String VALUE_TIME_CONFINED = "TIME_CONFINED";
    public static final String VALUE_SERVER_PROT = "SERVER_PORT";
    public static final String VALUE_SERVER_IP = "SERVER_IP";


    public static final String STRING = "String";
    public static final String INTEGER = "Integer";
    public static final String LONG  = "Long";

    public static final Log log = LogFactory.getLog(com.bfd.mf.crawler.config.ConfigLoad.class);
    public static void initConfigs(){
        try{
            log.info("get config begin");
            Map map = new HashMap<>();
            map.put("one", "one");
            log.info(map);
            log.info(JsonUtils.compressMap(map));
            Map<String, String> config = PropertiesUtil.getProperties(ConfigCache.PATH_CONFIG);
            log.info("config " + config);
            initConfig(config, ConfigLoad.KEY_INTERVAL, ConfigLoad.VALUE_INTERVAL, ConfigLoad.LONG, false, null, ConfigCache.class);
            initConfig(config, ConfigLoad.KEY_TIME_CONFINED, ConfigLoad.VALUE_TIME_CONFINED, ConfigLoad.INTEGER, false, null, ConfigCache.class);
            initConfig(config, ConfigLoad.KEY_SERVER_PORT, ConfigLoad.VALUE_SERVER_PROT, ConfigLoad.INTEGER, false, null, ConfigCache.class);
            initConfig(config, ConfigLoad.KEY_SERVER_IP, ConfigLoad.VALUE_SERVER_IP, ConfigLoad.STRING, false, null, ConfigCache.class);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error to Config, system exit ",e );
        }
    }


    public static void initConfig(Map<String, String> configs, String key, String configCacheKey, String type, boolean isDefault,
                                  Object defaultValue,Class c) {
        try {
            if (!configs.containsKey(key)) {
                if (isDefault) {
                    setNewValue(configCacheKey, type, defaultValue,c);
                    log.info("initConfig default key:" + configCacheKey + " defaultValue:" + defaultValue);
                    return;
                } else {
                    log.error("initConfig lost key:" + key);
                    System.exit(0);
                }
            }
            String rawValue = configs.get(key);
            if ("".equals(rawValue)) {
                log.error("crawl-config.properties value is empty, key: " + key);
                System.exit(0);
            }
            Object value = getNewValue(rawValue, type);
            setNewValue(configCacheKey, type, value,c);
            log.info("initConfig " + configCacheKey + " : " + value);
        } catch (Exception e) {
            log.error("error to initConfig " + configCacheKey, e);
            System.exit(0);
        }
    }


    public static void setNewValue(String configCacheKey, String type, Object value,Class c) {
        Field f;
        try {
            f = c.getDeclaredField(configCacheKey);
            f.set(null, value);
        } catch (Exception e) {
            log.error("set new value err key:" + configCacheKey + " type:" + type + " value:" + value, e);
        }
    }

    public static Object getNewValue(String rawValue, String type) {
        Object value = null;
        if (type.equals(com.bfd.mf.crawler.download.config.ConfigCache.INTEGER)) {
            value = Integer.parseInt(rawValue);
        } else if (type.equals(com.bfd.mf.crawler.download.config.ConfigCache.LIST)) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < rawValue.split(",").length; i++) {
                list.add(rawValue.split(",")[i]);
            }
            value = list;
        } else if (type.equals(com.bfd.mf.crawler.download.config.ConfigCache.LONG)) {
            value = Long.parseLong(rawValue);
        } else if (type.equals(com.bfd.mf.crawler.download.config.ConfigCache.BOOLEAN)) {
            if ("false".equals(rawValue)) {
                value = false;
            } else {
                value = true;
            }
        } else if (type.equals(com.bfd.mf.crawler.download.config.ConfigCache.MAP)) {
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < rawValue.split(",").length; i++) {
                String[] one = rawValue.split(",")[i].split(":");
                map.put(one[0], one[1]);
            }
            value = map;
        } else {
            value = rawValue;
        }
        return value;

    }


}
