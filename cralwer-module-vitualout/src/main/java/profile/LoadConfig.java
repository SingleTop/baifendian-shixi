package profile;

import com.bfd.crawler.utils.basicutil.configutil.PropertiesUtil;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
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
 * @Date: Created in 11:02 2018/2/1
 * @Modified_By:
 */
public
class LoadConfig {
    public static final Log log = LogFactory.getLog(profile.LoadConfig.class);
    public static void initConfigs(){
        try{
            log.info("get config begin");
            Map<String, String> config = PropertiesUtil.getProperties(ConfigCache.PATH_CONFIG);
            log.info("config " + config);
            initConfig(config, NameSpace.KEY_START_REPU_TIME, NameSpace.VALUE_START_REPU_TIME, NameSpace.STRING, false, null, ConfigCache.class);
            initConfig(config, NameSpace.KEY_END_REPU_TIME, NameSpace.VALUE_END_REPU_TIME, NameSpace.STRING, false, null, ConfigCache.class);
            initConfig(config, NameSpace.KEY_IS_ONLY_REPU, NameSpace.VALUE_IS_ONLY_REPU, NameSpace.STRING, true, "no", ConfigCache.class);


        }catch (Throwable e){
            log.error("error to init Config , syste exit, e");
            System.exit(0);
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
