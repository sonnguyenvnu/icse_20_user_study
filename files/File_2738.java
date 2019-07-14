/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/9 22:30</create-date>
 *
 * <copyright file="CommonDictioanry.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary;

import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static com.hankcs.hanlp.HanLP.Config.IOAdapter;
import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * å?¯ä»¥è°ƒæ•´å¤§å°?çš„è¯?å…¸
 *
 * @author hankcs
 */
public abstract class SimpleDictionary<V>
{
    BinTrie<V> trie = new BinTrie<V>();

    public boolean load(String path)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(IOAdapter == null ? new FileInputStream(path) : IOAdapter.open(path), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null)
            {
                Map.Entry<String, V> entry = onGenerateEntry(line);
                if (entry == null) continue;
                trie.put(entry.getKey(), entry.getValue());
            }
            br.close();
        }
        catch (Exception e)
        {
            logger.warning("è¯»å?–" + path + "å¤±è´¥" + e);
            return false;
        }
        return true;
    }

    /**
     * æŸ¥è¯¢ä¸€ä¸ªå?•è¯?
     *
     * @param key
     * @return å?•è¯?å¯¹åº”çš„æ?¡ç›®
     */
    public V get(String key)
    {
        return trie.get(key);
    }

    /**
     * ç”±å?‚æ•°æž„é€ ä¸€ä¸ªè¯?æ?¡
     *
     * @param line
     * @return
     */
    protected abstract Map.Entry<String, V> onGenerateEntry(String line);

    /**
     * ä»¥æˆ‘ä¸ºä¸»è¯?å…¸ï¼Œå?ˆå¹¶ä¸€ä¸ªå‰¯è¯?å…¸ï¼Œæˆ‘æœ‰çš„è¯?æ?¡ä¸?ä¼šè¢«å‰¯è¯?å…¸è¦†ç›–
     * @param other å‰¯è¯?å…¸
     */
    public void combine(SimpleDictionary<V> other)
    {
        if (other.trie == null)
        {
            logger.warning("æœ‰ä¸ªè¯?å…¸è¿˜æ²¡åŠ è½½");
            return;
        }
        for (Map.Entry<String, V> entry : other.trie.entrySet())
        {
            if (trie.containsKey(entry.getKey())) continue;
            trie.put(entry.getKey(), entry.getValue());
        }
    }
    /**
     * èŽ·å?–é”®å€¼å¯¹é›†å?ˆ
     * @return
     */
    public Set<Map.Entry<String, V>> entrySet()
    {
        return trie.entrySet();
    }

    /**
     * é”®é›†å?ˆ
     * @return
     */
    public Set<String> keySet()
    {
        TreeSet<String> keySet = new TreeSet<String>();

        for (Map.Entry<String, V> entry : entrySet())
        {
            keySet.add(entry.getKey());
        }

        return keySet;
    }

    /**
     * è¿‡æ»¤éƒ¨åˆ†è¯?æ?¡
     * @param filter è¿‡æ»¤å™¨
     * @return åˆ é™¤äº†å¤šå°‘æ?¡
     */
    public int remove(Filter filter)
    {
        int size = trie.size();
        for (Map.Entry<String, V> entry : entrySet())
        {
            if (filter.remove(entry))
            {
                trie.remove(entry.getKey());
            }
        }

        return size - trie.size();
    }

    public interface Filter<V>
    {
        boolean remove(Map.Entry<String, V> entry);
    }
    /**
     * å?‘ä¸­åŠ å…¥å?•è¯?
     * @param key
     * @param value
     */
    public void add(String key, V value)
    {
        trie.put(key, value);
    }

    public int size()
    {
        return trie.size();
    }
}
