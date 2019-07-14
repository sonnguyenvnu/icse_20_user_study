/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/13 13:12</create-date>
 *
 * <copyright file="CoreSynonymDictionary.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.algorithm.EditDistance;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.dictionary.common.CommonSynonymDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;
import static com.hankcs.hanlp.utility.Predefine.logger;
/**
 * æ ¸å¿ƒå?Œä¹‰è¯?è¯?å…¸
 *
 * @author hankcs
 */
public class CoreSynonymDictionary
{
    static CommonSynonymDictionary dictionary;

    static
    {
        try
        {
            long start = System.currentTimeMillis();
            dictionary = CommonSynonymDictionary.create(IOUtil.newInputStream(HanLP.Config.CoreSynonymDictionaryDictionaryPath));
            logger.info("è½½å…¥æ ¸å¿ƒå?Œä¹‰è¯?è¯?å…¸æˆ?åŠŸï¼Œè€—æ—¶ " + (System.currentTimeMillis() - start) + " ms");
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("è½½å…¥æ ¸å¿ƒå?Œä¹‰è¯?è¯?å…¸å¤±è´¥" + e);
        }
    }

    /**
     * èŽ·å?–ä¸€ä¸ªè¯?çš„å?Œä¹‰è¯?ï¼ˆæ„?ä¹‰å®Œå…¨ç›¸å?Œçš„ï¼Œå?³{@link com.hankcs.hanlp.dictionary.common.CommonSynonymDictionary.SynonymItem#type}
     * == {@link com.hankcs.hanlp.corpus.synonym.Synonym.Type#EQUAL}çš„ï¼‰åˆ—è¡¨
     * @param key
     * @return
     */
    public static CommonSynonymDictionary.SynonymItem get(String key)
    {
        return dictionary.get(key);
    }

    /**
     * ä¸?åˆ†è¯?ç›´æŽ¥è½¬æ?¢
     * @param text
     * @return
     */
    public static String rewriteQuickly(String text)
    {
        return dictionary.rewriteQuickly(text);
    }

    public static String rewrite(String text)
    {
        return dictionary.rewrite(text);
    }

    /**
     * è¯­ä¹‰è·?ç¦»
     * @param itemA
     * @param itemB
     * @return
     */
    public static long distance(CommonSynonymDictionary.SynonymItem itemA, CommonSynonymDictionary.SynonymItem itemB)
    {
        return itemA.distance(itemB);
    }

    /**
     * åˆ¤æ–­ä¸¤ä¸ªå?•è¯?ä¹‹é—´çš„è¯­ä¹‰è·?ç¦»
     * @param A
     * @param B
     * @return
     */
    public static long distance(String A, String B)
    {
        CommonSynonymDictionary.SynonymItem itemA = get(A);
        CommonSynonymDictionary.SynonymItem itemB = get(B);
        if (itemA == null || itemB == null) return Long.MAX_VALUE;

        return distance(itemA, itemB);
    }

    /**
     * è®¡ç®—ä¸¤ä¸ªå?•è¯?ä¹‹é—´çš„ç›¸ä¼¼åº¦ï¼Œ0è¡¨ç¤ºä¸?ç›¸ä¼¼ï¼Œ1è¡¨ç¤ºå®Œå…¨ç›¸ä¼¼
     * @param A
     * @param B
     * @return
     */
    public static double similarity(String A, String B)
    {
        long distance = distance(A, B);
        if (distance > dictionary.getMaxSynonymItemIdDistance()) return 0.0;

        return (dictionary.getMaxSynonymItemIdDistance() - distance) / (double) dictionary.getMaxSynonymItemIdDistance();
    }

    /**
     * å°†åˆ†è¯?ç»“æžœè½¬æ?¢ä¸ºå?Œä¹‰è¯?åˆ—è¡¨
     * @param sentence å?¥å­?
     * @param withUndefinedItem æ˜¯å?¦ä¿?ç•™è¯?å…¸ä¸­æ²¡æœ‰çš„è¯?è¯­
     * @return
     */
    public static List<CommonSynonymDictionary.SynonymItem> convert(List<Term> sentence, boolean withUndefinedItem)
    {
        List<CommonSynonymDictionary.SynonymItem> synonymItemList = new ArrayList<CommonSynonymDictionary.SynonymItem>(sentence.size());
        for (Term term : sentence)
        {
            CommonSynonymDictionary.SynonymItem item = get(term.word);
            if (item == null)
            {
                if (withUndefinedItem)
                {
                    item = CommonSynonymDictionary.SynonymItem.createUndefined(term.word);
                    synonymItemList.add(item);
                }

            }
            else
            {
                synonymItemList.add(item);
            }
        }

        return synonymItemList;
    }

    /**
     * èŽ·å?–è¯­ä¹‰æ ‡ç­¾
     * @return
     */
    public static long[] getLexemeArray(List<CommonSynonymDictionary.SynonymItem> synonymItemList)
    {
        long[] array = new long[synonymItemList.size()];
        int i = 0;
        for (CommonSynonymDictionary.SynonymItem item : synonymItemList)
        {
            array[i++] = item.entry.id;
        }
        return array;
    }


    public long distance(List<CommonSynonymDictionary.SynonymItem> synonymItemListA, List<CommonSynonymDictionary.SynonymItem> synonymItemListB)
    {
        return EditDistance.compute(synonymItemListA, synonymItemListB);
    }

    public long distance(long[] arrayA, long[] arrayB)
    {
        return EditDistance.compute(arrayA, arrayB);
    }
}
