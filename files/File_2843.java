/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/8 1:16</create-date>
 *
 * <copyright file="TermFrequency.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.occurrence;

import java.util.AbstractMap;

/**
 * �?与�?频的简�?��?装
 * @author hankcs
 */
public class TermFrequency extends AbstractMap.SimpleEntry<String, Integer> implements Comparable<TermFrequency>
{
    public TermFrequency(String term, Integer frequency)
    {
        super(term, frequency);
    }

    public TermFrequency(String term)
    {
        this(term, 1);
    }

    /**
     * 频次增加若干
     * @param number
     * @return
     */
    public int increase(int number)
    {
        setValue(getValue() + number);
        return getValue();
    }

    public String getTerm()
    {
        return getKey();
    }

    public Integer getFrequency()
    {
        return getValue();
    }

    /**
     * 频次加一
     * @return
     */
    public int increase()
    {
        return increase(1);
    }

    @Override
    public int compareTo(TermFrequency o)
    {
        if (this.getFrequency().compareTo(o.getFrequency()) == 0) return getKey().compareTo(o.getKey());
        return this.getFrequency().compareTo(o.getFrequency());
    }
}
