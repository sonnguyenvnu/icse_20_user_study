/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-09-04 PM10:29</create-date>
 *
 * <copyright file="LinearModel.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron.model;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.algorithm.MaxHeap;
import com.hankcs.hanlp.collection.trie.datrie.MutableDoubleArrayTrieInteger;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.ByteArrayStream;
import com.hankcs.hanlp.corpus.io.ICacheAble;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.model.perceptron.common.TaskType;
import com.hankcs.hanlp.model.perceptron.feature.FeatureMap;
import com.hankcs.hanlp.model.perceptron.feature.FeatureSortItem;
import com.hankcs.hanlp.model.perceptron.feature.ImmutableFeatureMDatMap;
import com.hankcs.hanlp.model.perceptron.instance.Instance;
import com.hankcs.hanlp.model.perceptron.tagset.TagSet;
import com.hankcs.hanlp.utility.MathUtility;

import java.io.*;
import java.util.*;

import static com.hankcs.hanlp.classification.utilities.io.ConsoleLogger.logger;

/**
 * 在线学习标注模型
 *
 * @author hankcs
 */
public class LinearModel implements ICacheAble
{
    /**
     * 特�?函数
     */
    public FeatureMap featureMap;
    /**
     * 特�?�?��?
     */
    public float[] parameter;


    public LinearModel(FeatureMap featureMap, float[] parameter)
    {
        this.featureMap = featureMap;
        this.parameter = parameter;
    }

    public LinearModel(FeatureMap featureMap)
    {
        this.featureMap = featureMap;
        parameter = new float[featureMap.size() * featureMap.tagSet.size()];
    }

    public LinearModel(String modelFile) throws IOException
    {
        load(modelFile);
    }

    /**
     * 模型压缩
     * @param ratio 压缩比c（压缩掉的体积，压缩�?�体积�?�为1-c）
     * @return
     */
    public LinearModel compress(final double ratio)
    {
        return compress(ratio, 1e-3f);
    }

    /**
     * @param ratio 压缩比c（压缩掉的体积，压缩�?�体积�?�为1-c）
     * @param threshold 特�?�?��?�?对值之和最低阈值
     * @return
     */
    public LinearModel compress(final double ratio, final double threshold)
    {
        if (ratio < 0 || ratio >= 1)
        {
            throw new IllegalArgumentException("压缩比必须介于 0 和 1 之间");
        }
        if (ratio == 0) return this;
        Set<Map.Entry<String, Integer>> featureIdSet = featureMap.entrySet();
        TagSet tagSet = featureMap.tagSet;
        MaxHeap<FeatureSortItem> heap = new MaxHeap<FeatureSortItem>((int) ((featureIdSet.size() - tagSet.sizeIncludingBos()) * (1.0f - ratio)), new Comparator<FeatureSortItem>()
        {
            @Override
            public int compare(FeatureSortItem o1, FeatureSortItem o2)
            {
                return Float.compare(o1.total, o2.total);
            }
        });

        logger.start("�?剪特�?...\n");
        int logEvery = (int) Math.ceil(featureMap.size() / 10000f);
        int n = 0;
        for (Map.Entry<String, Integer> entry : featureIdSet)
        {
            if (++n % logEvery == 0 || n == featureMap.size())
            {
                logger.out("\r%.2f%% ", MathUtility.percentage(n, featureMap.size()));
            }
            if (entry.getValue() < tagSet.sizeIncludingBos())
            {
                continue;
            }
            FeatureSortItem item = new FeatureSortItem(entry, this.parameter, tagSet.size());
            if (item.total < threshold) continue;
            heap.add(item);
        }
        logger.finish("\n�?剪完毕\n");

        int size = heap.size() + tagSet.sizeIncludingBos();
        float[] parameter = new float[size * tagSet.size()];
        MutableDoubleArrayTrieInteger mdat = new MutableDoubleArrayTrieInteger();
        for (Map.Entry<String, Integer> tag : tagSet)
        {
            mdat.add("BL=" + tag.getKey());
        }
        mdat.add("BL=_BL_");
        for (int i = 0; i < tagSet.size() * tagSet.sizeIncludingBos(); i++)
        {
            parameter[i] = this.parameter[i];
        }
        logger.start("构建�?�数组trie树...\n");
        logEvery = (int) Math.ceil(heap.size() / 10000f);
        n = 0;
        for (FeatureSortItem item : heap)
        {
            if (++n % logEvery == 0 || n == heap.size())
            {
                logger.out("\r%.2f%% ", MathUtility.percentage(n, heap.size()));
            }
            int id = mdat.size();
            mdat.put(item.key, id);
            for (int i = 0; i < tagSet.size(); ++i)
            {
                parameter[id * tagSet.size() + i] = this.parameter[item.id * tagSet.size() + i];
            }
        }
        logger.finish("\n构建完毕\n");
        this.featureMap = new ImmutableFeatureMDatMap(mdat, tagSet);
        this.parameter = parameter;
        return this;
    }

    /**
     * �?存到路径
     *
     * @param modelFile
     * @throws IOException
     */
    public void save(String modelFile) throws IOException
    {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(modelFile)));
        save(out);
        out.close();
    }

    /**
     * 压缩并�?存
     *
     * @param modelFile 路径
     * @param ratio     压缩比c（压缩掉的体积，压缩�?�体积�?�为1-c）
     * @throws IOException
     */
    public void save(String modelFile, final double ratio) throws IOException
    {
        save(modelFile, featureMap.entrySet(), ratio);
    }

    public void save(String modelFile, Set<Map.Entry<String, Integer>> featureIdSet, final double ratio) throws IOException
    {
        save(modelFile, featureIdSet, ratio, false);
    }

    /**
     * �?存
     *
     * @param modelFile    路径
     * @param featureIdSet 特�?集（有些数�?�结构�?支�?�??历，�?�以�??供构造时用到的特�?集�?�规�?�这个缺陷）
     * @param ratio        压缩比
     * @param text         是�?�输出文本以供调试
     * @throws IOException
     */
    public void save(String modelFile, Set<Map.Entry<String, Integer>> featureIdSet, final double ratio, boolean text) throws IOException
    {
        float[] parameter = this.parameter;
        this.compress(ratio, 1e-3f);

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(modelFile)));
        save(out);
        out.close();

        if (text)
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(modelFile + ".txt"), "UTF-8"));
            TagSet tagSet = featureMap.tagSet;
            for (Map.Entry<String, Integer> entry : featureIdSet)
            {
                bw.write(entry.getKey());
                if (featureIdSet.size() == parameter.length)
                {
                    bw.write("\t");
                    bw.write(String.valueOf(parameter[entry.getValue()]));
                }
                else
                {
                    for (int i = 0; i < tagSet.size(); ++i)
                    {
                        bw.write("\t");
                        bw.write(String.valueOf(parameter[entry.getValue() * tagSet.size() + i]));
                    }
                }
                bw.newLine();
            }
            bw.close();
        }
    }

    /**
     * �?�数更新
     *
     * @param x 特�?�?��?
     * @param y 正确答案
     */
    public void update(Collection<Integer> x, int y)
    {
        assert y == 1 || y == -1 : "感知机的标签y必须是±1";
        for (Integer f : x)
            parameter[f] += y;
    }

    /**
     * 分离超平�?�解�?
     *
     * @param x 特�?�?��?
     * @return sign(wx)
     */
    public int decode(Collection<Integer> x)
    {
        float y = 0;
        for (Integer f : x)
            y += parameter[f];
        return y < 0 ? -1 : 1;
    }

    /**
     * 维特比解�?
     *
     * @param instance 实例
     * @return
     */
    public double viterbiDecode(Instance instance)
    {
        return viterbiDecode(instance, instance.tagArray);
    }

    /**
     * 维特比解�?
     *
     * @param instance   实例
     * @param guessLabel 输出标签
     * @return
     */
    public double viterbiDecode(Instance instance, int[] guessLabel)
    {
        final int[] allLabel = featureMap.allLabels();
        final int bos = featureMap.bosTag();
        final int sentenceLength = instance.tagArray.length;
        final int labelSize = allLabel.length;

        int[][] preMatrix = new int[sentenceLength][labelSize];
        double[][] scoreMatrix = new double[2][labelSize];

        for (int i = 0; i < sentenceLength; i++)
        {
            int _i = i & 1;
            int _i_1 = 1 - _i;
            int[] allFeature = instance.getFeatureAt(i);
            final int transitionFeatureIndex = allFeature.length - 1;
            if (0 == i)
            {
                allFeature[transitionFeatureIndex] = bos;
                for (int j = 0; j < allLabel.length; j++)
                {
                    preMatrix[0][j] = j;

                    double score = score(allFeature, j);

                    scoreMatrix[0][j] = score;
                }
            }
            else
            {
                for (int curLabel = 0; curLabel < allLabel.length; curLabel++)
                {

                    double maxScore = Integer.MIN_VALUE;

                    for (int preLabel = 0; preLabel < allLabel.length; preLabel++)
                    {

                        allFeature[transitionFeatureIndex] = preLabel;
                        double score = score(allFeature, curLabel);

                        double curScore = scoreMatrix[_i_1][preLabel] + score;

                        if (maxScore < curScore)
                        {
                            maxScore = curScore;
                            preMatrix[i][curLabel] = preLabel;
                            scoreMatrix[_i][curLabel] = maxScore;
                        }
                    }
                }

            }
        }

        int maxIndex = 0;
        double maxScore = scoreMatrix[(sentenceLength - 1) & 1][0];

        for (int index = 1; index < allLabel.length; index++)
        {
            if (maxScore < scoreMatrix[(sentenceLength - 1) & 1][index])
            {
                maxIndex = index;
                maxScore = scoreMatrix[(sentenceLength - 1) & 1][index];
            }
        }

        for (int i = sentenceLength - 1; i >= 0; --i)
        {
            guessLabel[i] = allLabel[maxIndex];
            maxIndex = preMatrix[i][maxIndex];
        }

        return maxScore;
    }

    /**
     * 通过命中的特�?函数计算得分
     *
     * @param featureVector 压缩形�?的特�?id构�?的特�?�?��?
     * @return
     */
    public double score(int[] featureVector, int currentTag)
    {
        double score = 0;
        for (int index : featureVector)
        {
            if (index == -1)
            {
                continue;
            }
            else if (index < -1 || index >= featureMap.size())
            {
                throw new IllegalArgumentException("在打分时传入了�?�法的下标");
            }
            else
            {
                index = index * featureMap.tagSet.size() + currentTag;
                score += parameter[index];    // 其实就是特�?�?��?的累加
            }
        }
        return score;
    }

    /**
     * 加载模型
     *
     * @param modelFile
     * @throws IOException
     */
    public void load(String modelFile) throws IOException
    {
        if (HanLP.Config.DEBUG)
            logger.start("加载 %s ... ", modelFile);
        ByteArrayStream byteArray = ByteArrayStream.createByteArrayStream(modelFile);
        if (!load(byteArray))
        {
            throw new IOException(String.format("%s 加载失败", modelFile));
        }
        if (HanLP.Config.DEBUG)
            logger.finish(" 加载完毕\n");
    }

    public TagSet tagSet()
    {
        return featureMap.tagSet;
    }

    @Override
    public void save(DataOutputStream out) throws IOException
    {
        if (!(featureMap instanceof ImmutableFeatureMDatMap))
        {
            featureMap = new ImmutableFeatureMDatMap(featureMap.entrySet(), tagSet());
        }
        featureMap.save(out);
        for (float aParameter : this.parameter)
        {
            out.writeFloat(aParameter);
        }
    }

    @Override
    public boolean load(ByteArray byteArray)
    {
        if (byteArray == null)
            return false;
        featureMap = new ImmutableFeatureMDatMap();
        featureMap.load(byteArray);
        int size = featureMap.size();
        TagSet tagSet = featureMap.tagSet;
        if (tagSet.type == TaskType.CLASSIFICATION)
        {
            parameter = new float[size];
            for (int i = 0; i < size; i++)
            {
                parameter[i] = byteArray.nextFloat();
            }
        }
        else
        {
            parameter = new float[size * tagSet.size()];
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < tagSet.size(); ++j)
                {
                    parameter[i * tagSet.size() + j] = byteArray.nextFloat();
                }
            }
        }
//        assert !byteArray.hasMore();
//        byteArray.close();
        if (!byteArray.hasMore())
            byteArray.close();
        return true;
    }

    public TaskType taskType()
    {
        return featureMap.tagSet.type;
    }
}
