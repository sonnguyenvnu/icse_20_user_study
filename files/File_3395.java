/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-09-04 PM5:16</create-date>
 *
 * <copyright file="SentenceInstance.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron.instance;

import com.hankcs.hanlp.model.perceptron.feature.FeatureMap;
import com.hankcs.hanlp.model.perceptron.tagset.TagSet;

import java.util.List;

/**
 * @author hankcs
 */
public class Instance
{
    public int[][] featureMatrix;
    public int[] tagArray;

    protected Instance()
    {
    }

    protected static int[] toFeatureArray(List<Integer> featureVector)
    {
        int[] featureArray = new int[featureVector.size() + 1];   // 最�?�一列留给转移特�?
        int index = -1;
        for (Integer feature : featureVector)
        {
            featureArray[++index] = feature;
        }

        return featureArray;
    }

    public int[] getFeatureAt(int position)
    {
        return featureMatrix[position];
    }

    public int length()
    {
        return tagArray.length;
    }

    protected static void addFeature(CharSequence rawFeature, List<Integer> featureVector, FeatureMap featureMap)
    {
        int id = featureMap.idOf(rawFeature.toString());
        if (id != -1)
        {
            featureVector.add(id);
        }
    }

    /**
     * 添加特�?，�?�时清空缓存
     *
     * @param rawFeature
     * @param featureVector
     * @param featureMap
     */
    protected static void addFeatureThenClear(StringBuilder rawFeature, List<Integer> featureVector, FeatureMap featureMap)
    {
        int id = featureMap.idOf(rawFeature.toString());
        if (id != -1)
        {
            featureVector.add(id);
        }
        rawFeature.setLength(0);
    }

    /**
     * 根�?�标注集还原字符形�?的标签
     *
     * @param tagSet
     * @return
     */
    public String[] tags(TagSet tagSet)
    {
        assert tagArray != null;

        String[] tags = new String[tagArray.length];
        for (int i = 0; i < tags.length; i++)
        {
            tags[i] = tagSet.stringOf(tagArray[i]);
        }

        return tags;
    }

    /**
     * 实例大�?（有多少个�?预测的元素）
     *
     * @return
     */
    public int size()
    {
        return featureMatrix.length;
    }
}
