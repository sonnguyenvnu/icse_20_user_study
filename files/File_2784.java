/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/8 17:25</create-date>
 *
 * <copyright file="Word.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.document.sentence.word;

import static com.hankcs.hanlp.utility.Predefine.logger;
/**
 * ä¸€ä¸ªå?•è¯?
 * @author hankcs
 */
public class Word implements IWord
{
    /**
     * å?•è¯?çš„çœŸå®žå€¼ï¼Œæ¯”å¦‚â€œç¨‹åº?â€?
     */
    public String value;
    /**
     * å?•è¯?çš„æ ‡ç­¾ï¼Œæ¯”å¦‚â€œnâ€?
     */
    public String label;

    @Override
    public String toString()
    {
        if (label == null)
            return value;
        return value + '/' + label;
    }

    public Word(String value, String label)
    {
        this.value = value;
        this.label = label;
    }

    /**
     * é€šè¿‡å?‚æ•°æž„é€ ä¸€ä¸ªå?•è¯?
     * @param param æ¯”å¦‚ äººæ°‘ç½‘/nz
     * @return ä¸€ä¸ªå?•è¯?
     */
    public static Word create(String param)
    {
        if (param == null) return null;
        int cutIndex = param.lastIndexOf('/');
        if (cutIndex <= 0 || cutIndex == param.length() - 1)
        {
            logger.warning("ä½¿ç”¨ " + param + "åˆ›å»ºå?•ä¸ªå?•è¯?å¤±è´¥");
            return null;
        }

        return new Word(param.substring(0, cutIndex), param.substring(cutIndex + 1));
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    @Override
    public void setLabel(String label)
    {
        this.label = label;
    }

    @Override
    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public int length()
    {
        return value.length();
    }
}
