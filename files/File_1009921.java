package com.xiaojinzi.component.cache;

import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * ================================================
 * LRU �?� Least Recently Used,最近最少使用,当缓存满了,会优先淘汰那些最近最�?常访问的数�?�
 * @see Cache
 * ================================================
 */
public class LruCache<K, V> implements Cache<K, V> {
    private final LinkedHashMap<K, V> cache = new LinkedHashMap<>(100, 0.75f, true);
    private final int initialMaxSize;
    private int maxSize;
    private int currentSize = 0;

    /**
     * Constructor for LruCache.
     *
     * @param size 这个缓存的最大 size,这个 size 所使用的�?��?必须和 {@link #getItemSize(Object)} 所使用的�?��?一致.
     */
    public LruCache(int size) {
        this.initialMaxSize = size;
        this.maxSize = size;
    }

    /**
     * 设置一个系数应用于当时构造函数中所传入的 size, 从而得到一个新的 {@link #maxSize}
     * 并会立�?�调用 {@link #evict} 开始清除满足�?�件的�?�目
     *
     * @param multiplier 系数
     */
    public synchronized void setSizeMultiplier(float multiplier) {
        if (multiplier < 0) {
            throw new IllegalArgumentException("Multiplier must be >= 0");
        }
        maxSize = Math.round(initialMaxSize * multiplier);
        evict();
    }

    /**
     * 返回�?个 {@code item} 所�?�用的 size,默认为1,这个 size 的�?��?必须和构造函数所传入的 size 一致
     * �?类�?�以�?写这个方法以适应�?�?�的�?��?,比如说 bytes
     *
     * @param value �?个 {@code item} key 所�?�用的 size
     * @param value �?个 {@code item} value 所�?�用的 size
     * @return �?�个 item 的 {@code size}
     */
    protected int getItemSize(K key,V value) {
        return 1;
    }

    private int safeSizeOf(K key,V value) {
        int result = getItemSize(key, value);
        if (result < 0) {
            throw new IllegalStateException("Negative size: " + key + "=" + value);
        }
        return result;
    }

    /**
     * 当缓存中有被驱�?的�?�目时,会回调此方法,默认空实现,�?类�?�以�?写这个方法
     *
     * @param key   被驱�?�?�目的 {@code key}
     * @param value 被驱�?�?�目的 {@code value}
     */
    protected void onItemEvicted(K key, V value) {
        // optional override
    }

    /**
     * 返回当�?缓存所能�?许的最大 size
     *
     * @return {@code maxSize}
     */
    @Override
    public synchronized int getMaxSize() {
        return maxSize;
    }

    /**
     * 返回当�?缓存已�?�用的总 size
     *
     * @return {@code size}
     */
    @Override
    public synchronized int size() {
        return currentSize;
    }

    /**
     * 如果这个 {@code key} 在缓存中有对应的 {@code value} 并且�?为 {@code null},则返回 true
     *
     * @param key 用�?�映射的 {@code key}
     * @return {@code true} 为在容器中�?�有这个 {@code key}, �?�则为 {@code false}
     */
    @Override
    public synchronized boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    /**
     * 返回当�?缓存中�?�有的所有 {@code key}
     *
     * @return {@code keySet}
     */
    @Override
    public synchronized Set<K> keySet() {
        return cache.keySet();
    }

    /**
     * 返回这个 {@code key} 在缓存中对应的 {@code value}, 如果返回 {@code null} 说明这个 {@code key} 没有对应的 {@code value}
     *
     * @param key 用�?�映射的 {@code key}
     * @return {@code value}
     */
    @Override
    @Nullable
    public synchronized V get(K key) {
        return cache.get(key);
    }

    /**
     * 将 {@code key} 和 {@code value} 以�?�目的形�?加入缓存,如果这个 {@code key} 在缓存中已�?有对应的 {@code value}
     * 则此 {@code value} 被新的 {@code value} 替�?�并返回,如果为 {@code null} 说明是一个新�?�目
     * <p>
     * 如果 {@link #safeSizeOf} 返回的 size 大于或等于缓存所能�?许的最大 size, 则�?能�?�缓存中添加此�?�目
     * 此时会回调 {@link #onItemEvicted(Object, Object)} 通知此方法当�?被驱�?的�?�目
     *
     * @param key   通过这个 {@code key} 添加�?�目
     * @param value 需�?添加的 {@code value}
     * @return 如果这个 {@code key} 在容器中已�?储存有 {@code value}, 则返回之�?的 {@code value} �?�则返回 {@code null}
     */
    @Override
    @Nullable
    public synchronized V put(K key, V value) {
        final int itemSize = safeSizeOf(key,value);
        if (itemSize >= maxSize) {
            onItemEvicted(key, value);
            return null;
        }

        final V result = cache.put(key, value);
        if (value != null) {
            currentSize += safeSizeOf(key,value);
        }
        if (result != null) {
            currentSize -= safeSizeOf(key,result);
        }
        evict();

        return result;
    }

    /**
     * 移除缓存中这个 {@code key} 所对应的�?�目,并返回所移除�?�目的 {@code value}
     * 如果返回为 {@code null} 则有�?�能时因为这个 {@code key} 对应的 {@code value} 为 {@code null} 或�?�目�?存在
     *
     * @param key 使用这个 {@code key} 移除对应的�?�目
     * @return 如果这个 {@code key} 在容器中已�?储存有 {@code value} 并且删除�?功则返回删除的 {@code value}, �?�则返回 {@code null}
     */
    @Override
    @Nullable
    public synchronized V remove(K key) {
        final V value = cache.remove(key);
        if (value != null) {
            currentSize -= safeSizeOf(key,value);
        }
        return value;
    }

    /**
     * 清除缓存中所有的内容
     */
    @Override
    public void clear() {
        trimToSize(0);
    }

    /**
     * 当指定的 size �?于当�?缓存已�?�用的总 size 时,会开始清除缓存中最近最少使用的�?�目
     *
     * @param size {@code size}
     */
    protected synchronized void trimToSize(int size) {
        Map.Entry<K, V> last;
        Iterator<Entry<K, V>> iterator = null;
        while (currentSize > size) {
            if (iterator == null) {
                iterator = cache.entrySet().iterator();
            }
            last = iterator.next();
            final K key = last.getKey();
            final V value = last.getValue();

            iterator.remove();
            currentSize -= safeSizeOf(key,value);
            onItemEvicted(key, value);
        }
    }

    /**
     * 当缓存中已�?�用的总 size 大于所能�?许的最大 size ,会使用  {@link #trimToSize(int)} 开始清除满足�?�件的�?�目
     */
    private void evict() {
        trimToSize(maxSize);
    }
}

