/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>2015/7/29 16:35</create-date>
 *
 * <copyright file="DumpReader.java" company="�?农场">
 * Copyright (c) 2008-2015, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.io;

import java.io.File;
import java.io.FileFilter;

/**
 * 文本读�?�工具
 * @author hankcs
 */
public class EasyReader
{
    /**
     * 根目录
     */
    String root;
    /**
     * 是�?�输出进度
     */
    boolean verbose = true;

    /**
     * 构造
     * @param root 根目录
     */
    public EasyReader(String root)
    {
        this.root = root;
    }

    /**
     * 构造
     * @param root 根目录
     * @param verbose 是�?�输出进度
     */
    public EasyReader(String root, boolean verbose)
    {
        this.root = root;
        this.verbose = verbose;
    }

    /**
     * 读�?�
     * @param handler 处�?�逻辑
     * @param size 读�?�多少个文件
     * @throws Exception
     */
    public void read(LineHandler handler, int size) throws Exception
    {
        File rootFile = new File(root);
        File[] files;
        if (rootFile.isDirectory())
        {
            files = rootFile.listFiles(new FileFilter()
            {
                @Override
                public boolean accept(File pathname)
                {
                    return pathname.isFile() && !pathname.getName().endsWith(".bin");
                }
            });
            if (files == null)
            {
                if (rootFile.isFile())
                    files = new File[]{rootFile};
                else return;
            }
        }
        else
        {
            files = new File[]{rootFile};
        }

        int n = 0;
        int totalAddress = 0;
        long start = System.currentTimeMillis();
        for (File file : files)
        {
            if (size-- == 0) break;
            if (file.isDirectory()) continue;
            if (verbose) System.out.printf("正在处�?�%s, %d / %d\n", file.getName(), ++n, files.length);
            IOUtil.LineIterator lineIterator = new IOUtil.LineIterator(file.getAbsolutePath());
            while (lineIterator.hasNext())
            {
                ++totalAddress;
                String line = lineIterator.next();
                if (line.length() == 0) continue;
                handler.handle(line);
            }
        }
        handler.done();
        if (verbose) System.out.printf("处�?�了 %.2f 万行，花费了 %.2f min\n", totalAddress / 10000.0, (System.currentTimeMillis() - start) / 1000.0 / 60.0);
    }

    /**
     * 读�?�
     * @param handler 处�?�逻辑
     * @throws Exception
     */
    public void read(LineHandler handler) throws Exception
    {
        read(handler, Integer.MAX_VALUE);
    }
}
