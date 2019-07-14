/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-09-07 PM4:42</create-date>
 *
 * <copyright file="FileIOAdapter.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.io;

import java.io.*;

/**
 * 基于普通文件系统的IO适�?器
 *
 * @author hankcs
 */
public class FileIOAdapter implements IIOAdapter
{
    @Override
    public InputStream open(String path) throws FileNotFoundException
    {
        return new FileInputStream(path);
    }

    @Override
    public OutputStream create(String path) throws FileNotFoundException
    {
        return new FileOutputStream(path);
    }
}
