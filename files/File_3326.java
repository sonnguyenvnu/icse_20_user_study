/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/9 21:34</create-date>
 *
 * <copyright file="Table.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.crf;

/**
 * 给一个实例生�?一个元素表
 * @author hankcs
 */
public class Table
{
    /**
     * 真实值，请�?�?直接读�?�
     */
    public String[][] v;
    static final String HEAD = "_B";

    @Override
    public String toString()
    {
        if (v == null) return "null";
        final StringBuilder sb = new StringBuilder(v.length * v[0].length * 2);
        for (String[] line : v)
        {
            for (String element : line)
            {
                sb.append(element).append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 获�?�表中�?一个元素
     * @param x
     * @param y
     * @return
     */
    public String get(int x, int y)
    {
        if (x < 0) return HEAD + x;
        if (x >= v.length) return HEAD + "+" + (x - v.length + 1);

        return v[x][y];
    }

    public void setLast(int x, String t)
    {
        v[x][v[x].length - 1] = t;
    }

    public int size()
    {
        return v.length;
    }
}
