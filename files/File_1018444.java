package com.xiaolyuh.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Redis分布�?�?
 * 使用 SET resource-name anystring NX EX max-lock-time 实现
 * <p>
 * 该方案在 Redis 官方 SET 命令页有详细介�?。
 * http://doc.redisfans.com/string/set.html
 * <p>
 * 在介�?该分布�?�?设计之�?，我们先�?�看一下在从 Redis 2.6.12 开始 SET �??供的新特性，
 * 命令 SET key value [EX seconds] [PX milliseconds] [NX|XX]，其中：
 * <p>
 * EX seconds — 以秒为�?��?设置 key 的过期时间；
 * PX milliseconds — 以毫秒为�?��?设置 key 的过期时间；
 * NX — 将key 的值设为value ，当且仅当key �?存在，等效于 SETNX。
 * XX — 将key 的值设为value ，当且仅当key 存在，等效于 SETEX。
 * <p>
 * 命令 SET resource-name anystring NX EX max-lock-time 是一�?在 Redis 中实现�?的简�?�方法。
 * <p>
 * 客户端执行以上的命令：
 * <p>
 * 如果�?务器返回 OK ，那么这个客户端获得�?。
 * 如果�?务器返回 NIL ，那么客户端获�?��?失败，�?�以在�?�?��?�?试。
 *
 * @author yuhao.wangwang
 * @version 1.0
 * @date 2017年11月3日 上�?�10:21:27
 */
public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 将key 的值设为value ，当且仅当key �?存在，等效于 SETNX。
     */
    public static final String NX = "NX";

    /**
     * seconds — 以秒为�?��?设置 key 的过期时间，等效于EXPIRE key seconds
     */
    public static final String EX = "EX";

    /**
     * 调用set�?�的返回值
     */
    public static final String OK = "OK";

    /**
     * 默认请求�?的超时时间(ms 毫秒)
     */
    private static final long TIME_OUT = 100;

    /**
     * 默认�?的有效时间(s)
     */
    public static final int EXPIRE = 60;

    /**
     * 解�?的lua脚本
     */
    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    /**
     * �?标志对应的key
     */
    private String lockKey;

    /**
     * 记录到日志的�?标志对应的key
     */
    private String lockKeyLog = "";

    /**
     * �?对应的值
     */
    private String lockValue;

    /**
     * �?的有效时间(s)
     */
    private int expireTime = EXPIRE;

    /**
     * 请求�?的超时时间(ms)
     */
    private long timeOut = TIME_OUT;

    /**
     * �?标记
     */
    private volatile boolean locked = false;

    final Random random = new Random();

    /**
     * 使用默认的�?过期时间和请求�?的超时时间
     *
     * @param redisTemplate
     * @param lockKey       �?的key（Redis的Key）
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_lock";
    }

    /**
     * 使用默认的请求�?的超时时间，指定�?的过期时间
     *
     * @param redisTemplate
     * @param lockKey       �?的key（Redis的Key）
     * @param expireTime    �?的过期时间(�?��?：秒)
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int expireTime) {
        this(redisTemplate, lockKey);
        this.expireTime = expireTime;
    }

    /**
     * 使用默认的�?的过期时间，指定请求�?的超时时间
     *
     * @param redisTemplate
     * @param lockKey       �?的key（Redis的Key）
     * @param timeOut       请求�?的超时时间(�?��?：毫秒)
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, long timeOut) {
        this(redisTemplate, lockKey);
        this.timeOut = timeOut;
    }

    /**
     * �?的过期时间和请求�?的超时时间都是用指定的值
     *
     * @param redisTemplate
     * @param lockKey       �?的key（Redis的Key）
     * @param expireTime    �?的过期时间(�?��?：秒)
     * @param timeOut       请求�?的超时时间(�?��?：毫秒)
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int expireTime, long timeOut) {
        this(redisTemplate, lockKey, expireTime);
        this.timeOut = timeOut;
    }

    /**
     * �?试获�?��? 超时返回
     *
     * @return
     */
    public boolean tryLock() {
        // 生�?�?机key
        lockValue = UUID.randomUUID().toString();
        // 请求�?超时时间，纳秒
        long timeout = timeOut * 1000000;
        // 系统当�?时间，纳秒
        long nowTime = System.nanoTime();
        while ((System.nanoTime() - nowTime) < timeout) {
            if (OK.equalsIgnoreCase(this.set(lockKey, lockValue, expireTime))) {
                locked = true;
                // 上�?�?功结�?�请求
                return locked;
            }

            // �?次请求等待一段时间
            seleep(10, 50000);
        }
        return locked;
    }

    /**
     * �?试获�?��? 立�?�返回
     *
     * @return 是�?��?功获得�?
     */
    public boolean lock() {
        lockValue = UUID.randomUUID().toString();
        //�?存在则添加 且设置过期时间（�?��?ms）
        String result = set(lockKey, lockValue, expireTime);
        locked = OK.equalsIgnoreCase(result);
        return locked;
    }

    /**
     * 以阻塞方�?的获�?��?
     *
     * @return 是�?��?功获得�?
     */
    public boolean lockBlock() {
        lockValue = UUID.randomUUID().toString();
        while (true) {
            //�?存在则添加 且设置过期时间（�?��?ms）
            String result = set(lockKey, lockValue, expireTime);
            if (OK.equalsIgnoreCase(result)) {
                locked = true;
                return locked;
            }

            // �?次请求等待一段时间
            seleep(10, 50000);
        }
    }

    /**
     * 解�?
     * <p>
     * �?�以通过以下修改，让这个�?实现更�?�壮：
     * <p>
     * �?使用固定的字符串作为键的值，而是设置一个�?�?�猜测（non-guessable）的长�?机字符串，作为�?�令串（token）。
     * �?使用 DEL 命令�?�释放�?，而是�?��?一个 Lua 脚本，这个脚本�?�在客户端传入的值和键的�?�令串相匹�?时，�?对键进行删除。
     * 这两个改动�?�以防止�?有过期�?的客户端误删现有�?的情况出现。
     */
    public Boolean unlock() {
        // �?�有加�?�?功并且�?还有效�?去释放�?
        // �?�有加�?�?功并且�?还有效�?去释放�?
        if (locked) {
            return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    Object nativeConnection = connection.getNativeConnection();
                    Long result = 0L;

                    List<String> keys = new ArrayList<>();
                    keys.add(lockKey);
                    List<String> values = new ArrayList<>();
                    values.add(lockValue);

                    // 集群模�?
                    if (nativeConnection instanceof JedisCluster) {
                        result = (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, values);
                    }

                    // �?�机模�?
                    if (nativeConnection instanceof Jedis) {
                        result = (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, values);
                    }

                    if (result == 0 && !StringUtils.isEmpty(lockKeyLog)) {
                        logger.info("Redis分布�?�?，解�?{}失败�?解�?时间：{}", lockKeyLog, System.currentTimeMillis());
                    }

                    locked = result == 0;
                    return result == 1;
                }
            });
        }

        return true;
    }

    /**
     * 获�?��?状�?
     * @Title: isLock
     * @Description: TODO
     * @return  
     * @author yuhao.wang
     */
    public boolean isLock() {
		
		return locked;
	}
    
    /**
     * �?写redisTemplate的set方法
     * <p>
     * 命令 SET resource-name anystring NX EX max-lock-time 是一�?在 Redis 中实现�?的简�?�方法。
     * <p>
     * 客户端执行以上的命令：
     * <p>
     * 如果�?务器返回 OK ，那么这个客户端获得�?。
     * 如果�?务器返回 NIL ，那么客户端获�?��?失败，�?�以在�?�?��?�?试。
     *
     * @param key     �?的Key
     * @param value   �?里�?�的值
     * @param seconds 过去时间（秒）
     * @return
     */
    private String set(final String key, final String value, final long seconds) {
        Assert.isTrue(!StringUtils.isEmpty(key), "key�?能为空");
        return (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                String result = null;
                if (nativeConnection instanceof JedisCommands) {
                    result = ((JedisCommands) nativeConnection).set(key, value, NX, EX, seconds);
                }

                if (!StringUtils.isEmpty(lockKeyLog) && !StringUtils.isEmpty(result)) {
                    logger.info("获�?��?{}的时间：{}", lockKeyLog, System.currentTimeMillis());
                }

                return result;
            }
        });
    }

    /**
     * @param millis 毫秒
     * @param nanos  纳秒
     * @Title: seleep
     * @Description: 线程等待时间
     * @author yuhao.wang
     */
    private void seleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, random.nextInt(nanos));
        } catch (InterruptedException e) {
            logger.info("获�?�分布�?�?休眠被中断：", e);
        }
    }

    public String getLockKeyLog() {
        return lockKeyLog;
    }

    public void setLockKeyLog(String lockKeyLog) {
        this.lockKeyLog = lockKeyLog;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

}
