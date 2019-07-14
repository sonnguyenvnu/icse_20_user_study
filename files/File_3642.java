/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/24 23:17</create-date>
 *
 * <copyright file="SpeedTokenizer.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.tokenizer;

import com.hankcs.hanlp.seg.Other.DoubleArrayTrieSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * æž?é€Ÿåˆ†è¯?ï¼ŒåŸºäºŽDouble Array Trieå®žçŽ°çš„è¯?å…¸åˆ†è¯?ï¼Œé€‚ç”¨äºŽâ€œé«˜å?žå??é‡?â€?â€œç²¾åº¦ä¸€èˆ¬â€?çš„åœºå?ˆ
 * @author hankcs
 */
public class SpeedTokenizer
{
    /**
     * é¢„ç½®åˆ†è¯?å™¨
     */
    public static final Segment SEGMENT = new DoubleArrayTrieSegment();
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
