/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-06-23 8:30 PM</create-date>
 *
 * <copyright file="TransforMatrix.java">
 * Copyright (c) 2018, Han He. All Rights Reserved, http://www.hankcs.com/
 * This source is subject to Han He. Please contact Han He for more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary;

import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * @author hankcs
 */
public abstract class TransformMatrix
{
    // HMM的五元组
    //int[] observations;
    /**
     * �?状�?
     */
    public int[] states;
    /**
     * �?始概率
     */
    public double[] start_probability;
    /**
     * 转移概率
     */
    public double[][] transititon_probability;
    /**
     * 内部标签下标最大值�?超过这个值，用于矩阵创建
     */
    protected int ordinaryMax;
    /**
     * 储存转移矩阵
     */
    int[][] matrix;
    /**
     * 储存�?个标签出现的次数
     */
    int[] total;
    /**
     * 所有标签出现的总次数
     */
    int totalFrequency;

    public boolean load(String path)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            // 第一行是矩阵的�?�个类型
            String line = br.readLine();
            String[] _param = line.split(",");
            // 为了制表方便，第一个label是废物，所以�?抹掉它
            String[] labels = new String[_param.length - 1];
            System.arraycopy(_param, 1, labels, 0, labels.length);
            int[] ordinaryArray = new int[labels.length];
            ordinaryMax = 0;
            for (int i = 0; i < ordinaryArray.length; ++i)
            {
                ordinaryArray[i] = ordinal(labels[i]);
                ordinaryMax = Math.max(ordinaryMax, ordinaryArray[i]);
            }
            ++ordinaryMax;
            matrix = new int[ordinaryMax][ordinaryMax];
            for (int i = 0; i < ordinaryMax; ++i)
            {
                for (int j = 0; j < ordinaryMax; ++j)
                {
                    matrix[i][j] = 0;
                }
            }
            // 之�?�就�??述了矩阵
            while ((line = br.readLine()) != null)
            {
                String[] paramArray = line.split(",");
                int currentOrdinary = ordinal(paramArray[0]);
                for (int i = 0; i < ordinaryArray.length; ++i)
                {
                    matrix[currentOrdinary][ordinaryArray[i]] = Integer.valueOf(paramArray[1 + i]);
                }
            }
            br.close();
            // 需�?统计一下�?个标签出现的次数
            total = new int[ordinaryMax];
            for (int j = 0; j < ordinaryMax; ++j)
            {
                total[j] = 0;
                for (int i = 0; i < ordinaryMax; ++i)
                {
                    total[j] += matrix[j][i]; // 按行累加
                }
            }
            for (int j = 0; j < ordinaryMax; ++j)
            {
                if (total[j] == 0)
                {
                    for (int i = 0; i < ordinaryMax; ++i)
                    {
                        total[j] += matrix[i][j]; // 按列累加
                    }
                }
            }
            for (int j = 0; j < ordinaryMax; ++j)
            {
                totalFrequency += total[j];
            }
            // 下�?�计算HMM四元组
            states = ordinaryArray;
            start_probability = new double[ordinaryMax];
            for (int s : states)
            {
                double frequency = total[s] + 1e-8;
                start_probability[s] = -Math.log(frequency / totalFrequency);
            }
            transititon_probability = new double[ordinaryMax][ordinaryMax];
            for (int from : states)
            {
                for (int to : states)
                {
                    double frequency = matrix[from][to] + 1e-8;
                    transititon_probability[from][to] = -Math.log(frequency / total[from]);
//                    System.out.println("from" + NR.values()[from] + " to" + NR.values()[to] + " = " + transititon_probability[from][to]);
                }
            }
        }
        catch (Exception e)
        {
            logger.warning("读�?�" + path + "失败" + e);
            return false;
        }

        return true;
    }

    /**
     * 拓展内部矩阵,仅用于通过�??射新增了枚举实例之�?�的兼容措施
     */
    public void extend(int ordinaryMax)
    {
        this.ordinaryMax = ordinaryMax;
        double[][] n_transititon_probability = new double[ordinaryMax][ordinaryMax];
        for (int i = 0; i < transititon_probability.length; i++)
        {
            System.arraycopy(transititon_probability[i], 0, n_transititon_probability[i], 0, transititon_probability.length);
        }
        transititon_probability = n_transititon_probability;

        int[] n_total = new int[ordinaryMax];
        System.arraycopy(total, 0, n_total, 0, total.length);
        total = n_total;

        double[] n_start_probability = new double[ordinaryMax];
        System.arraycopy(start_probability, 0, n_start_probability, 0, start_probability.length);
        start_probability = n_start_probability;

        int[][] n_matrix = new int[ordinaryMax][ordinaryMax];
        for (int i = 0; i < matrix.length; i++)
        {
            System.arraycopy(matrix[i], 0, n_matrix[i], 0, matrix.length);
        }
        matrix = n_matrix;
    }

    public abstract int ordinal(String tag);

    public int getTotalFrequency(int ordinal)
    {
        return total[ordinal];
    }
}
