package com.hankcs.hanlp.classification.classifiers;

import com.hankcs.hanlp.utility.MathUtility;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.classification.corpus.*;
import com.hankcs.hanlp.classification.features.ChiSquareFeatureExtractor;
import com.hankcs.hanlp.classification.features.BaseFeatureData;
import com.hankcs.hanlp.classification.models.AbstractModel;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;

import static com.hankcs.hanlp.classification.utilities.io.ConsoleLogger.logger;

import java.util.*;

/**
 * 实现一个基于多项�?�?�?�斯模型的文本分类器
 */
public class NaiveBayesClassifier extends AbstractClassifier
{

    private NaiveBayesModel model;

    /**
     * 由训练结果构造一个�?�?�斯分类器，通常用�?�加载�?盘中的分类器
     *
     * @param naiveBayesModel
     */
    public NaiveBayesClassifier(NaiveBayesModel naiveBayesModel)
    {
        this.model = naiveBayesModel;
    }

    /**
     * 构造一个空白的�?�?�斯分类器，通常准备用�?�进行训练
     */
    public NaiveBayesClassifier()
    {
        this(null);
    }

    /**
     * 获�?�训练结果
     *
     * @return
     */
    public NaiveBayesModel getNaiveBayesModel()
    {
        return model;
    }

    public void train(IDataSet dataSet)
    {
        logger.out("原始数�?�集大�?:%d\n", dataSet.size());
        //选择最佳特�?
        BaseFeatureData featureData = selectFeatures(dataSet);

        //�?始化分类器所用的数�?�
        model = new NaiveBayesModel();
        model.n = featureData.n; //样本数�?
        model.d = featureData.featureCategoryJointCount.length; //特�?数�?

        model.c = featureData.categoryCounts.length; //类目数�?
        model.logPriors = new TreeMap<Integer, Double>();

        int sumCategory;
        for (int category = 0; category < featureData.categoryCounts.length; category++)
        {
            sumCategory = featureData.categoryCounts[category];
            model.logPriors.put(category, Math.log((double) sumCategory / model.n));
        }

        //拉普拉斯平滑处�?�（�?�称加一平滑），这时需�?估计�?个类目下的实例
        Map<Integer, Double> featureOccurrencesInCategory = new TreeMap<Integer, Double>();

        Double featureOccSum;
        for (Integer category : model.logPriors.keySet())
        {
            featureOccSum = 0.0;
            for (int feature = 0; feature < featureData.featureCategoryJointCount.length; feature++)
            {

                featureOccSum += featureData.featureCategoryJointCount[feature][category];
            }
            featureOccurrencesInCategory.put(category, featureOccSum);
        }

        //对数似然估计
        int count;
        int[] featureCategoryCounts;
        double logLikelihood;
        for (Integer category : model.logPriors.keySet())
        {
            for (int feature = 0; feature < featureData.featureCategoryJointCount.length; feature++)
            {

                featureCategoryCounts = featureData.featureCategoryJointCount[feature];

                count = featureCategoryCounts[category];

                logLikelihood = Math.log((count + 1.0) / (featureOccurrencesInCategory.get(category) + model.d));
                if (!model.logLikelihoods.containsKey(feature))
                {
                    model.logLikelihoods.put(feature, new TreeMap<Integer, Double>());
                }
                model.logLikelihoods.get(feature).put(category, logLikelihood);
            }
        }
        logger.out("�?�?�斯统计结�?�\n");
        model.catalog = dataSet.getCatalog().toArray();
        model.tokenizer = dataSet.getTokenizer();
        model.wordIdTrie = featureData.wordIdTrie;
    }

    public AbstractModel getModel()
    {
        return model;
    }

    public Map<String, Double> predict(String text) throws IllegalArgumentException, IllegalStateException
    {
        if (model == null)
        {
            throw new IllegalStateException("未训练模型�?无法执行预测�?");
        }
        if (text == null)
        {
            throw new IllegalArgumentException("�?�数 text == null");
        }

        //分�?，创建文档
        Document doc = new Document(model.wordIdTrie, model.tokenizer.segment(text));

        return predict(doc);
    }

    @Override
    public double[] categorize(Document document) throws IllegalArgumentException, IllegalStateException
    {
        Integer category;
        Integer feature;
        Integer occurrences;
        Double logprob;

        double[] predictionScores = new double[model.catalog.length];
        for (Map.Entry<Integer, Double> entry1 : model.logPriors.entrySet())
        {
            category = entry1.getKey();
            logprob = entry1.getValue(); //用类目的对数似然�?始化概率

            //对文档中的�?个特�?
            for (Map.Entry<Integer, int[]> entry2 : document.tfMap.entrySet())
            {
                feature = entry2.getKey();

                if (!model.logLikelihoods.containsKey(feature))
                {
                    continue; //如果在模型中找�?到就跳过了
                }

                occurrences = entry2.getValue()[0]; //获�?�其在文档中的频次

                logprob += occurrences * model.logLikelihoods.get(feature).get(category); //将对数似然乘上频次
            }
            predictionScores[category] = logprob;
        }

        if (configProbabilityEnabled) MathUtility.normalizeExp(predictionScores);
        return predictionScores;
    }

    /**
     * 统计特�?并且执行特�?选择，返回一个FeatureStats对象，用于计算模型中的概率
     *
     * @param dataSet
     * @return
     */
    protected BaseFeatureData selectFeatures(IDataSet dataSet)
    {
        ChiSquareFeatureExtractor chiSquareFeatureExtractor = new ChiSquareFeatureExtractor();

        logger.start("使用�?�方检测选择特�?中...");
        //FeatureStats对象包�?�文档中所有特�?�?�其统计信�?�
        BaseFeatureData featureData = chiSquareFeatureExtractor.extractBasicFeatureData(dataSet); //执行统计

        //我们传入这些统计信�?�到特�?选择算法中，得到特�?与其分值
        Map<Integer, Double> selectedFeatures = chiSquareFeatureExtractor.chi_square(featureData);

        //从统计数�?�中删掉无用的特�?并�?建特�?映射表
        int[][] featureCategoryJointCount = new int[selectedFeatures.size()][];
        featureData.wordIdTrie = new BinTrie<Integer>();
        String[] wordIdArray = dataSet.getLexicon().getWordIdArray();
        int p = -1;
        for (Integer feature : selectedFeatures.keySet())
        {
            featureCategoryJointCount[++p] = featureData.featureCategoryJointCount[feature];
            featureData.wordIdTrie.put(wordIdArray[feature], p);
        }
        logger.finish(",选中特�?数:%d / %d = %.2f%%\n", featureCategoryJointCount.length,
                      featureData.featureCategoryJointCount.length,
                      featureCategoryJointCount.length / (double)featureData.featureCategoryJointCount.length * 100.);
        featureData.featureCategoryJointCount = featureCategoryJointCount;

        return featureData;
    }
}
