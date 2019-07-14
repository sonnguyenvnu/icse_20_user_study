/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>16/2/6 PM5:01</create-date>
 *
 * <copyright file="ToolUtility.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.classification.utilities;

import java.util.*;

/**
 * @author hankcs
 */
public class CollectionUtility
{
    public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> input, final boolean desc)
    {
        LinkedHashMap<K, V> output = new LinkedHashMap<K, V>(input.size());
        ArrayList<Map.Entry<K, V>> entryList = new ArrayList<Map.Entry<K, V>>(input.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<K, V>>()
        {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                if (desc) return o2.getValue().compareTo(o1.getValue());
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<K, V> entry : entryList)
        {
            output.put(entry.getKey(), entry.getValue());
        }

        return output;
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> input)
    {
        return sortMapByValue(input, true);
    }

    public static String max(Map<String, Double> scoreMap)
    {
        double max = Double.NEGATIVE_INFINITY;
        String best = null;
        for (Map.Entry<String, Double> entry : scoreMap.entrySet())
        {
            Double score = entry.getValue();
            if (score > max)
            {
                max = score;
                best = entry.getKey();
            }
        }

        return best;
    }

    /**
     * 分割数组为两个数组
     * @param src 原数组
     * @param rate 第一个数组所�?�的比例
     * @return 两个数组
     */
    public static String[][] spiltArray(String[] src, double rate)
    {
        assert 0 <= rate && rate <= 1;
        String[][] output = new String[2][];
        output[0] = new String[(int) (src.length * rate)];
        output[1] = new String[src.length - output[0].length];
        System.arraycopy(src, 0, output[0], 0, output[0].length);
        System.arraycopy(src, output[0].length, output[1], 0, output[1].length);
        return output;
    }

    /**
     * 分割Map,其中旧map直接被改�?�
     * @param src
     * @param rate
     * @return
     */
    public static Map<String, String[]> splitMap(Map<String, String[]> src, double rate)
    {
        assert 0 <= rate && rate <= 1;
        Map<String, String[]> output = new TreeMap<String, String[]>();
        for (Map.Entry<String, String[]> entry : src.entrySet())
        {
            String[][] array = spiltArray(entry.getValue(), rate);
            output.put(entry.getKey(), array[0]);
            entry.setValue(array[1]);
        }

        return output;
    }
}
