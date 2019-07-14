/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/13 13:04</create-date>
 *
 * <copyright file="Synonym.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.synonym;

import java.util.ArrayList;
import java.util.List;

/**
 * å?Œä¹‰è¯?
 * @author hankcs
 */
public class Synonym implements ISynonym
{
    public String realWord;
    public long id;
    public Type type;

    @Deprecated
    public Synonym(String realWord, String idString)
    {
        this.realWord = realWord;
        id = SynonymHelper.convertString2Id(idString);
    }

    @Deprecated
    public Synonym(String realWord, long id)
    {
        this.realWord = realWord;
        this.id = id;
    }

    public Synonym(String realWord, long id, Type type)
    {
        this.realWord = realWord;
        this.id = id;
        this.type = type;
    }

    @Override
    public String getRealWord()
    {
        return realWord;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public String getIdString()
    {
        return SynonymHelper.convertId2StringWithIndex(id);
    }

    /**
     * é€šè¿‡ç±»ä¼¼ Bh06A32= ç•ªèŒ„ è¥¿çº¢æŸ¿ çš„å­—ç¬¦ä¸²æž„é€ ä¸€ç³»åˆ—å?Œä¹‰è¯?
     * @param param
     * @return
     */
    public static List<Synonym> create(String param)
    {
        if (param == null) return null;
        String[] args = param.split(" ");
        return create(args);
    }

    /**
     * @see com.hankcs.hanlp.corpus.synonym.Synonym#create(String)
     * @param args
     * @return
     */
    public static ArrayList<Synonym> create(String[] args)
    {
        ArrayList<Synonym> synonymList = new ArrayList<Synonym>(args.length - 1);

        String idString = args[0];
        Type type;
        switch (idString.charAt(idString.length() - 1))
        {
            case '=':
                type = Type.EQUAL;
                break;
            case '#':
                type = Type.LIKE;
                break;
            default:
                type = Type.SINGLE;
                break;
        }
        long startId = SynonymHelper.convertString2IdWithIndex(idString, 0);    // idä»Žè¿™é‡Œå¼€å§‹
        for (int i = 1; i < args.length; ++i)
        {
            if (type == Type.LIKE)
            {
                synonymList.add(new Synonym(args[i], startId + i, type));             // å¦‚æžœä¸?å?Œåˆ™idé€’å¢ž
            }
            else
            {
                synonymList.add(new Synonym(args[i], startId, type));             // å¦‚æžœç›¸å?Œåˆ™ä¸?å?˜
            }
        }
        return synonymList;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(realWord);
        switch (type)
        {

            case EQUAL:
                sb.append('=');
                break;
            case LIKE:
                sb.append('#');
                break;
            case SINGLE:
                sb.append('@');
                break;
            case UNDEFINED:
                sb.append('?');
                break;
        }
        sb.append(getIdString());
        return sb.toString();
    }

    /**
     * è¯­ä¹‰è·?ç¦»
     * @param other
     * @return
     */
    public long distance(Synonym other)
    {
        return Math.abs(id - other.id);
    }

    public enum Type
    {
        /**
         * å®Œå…¨å?Œä¹‰è¯?ï¼Œå¯¹åº”è¯?å…¸ä¸­çš„=å?·
         */
        EQUAL,
        /**
         * å?Œç±»è¯?ï¼Œå¯¹åº”#
         */
        LIKE,
        /**
         * å°?é—­è¯?ï¼Œæ²¡æœ‰å?Œä¹‰è¯?æˆ–å?Œç±»è¯?
         */
        SINGLE,

        /**
         * æœªå®šä¹‰ï¼Œé€šå¸¸å±žäºŽé?žè¯?å…¸ä¸­çš„è¯?
         */
        UNDEFINED,
    }
}
