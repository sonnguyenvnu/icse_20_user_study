/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/8/22 15:58</create-date>
 *
 * <copyright file="TextRank.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.summary;


import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.hankcs.hanlp.utility.TextUtility;

import java.util.*;

/**
 * TextRank 自动摘�?
 *
 * @author hankcs
 */
public class TextRankSentence
{
    /**
     * 阻尼系数（Ｄ�?�?�?ｉｎｇＦ�?ｃｔ�?ｒ），一般�?�值为0.85
     */
    final static double d = 0.85;
    /**
     * 最大迭代次数
     */
    final static int max_iter = 200;
    final static double min_diff = 0.001;
    
    final static String default_sentence_separator = "[，,。:：“�?？?�?!；;]";
    /**
     * 文档�?��?的个数
     */
    int D;
    /**
     * 拆分为[�?��?[�?��?]]形�?的文档
     */
    List<List<String>> docs;
    /**
     * 排�?�?�的最终结果 score <-> index
     */
    TreeMap<Double, Integer> top;

    /**
     * �?��?和其他�?��?的相关程度
     */
    double[][] weight;
    /**
     * 该�?��?和其他�?��?相关程度之和
     */
    double[] weight_sum;
    /**
     * 迭代之�?�收敛的�?��?
     */
    double[] vertex;

    /**
     * BM25相似度
     */
    BM25 bm25;

    public TextRankSentence(List<List<String>> docs)
    {
        this.docs = docs;
        bm25 = new BM25(docs);
        D = docs.size();
        weight = new double[D][D];
        weight_sum = new double[D];
        vertex = new double[D];
        top = new TreeMap<Double, Integer>(Collections.reverseOrder());
        solve();
    }

    private void solve()
    {
        int cnt = 0;
        for (List<String> sentence : docs)
        {
            double[] scores = bm25.simAll(sentence);
//            System.out.println(Arrays.toString(scores));
            weight[cnt] = scores;
            weight_sum[cnt] = sum(scores) - scores[cnt]; // �?掉自己，自己跟自己肯定最相似
            vertex[cnt] = 1.0;
            ++cnt;
        }
        for (int _ = 0; _ < max_iter; ++_)
        {
            double[] m = new double[D];
            double max_diff = 0;
            for (int i = 0; i < D; ++i)
            {
                m[i] = 1 - d;
                for (int j = 0; j < D; ++j)
                {
                    if (j == i || weight_sum[j] == 0) continue;
                    m[i] += (d * weight[j][i] / weight_sum[j] * vertex[j]);
                }
                double diff = Math.abs(m[i] - vertex[i]);
                if (diff > max_diff)
                {
                    max_diff = diff;
                }
            }
            vertex = m;
            if (max_diff <= min_diff) break;
        }
        // 我们�?�排个�?�?�
        for (int i = 0; i < D; ++i)
        {
            top.put(vertex[i], i);
        }
    }

    /**
     * 获�?��?几个关键�?��?
     *
     * @param size �?几个
     * @return 关键�?��?的下标
     */
    public int[] getTopSentence(int size)
    {
        Collection<Integer> values = top.values();
        size = Math.min(size, values.size());
        int[] indexArray = new int[size];
        Iterator<Integer> it = values.iterator();
        for (int i = 0; i < size; ++i)
        {
            indexArray[i] = it.next();
        }
        return indexArray;
    }

    /**
     * 简�?�的求和
     *
     * @param array
     * @return
     */
    private static double sum(double[] array)
    {
        double total = 0;
        for (double v : array)
        {
            total += v;
        }
        return total;
    }

    /**
     * 将文章分割为�?��?
     * 默认�?��?分隔符为：[，,。:：“�?？?�?!；;]
     *
     * @param document
     * @return
     */
    static List<String> splitSentence(String document)
    {
    	return splitSentence(document, default_sentence_separator);
    }

    /**
     * 将文章分割为�?��?
     *	 
     * @param document 待分割的文档
     * @param sentence_separator �?��?分隔符，正则表达�?，如：   [。:？?�?!；;]
     * @return
     */
    static List<String> splitSentence(String document, String sentence_separator)
    {
        List<String> sentences = new ArrayList<String>();
        for (String line : document.split("[\r\n]"))
        {
            line = line.trim();
            if (line.length() == 0) continue;
            for (String sent : line.split(sentence_separator))		// [，,。:：“�?？?�?!；;]
            {
                sent = sent.trim();
                if (sent.length() == 0) continue;
                sentences.add(sent);
            }
        }

        return sentences;
    }

    /**
     * 将�?��?列表转化为文档
     *
     * @param sentenceList
     * @return
     */
    private static List<List<String>> convertSentenceListToDocument(List<String> sentenceList)
    {
        List<List<String>> docs = new ArrayList<List<String>>(sentenceList.size());
        for (String sentence : sentenceList)
        {
            List<Term> termList = StandardTokenizer.segment(sentence.toCharArray());
            List<String> wordList = new LinkedList<String>();
            for (Term term : termList)
            {
                if (CoreStopWordDictionary.shouldInclude(term))
                {
                    wordList.add(term.word);
                }
            }
            docs.add(wordList);
        }
        return docs;
    }

    /**
     * 一�?��?调用接�?�
     *
     * @param document 目标文档
     * @param size     需�?的关键�?�的个数
     * @return 关键�?�列表
     */
    public static List<String> getTopSentenceList(String document, int size)
    {
    	return getTopSentenceList(document, size, default_sentence_separator);
    }

    /**
     * 一�?��?调用接�?�
     *
     * @param document 目标文档
     * @param size     需�?的关键�?�的个数
     * @param sentence_separator �?��?分隔符，正则格�?， 如：[。？?�?!；;]
     * @return 关键�?�列表
     */
    public static List<String> getTopSentenceList(String document, int size, String sentence_separator)
    {
        List<String> sentenceList = splitSentence(document, sentence_separator);
        List<List<String>> docs = convertSentenceListToDocument(sentenceList);
        TextRankSentence textRank = new TextRankSentence(docs);
        int[] topSentence = textRank.getTopSentence(size);
        List<String> resultList = new LinkedList<String>();
        for (int i : topSentence)
        {
            resultList.add(sentenceList.get(i));
        }
        return resultList;
    }

    /**
     * 一�?��?调用接�?�
     *
     * @param document   目标文档
     * @param max_length 需�?摘�?的长度
     * @return 摘�?文本
     */
    public static String getSummary(String document, int max_length)
    {
    	return getSummary(document, max_length, default_sentence_separator);
    }

    /**
     * 一�?��?调用接�?�
     *
     * @param document   目标文档
     * @param max_length 需�?摘�?的长度
     * @param sentence_separator �?��?分隔符，正则格�?， 如：[。？?�?!；;]
     * @return 摘�?文本
     */
    public static String getSummary(String document, int max_length, String sentence_separator)
    {
        List<String> sentenceList = splitSentence(document, sentence_separator);

        int sentence_count = sentenceList.size();
        int document_length = document.length();
        int sentence_length_avg = document_length / sentence_count;
        int size = max_length / sentence_length_avg + 1;
        List<List<String>> docs = convertSentenceListToDocument(sentenceList);
        TextRankSentence textRank = new TextRankSentence(docs);
        int[] topSentence = textRank.getTopSentence(size);
        List<String> resultList = new LinkedList<String>();
        for (int i : topSentence)
        {
            resultList.add(sentenceList.get(i));
        }

        resultList = permutation(resultList, sentenceList);
        resultList = pick_sentences(resultList, max_length);
        return TextUtility.join("。", resultList);
    }

    private static List<String> permutation(List<String> resultList, final List<String> sentenceList)
    {
        Collections.sort(resultList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Integer num1 = sentenceList.indexOf(o1);
                Integer num2 = sentenceList.indexOf(o2);
                return num1.compareTo(num2);
            }
        });
        return resultList;
    }

    private static List<String> pick_sentences(List<String> resultList, int max_length)
    {
        List<String> summary = new ArrayList<String>();
        int count = 0;
        for (String result : resultList) {
            if (count + result.length() <= max_length) {
                summary.add(result);
                count += result.length();
            }
        }
        return summary;
    }

}
