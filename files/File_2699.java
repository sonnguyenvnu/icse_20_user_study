/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/18 21:36</create-date>
 *
 * <copyright file="CommonDictionaryMaker.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary;

import com.hankcs.hanlp.corpus.document.CorpusLoader;
import com.hankcs.hanlp.corpus.document.Document;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * @author hankcs
 */
public abstract class CommonDictionaryMaker implements ISaveAble
{
    public boolean verbose = false;
    /**
     * 语料库中的�?��?
     */
    EasyDictionary dictionary;
    /**
     * 输出�?典
     */
    DictionaryMaker dictionaryMaker;
    /**
     * 2元文法�?典
     */
    NGramDictionaryMaker nGramDictionaryMaker;

    public CommonDictionaryMaker(EasyDictionary dictionary)
    {
        nGramDictionaryMaker = new NGramDictionaryMaker();
        dictionaryMaker = new DictionaryMaker();
        this.dictionary = dictionary;
    }

    @Override
    public boolean saveTxtTo(String path)
    {
        if (dictionaryMaker.saveTxtTo(path + ".txt"))
        {
            if (nGramDictionaryMaker.saveTxtTo(path))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 处�?�语料，准备�?典
     */
    public void compute(List<List<IWord>> sentenceList)
    {
        roleTag(sentenceList);
        addToDictionary(sentenceList);
    }

    /**
     * �?�compute
     * @param sentenceList
     */
    public void learn(List<Sentence> sentenceList)
    {
        List<List<IWord>> s = new ArrayList<List<IWord>>(sentenceList.size());
        for (Sentence sentence : sentenceList)
        {
            s.add(sentence.wordList);
        }
        compute(s);
    }

    /**
     * �?�compute
     * @param sentences
     */
    public void learn(Sentence ... sentences)
    {
        learn(Arrays.asList(sentences));
    }

    /**
     * 训练
     * @param corpus 语料库路径
     */
    public void train(String corpus)
    {
        CorpusLoader.walk(corpus, new CorpusLoader.Handler()
        {
            @Override
            public void handle(Document document)
            {
                List<List<Word>> simpleSentenceList = document.getSimpleSentenceList();
                List<List<IWord>> compatibleList = new LinkedList<List<IWord>>();
                for (List<Word> wordList : simpleSentenceList)
                {
                    compatibleList.add(new LinkedList<IWord>(wordList));
                }
                CommonDictionaryMaker.this.compute(compatibleList);
            }
        });
    }

    /**
     * 加入到�?典中，�?许�?类自定义过滤等等，这样比较�?�活
     * @param sentenceList
     */
    abstract protected void addToDictionary(List<List<IWord>> sentenceList);

    /**
     * 角色标注，如果�?类�?进行label的调整或增加新的首尾等等，�?�以在此进行
     */
    abstract protected void roleTag(List<List<IWord>> sentenceList);
}
