package com.sohu.tv.builder;

import com.alibaba.fastjson.JSONObject;
import com.sohu.tv.cachecloud.client.basic.util.ConstUtils;
import com.sohu.tv.cachecloud.client.basic.util.HttpUtils;
import com.sohu.tv.cachecloud.client.basic.util.StringUtil;
import com.sohu.tv.cachecloud.client.jedis.stat.ClientDataCollectReportExecutor;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 构造redis�?�机的builder；
 *
 * @author: lingguo
 * @time: 2014/9/23 17:42
 */
public class RedisStandaloneBuilder {
    private Logger logger = LoggerFactory.getLogger(RedisStandaloneBuilder.class);

    private static final Lock LOCK = new ReentrantLock();
    private volatile JedisPool jedisPool;
    private GenericObjectPoolConfig poolConfig;
    private final long appId;
    private int timeout = Protocol.DEFAULT_TIMEOUT;
    /**
     * 是�?�开�?�统计
     */
    private boolean clientStatIsOpen = true;

    /**
     * 构造函数package访问域，package外直接构造实例；
     *
     * @param appId
     */
    RedisStandaloneBuilder(final long appId) {
        this.appId = appId;
        poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 3);
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 2);
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE);
        poolConfig.setJmxEnabled(true);
        poolConfig.setJmxNamePrefix("jedis-pool");
    }

    public JedisPool build() {
        if (jedisPool == null) {
            while (true) {
                try {
                    LOCK.tryLock(100, TimeUnit.MILLISECONDS);
                    if (jedisPool == null) {
                        /**
                         * 心跳返回的请求为空；
                         */
                        String response = HttpUtils.doGet(String.format(ConstUtils.REDIS_STANDALONE_URL, appId));
                        if (response == null || response.isEmpty()) {
                            logger.warn("cannot get response from server, appId={}. continue...", appId);
                            continue;
                        }
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = JSONObject.parseObject(response);
                        } catch (Exception e) {
                            logger.error("read json from response error, appId: {}.", appId, e);
                        }
                        if (jsonObject == null) {
                            logger.warn("invalid response, appId: {}. continue...", appId);
                            continue;
                        }
                        /**
                         * 从心跳中�??�?�HostAndPort，构造JedisPool实例；
                         */
                        String instance = jsonObject.getString("standalone");
                        String[] instanceArr = instance.split(":");
                        if (instanceArr.length != 2) {
                            logger.warn("instance info is invalid, instance: {}, appId: {}, continue...", instance, appId);
                            continue;
                        }
                        
                        //收集上报数�?�
                        if (clientStatIsOpen) {
                            ClientDataCollectReportExecutor.getInstance();
                        }
                        
                        String password = jsonObject.getString("password");
                        if (StringUtil.isBlank(password)) {
                            jedisPool = new JedisPool(poolConfig, instanceArr[0], Integer.valueOf(instanceArr[1]), timeout);
                        } else {
                            jedisPool = new JedisPool(poolConfig, instanceArr[0], Integer.valueOf(instanceArr[1]), timeout, password);
                        }

                        return jedisPool;
                    }
                } catch (InterruptedException e) {
                    logger.error("error in build().", e);
                }
            }
        }
        return jedisPool;
    }

    /**
     * �?置
     *
     * @param poolConfig
     * @return
     */
    public RedisStandaloneBuilder setPoolConfig(GenericObjectPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
        return this;
    }

    /**
     *
     * @param timeout �?��?:毫秒
     * @return
     */
    public RedisStandaloneBuilder setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }
    
    /**
     * 是�?�开�?�统计
     * @param clientStatIsOpen
     * @return
     */
    public RedisStandaloneBuilder setClientStatIsOpen(boolean clientStatIsOpen) {
        this.clientStatIsOpen = clientStatIsOpen;
        return this;
    }
}
