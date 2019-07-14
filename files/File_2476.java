package com.hankcs.hanlp.classification.features;

import com.hankcs.hanlp.algorithm.MaxHeap;
import com.hankcs.hanlp.classification.corpus.IDataSet;
import com.hankcs.hanlp.classification.statistics.ContinuousDistributions;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 特�?�??�?�，用�?�生�?FeatureStats并且使用�?�方测试选择最佳特�?
 */
public class ChiSquareFeatureExtractor
{
    /**
     * 在P值（拒真错误概率）为0.001时的�?�方临界值，用于特�?选择算法
     */
    protected double chisquareCriticalValue = 10.83;

    protected int maxSize = 1000000;

    /**
     * 生�?一个FeatureStats对象，包�?�一个分类中的所有�?语，分类数，实例数。这些统计数�?�
     * 将用于特�?选择算法。
     *
     * @param dataSet
     * @return
     */
    public static BaseFeatureData extractBasicFeatureData(IDataSet dataSet)
    {
        BaseFeatureData stats = new BaseFeatureData(dataSet);
        return stats;
    }

    /**
     * 使用�?�方�?��?�数校验�?�执行特�?选择<br>
     *     https://nlp.stanford.edu/IR-book/html/htmledition/feature-selectionchi2-feature-selection-1.html
     *
     * @param stats
     * @return
     */
    public Map<Integer, Double> chi_square(BaseFeatureData stats)
    {
        Map<Integer, Double> selectedFeatures = new HashMap<Integer, Double>();

        double N1dot, N0dot, N00, N01, N10, N11;
        double chisquareScore;
        Double previousScore;
        for (int feature = 0; feature < stats.featureCategoryJointCount.length; feature++)
        {
            int[] categoryList = stats.featureCategoryJointCount[feature];

            //计算 N1. (�?�有该特�?的文档数�?)
            N1dot = 0;
            for (int count : categoryList)
            {
                N1dot += count;
            }

            //还有 N0. (�?�?�该特�?的文档数�?)
            N0dot = stats.n - N1dot;

            for (int category = 0; category < categoryList.length; category++)
            {

                N11 = categoryList[category]; //N11 是�?�有该特�?并属于该类目的文档数�?
                N01 = stats.categoryCounts[category] - N11; //N01 是�?�?�该特�?�?�属于该类目的文档数�?

                N00 = N0dot - N01; //N00 是�?�?�该特�?也�?属于该类目的文档数�?
                N10 = N1dot - N11; //N10 是�?�有该特�?�?��?属于该类目的文档数�?

                //基于上述统计数�?�计算�?�方分值
                chisquareScore = stats.n * Math.pow(N11 * N00 - N10 * N01, 2) / ((N11 + N01) * (N11 + N10) * (N10 + N00) * (N01 + N00));

                //如果分数大于临界值则加入特�?列表
                if (chisquareScore >= chisquareCriticalValue)
                {
                    previousScore = selectedFeatures.get(feature);
                    if (previousScore == null || chisquareScore > previousScore)
                    {
                        selectedFeatures.put(feature, chisquareScore);
                    }
                }
            }
        }
        if (selectedFeatures.size() == 0) // 当特�?全部无法通过�?�方检测时，�?�全集作为特�?
        {
            for (int feature = 0; feature < stats.featureCategoryJointCount.length; feature++)
            {
                selectedFeatures.put(feature, 0.);
            }
        }
        if (selectedFeatures.size() > maxSize)
        {
            MaxHeap<Map.Entry<Integer, Double>> maxHeap = new MaxHeap<Map.Entry<Integer, Double>>(maxSize, new Comparator<Map.Entry<Integer, Double>>()
            {
                @Override
                public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            for (Map.Entry<Integer, Double> entry : selectedFeatures.entrySet())
            {
                maxHeap.add(entry);
            }
            selectedFeatures.clear();
            for (Map.Entry<Integer, Double> entry : maxHeap)
            {
                selectedFeatures.put(entry.getKey(), entry.getValue());
            }
        }

        return selectedFeatures;
    }

    /**
     * 获�?��?�方临界值
     *
     * @return
     */
    public double getChisquareCriticalValue()
    {
        return chisquareCriticalValue;
    }

    /**
     * 设置�?�方临界值
     *
     * @param chisquareCriticalValue
     */
    public void setChisquareCriticalValue(double chisquareCriticalValue)
    {
        this.chisquareCriticalValue = chisquareCriticalValue;
    }

    public ChiSquareFeatureExtractor setALevel(double aLevel)
    {
        chisquareCriticalValue = ContinuousDistributions.ChisquareInverseCdf(aLevel, 1);
        return this;
    }

    public double getALevel()
    {
        return ContinuousDistributions.ChisquareCdf(chisquareCriticalValue, 1);
    }
}
