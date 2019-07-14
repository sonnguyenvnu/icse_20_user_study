/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>2015/10/31 20:42</create-date>
 *
 * <copyright file="ActionFactory.java" company="��ũ��">
 * Copyright (c) 2008-2015, ��ũ��. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dependency.nnparser.action;

/**
 * @author hankcs
 */
public class ActionFactory implements ActionType
{
    /**
     * �?建立�?存关系，�?�转移�?�法分�?的焦点，�?�新的左焦点�?是原�?�的�?�焦点�?，�?此类推。
     * @return
     */
    public static Action make_shift()
    {
        return new Action(kShift, 0);
    }

    /**
     * 建立�?�焦点�?�?存于左焦点�?的�?存关系
     * @param rel �?存关系
     * @return
     */
    public static Action make_left_arc(final int rel)
    {
        return new Action(kLeftArc, rel);
    }

    /**
     * 建立左焦点�?�?存于�?�焦点�?的�?存关系
     * @param rel �?存关系
     * @return
     */
    public static Action make_right_arc(final int rel)
    {
        return new Action(kRightArc, rel);
    }
}
