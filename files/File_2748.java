/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/17 17:24</create-date>
 *
 * <copyright file="SuffixDictionary.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary;

import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;

import java.util.*;

/**
 * å?Žç¼€æ ‘è¯?å…¸
 * @author hankcs
 */
public class SuffixDictionary
{
    BinTrie<Integer> trie;

    public SuffixDictionary()
    {
        trie = new BinTrie<Integer>();
    }

    /**
     * æ·»åŠ ä¸€ä¸ªè¯?è¯­
     * @param word
     */
    public void add(String word)
    {
        word = reverse(word);
        trie.put(word, word.length());
    }

    public void addAll(String total)
    {
        for (int i = 0; i < total.length(); ++i)
        {
            add(String.valueOf(total.charAt(i)));
        }
    }

    public void addAll(String[] total)
    {
        for (String single : total)
        {
            add(single);
        }
    }

    /**
     * æŸ¥æ‰¾æ˜¯å?¦æœ‰è¯¥å?Žç¼€
     * @param suffix
     * @return
     */
    public int get(String suffix)
    {
        suffix = reverse(suffix);
        Integer length = trie.get(suffix);
        if (length == null) return 0;

        return length;
    }

    /**
     * è¯?è¯­æ˜¯å?¦ä»¥è¯¥è¯?å…¸ä¸­çš„æŸ?ä¸ªå?•è¯?ç»“å°¾
     * @param word
     * @return
     */
    public boolean endsWith(String word)
    {
        word = reverse(word);
        return trie.commonPrefixSearchWithValue(word).size() > 0;
    }

    /**
     * èŽ·å?–æœ€é•¿çš„å?Žç¼€
     * @param word
     * @return
     */
    public int getLongestSuffixLength(String word)
    {
        word = reverse(word);
        LinkedList<Map.Entry<String, Integer>> suffixList = trie.commonPrefixSearchWithValue(word);
        if (suffixList.size() == 0) return 0;
        return suffixList.getLast().getValue();
    }

    private static String reverse(String word)
    {
        return new StringBuilder(word).reverse().toString();
    }

    /**
     * é”®å€¼å¯¹
     * @return
     */
    public Set<Map.Entry<String, Integer>> entrySet()
    {
        Set<Map.Entry<String, Integer>> treeSet = new LinkedHashSet<Map.Entry<String, Integer>>();
        for (Map.Entry<String, Integer> entry : trie.entrySet())
        {
            treeSet.add(new AbstractMap.SimpleEntry<String, Integer>(reverse(entry.getKey()), entry.getValue()));
        }

        return treeSet;
    }
}
