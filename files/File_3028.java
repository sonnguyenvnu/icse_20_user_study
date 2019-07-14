/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/9 22:30</create-date>
 *
 * <copyright file="CommonDictioanry.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.common;

import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.utility.TextUtility;

import java.io.*;
import java.util.*;

import static com.hankcs.hanlp.utility.Predefine.BIN_EXT;
import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 通用的�?典，对应固定格�?的�?典，但是标签�?�以泛型化
 *
 * @author hankcs
 */
public abstract class CommonDictionary<V>
{
    DoubleArrayTrie<V> trie;

    /**
     * 从字节中加载值数组
     *
     * @param byteArray
     * @return
     */
    protected abstract V[] loadValueArray(ByteArray byteArray);

    /**
     * 从txt路径加载
     *
     * @param path
     * @return
     */
    public boolean load(String path)
    {
        trie = new DoubleArrayTrie<V>();
        long start = System.currentTimeMillis();
        if (loadDat(ByteArray.createByteArray(path + BIN_EXT)))
        {
            return true;
        }
        TreeMap<String, V> map = new TreeMap<String, V>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] paramArray = line.split("\\s");
                map.put(paramArray[0], createValue(paramArray));
            }
            br.close();
        }
        catch (Exception e)
        {
            logger.warning("读�?�" + path + "失败" + e);
            return false;
        }
        onLoaded(map);
        Set<Map.Entry<String, V>> entrySet = map.entrySet();
        List<String> keyList = new ArrayList<String>(entrySet.size());
        List<V> valueList = new ArrayList<V>(entrySet.size());
        for (Map.Entry<String, V> entry : entrySet)
        {
            keyList.add(entry.getKey());
            valueList.add(entry.getValue());
        }
        int resultCode = trie.build(keyList, valueList);
        if (resultCode != 0)
        {
            logger.warning("trie建立失败");
            return false;
        }
        logger.info(path + "加载�?功，耗时" + (System.currentTimeMillis() - start) + "ms");
        saveDat(path + BIN_EXT, valueList);
        return true;
    }

    /**
     * 从dat路径加载
     *
     * @param byteArray
     * @return
     */
    protected boolean loadDat(ByteArray byteArray)
    {
        V[] valueArray = loadValueArray(byteArray);
        if (valueArray == null)
        {
            return false;
        }
        return trie.load(byteArray.getBytes(), byteArray.getOffset(), valueArray);
    }

    /**
     * �?存dat到路径
     *
     * @param path
     * @param valueArray
     * @return
     */
    protected boolean saveDat(String path, List<V> valueArray)
    {
        try
        {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(path)));
            out.writeInt(valueArray.size());
            for (V item : valueArray)
            {
                saveValue(item, out);
            }
            trie.save(out);
            out.close();
        }
        catch (Exception e)
        {
            logger.warning("�?存失败" + TextUtility.exceptionToString(e));
            return false;
        }
        return true;
    }

    /**
     * �?存�?�个值到�?中
     *
     * @param value
     * @param out
     * @throws IOException
     */
    protected abstract void saveValue(V value, DataOutputStream out) throws IOException;

    /**
     * 查询一个�?��?
     *
     * @param key
     * @return �?��?对应的�?�目
     */
    public V get(String key)
    {
        return trie.get(key);
    }

    /**
     * 是�?��?�有键
     *
     * @param key
     * @return
     */
    public boolean contains(String key)
    {
        return get(key) != null;
    }

    /**
     * �?典大�?
     *
     * @return
     */
    public int size()
    {
        return trie.size();
    }

    /**
     * 从一行�?典�?�目创建值
     *
     * @param params 第一个元素为键，请注�?跳过
     * @return
     */
    protected abstract V createValue(String[] params);

    /**
     * 文本�?典加载完毕的回调函数
     *
     * @param map
     */
    protected void onLoaded(TreeMap<String, V> map)
    {
    }
}
