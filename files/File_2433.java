package com.hankcs.hanlp.algorithm.ahocorasick.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 线段树上�?�的节点，实际上是一些区间的集�?�，并且按中点维护了两个节点
 */
public class IntervalNode
{
    /**
     * 方�?�
     */
    private enum Direction
    {
        LEFT, RIGHT
    }

    /**
     * 区间集�?�的最左端
     */
    private IntervalNode left = null;
    /**
     * 最�?�端
     */
    private IntervalNode right = null;
    /**
     * 中点
     */
    private int point;
    /**
     * 区间集�?�
     */
    private List<Intervalable> intervals = new ArrayList<Intervalable>();

    /**
     * 构造一个节点
     * @param intervals
     */
    public IntervalNode(List<Intervalable> intervals)
    {
        this.point = determineMedian(intervals);

        List<Intervalable> toLeft = new ArrayList<Intervalable>();  // 以中点为界�?�左的区间
        List<Intervalable> toRight = new ArrayList<Intervalable>(); // �?��?�的区间

        for (Intervalable interval : intervals)
        {
            if (interval.getEnd() < this.point)
            {
                toLeft.add(interval);
            }
            else if (interval.getStart() > this.point)
            {
                toRight.add(interval);
            }
            else
            {
                this.intervals.add(interval);
            }
        }

        if (toLeft.size() > 0)
        {
            this.left = new IntervalNode(toLeft);
        }
        if (toRight.size() > 0)
        {
            this.right = new IntervalNode(toRight);
        }
    }

    /**
     * 计算中点
     * @param intervals 区间集�?�
     * @return 中点�??标
     */
    public int determineMedian(List<Intervalable> intervals)
    {
        int start = -1;
        int end = -1;
        for (Intervalable interval : intervals)
        {
            int currentStart = interval.getStart();
            int currentEnd = interval.getEnd();
            if (start == -1 || currentStart < start)
            {
                start = currentStart;
            }
            if (end == -1 || currentEnd > end)
            {
                end = currentEnd;
            }
        }
        return (start + end) / 2;
    }

    /**
     * 寻找与interval有�?�?�的区间
     * @param interval
     * @return
     */
    public List<Intervalable> findOverlaps(Intervalable interval)
    {

        List<Intervalable> overlaps = new ArrayList<Intervalable>();

        if (this.point < interval.getStart())
        {
            // �?�边找找
            addToOverlaps(interval, overlaps, findOverlappingRanges(this.right, interval));
            addToOverlaps(interval, overlaps, checkForOverlapsToTheRight(interval));
        }
        else if (this.point > interval.getEnd())
        {
            // 左边找找
            addToOverlaps(interval, overlaps, findOverlappingRanges(this.left, interval));
            addToOverlaps(interval, overlaps, checkForOverlapsToTheLeft(interval));
        }
        else
        {
            // �?�则在当�?区间
            addToOverlaps(interval, overlaps, this.intervals);
            addToOverlaps(interval, overlaps, findOverlappingRanges(this.left, interval));
            addToOverlaps(interval, overlaps, findOverlappingRanges(this.right, interval));
        }

        return overlaps;
    }

    /**
     * 添加到�?�?�区间列表中
     * @param interval 跟此区间�?�?�
     * @param overlaps �?�?�区间列表
     * @param newOverlaps 希望将这些区间加入
     */
    protected void addToOverlaps(Intervalable interval, List<Intervalable> overlaps, List<Intervalable> newOverlaps)
    {
        for (Intervalable currentInterval : newOverlaps)
        {
            if (!currentInterval.equals(interval))
            {
                overlaps.add(currentInterval);
            }
        }
    }

    /**
     * 往左边寻找�?�?�
     * @param interval
     * @return
     */
    protected List<Intervalable> checkForOverlapsToTheLeft(Intervalable interval)
    {
        return checkForOverlaps(interval, Direction.LEFT);
    }

    /**
     * 往�?�边寻找�?�?�
     * @param interval
     * @return
     */
    protected List<Intervalable> checkForOverlapsToTheRight(Intervalable interval)
    {
        return checkForOverlaps(interval, Direction.RIGHT);
    }

    /**
     * 寻找�?�?�
     * @param interval 一个区间，与该区间�?�?�
     * @param direction 方�?�，表明�?�?�区间在interval的左边还是�?�边
     * @return
     */
    protected List<Intervalable> checkForOverlaps(Intervalable interval, Direction direction)
    {

        List<Intervalable> overlaps = new ArrayList<Intervalable>();
        for (Intervalable currentInterval : this.intervals)
        {
            switch (direction)
            {
                case LEFT:
                    if (currentInterval.getStart() <= interval.getEnd())
                    {
                        overlaps.add(currentInterval);
                    }
                    break;
                case RIGHT:
                    if (currentInterval.getEnd() >= interval.getStart())
                    {
                        overlaps.add(currentInterval);
                    }
                    break;
            }
        }
        return overlaps;
    }

    /**
     * 是对IntervalNode.findOverlaps(Intervalable)的一个包装，防止NPE
     * @see com.hankcs.hanlp.algorithm.ahocorasick.interval.IntervalNode#findOverlaps(Intervalable)
     * @param node
     * @param interval
     * @return
     */
    protected static List<Intervalable> findOverlappingRanges(IntervalNode node, Intervalable interval)
    {
        if (node != null)
        {
            return node.findOverlaps(interval);
        }
        return Collections.emptyList();
    }

}
