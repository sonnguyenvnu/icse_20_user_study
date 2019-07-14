/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-07-23 PM4:24</create-date>
 *
 * <copyright file="WordVectorMap.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.mining.word2vec;

import java.io.IOException;
import java.util.*;

/**
 * �?�?��?模型
 *
 * @author hankcs
 */
public class WordVectorModel extends AbstractVectorModel<String>
{
    /**
     * 加载模型<br>
     *
     * @param modelFileName 模型路径
     * @throws IOException 加载错误
     */
    public WordVectorModel(String modelFileName) throws IOException
    {
        super(loadVectorMap(modelFileName));
    }

    private static TreeMap<String, Vector> loadVectorMap(String modelFileName) throws IOException
    {
        VectorsReader reader = new VectorsReader(modelFileName);
        reader.readVectorFile();
        TreeMap<String, Vector> map = new TreeMap<String, Vector>();
        for (int i = 0; i < reader.vocab.length; i++)
        {
            map.put(reader.vocab[i], new Vector(reader.matrix[i]));
        }
        return map;
    }

    /**
     * 返回跟 A - B + C 最相似的�?语,比如 中国 - 北京 + 东京 = 日本。输入顺�?按照 中国 北京 东京
     *
     * @param A �?�加法的�?语
     * @param B �?��?法的�?语
     * @param C �?�加法的�?语
     * @return 与(A - B + C)语义�?离最近的�?语�?�其相似度列表
     */
    public List<Map.Entry<String, Float>> analogy(String A, String B, String C)
    {
        return analogy(A, B, C, 10);
    }

    /**
     * 返回跟 A - B + C 最相似的�?语,比如 中国 - 北京 + 东京 = 日本。输入顺�?按照 中国 北京 东京
     *
     * @param A    �?�加法的�?语
     * @param B    �?��?法的�?语
     * @param C    �?�加法的�?语
     * @param size topN个
     * @return 与(A - B + C)语义�?离最近的�?语�?�其相似度列表
     */
    public List<Map.Entry<String, Float>> analogy(String A, String B, String C, int size)
    {
        Vector a = storage.get(A);
        Vector b = storage.get(B);
        Vector c = storage.get(C);
        if (a == null || b == null || c == null)
        {
            return Collections.emptyList();
        }

        List<Map.Entry<String, Float>> resultList = nearest(a.minus(b).add(c), size + 3);
        ListIterator<Map.Entry<String, Float>> listIterator = resultList.listIterator();
        while (listIterator.hasNext())
        {
            String key = listIterator.next().getKey();
            if (key.equals(A) || key.equals(B) || key.equals(C))
            {
                listIterator.remove();
            }
        }

        if (resultList.size() > size)
        {
            resultList = resultList.subList(0, size);
        }

        return resultList;
    }

    @Override
    public Vector query(String query)
    {
        return vector(query);
    }
}
