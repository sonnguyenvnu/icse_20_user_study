package com.sohu.cache.machine;

import com.sohu.cache.constant.EmptyObjectConstant;
import com.sohu.cache.constant.SymbolConstant;
import com.sohu.cache.exception.SSHException;
import com.sohu.cache.ssh.SSHUtil;
import com.sohu.cache.util.ConstUtils;
import com.google.common.util.concurrent.AtomicLongMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生�?一个redis�?�用端�?�
 *
 * @author: lingguo
 * @time: 2014/8/25 20:57
 */
public class PortGenerator {
    private static Logger logger = LoggerFactory.getLogger(PortGenerator.class);
    /**
     * redis port常�?
     */
    private static final Integer REDIS_START_PORT = 6379;
    private static AtomicLongMap<String> redisPortHolder = AtomicLongMap.create();

    /**
     * 返回一个redis的�?�用端�?�：
     *  - 1. 通过shell查询redis当�?已用的最大port；
     *  - 2. 为什么�?�步：防止多线程访问时获�?�到�?�样的端�?�；
     *  - 3. 为什么还用原�?计数：连续两次调用时，如果进程还没�?�动，则拿到的�?然是相�?�的端�?�；
     *
     * @param ip
     * @return
     */
    public static synchronized Integer getRedisPort(final String ip) {
        if (redisPortHolder.get(ip) == 0L) {
            redisPortHolder.put(ip, REDIS_START_PORT);
        }
        String maxPortStr = "";
        try {
            int sshPort = SSHUtil.getSshPort(ip);
            maxPortStr = getMaxPortStr(ip, sshPort);
        } catch (SSHException e) {
            logger.error("cannot get max port of redis by ssh, ip: {}", ip, e);
        }

        if (StringUtils.isBlank(maxPortStr) || !StringUtils.isNumeric(maxPortStr)) {
            logger.warn("{} the max port of redis is invalid, maxPortStr: {}", ip, maxPortStr);
            return new Long(redisPortHolder.getAndIncrement(ip)).intValue();
        }

        int availablePort = Integer.valueOf(maxPortStr) + 1;
        // 兼容连续调用的情况
        if (availablePort < redisPortHolder.get(ip)) {
            availablePort = new Long(redisPortHolder.getAndIncrement(ip)).intValue();
        } else {    // 正常情况，以�?�兼容系统�?�?�和当�?端�?��?�?�用的情形
            redisPortHolder.put(ip, availablePort + 1);
        }

        try {
            while (SSHUtil.isPortUsed(ip, availablePort)) {
                availablePort++;
            }
        } catch (SSHException e) {
            logger.error("check port error, ip: {}, port: {}", ip, availablePort, e);
        }
        redisPortHolder.put(ip, availablePort+1);
        return availablePort;
    }

    @Deprecated
    public static String getMaxPortStrOld(String ip, int sshPort) throws SSHException {
        String redisPidCmd = "ps -ef | grep redis | grep -v 'grep' |  awk -F '*:' '{print $2}' " +
                " | awk -F ' ' '{print $1}' | sort -r | head -1";
        return SSHUtil.execute(ip, sshPort, ConstUtils.USERNAME, ConstUtils.PASSWORD, redisPidCmd);
    }
    
    /**
     * 直接解�?ps -ef | grep redis | grep -v 'grep'
     * @param ip
     * @param sshPort
     * @return
     * @throws SSHException
     */
     public static String getMaxPortStr(String ip, int sshPort) throws SSHException {
        String redisPidCmd = "ps -ef | grep redis | grep -v 'grep'";
        String redisProcessStr = SSHUtil.execute(ip, sshPort, ConstUtils.USERNAME, ConstUtils.PASSWORD, redisPidCmd);
        if (StringUtils.isBlank(redisProcessStr)) {
            return EmptyObjectConstant.EMPTY_STRING;
        }
        int maxPort = 0;
        String[] lines = redisProcessStr.split(SymbolConstant.ENTER);
        for (String line : lines) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            int redisServerIndex = line.indexOf("redis-server");
            int redisSentinelIndex = line.indexOf("redis-sentinel");
            if (redisServerIndex >= 0) {
                line = line.substring(redisServerIndex);
            }
            if (redisSentinelIndex >= 0) {
                line = line.substring(redisSentinelIndex);
            }
            if (redisServerIndex < 0 && redisSentinelIndex < 0) {
                continue;
            }
            String[] items = line.split(SymbolConstant.SPACE);
            if (items.length >= 2) {
                String hostPort = items[1];
                if (StringUtils.isBlank(hostPort)) {
                    continue;
                }
                String[] hostPortArr = hostPort.split(SymbolConstant.COLON);
                if (hostPortArr.length != 2) {
                    continue;
                }
                String portStr = hostPortArr[1];
                if (!NumberUtils.isDigits(portStr)) {
                    continue;
                }
                int port = NumberUtils.toInt(portStr);
                if (port > maxPort) {
                    maxPort = port;
                }
            }
        }
        return maxPort == 0 ? EmptyObjectConstant.EMPTY_STRING : String.valueOf(maxPort);
    }

}
