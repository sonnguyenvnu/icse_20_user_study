/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016/1/29 18:00</create-date>
 *
 * <copyright file="AbstractClassifier.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.classification.classifiers;

import com.hankcs.hanlp.classification.corpus.Document;
import com.hankcs.hanlp.classification.corpus.IDataSet;
import com.hankcs.hanlp.classification.corpus.MemoryDataSet;
import com.hankcs.hanlp.classification.models.AbstractModel;
import com.hankcs.hanlp.classification.utilities.CollectionUtility;
import com.hankcs.hanlp.utility.MathUtility;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static com.hankcs.hanlp.classification.utilities.io.ConsoleLogger.logger;

/**
 * @author hankcs
 */
public abstract class AbstractClassifier implements IClassifier
{
    @Override
    public IClassifier enableProbability(boolean enable)
    {
        return this;
    }

    /**
     * 是�?�计算概率
     */
    boolean configProbabilityEnabled = true;

    /**
     * 使用一个训练出�?�的分类器�?�预测分类
     *
     * @param text
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    @Override
    public String classify(String text) throws IllegalArgumentException, IllegalStateException
    {
        Map<String, Double> scoreMap = predict(text);

        return CollectionUtility.max(scoreMap);
    }

    @Override
    public String classify(Document document) throws IllegalArgumentException, IllegalStateException
    {
        Map<String, Double> scoreMap = predict(document);

        return CollectionUtility.max(scoreMap);
    }

    @Override
    public void train(String folderPath, String charsetName) throws IOException
    {
        IDataSet dataSet = new MemoryDataSet();
        dataSet.load(folderPath, charsetName);
        train(dataSet);
    }

    @Override
    public void train(Map<String, String[]> trainingDataSet) throws IllegalArgumentException
    {
        IDataSet dataSet = new MemoryDataSet();
        logger.start("正在构造训练数�?�集...");
        int total = trainingDataSet.size();
        int cur = 0;
        for (Map.Entry<String, String[]> entry : trainingDataSet.entrySet())
        {
            String category = entry.getKey();
            logger.out("[%s]...", category);
            for (String doc : entry.getValue())
            {
                dataSet.add(category, doc);
            }
            ++cur;
            logger.out("%.2f%%...", MathUtility.percentage(cur, total));
        }
        logger.finish(" 加载完毕\n");
        train(dataSet);
    }

    @Override
    public void train(String folderPath) throws IOException
    {
        train(folderPath, "UTF-8");
    }

    @Override
    public Map<String, Double> predict(Document document)
    {
        AbstractModel model = getModel();
        if (model == null)
        {
            throw new IllegalStateException("未训练模型�?无法执行预测�?");
        }
        if (document == null)
        {
            throw new IllegalArgumentException("�?�数 text == null");
        }

        double[] probs = categorize(document);
        Map<String, Double> scoreMap = new TreeMap<String, Double>();
        for (int i = 0; i < probs.length; i++)
        {
            scoreMap.put(model.catalog[i], probs[i]);
        }
        return scoreMap;
    }

    @Override
    public int label(Document document) throws IllegalArgumentException, IllegalStateException
    {
        AbstractModel model = getModel();
        if (model == null)
        {
            throw new IllegalStateException("未训练模型�?无法执行预测�?");
        }
        if (document == null)
        {
            throw new IllegalArgumentException("�?�数 text == null");
        }

        double[] probs = categorize(document);
        double max = Double.NEGATIVE_INFINITY;
        int best = -1;
        for (int i = 0; i < probs.length; i++)
        {
            if (probs[i] > max)
            {
                max = probs[i];
                best = i;
            }
        }
        return best;
    }
}
