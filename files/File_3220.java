/*
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2017-11-02 13:42</create-date>
 *
 * <copyright file="AbstractWordVectorModel.java" company="�?农场">
 * Copyright (c) 2017, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.mining.word2vec;

import com.hankcs.hanlp.algorithm.MaxHeap;

import java.util.*;

/**
 * 抽象的�?��?模型，将抽象的对象映射为�?��?
 *
 * @author hankcs
 */
public abstract class AbstractVectorModel<K>
{
    Map<K, Vector> storage;

    public AbstractVectorModel(Map<K, Vector> storage)
    {
        this.storage = storage;
    }

    public AbstractVectorModel()
    {
        storage = new TreeMap<K, Vector>();
    }

    /**
     * 获�?�一个键的�?��?（键�?会被预处�?�）
     *
     * @param key 键
     * @return �?��?
     */
    final public Vector vector(K key)
    {
        Vector vector = storage.get(key);
        if (vector == null) return null;
        return vector;
    }

    /**
     * 余弦相似度
     *
     * @param what 一个�?
     * @param with �?�一个�?
     * @return 余弦相似度
     */
    public float similarity(K what, K with)
    {
        Vector vectorWhat = storage.get(what);
        if (vectorWhat == null)
        {
            return -1f;
        }
        Vector vectorWith = storage.get(with);
        if (vectorWith == null)
        {
            return -1f;
        }
        return vectorWhat.cosineForUnitVector(vectorWith);
    }

    /**
     * 查询与key最相似的元素
     *
     * @param key  键
     * @param size topN个
     * @return 键值对列表, 键是相似�?语, 值是相似度, 按相似度�?�?排列
     */
    public List<Map.Entry<K, Float>> nearest(K key, int size)
    {
        Vector vector = storage.get(key);
        if (vector == null)
        {
            return Collections.emptyList();
        }
        return nearest(key, vector, size);
    }

    /**
     * 查询与key最相似的元素
     *
     * @param key    键 结果将排除该键
     * @param vector �?��?
     * @param size   topN个
     * @return 键值对列表, 键是相似�?语, 值是相似度, 按相似度�?�?排列
     */
    private List<Map.Entry<K, Float>> nearest(K key, Vector vector, int size)
    {
        MaxHeap<Map.Entry<K, Float>> maxHeap = new MaxHeap<Map.Entry<K, Float>>(size, new Comparator<Map.Entry<K, Float>>()
        {
            @Override
            public int compare(Map.Entry<K, Float> o1, Map.Entry<K, Float> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (Map.Entry<K, Vector> entry : storage.entrySet())
        {
            if (entry.getKey().equals(key))
            {
                continue;
            }
            maxHeap.add(new AbstractMap.SimpleEntry<K, Float>(entry.getKey(), entry.getValue().cosineForUnitVector(vector)));
        }
        return maxHeap.toList();
    }

    /**
     * 获�?�与�?��?最相似的�?语
     *
     * @param vector �?��?
     * @param size   topN个
     * @return 键值对列表, 键是相似�?语, 值是相似度, 按相似度�?�?排列
     */
    public List<Map.Entry<K, Float>> nearest(Vector vector, int size)
    {
        MaxHeap<Map.Entry<K, Float>> maxHeap = new MaxHeap<Map.Entry<K, Float>>(size, new Comparator<Map.Entry<K, Float>>()
        {
            @Override
            public int compare(Map.Entry<K, Float> o1, Map.Entry<K, Float> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (Map.Entry<K, Vector> entry : storage.entrySet())
        {
            maxHeap.add(new AbstractMap.SimpleEntry<K, Float>(entry.getKey(), entry.getValue().cosineForUnitVector(vector)));
        }
        return maxHeap.toList();
    }

    /**
     * 获�?�与�?��?最相似的�?语（默认10个）
     *
     * @param vector �?��?
     * @return 键值对列表, 键是相似�?语, 值是相似度, 按相似度�?�?排列
     */
    public List<Map.Entry<K, Float>> nearest(Vector vector)
    {
        return nearest(vector, 10);
    }

    /**
     * 查询与�?语最相似的�?语
     *
     * @param key �?语
     * @return 键值对列表, 键是相似�?语, 值是相似度, 按相似度�?�?排列
     */
    public List<Map.Entry<K, Float>> nearest(K key)
    {
        return nearest(key, 10);
    }

    /**
     * 执行查询最相似的对象（�?类通过query方法决定如何解�?query，然�?�通过此方法执行查询）
     *
     * @param query 查询语�?�（或者说一个对象的内容）
     * @param size  需�?返回�?多少个对象
     * @return
     */
    final List<Map.Entry<K, Float>> queryNearest(String query, int size)
    {
        if (query == null || query.length() == 0)
        {
            return Collections.emptyList();
        }
        try
        {
            return nearest(query(query), size);
        }
        catch (Exception e)
        {
            return Collections.emptyList();
        }
    }

    /**
     * 查询抽象文本对应的�?��?。此方法应当�?�?返回�?��?�?��?。
     *
     * @param query
     * @return
     */
    public abstract Vector query(String query);

    /**
     * 模型中的�?�?��?总数（�?表大�?）
     *
     * @return
     */
    public int size()
    {
        return storage.size();
    }

    /**
     * 模型中的�?�?��?维度
     *
     * @return
     */
    public int dimension()
    {
        if (storage == null || storage.isEmpty())
        {
            return 0;
        }
        return storage.values().iterator().next().size();
    }

    /**
     * 删除元素
     *
     * @param key
     * @return
     */
    public Vector remove(K key)
    {
        return storage.remove(key);
    }
}
