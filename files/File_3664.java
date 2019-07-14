/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>2015/7/14 11:01</create-date>
 *
 * <copyright file="WordNatureUtil.java" company="ç ?å†œåœº">
 * Copyright (c) 2008-2015, ç ?å†œåœº. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.utility;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * è·Ÿè¯?è¯­ä¸Žè¯?æ€§æœ‰å…³çš„å·¥å…·ç±»ï¼Œå?¯ä»¥å…¨å±€åŠ¨æ€?ä¿®æ”¹HanLPå†…éƒ¨è¯?åº“
 *
 * @author hankcs
 */
public class LexiconUtility
{
    /**
     * ä»ŽHanLPçš„è¯?åº“ä¸­æ??å?–æŸ?ä¸ªå?•è¯?çš„å±žæ€§ï¼ˆåŒ…æ‹¬æ ¸å¿ƒè¯?å…¸å’Œç”¨æˆ·è¯?å…¸ï¼‰
     *
     * @param word å?•è¯?
     * @return åŒ…å?«è¯?æ€§ä¸Žé¢‘æ¬¡çš„ä¿¡æ?¯
     */
    public static CoreDictionary.Attribute getAttribute(String word)
    {
        CoreDictionary.Attribute attribute = CoreDictionary.get(word);
        if (attribute != null) return attribute;
        return CustomDictionary.get(word);
    }

    /**
     * è¯?åº“æ˜¯å?¦æ”¶å½•äº†è¯?è¯­ï¼ˆæŸ¥è¯¢æ ¸å¿ƒè¯?å…¸å’Œç”¨æˆ·è¯?å…¸ï¼‰
     * @param word
     * @return
     */
    public static boolean contains(String word)
    {
        return getAttribute(word) != null;
    }

    /**
     * ä»ŽHanLPçš„è¯?åº“ä¸­æ??å?–æŸ?ä¸ªå?•è¯?çš„å±žæ€§ï¼ˆåŒ…æ‹¬æ ¸å¿ƒè¯?å…¸å’Œç”¨æˆ·è¯?å…¸ï¼‰
     *
     * @param term å?•è¯?
     * @return åŒ…å?«è¯?æ€§ä¸Žé¢‘æ¬¡çš„ä¿¡æ?¯
     */
    public static CoreDictionary.Attribute getAttribute(Term term)
    {
        return getAttribute(term.word);
    }

    /**
     * èŽ·å?–æŸ?ä¸ªå?•è¯?çš„è¯?é¢‘
     * @param word
     * @return
     */
    public static int getFrequency(String word)
    {
        CoreDictionary.Attribute attribute = getAttribute(word);
        if (attribute == null) return 0;
        return attribute.totalFrequency;
    }

    /**
     * è®¾ç½®æŸ?ä¸ªå?•è¯?çš„å±žæ€§
     * @param word
     * @param attribute
     * @return
     */
    public static boolean setAttribute(String word, CoreDictionary.Attribute attribute)
    {
        if (attribute == null) return false;

        if (CoreDictionary.trie.set(word, attribute)) return true;
        if (CustomDictionary.dat.set(word, attribute)) return true;
        if (CustomDictionary.trie == null)
        {
            CustomDictionary.add(word);
        }
        CustomDictionary.trie.put(word, attribute);
        return true;
    }

    /**
     * è®¾ç½®æŸ?ä¸ªå?•è¯?çš„å±žæ€§
     * @param word
     * @param natures
     * @return
     */
    public static boolean setAttribute(String word, Nature... natures)
    {
        if (natures == null) return false;

        CoreDictionary.Attribute attribute = new CoreDictionary.Attribute(natures, new int[natures.length]);
        Arrays.fill(attribute.frequency, 1);

        return setAttribute(word, attribute);
    }

    /**
     * è®¾ç½®æŸ?ä¸ªå?•è¯?çš„å±žæ€§
     * @param word
     * @param natures
     * @return
     */
    public static boolean setAttribute(String word, String... natures)
    {
        if (natures == null) return false;

        Nature[] natureArray = new Nature[natures.length];
        for (int i = 0; i < natureArray.length; i++)
        {
            natureArray[i] = Nature.create(natures[i]);
        }

        return setAttribute(word, natureArray);
    }


    /**
     * è®¾ç½®æŸ?ä¸ªå?•è¯?çš„å±žæ€§
     * @param word
     * @param natureWithFrequency
     * @return
     */
    public static boolean setAttribute(String word, String natureWithFrequency)
    {
        CoreDictionary.Attribute attribute = CoreDictionary.Attribute.create(natureWithFrequency);
        return setAttribute(word, attribute);
    }

    /**
     * å°†å­—ç¬¦ä¸²è¯?æ€§è½¬ä¸ºEnumè¯?æ€§
     * @param name è¯?æ€§å??ç§°
     * @param customNatureCollector ä¸€ä¸ªæ”¶é›†é›†å?ˆ
     * @return è½¬æ?¢ç»“æžœ
     */
    public static Nature convertStringToNature(String name, LinkedHashSet<Nature> customNatureCollector)
    {
        Nature nature = Nature.fromString(name);
        if (nature == null)
        {
            nature = Nature.create(name);
            if (customNatureCollector != null) customNatureCollector.add(nature);
        }
        return nature;
    }

    /**
     * å°†å­—ç¬¦ä¸²è¯?æ€§è½¬ä¸ºEnumè¯?æ€§
     * @param name è¯?æ€§å??ç§°
     * @return è½¬æ?¢ç»“æžœ
     */
    public static Nature convertStringToNature(String name)
    {
        return convertStringToNature(name, null);
    }
}
