/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-04-04 ä¸‹å?ˆ2:40</create-date>
 *
 * <copyright file="Edge.java">
 * Copyright (c) 2018, Han He. All Rights Reserved, http://www.hankcs.com/
 * This source is subject to Han He. Please contact Han He to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dependency.perceptron.accessories;

/**
 * ä¾?å­˜å?¥æ³•æ ‘ä¸Šçš„ä¸€æ?¡è¾¹
 * @author hankcs
 */
public class Edge
{
    /**
     * head
     */
    public int headIndex;
    /**
     * label
     */
    public int relationId;

    public Edge(int headIndex, int relationId)
    {
        this.headIndex = headIndex;
        this.relationId = relationId;
    }

    @Override
    public Edge clone()
    {
        return new Edge(headIndex, relationId);
    }
}
