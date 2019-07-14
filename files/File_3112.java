/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/15 19:39</create-date>
 *
 * <copyright file="CoreStopwordDictionary.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.stopword;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.utility.Predefine;
import com.hankcs.hanlp.utility.TextUtility;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.ListIterator;
import static com.hankcs.hanlp.utility.Predefine.logger;


/**
 * æ ¸å¿ƒå?œç”¨è¯?è¯?å…¸
 * @author hankcs
 */
public class CoreStopWordDictionary
{
    static StopWordDictionary dictionary;
    static
    {
        load(HanLP.Config.CoreStopWordDictionaryPath, true);
    }

    /**
     * é‡?æ–°åŠ è½½{@link HanLP.Config#CoreStopWordDictionaryPath}æ‰€æŒ‡å®šçš„å?œç”¨è¯?è¯?å…¸ï¼Œå¹¶ä¸”ç”Ÿæˆ?æ–°ç¼“å­˜ã€‚
     */
    public static void reload()
    {
        load(HanLP.Config.CoreStopWordDictionaryPath, false);
    }

    /**
     * åŠ è½½å?¦ä¸€éƒ¨å?œç”¨è¯?è¯?å…¸
     * @param coreStopWordDictionaryPath è¯?å…¸è·¯å¾„
     * @param loadCacheIfPossible æ˜¯å?¦ä¼˜å…ˆåŠ è½½ç¼“å­˜ï¼ˆé€Ÿåº¦æ›´å¿«ï¼‰
     */
    public static void load(String coreStopWordDictionaryPath, boolean loadCacheIfPossible)
    {
        ByteArray byteArray = loadCacheIfPossible ? ByteArray.createByteArray(coreStopWordDictionaryPath + Predefine.BIN_EXT) : null;
        if (byteArray == null)
        {
            try
            {
                dictionary = new StopWordDictionary(HanLP.Config.CoreStopWordDictionaryPath);
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(HanLP.Config.CoreStopWordDictionaryPath + Predefine.BIN_EXT)));
                dictionary.save(out);
                out.close();
            }
            catch (Exception e)
            {
                logger.severe("è½½å…¥å?œç”¨è¯?è¯?å…¸" + HanLP.Config.CoreStopWordDictionaryPath + "å¤±è´¥"  + TextUtility.exceptionToString(e));
                throw new RuntimeException("è½½å…¥å?œç”¨è¯?è¯?å…¸" + HanLP.Config.CoreStopWordDictionaryPath + "å¤±è´¥");
            }
        }
        else
        {
            dictionary = new StopWordDictionary();
            dictionary.load(byteArray);
        }
    }

    public static boolean contains(String key)
    {
        return dictionary.contains(key);
    }

    /**
     * æ ¸å¿ƒå?œç”¨è¯?å…¸çš„æ ¸å¿ƒè¿‡æ»¤å™¨ï¼Œè¯?æ€§å±žäºŽå??è¯?ã€?åŠ¨è¯?ã€?å‰¯è¯?ã€?å½¢å®¹è¯?ï¼Œå¹¶ä¸”ä¸?åœ¨å?œç”¨è¯?è¡¨ä¸­æ‰?ä¸?ä¼šè¢«è¿‡æ»¤
     */
    public static Filter FILTER = new Filter()
    {
        @Override
        public boolean shouldInclude(Term term)
        {
            // é™¤æŽ‰å?œç”¨è¯?
            String nature = term.nature != null ? term.nature.toString() : "ç©º";
            char firstChar = nature.charAt(0);
            switch (firstChar)
            {
                case 'm':
                case 'b':
                case 'c':
                case 'e':
                case 'o':
                case 'p':
                case 'q':
                case 'u':
                case 'y':
                case 'z':
                case 'r':
                case 'w':
                {
                    return false;
                }
                default:
                {
                    if (!CoreStopWordDictionary.contains(term.word))
                    {
                        return true;
                    }
                }
                break;
            }

            return false;
        }
    };

    /**
     * æ˜¯å?¦åº”å½“å°†è¿™ä¸ªtermçº³å…¥è®¡ç®—
     *
     * @param term
     * @return æ˜¯å?¦åº”å½“
     */
    public static boolean shouldInclude(Term term)
    {
        return FILTER.shouldInclude(term);
    }

    /**
     * æ˜¯å?¦åº”å½“åŽ»æŽ‰è¿™ä¸ªè¯?
     * @param term è¯?
     * @return æ˜¯å?¦åº”å½“åŽ»æŽ‰
     */
    public static boolean shouldRemove(Term term)
    {
        return !shouldInclude(term);
    }

    /**
     * åŠ å…¥å?œç”¨è¯?åˆ°å?œç”¨è¯?è¯?å…¸ä¸­
     * @param stopWord å?œç”¨è¯?
     * @return è¯?å…¸æ˜¯å?¦å?‘ç”Ÿäº†æ”¹å?˜
     */
    public static boolean add(String stopWord)
    {
        return dictionary.add(stopWord);
    }

    /**
     * ä»Žå?œç”¨è¯?è¯?å…¸ä¸­åˆ é™¤å?œç”¨è¯?
     * @param stopWord å?œç”¨è¯?
     * @return è¯?å…¸æ˜¯å?¦å?‘ç”Ÿäº†æ”¹å?˜
     */
    public static boolean remove(String stopWord)
    {
        return dictionary.remove(stopWord);
    }

    /**
     * å¯¹åˆ†è¯?ç»“æžœåº”ç”¨è¿‡æ»¤
     * @param termList
     */
    public static void apply(List<Term> termList)
    {
        ListIterator<Term> listIterator = termList.listIterator();
        while (listIterator.hasNext())
        {
            if (shouldRemove(listIterator.next())) listIterator.remove();
        }
    }
}
