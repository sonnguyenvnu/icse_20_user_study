/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/13 13:13</create-date>
 *
 * <copyright file="CommonSynonymDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.common;

import com.hankcs.hanlp.algorithm.ArrayDistance;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.synonym.Synonym;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import static com.hankcs.hanlp.utility.Predefine.logger;
/**
 * 一个没有指定资�?�?置的通用�?�义�?�?典
 *
 * @author hankcs
 */
public class CommonSynonymDictionaryEx
{
    DoubleArrayTrie<Long[]> trie;

    private CommonSynonymDictionaryEx()
    {
    }

    public static CommonSynonymDictionaryEx create(InputStream inputStream)
    {
        CommonSynonymDictionaryEx dictionary = new CommonSynonymDictionaryEx();
        if (dictionary.load(inputStream))
        {
            return dictionary;
        }

//        TreeSet<Float> set = new TreeSet<Float>();

        return null;
    }

    public boolean load(InputStream inputStream)
    {
        trie = new DoubleArrayTrie<Long[]>();
        TreeMap<String, Set<Long>> treeMap = new TreeMap<String, Set<Long>>();
        String line = null;
        try
        {
            BufferedReader bw = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = bw.readLine()) != null)
            {
                String[] args = line.split(" ");
                List<Synonym> synonymList = Synonym.create(args);
                for (Synonym synonym : synonymList)
                {
                    Set<Long> idSet = treeMap.get(synonym.realWord);
                    if (idSet == null)
                    {
                        idSet = new TreeSet<Long>();
                        treeMap.put(synonym.realWord, idSet);
                    }
                    idSet.add(synonym.id);
                }
            }
            bw.close();
            List<String> keyList = new ArrayList<String>(treeMap.size());
            for (String key : treeMap.keySet())
            {
                keyList.add(key);
            }
            List<Long[]> valueList = new ArrayList<Long[]>(treeMap.size());
            for (Set<Long> idSet : treeMap.values())
            {
                valueList.add(idSet.toArray(new Long[0]));
            }
            int resultCode = trie.build(keyList, valueList);
            if (resultCode != 0)
            {
                logger.warning("构建" + inputStream + "失败，错误�?" + resultCode);
                return false;
            }
        }
        catch (Exception e)
        {
            logger.warning("读�?�" + inputStream + "失败，�?�能由行" + line + "造�?" + e);
            return false;
        }
        return true;
    }

    public Long[] get(String key)
    {
        return trie.get(key);
    }

    /**
     * 语义�?离
     * @param a
     * @param b
     * @return
     */
    public long distance(String a, String b)
    {
        Long[] itemA = get(a);
        if (itemA == null) return Long.MAX_VALUE / 3;
        Long[] itemB = get(b);
        if (itemB == null) return Long.MAX_VALUE / 3;

        return ArrayDistance.computeAverageDistance(itemA, itemB);
    }

    /**
     * �?典中的一个�?�目
     */
    public static class SynonymItem extends Synonym
    {
        /**
         * �?�目的value，是key的�?�义�?近义�?列表
         */
        public Map<String, Synonym> synonymMap;

        public SynonymItem(Synonym entry, Map<String, Synonym> synonymMap)
        {
            super(entry.realWord, entry.id, entry.type);
            this.synonymMap = synonymMap;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(' ');
            sb.append(synonymMap);
            return sb.toString();
        }
    }
}
