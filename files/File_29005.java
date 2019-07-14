package com.sohu.tv.jedis.stat.data;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.tv.jedis.stat.constant.ClientReportConstant;
import com.sohu.tv.jedis.stat.enums.ClientExceptionType;
import com.sohu.tv.jedis.stat.enums.ValueSizeDistriEnum;
import com.sohu.tv.jedis.stat.model.CostTimeDetailStatKey;
import com.sohu.tv.jedis.stat.model.CostTimeDetailStatModel;
import com.sohu.tv.jedis.stat.model.ExceptionModel;
import com.sohu.tv.jedis.stat.model.UsefulDataModel;
import com.sohu.tv.jedis.stat.model.ValueLengthModel;
import com.sohu.tv.jedis.stat.utils.AtomicLongMap;
import com.sohu.tv.jedis.stat.utils.DateUtils;
import com.sohu.tv.jedis.stat.utils.NamedThreadFactory;
import com.sohu.tv.jedis.stat.utils.NumberUtil;

/**
 * jedis有价值数�?�收集器(耗时,值分布,异常等)
 * 
 * @author leifu
 * @Date 2015年1月13日
 * @Time 下�?�5:42:31
 */
public class UsefulDataCollector {

    private final static Logger logger = LoggerFactory.getLogger(UsefulDataCollector.class);

    /**
     * 耗时详细统计
     */
    private static ConcurrentHashMap<CostTimeDetailStatKey, AtomicLongMap<Integer>> DATA_COST_TIME_MAP_ALL = new ConcurrentHashMap<CostTimeDetailStatKey, AtomicLongMap<Integer>>();

    /**
     * 值分布统计
     */
    private static ConcurrentHashMap<String, AtomicLongMap<ValueLengthModel>> DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL = new ConcurrentHashMap<String, AtomicLongMap<ValueLengthModel>>();

    /**
     * 异常详细统计
     */
    private static ConcurrentHashMap<String, AtomicLongMap<ExceptionModel>> DATA_EXCEPTION_MAP_ALL = new ConcurrentHashMap<String, AtomicLongMap<ExceptionModel>>();

    /**
     * 收集耗时统计(统计收集数�?�本身对于速度的影�?)
     */
    private static ConcurrentHashMap<String, AtomicLongMap<Long>> COLLECTION_COST_TIME_MAP_ALL = new ConcurrentHashMap<String, AtomicLongMap<Long>>();

    /**
     * 数�?�定时清�?�
     */
    private final static ScheduledExecutorService jedisDataCleanScheduledExecutor = Executors.newScheduledThreadPool(2,
            new NamedThreadFactory("jedisCleanScheduledExecutor", true));
    private static ScheduledFuture<?> jedisDataCleanScheduleFuture;
    private final static int delay = 10;
    private final static int fixCycle = 60;

    static {
        init();
    }

    public static void init() {
        Thread jedisCleanDataThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 清�?�2分钟�?数�?�
                    Date date = DateUtils.addMinutes(new Date(), -2);
                    String dateSdf = ClientReportConstant.getCollectTimeSDf().format(date);
                    clearCostTime(dateSdf);
                    clearException(dateSdf);
                    clearValueLength(dateSdf);
                    clearCollectionCost(dateSdf);
                } catch (Exception e) {
                    logger.error("jedisCleanData thread message is" + e.getMessage(), e);
                }
            }
        });
        jedisCleanDataThread.setDaemon(true);

        // �?�动定时任务
        jedisDataCleanScheduleFuture = jedisDataCleanScheduledExecutor
                .scheduleWithFixedDelay(jedisCleanDataThread, delay, fixCycle,
                        TimeUnit.SECONDS);
    }

    /**
     * 关闭
     */
    public static void close() {
        try {
            jedisDataCleanScheduleFuture.cancel(true);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
    }

    /**
     * 收集耗时和值分布
     * 
     * @param costModel
     */
    public static void collectCostAndValueDistribute(UsefulDataModel costModel) {
        Long start = System.currentTimeMillis();
        try {
            // 基础数�?�
            String currentMinute = ClientReportConstant.getCollectTimeSDf().format(new Date());
            int cost = (int) costModel.getCost();
            String command = costModel.getCommand();
            String hostPort = costModel.getHostPort();
            int valueBytesLength = costModel.getValueBytesLength();

            // 耗时详细统计
            CostTimeDetailStatKey costTimeDetailStatKey = new CostTimeDetailStatKey(currentMinute, command, hostPort);
            if (DATA_COST_TIME_MAP_ALL.containsKey(costTimeDetailStatKey)) {
                AtomicLongMap<Integer> stat = DATA_COST_TIME_MAP_ALL.get(costTimeDetailStatKey);
                stat.getAndIncrement(cost);
            } else {
                AtomicLongMap<Integer> stat = AtomicLongMap.create();
                stat.getAndIncrement(cost);
                AtomicLongMap<Integer> currentStat = DATA_COST_TIME_MAP_ALL.putIfAbsent(costTimeDetailStatKey, stat);
                if (currentStat != null) {
                    currentStat.getAndIncrement(cost);
                }
            }

            // 值分布
            ValueSizeDistriEnum redisValueSizeEnum = ValueSizeDistriEnum.getRightSizeBetween(valueBytesLength);
            if (redisValueSizeEnum != null) {
                ValueLengthModel valueLengthModel = new ValueLengthModel(redisValueSizeEnum, costModel.getCommand(),
                        costModel.getHostPort());
                if (DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.containsKey(currentMinute)) {
                    DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.get(currentMinute).getAndIncrement(valueLengthModel);
                } else {
                    AtomicLongMap<ValueLengthModel> dataValueLengthMap = AtomicLongMap.create();
                    dataValueLengthMap.getAndIncrement(valueLengthModel);
                    AtomicLongMap<ValueLengthModel> currentDataValueLengthMap = DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL
                            .putIfAbsent(currentMinute, dataValueLengthMap);
                    if (currentDataValueLengthMap != null) {
                        currentDataValueLengthMap.getAndIncrement(valueLengthModel);
                    }
                }
            }

            // 统计收集这件事本身的耗时
            Long collectCostTime = System.currentTimeMillis() - start;
            if (COLLECTION_COST_TIME_MAP_ALL.containsKey(currentMinute)) {
                AtomicLongMap<Long> stat = COLLECTION_COST_TIME_MAP_ALL.get(currentMinute);
                stat.getAndIncrement(collectCostTime);
            } else {
                AtomicLongMap<Long> stat = AtomicLongMap.create();
                stat.getAndIncrement(collectCostTime);
                AtomicLongMap<Long> currentStat = COLLECTION_COST_TIME_MAP_ALL.putIfAbsent(currentMinute, stat);
                if (currentStat != null) {
                    currentStat.getAndIncrement(collectCostTime);
                }
            }
        } catch (Exception e) {
            logger.error("collect data error: " + e.getMessage());
        }
    }

    /**
     * 收集异常
     * 
     * @param exception
     * @param hostPort
     * @param currentTime(�?留)
     */
    public static void collectException(Exception exception, String hostPort, long currentTime) {
        collectException(exception, hostPort, currentTime, ClientExceptionType.REDIS_TYPE);
    }

    /**
     * 收集异常
     * 
     * @param exception
     * @param hostPort
     * @param currentTime
     * @param clientExceptionType（区分jedis还是client）
     */
    public static void collectException(Exception exception, String hostPort, long currentTime,
            ClientExceptionType clientExceptionType) {
        if (exception == null) {
            return;
        }
        try {
            // 当�?分钟 yyyyMMddHHmm00
            String currentMinute = ClientReportConstant.getCollectTimeSDf().format(new Date());

            ExceptionModel jedisExceptionModel = new ExceptionModel();
            String exceptionClassName = exception.getClass().getName();
            jedisExceptionModel.setExceptionClass(exceptionClassName);
            jedisExceptionModel.setHostPort(hostPort);
            jedisExceptionModel.setClientExceptionType(clientExceptionType);

            if (DATA_EXCEPTION_MAP_ALL.containsKey(currentMinute)) {
                DATA_EXCEPTION_MAP_ALL.get(currentMinute).getAndIncrement(jedisExceptionModel);
            } else {
                AtomicLongMap<ExceptionModel> dataExcpetionMap = AtomicLongMap.create();
                dataExcpetionMap.getAndIncrement(jedisExceptionModel);
                AtomicLongMap<ExceptionModel> currentDataExcpetionMap = DATA_EXCEPTION_MAP_ALL.putIfAbsent(
                        currentMinute, dataExcpetionMap);
                if (currentDataExcpetionMap != null) {
                    currentDataExcpetionMap.getAndIncrement(jedisExceptionModel);
                }
            }
        } catch (Exception e) {
            logger.error("collect exception error: " + e.getMessage());
        }
    }

    /**
     * 清除targetMinute之�?的耗时
     * 
     * @param targetMinute
     */
    private static void clearCostTime(String targetMinute) {
        try {
            if (targetMinute == "" || "".equals(targetMinute)) {
                return;
            }
            long targetMinuteLong = NumberUtil.toLong(targetMinute);
            if (targetMinuteLong == 0) {
                return;
            }
            for (CostTimeDetailStatKey key : DATA_COST_TIME_MAP_ALL.keySet()) {
                long minute = NumberUtil.toLong(key.getCurrentMinute());
                if (minute < targetMinuteLong) {
                    DATA_COST_TIME_MAP_ALL.remove(key);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 清除targetMinute之�?的值分布
     * 
     * @param targetMinute
     */
    private static void clearValueLength(String targetMinute) {
        try {
            if (targetMinute == "" || "".equals(targetMinute)) {
                return;
            }
            long targetMinuteLong = NumberUtil.toLong(targetMinute);
            if (targetMinuteLong == 0) {
                return;
            }
            for (String key : DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.keySet()) {
                long minute = NumberUtil.toLong(key);
                if (minute < targetMinuteLong) {
                    DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.remove(key);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 清除收集数�?�耗时本身
     * 
     * @param targetMinute
     */
    private static void clearCollectionCost(String targetMinute) {
        try {
            if (targetMinute == "" || "".equals(targetMinute)) {
                return;
            }
            long targetMinuteLong = NumberUtil.toLong(targetMinute);
            if (targetMinuteLong == 0) {
                return;
            }
            for (String key : COLLECTION_COST_TIME_MAP_ALL.keySet()) {
                long minute = NumberUtil.toLong(key);
                if (minute < targetMinuteLong) {
                    COLLECTION_COST_TIME_MAP_ALL.remove(key);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 清除targetMinute之�?的异常
     * 
     * @param targetMinute
     */
    private static void clearException(String targetMinute) {
        try {
            if (targetMinute == "" || "".equals(targetMinute)) {
                return;
            }
            long targetMinuteLong = NumberUtil.toLong(targetMinute);
            if (targetMinuteLong == 0) {
                return;
            }
            for (String key : DATA_EXCEPTION_MAP_ALL.keySet()) {
                long minute = NumberUtil.toLong(key);
                if (minute < targetMinuteLong) {
                    DATA_EXCEPTION_MAP_ALL.remove(key);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获�?�上一分钟的值分布
     * 
     * @param currentMinuteStamp
     */
    public static Map<ValueLengthModel, Long> getValueLengthLastMinute(String currentMinuteStamp) {
        AtomicLongMap<ValueLengthModel> map = DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.get(currentMinuteStamp);
        if (map == null || map.isEmpty()) {
            return Collections.emptyMap();
        }
        return map.asMap();
    }

    /**
     * 获�?�上一分钟的耗时
     * 
     * @param currentMinuteStamp
     */
    public static Map<CostTimeDetailStatKey, AtomicLongMap<Integer>> getCostTimeLastMinute(String currentMinuteStamp) {
        Map<CostTimeDetailStatKey, AtomicLongMap<Integer>> result = new HashMap<CostTimeDetailStatKey, AtomicLongMap<Integer>>();
        for (Entry<CostTimeDetailStatKey, AtomicLongMap<Integer>> entry : DATA_COST_TIME_MAP_ALL.entrySet()) {
            CostTimeDetailStatKey costTimeDetailStatKey = entry.getKey();
            if (costTimeDetailStatKey != null && costTimeDetailStatKey.getCurrentMinute() != null
                    && costTimeDetailStatKey.getCurrentMinute().equals(currentMinuteStamp)) {
                result.put(costTimeDetailStatKey, entry.getValue());
            }
        }
        return result;
    }

    /**
     * 获�?�上一分钟的异常
     * 
     * @param currentMinuteStamp
     */
    public static Map<ExceptionModel, Long> getExceptionLastMinute(String currentMinuteStamp) {
        AtomicLongMap<ExceptionModel> map = DATA_EXCEPTION_MAP_ALL.get(currentMinuteStamp);
        if (map == null || map.isEmpty()) {
            return Collections.emptyMap();
        }
        return map.asMap();
    }

    /**
     * 产生耗时详细分布
     * 
     * @param statMap
     */
    public static CostTimeDetailStatModel generateCostTimeDetailStatKey(AtomicLongMap<Integer> statMap) {
        CostTimeDetailStatModel model = new CostTimeDetailStatModel();
        model.setMean(getMeanValue(statMap));
        model.setMedian(fillCostTimeDetailStatModel(model, statMap, 50));
        model.setNinetyPercentMax(fillCostTimeDetailStatModel(model, statMap, 90));
        model.setNinetyNinePercentMax(fillCostTimeDetailStatModel(model, statMap, 99));
        model.setHundredMax(fillCostTimeDetailStatModel(model, statMap, 100));
        // 上�?�已�?设置过了
        // model.setTotalCount(getTotalValue(statMap));
        return model;

    }

    /**
     * 获�?�平�?�值
     * 
     * @param statMap
     */
    private static double getMeanValue(AtomicLongMap<Integer> statMap) {
        if (statMap == null || statMap.isEmpty()) {
            return 0;
        }
        Map<Integer, Long> map = statMap.asMap();
        Long totalCount = 0L;
        Long totalValue = 0L;
        for (Entry<Integer, Long> entry : map.entrySet()) {
            totalCount += entry.getValue();
            totalValue += entry.getKey() * entry.getValue();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        Double result = totalValue * 1.0 / totalCount * 1.0;
        return NumberUtil.toDouble(df.format(result));
    }

    /**
     * 计算Integer-Long结构排�?�?�，百分之多少所在的对应数�?�
     * 
     * @param statMap
     * @param percent
     * @return
     */
    private static int fillCostTimeDetailStatModel(CostTimeDetailStatModel model, AtomicLongMap<Integer> statMap,
            double percent) {
        int wrongResultValue = 0;
        if (percent > 100 || percent < 0) {
            return wrongResultValue;
        }
        if (statMap == null || statMap.isEmpty()) {
            return wrongResultValue;
        }
        Map<Integer, Long> sortKeyMap = new TreeMap<Integer, Long>(statMap.asMap());
        Long totalSize = model.getTotalCount();
        if (totalSize <= 0) {
            for (Long count : sortKeyMap.values()) {
                totalSize += count;
            }
            model.setTotalCount(totalSize);
        }
        return getPercentValue(totalSize, sortKeyMap, percent);
    }

    private static int getPercentValue(Long totalSize, Map<Integer, Long> sortKeyMap, double percent) {
        // 计算百分比所在个数
        Long targetLocation = (long) (totalSize * percent / 100.0);
        Long count = 0L;
        Integer key = 0;
        for (Entry<Integer, Long> entry : sortKeyMap.entrySet()) {
            key = entry.getKey();
            count += entry.getValue();
            if (count > targetLocation) {
                break;
            }
        }
        return key;
    }

    public static Map<CostTimeDetailStatKey, AtomicLongMap<Integer>> getDataCostTimeMapAll() {
        return DATA_COST_TIME_MAP_ALL;
    }

    public static Map<String, AtomicLongMap<ValueLengthModel>> getDataValueLengthDistributeMapAll() {
        return DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL;
    }

    public static Map<String, AtomicLongMap<ExceptionModel>> getDataExceptionMapAll() {
        return DATA_EXCEPTION_MAP_ALL;
    }

    public static Map<String, AtomicLongMap<Long>> getCollectionCostTimeMapAll() {
        return COLLECTION_COST_TIME_MAP_ALL;
    }

    public static void setCOLLECTION_COST_TIME_MAP_ALL(ConcurrentHashMap<String, AtomicLongMap<Long>> cOLLECTION_COST_TIME_MAP_ALL) {
        COLLECTION_COST_TIME_MAP_ALL = cOLLECTION_COST_TIME_MAP_ALL;
    }

}
