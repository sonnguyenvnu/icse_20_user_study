/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/10 14:47</create-date>
 *
 * <copyright file="PersonDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.ns;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.corpus.dictionary.item.EnumItem;
import com.hankcs.hanlp.corpus.tag.NS;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.TransformMatrixDictionary;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;
import com.hankcs.hanlp.utility.Predefine;

import java.util.*;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 地�??识别用的�?典，实际上是对两个�?典的包装
 *
 * @author hankcs
 */
public class PlaceDictionary
{
    /**
     * 地�??�?典
     */
    public static NSDictionary dictionary;
    /**
     * 转移矩阵�?典
     */
    public static TransformMatrixDictionary<NS> transformMatrixDictionary;
    /**
     * AC算法用到的Trie树
     */
    public static AhoCorasickDoubleArrayTrie<String> trie;

    /**
     * 本�?典专注的�?的ID
     */
    static final int WORD_ID = CoreDictionary.getWordID(Predefine.TAG_PLACE);
    /**
     * 本�?典专注的�?的属性
     */
    static final CoreDictionary.Attribute ATTRIBUTE = CoreDictionary.get(WORD_ID);

    static
    {
        long start = System.currentTimeMillis();
        dictionary = new NSDictionary();
        if (dictionary.load(HanLP.Config.PlaceDictionaryPath))
            logger.info(HanLP.Config.PlaceDictionaryPath + "加载�?功，耗时" + (System.currentTimeMillis() - start) + "ms");
        else
            throw new IllegalArgumentException(HanLP.Config.PlaceDictionaryPath + "加载失败");
        transformMatrixDictionary = new TransformMatrixDictionary<NS>(NS.class);
        transformMatrixDictionary.load(HanLP.Config.PlaceDictionaryTrPath);
        trie = new AhoCorasickDoubleArrayTrie<String>();
        TreeMap<String, String> patternMap = new TreeMap<String, String>();
        patternMap.put("CH", "CH");
        patternMap.put("CDH", "CDH");
        patternMap.put("CDEH", "CDEH");
        patternMap.put("GH", "GH");
        trie.build(patternMap);
    }

    /**
     * 模�?匹�?
     *
     * @param nsList         确定的标注�?列
     * @param vertexList     原始的未加角色标注的�?列
     * @param wordNetOptimum 待优化的图
     * @param wordNetAll
     */
    public static void parsePattern(List<NS> nsList, List<Vertex> vertexList, final WordNet wordNetOptimum, final WordNet wordNetAll)
    {
//        ListIterator<Vertex> listIterator = vertexList.listIterator();
        StringBuilder sbPattern = new StringBuilder(nsList.size());
        for (NS ns : nsList)
        {
            sbPattern.append(ns.toString());
        }
        String pattern = sbPattern.toString();
        final Vertex[] wordArray = vertexList.toArray(new Vertex[0]);
        trie.parseText(pattern, new AhoCorasickDoubleArrayTrie.IHit<String>()
        {
            @Override
            public void hit(int begin, int end, String value)
            {
                StringBuilder sbName = new StringBuilder();
                for (int i = begin; i < end; ++i)
                {
                    sbName.append(wordArray[i].realWord);
                }
                String name = sbName.toString();
                // 对一些bad case�?�出调整
                if (isBadCase(name)) return;

                // 正�?算它是一个�??字
                if (HanLP.Config.DEBUG)
                {
                    System.out.printf("识别出地�??：%s %s\n", name, value);
                }
                int offset = 0;
                for (int i = 0; i < begin; ++i)
                {
                    offset += wordArray[i].realWord.length();
                }
                wordNetOptimum.insert(offset, new Vertex(Predefine.TAG_PLACE, name, ATTRIBUTE, WORD_ID), wordNetAll);
            }
        });
    }

    /**
     * 因为任何算法都无法解决100%的问题，总是有一些bad case，这些bad case会以“盖公章 A 1�?的形�?加入�?典中<BR>
     * 这个方法返回是�?�是bad case
     *
     * @param name
     * @return
     */
    static boolean isBadCase(String name)
    {
        EnumItem<NS> nrEnumItem = dictionary.get(name);
        if (nrEnumItem == null) return false;
        return nrEnumItem.containsLabel(NS.Z);
    }
}
