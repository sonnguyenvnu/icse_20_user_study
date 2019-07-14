/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-08-30 AM10:29</create-date>
 *
 * <copyright file="SimplifiedToHongKongChineseDictionary.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.ts;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;

import java.util.TreeMap;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * �?�湾�?体转香港�?体
 *
 * @author hankcs
 */
public class TaiwanToHongKongChineseDictionary extends BaseChineseDictionary
{
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();

    static
    {
        long start = System.currentTimeMillis();
        String datPath = HanLP.Config.tcDictionaryRoot + "tw2hk";
        if (!loadDat(datPath, trie))
        {
            TreeMap<String, String> t2hk = new TreeMap<String, String>();
            TreeMap<String, String> tw2t = new TreeMap<String, String>();
            if (!load(t2hk, false, HanLP.Config.tcDictionaryRoot + "t2hk.txt") ||
                    !load(tw2t, true, HanLP.Config.tcDictionaryRoot + "t2tw.txt"))
            {
                throw new IllegalArgumentException("�?�湾�?体转香港�?体�?典加载失败");
            }
            combineReverseChain(t2hk, tw2t, false);
            trie.build(t2hk);
            saveDat(datPath, trie, t2hk.entrySet());
        }
        logger.info("�?�湾�?体转香港�?体�?典加载�?功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToTraditionalHongKongChinese(String traditionalTaiwanChinese)
    {
        return segLongest(traditionalTaiwanChinese.toCharArray(), trie);
    }

    public static String convertToTraditionalHongKongChinese(char[] traditionalTaiwanChinese)
    {
        return segLongest(traditionalTaiwanChinese, trie);
    }
}
