/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/11 17:20</create-date>
 *
 * <copyright file="BigramDependencyModel.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.bigram;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.dependency.model.WordNatureWeightModelMaker;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.utility.Predefine;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.TreeMap;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 2-gram�?存模型，根�?�两个�?的�?和�?性猜测它们最�?�能的�?存关系
 * @author hankcs
 */
public class BigramDependencyModel
{
    static DoubleArrayTrie<String> trie;

    static
    {
        long start = System.currentTimeMillis();
        if (load(HanLP.Config.WordNatureModelPath))
        {
            logger.info("加载�?存�?�法二元模型" + HanLP.Config.WordNatureModelPath + "�?功，耗时：" + (System.currentTimeMillis() - start) + " ms");
        }
        else
        {
            logger.warning("加载�?存�?�法二元模型" + HanLP.Config.WordNatureModelPath + "失败，耗时：" + (System.currentTimeMillis() - start) + " ms");
        }
    }

    static boolean load(String path)
    {
        trie = new DoubleArrayTrie<String>();
        if (loadDat(path + ".bi" + Predefine.BIN_EXT)) return true;
        TreeMap<String, String> map = new TreeMap<String, String>();
        for (String line : IOUtil.readLineListWithLessMemory(path))
        {
            String[] param = line.split(" ");
            if (param[0].endsWith("@"))
            {
                continue;
            }
            String dependency = param[1];
            map.put(param[0], dependency);
        }
        if (map.size() == 0) return false;
        trie.build(map);
        if (!saveDat(path, map)) logger.warning("缓存" + path + Predefine.BIN_EXT + "失败");
        return true;
    }

    private static boolean loadDat(String path)
    {
        ByteArray byteArray = ByteArray.createByteArray(path);
        if (byteArray == null) return false;
        int size = byteArray.nextInt();
        String[] valueArray = new String[size];
        for (int i = 0; i < valueArray.length; ++i)
        {
            valueArray[i] = byteArray.nextUTF();
        }
        return trie.load(byteArray, valueArray);
    }

    static boolean saveDat(String path, TreeMap<String, String> map)
    {
        Collection<String> dependencyList = map.values();
        // 缓存值文件
        try
        {
            DataOutputStream out = new DataOutputStream(IOUtil.newOutputStream(path +  ".bi" + Predefine.BIN_EXT));
            out.writeInt(dependencyList.size());
            for (String dependency : dependencyList)
            {
                out.writeUTF(dependency);
            }
            if (!trie.save(out)) return false;
            out.close();
        }
        catch (Exception e)
        {
            logger.warning("�?存失败" + e);
            return false;
        }
        return true;
    }

    public static String get(String key)
    {
        return trie.get(key);
    }

    /**
     * 获�?�一个�?和�?�一个�?最�?�能的�?存关系
     * @param fromWord
     * @param fromPos
     * @param toWord
     * @param toPos
     * @return
     */
    public static String get(String fromWord, String fromPos, String toWord, String toPos)
    {
        String dependency = get(fromWord + "@" + toWord);
        if (dependency == null) dependency = get(fromWord + "@" + WordNatureWeightModelMaker.wrapTag(toPos));
        if (dependency == null) dependency = get(WordNatureWeightModelMaker.wrapTag(fromPos) + "@" + toWord);
        if (dependency == null) dependency = get(WordNatureWeightModelMaker.wrapTag(fromPos) + "@" + WordNatureWeightModelMaker.wrapTag(toPos));
        if (dependency == null) dependency = "未知";

        return dependency;
    }
}
