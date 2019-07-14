/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/18 17:16</create-date>
 *
 * <copyright file="CommonStringDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.common;


import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.utility.Predefine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 最简�?�的�?典，�?一行�?�有一个�?，没别的
 * @author hankcs
 */
public class CommonStringDictionary
{
    BinTrie<Byte> trie;
    public boolean load(String path)
    {
        trie = new BinTrie<Byte>();
        if (loadDat(path + Predefine.TRIE_EXT)) return true;
        String line = null;
        try
        {
            BufferedReader bw = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path)));
            while ((line = bw.readLine()) != null)
            {
                trie.put(line, null);
            }
            bw.close();
        }
        catch (Exception e)
        {
            logger.warning("加载" + path + "失败，" + e);
        }
        if (!trie.save(path + Predefine.TRIE_EXT)) logger.warning("缓存" + path + Predefine.TRIE_EXT + "失败");
        return true;
    }

    boolean loadDat(String path)
    {
        return trie.load(path);
    }

    public Set<String> keySet()
    {
        Set<String> keySet = new LinkedHashSet<String>();
        for (Map.Entry<String, Byte> entry : trie.entrySet())
        {
            keySet.add(entry.getKey());
        }

        return keySet;
    }
}
