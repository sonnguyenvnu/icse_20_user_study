/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/5 16:34</create-date>
 *
 * <copyright file="BaseScorer.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.suggest.scorer;

import java.util.*;


/**
 * 基本打分器
 * @param <T> 这是储存器map中key的类型，具有相�?�key的�?��?会存入�?�一个entry
 * @author hankcs
 */
public abstract class BaseScorer<T extends ISentenceKey> implements IScorer
{
    public BaseScorer()
    {
        storage = new TreeMap<T, Set<String>>();
    }

    /**
     * 储存
     */
    protected Map<T, Set<String>> storage;
    /**
     * �?��?
     */
    public double boost = 1.0;

    /**
     * 设置�?��?
     * @param boost
     * @return
     */
    public BaseScorer setBoost(double boost)
    {
        this.boost = boost;
        return this;
    }

    @Override
    public void addSentence(String sentence)
    {
        T key = generateKey(sentence);
        if (key == null) return;
        Set<String> set = storage.get(key);
        if (set == null)
        {
            set = new TreeSet<String>();
            storage.put(key, set);
        }
        set.add(sentence);
    }

    /**
     * 生�?能够代表这个�?��?的键
     * @param sentence
     * @return
     */
    protected abstract T generateKey(String sentence);

    @Override
    public Map<String, Double> computeScore(String outerSentence)
    {
        TreeMap<String, Double> result = new TreeMap<String, Double>(Collections.reverseOrder());
        T keyOuter = generateKey(outerSentence);
        if (keyOuter == null) return result;
        for (Map.Entry<T, Set<String>> entry : storage.entrySet())
        {
            T key = entry.getKey();
            Double score = keyOuter.similarity(key);
            for (String sentence : entry.getValue())
            {
                result.put(sentence, score);
            }
        }
        return result;
    }

    @Override
    public void removeAllSentences()
    {
        storage.clear();
    }
}
