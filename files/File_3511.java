/*
 * <summary></summary>
 * <author>hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2015/5/7 18:47</create-date>
 *
 * <copyright file="DictionaryBasedSegment.java">
 * Copyright (c) 2003-2015, hankcs. All Right Reserved, http://www.hankcs.com/
 * </copyright>
 */
package com.hankcs.hanlp.seg;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.NShort.Path.AtomNode;

import java.util.List;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 基于�?典的机械分�?器基类
 *
 * @author hankcs
 */
public abstract class DictionaryBasedSegment extends Segment
{
    /**
     * 开�?�数�?和英文识别（与标准�?义上的�?性标注�?�?�，�?�是借用这个�?置方法，�?是真的开�?�了�?性标注。
     * 一般用�?典分�?的用户�?太�?�能是NLP专业人士，对�?性准确率�?求�?高，所以干脆�?为�?典分�?实现�?性标注。）
     *
     * @param enable
     * @return
     */
    public Segment enablePartOfSpeechTagging(boolean enable)
    {
        return super.enablePartOfSpeechTagging(enable);
    }

    /**
     * �?性标注
     *
     * @param charArray   字符数组
     * @param wordNet     �?语长度
     * @param natureArray 输出�?性
     */
    protected void posTag(char[] charArray, int[] wordNet, Nature[] natureArray)
    {
        if (config.speechTagging)
        {
            for (int i = 0; i < natureArray.length; )
            {
                if (natureArray[i] == null)
                {
                    int j = i + 1;
                    for (; j < natureArray.length; ++j)
                    {
                        if (natureArray[j] != null) break;
                    }
                    List<AtomNode> atomNodeList = quickAtomSegment(charArray, i, j);
                    for (AtomNode atomNode : atomNodeList)
                    {
                        if (atomNode.sWord.length() >= wordNet[i])
                        {
                            wordNet[i] = atomNode.sWord.length();
                            natureArray[i] = atomNode.getNature();
                            i += wordNet[i];
                        }
                    }
                    i = j;
                }
                else
                {
                    ++i;
                }
            }
        }
    }

    @Override
    public Segment enableCustomDictionary(boolean enable)
    {
        if (enable)
        {
            logger.warning("为基于�?典的分�?器开�?�用户�?典太浪费了，建议直接将所有�?典的路径传入构造函数，这样速度更快�?内存更�?");
        }
        return super.enableCustomDictionary(enable);
    }
}
