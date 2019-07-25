package com.fisher.cache.support;

import com.fisher.cache.CacheRedisCaffeineProperties;
import com.github.benmanes.caffeine.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class RedisCaffeineCache extends AbstractValueAdaptingCache {
	
	private final Logger logger = LoggerFactory.getLogger(RedisCaffeineCache.class);

	private String name;
	
	private RedisTemplate<Object, Object> stringKeyRedisTemplate;

	private Cache<Object, Object> caffeineCache;

	private String cachePrefix;

	private long defaultExpiration = 0;

	private Map<String, Long> expires;
	
	private String topic = "cache:redis:caffeine:topic";
	
	private Map<String, ReentrantLock> keyLockMap = new ConcurrentHashMap<String, ReentrantLock>();
	
	protected RedisCaffeineCache(boolean allowNullValues) {
		super(allowNullValues);
	}
	
	public RedisCaffeineCache(String name, RedisTemplate<Object, Object> stringKeyRedisTemplate,
                              Cache<Object, Object> caffeineCache, CacheRedisCaffeineProperties cacheRedisCaffeineProperties) {
		super(cacheRedisCaffeineProperties.isCacheNullValues());
		this.name = name;
		this.stringKeyRedisTemplate = stringKeyRedisTemplate;
		this.caffeineCache = caffeineCache;
		this.cachePrefix = cacheRedisCaffeineProperties.getCachePrefix();
		this.defaultExpiration = cacheRedisCaffeineProperties.getRedis().getDefaultExpiration();
		this.expires = cacheRedisCaffeineProperties.getRedis().getExpires();
		this.topic = cacheRedisCaffeineProperties.getRedis().getTopic();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		Object value = lookup(key);
		if(value != null) {
			return (T) value;
		}
		
		ReentrantLock lock = keyLockMap.get(key.toString());
		if(lock == null) {
			logger.debug("为key创建�? : {}", key);
			lock = new ReentrantLock();
			keyLockMap.putIfAbsent(key.toString(), lock);
		}
		try {
			lock.lock();
			value = lookup(key);
			if(value != null) {
				return (T) value;
			}
			value = valueLoader.call();
			Object storeValue = toStoreValue(value);
			put(key, storeValue);
			return (T) value;
		} catch (Exception e) {
			throw new ValueRetrievalException(key, valueLoader, e.getCause());
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void put(Object key, Object value) {
		if (!super.isAllowNullValues() && value == null) {
			this.evict(key);
            return;
        }
		long expire = getExpire();
		if(expire > 0) {
			stringKeyRedisTemplate.opsForValue().set(getKey(key), toStoreValue(value), expire, TimeUnit.MILLISECONDS);
		} else {
			stringKeyRedisTemplate.opsForValue().set(getKey(key), toStoreValue(value));
		}
		
		push(new CacheMessage(this.name, key));
		
		caffeineCache.put(key, value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		Object cacheKey = getKey(key);
		Object prevValue = null;
		// 考虑使用分布�?�?，或者将redis的setIfAbsent改为原�?性�?作
		synchronized (key) {
			prevValue = stringKeyRedisTemplate.opsForValue().get(cacheKey);
			if(prevValue == null) {
				long expire = getExpire();
				if(expire > 0) {
					stringKeyRedisTemplate.opsForValue().set(getKey(key), toStoreValue(value), expire, TimeUnit.MILLISECONDS);
				} else {
					stringKeyRedisTemplate.opsForValue().set(getKey(key), toStoreValue(value));
				}
				
				push(new CacheMessage(this.name, key));
				
				caffeineCache.put(key, toStoreValue(value));
			}
		}
		return toValueWrapper(prevValue);
	}

	@Override
	public void evict(Object key) {
		// 先清除redis中缓存数�?�，然�?�清除caffeine中的缓存，�?��?短时间内如果先清除caffeine缓存�?�其他请求会�?从redis里加载到caffeine中
		stringKeyRedisTemplate.delete(getKey(key));
		
		push(new CacheMessage(this.name, key));
		
		caffeineCache.invalidate(key);
	}

	@Override
	public void clear() {
		// 先清除redis中缓存数�?�，然�?�清除caffeine中的缓存，�?��?短时间内如果先清除caffeine缓存�?�其他请求会�?从redis里加载到caffeine中
		Set<Object> keys = stringKeyRedisTemplate.keys(this.name.concat(":*"));
		for(Object key : keys) {
			stringKeyRedisTemplate.delete(key);
		}
		
		push(new CacheMessage(this.name, null));
		
		caffeineCache.invalidateAll();
	}

	@Override
	protected Object lookup(Object key) {
		Object cacheKey = getKey(key);
		Object value = caffeineCache.getIfPresent(key);
		if(value != null) {
			logger.debug("从caffeine中获�?�缓存, key为: {}", cacheKey);
			return value;
		}
		
		value = stringKeyRedisTemplate.opsForValue().get(cacheKey);
		
		if(value != null) {
			logger.debug("从reids获�?�缓存并且放到caffeine中, key为 : {}", cacheKey);
			caffeineCache.put(key, value);
		}
		return value;
	}

	private Object getKey(Object key) {
		return this.name.concat(":").concat(StringUtils.isEmpty(cachePrefix) ? key.toString() : cachePrefix.concat(":").concat(key.toString()));
	}
	
	private long getExpire() {
		long expire = defaultExpiration;
		Long cacheNameExpire = expires.get(this.name);
		return cacheNameExpire == null ? expire : cacheNameExpire.longValue();
	}
	
	/**
	 * @description 缓存�?�更时通知其他节点清�?�本地缓存
	 * @author fuwei.deng
	 * @date 2018年1月31日 下�?�3:20:28
	 * @version 1.0.0
	 * @param message
	 */
	private void push(CacheMessage message) {
		stringKeyRedisTemplate.convertAndSend(topic, message);
	}
	
	/**
	 * @description 清�?�本地缓存
	 * @author fuwei.deng
	 * @date 2018年1月31日 下�?�3:15:39
	 * @version 1.0.0
	 * @param key
	 */
	public void clearLocal(Object key) {
		logger.debug("清空本地缓存, key为: {}", key);
		if(key == null) {
			caffeineCache.invalidateAll();
		} else {
			caffeineCache.invalidate(key);
		}
	}
}
