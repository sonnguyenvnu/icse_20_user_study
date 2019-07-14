package com.sohu.cache.redis.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.sohu.cache.async.AsyncService;
import com.sohu.cache.async.AsyncThreadPoolFactory;
import com.sohu.cache.async.KeyCallable;
import com.sohu.cache.constant.*;
import com.sohu.cache.dao.*;
import com.sohu.cache.entity.*;
import com.sohu.cache.machine.MachineCenter;
import com.sohu.cache.protocol.RedisProtocol;
import com.sohu.cache.redis.RedisCenter;
import com.sohu.cache.redis.enums.RedisInfoEnum;
import com.sohu.cache.redis.enums.RedisReadOnlyCommandEnum;
import com.sohu.cache.schedule.SchedulerCenter;
import com.sohu.cache.stats.instance.InstanceStatsCenter;
import com.sohu.cache.util.*;
import com.sohu.cache.web.util.DateUtil;
import com.sohu.cache.web.vo.RedisSlowLog;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisAskDataException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisMovedDataException;
import redis.clients.util.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yijunzhang on 14-6-10.
 */
public class RedisCenterImpl implements RedisCenter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final int REDIS_DEFAULT_TIME = 4000;

    private SchedulerCenter schedulerCenter;

    private AppStatsDao appStatsDao;

    private AsyncService asyncService;

    private InstanceDao instanceDao;

    private InstanceStatsDao instanceStatsDao;

    private InstanceStatsCenter instanceStatsCenter;

    private MachineCenter machineCenter;

    private volatile Map<String, JedisPool> jedisPoolMap = new HashMap<String, JedisPool>();

    private final Lock lock = new ReentrantLock();

    private AppDao appDao;

    private AppAuditLogDao appAuditLogDao;

    public static final String REDIS_SLOWLOG_POOL = "redis-slowlog-pool";

    public void init() {
        asyncService.assemblePool(getThreadPoolKey(), AsyncThreadPoolFactory.REDIS_SLOWLOG_THREAD_POOL);
    }

    private InstanceSlowLogDao instanceSlowLogDao;

    @Override
    public boolean deployRedisCollection(long appId, String host, int port) {
        Assert.isTrue(appId > 0);
        Assert.hasText(host);
        Assert.isTrue(port > 0);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(ConstUtils.HOST_KEY, host);
        dataMap.put(ConstUtils.PORT_KEY, port);
        dataMap.put(ConstUtils.APP_KEY, appId);
        JobKey jobKey = JobKey.jobKey(ConstUtils.REDIS_JOB_NAME, ConstUtils.REDIS_JOB_GROUP);
        TriggerKey triggerKey = TriggerKey
                .triggerKey(ObjectConvert.linkIpAndPort(host, port), ConstUtils.REDIS_TRIGGER_GROUP + appId);
        return schedulerCenter
                .deployJobByCron(jobKey, triggerKey, dataMap, ScheduleUtil.getMinuteCronByAppId(appId), false);
    }

    private JedisPool maintainJedisPool(String host, int port, String password) {
        String hostAndPort = ObjectConvert.linkIpAndPort(host, port);
        JedisPool jedisPool = jedisPoolMap.get(hostAndPort);
        if (jedisPool == null) {
            lock.lock();
            try {
                //double check
                jedisPool = jedisPoolMap.get(hostAndPort);
                if (jedisPool == null) {
                    try {
                        if (StringUtils.isNotBlank(password)) {
                            jedisPool = new JedisPool(new GenericObjectPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, password);
                        } else {
                            jedisPool = new JedisPool(new GenericObjectPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT);
                        }
                        jedisPoolMap.put(hostAndPort, jedisPool);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    } finally {

                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return jedisPool;
    }

    @Override
    public boolean unDeployRedisCollection(long appId, String host, int port) {
        Assert.isTrue(appId > 0);
        Assert.hasText(host);
        Assert.isTrue(port > 0);
        TriggerKey triggerKey = TriggerKey
                .triggerKey(ObjectConvert.linkIpAndPort(host, port), ConstUtils.REDIS_TRIGGER_GROUP + appId);
        Trigger trigger = schedulerCenter.getTrigger(triggerKey);
        if (trigger == null) {
            return true;
        }
        return schedulerCenter.unscheduleJob(triggerKey);
    }

    private String buildFutureKey(long appId, long collectTime, String host, int port) {
        StringBuilder keyBuffer = new StringBuilder("redis-");
        keyBuffer.append(collectTime);
        keyBuffer.append("-");
        keyBuffer.append(appId);
        keyBuffer.append("-");
        keyBuffer.append(host + ":" + port);
        return keyBuffer.toString();
    }

    private class RedisKeyCallable extends KeyCallable<Boolean> {
        private final long appId;
        private final long collectTime;
        private final String host;
        private final int port;
        private final Map<RedisConstant, Map<String, Object>> infoMap;
        private final Map<String, Object> clusterInfoMap;


        private RedisKeyCallable(long appId, long collectTime, String host, int port,
                                 Map<RedisConstant, Map<String, Object>> infoMap, Map<String, Object> clusterInfoMap) {
            super(buildFutureKey(appId, collectTime, host, port));
            this.appId = appId;
            this.collectTime = collectTime;
            this.host = host;
            this.port = port;
            this.infoMap = infoMap;
            this.clusterInfoMap = clusterInfoMap;
        }

        @Override
        public Boolean execute() {
            //比对currentInfoMap和lastInfoMap,计算差值
            long lastCollectTime = ScheduleUtil.getLastCollectTime(collectTime);
            Map<String, Object> lastInfoMap = instanceStatsCenter
                    .queryStandardInfoMap(lastCollectTime, host, port, ConstUtils.REDIS);

            if (lastInfoMap == null || lastInfoMap.isEmpty()) {
                logger.error("[redis-lastInfoMap] : lastCollectTime = {} appId={} host:port = {}:{} is null",
                        lastCollectTime, appId, host, port);
            }
            //基本统计累加差值
            Table<RedisConstant, String, Long> baseDiffTable = getAccumulationDiff(infoMap, lastInfoMap);
            fillAccumulationMap(infoMap, baseDiffTable);

            //命令累加差值
            Table<RedisConstant, String, Long> commandDiffTable = getCommandsDiff(infoMap, lastInfoMap);
            fillAccumulationMap(infoMap, commandDiffTable);

            Map<String, Object> currentInfoMap = new LinkedHashMap<String, Object>();
            for (RedisConstant constant : infoMap.keySet()) {
                currentInfoMap.put(constant.getValue(), infoMap.get(constant));
            }
            currentInfoMap.put(ConstUtils.COLLECT_TIME, collectTime);
            instanceStatsCenter.saveStandardStats(currentInfoMap, clusterInfoMap, host, port, ConstUtils.REDIS);

            // 更新实例在db中的状�?
            InstanceStats instanceStats = getInstanceStats(appId, host, port, infoMap);
            if (instanceStats != null) {
                instanceStatsDao.updateInstanceStats(instanceStats);
            }

            boolean isMaster = isMaster(infoMap);
            if (isMaster) {
                Table<RedisConstant, String, Long> diffTable = HashBasedTable.create();
                diffTable.putAll(baseDiffTable);
                diffTable.putAll(commandDiffTable);

                long allCommandCount = 0L;
                //更新命令统计
                List<AppCommandStats> commandStatsList = getCommandStatsList(appId, collectTime, diffTable);
                for (AppCommandStats commandStats : commandStatsList) {
                    //排除无效命令且存储有累加的数�?�
                    if (RedisExcludeCommand.isExcludeCommand(commandStats.getCommandName())
                            || commandStats.getCommandCount() <= 0L) {
                        continue;
                    }
                    allCommandCount += commandStats.getCommandCount();
                    try {
                        appStatsDao.mergeMinuteCommandStatus(commandStats);
                        appStatsDao.mergeHourCommandStatus(commandStats);
                    } catch (Exception e) {
                        logger.error(e.getMessage() + appId, e);
                    }
                }
                //写入app分钟统计
                AppStats appStats = getAppStats(appId, collectTime, diffTable, infoMap);
                try {
                    appStats.setCommandCount(allCommandCount);
                    appStatsDao.mergeMinuteAppStats(appStats);
                    appStatsDao.mergeHourAppStats(appStats);
                } catch (Exception e) {
                    logger.error(e.getMessage() + appId, e);
                }
                logger.info("collect redis info done, appId: {}, instance: {}:{}, time: {}", appId, host, port,
                        collectTime);
            }

            return true;
        }
    }


    @Override
    public List<InstanceSlowLog> collectRedisSlowLog(long appId, long collectTime, String host, int port) {
        Assert.isTrue(appId > 0);
        Assert.hasText(host);
        Assert.isTrue(port > 0);
        InstanceInfo instanceInfo = instanceDao.getInstByIpAndPort(host, port);
        //�?存在实例/实例异常/下线
        if (instanceInfo == null) {
            return null;
        }
        if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
            //忽略sentinel redis实例
            return null;
        }
        // 从redis中获�?�慢查询日志
        List<RedisSlowLog> redisLowLogList = getRedisSlowLogs(appId, host, port, 100);
        if (CollectionUtils.isEmpty(redisLowLogList)) {
            return Collections.emptyList();
        }

        // transfer
        final List<InstanceSlowLog> instanceSlowLogList = new ArrayList<InstanceSlowLog>();
        for (RedisSlowLog redisSlowLog : redisLowLogList) {
            InstanceSlowLog instanceSlowLog = transferRedisSlowLogToInstance(redisSlowLog, instanceInfo);
            if (instanceSlowLog == null) {
                continue;
            }
            instanceSlowLogList.add(instanceSlowLog);
        }

        if (CollectionUtils.isEmpty(instanceSlowLogList)) {
            return Collections.emptyList();
        }

        //处�?�
        String key = getThreadPoolKey() + "_" + host + "_" + port;
        boolean isOk = asyncService.submitFuture(getThreadPoolKey(), new KeyCallable<Boolean>(key) {
            @Override
            public Boolean execute() {
                try {
                    instanceSlowLogDao.batchSave(instanceSlowLogList);
                    return true;
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    return false;
                }
            }
        });
        if (!isOk) {
            logger.error("slowlog submitFuture failed,appId:{},collectTime:{},host:{},port:{}", appId, collectTime, host, port);
        }
        return instanceSlowLogList;
    }

    private InstanceSlowLog transferRedisSlowLogToInstance(RedisSlowLog redisSlowLog, InstanceInfo instanceInfo) {
        if (redisSlowLog == null) {
            return null;
        }
        String command = redisSlowLog.getCommand();
        long executionTime = redisSlowLog.getExecutionTime();
        //如果command=BGREWRITEAOF并且�?于50毫秒,则忽略
        if (command.equalsIgnoreCase("BGREWRITEAOF") && executionTime < 50000) {
            return null;
        }
        InstanceSlowLog instanceSlowLog = new InstanceSlowLog();
        instanceSlowLog.setAppId(instanceInfo.getAppId());
        instanceSlowLog.setCommand(redisSlowLog.getCommand());
        instanceSlowLog.setCostTime((int) redisSlowLog.getExecutionTime());
        instanceSlowLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        instanceSlowLog.setExecuteTime(new Timestamp(redisSlowLog.getDate().getTime()));
        instanceSlowLog.setInstanceId(instanceInfo.getId());
        instanceSlowLog.setIp(instanceInfo.getIp());
        instanceSlowLog.setPort(instanceInfo.getPort());
        instanceSlowLog.setSlowLogId(redisSlowLog.getId());

        return instanceSlowLog;
    }

    private String getThreadPoolKey() {
        return REDIS_SLOWLOG_POOL;
    }

    @Override
    public Map<RedisConstant, Map<String, Object>> collectRedisInfo(long appId, long collectTime, String host,
                                                                    int port) {
        Assert.isTrue(appId > 0);
        Assert.hasText(host);
        Assert.isTrue(port > 0);
        long start = System.currentTimeMillis();
        InstanceInfo instanceInfo = instanceDao.getInstByIpAndPort(host, port);
        //�?存在实例/实例异常/下线
        if (instanceInfo == null) {
            return null;
        }
        if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
            //忽略sentinel redis实例
            return null;
        }
        Map<RedisConstant, Map<String, Object>> infoMap = this.getInfoStats(appId, host, port);
        if (infoMap == null || infoMap.isEmpty()) {
            logger.error("appId:{},collectTime:{},host:{},port:{} cost={} ms redis infoMap is null",
                    new Object[]{appId, collectTime, host, port, (System.currentTimeMillis() - start)});
            return infoMap;
        }
        // cluster info统计
        Map<String,Object> clusterInfoMap = getClusterInfoStats(appId, instanceInfo);
        
        boolean isOk = asyncService.submitFuture(new RedisKeyCallable(appId, collectTime, host, port, infoMap, clusterInfoMap));
        if (!isOk) {
            logger.error("submitFuture failed,appId:{},collectTime:{},host:{},port:{} cost={} ms",
                    new Object[]{appId, collectTime, host, port, (System.currentTimeMillis() - start)});
        }
        return infoMap;
    }

    @Override
    public Map<RedisConstant, Map<String, Object>> getInfoStats(final long appId, final String host, final int port) {
        Map<RedisConstant, Map<String, Object>> infoMap = null;
        final StringBuilder infoBuilder = new StringBuilder();
        try {
            boolean isOk = new IdempotentConfirmer() {
                private int timeOutFactor = 1;

                @Override
                public boolean execute() {
                    Jedis jedis = null;
                    try {
                    	    jedis = getJedis(appId, host, port);
                        jedis.getClient().setConnectionTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
                        jedis.getClient().setSoTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
                        String info = jedis.info("all");
                        infoBuilder.append(info);
                        return StringUtils.isNotBlank(info);
                    } catch (Exception e) {
                        logger.warn("{}:{}, redis-getInfoStats errorMsg:{}", host, port, e.getMessage());
                        return false;
                    } finally {
                        if (jedis != null)
                            jedis.close();
                    }
                }
            }.run();
            if (!isOk) {
                return infoMap;
            }
            infoMap = processRedisStats(infoBuilder.toString());
        } catch (Exception e) {
            logger.error(e.getMessage() + " {}:{}", host, port, e);
        }
        if (infoMap == null || infoMap.isEmpty()) {
            logger.error("host:{},port:{} redis infoMap is null", host, port);
            return infoMap;
        }
        return infoMap;
    }
    
    @Override
    public Map<String, Object> getClusterInfoStats(final long appId, final String host, final int port) {
        InstanceInfo instanceInfo = instanceDao.getAllInstByIpAndPort(host, port);
        return getClusterInfoStats(appId, instanceInfo);
    }
    
    @Override
    public Map<String, Object> getClusterInfoStats(final long appId, final InstanceInfo instanceInfo) {
        long startTime = System.currentTimeMillis();
        if (instanceInfo == null) {
            logger.warn("getClusterInfoStats instanceInfo is null");
            return Collections.emptyMap();
        }
        if (!TypeUtil.isRedisCluster(instanceInfo.getType())) {
            return Collections.emptyMap();
        }
        final String host = instanceInfo.getIp();
        final int port = instanceInfo.getPort();
        Map<String, Object> clusterInfoMap = null;
        final StringBuilder infoBuilder = new StringBuilder();
        try {
            boolean isOk = new IdempotentConfirmer() {
                private int timeOutFactor = 1;

                @Override
                public boolean execute() {
                    Jedis jedis = null;
                    try {
                        jedis = getJedis(appId, host, port);
                        jedis.getClient().setConnectionTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
                        jedis.getClient().setSoTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
                        String clusterInfo = jedis.clusterInfo();
                        infoBuilder.append(clusterInfo);
                        return StringUtils.isNotBlank(clusterInfo);
                    } catch (Exception e) {
                        logger.warn("{}:{}, redis-getInfoStats errorMsg:{}", host, port, e.getMessage());
                        return false;
                    } finally {
                        if (jedis != null)
                            jedis.close();
                    }
                }
            }.run();
            if (!isOk) {
                return clusterInfoMap;
            }
            clusterInfoMap = processClusterInfoStats(infoBuilder.toString());
        } catch (Exception e) {
            logger.error(e.getMessage() + " {}:{}", host, port, e);
        }
        if (MapUtils.isEmpty(clusterInfoMap)) {
            logger.error("{}:{} redis clusterInfoMap is null", host, port);
            return Collections.emptyMap();
        }
        long costTime = System.currentTimeMillis() - startTime;
        if (costTime > 100) {
            logger.warn("{}:{} cluster info cost time {} ms", host, port, costTime);
        }
        return clusterInfoMap;
    }

    private void fillAccumulationMap(Map<RedisConstant, Map<String, Object>> infoMap,
                                     Table<RedisConstant, String, Long> table) {
        if (table == null || table.isEmpty()) {
            return;
        }
        Map<String, Object> accMap = infoMap.get(RedisConstant.DIFF);
        if (accMap == null) {
            accMap = new LinkedHashMap<String, Object>();
            infoMap.put(RedisConstant.DIFF, accMap);
        }
        for (RedisConstant constant : table.rowKeySet()) {
            Map<String, Long> rowMap = table.row(constant);
            accMap.putAll(rowMap);
        }
    }

    /**
     * 获�?�累加�?�数值
     *
     * @param currentInfoMap
     * @return 累加差值map
     */
    private Table<RedisConstant, String, Long> getAccumulationDiff(
            Map<RedisConstant, Map<String, Object>> currentInfoMap,
            Map<String, Object> lastInfoMap) {
        //没有上一次统计快照，忽略差值统计
        if (lastInfoMap == null || lastInfoMap.isEmpty()) {
            return HashBasedTable.create();
        }
        Map<RedisInfoEnum, Long> currentMap = new LinkedHashMap<RedisInfoEnum, Long>();
        for (RedisInfoEnum acc : RedisInfoEnum.getNeedCalDifRedisInfoEnumList()) {
            Long count = getCommonCount(currentInfoMap, acc.getRedisConstant(), acc.getValue());
            if (count != null) {
                currentMap.put(acc, count);
            }
        }
        Map<RedisInfoEnum, Long> lastMap = new LinkedHashMap<RedisInfoEnum, Long>();
        for (RedisInfoEnum acc : RedisInfoEnum.getNeedCalDifRedisInfoEnumList()) {
            if (lastInfoMap != null) {
                Long lastCount = getCommonCount(lastInfoMap, acc.getRedisConstant(), acc.getValue());
                if (lastCount != null) {
                    lastMap.put(acc, lastCount);
                }
            }
        }
        Table<RedisConstant, String, Long> resultTable = HashBasedTable.create();
        for (RedisInfoEnum key : currentMap.keySet()) {
            Long value = MapUtils.getLong(currentMap, key, null);
            Long lastValue = MapUtils.getLong(lastMap, key, null);
            if (value == null || lastValue == null) {
                //忽略
                continue;
            }
            long diff = 0L;
            if (value > lastValue) {
                diff = value - lastValue;
            }
            resultTable.put(key.getRedisConstant(), key.getValue(), diff);
        }
        return resultTable;
    }

    /**
     * 获�?�命令差值统计
     *
     * @param currentInfoMap
     * @param lastInfoMap
     * @return 命令统计
     */
    private Table<RedisConstant, String, Long> getCommandsDiff(Map<RedisConstant, Map<String, Object>> currentInfoMap,
                                                               Map<String, Object> lastInfoMap) {
        //没有上一次统计快照，忽略差值统计
        if (lastInfoMap == null || lastInfoMap.isEmpty()) {
            return HashBasedTable.create();
        }
        Map<String, Object> map = currentInfoMap.get(RedisConstant.Commandstats);
        Map<String, Long> currentMap = transferLongMap(map);
        Map<String, Object> lastObjectMap;
        if (lastInfoMap.get(RedisConstant.Commandstats.getValue()) == null) {
            lastObjectMap = new HashMap<String, Object>();
        } else {
            lastObjectMap = (Map<String, Object>) lastInfoMap.get(RedisConstant.Commandstats.getValue());
        }
        Map<String, Long> lastMap = transferLongMap(lastObjectMap);

        Table<RedisConstant, String, Long> resultTable = HashBasedTable.create();
        for (String command : currentMap.keySet()) {
            long lastCount = MapUtils.getLong(lastMap, command, 0L);
            long currentCount = MapUtils.getLong(currentMap, command, 0L);
            if (currentCount > lastCount) {
                resultTable.put(RedisConstant.Commandstats, command, currentCount - lastCount);
            }
        }
        return resultTable;
    }

    private AppStats getAppStats(long appId, long collectTime, Table<RedisConstant, String, Long> table,
                                 Map<RedisConstant, Map<String, Object>> infoMap) {
        AppStats appStats = new AppStats();
        appStats.setAppId(appId);
        appStats.setCollectTime(collectTime);
        appStats.setModifyTime(new Date());
        appStats.setUsedMemory(MapUtils.getLong(infoMap.get(RedisConstant.Memory), RedisInfoEnum.used_memory.getValue(), 0L));
        appStats.setHits(MapUtils.getLong(table.row(RedisConstant.Stats), RedisInfoEnum.keyspace_hits.getValue(), 0L));
        appStats.setMisses(MapUtils.getLong(table.row(RedisConstant.Stats), RedisInfoEnum.keyspace_misses.getValue(), 0L));
        appStats.setEvictedKeys(MapUtils.getLong(table.row(RedisConstant.Stats), RedisInfoEnum.evicted_keys.getValue(), 0L));
        appStats.setExpiredKeys(MapUtils.getLong(table.row(RedisConstant.Stats), RedisInfoEnum.expired_keys.getValue(), 0L));
        appStats.setNetInputByte(MapUtils.getLong(table.row(RedisConstant.Stats), RedisInfoEnum.total_net_input_bytes.getValue(), 0L));
        appStats.setNetOutputByte(MapUtils.getLong(table.row(RedisConstant.Stats), RedisInfoEnum.total_net_output_bytes.getValue(), 0L));
        appStats.setConnectedClients(MapUtils.getIntValue(infoMap.get(RedisConstant.Clients), RedisInfoEnum.connected_clients.getValue(), 0));
        appStats.setObjectSize(getObjectSize(infoMap));
        return appStats;
    }

    private long getObjectSize(Map<RedisConstant, Map<String, Object>> currentInfoMap) {
        Map<String, Object> sizeMap = currentInfoMap.get(RedisConstant.Keyspace);
        if (sizeMap == null || sizeMap.isEmpty()) {
            return 0L;
        }
        long result = 0L;
        Map<String, Long> longSizeMap = transferLongMap(sizeMap);
        for (String key : longSizeMap.keySet()) {
            result += longSizeMap.get(key);
        }
        return result;
    }

    private Long getCommonCount(Map<?, ?> infoMap, RedisConstant redisConstant, String commond) {
        Object constantObject =
                infoMap.get(redisConstant) == null ? infoMap.get(redisConstant.getValue()) : infoMap.get(redisConstant);
        if (constantObject != null && (constantObject instanceof Map)) {
            Map constantMap = (Map) constantObject;
            if (constantMap == null || constantMap.get(commond) == null) {
                return null;
            }
            return MapUtils.getLongValue(constantMap, commond);
        }
        return null;
    }

    /**
     * 转�?�redis 命令行统计结果
     *
     * @param commandMap
     * @return
     */
    private Map<String, Long> transferLongMap(Map<String, Object> commandMap) {
        Map<String, Long> resultMap = new HashMap<String, Long>();
        if (commandMap == null || commandMap.isEmpty()) {
            return resultMap;
        }
        for (String key : commandMap.keySet()) {
            if (commandMap.get(key) == null) {
                continue;
            }
            String value = commandMap.get(key).toString();
            String[] stats = value.split(",");
            if (stats.length == 0) {
                continue;
            }
            String[] calls = stats[0].split("=");
            if (calls == null || calls.length < 2) {
                continue;
            }
            long callCount = Long.valueOf(calls[1]);
            resultMap.put(key, callCount);
        }
        return resultMap;
    }

    private List<AppCommandStats> getCommandStatsList(long appId, long collectTime,
                                                      Table<RedisConstant, String, Long> table) {
        Map<String, Long> commandMap = table.row(RedisConstant.Commandstats);
        List<AppCommandStats> list = new ArrayList<AppCommandStats>();
        if (commandMap == null) {
            return list;
        }
        for (String key : commandMap.keySet()) {
            String commandName = key.replace("cmdstat_", "");
            long callCount = MapUtils.getLong(commandMap, key, 0L);
            if (callCount == 0L) {
                continue;
            }
            AppCommandStats commandStats = new AppCommandStats();
            commandStats.setAppId(appId);
            commandStats.setCollectTime(collectTime);
            commandStats.setCommandName(commandName);
            commandStats.setCommandCount(callCount);
            commandStats.setModifyTime(new Date());
            list.add(commandStats);
        }
        return list;
    }
    
    /**
     * 处�?�clusterinfo统计信�?�
     * @param clusterInfo
     * @return
     */
    private Map<String, Object> processClusterInfoStats(String clusterInfo) {
        Map<String, Object> clusterInfoMap = new HashMap<String, Object>();
        String[] lines = clusterInfo.split("\r\n");
        for (String line : lines) {
            String[] pair = line.split(":");
            if (pair.length == 2) {
                clusterInfoMap.put(pair[0], pair[1]);
            }
        }
        return clusterInfoMap;
    }

    /**
     * 处�?�redis统计信�?�
     *
     * @param statResult 统计结果串
     */
    private Map<RedisConstant, Map<String, Object>> processRedisStats(String statResult) {
        Map<RedisConstant, Map<String, Object>> redisStatMap = new HashMap<RedisConstant, Map<String, Object>>();
        String[] data = statResult.split("\r\n");
        String key;
        int i = 0;
        int length = data.length;
        while (i < length) {
            if (data[i].contains("#")) {
                int index = data[i].indexOf('#');
                key = data[i].substring(index + 1);
                ++i;
                RedisConstant redisConstant = RedisConstant.value(key.trim());
                if (redisConstant == null) {
                    continue;
                }
                Map<String, Object> sectionMap = new LinkedHashMap<String, Object>();
                while (i < length && data[i].contains(":")) {
                    String[] pair = StringUtils.splitByWholeSeparator(data[i], ":");
                    sectionMap.put(pair[0], pair[1]);
                    i++;
                }
                redisStatMap.put(redisConstant, sectionMap);
            } else {
                i++;
            }
        }
        return redisStatMap;
    }
    
    /**
     * 根�?�infoMap的结果判断实例的主从
     *
     * @param infoMap
     * @return
     */
    private Boolean hasSlaves(Map<RedisConstant, Map<String, Object>> infoMap) {
        Map<String, Object> replicationMap = infoMap.get(RedisConstant.Replication);
        if (MapUtils.isEmpty(replicationMap)) {
            return null;
        }
        for (Entry<String, Object> entry : replicationMap.entrySet()) {
            String key = entry.getKey();
            //判断一个�?��?�
            if (key != null && key.contains("slave0")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根�?�infoMap的结果判断实例的主从
     *
     * @param infoMap
     * @return
     */
    private Boolean isMaster(Map<RedisConstant, Map<String, Object>> infoMap) {
        Map<String, Object> map = infoMap.get(RedisConstant.Replication);
        if (map == null || map.get(RedisInfoEnum.role.getValue()) == null) {
            return null;
        }
        if (String.valueOf(map.get(RedisInfoEnum.role.getValue())).equals("master")) {
            return true;
        }
        return false;
    }

    /**
     * 根�?�ip和port判断�?一个实例当�?是主还是从
     *
     * @param ip   ip
     * @param port port
     * @return 主返回true， 从返回false；
     */
    @Override
    public Boolean isMaster(long appId, String ip, int port) {
        Jedis jedis = getJedis(appId, ip, port, REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
        try {
            String info = jedis.info("all");
            Map<RedisConstant, Map<String, Object>> infoMap = processRedisStats(info);
            return isMaster(infoMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            jedis.close();
        }
    }
    
    /**
     * 根�?�ip和port判断redis实例当�?是�?�有从节点
     * @param ip   ip
     * @param port port
     * @return 主返回true，从返回false；
     */
    public Boolean hasSlaves(long appId, String ip, int port) {
        Jedis jedis = getJedis(appId, ip, port, REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
        try {
            String info = jedis.info("all");
            Map<RedisConstant, Map<String, Object>> infoMap = processRedisStats(info);
            return hasSlaves(infoMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            jedis.close();
        }
    }   

    @Override
    public HostAndPort getMaster(String ip, int port, String password) {
        JedisPool jedisPool = maintainJedisPool(ip, port, password);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String info = jedis.info(RedisConstant.Replication.getValue());
            Map<RedisConstant, Map<String, Object>> infoMap = processRedisStats(info);
            Map<String, Object> map = infoMap.get(RedisConstant.Replication);
            if (map == null) {
                return null;
            }
            String masterHost = MapUtils.getString(map, RedisInfoEnum.master_host.getValue(), null);
            int masterPort = MapUtils.getInteger(map, RedisInfoEnum.master_port.getValue(), 0);
            if (StringUtils.isNotBlank(masterHost) && masterPort > 0) {
                return new HostAndPort(masterHost, masterPort);
            }
            return null;
        } catch (Exception e) {
            logger.error("{}:{} getMaster failed {}", ip, port, e.getMessage(), e);
            return null;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
    
    @Override
	public boolean isRun(final String ip, final int port) {
    		return isRun(ip, port, null);
    }

    @Override
    public boolean isRun(final long appId, final String ip, final int port) {
    		AppDesc appDesc = appDao.getAppDescById(appId);
    		return isRun(ip, port, appDesc.getPassword());
    }
    
    @Override
	public boolean isRun(final String ip, final int port, final String password) {
   		boolean isRun = new IdempotentConfirmer() {
            private int timeOutFactor = 1;

            @Override
            public boolean execute() {
                Jedis jedis = null;
                try {
                		jedis = getJedis(ip, port, password);
                    jedis.getClient().setConnectionTimeout(Protocol.DEFAULT_TIMEOUT * (timeOutFactor++));
                    jedis.getClient().setSoTimeout(Protocol.DEFAULT_TIMEOUT * (timeOutFactor++));
                    String pong = jedis.ping();
                    return pong != null && pong.equalsIgnoreCase("PONG");
                } catch (JedisDataException e) {
                    String message = e.getMessage();
                    logger.warn(e.getMessage());
                    if (StringUtils.isNotBlank(message) && message.startsWith("LOADING")) {
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    logger.warn("{}:{} error message is {} ", ip, port, e.getMessage());
                    return false;
                } finally {
                		if (jedis != null) {
                			jedis.close();
					}
                }
            }
        }.run();
        return isRun;
	}
    

    @Override
    public boolean shutdown(long appId, String ip, int port) {
        boolean isRun = isRun(appId, ip, port);
        if (!isRun) {
            return true;
        }
        final Jedis jedis = getJedis(appId, ip, port);
        try {
            //关闭实例节点
            boolean isShutdown = new IdempotentConfirmer() {
                @Override
                public boolean execute() {
                    jedis.shutdown();
                    return true;
                }
            }.run();
            if (!isShutdown) {
                logger.error("{}:{} redis not shutdown!", ip, port);
            }
            return isShutdown;
        } finally {
            jedis.close();
        }
    }
    
    @Override
	public boolean shutdown(String ip, int port) {
    		boolean isRun = isRun(ip, port);
        if (!isRun) {
            return true;
        }
        final Jedis jedis = getJedis(ip, port);
        try {
            //关闭实例节点
            boolean isShutdown = new IdempotentConfirmer() {
                @Override
                public boolean execute() {
                    jedis.shutdown();
                    return true;
                }
            }.run();
            if (!isShutdown) {
                logger.error("{}:{} redis not shutdown!", ip, port);
            }
            return isShutdown;
        } finally {
            jedis.close();
        }
	}

    /**
     * 返回当�?实例的一些关键指标
     *
     * @param appId
     * @param ip
     * @param port
     * @param infoMap
     * @return
     */
    public InstanceStats getInstanceStats(long appId, String ip, int port,
                                          Map<RedisConstant, Map<String, Object>> infoMap) {
        if (infoMap == null) {
            return null;
        }
        // 查询最大内存�?制
        Long maxMemory = this.getRedisMaxMemory(appId, ip, port);
        /**
         * 将实例的一些关键指标返回
         */
        InstanceStats instanceStats = new InstanceStats();
        instanceStats.setAppId(appId);
        InstanceInfo curInst = instanceDao.getInstByIpAndPort(ip, port);
        if (curInst != null) {
            instanceStats.setHostId(curInst.getHostId());
            instanceStats.setInstId(curInst.getId());
        } else {
            logger.error("redis={}:{} not found", ip, port);
            return null;
        }
        instanceStats.setIp(ip);
        instanceStats.setPort(port);
        if (maxMemory != null) {
            instanceStats.setMaxMemory(maxMemory);
        }
        instanceStats.setUsedMemory(MapUtils.getLongValue(infoMap.get(RedisConstant.Memory), RedisInfoEnum.used_memory.getValue(), 0));
        instanceStats.setHits(MapUtils.getLongValue(infoMap.get(RedisConstant.Stats), RedisInfoEnum.keyspace_hits.getValue(), 0));
        instanceStats.setMisses(MapUtils.getLongValue(infoMap.get(RedisConstant.Stats), RedisInfoEnum.keyspace_misses.getValue(), 0));
        instanceStats.setCurrConnections(MapUtils.getIntValue(infoMap.get(RedisConstant.Clients), RedisInfoEnum.connected_clients.getValue(), 0));
        instanceStats.setCurrItems(getObjectSize(infoMap));
        instanceStats.setRole((byte) 1);
        if (MapUtils.getString(infoMap.get(RedisConstant.Replication), RedisInfoEnum.role.getValue()).equals("slave")) {
            instanceStats.setRole((byte) 2);
        }
        instanceStats.setModifyTime(new Timestamp(System.currentTimeMillis()));
        instanceStats.setMemFragmentationRatio(MapUtils.getDoubleValue(infoMap.get(RedisConstant.Memory), RedisInfoEnum.mem_fragmentation_ratio.getValue(), 0.0));
        instanceStats.setAofDelayedFsync(MapUtils.getIntValue(infoMap.get(RedisConstant.Persistence), RedisInfoEnum.aof_delayed_fsync.getValue(), 0));
        return instanceStats;
    }

    @Override
    public Long getRedisMaxMemory(final long appId, final String ip, final int port) {
        final String key = "maxmemory";
        final Map<String, Long> resultMap = new HashMap<String, Long>();
        boolean isSuccess = new IdempotentConfirmer() {
            private int timeOutFactor = 1;

            @Override
            public boolean execute() {
                Jedis jedis = null;
                try {
                    jedis = getJedis(appId, ip, port);
                    jedis.getClient().setConnectionTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
                    jedis.getClient().setSoTimeout(REDIS_DEFAULT_TIME * (timeOutFactor++));
                    List<String> maxMemoryList = jedis.configGet(key); // 返回结果：list中是2个字符串，如："maxmemory",
                    // "4096000000"
                    if (maxMemoryList != null && maxMemoryList.size() >= 2) {
                        resultMap.put(key, Long.valueOf(maxMemoryList.get(1)));
                    }
                    return MapUtils.isNotEmpty(resultMap);
                } catch (Exception e) {
                    logger.warn("{}:{} errorMsg: {}", ip, port, e.getMessage());
                    return false;
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
            }
        }.run();
        if (isSuccess) {
            return MapUtils.getLong(resultMap, key);
        } else {
            logger.error("{}:{} getMaxMemory failed!", ip, port);
            return null;
        }
    }

    @Override
    public String executeCommand(AppDesc appDesc, String command) {
        //�?�测试应用�?�能执行白�??�?�里�?�的命令
        if (AppDescEnum.AppTest.NOT_TEST.getValue() == appDesc.getIsTest()) {
            if (!RedisReadOnlyCommandEnum.contains(command)) {
                return "online app only support read-only and safe command";
            }
        }
        int type = appDesc.getType();
        long appId = appDesc.getAppId();
        String password = appDesc.getPassword();
        if (type == ConstUtils.CACHE_REDIS_SENTINEL) {
            JedisSentinelPool jedisSentinelPool = getJedisSentinelPool(appDesc);
            if (jedisSentinelPool == null) {
                return "sentinel can not execute ";
            }
            Jedis jedis = null;
            try {
                jedis = jedisSentinelPool.getResource();
                String host = jedis.getClient().getHost();
                int port = jedis.getClient().getPort();
                return executeCommand(appId, host, port, command);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "�?行出错:" + e.getMessage();
            } finally {
                if (jedis != null) jedis.close();
                jedisSentinelPool.destroy();
            }
        } else if (type == ConstUtils.CACHE_REDIS_STANDALONE) {
            List<InstanceInfo> instanceList = instanceDao.getInstListByAppId(appId);
            if (instanceList == null || instanceList.isEmpty()) {
                return "应用没有�?行的实例";
            }
            String host = null;
            int port = 0;
            for (InstanceInfo instanceInfo : instanceList) {
                host = instanceInfo.getIp();
                port = instanceInfo.getPort();
                break;
            }
            try {
                return executeCommand(appId, host, port, command);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "�?行出错:" + e.getMessage();
            }
        } else if (type == ConstUtils.CACHE_TYPE_REDIS_CLUSTER) {
            List<InstanceInfo> instanceList = instanceDao.getInstListByAppId(appId);
            if (instanceList == null || instanceList.isEmpty()) {
                return "应用没有�?行的实例";
            }
            Set<HostAndPort> clusterHosts = new LinkedHashSet<HostAndPort>();
            for (InstanceInfo instance : instanceList) {
                if (instance == null || instance.getStatus() == InstanceStatusEnum.OFFLINE_STATUS.getStatus()) {
                    continue;
                }
                clusterHosts.add(new HostAndPort(instance.getIp(), instance.getPort()));
            }
            if (clusterHosts.isEmpty()) {
                return "no run instance";
            }
            String commandKey = getCommandKey(command);
            for (HostAndPort hostAndPort : clusterHosts) {
                HostAndPort rightHostAndPort = getClusterRightHostAndPort(hostAndPort.getHost(), hostAndPort.getPort(), password, command, commandKey);
                if (rightHostAndPort != null) {
                    try {
                        return executeCommand(appId, rightHostAndPort.getHost(), rightHostAndPort.getPort(), command);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        return "�?行出错:" + e.getMessage();
                    }
                }
            }
            
        }
        return "�?支�?应用类型";
    }
    
    /**
     * 获�?�key对应的节点
     * @param host
     * @param port
     * @param password
     * @param command
     * @param key
     * @return
     */
    private HostAndPort getClusterRightHostAndPort(String host, int port, String password, String command, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis(host, port, password);
            jedis.type(key);
            return new HostAndPort(host, port);
        } catch (JedisMovedDataException e) {
            return e.getTargetNode();
        } catch (JedisAskDataException e) {
            return e.getTargetNode();
        } catch (Exception e) {
            logger.error("command {} is error", command, e.getMessage(), e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    private String getCommandKey(String command) {
        String[] array = StringUtils.trim(command).split("\\s+");
        if (array.length > 1) {
            return array[1];
        } else {
            return null;
        }
    }

    @Override
    public String executeCommand(long appId, String host, int port, String command) {
        AppDesc appDesc = appDao.getAppDescById(appId);
        if (appDesc == null) {
            return "not exist appId";
        }
        //�?�测试应用�?�能执行白�??�?�里�?�的命令
        if (AppDescEnum.AppTest.NOT_TEST.getValue() == appDesc.getIsTest()) {
            if (!RedisReadOnlyCommandEnum.contains(command)) {
                return "online app only support read-only and safe command ";
            }
        }
        String password = appDesc.getPassword();
        String shell = RedisProtocol.getExecuteCommandShell(host, port, password, command);
        //记录客户端�?��?日志
        logger.warn("executeRedisShell={}", shell);
        return machineCenter.executeShell(host, shell);
    }

    @Override
    public JedisSentinelPool getJedisSentinelPool(AppDesc appDesc) {
        if (appDesc == null) {
            logger.error("appDesc is null");
            return null;
        }
        if (appDesc.getType() != ConstUtils.CACHE_REDIS_SENTINEL) {
            logger.error("type={} is not sentinel", appDesc.getType());
            return null;
        }
        long appId = appDesc.getAppId();
        List<InstanceInfo> instanceInfos = instanceDao.getInstListByAppId(appId);

        String masterName = null;
        for (Iterator<InstanceInfo> i = instanceInfos.iterator(); i.hasNext(); ) {
            InstanceInfo instanceInfo = i.next();
            if (instanceInfo.getType() != ConstUtils.CACHE_REDIS_SENTINEL) {
                i.remove();
                continue;
            }
            if (masterName == null && StringUtils.isNotBlank(instanceInfo.getCmd())) {
                masterName = instanceInfo.getCmd();
            }
        }
        Set<String> sentinels = new HashSet<String>();
        for (InstanceInfo instanceInfo : instanceInfos) {
            sentinels.add(instanceInfo.getIp() + ":" + instanceInfo.getPort());
        }
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels);
        return jedisSentinelPool;
    }

    @Override
    public Map<String, String> getRedisConfigList(int instanceId) {
        if (instanceId <= 0) {
            return Collections.emptyMap();
        }
        InstanceInfo instanceInfo = instanceDao.getInstanceInfoById(instanceId);
        if (instanceInfo == null) {
            return Collections.emptyMap();
        }
        if (TypeUtil.isRedisType(instanceInfo.getType())) {
            Jedis jedis = null;
            try {
            		jedis = getJedis(instanceInfo.getAppId(), instanceInfo.getIp(), instanceInfo.getPort(), REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
                List<String> configs = jedis.configGet("*");
                Map<String, String> configMap = new LinkedHashMap<String, String>();
                for (int i = 0; i < configs.size(); i += 2) {
                    if (i < configs.size()) {
                        String key = configs.get(i);
                        String value = configs.get(i + 1);
                        if (StringUtils.isBlank(value)) {
                            continue;
                        }
                        configMap.put(key, value);
                    }
                }
                return configMap;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }

        return Collections.emptyMap();
    }

    @Override
    public List<RedisSlowLog> getRedisSlowLogs(int instanceId, int maxCount) {
        if (instanceId <= 0) {
            return Collections.emptyList();
        }
        InstanceInfo instanceInfo = instanceDao.getInstanceInfoById(instanceId);
        if (instanceInfo == null) {
            return Collections.emptyList();
        }
        if (TypeUtil.isRedisType(instanceInfo.getType())) {
            return getRedisSlowLogs(instanceInfo.getAppId(), instanceInfo.getIp(), instanceInfo.getPort(), maxCount);
        }
        return Collections.emptyList();
    }


    private List<RedisSlowLog> getRedisSlowLogs(long appId, String host, int port, int maxCount) {
        Jedis jedis = null;
        try {
        		jedis = getJedis(appId, host, port, REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
            List<RedisSlowLog> resultList = new ArrayList<RedisSlowLog>();
            List<Slowlog> slowlogs = null;
            if (maxCount > 0) {
                slowlogs = jedis.slowlogGet(maxCount);
            } else {
                slowlogs = jedis.slowlogGet();
            }
            if (slowlogs != null && slowlogs.size() > 0) {
                for (Slowlog sl : slowlogs) {
                    RedisSlowLog rs = new RedisSlowLog();
                    rs.setId(sl.getId());
                    rs.setExecutionTime(sl.getExecutionTime());
                    long time = sl.getTimeStamp() * 1000L;
                    rs.setDate(new Date(time));
                    rs.setTimeStamp(DateUtil.formatYYYYMMddHHMMSS(new Date(time)));
                    rs.setCommand(StringUtils.join(sl.getArgs(), " "));
                    resultList.add(rs);
                }
            }
            return resultList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean configRewrite(final long appId, final String host, final int port) {
        return new IdempotentConfirmer() {
            @Override
            public boolean execute() {
                Jedis jedis = getJedis(appId, host, port, REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
                try {
                    String response = jedis.configRewrite();
                    return response != null && response.equalsIgnoreCase("OK");
                } finally {
                    jedis.close();
                }
            }
        }.run();
    }

    @Override
    public boolean cleanAppData(AppDesc appDesc, AppUser appUser) {
        if (appDesc == null) {
            return false;
        }

        long appId = appDesc.getAppId();

        // 线上应用�?能清�?�数�?�
        if (AppDescEnum.AppTest.IS_TEST.getValue() != appDesc.getIsTest()) {
            logger.error("appId {} profile must be test", appId);
            return false;
        }

        // 必须是redis应用
        if (!TypeUtil.isRedisType(appDesc.getType())) {
            logger.error("appId {} type must be redis", appId);
            return false;
        }

        // 实例验�?
        List<InstanceInfo> instanceList = instanceDao.getInstListByAppId(appId);
        if (CollectionUtils.isEmpty(instanceList)) {
            logger.error("appId {} instanceList is empty", appId);
            return false;
        }

        // 开始清除
        for (InstanceInfo instance : instanceList) {
            if (instance.getStatus() != InstanceStatusEnum.GOOD_STATUS.getStatus()) {
                continue;
            }
            String host = instance.getIp();
            int port = instance.getPort();
            // master + �?�sentinel节点
            Boolean isMater = isMaster(appId, host, port);
            if (isMater != null && isMater.equals(true) && !TypeUtil.isRedisSentinel(instance.getType())) {
            		Jedis jedis = getJedis(appId, host, port);
            		jedis.getClient().setConnectionTimeout(REDIS_DEFAULT_TIME);
            		jedis.getClient().setSoTimeout(60000);
                try {
                    logger.warn("{}:{} start clear data", host, port);
                    long start = System.currentTimeMillis();
                    String result = jedis.flushAll();
                    if (!"ok".equalsIgnoreCase(result)) {
                        return false;
                    }
                    logger.warn("{}:{} finish clear data, cost time:{} ms", host, port,
                            (System.currentTimeMillis() - start));
                } catch (Exception e) {
                    logger.error("clear redis: " + e.getMessage(), e);
                    return false;
                } finally {
                    jedis.close();
                }
            }
        }

        //记录日志
        AppAuditLog appAuditLog = AppAuditLog.generate(appDesc, appUser, 0L, AppAuditLogTypeEnum.APP_CLEAN_DATA);
        appAuditLogDao.save(appAuditLog);

        return true;
    }

    @Override
    public boolean isSingleClusterNode(long appId, String host, int port) {
        final Jedis jedis = getJedis(appId, host, port);
        try {
            String clusterNodes = jedis.clusterNodes();
            if (StringUtils.isBlank(clusterNodes)) {
                throw new RuntimeException(host + ":" + port + "clusterNodes is null");
            }
            String[] nodeInfos = clusterNodes.split("\n");
            if (nodeInfos.length == 1) {
                return true;
            }
            return false;
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> getClientList(int instanceId) {
        if (instanceId <= 0) {
            return Collections.emptyList();
        }
        InstanceInfo instanceInfo = instanceDao.getInstanceInfoById(instanceId);
        if (instanceInfo == null) {
            return Collections.emptyList();
        }
        if (TypeUtil.isRedisType(instanceInfo.getType())) {
            Jedis jedis = null;
            try {
            		jedis = getJedis(instanceInfo.getAppId(), instanceInfo.getIp(), instanceInfo.getPort(), REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
                jedis.clientList();
                List<String> resultList = new ArrayList<String>();
                String clientList = jedis.clientList();
                if (StringUtils.isNotBlank(clientList)) {
                    String[] array = clientList.split("\n");
                    resultList.addAll(Arrays.asList(array));
                }
                return resultList;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Map<String, String> getClusterLossSlots(long appId) {
        // 1.从应用中获�?�一个�?�康的主节点
        InstanceInfo sourceMasterInstance = getHealthyInstanceInfo(appId);
        if (sourceMasterInstance == null) {
            return Collections.emptyMap();
        }
        // 2. 获�?�所有slot和节点的对应关系
        Map<Integer, String> slotHostPortMap = getSlotsHostPortMap(appId, sourceMasterInstance.getIp(), sourceMasterInstance.getPort());
        // 3. 获�?�集群中失�?�的slot
        List<Integer> lossSlotList = getClusterLossSlots(appId, sourceMasterInstance.getIp(), sourceMasterInstance.getPort());
        // 3.1 将失�?�的slot列表组装�?Map<String host:port,List<Integer> lossSlotList>
        Map<String, List<Integer>> hostPortSlotMap = new HashMap<String, List<Integer>>();
        if (CollectionUtils.isNotEmpty(lossSlotList)) {
            for (Integer lossSlot : lossSlotList) {
                String key = slotHostPortMap.get(lossSlot);
                if (hostPortSlotMap.containsKey(key)) {
                    hostPortSlotMap.get(key).add(lossSlot);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(lossSlot);
                    hostPortSlotMap.put(key, list);
                }
            }
        }
        // 3.2 hostPortSlotMap组装�?Map<String host:port,String startSlot-endSlot>
        Map<String, String> slotSegmentsMap = new HashMap<String, String>();
        for (Entry<String, List<Integer>> entry : hostPortSlotMap.entrySet()) {
            List<Integer> list = entry.getValue();
            List<String> slotSegments = new ArrayList<String>();
            int min = list.get(0);
            int max = min;
            for (int i = 1; i < list.size(); i++) {
                int temp = list.get(i);
                if (temp == max + 1) {
                    max = temp;
                } else {
                    slotSegments.add(String.valueOf(min) + "-" + String.valueOf(max));
                    min = temp;
                    max = temp;
                }
            }
            slotSegments.add(String.valueOf(min) + "-" + String.valueOf(max));
            slotSegmentsMap.put(entry.getKey(), slotSegments.toString());
        }
        return slotSegmentsMap;
    }

    /**
     * 从一个应用中获�?�一个�?�康的主节点
     *
     * @param appId
     * @return
     */
    public InstanceInfo getHealthyInstanceInfo(long appId) {
        InstanceInfo sourceMasterInstance = null;
        List<InstanceInfo> appInstanceInfoList = instanceDao.getInstListByAppId(appId);
        if (CollectionUtils.isEmpty(appInstanceInfoList)) {
            logger.error("appId {} has not instances", appId);
            return null;
        }
        for (InstanceInfo instanceInfo : appInstanceInfoList) {
            int instanceType = instanceInfo.getType();
            if (!TypeUtil.isRedisCluster(instanceType)) {
                continue;
            }
            final String host = instanceInfo.getIp();
            final int port = instanceInfo.getPort();
            if (instanceInfo.getStatus() != InstanceStatusEnum.GOOD_STATUS.getStatus()) {
                continue;
            }
            boolean isRun = isRun(appId, host, port);
            if (!isRun) {
                logger.warn("{}:{} is not run", host, port);
                continue;
            }
            boolean isMaster = isMaster(appId, host, port);
            if (!isMaster) {
                logger.warn("{}:{} is not master", host, port);
                continue;
            }
            sourceMasterInstance = instanceInfo;
            break;
        }
        return sourceMasterInstance;
    }

    /**
     * 从一个应用中获�?�所有�?�康master节点
     *
     * @param appId
     * @return 应用对应master节点列表
     */
    public List<InstanceInfo> getAllHealthyInstanceInfo(long appId) {
        // return instances
        List<InstanceInfo> allInstance = new ArrayList<InstanceInfo>();
        List<InstanceInfo> appInstanceInfoList = instanceDao.getInstListByAppId(appId);
        if (CollectionUtils.isEmpty(appInstanceInfoList)) {
            logger.error("appId {} has not instances", appId);
            return null;
        }
        for (InstanceInfo instanceInfo : appInstanceInfoList) {
            int instanceType = instanceInfo.getType();
            if (!TypeUtil.isRedisCluster(instanceType)) {
                continue;
            }
            final String host = instanceInfo.getIp();
            final int port = instanceInfo.getPort();
            if (instanceInfo.getStatus() != InstanceStatusEnum.GOOD_STATUS.getStatus()) {
                continue;
            }
            boolean isRun = isRun(appId, host, port);
            if (!isRun) {
                logger.warn("{}:{} is not run", host, port);
                continue;
            }
            boolean isMaster = isMaster(appId, host, port);
            if (!isMaster) {
                logger.warn("{}:{} is not master", host, port);
                continue;
            }
            // add exist redis
            allInstance.add(instanceInfo);
        }
        return allInstance;
    }

    /**
     * clusterslots命令拼接�?Map<Integer slot, String host:port>
     *
     * @param host
     * @param port
     * @return
     */
    private Map<Integer, String> getSlotsHostPortMap(long appId, String host, int port) {
        Map<Integer, String> slotHostPortMap = new HashMap<Integer, String>();
        Jedis jedis = null;
        try {
            jedis = getJedis(appId, host, port);
            List<Object> slots = jedis.clusterSlots();
            for (Object slotInfoObj : slots) {
                List<Object> slotInfo = (List<Object>) slotInfoObj;
                if (slotInfo.size() <= 2) {
                    continue;
                }
                List<Integer> slotNums = getAssignedSlotArray(slotInfo);

                // hostInfos
                List<Object> hostInfos = (List<Object>) slotInfo.get(2);
                if (hostInfos.size() <= 0) {
                    continue;
                }
                HostAndPort targetNode = generateHostAndPort(hostInfos);

                for (Integer slot : slotNums) {
                    slotHostPortMap.put(slot, targetNode.getHost() + ":" + targetNode.getPort());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return slotHostPortMap;
    }

    private HostAndPort generateHostAndPort(List<Object> hostInfos) {
        return new HostAndPort(SafeEncoder.encode((byte[]) hostInfos.get(0)),
                ((Long) hostInfos.get(1)).intValue());
    }

    private List<Integer> getAssignedSlotArray(List<Object> slotInfo) {
        List<Integer> slotNums = new ArrayList<Integer>();
        for (int slot = ((Long) slotInfo.get(0)).intValue(); slot <= ((Long) slotInfo.get(1))
                .intValue(); slot++) {
            slotNums.add(slot);
        }
        return slotNums;
    }


    @Override
    public List<Integer> getClusterLossSlots(long appId, String host, int port) {
        InstanceInfo instanceInfo = instanceDao.getAllInstByIpAndPort(host, port);
        if (instanceInfo == null) {
            logger.warn("{}:{} instanceInfo is null", host, port);
            return Collections.emptyList();
        }
        if (!TypeUtil.isRedisCluster(instanceInfo.getType())) {
            logger.warn("{}:{} is not rediscluster type", host, port);
            return Collections.emptyList();
        }
        List<Integer> clusterLossSlots = new ArrayList<Integer>();
        Jedis jedis = null;
        try {
            jedis = getJedis(appId, host, port, REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
            String clusterNodes = jedis.clusterNodes();
            if (StringUtils.isBlank(clusterNodes)) {
                throw new RuntimeException(host + ":" + port + "clusterNodes is null");
            }
            Set<Integer> allSlots = new LinkedHashSet<Integer>();
            for (int i = 0; i <= 16383; i++) {
                allSlots.add(i);
            }

            // 解�?
            ClusterNodeInformationParser nodeInfoParser = new ClusterNodeInformationParser();
            for (String nodeInfo : clusterNodes.split("\n")) {
                if (StringUtils.isNotBlank(nodeInfo) && !nodeInfo.contains("disconnected")) {
                    ClusterNodeInformation clusterNodeInfo = nodeInfoParser.parse(nodeInfo, new HostAndPort(host, port));
                    List<Integer> availableSlots = clusterNodeInfo.getAvailableSlots();
                    for (Integer slot : availableSlots) {
                        allSlots.remove(slot);
                    }
                }
            }
            clusterLossSlots = new ArrayList<Integer>(allSlots);
        } catch (Exception e) {
            logger.error("getClusterLossSlots: " + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return clusterLossSlots;
    }

    @Override
    public List<Integer> getInstanceSlots(long appId, String healthHost, int healthPort, String lossSlotsHost, int lossSlotsPort) {
        InstanceInfo instanceInfo = instanceDao.getAllInstByIpAndPort(healthHost, healthPort);
        if (instanceInfo == null) {
            logger.warn("{}:{} instanceInfo is null", healthHost, healthPort);
            return Collections.emptyList();
        }
        if (!TypeUtil.isRedisCluster(instanceInfo.getType())) {
            logger.warn("{}:{} is not rediscluster type", healthHost, healthPort);
            return Collections.emptyList();
        }
        List<Integer> clusterLossSlots = new ArrayList<Integer>();
        Jedis jedis = null;
        try {
        		jedis = getJedis(appId, healthHost, healthPort, REDIS_DEFAULT_TIME, REDIS_DEFAULT_TIME);
            String clusterNodes = jedis.clusterNodes();
            if (StringUtils.isBlank(clusterNodes)) {
                throw new RuntimeException(healthHost + ":" + healthPort + "clusterNodes is null");
            }
            // 解�?
            ClusterNodeInformationParser nodeInfoParser = new ClusterNodeInformationParser();
            for (String nodeInfo : clusterNodes.split("\n")) {
                if (StringUtils.isNotBlank(nodeInfo) && nodeInfo.contains("disconnected") && nodeInfo.contains(lossSlotsHost + ":" + lossSlotsPort)) {
                    ClusterNodeInformation clusterNodeInfo = nodeInfoParser.parse(nodeInfo, new HostAndPort(healthHost, healthPort));
                    clusterLossSlots = clusterNodeInfo.getAvailableSlots();
                }
            }
        } catch (Exception e) {
            logger.error("getClusterLossSlots: " + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return clusterLossSlots;
    }


    public void destory() {
        for (JedisPool jedisPool : jedisPoolMap.values()) {
            jedisPool.destroy();
        }
    }

    @Override
    public boolean deployRedisSlowLogCollection(long appId, String host, int port) {
        Assert.isTrue(appId > 0);
        Assert.hasText(host);
        Assert.isTrue(port > 0);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(ConstUtils.HOST_KEY, host);
        dataMap.put(ConstUtils.PORT_KEY, port);
        dataMap.put(ConstUtils.APP_KEY, appId);
        JobKey jobKey = JobKey.jobKey(ConstUtils.REDIS_SLOWLOG_JOB_NAME, ConstUtils.REDIS_SLOWLOG_JOB_GROUP);
        TriggerKey triggerKey = TriggerKey.triggerKey(ObjectConvert.linkIpAndPort(host, port), ConstUtils.REDIS_SLOWLOG_TRIGGER_GROUP + appId);
        boolean result = schedulerCenter.deployJobByCron(jobKey, triggerKey, dataMap, ScheduleUtil.getRedisSlowLogCron(appId), false);
        return result;
    }

    @Override
    public boolean unDeployRedisSlowLogCollection(long appId, String host, int port) {
        Assert.isTrue(appId > 0);
        Assert.hasText(host);
        Assert.isTrue(port > 0);
        TriggerKey triggerKey = TriggerKey.triggerKey(ObjectConvert.linkIpAndPort(host, port), ConstUtils.REDIS_SLOWLOG_TRIGGER_GROUP + appId);
        Trigger trigger = schedulerCenter.getTrigger(triggerKey);
        if (trigger == null) {
            return true;
        }
        return schedulerCenter.unscheduleJob(triggerKey);
    }

    @Override
    public List<InstanceSlowLog> getInstanceSlowLogByAppId(long appId) {
        try {
            return instanceSlowLogDao.getByAppId(appId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }


    @Override
    public List<InstanceSlowLog> getInstanceSlowLogByAppId(long appId, Date startDate, Date endDate) {
        try {
            return instanceSlowLogDao.search(appId, startDate, endDate);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public Map<String, Long> getInstanceSlowLogCountMapByAppId(Long appId, Date startDate, Date endDate) {
        try {
            List<Map<String, Object>> list = instanceSlowLogDao.getInstanceSlowLogCountMapByAppId(appId, startDate, endDate);
            if (CollectionUtils.isEmpty(list)) {
                return Collections.emptyMap();
            }
            Map<String, Long> resultMap = new LinkedHashMap<String, Long>();
            for (Map<String, Object> map : list) {
                long count = MapUtils.getLongValue(map, "count");
                String hostPort = MapUtils.getString(map, "hostPort");
                if (StringUtils.isNotBlank(hostPort)) {
                    resultMap.put(hostPort, count);
                }
            }
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyMap();
        }
    }

    @Override
    public Map<String, InstanceSlotModel> getClusterSlotsMap(long appId) {
    		AppDesc appDesc = appDao.getAppDescById(appId);
    		if (!TypeUtil.isRedisCluster(appDesc.getType())) {
    			return Collections.emptyMap();
    		}
		// 最终结果
		Map<String, InstanceSlotModel> resultMap = new HashMap<String, InstanceSlotModel>();

		// 找到一个�?行的节点用�?�执行cluster slots
		List<InstanceInfo> instanceList = instanceDao.getInstListByAppId(appId);
		String host = null;
		int port = 0;
		for (InstanceInfo instanceInfo : instanceList) {
			if (instanceInfo.isOffline()) {
				continue;
			}
			host = instanceInfo.getIp();
			port = instanceInfo.getPort();
			boolean isRun = isRun(appId, host, port);
			if (isRun) {
				break;
			}
		}
		if (StringUtils.isBlank(host) || port <= 0) {
			return Collections.emptyMap();
		}

		// 获�?�cluster slots
		List<Object> clusterSlotList = null;
		Jedis jedis = null;
		try {
			jedis = getJedis(appId, host, port);
			clusterSlotList = jedis.clusterSlots();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		if (clusterSlotList == null || clusterSlotList.size() == 0) {
			return Collections.emptyMap();
		}
		//clusterSlotList形如：
//		[0, 1, [[B@5caf905d, 6380], [[B@27716f4, 6379]]
//		[3, 4096, [[B@8efb846, 6380], [[B@2a84aee7, 6379]]
//		[12291, 16383, [[B@a09ee92, 6383], [[B@30f39991, 6382]]
//		[2, 2, [[B@452b3a41, 6381], [[B@4a574795, 6382]]
//		[8194, 12290, [[B@f6f4d33, 6381], [[B@23fc625e, 6382]]
//		[4097, 8193, [[B@3f99bd52, 6380], [[B@4f023edb, 6381]]

		for (Object clusterSlotObj : clusterSlotList) {
			List<Object> slotInfoList = (List<Object>) clusterSlotObj;
			if (slotInfoList.size() <= 2) {
				continue;
			}
			//获�?�slot的start到end相关
			int startSlot = ((Long) slotInfoList.get(0)).intValue();
			int endSlot = ((Long) slotInfoList.get(1)).intValue();
			String slotDistribute = getStartToEndSlotDistribute(startSlot, endSlot);
			List<Integer> slotList = getStartToEndSlotList(startSlot, endSlot);

			List<Object> masterInfoList = (List<Object>) slotInfoList.get(2);
			String tempHost = SafeEncoder.encode((byte[]) masterInfoList.get(0));
			int tempPort = ((Long) masterInfoList.get(1)).intValue();
			String hostPort = tempHost + ":" + tempPort;
			if (resultMap.containsKey(hostPort)) {
				InstanceSlotModel instanceSlotModel = resultMap.get(hostPort);
				instanceSlotModel.getSlotDistributeList().add(slotDistribute);
				instanceSlotModel.getSlotList().addAll(slotList);
			} else {
				InstanceSlotModel instanceSlotModel = new InstanceSlotModel();
				instanceSlotModel.setHost(tempHost);
				instanceSlotModel.setPort(tempPort);
				List<String> slotDistributeList = new ArrayList<String>();
				slotDistributeList.add(slotDistribute);
				instanceSlotModel.setSlotDistributeList(slotDistributeList);
				instanceSlotModel.setSlotList(slotList);
				resultMap.put(hostPort, instanceSlotModel);
			}
		}
		return resultMap;
	}

	/**
	 * 获�?�slot列表
	 * @param startSlot
	 * @param endSlot
	 * @return
	 */
	private List<Integer> getStartToEndSlotList(int startSlot, int endSlot) {
		List<Integer> slotList = new ArrayList<Integer>();
		if (startSlot == endSlot) {
			slotList.add(startSlot);
		} else {
			for (int i = startSlot; i <= endSlot; i++) {
				slotList.add(i);
			}
		}
		return slotList;
	}

	/**
     * 0,4096 0-4096
     * 2,2 2-2
     * @param slotInfo
     * @return
     */
	private String getStartToEndSlotDistribute(int startSlot, int endSlot) {
		if (startSlot == endSlot) {
			return String.valueOf(startSlot);
		} else {
			return startSlot + "-" + endSlot;
		}
    }
	
	@Override
    public String getRedisVersion(long appId, String ip, int port) {
	    Map<RedisConstant, Map<String, Object>> infoAllMap = getInfoStats(appId, ip, port);
	    if (MapUtils.isEmpty(infoAllMap)) {
	        return null;
	    }
	    Map<String, Object> serverMap = infoAllMap.get(RedisConstant.Server);
	    if (MapUtils.isEmpty(serverMap)) {
            return null;
        }
	    return MapUtils.getString(serverMap, "redis_version");
    }
	
	@Override
	public String getNodeId(long appId, String ip, int port) {
        final Jedis jedis = getJedis(appId, ip, port);
        try {
            final StringBuilder clusterNodes = new StringBuilder();
            boolean isGetNodes = new IdempotentConfirmer() {
                @Override
                public boolean execute() {
                    String nodes = jedis.clusterNodes();
                    if (nodes != null && nodes.length() > 0) {
                        clusterNodes.append(nodes);
                        return true;
                    }
                    return false;
                }
            }.run();
            if (!isGetNodes) {
                logger.error("{}:{} clusterNodes failed", jedis.getClient().getHost(), jedis.getClient().getPort());
                return null;
            }
            for (String infoLine : clusterNodes.toString().split("\n")) {
                if (infoLine.contains("myself")) {
                    String nodeId = infoLine.split(" ")[0];
                    return nodeId;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return null;
    }
	
    public void setSchedulerCenter(SchedulerCenter schedulerCenter) {
        this.schedulerCenter = schedulerCenter;
    }

    public void setInstanceStatsCenter(InstanceStatsCenter instanceStatsCenter) {
        this.instanceStatsCenter = instanceStatsCenter;
    }

    public void setAppStatsDao(AppStatsDao appStatsDao) {
        this.appStatsDao = appStatsDao;
    }

    public void setAsyncService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public void setInstanceDao(InstanceDao instanceDao) {
        this.instanceDao = instanceDao;
    }

    public void setInstanceStatsDao(InstanceStatsDao instanceStatsDao) {
        this.instanceStatsDao = instanceStatsDao;
    }

    public void setMachineCenter(MachineCenter machineCenter) {
        this.machineCenter = machineCenter;
    }

    public void setAppDao(AppDao appDao) {
        this.appDao = appDao;
    }

    public void setAppAuditLogDao(AppAuditLogDao appAuditLogDao) {
        this.appAuditLogDao = appAuditLogDao;
    }

    public void setInstanceSlowLogDao(InstanceSlowLogDao instanceSlowLogDao) {
        this.instanceSlowLogDao = instanceSlowLogDao;
    }
    
    @Override
	public Jedis getJedis(long appId, String host, int port) {
		return getJedis(appId, host, port, Protocol.DEFAULT_TIMEOUT, Protocol.DEFAULT_TIMEOUT);
	}

	@Override
	public Jedis getJedis(long appId, String host, int port, int connectionTimeout, int soTimeout) {
		AppDesc appDesc = appDao.getAppDescById(appId);
		String password = appDesc.getPassword();
		Jedis jedis = new Jedis(host, port);
		jedis.getClient().setConnectionTimeout(connectionTimeout);
		jedis.getClient().setSoTimeout(soTimeout);
		if (StringUtils.isNotBlank(password)) {
			jedis.auth(password);
		}
		return jedis;
	}

	@Override
	public Jedis getJedis(String host, int port, String password) {
		Jedis jedis = new Jedis(host, port);
		jedis.getClient().setConnectionTimeout(Protocol.DEFAULT_TIMEOUT);
		jedis.getClient().setSoTimeout(Protocol.DEFAULT_TIMEOUT);
		if (StringUtils.isNotBlank(password)) {
			jedis.auth(password);
		}
		return jedis;
	}
	
	@Override
	public Jedis getJedis(String host, int port) {
		return getJedis(host, port, null);
	}

    

	

}
