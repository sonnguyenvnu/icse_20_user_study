/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/17 19:34</create-date>
 *
 * <copyright file="PlaceRecognition.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.liNTunsoft.com/
 * This source is subject to the LiNTunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.recognition.nt;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.algorithm.Viterbi;
import com.hankcs.hanlp.corpus.dictionary.item.EnumItem;
import com.hankcs.hanlp.corpus.tag.NT;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.nt.OrganizationDictionary;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.hankcs.hanlp.corpus.tag.Nature.*;

/**
 * 地�?�识别
 *
 * @author hankcs
 */
public class OrganizationRecognition
{
    public static boolean recognition(List<Vertex> pWordSegResult, WordNet wordNetOptimum, WordNet wordNetAll)
    {
        List<EnumItem<NT>> roleTagList = roleTag(pWordSegResult, wordNetAll);
        if (HanLP.Config.DEBUG)
        {
            StringBuilder sbLog = new StringBuilder();
            Iterator<Vertex> iterator = pWordSegResult.iterator();
            for (EnumItem<NT> NTEnumItem : roleTagList)
            {
                sbLog.append('[');
                sbLog.append(iterator.next().realWord);
                sbLog.append(' ');
                sbLog.append(NTEnumItem);
                sbLog.append(']');
            }
            System.out.printf("机构�??角色观察：%s\n", sbLog.toString());
        }
        List<NT> NTList = viterbiCompute(roleTagList);
        if (HanLP.Config.DEBUG)
        {
            StringBuilder sbLog = new StringBuilder();
            Iterator<Vertex> iterator = pWordSegResult.iterator();
            sbLog.append('[');
            for (NT NT : NTList)
            {
                sbLog.append(iterator.next().realWord);
                sbLog.append('/');
                sbLog.append(NT);
                sbLog.append(" ,");
            }
            if (sbLog.length() > 1) sbLog.delete(sbLog.length() - 2, sbLog.length());
            sbLog.append(']');
            System.out.printf("机构�??角色标注：%s\n", sbLog.toString());
        }

        OrganizationDictionary.parsePattern(NTList, pWordSegResult, wordNetOptimum, wordNetAll);
        return true;
    }

    public static List<EnumItem<NT>> roleTag(List<Vertex> vertexList, WordNet wordNetAll)
    {
        List<EnumItem<NT>> tagList = new LinkedList<EnumItem<NT>>();
        //        int line = 0;
        for (Vertex vertex : vertexList)
        {
            // 构�?更长的
            Nature nature = vertex.guessNature();
            if (nature == nrf)
            {
                if (vertex.getAttribute().totalFrequency <= 1000)
                {
                    tagList.add(new EnumItem<NT>(NT.F, 1000));
                    continue;
                }
            }
            else if (nature == ni || nature == nic || nature == nis || nature == nit)
            {
                EnumItem<NT> ntEnumItem = new EnumItem<NT>(NT.K, 1000);
                ntEnumItem.addLabel(NT.D, 1000);
                tagList.add(ntEnumItem);
                continue;
            }
            else if (nature == m)
            {
                EnumItem<NT> ntEnumItem = new EnumItem<NT>(NT.M, 1000);
                tagList.add(ntEnumItem);
                continue;
            }

            EnumItem<NT> NTEnumItem = OrganizationDictionary.dictionary.get(vertex.word);  // 此处用等效�?，更加精准
            if (NTEnumItem == null)
            {
                NTEnumItem = new EnumItem<NT>(NT.Z, OrganizationDictionary.transformMatrixDictionary.getTotalFrequency(NT.Z));
            }
            tagList.add(NTEnumItem);
//            line += vertex.realWord.length();
        }
        return tagList;
    }

    /**
     * 维特比算法求解最优标签
     *
     * @param roleTagList
     * @return
     */
    public static List<NT> viterbiCompute(List<EnumItem<NT>> roleTagList)
    {
        return Viterbi.computeEnum(roleTagList, OrganizationDictionary.transformMatrixDictionary);
    }
}
