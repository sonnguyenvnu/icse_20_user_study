package com.xxl.sso.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Redis client base on jedis
 *
 * @author xuxueli 2015-7-10 18:34:07
 */
public class JedisUtil {
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    /**
     * redis address, like "{ip}"�?"{ip}:{port}"�?"{redis/rediss}://xxl-sso:{password}@{ip}:{port:6379}/{db}"；Multiple "," separated
     */
    private static String address;

    public static void init(String address) {
        JedisUtil.address = address;

        getInstance();
    }

    // ------------------------ ShardedJedisPool ------------------------
    /**
     *  方�?01: Redis�?�节点 + Jedis�?�例 : Redis�?�节点压力过�?, Jedis�?�例存在并�?�瓶颈 》》�?�?�用于线上
     *      new Jedis("127.0.0.1", 6379).get("cache_key");
     *  方�?02: Redis�?�节点 + JedisPool�?�节点连接池 》》 Redis�?�节点压力过�?，负载和容�?�比较差
     *      new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379, 10000).getResource().get("cache_key");
     *  方�?03: Redis分片(通过client端集群,一致性哈希方�?实现) + Jedis多节点连接池 》》Redis集群,负载和容�?�较好, ShardedJedisPool一致性哈希分片,读写�?�匀，动�?扩充
     *      new ShardedJedisPool(new JedisPoolConfig(), new LinkedList<JedisShardInfo>());
     *  方�?03: Redis集群；
     *      new JedisCluster(jedisClusterNodes);    // TODO
     */

    private static ShardedJedisPool shardedJedisPool;
    private static ReentrantLock INSTANCE_INIT_LOCL = new ReentrantLock(false);

    /**
     * 获�?�ShardedJedis实例
     *
     * @return
     */
    private static ShardedJedis getInstance() {
        if (shardedJedisPool == null) {
            try {
                if (INSTANCE_INIT_LOCL.tryLock(2, TimeUnit.SECONDS)) {

                    try {

                        if (shardedJedisPool == null) {

                            // JedisPoolConfig
                            JedisPoolConfig config = new JedisPoolConfig();
                            config.setMaxTotal(200);
                            config.setMaxIdle(50);
                            config.setMinIdle(8);
                            config.setMaxWaitMillis(10000);         // 获�?�连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, �?于零:阻塞�?确定的时间,  默认-1
                            config.setTestOnBorrow(true);           // 在获�?�连接的时候检查有效性, 默认false
                            config.setTestOnReturn(false);          // 调用returnObject方法时，是�?�进行有效检查
                            config.setTestWhileIdle(true);          // Idle时进行连接扫�??
                            config.setTimeBetweenEvictionRunsMillis(30000);     // 表示idle object evitor两次扫�??之间�?sleep的毫秒数
                            config.setNumTestsPerEvictionRun(10);               // 表示idle object evitor�?次扫�??的最多的对象数
                            config.setMinEvictableIdleTimeMillis(60000);        // 表示一个对象至少�?�留在idle状�?的最短时间，然�?��?能被idle object evitor扫�??并驱�?；这一项�?�有在timeBetweenEvictionRunsMillis大于0时�?有�?义


                            // JedisShardInfo List
                            List<JedisShardInfo> jedisShardInfos = new LinkedList<JedisShardInfo>();

                            String[] addressArr = address.split(",");
                            for (int i = 0; i < addressArr.length; i++) {
                                JedisShardInfo jedisShardInfo = new JedisShardInfo(addressArr[i]);
                                jedisShardInfos.add(jedisShardInfo);
                            }
                            shardedJedisPool = new ShardedJedisPool(config, jedisShardInfos);
                            logger.info(">>>>>>>>>>> xxl-sso, JedisUtil.ShardedJedisPool init success.");
                        }

                    } finally {
                        INSTANCE_INIT_LOCL.unlock();
                    }
                }

            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }

        if (shardedJedisPool == null) {
            throw new NullPointerException(">>>>>>>>>>> xxl-sso, JedisUtil.ShardedJedisPool is null.");
        }

        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        return shardedJedis;
    }

    public static void close() throws IOException {
        if(shardedJedisPool != null) {
            shardedJedisPool.close();
        }
    }


    // ------------------------ serialize and unserialize ------------------------

    /**
     * 将对象-->byte[] (由于jedis中�?支�?直接存储object所以转�?��?byte[]存入)
     *
     * @param object
     * @return
     */
    private static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // �?列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                oos.close();
                baos.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 将byte[] -->Object
     *
     * @param bytes
     * @return
     */
    private static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // �??�?列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                bais.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    // ------------------------ jedis util ------------------------
    /**
     * 存储简�?�的字符串或者是Object 因为jedis没有分装直接存储Object的方法，所以在存储对象需斟酌下
     * 存储对象的字段是�?是�?�常多而且是�?是�?个字段都用到，如果是的�?那建议直接存储对象，
     * �?�则建议用集�?�的方�?存储，因为redis�?�以针对集�?�进行日常的�?作很方便而且还�?�以节�?空间
     */

    /**
     * Set String
     *
     * @param key
     * @param value
     * @param seconds 存活时间,�?��?/秒
     * @return
     */
    public static String setStringValue(String key, String value, int seconds) {
        String result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    /**
     * Set Object
     *
     * @param key
     * @param obj
     * @param seconds 存活时间,�?��?/秒
     */
    public static String setObjectValue(String key, Object obj, int seconds) {
        String result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.setex(key.getBytes(), seconds, serialize(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    /**
     * Get String
     *
     * @param key
     * @return
     */
    public static String getStringValue(String key) {
        String value = null;
        ShardedJedis client = getInstance();
        try {
            value = client.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return value;
    }

    /**
     * Get Object
     *
     * @param key
     * @return
     */
    public static Object getObjectValue(String key) {
        Object obj = null;
        ShardedJedis client = getInstance();
        try {
            byte[] bytes = client.get(key.getBytes());
            if (bytes != null && bytes.length > 0) {
                obj = unserialize(bytes);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return obj;
    }

    /**
     * Delete key
     *
     * @param key
     * @return Integer reply, specifically:
     * an integer greater than 0 if one or more keys were removed
     * 0 if none of the specified key existed
     */
    public static Long del(String key) {
        Long result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    /**
     * incrBy i(+i)
     *
     * @param key
     * @param i
     * @return new value after incr
     */
    public static Long incrBy(String key, int i) {
        Long result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.incrBy(key, i);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    /**
     * exists valid
     *
     * @param key
     * @return Boolean reply, true if the key exists, otherwise false
     */
    public static boolean exists(String key) {
        Boolean result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.exists(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    /**
     * expire reset
     *
     * @param key
     * @param seconds 存活时间,�?��?/秒
     * @return Integer reply, specifically:
     * 1: the timeout was set.
     * 0: the timeout was not set since the key already has an associated timeout (versions lt 2.1.3), or the key does not exist.
     */
    public static long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.expire(key, seconds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    /**
     * expire at unixTime
     *
     * @param key
     * @param unixTime
     * @return
     */
    public static long expireAt(String key, long unixTime) {
        Long result = null;
        ShardedJedis client = getInstance();
        try {
            result = client.expireAt(key, unixTime);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String xxlSsoRedisAddress = "redis://xxl-sso:password@127.0.0.1:6379/0";
        xxlSsoRedisAddress = "redis://127.0.0.1:6379/0";
        init(xxlSsoRedisAddress);

        setObjectValue("key", "666", 2*60*60);
        System.out.println(getObjectValue("key"));

    }

}
