/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/1 23:04</create-date>
 *
 * <copyright file="SimplifiedChineseDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.ts;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.utility.Predefine;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 简体=�?体�?典
 * @author hankcs
 */
public class SimplifiedChineseDictionary extends BaseChineseDictionary
{
    /**
     * 简体=�?体
     */
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();
    
    static
    {
        long start = System.currentTimeMillis();
        if (!load(HanLP.Config.tcDictionaryRoot + "s2t.txt", trie, false))
        {
            throw new IllegalArgumentException("简�?�?典" + HanLP.Config.tcDictionaryRoot + "s2t.txt" + Predefine.BIN_EXT + "加载失败");
        }

        logger.info("简�?�?典" + HanLP.Config.tcDictionaryRoot + "s2t.txt" + Predefine.BIN_EXT + "加载�?功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToTraditionalChinese(String simplifiedChineseString)
    {
        return segLongest(simplifiedChineseString.toCharArray(), trie);
    }

    public static String convertToTraditionalChinese(char[] simplifiedChinese)
    {
        return segLongest(simplifiedChinese, trie);
    }

    public static String getTraditionalChinese(String simplifiedChinese)
    {
        return trie.get(simplifiedChinese);
    }
}
