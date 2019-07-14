/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/23 20:07</create-date>
 *
 * <copyright file="CoreDictionaryACDAT.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.utility.LexiconUtility;
import com.hankcs.hanlp.utility.Predefine;
import com.hankcs.hanlp.utility.TextUtility;

import java.io.*;
import java.util.*;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 使用DoubleArrayTrie实现的核心�?典
 * @author hankcs
 */
public class CoreDictionary
{
    public static DoubleArrayTrie<Attribute> trie = new DoubleArrayTrie<Attribute>();
    public final static String path = HanLP.Config.CoreDictionaryPath;
    public static final int totalFrequency = 221894;

    // 自动加载�?典
    static
    {
        long start = System.currentTimeMillis();
        if (!load(path))
        {
            throw new IllegalArgumentException("核心�?典" + path + "加载失败");
        }
        else
        {
            logger.info(path + "加载�?功，" + trie.size() + "个�?�?�，耗时" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    // 一些特殊的WORD_ID
    public static final int NR_WORD_ID = getWordID(Predefine.TAG_PEOPLE);
    public static final int NS_WORD_ID = getWordID(Predefine.TAG_PLACE);
    public static final int NT_WORD_ID = getWordID(Predefine.TAG_GROUP);
    public static final int T_WORD_ID = getWordID(Predefine.TAG_TIME);
    public static final int X_WORD_ID = getWordID(Predefine.TAG_CLUSTER);
    public static final int M_WORD_ID = getWordID(Predefine.TAG_NUMBER);
    public static final int NX_WORD_ID = getWordID(Predefine.TAG_PROPER);

    private static boolean load(String path)
    {
        logger.info("核心�?典开始加载:" + path);
        if (loadDat(path)) return true;
        TreeMap<String, CoreDictionary.Attribute> map = new TreeMap<String, Attribute>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            String line;
            int MAX_FREQUENCY = 0;
            long start = System.currentTimeMillis();
            while ((line = br.readLine()) != null)
            {
                String param[] = line.split("\\s");
                int natureCount = (param.length - 1) / 2;
                CoreDictionary.Attribute attribute = new CoreDictionary.Attribute(natureCount);
                for (int i = 0; i < natureCount; ++i)
                {
                    attribute.nature[i] = Nature.create(param[1 + 2 * i]);
                    attribute.frequency[i] = Integer.parseInt(param[2 + 2 * i]);
                    attribute.totalFrequency += attribute.frequency[i];
                }
                map.put(param[0], attribute);
                MAX_FREQUENCY += attribute.totalFrequency;
            }
            logger.info("核心�?典读入�?�?�" + map.size() + " 全部频次" + MAX_FREQUENCY + "，耗时" + (System.currentTimeMillis() - start) + "ms");
            br.close();
            trie.build(map);
            logger.info("核心�?典加载�?功:" + trie.size() + "个�?�?�，下�?�将写入缓存……");
            try
            {
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(path + Predefine.BIN_EXT)));
                Collection<CoreDictionary.Attribute> attributeList = map.values();
                out.writeInt(attributeList.size());
                for (CoreDictionary.Attribute attribute : attributeList)
                {
                    out.writeInt(attribute.totalFrequency);
                    out.writeInt(attribute.nature.length);
                    for (int i = 0; i < attribute.nature.length; ++i)
                    {
                        out.writeInt(attribute.nature[i].ordinal());
                        out.writeInt(attribute.frequency[i]);
                    }
                }
                trie.save(out);
                out.close();
            }
            catch (Exception e)
            {
                logger.warning("�?存失败" + e);
                return false;
            }
        }
        catch (FileNotFoundException e)
        {
            logger.warning("核心�?典" + path + "�?存在�?" + e);
            return false;
        }
        catch (IOException e)
        {
            logger.warning("核心�?典" + path + "读�?�错误�?" + e);
            return false;
        }

        return true;
    }

    /**
     * 从�?盘加载�?�数组
     *
     * @param path
     * @return
     */
    static boolean loadDat(String path)
    {
        try
        {
            ByteArray byteArray = ByteArray.createByteArray(path + Predefine.BIN_EXT);
            if (byteArray == null) return false;
            int size = byteArray.nextInt();
            CoreDictionary.Attribute[] attributes = new CoreDictionary.Attribute[size];
            final Nature[] natureIndexArray = Nature.values();
            for (int i = 0; i < size; ++i)
            {
                // 第一个是全部频次，第二个是�?性个数
                int currentTotalFrequency = byteArray.nextInt();
                int length = byteArray.nextInt();
                attributes[i] = new CoreDictionary.Attribute(length);
                attributes[i].totalFrequency = currentTotalFrequency;
                for (int j = 0; j < length; ++j)
                {
                    attributes[i].nature[j] = natureIndexArray[byteArray.nextInt()];
                    attributes[i].frequency[j] = byteArray.nextInt();
                }
            }
            if (!trie.load(byteArray, attributes) || byteArray.hasMore()) return false;
        }
        catch (Exception e)
        {
            logger.warning("读�?�失败，问题�?�生在" + e);
            return false;
        }
        return true;
    }

    /**
     * 获�?��?�目
     * @param key
     * @return
     */
    public static Attribute get(String key)
    {
        return trie.get(key);
    }

    /**
     * 获�?��?�目
     * @param wordID
     * @return
     */
    public static Attribute get(int wordID)
    {
        return trie.get(wordID);
    }

    /**
     * 获�?��?频
     *
     * @param term
     * @return
     */
    public static int getTermFrequency(String term)
    {
        Attribute attribute = get(term);
        if (attribute == null) return 0;
        return attribute.totalFrequency;
    }

    /**
     * 是�?�包�?��?语
     * @param key
     * @return
     */
    public static boolean contains(String key)
    {
        return trie.get(key) != null;
    }

    /**
     * 核心�?典中的�?属性
     */
    static public class Attribute implements Serializable
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

        // 几个预定义的�?��?

//        public static Attribute NUMBER = new Attribute()

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

        public Attribute(Nature[] nature, int[] frequency, int totalFrequency)
        {
            this.nature = nature;
            this.frequency = frequency;
            this.totalFrequency = totalFrequency;
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

        public static Attribute create(String natureWithFrequency)
        {
            try
            {
                String param[] = natureWithFrequency.split(" ");
                if (param.length % 2 != 0)
                {
                    return new Attribute(Nature.create(natureWithFrequency.trim()), 1); // 儿童�?
                }
                int natureCount = param.length / 2;
                Attribute attribute = new Attribute(natureCount);
                for (int i = 0; i < natureCount; ++i)
                {
                    attribute.nature[i] = Nature.create(param[2 * i]);
                    attribute.frequency[i] = Integer.parseInt(param[1 + 2 * i]);
                    attribute.totalFrequency += attribute.frequency[i];
                }
                return attribute;
            }
            catch (Exception e)
            {
                logger.warning("使用字符串" + natureWithFrequency + "创建�?�?�属性失败�?" + TextUtility.exceptionToString(e));
                return null;
            }
        }

        /**
         * 从字节�?中加载
         * @param byteArray
         * @param natureIndexArray
         * @return
         */
        public static Attribute create(ByteArray byteArray, Nature[] natureIndexArray)
        {
            int currentTotalFrequency = byteArray.nextInt();
            int length = byteArray.nextInt();
            Attribute attribute = new Attribute(length);
            attribute.totalFrequency = currentTotalFrequency;
            for (int j = 0; j < length; ++j)
            {
                attribute.nature[j] = natureIndexArray[byteArray.nextInt()];
                attribute.frequency[j] = byteArray.nextInt();
            }

            return attribute;
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
            int i = 0;
            for (Nature pos : this.nature)
            {
                if (nature == pos)
                {
                    return frequency[i];
                }
                ++i;
            }
            return 0;
        }

        /**
         * 是�?�有�?个�?性
         * @param nature
         * @return
         */
        public boolean hasNature(Nature nature)
        {
            return getNatureFrequency(nature) > 0;
        }

        /**
         * 是�?�有以�?个�?缀开头的�?性
         * @param prefix �?性�?缀，比如u会查询是�?�有ude, uzhe等等
         * @return
         */
        public boolean hasNatureStartsWith(String prefix)
        {
            for (Nature n : nature)
            {
                if (n.startsWith(prefix)) return true;
            }
            return false;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nature.length; ++i)
            {
                sb.append(nature[i]).append(' ').append(frequency[i]).append(' ');
            }
            return sb.toString();
        }

        public void save(DataOutputStream out) throws IOException
        {
            out.writeInt(totalFrequency);
            out.writeInt(nature.length);
            for (int i = 0; i < nature.length; ++i)
            {
                out.writeInt(nature[i].ordinal());
                out.writeInt(frequency[i]);
            }
        }
    }

    /**
     * 获�?��?语的ID
     * @param a �?语
     * @return ID,如果�?存在,则返回-1
     */
    public static int getWordID(String a)
    {
        return CoreDictionary.trie.exactMatchSearch(a);
    }
}
