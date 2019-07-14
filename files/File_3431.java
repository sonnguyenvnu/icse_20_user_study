/*
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2017-10-26 下�?�9:12</create-date>
 *
 * <copyright file="POSTrainer.java" company="�?农场">
 * Copyright (c) 2017, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron;

import com.hankcs.hanlp.model.perceptron.feature.FeatureMap;
import com.hankcs.hanlp.model.perceptron.instance.Instance;
import com.hankcs.hanlp.model.perceptron.instance.POSInstance;
import com.hankcs.hanlp.model.perceptron.tagset.POSTagSet;
import com.hankcs.hanlp.model.perceptron.tagset.TagSet;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;

import java.io.IOException;

/**
 * @author hankcs
 */
public class POSTrainer extends PerceptronTrainer
{
    @Override
    protected TagSet createTagSet()
    {
        return new POSTagSet();
    }

    @Override
    protected Instance createInstance(Sentence sentence, FeatureMap featureMap)
    {
        return POSInstance.create(sentence, featureMap);
    }

    @Override
    public Result train(String trainingFile, String developFile, String modelFile) throws IOException
    {
        // �?性标注模型压缩会显著�?低效果
        return train(trainingFile, developFile, modelFile, 0, 10, Runtime.getRuntime().availableProcessors());
    }
}
