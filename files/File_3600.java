/*
 * <summary></summary>
 * <author>hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2015/5/4 23:36</create-date>
 *
 * <copyright file="BasicTokenizer.java">
 * Copyright (c) 2003-2015, hankcs. All Right Reserved, http://www.hankcs.com/
 * </copyright>
 */
package com.hankcs.hanlp.tokenizer;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * åŸºç¡€åˆ†è¯?å™¨ï¼Œå?ªå?šåŸºæœ¬NGramåˆ†è¯?ï¼Œä¸?è¯†åˆ«å‘½å??å®žä½“ï¼Œä¸?ä½¿ç”¨ç”¨æˆ·è¯?å…¸
 * @author hankcs
 */
public class BasicTokenizer
{
    /**
     * é¢„ç½®åˆ†è¯?å™¨
     */
    public static final Segment SEGMENT = HanLP.newSegment().enableAllNamedEntityRecognize(false).enableCustomDictionary(false);

    /**
     * åˆ†è¯?
     * @param text æ–‡æœ¬
     * @return åˆ†è¯?ç»“æžœ
     */
    public static List<Term> segment(String text)
    {
        return SEGMENT.seg(text.toCharArray());
    }

    /**
     * åˆ†è¯?
     * @param text æ–‡æœ¬
     * @return åˆ†è¯?ç»“æžœ
     */
    public static List<Term> segment(char[] text)
    {
        return SEGMENT.seg(text);
    }

    /**
     * åˆ‡åˆ†ä¸ºå?¥å­?å½¢å¼?
     * @param text æ–‡æœ¬
     * @return å?¥å­?åˆ—è¡¨
     */
    public static List<List<Term>> seg2sentence(String text)
    {
        return SEGMENT.seg2sentence(text);
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
