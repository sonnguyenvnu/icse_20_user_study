/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/05/2014/5/21 18:05</create-date>
 *
 * <copyright file="Graph.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.seg.common;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hankcs
 */
public class Graph
{
    /**
     * 顶点
     */
    public Vertex[] vertexes;

    /**
     * 边，到达下标i
     */
    public List<EdgeFrom>[] edgesTo;

    /**
     * 将一个�?网转为�?图
     * @param vertexes 顶点数组
     */
    public Graph(Vertex[] vertexes)
    {
        int size = vertexes.length;
        this.vertexes = vertexes;
        edgesTo = new List[size];
        for (int i = 0; i < size; ++i)
        {
            edgesTo[i] = new LinkedList<EdgeFrom>();
        }
    }

    /**
     * 连接两个节点
     * @param from 起点
     * @param to 终点
     * @param weight 花费
     */
    public void connect(int from, int to, double weight)
    {
        edgesTo[to].add(new EdgeFrom(from, weight, vertexes[from].word + '@' + vertexes[to].word));
    }


    /**
     * 获�?�到达顶点to的边列表
     * @param to 到达顶点to
     * @return 到达顶点to的边列表
     */
    public List<EdgeFrom> getEdgeListTo(int to)
    {
        return edgesTo[to];
    }

    @Override
    public String toString()
    {
        return "Graph{" +
                "vertexes=" + Arrays.toString(vertexes) +
                ", edgesTo=" + Arrays.toString(edgesTo) +
                '}';
    }

    public String printByTo()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("========按终点打�?�========\n");
        for (int to = 0; to < edgesTo.length; ++to)
        {
            List<EdgeFrom> edgeFromList = edgesTo[to];
            for (EdgeFrom edgeFrom : edgeFromList)
            {
                sb.append(String.format("to:%3d, from:%3d, weight:%05.2f, word:%s\n", to, edgeFrom.from, edgeFrom.weight, edgeFrom.name));
            }
        }

        return sb.toString();
    }

    /**
     * 根�?�节点下标数组解释出对应的路径
     * @param path
     * @return
     */
    public List<Vertex> parsePath(int[] path)
    {
        List<Vertex> vertexList = new LinkedList<Vertex>();
        for (int i : path)
        {
            vertexList.add(vertexes[i]);
        }

        return vertexList;
    }

    /**
     * 从一个路径中转�?�出空格隔开的结果
     * @param path
     * @return
     */
    public static String parseResult(List<Vertex> path)
    {
        if (path.size() < 2)
        {
            throw new RuntimeException("路径节点数�?于2:" + path);
        }
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < path.size() - 1; ++i)
        {
            Vertex v = path.get(i);
            sb.append(v.getRealWord() + " ");
        }

        return sb.toString();
    }

    public Vertex[] getVertexes()
    {
        return vertexes;
    }

    public List<EdgeFrom>[] getEdgesTo()
    {
        return edgesTo;
    }
}
