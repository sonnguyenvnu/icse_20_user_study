/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/18 18:37</create-date>
 *
 * <copyright file="KeywordExtractor.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.summary;

import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * æ??å?–å…³é”®è¯?çš„åŸºç±»
 *
 * @author hankcs
 */
public abstract class KeywordExtractor
{
    /**
     * é»˜è®¤åˆ†è¯?å™¨
     */
    protected Segment defaultSegment;

    public KeywordExtractor(Segment defaultSegment)
    {
        this.defaultSegment = defaultSegment;
    }

    public KeywordExtractor()
    {
        this(StandardTokenizer.SEGMENT);
    }

    /**
     * æ˜¯å?¦åº”å½“å°†è¿™ä¸ªtermçº³å…¥è®¡ç®—ï¼Œè¯?æ€§å±žäºŽå??è¯?ã€?åŠ¨è¯?ã€?å‰¯è¯?ã€?å½¢å®¹è¯?
     *
     * @param term
     * @return æ˜¯å?¦åº”å½“
     */
    protected boolean shouldInclude(Term term)
    {
        // é™¤æŽ‰å?œç”¨è¯?
        return CoreStopWordDictionary.shouldInclude(term);
    }

    /**
     * è®¾ç½®å…³é”®è¯?æ??å?–å™¨ä½¿ç”¨çš„åˆ†è¯?å™¨
     *
     * @param segment ä»»ä½•å¼€å?¯äº†è¯?æ€§æ ‡æ³¨çš„åˆ†è¯?å™¨
     * @return è‡ªå·±
     */
    public KeywordExtractor setSegment(Segment segment)
    {
        defaultSegment = segment;
        return this;
    }

    public Segment getSegment()
    {
        return defaultSegment;
    }

    /**
     * æ??å?–å…³é”®è¯?
     *
     * @param document å…³é”®è¯?
     * @param size     éœ€è¦?å‡ ä¸ªå…³é”®è¯?
     * @return
     */
    public List<String> getKeywords(String document, int size)
    {
        return getKeywords(defaultSegment.seg(document), size);
    }

    /**
     * æ??å?–å…³é”®è¯?ï¼ˆtop 10ï¼‰
     *
     * @param document æ–‡ç« 
     * @return
     */
    public List<String> getKeywords(String document)
    {
        return getKeywords(defaultSegment.seg(document), 10);
    }

    protected void filter(List<Term> termList)
    {
        ListIterator<Term> listIterator = termList.listIterator();
        while (listIterator.hasNext())
        {
            if (!shouldInclude(listIterator.next()))
                listIterator.remove();
        }
    }

    abstract public List<String> getKeywords(List<Term> termList, int size);
}
