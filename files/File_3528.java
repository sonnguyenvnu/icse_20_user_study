/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/05/2014/5/21 19:09</create-date>
 *
 * <copyright file="NShortPath.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.seg.NShort.Path;

import com.hankcs.hanlp.seg.common.EdgeFrom;
import com.hankcs.hanlp.seg.common.Graph;
import com.hankcs.hanlp.utility.Predefine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hankcs
 */
public class NShortPath
{
    /**
     * 图
     */
    private Graph graph;
    /**
     * �?N个最短路径
     */
    private int N;
    /**
     * 图的顶点个数
     */
    private int vertexCount;
    /**
     * �?个顶点的N个最�?边的起点
     */
    private CQueue[][] fromArray;
    /**
     * 到�?个顶点的�?N个最�?边的�?��?之和
     */
    private double[][] weightArray;

    /**
     * 构造一个N最短路径计算器
     * @param graph �?计算的图
     * @param N �?计算�?几�?�最短路径，当然结果�?一定就是N�?�
     */
    public NShortPath(Graph graph, int N)
    {
        calculate(graph, N);
    }

    /**
     * �?始化，主�?分�?内存
     * @param inGraph 输入图
     * @param nValueKind 希望的N值
     */
    private void initNShortPath(Graph inGraph, int nValueKind)
    {
        graph = inGraph;
        N = nValueKind;

        // 获�?�顶点的数目
        vertexCount = inGraph.vertexes.length;

        fromArray = new CQueue[vertexCount - 1][];  // �?包�?�起点
        weightArray = new double[vertexCount - 1][];

        //�?个节点的最�?堆
        for (int i = 0; i < vertexCount - 1; i++)
        {
            fromArray[i] = new CQueue[nValueKind];
            weightArray[i] = new double[nValueKind];

            for (int j = 0; j < nValueKind; j++)
                fromArray[i][j] = new CQueue();
        }
    }

    /**
     * 计算出所有结点上�?�能的路径，为路径数�?��??供数�?�准备
     * @param inGraph 输入图
     * @param nValueKind �?N个结果
     */
    private void calculate(Graph inGraph, int nValueKind)
    {
        initNShortPath(inGraph, nValueKind);

        QueueElement tmpElement;
        CQueue queWork = new CQueue();
        double eWeight;

        for (int nCurNode = 1; nCurNode < vertexCount; ++nCurNode)
        {
            // 将所有到当�?结点（nCurNode)�?�能到达的边根�?�eWeight排�?并压入队列
            enQueueCurNodeEdges(queWork, nCurNode);

            // �?始化当�?结点所有边的eWeight值
            for (int i = 0; i < N; ++i)
                weightArray[nCurNode - 1][i] = Double.MAX_VALUE;

            // 将queWork中的内容装入fromArray
            tmpElement = queWork.deQueue();
            if (tmpElement != null)
            {
                for (int i = 0; i < N; ++i)
                {
                    eWeight = tmpElement.weight;
                    weightArray[nCurNode - 1][i] = eWeight;
                    do
                    {
                        fromArray[nCurNode - 1][i].enQueue(new QueueElement(tmpElement.from, tmpElement.index, 0));
                        tmpElement = queWork.deQueue();
                        if (tmpElement == null)
                        {
                            i = N;
                            break;
                        }
                    } while (tmpElement.weight == eWeight);
                }
            }
        }
    }

    /**
     * 将所有到当�?结点（nCurNode）�?�能的边根�?�eWeight排�?并压入队列
     * @param queWork
     * @param nCurNode
     */
    private void enQueueCurNodeEdges(CQueue queWork, int nCurNode)
    {
        int nPreNode;
        double eWeight;
        List<EdgeFrom> pEdgeToList;

        queWork.clear();
        pEdgeToList = graph.getEdgeListTo(nCurNode);

        // Get all the edgesFrom
        for (EdgeFrom e : pEdgeToList)
        {
            nPreNode = e.from;
            eWeight = e.weight;

            for (int i = 0; i < N; i++)
            {
                // 第一个结点，没有PreNode，直接加入队列
                if (nPreNode == 0)
                {
                    queWork.enQueue(new QueueElement(nPreNode, i, eWeight));
                    break;
                }

                // 如果PreNode的Weight == INFINITE_VALUE，则没有必�?继续下去了
                if (weightArray[nPreNode - 1][i] == Double.MAX_VALUE)
                    break;

                queWork.enQueue(new QueueElement(nPreNode, i, eWeight + weightArray[nPreNode - 1][i]));
            }
        }
    }

    /**
     * 获�?��?index+1短的路径
     * @param index index �? 0 : 最短的路径； index = 1 ： 次短的路径, �?此类推。index <= this.N
     * @return
     */
    public List<int[]> getPaths(int index)
    {
        assert (index <= N && index >= 0);

        Stack<PathNode> stack = new Stack<PathNode>();
        int curNode = vertexCount - 1, curIndex = index;
        QueueElement element;
        PathNode node;
        int[] aPath;
        List<int[]> result = new ArrayList<int[]>();

        element = fromArray[curNode - 1][curIndex].GetFirst();
        while (element != null)
        {
            // ---------- 通过压栈得到路径 -----------
            stack.push(new PathNode(curNode, curIndex));
            stack.push(new PathNode(element.from, element.index));
            curNode = element.from;

            while (curNode != 0)
            {
                element = fromArray[element.from - 1][element.index].GetFirst();
//                System.out.println(element.from + " " + element.index);
                stack.push(new PathNode(element.from, element.index));
                curNode = element.from;
            }

            // -------------- 输出路径 --------------
            PathNode[] nArray = new PathNode[stack.size()];
            for (int i = 0; i < stack.size(); ++i)
            {
                nArray[i] = stack.get(stack.size() - i - 1);
            }
            aPath = new int[nArray.length];

            for (int i = 0; i < aPath.length; i++)
                aPath[i] = nArray[i].from;

            result.add(aPath);

            // -------------- 出栈以检查是�?�还有其它路径 --------------
            do
            {
                node = stack.pop();
                curNode = node.from;
                curIndex = node.index;

            } while (curNode < 1 || (stack.size() != 0 && !fromArray[curNode - 1][curIndex].CanGetNext()));

            element = fromArray[curNode - 1][curIndex].GetNext();
        }

        return result;
    }

    /**
     * 获�?�唯一一�?�最短路径，当然最短路径�?�能�?�?�一�?�
     * @return
     */
    public Integer[] getBestPath()
    {
        assert (vertexCount > 2);

        Stack<Integer> stack = new Stack<Integer>();
        int curNode = vertexCount - 1, curIndex = 0;
        QueueElement element;

        element = fromArray[curNode - 1][curIndex].GetFirst();

        stack.push(curNode);
        stack.push(element.from);
        curNode = element.from;

        while (curNode != 0)
        {
            element = fromArray[element.from - 1][element.index].GetFirst();
            stack.push(element.from);
            curNode = element.from;
        }

        return (Integer[]) stack.toArray();
    }


    /**
     * 从短到长获�?�至多 n �?�路径
     * @param n
     * @return
     */
    public List<int[]> getNPaths(int n)
    {
        List<int[]> result = new ArrayList<int[]>();

        n = Math.min(Predefine.MAX_SEGMENT_NUM, n);
        for (int i = 0; i < N && result.size() < n; ++i)
        {
            List<int[]> pathList = getPaths(i);
            for (int[] path : pathList)
            {
                if (result.size() == n) break;
                result.add(path);
            }
        }

        return result;
    }

    /**
     * 获�?��?10�?�最短路径
     * @return
     */
    public List<int[]> getNPaths()
    {
        return getNPaths(Predefine.MAX_SEGMENT_NUM);
    }
}
