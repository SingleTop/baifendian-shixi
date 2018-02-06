package com.bfd.mf.crawler.config;

/**
 * @Author: chengwei.wang
 * @Description:
 * @Date: Created in 16:00 2018/1/31
 * @Modified_By:
 */
public
class ConfigCache {
    public static final String lock = "lock";
    public static String resourceInfo = "";
    public static String FIVE_STEP_INIT = "0,0,0,0,0,0,0,0";
    public static Long INTERVAL = 0L;
    public static Integer TIME_CONFINED = 0;

    public static Integer SERVER_PORT = 0;
    public static String SERVER_IP = "";

    public static String PROXY_IP ="p5.t.16yun.cn";
    public static String PROXY_PORT = "6445";

    public static String PATH_ROOT = "../etc/";

    public static String PATH_LOG4J = PATH_ROOT + "proxyServerLog.properties";
    public static String PATH_CONFIG = PATH_ROOT + "proxyServerConfig.properties";
   // public static String PROXY




}
