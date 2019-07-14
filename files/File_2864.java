/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/20 17:29</create-date>
 *
 * <copyright file="AbstractDependencyParser.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dependency;

import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.utility.GlobalObjectPool;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author hankcs
 */
public abstract class AbstractDependencyParser implements IDependencyParser
{
    /**
     * 本Parser使用的分�?器，�?�以自由替�?�
     */
    private Segment segment;
    /**
     * �?存关系映射表（�?�以将英文标签映射为中文）
     */
    private Map<String, String> deprelTranslater;
    /**
     * 是�?�自动转�?��?存关系
     */
    private boolean enableDeprelTranslater;

    public AbstractDependencyParser(Segment segment)
    {
        this.segment = segment;
    }

    public AbstractDependencyParser()
    {
        this(NLPTokenizer.ANALYZER);
    }

    @Override
    public CoNLLSentence parse(String sentence)
    {
        assert sentence != null;
        CoNLLSentence output = parse(segment.seg(sentence.toCharArray()));
        if (enableDeprelTranslater && deprelTranslater != null)
        {
            for (CoNLLWord word : output)
            {
                String translatedDeprel = deprelTranslater.get(word.DEPREL);
                word.DEPREL = translatedDeprel;
            }
        }
        return output;
    }

    @Override
    public Segment getSegment()
    {
        return segment;
    }

    @Override
    public IDependencyParser setSegment(Segment segment)
    {
        this.segment = segment;
        return this;
    }

    @Override
    public Map<String, String> getDeprelTranslator()
    {
        return deprelTranslater;
    }

    @Override
    public IDependencyParser setDeprelTranslator(Map<String, String> deprelTranslator)
    {
        this.deprelTranslater = deprelTranslator;
        return this;
    }

    /**
     * 设置映射表
     * @param deprelTranslatorPath 映射表路径
     * @return
     */
    public IDependencyParser setDeprelTranslater(String deprelTranslatorPath)
    {
        deprelTranslater = GlobalObjectPool.get(deprelTranslatorPath);
        if (deprelTranslater != null) return this;

        IOUtil.LineIterator iterator = new IOUtil.LineIterator(deprelTranslatorPath);
        deprelTranslater = new TreeMap<String, String>();
        while (iterator.hasNext())
        {
            String[] args = iterator.next().split("\\s");
            deprelTranslater.put(args[0], args[1]);
        }
        if (deprelTranslater.size() == 0)
        {
            deprelTranslater = null;
        }
        GlobalObjectPool.put(deprelTranslatorPath, deprelTranslater);

        return this;
    }

    @Override
    public IDependencyParser enableDeprelTranslator(boolean enable)
    {
        enableDeprelTranslater = enable;
        return this;
    }
}
