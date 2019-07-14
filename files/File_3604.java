/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/19 18:33</create-date>
 *
 * <copyright file="IndexTokenizer.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.tokenizer;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * ç´¢å¼•åˆ†è¯?å™¨
 * @author hankcs
 */
public class IndexTokenizer
{
    /**
     * é¢„ç½®åˆ†è¯?å™¨
     */
    public static final Segment SEGMENT = HanLP.newSegment().enableIndexMode(true);
    public static List<Term> segment(String text)
    {
        return SEGMENT.seg(text);
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
