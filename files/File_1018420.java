package com.xiaolyuh.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Redis分布�?�?（这�?方�?�?务器时间一定�?�?�步，�?�则会出问题）
 * <p>
 * 执行步骤
 * 1. setnx(lockkey, 当�?时间+过期超时时间) ，如果返回1，则获�?��?�?功；如果返回0则没有获�?�到�?，转�?�2。
 * <p>
 * 2. get(lockkey)获�?�值oldExpireTime ，并将这个value值与当�?的系统时间进行比较，如果�?于当�?系统时间，则认为这个�?已�?超时，�?�以�?许别的请求�?新获�?�，转�?�3。
 * <p>
 * 3. 计算newExpireTime=当�?时间+过期超时时间，然�?�getset(lockkey, newExpireTime) 会返回当�?lockkey的值currentExpireTime。
 * <p>
 * 4. 判断currentExpireTime与oldExpireTime 是�?�相等，如果相等，说明当�?getset设置�?功，获�?�到了�?。如果�?相等，说明这个�?�?�被别的请求获�?�走了，那么当�?请求�?�以直接返回失败，或者继续�?试。
 * <p>
 * 5. 在获�?�到�?之�?�，当�?线程�?�以开始自己的业务处�?�，当处�?�完毕�?�，比较自己的处�?�时间和对于�?设置的超时时间，如果�?于�?设置的超时时间，则直接执行delete释放�?；如果大于�?设置的超时时间，则�?需�?�?�?进行处�?�。
 *
 * @author yuhao.wangwang
 * @version 1.0
 * @date 2017年11月3日 上�?�10:21:27
 */
public class RedisLock2 {

    /**
     * 默认请求�?的超时时间(ms 毫秒)
     */
    private static final long TIME_OUT = 100;

    /**
     * 默认�?的有效时间(s)
     */
    public static final int EXPIRE = 60;

    private static Logger logger = LoggerFactory.getLogger(RedisLock2.class);

    private StringRedisTemplate redisTemplate;

    /**
     * �?标志对应的key
     */
    private String lockKey;
    /**
     * �?的有效时间(s)
     */
    private int expireTime = EXPIRE;

    /**
     * 请求�?的超时时间(ms)
     */
    private long timeOut = TIME_OUT;

    /**
     * �?的有效时间
     */
    private long expires = 0;

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
    public RedisLock2(StringRedisTemplate redisTemplate, String lockKey) {
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
    public RedisLock2(StringRedisTemplate redisTemplate, String lockKey, int expireTime) {
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
    public RedisLock2(StringRedisTemplate redisTemplate, String lockKey, long timeOut) {
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
    public RedisLock2(StringRedisTemplate redisTemplate, String lockKey, int expireTime, long timeOut) {
        this(redisTemplate, lockKey, expireTime);
        this.timeOut = timeOut;
    }

    /**
     * @return 获�?��?的key
     */
    public String getLockKey() {
        return lockKey;
    }

    /**
     * 获得 lock.
     * 实现�?路: 主�?是使用了redis 的setnx命令,缓存了�?.
     * reids缓存的key是�?的key,所有的共享, value是�?的到期时间(注�?:这里把过期时间放在value了,没有时间上设置其超时时间)
     * 执行过程:
     * 1.通过setnx�?试设置�?个key的值,�?功(当�?没有这个�?)则返回,�?功获得�?
     * 2.�?已�?存在则获�?��?的到期时间,和当�?时间比较,超时的�?,则设置新的值
     *
     * @return true if lock is acquired, false acquire timeouted
     * @throws InterruptedException in case of thread interruption
     */
    public boolean lock() {
        // 请求�?超时时间，纳秒
        long timeout = timeOut * 1000000;
        // 系统当�?时间，纳秒
        long nowTime = System.nanoTime();

        while ((System.nanoTime() - nowTime) < timeout) {
            // 分布�?�?务器有时差，这里给1秒的误差值
            expires = System.currentTimeMillis() + expireTime * 1000 + 1 * 1000;
            String expiresStr = String.valueOf(expires); //�?到期时间

            if (redisTemplate.opsForValue().setIfAbsent(lockKey, expiresStr)) {
                locked = true;
                // 设置�?的有效期，也是�?的自动释放时间，也是一个客户端在其他客户端能抢�?��?之�?�?�以执行任务的时间
                // �?�以防止因异常情况无法释放�?而造�?死�?情况的�?�生
                redisTemplate.expire(lockKey, expireTime, TimeUnit.SECONDS);

                // 上�?�?功结�?�请求
                return true;
            }

            String currentValueStr = redisTemplate.opsForValue().get(lockKey); //redis里的时间
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                //判断是�?�为空，�?为空的情况下，如果被其他线程设置了值，则第二个�?�件判断是过�?去的
                // lock is expired

                String oldValueStr = redisTemplate.opsForValue().getAndSet(lockKey, expiresStr);
                //获�?�上一个�?到期时间，并设置现在的�?到期时间，
                //�?�有一个线程�?能获�?�上一个线上的设置时间，因为jedis.getSet是�?�步的
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    //防止误删（覆盖，因为key是相�?�的）了他人的�?——这里达�?到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以�?�以接�?�

                    //[分布�?的情况下]:如过这个时候，多个线程�?�好都到了这里，但是�?�有一个线程的设置值和当�?值相�?�，他�?有�?�利获�?��?
                    // lock acquired
                    locked = true;
                    return true;
                }
            }

            /*
                延迟10 毫秒,  这里使用�?机时间�?�能会好一点,�?�以防止饥饿进程的出现,�?�,当�?�时到达多个进程,
                �?�会有一个进程获得�?,其他的都用�?�样的频率进行�?试,�?��?�有�?�了一些进行,也以�?�样的频率申请�?,这将�?�能导致�?�?��?�的�?得�?到满足.
                使用�?机的等待时间�?�以一定程度上�?�?公平性
             */
            try {
                Thread.sleep(10, random.nextInt(50000));
            } catch (InterruptedException e) {
                logger.error("获�?�分布�?�?休眠被中断：", e);
            }

        }
        return locked;
    }


    /**
     * 解�?
     */
    public void unlock() {
        // �?�有加�?�?功并且�?还有效�?去释放�?
        if (locked && expires > System.currentTimeMillis()) {
            redisTemplate.delete(lockKey);
            locked = false;
        }
    }

}
