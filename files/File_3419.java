/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-06-21 9:08 AM</create-date>
 *
 * <copyright file="PerceptronNameGenderClassifier.java">
 * Copyright (c) 2018, Han He. All Rights Reserved, http://www.hankcs.com/
 * This source is subject to Han He. Please contact Han He for more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron;

import com.hankcs.hanlp.model.perceptron.feature.FeatureMap;
import com.hankcs.hanlp.model.perceptron.model.LinearModel;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 基于感知机的人�??性别分类器，预测人�??的性别
 *
 * @author hankcs
 */
public class PerceptronNameGenderClassifier extends PerceptronClassifier
{
    public PerceptronNameGenderClassifier()
    {
    }

    public PerceptronNameGenderClassifier(LinearModel model)
    {
        super(model);
    }

    public PerceptronNameGenderClassifier(String modelPath) throws IOException
    {
        super(modelPath);
    }

    @Override
    protected List<Integer> extractFeature(String text, FeatureMap featureMap)
    {
        List<Integer> featureList = new LinkedList<Integer>();
        String givenName = extractGivenName(text);
        // 特�?模�?�1：g[0]
        addFeature("1" + givenName.substring(0, 1), featureMap, featureList);
        // 特�?模�?�2：g[1]
        addFeature("2" + givenName.substring(1), featureMap, featureList);
        // 特�?模�?�3：g
//        addFeature("3" + givenName, featureMap, featureList);
        // �??置特�?（代表标签的先验分布，当样本�?�?�衡时有用，但此处的男女预测无用）
//        addFeature("b", featureMap, featureList);
        return featureList;
    }

    /**
     * 去掉姓�?，截�?�中国人�??中的�??字
     *
     * @param name 姓�??
     * @return �??
     */
    public static String extractGivenName(String name)
    {
        if (name.length() <= 2)
            return "_" + name.substring(name.length() - 1);
        else
            return name.substring(name.length() - 2);

    }
}
