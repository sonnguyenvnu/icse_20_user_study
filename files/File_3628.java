/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/8 1:58</create-date>
 *
 * <copyright file="NotionalTokenizer.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.tokenizer;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;
import java.util.ListIterator;

/**
 * å®žè¯?åˆ†è¯?å™¨ï¼Œè‡ªåŠ¨ç§»é™¤å?œç”¨è¯?
 *
 * @author hankcs
 */
public class NotionalTokenizer
{
    /**
     * é¢„ç½®åˆ†è¯?å™¨
     */
    public static Segment SEGMENT = HanLP.newSegment();

    public static List<Term> segment(String text)
    {
        return segment(text.toCharArray());
    }

    /**
     * åˆ†è¯?
     *
     * @param text æ–‡æœ¬
     * @return åˆ†è¯?ç»“æžœ
     */
    public static List<Term> segment(char[] text)
    {
        List<Term> resultList = SEGMENT.seg(text);
        ListIterator<Term> listIterator = resultList.listIterator();
        while (listIterator.hasNext())
        {
            if (!CoreStopWordDictionary.shouldInclude(listIterator.next()))
            {
                listIterator.remove();
            }
        }

        return resultList;
    }

    /**
     * åˆ‡åˆ†ä¸ºå?¥å­?å½¢å¼?
     *
     * @param text
     * @return
     */
    public static List<List<Term>> seg2sentence(String text)
    {
        List<List<Term>> sentenceList = SEGMENT.seg2sentence(text);
        for (List<Term> sentence : sentenceList)
        {
            ListIterator<Term> listIterator = sentence.listIterator();
            while (listIterator.hasNext())
            {
                if (!CoreStopWordDictionary.shouldInclude(listIterator.next()))
                {
                    listIterator.remove();
                }
            }
        }

        return sentenceList;
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

    /**
     * åˆ‡åˆ†ä¸ºå?¥å­?å½¢å¼?
     *
     * @param text
     * @param filterArrayChain è‡ªå®šä¹‰è¿‡æ»¤å™¨é“¾
     * @return
     */
    public static List<List<Term>> seg2sentence(String text, Filter... filterArrayChain)
    {
        List<List<Term>> sentenceList = SEGMENT.seg2sentence(text);
        for (List<Term> sentence : sentenceList)
        {
            ListIterator<Term> listIterator = sentence.listIterator();
            while (listIterator.hasNext())
            {
                if (filterArrayChain != null)
                {
                    Term term = listIterator.next();
                    for (Filter filter : filterArrayChain)
                    {
                        if (!filter.shouldInclude(term))
                        {
                            listIterator.remove();
                            break;
                        }
                    }
                }
            }
        }

        return sentenceList;
    }
}
