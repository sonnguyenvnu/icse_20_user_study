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
 * å?°æ¹¾ç¹?ä½“è½¬ç¹?ä½“
 * @author hankcs
 */
public class TaiwanToTraditionalChineseDictionary extends BaseChineseDictionary
{
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();
    static
    {
        long start = System.currentTimeMillis();
        String datPath = HanLP.Config.tcDictionaryRoot + "tw2t";
        if (!loadDat(datPath, trie))
        {
            TreeMap<String, String> tw2t = new TreeMap<String, String>();
            if (!load(tw2t, true, HanLP.Config.tcDictionaryRoot + "t2tw.txt"))
            {
                throw new IllegalArgumentException("å?°æ¹¾ç¹?ä½“è½¬ç¹?ä½“åŠ è½½å¤±è´¥");
            }
            trie.build(tw2t);
            saveDat(datPath, trie, tw2t.entrySet());
        }
        logger.info("å?°æ¹¾ç¹?ä½“è½¬ç¹?ä½“åŠ è½½æˆ?åŠŸï¼Œè€—æ—¶" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToTraditionalChinese(String traditionalTaiwanChineseString)
    {
        return segLongest(traditionalTaiwanChineseString.toCharArray(), trie);
    }

    public static String convertToTraditionalChinese(char[] traditionalTaiwanChineseString)
    {
        return segLongest(traditionalTaiwanChineseString, trie);
    }
}
