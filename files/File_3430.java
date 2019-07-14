/*
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2017-10-26 下�?�5:51</create-date>
 *
 * <copyright file="PerceptronTrainer.java" company="�?农场">
 * Copyright (c) 2017, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.perceptron.common.FrequencyMap;
import com.hankcs.hanlp.model.perceptron.feature.ImmutableFeatureMap;
import com.hankcs.hanlp.model.perceptron.feature.MutableFeatureMap;
import com.hankcs.hanlp.model.perceptron.instance.Instance;
import com.hankcs.hanlp.model.perceptron.model.AveragedPerceptron;
import com.hankcs.hanlp.model.perceptron.model.LinearModel;
import com.hankcs.hanlp.model.perceptron.model.StructuredPerceptron;
import com.hankcs.hanlp.model.perceptron.tagset.TagSet;
import com.hankcs.hanlp.model.perceptron.utility.IOUtility;
import com.hankcs.hanlp.model.perceptron.instance.InstanceHandler;
import com.hankcs.hanlp.model.perceptron.utility.Utility;
import com.hankcs.hanlp.classification.utilities.io.ConsoleLogger;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * 感知机训练基类
 *
 * @author hankcs
 */
public abstract class PerceptronTrainer extends InstanceConsumer
{

    /**
     * 训练结果
     */
    public static class Result
    {
        /**
         * 模型
         */
        LinearModel model;
        /**
         * 精确率(Precision), �?�回率(Recall)和F1-Measure<br>
         * 中文�?�考：https://blog.argcv.com/articles/1036.c
         */
        double prf[];

        public Result(LinearModel model, double[] prf)
        {
            this.model = model;
            this.prf = prf;
        }

        /**
         * 获�?�准确率
         *
         * @return
         */
        public double getAccuracy()
        {
            if (prf.length == 3)
            {
                return prf[2];
            }
            return prf[0];
        }

        /**
         * 获�?�模型
         *
         * @return
         */
        public LinearModel getModel()
        {
            return model;
        }
    }

    /**
     * 创建标注集
     *
     * @return
     */
    protected abstract TagSet createTagSet();

    /**
     * 训练
     *
     * @param trainingFile  训练集
     * @param developFile   开�?�集
     * @param modelFile     模型�?存路径
     * @param compressRatio 压缩比
     * @param maxIteration  最大迭代次数
     * @param threadNum     线程数
     * @return 一个包�?�模型和精度的结构
     * @throws IOException
     */
    public Result train(String trainingFile, String developFile,
                        String modelFile, final double compressRatio,
                        final int maxIteration, final int threadNum) throws IOException
    {
        if (developFile == null)
        {
            developFile = trainingFile;
        }
        // 加载训练语料
        TagSet tagSet = createTagSet();
        MutableFeatureMap mutableFeatureMap = new MutableFeatureMap(tagSet);
        ConsoleLogger logger = new ConsoleLogger();
        logger.start("开始加载训练集...\n");
        Instance[] instances = loadTrainInstances(trainingFile, mutableFeatureMap);
        tagSet.lock();
        logger.finish("\n加载完毕，实例一共%d�?�，特�?总数%d\n", instances.length, mutableFeatureMap.size() * tagSet.size());

        // 开始训练
        ImmutableFeatureMap immutableFeatureMap = new ImmutableFeatureMap(mutableFeatureMap.featureIdMap, tagSet);
        mutableFeatureMap = null;
        double[] accuracy = null;

        if (threadNum == 1)
        {
            AveragedPerceptron model;
            model = new AveragedPerceptron(immutableFeatureMap);
            final double[] total = new double[model.parameter.length];
            final int[] timestamp = new int[model.parameter.length];
            int current = 0;
            for (int iter = 1; iter <= maxIteration; iter++)
            {
                Utility.shuffleArray(instances);
                for (Instance instance : instances)
                {
                    ++current;
                    int[] guessLabel = new int[instance.length()];
                    model.viterbiDecode(instance, guessLabel);
                    for (int i = 0; i < instance.length(); i++)
                    {
                        int[] featureVector = instance.getFeatureAt(i);
                        int[] goldFeature = new int[featureVector.length];
                        int[] predFeature = new int[featureVector.length];
                        for (int j = 0; j < featureVector.length - 1; j++)
                        {
                            goldFeature[j] = featureVector[j] * tagSet.size() + instance.tagArray[i];
                            predFeature[j] = featureVector[j] * tagSet.size() + guessLabel[i];
                        }
                        goldFeature[featureVector.length - 1] = (i == 0 ? tagSet.bosId() : instance.tagArray[i - 1]) * tagSet.size() + instance.tagArray[i];
                        predFeature[featureVector.length - 1] = (i == 0 ? tagSet.bosId() : guessLabel[i - 1]) * tagSet.size() + guessLabel[i];
                        model.update(goldFeature, predFeature, total, timestamp, current);
                    }
                }

                // 在开�?�集上校验
                accuracy = trainingFile.equals(developFile) ? IOUtility.evaluate(instances, model) : evaluate(developFile, model);
                out.printf("Iter#%d - ", iter);
                printAccuracy(accuracy);
            }
            // 平�?�
            model.average(total, timestamp, current);
            accuracy = trainingFile.equals(developFile) ? IOUtility.evaluate(instances, model) : evaluate(developFile, model);
            out.print("AP - ");
            printAccuracy(accuracy);
            logger.start("以压缩比 %.2f �?存模型到 %s ... ", compressRatio, modelFile);
            model.save(modelFile, immutableFeatureMap.featureIdMap.entrySet(), compressRatio);
            logger.finish(" �?存完毕\n");
            if (compressRatio == 0) return new Result(model, accuracy);
        }
        else
        {
            // 多线程用Structure Perceptron
            StructuredPerceptron[] models = new StructuredPerceptron[threadNum];
            for (int i = 0; i < models.length; i++)
            {
                models[i] = new StructuredPerceptron(immutableFeatureMap);
            }

            TrainingWorker[] workers = new TrainingWorker[threadNum];
            int job = instances.length / threadNum;
            for (int iter = 1; iter <= maxIteration; iter++)
            {
                Utility.shuffleArray(instances);
                try
                {
                    for (int i = 0; i < workers.length; i++)
                    {
                        workers[i] = new TrainingWorker(instances, i * job,
                                                        i == workers.length - 1 ? instances.length : (i + 1) * job,
                                                        models[i]);
                        workers[i].start();
                    }
                    for (TrainingWorker worker : workers)
                    {
                        worker.join();
                    }
                    for (int j = 0; j < models[0].parameter.length; j++)
                    {
                        for (int i = 1; i < models.length; i++)
                        {
                            models[0].parameter[j] += models[i].parameter[j];
                        }
                        models[0].parameter[j] /= threadNum;
                    }
                    accuracy = trainingFile.equals(developFile) ? IOUtility.evaluate(instances, models[0]) : evaluate(developFile, models[0]);
                    out.printf("Iter#%d - ", iter);
                    printAccuracy(accuracy);
                }
                catch (InterruptedException e)
                {
                    err.printf("线程�?�步异常，训练失败\n");
                    e.printStackTrace();
                    return null;
                }
            }
            logger.start("以压缩比 %.2f �?存模型到 %s ... ", compressRatio, modelFile);
            models[0].save(modelFile, immutableFeatureMap.featureIdMap.entrySet(), compressRatio, HanLP.Config.DEBUG);
            logger.finish(" �?存完毕\n");
            if (compressRatio == 0) return new Result(models[0], accuracy);
        }

        LinearModel model = new LinearModel(modelFile);
        if (compressRatio > 0)
        {
            accuracy = evaluate(developFile, model);
            out.printf("\n%.2f compressed model - ", compressRatio);
            printAccuracy(accuracy);
        }

        return new Result(model, accuracy);
    }

    private void printAccuracy(double[] accuracy)
    {
        if (accuracy.length == 3)
        {
            out.printf("P:%.2f R:%.2f F:%.2f\n", accuracy[0], accuracy[1], accuracy[2]);
        }
        else
        {
            out.printf("P:%.2f\n", accuracy[0]);
        }
    }

    private static class TrainingWorker extends Thread
    {
        private Instance[] instances;
        private int start;
        private int end;
        private StructuredPerceptron model;

        public TrainingWorker(Instance[] instances, int start, int end, StructuredPerceptron model)
        {
            this.instances = instances;
            this.start = start;
            this.end = end;
            this.model = model;
        }

        @Override
        public void run()
        {
            for (int s = start; s < end; ++s)
            {
                Instance instance = instances[s];
                model.update(instance);
            }
//            out.printf("Finished [%d,%d)\n", start, end);
        }
    }

    protected Instance[] loadTrainInstances(String trainingFile, final MutableFeatureMap mutableFeatureMap) throws IOException
    {
        final List<Instance> instanceList = new LinkedList<Instance>();
        IOUtility.loadInstance(trainingFile, new InstanceHandler()
        {
            @Override
            public boolean process(Sentence sentence)
            {
                Utility.normalize(sentence);
                instanceList.add(PerceptronTrainer.this.createInstance(sentence, mutableFeatureMap));
                return false;
            }
        });
        Instance[] instances = new Instance[instanceList.size()];
        instanceList.toArray(instances);
        return instances;
    }


    private static DoubleArrayTrie<Integer> loadDictionary(String trainingFile, String dictionaryFile) throws IOException
    {
        FrequencyMap dictionaryMap = new FrequencyMap();
        if (dictionaryFile == null)
        {
            out.printf("从训练文件%s中统计�?库...\n", trainingFile);
            loadWordFromFile(trainingFile, dictionaryMap, true);
        }
        else
        {
            out.printf("从外部�?典%s中加载�?库...\n", trainingFile);
            loadWordFromFile(dictionaryFile, dictionaryMap, false);
        }
        DoubleArrayTrie<Integer> dat = new DoubleArrayTrie<Integer>();
        dat.build(dictionaryMap);
        out.printf("加载完毕，�?库总�?数：%d，总�?频：%d\n", dictionaryMap.size(), dictionaryMap.totalFrequency);

        return dat;
    }

    public Result train(String trainingFile, String modelFile) throws IOException
    {
        return train(trainingFile, trainingFile, modelFile);
    }

    public Result train(String trainingFile, String developFile, String modelFile) throws IOException
    {
        return train(trainingFile, developFile, modelFile, 0.1, 50, Runtime.getRuntime().availableProcessors());
    }

    private static void loadWordFromFile(String path, FrequencyMap storage, boolean segmented) throws IOException
    {
        BufferedReader br = IOUtility.newBufferedReader(path);
        String line;
        while ((line = br.readLine()) != null)
        {
            if (segmented)
            {
                for (String word : IOUtility.readLineToArray(line))
                {
                    storage.add(word);
                }
            }
            else
            {
                line = line.trim();
                if (line.length() != 0)
                {
                    storage.add(line);
                }
            }
        }
        br.close();
    }
}
