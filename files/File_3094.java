/*
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-03-24 下�?�6:46</create-date>
 *
 * <copyright file="PartOfSpeechTagDictionary.java" company="�?农场">
 * Copyright (c) 2018, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.other;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.IOUtil;

import java.util.Map;
import java.util.TreeMap;

/**
 * �?性标注集中英映射表
 *
 * @author hankcs
 */
public class PartOfSpeechTagDictionary
{
    /**
     * �?性映射表
     */
    public static Map<String, String> translator = new TreeMap<String, String>();

    static
    {
        load(HanLP.Config.PartOfSpeechTagDictionary);
    }

    public static void load(String path)
    {
        IOUtil.LineIterator iterator = new IOUtil.LineIterator(path);
        iterator.next(); // header
        while (iterator.hasNext())
        {
            String[] args = iterator.next().split(",");
            if (args.length < 3) continue;
            translator.put(args[1], args[2]);
        }
    }

    /**
     * 翻译�?性
     *
     * @param tag
     * @return
     */
    public static String translate(String tag)
    {
        String cn = translator.get(tag);
        if (cn == null) return tag;
        return cn;
    }
}
