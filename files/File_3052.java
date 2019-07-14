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
import com.hankcs.hanlp.dictionary.common.CommonSynonymDictionaryEx;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.utility.TextUtility;

import java.util.ArrayList;
import java.util.List;
import static com.hankcs.hanlp.utility.Predefine.logger;
/**
 * æ ¸å¿ƒå?Œä¹‰è¯?è¯?å…¸(ä½¿ç”¨è¯­ä¹‰idä½œä¸ºvalueï¼‰
 *
 * @author hankcs
 */
public class CoreSynonymDictionaryEx
{
    static CommonSynonymDictionaryEx dictionary;

    static
    {
        try
        {
            dictionary = CommonSynonymDictionaryEx.create(IOUtil.newInputStream(HanLP.Config.CoreSynonymDictionaryDictionaryPath));
        }
        catch (Exception e)
        {
            logger.severe("è½½å…¥æ ¸å¿ƒå?Œä¹‰è¯?è¯?å…¸å¤±è´¥");
            throw new IllegalArgumentException(e);
        }
    }

    public static Long[] get(String key)
    {
        return dictionary.get(key);
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
     * å°†åˆ†è¯?ç»“æžœè½¬æ?¢ä¸ºå?Œä¹‰è¯?åˆ—è¡¨
     * @param sentence å?¥å­?
     * @param withUndefinedItem æ˜¯å?¦ä¿?ç•™è¯?å…¸ä¸­æ²¡æœ‰çš„è¯?è¯­
     * @return
     */
    public static List<Long[]> convert(List<Term> sentence, boolean withUndefinedItem)
    {
        List<Long[]> synonymItemList = new ArrayList<Long[]>(sentence.size());
        for (Term term : sentence)
        {
            // é™¤æŽ‰å?œç”¨è¯?
            if (term.nature == null) continue;
            String nature = term.nature.toString();
            char firstChar = nature.charAt(0);
            switch (firstChar)
            {
                case 'm':
                {
                    if (!TextUtility.isAllChinese(term.word)) continue;
                }break;
                case 'w':
                {
                    continue;
                }
            }
            // å?œç”¨è¯?
            if (CoreStopWordDictionary.contains(term.word)) continue;
            Long[] item = get(term.word);
//            logger.trace("{} {}", wordResult.word, Arrays.toString(item));
            if (item == null)
            {
                if (withUndefinedItem)
                {
                    item = new Long[]{Long.MAX_VALUE / 3};
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
