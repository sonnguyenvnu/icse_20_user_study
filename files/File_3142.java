/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-08-30 AM10:29</create-date>
 *
 * <copyright file="SimplifiedToHongKongChineseDictionary.java" company="ç ?å†œåœº">
 * Copyright (c) 2008-2016, ç ?å†œåœº. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.ts;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;

import java.util.TreeMap;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * ç¹?ä½“è½¬å?°æ¹¾ç¹?ä½“
 * @author hankcs
 */
public class TraditionalToTaiwanChineseDictionary extends BaseChineseDictionary
{
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();
    static
    {
        long start = System.currentTimeMillis();
        String datPath = HanLP.Config.tcDictionaryRoot + "t2tw";
        if (!loadDat(datPath, trie))
        {
            TreeMap<String, String> t2tw = new TreeMap<String, String>();
            if (!load(t2tw, false, HanLP.Config.tcDictionaryRoot + "t2tw.txt"))
            {
                throw new IllegalArgumentException("ç¹?ä½“è½¬å?°æ¹¾ç¹?ä½“åŠ è½½å¤±è´¥");
            }
            trie.build(t2tw);
            saveDat(datPath, trie, t2tw.entrySet());
        }
        logger.info("ç¹?ä½“è½¬å?°æ¹¾ç¹?ä½“åŠ è½½æˆ?åŠŸï¼Œè€—æ—¶" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToTaiwanChinese(String traditionalTaiwanChineseString)
    {
        return segLongest(traditionalTaiwanChineseString.toCharArray(), trie);
    }

    public static String convertToTaiwanChinese(char[] traditionalTaiwanChineseString)
    {
        return segLongest(traditionalTaiwanChineseString, trie);
    }
}
