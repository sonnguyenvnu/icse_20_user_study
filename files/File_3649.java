/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/20 20:20</create-date>
 *
 * <copyright file="NLPTokenizer.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.tokenizer;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.other.CharTable;
import com.hankcs.hanlp.dictionary.ts.SimplifiedChineseDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.utility.SentencesUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * ç¹?ä½“ä¸­æ–‡åˆ†è¯?å™¨
 *
 * @author hankcs
 */
public class TraditionalChineseTokenizer
{
    /**
     * é¢„ç½®åˆ†è¯?å™¨
     */
    public static Segment SEGMENT = HanLP.newSegment();

    private static List<Term> segSentence(String text)
    {
        String sText = CharTable.convert(text);
        List<Term> termList = SEGMENT.seg(sText);
        int offset = 0;
        for (Term term : termList)
        {
            term.offset = offset;
            term.word = text.substring(offset, offset + term.length());
            offset += term.length();
        }

        return termList;
    }

    public static List<Term> segment(String text)
    {
        List<Term> termList = new LinkedList<Term>();
        for (String sentence : SentencesUtil.toSentenceList(text))
        {
            termList.addAll(segSentence(sentence));
        }

        return termList;
    }

    /**
     * åˆ†è¯?
     *
     * @param text æ–‡æœ¬
     * @return åˆ†è¯?ç»“æžœ
     */
    public static List<Term> segment(char[] text)
    {
        return segment(CharTable.convert(text));
    }

    /**
     * åˆ‡åˆ†ä¸ºå?¥å­?å½¢å¼?
     *
     * @param text æ–‡æœ¬
     * @return å?¥å­?åˆ—è¡¨
     */
    public static List<List<Term>> seg2sentence(String text)
    {
        List<List<Term>> resultList = new LinkedList<List<Term>>();
        {
            for (String sentence : SentencesUtil.toSentenceList(text))
            {
                resultList.add(segment(sentence));
            }
        }

        return resultList;
    }

    /**
     * åˆ†è¯?æ–­å?¥ è¾“å‡ºå?¥å­?å½¢å¼?
     *
     * @param text     å¾…åˆ†è¯?å?¥å­?
     * @param shortest æ˜¯å?¦æ–­å?¥ä¸ºæœ€ç»†çš„å­?å?¥ï¼ˆå°†é€—å?·ä¹Ÿè§†ä½œåˆ†éš”ç¬¦ï¼‰
     * @return å?¥å­?åˆ—è¡¨ï¼Œæ¯?ä¸ªå?¥å­?ç”±ä¸€ä¸ªå?•è¯?åˆ—è¡¨ç»„æˆ?
     */
    public static List<List<Term>> seg2sentence(String text, boolean shortest)
    {
        return SEGMENT.seg2sentence(text, shortest);
    }
}
