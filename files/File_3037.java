/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/24 12:46</create-date>
 *
 * <copyright file="CoreBiGramDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.utility.Predefine;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 核心�?典的二元接续�?典，采用整型储存，高性能
 *
 * @author hankcs
 */
public class CoreBiGramTableDictionary
{
    /**
     * �??述了�?在pair中的范围，具体说�?�<br>
     * 给定一个�?idA，从pair[start[idA]]开始的start[idA + 1] - start[idA]�??述了一些接续的频次
     */
    static int start[];
    /**
     * pair[�?�数n]表示key，pair[n+1]表示frequency
     */
    static int pair[];

    static
    {
        String path = HanLP.Config.BiGramDictionaryPath;
        logger.info("开始加载二元�?典" + path + ".table");
        long start = System.currentTimeMillis();
        if (!load(path))
        {
            throw new IllegalArgumentException("二元�?典加载失败");
        }
        else
        {
            logger.info(path + ".table" + "加载�?功，耗时" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    static boolean load(String path)
    {
        String datPath = HanLP.Config.BiGramDictionaryPath + ".table" + Predefine.BIN_EXT;
        if (loadDat(datPath)) return true;
        BufferedReader br;
        TreeMap<Integer, TreeMap<Integer, Integer>> map = new TreeMap<Integer, TreeMap<Integer, Integer>>();
        try
        {
            br = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            String line;
            int total = 0;
            int maxWordId = CoreDictionary.trie.size();
            while ((line = br.readLine()) != null)
            {
                String[] params = line.split("\\s");
                String[] twoWord = params[0].split("@", 2);
                String a = twoWord[0];
                int idA = CoreDictionary.trie.exactMatchSearch(a);
                if (idA == -1)
                {
//                    if (HanLP.Config.DEBUG)
//                        logger.warning(line + " 中的 " + a + "�?存在于核心�?典，将会忽略这一行");
                    continue;
                }
                String b = twoWord[1];
                int idB = CoreDictionary.trie.exactMatchSearch(b);
                if (idB == -1)
                {
//                    if (HanLP.Config.DEBUG)
//                        logger.warning(line + " 中的 " + b + "�?存在于核心�?典，将会忽略这一行");
                    continue;
                }
                int freq = Integer.parseInt(params[1]);
                TreeMap<Integer, Integer> biMap = map.get(idA);
                if (biMap == null)
                {
                    biMap = new TreeMap<Integer, Integer>();
                    map.put(idA, biMap);
                }
                biMap.put(idB, freq);
                total += 2;
            }
            br.close();
            start = new int[maxWordId + 1];
            pair = new int[total];  // total是接续的个数*2
            int offset = 0;

            for (int i = 0; i < maxWordId; ++i)
            {
                TreeMap<Integer, Integer> bMap = map.get(i);
                if (bMap != null)
                {
                    for (Map.Entry<Integer, Integer> entry : bMap.entrySet())
                    {
                        int index = offset << 1;
                        pair[index] = entry.getKey();
                        pair[index + 1] = entry.getValue();
                        ++offset;
                    }
                }
                start[i + 1] = offset;
            }

            logger.info("二元�?典读�?�完毕:" + path + "，构建为TableBin结构");
        }
        catch (FileNotFoundException e)
        {
            logger.severe("二元�?典" + path + "�?存在�?" + e);
            return false;
        }
        catch (IOException e)
        {
            logger.severe("二元�?典" + path + "读�?�错误�?" + e);
            return false;
        }
        logger.info("开始缓存二元�?典到" + datPath);
        if (!saveDat(datPath))
        {
            logger.warning("缓存二元�?典到" + datPath + "失败");
        }
        return true;
    }

    static boolean saveDat(String path)
    {
        try
        {
//            DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
//            out.writeInt(start.length);
//            for (int i : start)
//            {
//                out.writeInt(i);
//            }
//            out.writeInt(pair.length);
//            for (int i : pair)
//            {
//                out.writeInt(i);
//            }
//            out.close();
            ObjectOutputStream out = new ObjectOutputStream(IOUtil.newOutputStream(path));
            out.writeObject(start);
            out.writeObject(pair);
            out.close();
        }
        catch (Exception e)
        {
            logger.log(Level.WARNING, "在缓存" + path + "时�?�生异常", e);
            return false;
        }

        return true;
    }

    static boolean loadDat(String path)
    {
//        ByteArray byteArray = ByteArray.createByteArray(path);
//        if (byteArray == null) return false;
//
//        int size = byteArray.nextInt(); // 这两个数组从byte转为int竟然�?花4秒钟
//        start = new int[size];
//        for (int i = 0; i < size; ++i)
//        {
//            start[i] = byteArray.nextInt();
//        }
//
//        size = byteArray.nextInt();
//        pair = new int[size];
//        for (int i = 0; i < size; ++i)
//        {
//            pair[i] = byteArray.nextInt();
//        }

        try
        {
            ObjectInputStream in = new ObjectInputStream(IOUtil.newInputStream(path));
            start = (int[]) in.readObject();
            if (CoreDictionary.trie.size() != start.length - 1)     // 目�?CoreNatureDictionary.ngram.txt的缓存�?赖于CoreNatureDictionary.txt的缓存
            {                                                       // 所以这里校验一下二者的一致性，�?然�?�能导致下标越界或者ngram错乱的情况
                in.close();
                return false;
            }
            pair = (int[]) in.readObject();
            in.close();
        }
        catch (Exception e)
        {
            logger.warning("�?试载入缓存文件" + path + "�?�生异常[" + e + "]，下�?�将载入�?文件并自动缓存……");
            return false;
        }
        return true;
    }

    /**
     * 二分�?�索，由于二元接续�?一个�?固定时，�?�一个�?比较少，所以二分也能�?�得很高的性能
     * @param a 目标数组
     * @param fromIndex 开始下标
     * @param length 长度
     * @param key �?的id
     * @return 共现频次
     */
    private static int binarySearch(int[] a, int fromIndex, int length, int key)
    {
        int low = fromIndex;
        int high = fromIndex + length - 1;

        while (low <= high)
        {
            int mid = (low + high) >>> 1;
            int midVal = a[mid << 1];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * 获�?�共现频次
     *
     * @param a 第一个�?
     * @param b 第二个�?
     * @return 第一个�?@第二个�?出现的频次
     */
    public static int getBiFrequency(String a, String b)
    {
        int idA = CoreDictionary.trie.exactMatchSearch(a);
        if (idA == -1)
        {
            return 0;
        }
        int idB = CoreDictionary.trie.exactMatchSearch(b);
        if (idB == -1)
        {
            return 0;
        }
        int index = binarySearch(pair, start[idA], start[idA + 1] - start[idA], idB);
        if (index < 0) return 0;
        index <<= 1;
        return pair[index + 1];
    }

    /**
     * 获�?�共现频次
     * @param idA 第一个�?的id
     * @param idB 第二个�?的id
     * @return 共现频次
     */
    public static int getBiFrequency(int idA, int idB)
    {
        // 负数id表示�?�自用户�?典的�?语的�?频（用户自定义�?语没有id），返回正值增加其亲和度
        if (idA < 0)
        {
            return -idA;
        }
        if (idB < 0)
        {
            return -idB;
        }
        int index = binarySearch(pair, start[idA], start[idA + 1] - start[idA], idB);
        if (index < 0) return 0;
        index <<= 1;
        return pair[index + 1];
    }

    /**
     * 获�?��?语的ID
     *
     * @param a �?语
     * @return id
     */
    public static int getWordID(String a)
    {
        return CoreDictionary.trie.exactMatchSearch(a);
    }

    /**
     * 热更新二元接续�?典<br>
     *     集群环境（或其他IOAdapter）需�?自行删除缓存文件
     * @return 是�?��?功
     */
    public static boolean reload()
    {
        String biGramDictionaryPath = HanLP.Config.BiGramDictionaryPath;
        IOUtil.deleteFile(biGramDictionaryPath + ".table" + Predefine.BIN_EXT);

        return load(biGramDictionaryPath);
    }
}
