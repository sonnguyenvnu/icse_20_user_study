/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/9 0:06</create-date>
 *
 * <copyright file="Item.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary.item;

import java.util.*;

/**
 * �?典中的一个�?�目，比如“希望 v 7685 vn 616�?
 * @author hankcs
 */
public class Item extends SimpleItem
{
    /**
     * 该�?�目的索引，比如“啊�?
     */
    public String key;

    public Item(String key, String label)
    {
        this(key);
        labelMap.put(label, 1);
    }

    public Item(String key)
    {
        super();
        this.key = key;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(key);
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(labelMap.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<String, Integer> entry : entries)
        {
            sb.append(' ');             // 现阶段�?典分隔符统一使用空格
            sb.append(entry.getKey());
            sb.append(' ');
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    /**
     * 获�?�首个label
     * @return
     */
    public String firstLabel()
    {
        return labelMap.keySet().iterator().next();
    }

    /**
     *
     * @param param 类似 “希望 v 7685 vn 616�? 的字串
     * @return
     */
    public static Item create(String param)
    {
        if (param == null) return null;
        String mark = "\\s";    // 分隔符，历�?�格�?用空格，但是现在觉得用制表符比较好
        if (param.indexOf('\t') > 0) mark = "\t";
        String[] array = param.split(mark);
        return create(array);
    }

    public static Item create(String param[])
    {
        if (param.length % 2 == 0) return null;
        Item item = new Item(param[0]);
        int natureCount = (param.length - 1) / 2;
        for (int i = 0; i < natureCount; ++i)
        {
            item.labelMap.put(param[1 + 2 * i], Integer.parseInt(param[2 + 2 * i]));
        }
        return item;
    }
}
