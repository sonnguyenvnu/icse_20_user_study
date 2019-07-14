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
package com.hankcs.hanlp.dictionary.nr;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.corpus.dictionary.item.EnumItem;
import com.hankcs.hanlp.corpus.tag.NR;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.TransformMatrixDictionary;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;
import com.hankcs.hanlp.utility.Predefine;

import java.util.*;

import static com.hankcs.hanlp.corpus.tag.NR.*;
import static com.hankcs.hanlp.utility.Predefine.logger;
import static com.hankcs.hanlp.dictionary.nr.NRConstant.*;

/**
 * 人�??识别用的�?典，实际上是对两个�?典的包装
 *
 * @author hankcs
 */
public class PersonDictionary
{
    /**
     * 人�??�?典
     */
    public static NRDictionary dictionary;
    /**
     * 转移矩阵�?典
     */
    public static TransformMatrixDictionary<NR> transformMatrixDictionary;
    /**
     * AC算法用到的Trie树
     */
    public static AhoCorasickDoubleArrayTrie<NRPattern> trie;

    public static final CoreDictionary.Attribute ATTRIBUTE = new CoreDictionary.Attribute(Nature.nr, 100);

    static
    {
        long start = System.currentTimeMillis();
        dictionary = new NRDictionary();
        if (!dictionary.load(HanLP.Config.PersonDictionaryPath))
        {
            throw new IllegalArgumentException("人�??�?典加载失败：" + HanLP.Config.PersonDictionaryPath);
        }
        transformMatrixDictionary = new TransformMatrixDictionary<NR>(NR.class);
        transformMatrixDictionary.load(HanLP.Config.PersonDictionaryTrPath);
        trie = new AhoCorasickDoubleArrayTrie<NRPattern>();
        TreeMap<String, NRPattern> map = new TreeMap<String, NRPattern>();
        for (NRPattern pattern : NRPattern.values())
        {
            map.put(pattern.toString(), pattern);
        }
        trie.build(map);
        logger.info(HanLP.Config.PersonDictionaryPath + "加载�?功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 模�?匹�?
     *
     * @param nrList         确定的标注�?列
     * @param vertexList     原始的未加角色标注的�?列
     * @param wordNetOptimum 待优化的图
     * @param wordNetAll     全�?图
     */
    public static void parsePattern(List<NR> nrList, List<Vertex> vertexList, final WordNet wordNetOptimum, final WordNet wordNetAll)
    {
        // 拆分UV
        ListIterator<Vertex> listIterator = vertexList.listIterator();
        StringBuilder sbPattern = new StringBuilder(nrList.size());
        NR preNR = NR.A;
        boolean backUp = false;
        int index = 0;
        for (NR nr : nrList)
        {
            ++index;
            Vertex current = listIterator.next();
//            logger.trace("{}/{}", current.realWord, nr);
            switch (nr)
            {
                case U:
                    if (!backUp)
                    {
                        vertexList = new ArrayList<Vertex>(vertexList);
                        listIterator = vertexList.listIterator(index);
                        backUp = true;
                    }
                    sbPattern.append(NR.K.toString());
                    sbPattern.append(NR.B.toString());
                    preNR = B;
                    listIterator.previous();
                    String nowK = current.realWord.substring(0, current.realWord.length() - 1);
                    String nowB = current.realWord.substring(current.realWord.length() - 1);
                    listIterator.set(new Vertex(nowK));
                    listIterator.next();
                    listIterator.add(new Vertex(nowB));
                    continue;
                case V:
                    if (!backUp)
                    {
                        vertexList = new ArrayList<Vertex>(vertexList);
                        listIterator = vertexList.listIterator(index);
                        backUp = true;
                    }
                    if (preNR == B)
                    {
                        sbPattern.append(NR.E.toString());  //BE
                    }
                    else
                    {
                        sbPattern.append(NR.D.toString());  //CD
                    }
                    sbPattern.append(NR.L.toString());
                    // 对串也�?�一些修改
                    listIterator.previous();
                    String EorD = current.realWord.substring(0, 1);
                    String L = current.realWord.substring(1, current.realWord.length());
                    listIterator.set(new Vertex(EorD));
                    listIterator.next();
                    listIterator.add(new Vertex(L));
                    continue;
                default:
                    sbPattern.append(nr.toString());
                    break;
            }
            preNR = nr;
        }
        String pattern = sbPattern.toString();
//        logger.trace("模�?串：{}", pattern);
//        logger.trace("对应串：{}", vertexList);
//        if (pattern.length() != vertexList.size())
//        {
//            logger.warn("人�??识别模�?串有bug", pattern, vertexList);
//            return;
//        }
        final Vertex[] wordArray = vertexList.toArray(new Vertex[0]);
        final int[] offsetArray = new int[wordArray.length];
        offsetArray[0] = 0;
        for (int i = 1; i < wordArray.length; ++i)
        {
            offsetArray[i] = offsetArray[i - 1] + wordArray[i - 1].realWord.length();
        }
        trie.parseText(pattern, new AhoCorasickDoubleArrayTrie.IHit<NRPattern>()
        {
            @Override
            public void hit(int begin, int end, NRPattern value)
            {
//            logger.trace("匹�?到：{}", keyword);
                StringBuilder sbName = new StringBuilder();
                for (int i = begin; i < end; ++i)
                {
                    sbName.append(wordArray[i].realWord);
                }
                String name = sbName.toString();
//            logger.trace("识别出：{}", name);
                // 对一些bad case�?�出调整
                switch (value)
                {
                    case BCD:
                        if (name.charAt(0) == name.charAt(2)) return; // 姓和最�?�一个�??�?�?�能相等的
//                        String cd = name.substring(1);
//                        if (CoreDictionary.contains(cd))
//                        {
//                            EnumItem<NR> item = PersonDictionary.dictionary.get(cd);
//                            if (item == null || !item.containsLabel(Z)) return; // 三字�??字但是�?�两个字�?在�?典中，有很大�?�能性是误命中
//                        }
                        break;
                }
                if (isBadCase(name)) return;

                // 正�?算它是一个�??字
                if (HanLP.Config.DEBUG)
                {
                    System.out.printf("识别出人�??：%s %s\n", name, value);
                }
                int offset = offsetArray[begin];
                wordNetOptimum.insert(offset, new Vertex(Predefine.TAG_PEOPLE, name, ATTRIBUTE, WORD_ID), wordNetAll);
            }
        });
    }

    /**
     * 因为任何算法都无法解决100%的问题，总是有一些bad case，这些bad case会以“盖公章 A 1�?的形�?加入�?典中<BR>
     * 这个方法返回人�??是�?�是bad case
     *
     * @param name
     * @return
     */
    static boolean isBadCase(String name)
    {
        EnumItem<NR> nrEnumItem = dictionary.get(name);
        if (nrEnumItem == null) return false;
        return nrEnumItem.containsLabel(NR.A);
    }
}
