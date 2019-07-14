package com.sohu.tv.cachecloud.client.jedis.stat;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.tv.cachecloud.client.basic.util.ConstUtils;
import com.sohu.tv.cachecloud.client.basic.util.HttpUtils;
import com.sohu.tv.cachecloud.client.basic.util.JsonUtil;
import com.sohu.tv.jedis.stat.constant.ClientReportConstant;
import com.sohu.tv.jedis.stat.data.UsefulDataCollector;
import com.sohu.tv.jedis.stat.enums.ClientExceptionType;
import com.sohu.tv.jedis.stat.model.ClientReportBean;

/**
 * 客户端常�?和上报工具
 * @author leifu
 * @Date 2015年1月16日
 * @Time 下�?�2:50:59
 */
public class ClientReportDataCenter {

    private static Logger logger = LoggerFactory.getLogger(ClientReportDataCenter.class);

    /**
     * 上报
     * 
     * @param ccReportBean
     */
    public static void reportData(ClientReportBean ccReportBean) {

        if (ccReportBean == null) {
            logger.error("ccReportBean is null!");
        }

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(ClientReportConstant.JSON_PARAM, JsonUtil.toJson(ccReportBean));
        parameters.put(ClientReportConstant.CLIENT_VERSION, ConstUtils.CLIENT_VERSION);
        
        try {
            HttpUtils.doPost(ConstUtils.CACHECLOUD_REPORT_URL, parameters);
        } catch (Exception e) {
            logger.error("cachecloud reportData exception: " + e.getMessage());
            UsefulDataCollector.collectException(e, "", System.currentTimeMillis(), ClientExceptionType.CLIENT_EXCEPTION_TYPE);
        }
    }
}
