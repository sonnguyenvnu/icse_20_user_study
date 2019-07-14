/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/20 11:05</create-date>
 *
 * <copyright file="CoNLLSentence.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dependency.CoNll;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * CoNLL中的一个�?��?
 * @author hankcs
 */
public class CoNLLSentence implements Iterable<CoNLLWord>
{
    /**
     * 有许多行，�?行是一个�?��?
     */
    public CoNLLWord[] word;

    /**
     * 构造一个�?��?
     * @param lineList
     */
    public CoNLLSentence(List<CoNllLine> lineList)
    {
        CoNllLine[] lineArray = lineList.toArray(new CoNllLine[0]);
        this.word = new CoNLLWord[lineList.size()];
        int i = 0;
        for (CoNllLine line : lineList)
        {
            word[i++] = new CoNLLWord(line);
        }
        for (CoNLLWord nllWord : word)
        {
            int head = Integer.parseInt(lineArray[nllWord.ID - 1].value[6]) - 1;
            if (head != -1)
            {
                nllWord.HEAD = word[head];
            }
            else
            {
                nllWord.HEAD = CoNLLWord.ROOT;
            }
        }
    }

    public CoNLLSentence(CoNLLWord[] word)
    {
        this.word = word;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(word.length * 50);
        for (CoNLLWord word : this.word)
        {
            sb.append(word);
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 获�?�边的列表，edge[i][j]表示id为i的�?语与j存在一�?��?存关系为该值的边，�?�则为null
     * @return
     */
    public String[][] getEdgeArray()
    {
        String[][] edge = new String[word.length + 1][word.length + 1];
        for (CoNLLWord coNLLWord : word)
        {
            edge[coNLLWord.ID][coNLLWord.HEAD.ID] = coNLLWord.DEPREL;
        }

        return edge;
    }

    /**
     * 获�?�包�?�根节点在内的�?��?数组
     * @return
     */
    public CoNLLWord[] getWordArrayWithRoot()
    {
        CoNLLWord[] wordArray = new CoNLLWord[word.length + 1];
        wordArray[0] = CoNLLWord.ROOT;
        System.arraycopy(word, 0, wordArray, 1, word.length);

        return wordArray;
    }

    public CoNLLWord[] getWordArray()
    {
        return word;
    }

    @Override
    public Iterator<CoNLLWord> iterator()
    {
        return new Iterator<CoNLLWord>()
        {
            int index;
            @Override
            public boolean hasNext()
            {
                return index < word.length;
            }

            @Override
            public CoNLLWord next()
            {
                return word[index++];
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("CoNLLSentence是�?�读对象，�?�?许删除");
            }
        };
    }

    /**
     * 找出所有�?节点
     * @param word
     * @return
     */
    public List<CoNLLWord> findChildren(CoNLLWord word)
    {
        List<CoNLLWord> result = new LinkedList<CoNLLWord>();
        for (CoNLLWord other : this)
        {
            if (other.HEAD == word)
                result.add(other);
        }
        return result;
    }

    /**
     * 找出特定�?存关系的�?节点
     * @param word
     * @param relation
     * @return
     */
    public List<CoNLLWord> findChildren(CoNLLWord word, String relation)
    {
        List<CoNLLWord> result = new LinkedList<CoNLLWord>();
        for (CoNLLWord other : this)
        {
            if (other.HEAD == word && other.DEPREL.equals(relation))
                result.add(other);
        }
        return result;
    }
}
