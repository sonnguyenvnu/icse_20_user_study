/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-06-21 11:30 AM</create-date>
 *
 * <copyright file="PerceptronClassifier.java">
 * Copyright (c) 2018, Han He. All Rights Reserved, http://www.hankcs.com/
 * This source is subject to Han He. Please contact Han He for more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron;

import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.model.perceptron.common.TaskType;
import com.hankcs.hanlp.model.perceptron.feature.FeatureMap;
import com.hankcs.hanlp.model.perceptron.feature.LockableFeatureMap;
import com.hankcs.hanlp.model.perceptron.model.AveragedPerceptron;
import com.hankcs.hanlp.model.perceptron.model.LinearModel;
import com.hankcs.hanlp.model.perceptron.tagset.TagSet;
import com.hankcs.hanlp.model.perceptron.utility.Utility;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 感知机二分类器
 *
 * @author hankcs
 */
public abstract class PerceptronClassifier
{
    LinearModel model;

    public PerceptronClassifier()
    {
    }

    public PerceptronClassifier(LinearModel model)
    {
        if (model != null && model.taskType() != TaskType.CLASSIFICATION)
            throw new IllegalArgumentException("传入的模型并�?�分类模型");
        this.model = model;
    }

    public PerceptronClassifier(String modelPath) throws IOException
    {
        this(new LinearModel(modelPath));
    }

    /**
     * 朴素感知机训练算法
     *  @param instanceList 训练实例
     * @param featureMap   特�?函数
     * @param maxIteration 训练迭代次数
     */
    private static LinearModel trainNaivePerceptron(Instance[] instanceList, FeatureMap featureMap, int maxIteration)
    {
        LinearModel model = new LinearModel(featureMap, new float[featureMap.size()]);
        for (int it = 0; it < maxIteration; ++it)
        {
            Utility.shuffleArray(instanceList);
            for (Instance instance : instanceList)
            {
                int y = model.decode(instance.x);
                if (y != instance.y) // 误差�??馈
                    model.update(instance.x, instance.y);
            }
        }
        return model;
    }

    /**
     * 平�?�感知机训练算法
     *  @param instanceList 训练实例
     * @param featureMap   特�?函数
     * @param maxIteration 训练迭代次数
     */
    private static LinearModel trainAveragedPerceptron(Instance[] instanceList, FeatureMap featureMap, int maxIteration)
    {
        float[] parameter = new float[featureMap.size()];
        double[] sum = new double[featureMap.size()];
        int[] time = new int[featureMap.size()];

        AveragedPerceptron model = new AveragedPerceptron(featureMap, parameter);
        int t = 0;
        for (int it = 0; it < maxIteration; ++it)
        {
            Utility.shuffleArray(instanceList);
            for (Instance instance : instanceList)
            {
                ++t;
                int y = model.decode(instance.x);
                if (y != instance.y) // 误差�??馈
                    model.update(instance.x, instance.y, sum, time, t);
            }
        }
        model.average(sum, time, t);
        return model;
    }

    /**
     * 训练
     *
     * @param corpus       语料库
     * @param maxIteration 最大迭代次数
     * @return 模型在训练集上的准确率
     */
    public BinaryClassificationFMeasure train(String corpus, int maxIteration)
    {
        return train(corpus, maxIteration, true);
    }

    /**
     * 训练
     *
     * @param corpus            语料库
     * @param maxIteration      最大迭代次数
     * @param averagePerceptron 是�?�使用平�?�感知机算法
     * @return 模型在训练集上的准确率
     */
    public BinaryClassificationFMeasure train(String corpus, int maxIteration, boolean averagePerceptron)
    {
        FeatureMap featureMap = new LockableFeatureMap(new TagSet(TaskType.CLASSIFICATION));
        featureMap.mutable = true; // 训练时特�?映射�?�拓充
        Instance[] instanceList = readInstance(corpus, featureMap);
        model = averagePerceptron ? trainAveragedPerceptron(instanceList, featureMap, maxIteration)
            : trainNaivePerceptron(instanceList, featureMap, maxIteration);
        featureMap.mutable = false; // 训练结�?��?�特�?�?�?�写
        return evaluate(instanceList);
    }

    /**
     * 预测
     *
     * @param text
     * @return
     */
    public String predict(String text)
    {
        int y = model.decode(extractFeature(text, model.featureMap));
        if (y == -1)
            y = 0;
        return model.tagSet().stringOf(y);
    }

    /**
     * 评估
     *
     * @param corpus
     * @return
     */
    public BinaryClassificationFMeasure evaluate(String corpus)
    {
        Instance[] instanceList = readInstance(corpus, model.featureMap);
        return evaluate(instanceList);
    }

    /**
     * 评估
     *
     * @param instanceList
     * @return
     */
    public BinaryClassificationFMeasure evaluate(Instance[] instanceList)
    {
        int TP = 0, FP = 0, FN = 0;
        for (Instance instance : instanceList)
        {
            int y = model.decode(instance.x);
            if (y == 1)
            {
                if (instance.y == 1)
                    ++TP;
                else
                    ++FP;
            }
            else if (instance.y == 1)
                ++FN;
        }
        float p = TP / (float) (TP + FP) * 100;
        float r = TP / (float) (TP + FN) * 100;
        return new BinaryClassificationFMeasure(p, r, 2 * p * r / (p + r));
    }

    /**
     * 从语料库读�?�实例
     *
     * @param corpus     语料库
     * @param featureMap 特�?映射
     * @return 数�?�集
     */
    private Instance[] readInstance(String corpus, FeatureMap featureMap)
    {
        IOUtil.LineIterator lineIterator = new IOUtil.LineIterator(corpus);
        List<Instance> instanceList = new LinkedList<Instance>();
        for (String line : lineIterator)
        {
            String[] cells = line.split(",");
            String text = cells[0], label = cells[1];
            List<Integer> x = extractFeature(text, featureMap);
            int y = featureMap.tagSet.add(label);
            if (y == 0)
                y = -1; // 感知机标签约定为±1
            else if (y > 1)
                throw new IllegalArgumentException("类别数大于2，目�?�?�支�?二分类。");
            instanceList.add(new Instance(x, y));
        }
        return instanceList.toArray(new Instance[0]);
    }

    /**
     * 特�?�??�?�
     *
     * @param text       文本
     * @param featureMap 特�?映射
     * @return 特�?�?��?
     */
    protected abstract List<Integer> extractFeature(String text, FeatureMap featureMap);

    /**
     * �?�特�?�?��?�?�入特�?
     *
     * @param feature     特�?
     * @param featureMap  特�?映射
     * @param featureList 特�?�?��?
     */
    protected static void addFeature(String feature, FeatureMap featureMap, List<Integer> featureList)
    {
        int featureId = featureMap.idOf(feature);
        if (featureId != -1)
            featureList.add(featureId);
    }

    /**
     * 样本
     */
    static class Instance
    {
        /**
         * 特�?�?��?
         */
        List<Integer> x;
        /**
         * 标签
         */
        int y;

        public Instance(List<Integer> x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 准确率度�?
     */
    static class BinaryClassificationFMeasure
    {
        float P, R, F1;

        public BinaryClassificationFMeasure(float p, float r, float f1)
        {
            P = p;
            R = r;
            F1 = f1;
        }

        @Override
        public String toString()
        {
            return String.format("P=%.2f R=%.2f F1=%.2f", P, R, F1);
        }
    }

    public LinearModel getModel()
    {
        return model;
    }
}
