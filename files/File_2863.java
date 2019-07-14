/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-03-26 PM5:36</create-date>
 *
 * <copyright file="ReflectionHelper.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.util;

import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 修改final static域的�??射工具
 * @author hankcs
 */
public class ReflectionHelper
{
    private static final String MODIFIERS_FIELD = "modifiers";

    private static final ReflectionFactory reflection =
            ReflectionFactory.getReflectionFactory();

    public static void setStaticFinalField(
            Field field, Object value)
            throws NoSuchFieldException, IllegalAccessException
    {
        // 获得 public �?��?
        field.setAccessible(true);
        // 将modifiers域设为�?�final,这样就�?�以修改了
        Field modifiersField =
                Field.class.getDeclaredField(MODIFIERS_FIELD);
        modifiersField.setAccessible(true);
        int modifiers = modifiersField.getInt(field);
        // 去掉 final 标志�?
        modifiers &= ~Modifier.FINAL;
        modifiersField.setInt(field, modifiers);
        FieldAccessor fa = reflection.newFieldAccessor(
                field, false
        );
        fa.set(null, value);
    }
}
