/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/05/2014/5/17 14:58</create-date>
 *
 * <copyright file="AtomNode.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.seg.NShort.Path;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.other.CharType;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.utility.Predefine;

/**
 * 原�?分�?节点
 * @author hankcs
 */
public class AtomNode
{
    public String sWord;
    public int nPOS;

    public AtomNode(String sWord, int nPOS)
    {
        this.sWord = sWord;
        this.nPOS = nPOS;
    }

    public AtomNode(char c, int nPOS)
    {
        this.sWord = String.valueOf(c);
        this.nPOS = nPOS;
    }

    /**
     * 原�?的�?性
     * @return
     */
    public Nature getNature()
    {
        Nature nature = Nature.nz;
        switch (nPOS)
        {
            case CharType.CT_CHINESE:
                break;
            case CharType.CT_NUM:
            case CharType.CT_INDEX:
            case CharType.CT_CNUM:
                nature = Nature.m;
                sWord = Predefine.TAG_NUMBER;
                break;
            case CharType.CT_DELIMITER:
                nature = Nature.w;
                break;
            case CharType.CT_LETTER:
                nature = Nature.nx;
                sWord = Predefine.TAG_CLUSTER;
                break;
            case CharType.CT_SINGLE://12021-2129-3121
                if (Predefine.PATTERN_FLOAT_NUMBER.matcher(sWord).matches())//匹�?浮点数
                {
                    nature = Nature.m;
                    sWord = Predefine.TAG_NUMBER;
                } else
                {
                    nature = Nature.nx;
                    sWord = Predefine.TAG_CLUSTER;
                }
                break;
            default:
                break;
        }
        return nature;
    }

    @Override
    public String toString()
    {
        return "AtomNode{" +
                "word='" + sWord + '\'' +
                ", nature=" + nPOS +
                '}';
    }

    public static Vertex convert(String word, int type)
    {
        String name = word;
        Nature nature = Nature.n;
        int dValue = 1;
        switch (type)
        {
            case CharType.CT_CHINESE:
                break;
            case CharType.CT_NUM:
            case CharType.CT_INDEX:
            case CharType.CT_CNUM:
                nature = Nature.m;
                word = Predefine.TAG_NUMBER;
                break;
            case CharType.CT_DELIMITER:
                nature = Nature.w;
                break;
            case CharType.CT_LETTER:
                nature = Nature.nx;
                word = Predefine.TAG_CLUSTER;
                break;
            case CharType.CT_SINGLE://12021-2129-3121
//                if (Pattern.compile("^(-?\\d+)(\\.\\d+)?$").matcher(word).matches())//匹�?浮点数
//                {
//                    nature = Nature.m;
//                    word = Predefine.TAG_NUMBER;
//                } else
//                {
                    nature = Nature.nx;
                    word = Predefine.TAG_CLUSTER;
//                }
                break;
            default:
                break;
        }

        return new Vertex(word, name, new CoreDictionary.Attribute(nature, dValue));
    }
}
