package com.sohu.cache.stats.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

import com.sohu.cache.constant.ImportAppResult;
import com.sohu.cache.constant.InstanceStatusEnum;
import com.sohu.cache.dao.InstanceDao;
import com.sohu.cache.dao.InstanceStatsDao;
import com.sohu.cache.entity.AppDesc;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.entity.InstanceStats;
import com.sohu.cache.entity.MachineInfo;
import com.sohu.cache.machine.MachineCenter;
import com.sohu.cache.redis.RedisCenter;
import com.sohu.cache.stats.app.ImportAppCenter;
import com.sohu.cache.util.ConstUtils;
import com.sohu.cache.util.IdempotentConfirmer;
import com.sohu.cache.web.service.AppService;

/**
 * 导入应用
 * 
 * @author leifu
 * @Date 2016-4-16
 * @Time 下�?�3:42:49
 */
public class ImportAppCenterImpl implements ImportAppCenter {

    private Logger logger = LoggerFactory.getLogger(ImportAppCenterImpl.class);

    private AppService appService;

    private RedisCenter redisCenter;

    private MachineCenter machineCenter;

    private InstanceDao instanceDao;
    
    private InstanceStatsDao instanceStatsDao;
    
    @Override
    public ImportAppResult check(AppDesc appDesc, String appInstanceInfo) {
        // 1.检查是�?�应用信�?�为空
        if (appDesc == null) {
            return ImportAppResult.fail("应用信�?�为空");
        }
        // 2.检查应用�??是�?��?�?
        String appName = appDesc.getName();
        AppDesc existAppDesc = appService.getAppByName(appName);
        if (existAppDesc != null) {
            return ImportAppResult.fail(appName + ", 应用�??�?�?");
        }
        // 3.实例信�?�是�?�为空
        if (StringUtils.isBlank(appInstanceInfo)) {
            return ImportAppResult.fail("实例详情为空");
        }

        String[] appInstanceDetails = appInstanceInfo.split("\n");

        // 4.检查实例信�?�格�?是�?�正确
        for (String appInstance : appInstanceDetails) {
            if (StringUtils.isBlank(appInstance)) {
                return ImportAppResult.fail("应用实例信�?�有空行");
            }
            String[] instanceItems = appInstance.split(":");
            if (instanceItems.length != 3) {
                return ImportAppResult.fail("应用实例信�?�" + appInstance + "格�?错误，必须有2个冒�?�");
            }
            String ip = instanceItems[0];
            // 4.1.检查ip对应的机器是�?�存在
            try {
                MachineInfo machineInfo = machineCenter.getMachineInfoByIp(ip);
                if (machineInfo == null) {
                    return ImportAppResult.fail(appInstance + "中的ip�?存在");
                } else if (machineInfo.isOffline()) {
                    return ImportAppResult.fail(appInstance + "中的ip已�?被删除");
                }
            } catch (Exception e) {
                return ImportAppResult.fail(appInstance + "中的ip�?存在");
            }
            // 4.2.检查端�?�是�?�为整数
            String portStr = instanceItems[1];
            boolean portIsDigit = NumberUtils.isDigits(portStr);
            if (!portIsDigit) {
                return ImportAppResult.fail(appInstance + "中的port�?是整数");
            }

            int port = NumberUtils.toInt(portStr);
            // 4.3.检查ip:port是�?�已�?在instance_info表和instance_statistics中
            int count = instanceDao.getCountByIpAndPort(ip, port);
            if (count > 0) {
                return ImportAppResult.fail(appInstance + "中ip:port已�?在instance_info存在");
            }
            InstanceStats instanceStats = instanceStatsDao.getInstanceStatsByHost(ip, port);
            if (instanceStats != null) { 
                return ImportAppResult.fail(appInstance + "中ip:port已�?在instance_statistics存在");
            }
            // 4.4.检查Redis实例是�?�存活
            String memoryOrMasterName = instanceItems[2];
            int memoryOrMasterNameInt = NumberUtils.toInt(memoryOrMasterName);
            boolean isRun;
            if (memoryOrMasterNameInt > 0) {
            		isRun = redisCenter.isRun(ip, port, appDesc.getPassword());
            } else {
            		isRun = redisCenter.isRun(ip, port);
            }
            if (!isRun) {
                return ImportAppResult.fail(appInstance + "中的节点�?是存活的");
            }

            // 4.5.检查内存是�?�为整数
            boolean isSentinelNode = memoryOrMasterNameInt <= 0;
            if (isSentinelNode) {
                // 4.5.1 sentinel节点masterName判断
                if (StringUtils.isEmpty(memoryOrMasterName)) {
                    return ImportAppResult.fail(appInstance + "中的sentinel节点master为空");
                }
                // 判断masterName
                String masterName = getSentinelMasterName(ip, port);
                if (StringUtils.isEmpty(masterName) || !memoryOrMasterName.equals(masterName)) {
                    return ImportAppResult.fail(ip + ":" + port + ", masterName:" + masterName + "与所填"
                            + memoryOrMasterName + "�?一致");
                }
            } else {
                // 4.5.2 内存必须是整数
                boolean maxMemoryIsDigit = NumberUtils.isDigits(memoryOrMasterName);
                if (!maxMemoryIsDigit) {
                    return ImportAppResult.fail(appInstance + "中的maxmemory�?是整数");
                }
            }
        }
        
        // 5. 节点之间关系是�?�正确，这个比较麻烦，还是�?赖于用户填写的正确性。

        return ImportAppResult.success();
    }


    @Override
    public boolean importAppAndInstance(AppDesc appDesc, String appInstanceInfo) {
        boolean isSuccess = true;
        try {
            // 1.1 �?存应用信�?�
            appService.save(appDesc);
            long appId = appDesc.getAppId();
            // 1.2 更新appKey
            appService.updateAppKey(appId);

            int type = appDesc.getType();
            // 2.�?存应用和用户的关系
            appService.saveAppToUser(appId, appDesc.getUserId());
            // 3.�?存实例信�?�并开�?�统计
            String[] appInstanceDetails = appInstanceInfo.split("\n");
            // 4.检查实例信�?�格�?是�?�正确
            for (String appInstance : appInstanceDetails) {
                String[] instanceItems = appInstance.split(":");
                String host = instanceItems[0];
                int port = NumberUtils.toInt(instanceItems[1]);

                String memoryOrMasterName = instanceItems[2];
                boolean isSentinelNode = NumberUtils.toInt(memoryOrMasterName) <= 0;
                if (isSentinelNode) {
                    saveInstance(appId, host, port, 0, ConstUtils.CACHE_REDIS_SENTINEL, memoryOrMasterName);
                } else {
                    if (ConstUtils.CACHE_REDIS_STANDALONE == type || ConstUtils.CACHE_REDIS_SENTINEL == type) {
                        saveInstance(appId, host, port, NumberUtils.toInt(memoryOrMasterName), ConstUtils.CACHE_REDIS_STANDALONE, "");
                    } else if (ConstUtils.CACHE_TYPE_REDIS_CLUSTER == type) {
                        saveInstance(appId, host, port, NumberUtils.toInt(memoryOrMasterName), ConstUtils.CACHE_TYPE_REDIS_CLUSTER, "");
                    }
                    //deploy quartz
                    redisCenter.deployRedisCollection(appId, host, port);
                    redisCenter.deployRedisSlowLogCollection(appId, host, port);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            isSuccess = false;
        }
        return isSuccess;
    }
    
    /**
     * 获�?�sentinel的masterName
     * @param ip
     * @param port
     * @return
     */
    private String getSentinelMasterName(final String ip, final int port) {
        final StringBuilder masterName = new StringBuilder();
        new IdempotentConfirmer() {
            private int timeOutFactor = 1;
            @Override
            public boolean execute() {
                Jedis jedis = null;
                try {
                    // 预留
                    String password = null;
                    jedis = redisCenter.getJedis(ip, port, password);
                    jedis.getClient().setConnectionTimeout(Protocol.DEFAULT_TIMEOUT * (timeOutFactor++));
                    jedis.getClient().setSoTimeout(Protocol.DEFAULT_TIMEOUT * (timeOutFactor++));
                    List<Map<String, String>> mapList = jedis.sentinelMasters();
                    String targetKey = "name";
                    for (Map<String, String> map : mapList) {
                        if (map.containsKey(targetKey)) {
                            masterName.append(MapUtils.getString(map, targetKey, ""));
                        }
                    }
                    return true;
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
        return masterName.toString();
    }

    /**
     * �?存实例信�?�
     * @param appId
     * @param host
     * @param port
     * @param maxMemory
     * @param type
     * @param cmd
     * @return
     */
    private InstanceInfo saveInstance(long appId, String host, int port, int maxMemory, int type,
            String cmd) {
        InstanceInfo instanceInfo = new InstanceInfo();
        instanceInfo.setAppId(appId);
        MachineInfo machineInfo = machineCenter.getMachineInfoByIp(host);
        instanceInfo.setHostId(machineInfo.getId());
        instanceInfo.setConn(0);
        instanceInfo.setMem(maxMemory);
        instanceInfo.setStatus(InstanceStatusEnum.GOOD_STATUS.getStatus());
        instanceInfo.setPort(port);
        instanceInfo.setType(type);
        instanceInfo.setCmd(cmd);
        instanceInfo.setIp(host);
        instanceDao.saveInstance(instanceInfo);
        return instanceInfo;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public void setRedisCenter(RedisCenter redisCenter) {
        this.redisCenter = redisCenter;
    }

    public void setMachineCenter(MachineCenter machineCenter) {
        this.machineCenter = machineCenter;
    }

    public void setInstanceDao(InstanceDao instanceDao) {
        this.instanceDao = instanceDao;
    }


    public void setInstanceStatsDao(InstanceStatsDao instanceStatsDao) {
        this.instanceStatsDao = instanceStatsDao;
    }

}
