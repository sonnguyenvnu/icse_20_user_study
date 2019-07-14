/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/8/22 14:17</create-date>
 *
 * <copyright file="BM25.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.summary;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * �?�索相关性评分算法
 * @author hankcs
 */
public class BM25
{
    /**
     * 文档�?��?的个数
     */
    int D;

    /**
     * 文档�?��?的平�?�长度
     */
    double avgdl;

    /**
     * 拆分为[�?��?[�?��?]]形�?的文档
     */
    List<List<String>> docs;

    /**
     * 文档中�?个�?��?中的�?个�?与�?频
     */
    Map<String, Integer>[] f;

    /**
     * 文档中全部�?语与出现在几个�?��?中
     */
    Map<String, Integer> df;

    /**
     * IDF
     */
    Map<String, Double> idf;

    /**
     * 调节因�?
     */
    final static float k1 = 1.5f;

    /**
     * 调节因�?
     */
    final static float b = 0.75f;

    public BM25(List<List<String>> docs)
    {
        this.docs = docs;
        D = docs.size();
        for (List<String> sentence : docs)
        {
            avgdl += sentence.size();
        }
        avgdl /= D;
        f = new Map[D];
        df = new TreeMap<String, Integer>();
        idf = new TreeMap<String, Double>();
        init();
    }

    /**
     * 在构造时�?始化自己的所有�?�数
     */
    private void init()
    {
        int index = 0;
        for (List<String> sentence : docs)
        {
            Map<String, Integer> tf = new TreeMap<String, Integer>();
            for (String word : sentence)
            {
                Integer freq = tf.get(word);
                freq = (freq == null ? 0 : freq) + 1;
                tf.put(word, freq);
            }
            f[index] = tf;
            for (Map.Entry<String, Integer> entry : tf.entrySet())
            {
                String word = entry.getKey();
                Integer freq = df.get(word);
                freq = (freq == null ? 0 : freq) + 1;
                df.put(word, freq);
            }
            ++index;
        }
        for (Map.Entry<String, Integer> entry : df.entrySet())
        {
            String word = entry.getKey();
            Integer freq = entry.getValue();
            idf.put(word, Math.log(D - freq + 0.5) - Math.log(freq + 0.5));
        }
    }

    /**
     * 计算一个�?��?与一个文档的BM25相似度
     *
     * @param sentence �?��?（查询语�?�）
     * @param index    文档（用语料库中的下标表示）
     * @return BM25 score
     */
    public double sim(List<String> sentence, int index)
    {
        double score = 0;
        for (String word : sentence)
        {
            if (!f[index].containsKey(word)) continue;
            int d = docs.get(index).size();
            Integer tf = f[index].get(word);
            score += (idf.get(word) * tf * (k1 + 1)
                    / (tf + k1 * (1 - b + b * d
                                                / avgdl)));
        }

        return score;
    }

    public double[] simAll(List<String> sentence)
    {
        double[] scores = new double[D];
        for (int i = 0; i < D; ++i)
        {
            scores[i] = sim(sentence, i);
        }
        return scores;
    }
}
