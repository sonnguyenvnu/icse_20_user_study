/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2015/3/13 18:36</create-date>
 *
 * <copyright file="CommonSuffixExtractor.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary;

import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.occurrence.TermFrequency;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * 公共�?�缀�??�?�工具
 * @author hankcs
 */
public class CommonSuffixExtractor
{
    TFDictionary tfDictionary;

    public CommonSuffixExtractor()
    {
        tfDictionary = new TFDictionary();
    }

    public void add(String key)
    {
        tfDictionary.add(key);
    }

    public List<String> extractSuffixExtended(int length, int size)
    {
        return extractSuffix(length, size, true);
    }

    /**
     * �??�?�公共�?�缀
     * @param length 公共�?�缀长度
     * @param size 频率最高的�?多少个公共�?�缀
     * @param extend 长度是�?�拓展为从1到length为止的�?�缀
     * @return 公共�?�缀列表
     */
    public List<String> extractSuffix(int length, int size, boolean extend)
    {
        TFDictionary suffixTreeSet = new TFDictionary();
        for (String key : tfDictionary.keySet())
        {
            if (key.length() > length)
            {
                suffixTreeSet.add(key.substring(key.length() - length, key.length()));
                if (extend)
                {
                    for (int l = 1; l < length; ++l)
                    {
                        suffixTreeSet.add(key.substring(key.length() - l, key.length()));
                    }
                }
            }
        }

        if (extend)
        {
            size *= length;
        }

        return extract(suffixTreeSet, size);
    }

    private static List<String> extract(TFDictionary suffixTreeSet, int size)
    {
        List<String> suffixList = new ArrayList<String>(size);
        for (TermFrequency termFrequency : suffixTreeSet.values())
        {
            if (suffixList.size() >= size) break;
            suffixList.add(termFrequency.getKey());
        }

        return suffixList;
    }

    /**
     * 此方法认为�?�缀一定是整个的�?语，所以length是以�?语为�?��?的
     * @param length
     * @param size
     * @param extend
     * @return
     */
    public List<String> extractSuffixByWords(int length, int size, boolean extend)
    {
        TFDictionary suffixTreeSet = new TFDictionary();
        for (String key : tfDictionary.keySet())
        {
            List<Term> termList = StandardTokenizer.segment(key);
            if (termList.size() > length)
            {
                suffixTreeSet.add(combine(termList.subList(termList.size() - length, termList.size())));
                if (extend)
                {
                    for (int l = 1; l < length; ++l)
                    {
                        suffixTreeSet.add(combine(termList.subList(termList.size() - l, termList.size())));
                    }
                }
            }
        }

        return extract(suffixTreeSet, size);
    }


    private static String combine(List<Term> termList)
    {
        StringBuilder sbResult = new StringBuilder();
        for (Term term : termList)
        {
            sbResult.append(term.word);
        }

        return sbResult.toString();
    }
}
