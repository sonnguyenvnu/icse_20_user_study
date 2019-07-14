package com.sohu.tv.jedis.stat.constant;

import java.text.SimpleDateFormat;

/**
 * 客户端收集上报常�?
 * @author leifu
 * @Date 2015年1月16日
 * @Time 下�?�2:50:59
 */
public class ClientReportConstant {
    
    /**
     * 上报客户端版本
     */
    public static final String CLIENT_VERSION = "clientVersion";
    
    /**
     * 上报json数�?�
     */
    public static final String JSON_PARAM = "json";

    /**
     * 客户端上报数�?�类型
     */
    public static final String CLIENT_DATA_TYPE = "client_data_type";
    
    /**
     * 异常相关�?�数
     */
    //异常类�??
    public static final String EXCEPTION_CLASS = "exception_class";
    //异常消�?�
    public static final String EXCEPTION_MSG = "exception_msg";
    //异常�?�生时间
    public static final String EXCEPTION_HAPPEN_TIME = "exception_happen_time";
    //实例ip:port
    public static final String EXCEPTION_HOST_PORT = "exception_host_port";
    //异常个数
    public static final String EXCEPTION_COUNT = "exception_count";
    //异常类型(ClientExceptionType)
    public static final String EXCEPTION_TYPE = "exception_type";

    /**
     * 耗时相关�?�数
     */
    //耗时分布
    public static final String COST_DISTRI = "cost_distri";
    //调用次数
    public static final String COST_COUNT = "cost_count";
    //命令
    public static final String COST_COMMAND = "cost_command";
    //实例ip:port
    public static final String COST_HOST_PORT = "cost_host_port";
    //耗时90%最大
    public static final String COST_TIME_90_MAX = "cost_time_90_max";
    //耗时99%最大
    public static final String COST_TIME_99_MAX = "cost_time_99_max";
    //耗时最大
    public static final String COST_TIME_100_MAX = "cost_time_100_max";
    //耗时平�?�值
    public static final String COST_TIME_MEAN = "cost_time_mean";
    //耗时中值(50%)
    public static final String COST_TIME_MEDIAN = "cost_time_median";


    /**
     * 值分布相关�?�数
     */
    //值分布区间
    public static final String VALUE_DISTRI = "value_distri";
    //调用�?
    public static final String VALUE_COUNT = "value_count";
    //命令
    public static final String VALUE_COMMAND = "value_command";
    //host:port
    public static final String VALUE_HOST_PORT = "value_host_port";
    
    
    /**
     * 其他信�?�
     */
    //耗时map的大�?
    public static final String COST_MAP_SIZE = "cost_map_size";
    //值区间map的大�?
    public static final String VALUE_MAP_SIZE = "value_map_size";
    //异常map的大�?
    public static final String EXCEPTION_MAP_SIZE = "exception_map_size";
    //收集统计耗时map的大�?
    public static final String COLLECTION_MAP_SIZE = "collection_map_size";

    public static SimpleDateFormat getCollectTimeSDf(){
        return new SimpleDateFormat("yyyyMMddHHmm00");
    }
    
}
