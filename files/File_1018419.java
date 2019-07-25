package com.xiaolyuh.redis.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis分布�?�?（�?�能死�?）
 * <p>
 * 执行步骤
 * 1. setnx(lockkey, 1)  如果返回0，则说明�?��?失败；如果返回1，则说明�?��?�?功
 * <p>
 * 2 . expire()命令对lockkey设置超时时间，为的是�?��?死�?问题。
 * <p>
 * 3. 执行完业务代�?�?�，�?�以通过delete命令删除key。
 * <p>
 * 这个方案其实是�?�以解决日常工作中的需求的，但从技术方案的探讨上�?�说，�?�能还有一些�?�以完善的地方。
 * 比如，如果在第一步setnx执行�?功�?�，在expire()命令执行�?功�?，�?�生了宕机的现象，
 * 那么就�?然会出现死�?的问题，所以如果�?对其进行完善的�?，
 * �?�以使用redis的setnx()�?get()和getset()方法�?�实现分布�?�?。
 *
 * @author xin.yuan
 * @version 1.0
 * @date 2017年11月3日 上�?�10:21:27
 */
public class RedisLock {
    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    //////////////////// �?��?常�?定义开始///////////////////////
    /**
     * 存储到redis中的�?标志
     */
    private static final String LOCKED = "LOCKED";

    /**
     * 默认请求�?的超时时间(ms 毫秒)
     */
    private static final long TIME_OUT = 100;

    /**
     * 默认�?的有效时间(s)
     */
    public static final int EXPIRE = 60;
    //////////////////// �?��?常�?定义结�?�///////////////////////

    /**
     * �?标志对应的key
     */
    private String key;

    /**
     * �?的有效时间(s)
     */
    private int expireTime = EXPIRE;

    /**
     * 请求�?的超时时间(ms)
     */
    private long timeOut = TIME_OUT;

    /**
     * �?flag
     */
    private volatile boolean isLocked = false;
    /**
     * Redis管�?�模�?�
     */
    private StringRedisTemplate redisTemplate;

    /**
     * 构造方法
     *
     * @param redisTemplate Redis管�?�模�?�
     * @param key           �?定key
     * @param expireTime    �?过期时间 （秒）
     * @param timeOut       请求�?超时时间 (毫秒)
     */
    public RedisLock(StringRedisTemplate redisTemplate, String key, int expireTime, long timeOut) {
        this.key = key;
        this.expireTime = expireTime;
        this.timeOut = timeOut;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 构造方法
     *
     * @param redisTemplate Redis管�?�模�?�
     * @param key           �?定key
     * @param expireTime    �?过期时间
     */
    public RedisLock(StringRedisTemplate redisTemplate, String key, int expireTime) {
        this.key = key;
        this.expireTime = expireTime;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 构造方法(默认请求�?超时时间30秒，�?过期时间60秒)
     *
     * @param redisTemplate Redis管�?�模�?�
     * @param key           �?定key
     */
    public RedisLock(StringRedisTemplate redisTemplate, String key) {
        this.key = key;
        this.redisTemplate = redisTemplate;
    }

    public boolean lock() {
        // 系统当�?时间，纳秒
        long nowTime = System.nanoTime();
        // 请求�?超时时间，纳秒
        long timeout = timeOut * 1000000;
        final Random random = new Random();

        // �?断循环�?�Master节点请求�?，当请求时间(System.nanoTime() - nano)超过设定的超时时间则放弃请求�?
        // 这个�?�以防止一个客户端在�?个宕掉的master节点上阻塞过长时间
        // 如果一个master节点�?�?�用了，应该尽快�?试下一个master节点
        while ((System.nanoTime() - nowTime) < timeout) {
            // 将�?作为key存储到redis缓存中，存储�?功则获得�?
            if (redisTemplate.opsForValue().setIfAbsent(key, LOCKED)) {
                isLocked = true;
                // 设置�?的有效期，也是�?的自动释放时间，也是一个客户端在其他客户端能抢�?��?之�?�?�以执行任务的时间
                // �?�以防止因异常情况无法释放�?而造�?死�?情况的�?�生
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

                // 上�?�?功结�?�请求
                break;
            }
            // 获�?��?失败时，应该在�?机延时�?�进行�?试，�?��?�?�?�客户端�?�时�?试导致�?都无法拿到�?的情况出现
            // �?�眠10毫秒�?�继续请求�?
            try {
                Thread.sleep(10, random.nextInt(50000));
            } catch (InterruptedException e) {
                logger.error("获�?�分布�?�?休眠被中断：", e);
            }
        }
        return isLocked;

    }

    public boolean isLock() {

        return redisTemplate.hasKey(key);
    }

    public void unlock() {
        // 释放�?
        // �?管请求�?是�?��?功，�?��?已�?上�?，客户端都会进行释放�?的�?作
        if (isLocked) {
            redisTemplate.delete(key);
        }
    }

}
