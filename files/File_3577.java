/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/5 17:06</create-date>
 *
 * <copyright file="EditDistanceScorer.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.suggest.scorer.editdistance;

import com.hankcs.hanlp.suggest.scorer.BaseScorer;

/**
 * 编辑�?离打分器
 * @author hankcs
 */
public class EditDistanceScorer extends BaseScorer<CharArray>
{
    @Override
    protected CharArray generateKey(String sentence)
    {
        char[] charArray = sentence.toCharArray();
        if (charArray.length == 0) return null;
        return new CharArray(charArray);
    }
}
