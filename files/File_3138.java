/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/1 21:04</create-date>
 *
 * <copyright file="TraditionalChineseDictionary.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.ts;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * ç¹?ç®€è¯?å…¸ï¼Œæ??ä¾›ç®€ç¹?è½¬æ?¢
 * @author hankcs
 */
public class TraditionalChineseDictionary extends BaseChineseDictionary
{
    /**
     * ç¹?ä½“=ç®€ä½“
     */
    public static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();

    static
    {
        long start = System.currentTimeMillis();
        if (!load(HanLP.Config.tcDictionaryRoot + "t2s.txt", trie, false))
        {
            throw new IllegalArgumentException("ç¹?ç®€è¯?å…¸" + HanLP.Config.tcDictionaryRoot + "t2s.txt" + "åŠ è½½å¤±è´¥");
        }

        logger.info("ç¹?ç®€è¯?å…¸" + HanLP.Config.tcDictionaryRoot + "t2s.txt" + "åŠ è½½æˆ?åŠŸï¼Œè€—æ—¶" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToSimplifiedChinese(String traditionalChineseString)
    {
        return segLongest(traditionalChineseString.toCharArray(), trie);
    }

    public static String convertToSimplifiedChinese(char[] traditionalChinese)
    {
        return segLongest(traditionalChinese, trie);
    }

}
