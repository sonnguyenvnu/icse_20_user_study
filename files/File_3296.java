package com.hankcs.hanlp.model.crf.crfpp;

import java.util.List;

/**
 * 边
 *
 * @author zhifac
 */
public class Path
{
    public Node rnode;
    public Node lnode;
    public List<Integer> fvector;
    public double cost;

    public Path()
    {
        clear();
    }

    public void clear()
    {
        rnode = lnode = null;
        fvector = null;
        cost = 0.0;
    }

    /**
     * 计算边的期望
     *
     * @param expected 输出期望
     * @param Z        规范化因�?
     * @param size     标签个数
     */
    public void calcExpectation(double[] expected, double Z, int size)
    {
        double c = Math.exp(lnode.alpha + cost + rnode.beta - Z);
        for (int i = 0; fvector.get(i) != -1; i++)
        {
            int idx = fvector.get(i) + lnode.y * size + rnode.y;
            expected[idx] += c;
        }
    }

    public void add(Node _lnode, Node _rnode)
    {
        lnode = _lnode;
        rnode = _rnode;
        lnode.rpath.add(this);
        rnode.lpath.add(this);
    }
}
