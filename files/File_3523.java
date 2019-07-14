/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/05/2014/5/21 21:36</create-date>
 *
 * <copyright file="CQueue.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.seg.NShort.Path;

/**
 * 一个维护了上次访问�?置的优先级队列（最�?堆）
 *
 * @author hankcs
 */
public class CQueue
{
    private QueueElement pHead = null;
    private QueueElement pLastAccess = null;

    /**
     * 将QueueElement根�?�eWeight由�?到大的顺�?�?�入队列
     * @param newElement
     */
    public void enQueue(QueueElement newElement)
    {
        QueueElement pCur = pHead, pPre = null;

        while (pCur != null && pCur.weight < newElement.weight)
        {
            pPre = pCur;
            pCur = pCur.next;
        }

        newElement.next = pCur;

        if (pPre == null)
            pHead = newElement;
        else
            pPre.next = newElement;
    }

    /**
     * 从队列中�?�出�?�?�的一个元素
     * @return
     */
    public QueueElement deQueue()
    {
        if (pHead == null)
            return null;

        QueueElement pRet = pHead;
        pHead = pHead.next;

        return pRet;
    }

    /**
     * 读�?�第一个元素，但�?执行DeQueue�?作
     * @return
     */
    public QueueElement GetFirst()
    {
        pLastAccess = pHead;
        return pLastAccess;
    }

    /**
     * 读�?�上次读�?��?�的下一个元素，�?执行DeQueue�?作
     * @return
     */
    public QueueElement GetNext()
    {
        if (pLastAccess != null)
            pLastAccess = pLastAccess.next;

        return pLastAccess;
    }

    /**
     * 是�?��?然有下一个元素�?�供读�?�
     * @return
     */
    public boolean CanGetNext()
    {
        return (pLastAccess.next != null);
    }

    /**
     * 清除所有元素
     */
    public void clear()
    {
        pHead = null;
        pLastAccess = null;
    }
}
