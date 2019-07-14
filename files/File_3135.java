/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-08-30 AM10:29</create-date>
 *
 * <copyright file="SimplifiedToTaiwanChineseDictionary.java" company="�?农场">
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
 * �?�湾�?体转简体
 * @author hankcs
 */
public class TaiwanToSimplifiedChineseDictionary extends BaseChineseDictionary
{
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();
    static
    {
        long start = System.currentTimeMillis();
        String datPath = HanLP.Config.tcDictionaryRoot + "tw2s";
        if (!loadDat(datPath, trie))
        {
            TreeMap<String, String> t2s = new TreeMap<String, String>();
            TreeMap<String, String> tw2t = new TreeMap<String, String>();
            if (!load(t2s, false, HanLP.Config.tcDictionaryRoot + "t2s.txt") ||
                    !load(tw2t, true, HanLP.Config.tcDictionaryRoot + "t2tw.txt"))
            {
                throw new IllegalArgumentException("�?�湾�?体转简体�?典加载失败");
            }
            combineReverseChain(t2s, tw2t, true);
            trie.build(t2s);
            saveDat(datPath, trie, t2s.entrySet());
        }
        logger.info("�?�湾�?体转简体�?典加载�?功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToSimplifiedChinese(String traditionalTaiwanChinese)
    {
        return segLongest(traditionalTaiwanChinese.toCharArray(), trie);
    }

    public static String convertToSimplifiedChinese(char[] traditionalTaiwanChinese)
    {
        return segLongest(traditionalTaiwanChinese, trie);
    }
}
