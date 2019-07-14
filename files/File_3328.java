/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/9 16:56</create-date>
 *
 * <copyright file="Index.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model;

import com.hankcs.hanlp.collection.trie.ITrie;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.model.crf.CRFModel;
import com.hankcs.hanlp.model.crf.FeatureFunction;
import com.hankcs.hanlp.model.crf.Table;

import java.util.LinkedList;

/**
 * �?��?CRF分�?模型
 *
 * @author hankcs
 */
//  * @deprecated 已废弃，请使用功能更丰富�?设计更优雅的{@link com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer}。
public final class CRFSegmentModel extends CRFModel
{
    private int idM;
    private int idE;
    private int idS;

    /**
     * �?�?许构造空白实例
     */
    private CRFSegmentModel()
    {
    }

    /**
     * 以指定的trie树结构储存内部特�?函数
     *
     * @param featureFunctionTrie
     */
    public CRFSegmentModel(ITrie<FeatureFunction> featureFunctionTrie)
    {
        super(featureFunctionTrie);
    }

    /**
     * �?始化几个常�?
     */
    private void initTagSet()
    {
        idM = this.getTagId("M");
        idE = this.getTagId("E");
        idS = this.getTagId("S");
    }

    @Override
    public boolean load(ByteArray byteArray)
    {
        boolean result = super.load(byteArray);
        if (result)
        {
            initTagSet();
        }

        return result;
    }

    @Override
    protected void onLoadTxtFinished()
    {
        super.onLoadTxtFinished();
        initTagSet();
    }

    @Override
    public void tag(Table table)
    {
        int size = table.size();
        if (size == 1)
        {
            table.setLast(0, "S");
            return;
        }
        double[][] net = new double[size][4];
        for (int i = 0; i < size; ++i)
        {
            LinkedList<double[]> scoreList = computeScoreList(table, i);
            for (int tag = 0; tag < 4; ++tag)
            {
                net[i][tag] = computeScore(scoreList, tag);
            }
        }
        net[0][idM] = -1000.0;  // 第一个字�?�?�能是M或E
        net[0][idE] = -1000.0;
        int[][] from = new int[size][4];
        double[][] maxScoreAt = new double[2][4]; // 滚动数组
        System.arraycopy(net[0], 0, maxScoreAt[0], 0, 4); // �?始preI=0,  maxScoreAt[preI][pre] = net[0][pre]
        int curI = 0;
        for (int i = 1; i < size; ++i)
        {
            curI = i & 1;
            int preI = 1 - curI;
            for (int now = 0; now < 4; ++now)
            {
                double maxScore = -1e10;
                for (int pre = 0; pre < 4; ++pre)
                {
                    double score = maxScoreAt[preI][pre] + matrix[pre][now] + net[i][now];
                    if (score > maxScore)
                    {
                        maxScore = score;
                        from[i][now] = pre;
                        maxScoreAt[curI][now] = maxScore;
                    }
                }
                net[i][now] = maxScore;
            }
        }
        // �??�?�回溯最佳路径
        int maxTag = maxScoreAt[curI][idS] > maxScoreAt[curI][idE] ? idS : idE;
        table.setLast(size - 1, id2tag[maxTag]);
        maxTag = from[size - 1][maxTag];
        for (int i = size - 2; i > 0; --i)
        {
            table.setLast(i, id2tag[maxTag]);
            maxTag = from[i][maxTag];
        }
        table.setLast(0, id2tag[maxTag]);
    }
}
