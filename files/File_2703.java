/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/9 0:04</create-date>
 *
 * <copyright file="DictionaryMaker.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary;

import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.dictionary.item.Item;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;
import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.*;
import java.util.*;

import static com.hankcs.hanlp.HanLP.Config.IOAdapter;
import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * ä¸€ä¸ªé€šç”¨çš„è¯?å…¸åˆ¶ä½œå·¥å…·ï¼Œè¯?æ?¡æ ¼å¼?ï¼šè¯? æ ‡ç­¾ é¢‘æ¬¡
 * @author hankcs
 */
public class DictionaryMaker implements ISaveAble
{
    BinTrie<Item> trie;

    public DictionaryMaker()
    {
        trie = new BinTrie<Item>();
    }

    /**
     * å?‘è¯?å…¸ä¸­åŠ å…¥ä¸€ä¸ªè¯?è¯­
     *
     * @param word è¯?è¯­
     */
    public void add(IWord word)
    {
        Item item = trie.get(word.getValue());
        if (item == null)
        {
            item = new Item(word.getValue(), word.getLabel());
            trie.put(item.key, item);
        }
        else
        {
            item.addLabel(word.getLabel());
        }
    }

    public void add(String value, String label)
    {
        add(new Word(value, label));
    }

    /**
     * åˆ é™¤ä¸€ä¸ªè¯?æ?¡
     * @param value
     */
    public void remove(String value)
    {
        trie.remove(value);
    }

    public Item get(String key)
    {
        return trie.get(key);
    }

    public Item get(IWord word)
    {
        return get(word.getValue());
    }

    public TreeSet<String> labelSet()
    {
        TreeSet<String> labelSet = new TreeSet<String>();
        for (Map.Entry<String, Item> entry : entrySet())
        {
            labelSet.addAll(entry.getValue().labelMap.keySet());
        }

        return labelSet;
    }

    /**
     * è¯»å?–æ‰€æœ‰æ?¡ç›®
     *
     * @param path
     * @return
     */
    public static List<Item> loadAsItemList(String path)
    {
        List<Item> itemList = new LinkedList<Item>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(IOAdapter == null ? new FileInputStream(path) :
                                                                                 IOAdapter.open(path), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null)
            {
                Item item = Item.create(line);
                if (item == null)
                {
                    logger.warning("ä½¿ç”¨ã€?" + line + "ã€‘åˆ›å»ºItemå¤±è´¥");
                    return null;
//                    continue;
                }
                itemList.add(item);
            }
        }
        catch (Exception e)
        {
            logger.warning("è¯»å?–è¯?å…¸" + path + "å?‘ç”Ÿå¼‚å¸¸" + e);
            return null;
        }

        return itemList;
    }

    /**
     * ä»Žç£?ç›˜åŠ è½½
     * @param path
     * @return
     */
    public static DictionaryMaker load(String path)
    {
        DictionaryMaker dictionaryMaker = new DictionaryMaker();
        dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(path));

        return dictionaryMaker;
    }

    /**
     * æ?’å…¥å…¨éƒ¨æ?¡ç›®
     *
     * @param itemList
     */
    public void addAll(List<Item> itemList)
    {
        for (Item item : itemList)
        {
            add(item);
        }
    }

    /**
     * æ?’å…¥æ–°æ?¡ç›®ï¼Œä¸?æ‰§è¡Œå?ˆå¹¶
     *
     * @param itemList
     */
    public void addAllNotCombine(List<Item> itemList)
    {
        for (Item item : itemList)
        {
            addNotCombine(item);
        }
    }

    /**
     * æ?’å…¥æ?¡ç›®
     *
     * @param item
     */
    public void add(Item item)
    {
        Item innerItem = trie.get(item.key);
        if (innerItem == null)
        {
            innerItem = item;
            trie.put(innerItem.key, innerItem);
        }
        else
        {
            innerItem.combine(item);
        }
    }

    /**
     * æµ?è§ˆæ‰€æœ‰è¯?æ?¡
     * @return
     */
    public Set<Map.Entry<String, Item>> entrySet()
    {
        return trie.entrySet();
    }

    public Set<String> keySet()
    {
        return trie.keySet();
    }

    /**
     * æ?’å…¥æ?¡ç›®ï¼Œä½†æ˜¯ä¸?å?ˆå¹¶ï¼Œå¦‚æžœå·²æœ‰åˆ™å¿½ç•¥
     *
     * @param item
     */
    public void addNotCombine(Item item)
    {
        Item innerItem = trie.get(item.key);
        if (innerItem == null)
        {
            innerItem = item;
            trie.put(innerItem.key, innerItem);
        }
    }

    /**
     * å?ˆå¹¶ä¸¤éƒ¨è¯?å…¸
     *
     * @param pathA
     * @param pathB
     * @return
     */
    public static DictionaryMaker combine(String pathA, String pathB)
    {
        DictionaryMaker dictionaryMaker = new DictionaryMaker();
        dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(pathA));
        dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(pathB));

        return dictionaryMaker;
    }

    /**
     * å?ˆå¹¶å¤šéƒ¨è¯?å…¸
     *
     * @param pathArray
     * @return
     */
    public static DictionaryMaker combine(String... pathArray)
    {
        DictionaryMaker dictionaryMaker = new DictionaryMaker();
        for (String path : pathArray)
        {
            logger.warning("æ­£åœ¨å¤„ç?†" + path);
            dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(path));
        }
        return dictionaryMaker;
    }

    /**
     * å¯¹é™¤ç¬¬ä¸€ä¸ªä¹‹å¤–çš„è¯?å…¸æ‰§è¡Œæ ‡å‡†åŒ–ï¼Œå¹¶ä¸”å?ˆå¹¶
     *
     * @param pathArray
     * @return
     */
    public static DictionaryMaker combineWithNormalization(String[] pathArray)
    {
        DictionaryMaker dictionaryMaker = new DictionaryMaker();
        logger.info("æ­£åœ¨å¤„ç?†ä¸»è¯?å…¸" + pathArray[0]);
        dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(pathArray[0]));
        for (int i = 1; i < pathArray.length; ++i)
        {
            logger.info("æ­£åœ¨å¤„ç?†å‰¯è¯?å…¸" + pathArray[i] + "ï¼Œå°†æ‰§è¡Œæ–°è¯?å?ˆå¹¶æ¨¡å¼?");
            dictionaryMaker.addAllNotCombine(DictionaryMaker.loadAsItemList(pathArray[i]));
        }
        return dictionaryMaker;
    }

    /**
     * å?ˆå¹¶ï¼Œå?ªè¡¥å……é™¤ç¬¬ä¸€ä¸ªè¯?å…¸å¤–å…¶ä»–è¯?å…¸çš„æ–°è¯?
     *
     * @param pathArray
     * @return
     */
    public static DictionaryMaker combineWhenNotInclude(String[] pathArray)
    {
        DictionaryMaker dictionaryMaker = new DictionaryMaker();
        logger.info("æ­£åœ¨å¤„ç?†ä¸»è¯?å…¸" + pathArray[0]);
        dictionaryMaker.addAll(DictionaryMaker.loadAsItemList(pathArray[0]));
        for (int i = 1; i < pathArray.length; ++i)
        {
            logger.info("æ­£åœ¨å¤„ç?†å‰¯è¯?å…¸" + pathArray[i] + "ï¼Œå¹¶ä¸”è¿‡æ»¤å·²æœ‰è¯?å…¸");
            dictionaryMaker.addAllNotCombine(DictionaryMaker.normalizeFrequency(DictionaryMaker.loadAsItemList(pathArray[i])));
        }
        return dictionaryMaker;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("è¯?æ?¡æ•°é‡?ï¼š");
        sb.append(trie.size());
        return sb.toString();
    }

    @Override
    public boolean saveTxtTo(String path)
    {
        if (trie.size() == 0) return true;  // å¦‚æžœæ²¡æœ‰è¯?æ?¡ï¼Œé‚£ä¹Ÿç®—æˆ?åŠŸäº†
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(path), "UTF-8"));
            Set<Map.Entry<String, Item>> entries = trie.entrySet();
            for (Map.Entry<String, Item> entry : entries)
            {
                bw.write(entry.getValue().toString());
                bw.newLine();
            }
            bw.close();
        }
        catch (Exception e)
        {
            logger.warning("ä¿?å­˜åˆ°" + path + "å¤±è´¥" + e);
            return false;
        }

        return true;
    }

    public void add(String param)
    {
        Item item = Item.create(param);
        if (item != null) add(item);
    }

    public static interface Filter
    {
        /**
         * æ˜¯å?¦ä¿?å­˜è¿™ä¸ªæ?¡ç›®
         * @param item
         * @return trueè¡¨ç¤ºä¿?å­˜
         */
        boolean onSave(Item item);
    }

    /**
     * å…?è®¸ä¿?å­˜ä¹‹å‰?å¯¹å…¶å?šä¸€äº›è°ƒæ•´
     *
     * @param path
     * @param filter
     * @return
     */
    public boolean saveTxtTo(String path, Filter filter)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(path), "UTF-8"));
            Set<Map.Entry<String, Item>> entries = trie.entrySet();
            for (Map.Entry<String, Item> entry : entries)
            {
                if (filter.onSave(entry.getValue()))
                {
                    bw.write(entry.getValue().toString());
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (Exception e)
        {
            logger.warning("ä¿?å­˜åˆ°" + path + "å¤±è´¥" + e);
            return false;
        }

        return true;
    }

    /**
     * è°ƒæ•´é¢‘æ¬¡ï¼ŒæŒ‰æŽ’åº?å?Žçš„æ¬¡åº?ç»™å®šé¢‘æ¬¡
     *
     * @param itemList
     * @return å¤„ç?†å?Žçš„åˆ—è¡¨
     */
    public static List<Item> normalizeFrequency(List<Item> itemList)
    {
        for (Item item : itemList)
        {
            ArrayList<Map.Entry<String, Integer>> entryArray = new ArrayList<Map.Entry<String, Integer>>(item.labelMap.entrySet());
            Collections.sort(entryArray, new Comparator<Map.Entry<String, Integer>>()
            {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            int index = 1;
            for (Map.Entry<String, Integer> pair : entryArray)
            {
                item.labelMap.put(pair.getKey(), index);
                ++index;
            }
        }
        return itemList;
    }
}
