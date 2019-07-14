/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/9 3:11</create-date>
 *
 * <copyright file="EasyDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary;

import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.corpus.tag.Nature;

import java.io.*;
import java.util.*;

import static com.hankcs.hanlp.HanLP.Config.IOAdapter;
import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 一个通用的�?满足特定格�?的�?�数组�?典
 *
 * @author hankcs
 */
public class EasyDictionary
{
    DoubleArrayTrie<Attribute> trie = new DoubleArrayTrie<Attribute>();

    public static EasyDictionary create(String path)
    {
        EasyDictionary dictionary = new EasyDictionary();
        if (dictionary.load(path))
        {
            return dictionary;
        }
        else
        {
            logger.warning("从" + path + "读�?�失败");
        }

        return null;
    }

    private boolean load(String path)
    {
        logger.info("通用�?典开始加载:" + path);
        TreeMap<String, Attribute> map = new TreeMap<String, Attribute>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(IOAdapter == null ? new FileInputStream(path) : IOAdapter.open(path), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null)
            {
                String param[] = line.split("\\s+");
                int natureCount = (param.length - 1) / 2;
                Attribute attribute = new Attribute(natureCount);
                for (int i = 0; i < natureCount; ++i)
                {
                    attribute.nature[i] = Nature.create(param[1 + 2 * i]);
                    attribute.frequency[i] = Integer.parseInt(param[2 + 2 * i]);
                    attribute.totalFrequency += attribute.frequency[i];
                }
                map.put(param[0], attribute);
            }
            logger.info("通用�?典读入�?�?�" + map.size());
            br.close();
        }
        catch (FileNotFoundException e)
        {
            logger.severe("通用�?典" + path + "�?存在�?" + e);
            return false;
        }
        catch (IOException e)
        {
            logger.severe("通用�?典" + path + "读�?�错误�?" + e);
            return false;
        }

        logger.info("通用�?典DAT构建结果:" + trie.build(map));
        logger.info("通用�?典加载�?功:" + trie.size() +"个�?�?�" );
        return true;
    }

    public Attribute GetWordInfo(String key)
    {
        return trie.get(key);
    }

    public boolean contains(String key)
    {
        return GetWordInfo(key) != null;
    }

    public BaseSearcher getSearcher(String text)
    {
        return new Searcher(text);
    }

    public class Searcher extends BaseSearcher<Attribute>
    {
        /**
         * 分�?从何处开始，这是一个状�?
         */
        int begin;

        private List<Map.Entry<String, Attribute>> entryList;

        protected Searcher(char[] c)
        {
            super(c);
        }

        protected Searcher(String text)
        {
            super(text);
            entryList = new LinkedList<Map.Entry<String, Attribute>>();
        }

        @Override
        public Map.Entry<String, Attribute> next()
        {
            // �?�?首次调用找到一个�?语
            while (entryList.size() == 0 && begin < c.length)
            {
                entryList = trie.commonPrefixSearchWithValue(c, begin);
                ++begin;
            }
            // 之�?�调用仅在缓存用完的时候调用一次
            if (entryList.size() == 0 && begin < c.length)
            {
                entryList = trie.commonPrefixSearchWithValue(c, begin);
                ++begin;
            }
            if (entryList.size() == 0)
            {
                return null;
            }
            Map.Entry<String, Attribute> result = entryList.get(0);
            entryList.remove(0);
            offset = begin - 1;
            return result;
        }
    }

    /**
     * 通用�?典中的�?属性
     */
    static public class Attribute
    {
        /**
         * �?性列表
         */
        public Nature nature[];
        /**
         * �?性对应的�?频
         */
        public int frequency[];

        public int totalFrequency;

        public Attribute(int size)
        {
            nature = new Nature[size];
            frequency = new int[size];
        }

        public Attribute(Nature[] nature, int[] frequency)
        {
            this.nature = nature;
            this.frequency = frequency;
        }

        public Attribute(Nature nature, int frequency)
        {
            this(1);
            this.nature[0] = nature;
            this.frequency[0] = frequency;
            totalFrequency = frequency;
        }

        /**
         * 使用�?�个�?性，默认�?频1000构造
         *
         * @param nature
         */
        public Attribute(Nature nature)
        {
            this(nature, 1000);
        }

        /**
         * 获�?��?性的�?频
         *
         * @param nature 字符串�?性
         * @return �?频
         * @deprecated 推�??使用Nature�?�数�?
         */
        public int getNatureFrequency(String nature)
        {
            try
            {
                Nature pos = Nature.create(nature);
                return getNatureFrequency(pos);
            }
            catch (IllegalArgumentException e)
            {
                return 0;
            }
        }

        /**
         * 获�?��?性的�?频
         *
         * @param nature �?性
         * @return �?频
         */
        public int getNatureFrequency(final Nature nature)
        {
            int result = 0;
            int i = 0;
            for (Nature pos : this.nature)
            {
                if (nature == pos)
                {
                    return frequency[i];
                }
                ++i;
            }
            return result;
        }

        @Override
        public String toString()
        {
            return "Attribute{" +
                    "nature=" + Arrays.toString(nature) +
                    ", frequency=" + Arrays.toString(frequency) +
                    '}';
        }
    }
}
