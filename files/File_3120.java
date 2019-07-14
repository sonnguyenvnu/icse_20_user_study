/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/1 23:07</create-date>
 *
 * <copyright file="BaseChineseDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.ts;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.dictionary.StringDictionary;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.other.CharTable;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.utility.Predefine;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.*;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * @author hankcs
 */
public class BaseChineseDictionary
{
    static void combineChain(TreeMap<String, String> s2t, TreeMap<String, String> t2x)
    {
        for (Map.Entry<String, String> entry : s2t.entrySet())
        {
            String x = t2x.get(entry.getValue());
            if (x != null)
            {
                entry.setValue(x);
            }
        }
        for (Map.Entry<String, String> entry : t2x.entrySet())
        {
            String s = CharTable.convert(entry.getKey());
            if (!s2t.containsKey(s))
            {
                s2t.put(s, entry.getValue());
            }
        }
    }

    static void combineReverseChain(TreeMap<String, String> t2s, TreeMap<String, String> tw2t, boolean convert)
    {
        for (Map.Entry<String, String> entry : tw2t.entrySet())
        {
            String tw = entry.getKey();
            String s = t2s.get(entry.getValue());
            if (s == null)
                s = convert ? CharTable.convert(entry.getValue()) : entry.getValue();
            t2s.put(tw, s);
        }
    }

    /**
     * 读�?��?典
     * @param storage 储存空间
     * @param reverse 是�?�翻转键值对
     * @param pathArray 路径
     * @return 是�?�加载�?功
     */
    static boolean load(Map<String, String> storage, boolean reverse, String... pathArray)
    {
        StringDictionary dictionary = new StringDictionary("=");
        for (String path : pathArray)
        {
            if (!dictionary.load(path)) return false;
        }
        if (reverse) dictionary = dictionary.reverse();
        Set<Map.Entry<String, String>> entrySet = dictionary.entrySet();
        for (Map.Entry<String, String> entry : entrySet)
        {
            storage.put(entry.getKey(), entry.getValue());
        }

        return true;
    }
    /**
     * 将path的内容载入trie中
     * @param path
     * @param trie
     * @return
     */
    static boolean load(String path, AhoCorasickDoubleArrayTrie<String> trie)
    {
        return load(path, trie, false);
    }

    /**
     * 读�?��?典
     * @param path
     * @param trie
     * @param reverse 是�?�将其翻转
     * @return
     */
    static boolean load(String path, AhoCorasickDoubleArrayTrie<String> trie, boolean reverse)
    {
        String datPath = path;
        if (reverse)
        {
            datPath += Predefine.REVERSE_EXT;
        }
        if (loadDat(datPath, trie)) return true;
        // 从文本中载入并且�?试生�?dat
        TreeMap<String, String> map = new TreeMap<String, String>();
        if (!load(map, reverse, path)) return false;
        logger.info("正在构建AhoCorasickDoubleArrayTrie，�?��?：" + path);
        trie.build(map);
        logger.info("正在缓存�?�数组" + datPath);
        saveDat(datPath, trie, map.entrySet());
        return true;
    }

    static boolean loadDat(String path, AhoCorasickDoubleArrayTrie<String> trie)
    {
        ByteArray byteArray = ByteArray.createByteArray(path + Predefine.BIN_EXT);
        if (byteArray == null) return false;
        int size = byteArray.nextInt();
        String[] valueArray = new String[size];
        for (int i = 0; i < valueArray.length; ++i)
        {
            valueArray[i] = byteArray.nextString();
        }
        trie.load(byteArray, valueArray);
        return true;
    }

    static boolean saveDat(String path, AhoCorasickDoubleArrayTrie<String> trie, Set<Map.Entry<String, String>> entrySet)
    {
        if (trie.size() != entrySet.size())
        {
            logger.warning("键值对�?匹�?");
            return false;
        }
        try
        {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(path + Predefine.BIN_EXT)));
            out.writeInt(entrySet.size());
            for (Map.Entry<String, String> entry : entrySet)
            {
                char[] charArray = entry.getValue().toCharArray();
                out.writeInt(charArray.length);
                for (char c : charArray)
                {
                    out.writeChar(c);
                }
            }
            trie.save(out);
            out.close();
        }
        catch (Exception e)
        {
            logger.warning("缓存值dat" + path + "失败");
            return false;
        }

        return true;
    }

    public static BaseSearcher getSearcher(char[] charArray, DoubleArrayTrie<String> trie)
    {
        return new Searcher(charArray, trie);
    }

    protected static String segLongest(char[] charArray, DoubleArrayTrie<String> trie)
    {
        StringBuilder sb = new StringBuilder(charArray.length);
        BaseSearcher searcher = getSearcher(charArray, trie);
        Map.Entry<String, String> entry;
        int p = 0;  // 当�?处�?�到什么�?置
        int offset;
        while ((entry = searcher.next()) != null)
        {
            offset = searcher.getOffset();
            // 补足没查到的�?
            while (p < offset)
            {
                sb.append(charArray[p]);
                ++p;
            }
            sb.append(entry.getValue());
            p = offset + entry.getKey().length();
        }
        // 补足没查到的�?
        while (p < charArray.length)
        {
            sb.append(charArray[p]);
            ++p;
        }
        return sb.toString();
    }

    protected static String segLongest(char[] charArray, AhoCorasickDoubleArrayTrie<String> trie)
    {
        final String[] wordNet = new String[charArray.length];
        final int[] lengthNet = new int[charArray.length];
        trie.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<String>()
        {
            @Override
            public void hit(int begin, int end, String value)
            {
                int length = end - begin;
                if (length > lengthNet[begin])
                {
                    wordNet[begin] = value;
                    lengthNet[begin] = length;
                }
            }
        });
        StringBuilder sb = new StringBuilder(charArray.length);
        for (int offset = 0; offset < wordNet.length; )
        {
            if (wordNet[offset] == null)
            {
                sb.append(charArray[offset]);
                ++offset;
                continue;
            }
            sb.append(wordNet[offset]);
            offset += lengthNet[offset];
        }
        return sb.toString();
    }

    /**
     * 最长分�?
     */
    public static class Searcher extends BaseSearcher<String>
    {
        /**
         * 分�?从何处开始，这是一个状�?
         */
        int begin;

        DoubleArrayTrie<String> trie;

        protected Searcher(char[] c, DoubleArrayTrie<String> trie)
        {
            super(c);
            this.trie = trie;
        }

        protected Searcher(String text, DoubleArrayTrie<String> trie)
        {
            super(text);
            this.trie = trie;
        }

        @Override
        public Map.Entry<String, String> next()
        {
            // �?�?首次调用找到一个�?语
            Map.Entry<String, String> result = null;
            while (begin < c.length)
            {
                LinkedList<Map.Entry<String, String>> entryList = trie.commonPrefixSearchWithValue(c, begin);
                if (entryList.size() == 0)
                {
                    ++begin;
                }
                else
                {
                    result = entryList.getLast();
                    offset = begin;
                    begin += result.getKey().length();
                    break;
                }
            }
            if (result == null)
            {
                return null;
            }
            return result;
        }
    }
}
