/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/20 17:24</create-date>
 *
 * <copyright file="WordNatureDependencyParser.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dependency;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.dependency.common.Edge;
import com.hankcs.hanlp.dependency.common.Node;
import com.hankcs.hanlp.model.bigram.WordNatureDependencyModel;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.utility.GlobalObjectPool;

import java.util.List;

/**
 * ä¸€ä¸ªç®€å?•çš„å?¥æ³•åˆ†æž?å™¨
 *
 * @author hankcs
 */
public class WordNatureDependencyParser extends MinimumSpanningTreeParser
{
    private WordNatureDependencyModel model;

    public WordNatureDependencyParser(WordNatureDependencyModel model)
    {
        this.model = model;
    }

    public WordNatureDependencyParser(String modelPath)
    {
        model = GlobalObjectPool.get(modelPath);
        if (model != null) return;
        model = new WordNatureDependencyModel(modelPath);
        GlobalObjectPool.put(modelPath, model);
    }

    public WordNatureDependencyParser()
    {
        this(HanLP.Config.WordNatureModelPath);
    }

    /**
     * åˆ†æž?å?¥å­?çš„ä¾?å­˜å?¥æ³•
     *
     * @param termList å?¥å­?ï¼Œå?¯ä»¥æ˜¯ä»»ä½•å…·æœ‰è¯?æ€§æ ‡æ³¨åŠŸèƒ½çš„åˆ†è¯?å™¨çš„åˆ†è¯?ç»“æžœ
     * @return CoNLLæ ¼å¼?çš„ä¾?å­˜å?¥æ³•æ ‘
     */
    public static CoNLLSentence compute(List<Term> termList)
    {
        return new WordNatureDependencyParser().parse(termList);
    }

    /**
     * åˆ†æž?å?¥å­?çš„ä¾?å­˜å?¥æ³•
     *
     * @param sentence å?¥å­?
     * @return CoNLLæ ¼å¼?çš„ä¾?å­˜å?¥æ³•æ ‘
     */
    public static CoNLLSentence compute(String sentence)
    {
        return new WordNatureDependencyParser().parse(sentence);
    }

    @Override
    protected Edge makeEdge(Node[] nodeArray, int from, int to)
    {
        return model.getEdge(nodeArray[from], nodeArray[to]);
    }
}
