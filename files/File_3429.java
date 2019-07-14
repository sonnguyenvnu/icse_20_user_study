/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-09-05 PM7:56</create-date>
 *
 * <copyright file="AveragedPerceptronSegment.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.perceptron.feature.FeatureMap;
import com.hankcs.hanlp.model.perceptron.instance.CWSInstance;
import com.hankcs.hanlp.model.perceptron.model.LinearModel;
import com.hankcs.hanlp.model.perceptron.common.TaskType;
import com.hankcs.hanlp.model.perceptron.instance.Instance;
import com.hankcs.hanlp.model.perceptron.tagset.CWSTagSet;
import com.hankcs.hanlp.model.perceptron.utility.Utility;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.tokenizer.lexical.Segmenter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 中文分�?
 *
 * @author hankcs
 */
public class PerceptronSegmenter extends PerceptronTagger implements Segmenter
{
    private final CWSTagSet CWSTagSet;

    public PerceptronSegmenter(LinearModel cwsModel)
    {
        super(cwsModel);
        if (cwsModel.featureMap.tagSet.type != TaskType.CWS)
        {
            throw new IllegalArgumentException(String.format("错误的模型类型: 传入的�?是分�?模型，而是 %s 模型", cwsModel.featureMap.tagSet.type));
        }
        CWSTagSet = (CWSTagSet) cwsModel.featureMap.tagSet;
    }

    public PerceptronSegmenter(String cwsModelFile) throws IOException
    {
        this(new LinearModel(cwsModelFile));
    }

    /**
     * 加载�?置文件指定的模型
     * @throws IOException
     */
    public PerceptronSegmenter() throws IOException
    {
        this(HanLP.Config.PerceptronCWSModelPath);
    }

    public void segment(String text, List<String> output)
    {
        String normalized = normalize(text);
        segment(text, normalized, output);
    }

    public void segment(String text, String normalized, List<String> output)
    {
        if (text.isEmpty()) return;
        Instance instance = new CWSInstance(normalized, model.featureMap);
        segment(text, instance, output);
    }

    public void segment(String text, Instance instance, List<String> output)
    {
        int[] tagArray = instance.tagArray;
        model.viterbiDecode(instance, tagArray);

        StringBuilder result = new StringBuilder();
        result.append(text.charAt(0));

        for (int i = 1; i < tagArray.length; i++)
        {
            if (tagArray[i] == CWSTagSet.B || tagArray[i] == CWSTagSet.S)
            {
                output.add(result.toString());
                result.setLength(0);
            }
            result.append(text.charAt(i));
        }
        if (result.length() != 0)
        {
            output.add(result.toString());
        }
    }

    public List<String> segment(String sentence)
    {
        List<String> result = new LinkedList<String>();
        segment(sentence, result);
        return result;
    }

    /**
     * 在线学习
     *
     * @param segmentedSentence 分好�?的�?��?，空格或tab分割，�?�?��?性
     * @return 是�?�学习�?功（失败的原因是�?�数错误）
     */
    public boolean learn(String segmentedSentence)
    {
        return learn(segmentedSentence.split("\\s+"));
    }

    /**
     * 在线学习
     *
     * @param words 分好�?的�?��?
     * @return 是�?�学习�?功（失败的原因是�?�数错误）
     */
    public boolean learn(String... words)
    {
//        for (int i = 0; i < words.length; i++) // 防止传入带�?性的�?语
//        {
//            int index = words[i].indexOf('/');
//            if (index > 0)
//            {
//                words[i] = words[i].substring(0, index);
//            }
//        }
        return learn(new CWSInstance(words, model.featureMap));
    }

    @Override
    protected Instance createInstance(Sentence sentence, FeatureMap featureMap)
    {
        return CWSInstance.create(sentence, featureMap);
    }

    @Override
    public double[] evaluate(String corpora) throws IOException
    {
        // 这里用CWS的F1
        double[] prf = Utility.prf(Utility.evaluateCWS(corpora, this));
        return prf;
    }
}
