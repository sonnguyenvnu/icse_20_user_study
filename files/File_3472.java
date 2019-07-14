/*
 * <summary></summary>
 * <author>hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2015/5/7 10:59</create-date>
 *
 * <copyright file="CharacterBasedGenerativeModelSegment.java">
 * Copyright (c) 2003-2015, hankcs. All Right Reserved, http://www.hankcs.com/
 * </copyright>
 */
package com.hankcs.hanlp.seg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hankcs.hanlp.algorithm.Viterbi;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CoreDictionaryTransformMatrixDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.seg.common.Vertex;

/**
 * 基于“由字构�?�?方法分�?器基类
 * @author hankcs
 */
public abstract class CharacterBasedSegment extends Segment
{

    /**
     * 查询或猜测一个�?语的属性，
     * 先查�?典，然�?�对字�?�?数字串的属性进行判断，最�?�猜测未登录�?
     * @param term
     * @return
     */
    public static CoreDictionary.Attribute guessAttribute(Term term)
    {
        CoreDictionary.Attribute attribute = CoreDictionary.get(term.word);
        if (attribute == null)
        {
            attribute = CustomDictionary.get(term.word);
        }
        if (attribute == null)
        {
            if (term.nature != null)
            {
                if (Nature.nx == term.nature)
                    attribute = new CoreDictionary.Attribute(Nature.nx);
                else if (Nature.m == term.nature)
                    attribute = CoreDictionary.get(CoreDictionary.M_WORD_ID);
            }
            else if (term.word.trim().length() == 0)
                attribute = new CoreDictionary.Attribute(Nature.x);
            else attribute = new CoreDictionary.Attribute(Nature.nz);
        }
        else term.nature = attribute.nature[0];
        return attribute;
    }


    /**
     * 以下方法用于纯分�?模型
     * 分�?�?�?性标注�?��?�模型则直接�?载segSentence
     */
    @Override
    protected List<Term> segSentence(char[] sentence)
    {
        if (sentence.length == 0) return Collections.emptyList();
        List<Term> termList = roughSegSentence(sentence);
        if (!(config.ner || config.useCustomDictionary || config.speechTagging))
            return termList;
        List<Vertex> vertexList = toVertexList(termList, true);
        if (config.speechTagging)
        {
            Viterbi.compute(vertexList, CoreDictionaryTransformMatrixDictionary.transformMatrixDictionary);
            int i = 0;
            for (Term term : termList)
            {
                if (term.nature != null) term.nature = vertexList.get(i + 1).guessNature();
                ++i;
            }
        }
        if (config.useCustomDictionary)
        {
            combineByCustomDictionary(vertexList);
            termList = convert(vertexList, config.offset);
        }
        return termList;
    }

    /**
     * �?�纯的分�?模型实现该方法，仅输出�?
     * @param sentence
     * @return
     */
    protected abstract List<Term> roughSegSentence(char[] sentence);

    /**
     * 将中间结果转�?�为�?网顶点,
     * 这样就�?�以利用基于Vertex开�?�的功能, 如�?性标注�?NER等
     * @param wordList
     * @param appendStart
     * @return
     */
    protected List<Vertex> toVertexList(List<Term> wordList, boolean appendStart)
    {
        ArrayList<Vertex> vertexList = new ArrayList<Vertex>(wordList.size() + 2);
        if (appendStart) vertexList.add(Vertex.newB());
        for (Term word : wordList)
        {
            CoreDictionary.Attribute attribute = guessAttribute(word);
            Vertex vertex = new Vertex(word.word, attribute);
            vertexList.add(vertex);
        }
        if (appendStart) vertexList.add(Vertex.newE());
        return vertexList;
    }

}
