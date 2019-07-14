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

import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.lexical.AbstractLexicalAnalyzer;

import java.io.IOException;
import java.util.List;

/**
 * å?¯ä¾›è‡ªç„¶è¯­è¨€å¤„ç?†ç”¨çš„åˆ†è¯?å™¨ï¼Œæ›´é‡?è§†å‡†ç¡®çŽ‡ã€‚
 *
 * @author hankcs
 */
public class NLPTokenizer
{
    /**
     * é¢„ç½®åˆ†è¯?å™¨
     */
    public static AbstractLexicalAnalyzer ANALYZER;

    static
    {
        try
        {
            // ç›®å‰?æ„ŸçŸ¥æœºçš„æ•ˆæžœç›¸å½“ä¸?é”™ï¼Œå¦‚æžœèƒ½åœ¨æ›´å¤§çš„è¯­æ–™åº“ä¸Šè®­ç»ƒå°±æ›´å¥½äº†
            ANALYZER = new PerceptronLexicalAnalyzer();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static List<Term> segment(String text)
    {
        return ANALYZER.seg(text);
    }

    /**
     * åˆ†è¯?
     *
     * @param text æ–‡æœ¬
     * @return åˆ†è¯?ç»“æžœ
     */
    public static List<Term> segment(char[] text)
    {
        return ANALYZER.seg(text);
    }

    /**
     * åˆ‡åˆ†ä¸ºå?¥å­?å½¢å¼?
     *
     * @param text æ–‡æœ¬
     * @return å?¥å­?åˆ—è¡¨
     */
    public static List<List<Term>> seg2sentence(String text)
    {
        return ANALYZER.seg2sentence(text);
    }

    /**
     * è¯?æ³•åˆ†æž?
     *
     * @param sentence
     * @return ç»“æž„åŒ–å?¥å­?
     */
    public static Sentence analyze(final String sentence)
    {
        return ANALYZER.analyze(sentence);
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
        return ANALYZER.seg2sentence(text, shortest);
    }
}
