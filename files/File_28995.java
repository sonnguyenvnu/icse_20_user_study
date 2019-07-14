package com.sohu.tv.cachecloud.client.jedis.stat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.tv.cachecloud.client.basic.util.DateUtils;
import com.sohu.tv.cachecloud.client.basic.util.NamedThreadFactory;
import com.sohu.tv.cachecloud.client.basic.util.NetUtils;
import com.sohu.tv.jedis.stat.constant.ClientReportConstant;
import com.sohu.tv.jedis.stat.data.UsefulDataCollector;
import com.sohu.tv.jedis.stat.enums.ClientCollectDataTypeEnum;
import com.sohu.tv.jedis.stat.enums.ClientExceptionType;
import com.sohu.tv.jedis.stat.model.ClientReportBean;
import com.sohu.tv.jedis.stat.model.CostTimeDetailStatKey;
import com.sohu.tv.jedis.stat.model.CostTimeDetailStatModel;
import com.sohu.tv.jedis.stat.model.ExceptionModel;
import com.sohu.tv.jedis.stat.model.ValueLengthModel;
import com.sohu.tv.jedis.stat.utils.AtomicLongMap;
import com.sohu.tv.jedis.stat.utils.NumberUtil;

/**
 * jedis数�?�收集上报任务执行器
 * 
 * @author leifu
 * @Date 2015年1月14日
 * @Time 上�?�11:45:09
 */
public class ClientDataCollectReportExecutor {

    private final Logger logger = LoggerFactory.getLogger(ClientDataCollectReportExecutor.class);

    /**
     * 客户端ip(实际使用了web中统计的ip作为真正的客户端ip, 这个作为备用)
     */
    private static String clientIp = NetUtils.getLocalHost();

    /**
     * 数�?�收集上报
     */
    private final ScheduledExecutorService jedisDataCollectReportScheduledExecutor = Executors.newScheduledThreadPool(3,
            new NamedThreadFactory("jedisDataCollectReportScheduledExecutor", true));
    private ScheduledFuture<?> jedisDataCollectReportScheduleFuture;
    private final int delay = 5;
    private final int fixCycle = 60;

    private volatile static ClientDataCollectReportExecutor jedisDataCollectAndReportExecutor;

    private ClientDataCollectReportExecutor() {
        init();
    }

    public static ClientDataCollectReportExecutor getInstance() {
        if (jedisDataCollectAndReportExecutor == null) {
            synchronized (ClientDataCollectReportExecutor.class) {
                if (jedisDataCollectAndReportExecutor == null) {
                    jedisDataCollectAndReportExecutor = new ClientDataCollectReportExecutor();
                }
            }
        }
        return jedisDataCollectAndReportExecutor;
    }

    /**
     * �?始化
     */
    public void init() {
        Thread clientDataCollectReportThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String currentMinuteStamp = ClientReportConstant.getCollectTimeSDf().format(new Date());
                    collectReportAllData(currentMinuteStamp);
                } catch (Exception e) {
                    UsefulDataCollector.collectException(e, "", System.currentTimeMillis(), ClientExceptionType.CLIENT_EXCEPTION_TYPE);
                    logger.error("ClientDataCollectReport thread message is" + e.getMessage(), e);
                }
            }
        });
        clientDataCollectReportThread.setDaemon(true);

        // �?�动定时任务
        jedisDataCollectReportScheduleFuture = jedisDataCollectReportScheduledExecutor.scheduleWithFixedDelay(
                clientDataCollectReportThread, delay, fixCycle, TimeUnit.SECONDS);

    }
    
    /**
     * 收集上报数�?�
     * @param currentMinuteStamp
     */
    private void collectReportAllData(String currentMinuteStamp) {
    	//1. 获�?�上一分钟的所有数�?�
        String lastMinute = getLastMinute(currentMinuteStamp);
        if (lastMinute == null || "".equals(lastMinute.trim())) {
        	return;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.addAll(collectReportCostTimeData(lastMinute));
        list.addAll(collectReportValueDistriData(lastMinute));
        list.addAll(collectReportExceptionData(lastMinute));

        //上报统计数�?�,观察是�?�存在内存泄露的情况
        Map<String, Object> otherInfo = new HashMap<String, Object>(4, 1);
        otherInfo.put(ClientReportConstant.COST_MAP_SIZE, UsefulDataCollector.getDataCostTimeMapAll().size());
        otherInfo.put(ClientReportConstant.VALUE_MAP_SIZE, UsefulDataCollector.getDataValueLengthDistributeMapAll().size());
        otherInfo.put(ClientReportConstant.EXCEPTION_MAP_SIZE, UsefulDataCollector.getDataExceptionMapAll().size());
        otherInfo.put(ClientReportConstant.COLLECTION_MAP_SIZE, UsefulDataCollector.getCollectionCostTimeMapAll().size());
        
		//2. 上报数�?�
		if (!list.isEmpty()) {
	        ClientReportBean ccReportBean = new ClientReportBean(clientIp, NumberUtil.toLong(lastMinute), System.currentTimeMillis(), list, otherInfo);
	        ClientReportDataCenter.reportData(ccReportBean);
		}
	}

    /**
     * 收集耗时
     * 
     * @param lastMinute
     */
    private List<Map<String, Object>> collectReportCostTimeData(String lastMinute) {
        try {
        	//1. 收集数�?�
            Map<CostTimeDetailStatKey, AtomicLongMap<Integer>> map = UsefulDataCollector.getCostTimeLastMinute(lastMinute);
            if (map == null || map.isEmpty()) {
                return Collections.emptyList();
            }

            // 2. 组装数�?�
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (Entry<CostTimeDetailStatKey, AtomicLongMap<Integer>> entry : map.entrySet()) {
                CostTimeDetailStatKey costTimeDetailStatKey = entry.getKey();
                AtomicLongMap<Integer> statMap = entry.getValue();
                CostTimeDetailStatModel model = UsefulDataCollector.generateCostTimeDetailStatKey(statMap);
                
                Map<String, Object> tempMap = new HashMap<String, Object>();
                tempMap.put(ClientReportConstant.COST_COUNT, model.getTotalCount());
                tempMap.put(ClientReportConstant.COST_COMMAND, costTimeDetailStatKey.getCommand());
                tempMap.put(ClientReportConstant.COST_HOST_PORT, costTimeDetailStatKey.getHostPort());
                tempMap.put(ClientReportConstant.COST_TIME_90_MAX, model.getNinetyPercentMax());
                tempMap.put(ClientReportConstant.COST_TIME_99_MAX, model.getNinetyNinePercentMax());
                tempMap.put(ClientReportConstant.COST_TIME_100_MAX, model.getHundredMax());
                tempMap.put(ClientReportConstant.COST_TIME_MEAN, model.getMean());
                tempMap.put(ClientReportConstant.COST_TIME_MEDIAN, model.getMedian());
                tempMap.put(ClientReportConstant.CLIENT_DATA_TYPE, ClientCollectDataTypeEnum.COST_TIME_DISTRI_TYPE.getValue());
                list.add(tempMap);
            }
            return list;
        } catch (Exception e) {
            UsefulDataCollector.collectException(e, "", System.currentTimeMillis(), ClientExceptionType.CLIENT_EXCEPTION_TYPE);
            logger.error("collectReportCostTimeData:" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * 收集异常
     * @param lastMinute
     */
    private List<Map<String, Object>> collectReportExceptionData(String lastMinute) {
        try {
            // 1. �?��?�当�?时间�?一分钟的的数�?�
            Map<ExceptionModel, Long> map = UsefulDataCollector.getExceptionLastMinute(lastMinute);
            if (map == null || map.isEmpty()) {
                return Collections.emptyList();
            }

            // 2. 组装数�?�
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> tempMap = null;
            for (Entry<ExceptionModel, Long> entry : map.entrySet()){
            	ExceptionModel exceptionModel = entry.getKey();
            	Long exceptionCount = entry.getValue();
                tempMap = new HashMap<String, Object>();
                tempMap.put(ClientReportConstant.EXCEPTION_CLASS, exceptionModel.getExceptionClass());
                tempMap.put(ClientReportConstant.EXCEPTION_MSG, "");
                tempMap.put(ClientReportConstant.EXCEPTION_HAPPEN_TIME, System.currentTimeMillis());
                tempMap.put(ClientReportConstant.EXCEPTION_HOST_PORT, exceptionModel.getHostPort());
                tempMap.put(ClientReportConstant.EXCEPTION_COUNT, exceptionCount);
                tempMap.put(ClientReportConstant.EXCEPTION_TYPE, exceptionModel.getClientExceptionType().getType());
                tempMap.put(ClientReportConstant.CLIENT_DATA_TYPE, ClientCollectDataTypeEnum.EXCEPTION_TYPE.getValue());
                list.add(tempMap);
            }
            return list;
        } catch (Exception e) {
            UsefulDataCollector.collectException(e, "", System.currentTimeMillis(), ClientExceptionType.CLIENT_EXCEPTION_TYPE);
            logger.error("collectReportExceptionData:" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * 收集值分布
     * 
     * @param lastMinute
     */
    private List<Map<String, Object>> collectReportValueDistriData(String lastMinute) {
        try {
            // 1. �?��?�当�?时间�?一分钟的的数�?�
            Map<ValueLengthModel, Long> jedisValueLengthMap = UsefulDataCollector.getValueLengthLastMinute(lastMinute);
            if (jedisValueLengthMap == null || jedisValueLengthMap.isEmpty()) {
                return Collections.emptyList();
            }

            // 2.解�?拼接数�?�
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (Entry<ValueLengthModel, Long> entry : jedisValueLengthMap.entrySet()) {
            	ValueLengthModel model = entry.getKey();
                Long count = entry.getValue();
                Map<String, Object> tempMap = new HashMap<String, Object>();
                tempMap.put(ClientReportConstant.VALUE_DISTRI, model.getRedisValueSizeEnum().getValue());
                tempMap.put(ClientReportConstant.VALUE_COUNT, count);
                tempMap.put(ClientReportConstant.VALUE_COMMAND, model.getCommand());
                tempMap.put(ClientReportConstant.VALUE_HOST_PORT, model.getHostPort());
                tempMap.put(ClientReportConstant.CLIENT_DATA_TYPE, ClientCollectDataTypeEnum.VALUE_LENGTH_DISTRI_TYPE.getValue());
                list.add(tempMap);
            }
            return list;
        } catch (Exception e) {
            UsefulDataCollector.collectException(e, "", System.currentTimeMillis(), ClientExceptionType.CLIENT_EXCEPTION_TYPE);
            logger.error("collectReportValueDistriData:" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    
    /**
     * 获�?�上一分钟的字符串
     * 
     * @param currentMinuteStamp
     * @return
     */
    private String getLastMinute(String currentMinuteStamp) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date currentDate = sdf.parse(currentMinuteStamp);
            Date lastMinute = DateUtils.addMinutes(currentDate, -1);
            return sdf.format(lastMinute);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 关闭
     */
    public void close() {
    	//TODO�?�以加个JVM钩�?
        try {
            jedisDataCollectReportScheduleFuture.cancel(true);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
    }

    public static String getClientIp() {
        return clientIp;
    }

}
