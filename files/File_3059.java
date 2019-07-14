/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/5/10 12:42</create-date>
 *
 * <copyright file="WordDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.other.CharTable;
import com.hankcs.hanlp.utility.LexiconUtility;
import com.hankcs.hanlp.utility.Predefine;
import com.hankcs.hanlp.utility.TextUtility;

import java.io.*;
import java.util.*;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 用户自定义�?典<br>
 *     注�?自定义�?典的动�?增删改�?是线程安全的。
 *
 * @author He Han
 */
public class CustomDictionary
{
    /**
     * 用于储存用户动�?�?�入�?�?�的二分trie树
     */
    public static BinTrie<CoreDictionary.Attribute> trie;
    public static DoubleArrayTrie<CoreDictionary.Attribute> dat = new DoubleArrayTrie<CoreDictionary.Attribute>();

    // 自动加载�?典
    static
    {
        String path[] = HanLP.Config.CustomDictionaryPath;
        long start = System.currentTimeMillis();
        if (!loadMainDictionary(path[0]))
        {
            logger.warning("自定义�?典" + Arrays.toString(path) + "加载失败");
        }
        else
        {
            logger.info("自定义�?典加载�?功:" + dat.size() + "个�?�?�，耗时" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * 加载�?典
     * @param mainPath 缓存文件文件�??
     * @param path 自定义�?典
     * @param isCache 是�?�缓存结果
     */
    public static boolean loadMainDictionary(String mainPath, String path[], DoubleArrayTrie<CoreDictionary.Attribute> dat, boolean isCache)
    {
        logger.info("自定义�?典开始加载:" + mainPath);
        if (loadDat(mainPath, dat)) return true;
        TreeMap<String, CoreDictionary.Attribute> map = new TreeMap<String, CoreDictionary.Attribute>();
        LinkedHashSet<Nature> customNatureCollector = new LinkedHashSet<Nature>();
        try
        {
            //String path[] = HanLP.Config.CustomDictionaryPath;
            for (String p : path)
            {
                Nature defaultNature = Nature.n;
                File file = new File(p);
                String fileName = file.getName();
                int cut = fileName.lastIndexOf(' ');
                if (cut > 0)
                {
                    // 有默认�?性
                    String nature = fileName.substring(cut + 1);
                    p = file.getParent() + File.separator + fileName.substring(0, cut);
                    try
                    {
                        defaultNature = LexiconUtility.convertStringToNature(nature, customNatureCollector);
                    }
                    catch (Exception e)
                    {
                        logger.severe("�?置文件�?" + p + "】写错了�?" + e);
                        continue;
                    }
                }
                logger.info("以默认�?性[" + defaultNature + "]加载自定义�?典" + p + "中……");
                boolean success = load(p, defaultNature, map, customNatureCollector);
                if (!success) logger.warning("失败：" + p);
            }
            if (map.size() == 0)
            {
                logger.warning("没有加载到任何�?�?�");
                map.put(Predefine.TAG_OTHER, null);     // 当作空白�?��?符
            }
            logger.info("正在构建DoubleArrayTrie……");
            dat.build(map);
            if (isCache)
            {
                // 缓存�?dat文件，下次加载会快很多
                logger.info("正在缓存�?典为dat文件……");
                // 缓存值文件
                List<CoreDictionary.Attribute> attributeList = new LinkedList<CoreDictionary.Attribute>();
                for (Map.Entry<String, CoreDictionary.Attribute> entry : map.entrySet())
                {
                    attributeList.add(entry.getValue());
                }
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(mainPath + Predefine.BIN_EXT)));
                // 缓存用户�?性
                if (customNatureCollector.isEmpty()) // 热更新
                {
                    for (int i = Nature.begin.ordinal() + 1; i < Nature.values().length; ++i)
                    {
                        customNatureCollector.add(Nature.values()[i]);
                    }
                }
                IOUtil.writeCustomNature(out, customNatureCollector);
                // 缓存正文
                out.writeInt(attributeList.size());
                for (CoreDictionary.Attribute attribute : attributeList)
                {
                    attribute.save(out);
                }
                dat.save(out);
                out.close();
            }
        }
        catch (FileNotFoundException e)
        {
            logger.severe("自定义�?典" + mainPath + "�?存在�?" + e);
            return false;
        }
        catch (IOException e)
        {
            logger.severe("自定义�?典" + mainPath + "读�?�错误�?" + e);
            return false;
        }
        catch (Exception e)
        {
            logger.warning("自定义�?典" + mainPath + "缓存失败�?\n" + TextUtility.exceptionToString(e));
        }
        return true;
    }

    private static boolean loadMainDictionary(String mainPath)
    {
        return loadMainDictionary(mainPath, HanLP.Config.CustomDictionaryPath, CustomDictionary.dat, true);
    }


    /**
     * 加载用户�?典（追加）
     *
     * @param path          �?典路径
     * @param defaultNature 默认�?性
     * @param customNatureCollector 收集用户�?性
     * @return
     */
    public static boolean load(String path, Nature defaultNature, TreeMap<String, CoreDictionary.Attribute> map, LinkedHashSet<Nature> customNatureCollector)
    {
        try
        {
            String splitter = "\\s";
            if (path.endsWith(".csv"))
            {
                splitter = ",";
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null)
            {
                if (firstLine)
                {
                    line = IOUtil.removeUTF8BOM(line);
                    firstLine = false;
                }
                String[] param = line.split(splitter);
                if (param[0].length() == 0) continue;   // 排除空行
                if (HanLP.Config.Normalization) param[0] = CharTable.convert(param[0]); // 正规化

                int natureCount = (param.length - 1) / 2;
                CoreDictionary.Attribute attribute;
                if (natureCount == 0)
                {
                    attribute = new CoreDictionary.Attribute(defaultNature);
                }
                else
                {
                    attribute = new CoreDictionary.Attribute(natureCount);
                    for (int i = 0; i < natureCount; ++i)
                    {
                        attribute.nature[i] = LexiconUtility.convertStringToNature(param[1 + 2 * i], customNatureCollector);
                        attribute.frequency[i] = Integer.parseInt(param[2 + 2 * i]);
                        attribute.totalFrequency += attribute.frequency[i];
                    }
                }
//                if (updateAttributeIfExist(param[0], attribute, map, rewriteTable)) continue;
                map.put(param[0], attribute);
            }
            br.close();
        }
        catch (Exception e)
        {
            logger.severe("自定义�?典" + path + "读�?�错误�?" + e);
            return false;
        }

        return true;
    }

    /**
     * 如果已�?存在该�?�?�,直接更新该�?�?�的属性
     * @param key �?语
     * @param attribute �?语的属性
     * @param map 加载期间的map
     * @param rewriteTable
     * @return 是�?�更新了
     */
    private static boolean updateAttributeIfExist(String key, CoreDictionary.Attribute attribute, TreeMap<String, CoreDictionary.Attribute> map, TreeMap<Integer, CoreDictionary.Attribute> rewriteTable)
    {
        int wordID = CoreDictionary.getWordID(key);
        CoreDictionary.Attribute attributeExisted;
        if (wordID != -1)
        {
            attributeExisted = CoreDictionary.get(wordID);
            attributeExisted.nature = attribute.nature;
            attributeExisted.frequency = attribute.frequency;
            attributeExisted.totalFrequency = attribute.totalFrequency;
            // 收集该覆写
            rewriteTable.put(wordID, attribute);
            return true;
        }

        attributeExisted = map.get(key);
        if (attributeExisted != null)
        {
            attributeExisted.nature = attribute.nature;
            attributeExisted.frequency = attribute.frequency;
            attributeExisted.totalFrequency = attribute.totalFrequency;
            return true;
        }

        return false;
    }

    /**
     * 往自定义�?典中�?�入一个新�?（�?�覆盖模�?）<br>
     *     动�?增删�?会�?久化到�?典文件
     *
     * @param word                新�? 如“裸婚�?
     * @param natureWithFrequency �?性和其对应的频次，比如“nz 1 v 2�?，null时表示“nz 1�?
     * @return 是�?��?�入�?功（失败的原因�?�能是�?覆盖�?natureWithFrequency有问题等，�?�者�?�以通过调试模�?了解原因）
     */
    public static boolean add(String word, String natureWithFrequency)
    {
        if (contains(word)) return false;
        return insert(word, natureWithFrequency);
    }

    /**
     * 往自定义�?典中�?�入一个新�?（�?�覆盖模�?）<br>
     *     动�?增删�?会�?久化到�?典文件
     *
     * @param word                新�? 如“裸婚�?
     * @return 是�?��?�入�?功（失败的原因�?�能是�?覆盖等，�?�以通过调试模�?了解原因）
     */
    public static boolean add(String word)
    {
        if (HanLP.Config.Normalization) word = CharTable.convert(word);
        if (contains(word)) return false;
        return insert(word, null);
    }

    /**
     * 往自定义�?典中�?�入一个新�?（覆盖模�?）<br>
     *     动�?增删�?会�?久化到�?典文件
     *
     * @param word                新�? 如“裸婚�?
     * @param natureWithFrequency �?性和其对应的频次，比如“nz 1 v 2�?，null时表示“nz 1�?。
     * @return 是�?��?�入�?功（失败的原因�?�能是natureWithFrequency问题，�?�以通过调试模�?了解原因）
     */
    public static boolean insert(String word, String natureWithFrequency)
    {
        if (word == null) return false;
        if (HanLP.Config.Normalization) word = CharTable.convert(word);
        CoreDictionary.Attribute att = natureWithFrequency == null ? new CoreDictionary.Attribute(Nature.nz, 1) : CoreDictionary.Attribute.create(natureWithFrequency);
        if (att == null) return false;
        if (dat.set(word, att)) return true;
        if (trie == null) trie = new BinTrie<CoreDictionary.Attribute>();
        trie.put(word, att);
        return true;
    }

    /**
     * 以覆盖模�?增加新�?<br>
     *     动�?增删�?会�?久化到�?典文件
     *
     * @param word
     * @return
     */
    public static boolean insert(String word)
    {
        return insert(word, null);
    }

    public static boolean loadDat(String path, DoubleArrayTrie<CoreDictionary.Attribute> dat)
    {
        return loadDat(path, HanLP.Config.CustomDictionaryPath, dat);
    }

    /**
     * 从�?盘加载�?�数组
     *
     * @param path 主�?典路径
     * @param customDicPath 用户�?典路径
     * @return
     */
    public static boolean loadDat(String path, String customDicPath[], DoubleArrayTrie<CoreDictionary.Attribute> dat)
    {
        try
        {
            if (isDicNeedUpdate(path, customDicPath))
            {
                return false;
            }
            ByteArray byteArray = ByteArray.createByteArray(path + Predefine.BIN_EXT);
            if (byteArray == null) return false;
            int size = byteArray.nextInt();
            if (size < 0)   // 一�?兼容措施,当size�?于零表示文件头部储存了-size个用户�?性
            {
                while (++size <= 0)
                {
                    Nature.create(byteArray.nextString());
                }
                size = byteArray.nextInt();
            }
            CoreDictionary.Attribute[] attributes = new CoreDictionary.Attribute[size];
            final Nature[] natureIndexArray = Nature.values();
            for (int i = 0; i < size; ++i)
            {
                // 第一个是全部频次，第二个是�?性个数
                int currentTotalFrequency = byteArray.nextInt();
                int length = byteArray.nextInt();
                attributes[i] = new CoreDictionary.Attribute(length);
                attributes[i].totalFrequency = currentTotalFrequency;
                for (int j = 0; j < length; ++j)
                {
                    attributes[i].nature[j] = natureIndexArray[byteArray.nextInt()];
                    attributes[i].frequency[j] = byteArray.nextInt();
                }
            }
            if (!dat.load(byteArray, attributes)) return false;
        }
        catch (Exception e)
        {
            logger.warning("读�?�失败，问题�?�生在" + TextUtility.exceptionToString(e));
            return false;
        }
        return true;
    }

    /**
     * 获�?�本地�?典更新状�?
     * @return true 表示本地�?典比缓存文件新，需�?删除缓存
     */
    private static boolean isDicNeedUpdate(String mainPath, String path[])
    {
        if (HanLP.Config.IOAdapter != null &&
            !HanLP.Config.IOAdapter.getClass().getName().contains("com.hankcs.hanlp.corpus.io.FileIOAdapter"))
        {
            return false;
        }
        String binPath = mainPath + Predefine.BIN_EXT;
        File binFile = new File(binPath);
        if (!binFile.exists())
        {
            return true;
        }
        long lastModified = binFile.lastModified();
        //String path[] = HanLP.Config.CustomDictionaryPath;
        for (String p : path)
        {
            File f = new File(p);
            String fileName = f.getName();
            int cut = fileName.lastIndexOf(' ');
            if (cut > 0)
            {
                p = f.getParent() + File.separator + fileName.substring(0, cut);
            }
            f = new File(p);
            if (f.exists() && f.lastModified() > lastModified)
            {
                IOUtil.deleteFile(binPath); // 删掉缓存
                logger.info("已清除自定义�?典缓存文件�?");
                return true;
            }
        }
        return false;
    }

    /**
     * 查�?��?
     *
     * @param key
     * @return
     */
    public static CoreDictionary.Attribute get(String key)
    {
        if (HanLP.Config.Normalization) key = CharTable.convert(key);
        CoreDictionary.Attribute attribute = dat.get(key);
        if (attribute != null) return attribute;
        if (trie == null) return null;
        return trie.get(key);
    }

    /**
     * 删除�?��?<br>
     *     动�?增删�?会�?久化到�?典文件
     *
     * @param key
     */
    public static void remove(String key)
    {
        if (HanLP.Config.Normalization) key = CharTable.convert(key);
        if (trie == null) return;
        trie.remove(key);
    }

    /**
     * �?缀查询
     *
     * @param key
     * @return
     */
    public static LinkedList<Map.Entry<String, CoreDictionary.Attribute>> commonPrefixSearch(String key)
    {
        return trie.commonPrefixSearchWithValue(key);
    }

    /**
     * �?缀查询
     *
     * @param chars
     * @param begin
     * @return
     */
    public static LinkedList<Map.Entry<String, CoreDictionary.Attribute>> commonPrefixSearch(char[] chars, int begin)
    {
        return trie.commonPrefixSearchWithValue(chars, begin);
    }

    public static BaseSearcher getSearcher(String text)
    {
        return new Searcher(text);
    }

    @Override
    public String toString()
    {
        return "CustomDictionary{" +
                "trie=" + trie +
                '}';
    }

    /**
     * �?典中是�?��?�有�?语
     * @param key �?语
     * @return 是�?�包�?�
     */
    public static boolean contains(String key)
    {
        if (dat.exactMatchSearch(key) >= 0) return true;
        return trie != null && trie.containsKey(key);
    }

    /**
     * 获�?�一个BinTrie的查询工具
     * @param charArray 文本
     * @return 查询者
     */
    public static BaseSearcher getSearcher(char[] charArray)
    {
        return new Searcher(charArray);
    }

    static class Searcher extends BaseSearcher<CoreDictionary.Attribute>
    {
        /**
         * 分�?从何处开始，这是一个状�?
         */
        int begin;

        private LinkedList<Map.Entry<String, CoreDictionary.Attribute>> entryList;

        protected Searcher(char[] c)
        {
            super(c);
            entryList = new LinkedList<Map.Entry<String, CoreDictionary.Attribute>>();
        }

        protected Searcher(String text)
        {
            super(text);
            entryList = new LinkedList<Map.Entry<String, CoreDictionary.Attribute>>();
        }

        @Override
        public Map.Entry<String, CoreDictionary.Attribute> next()
        {
            // �?�?首次调用找到一个�?语
            while (entryList.size() == 0 && begin < c.length)
            {
                entryList = trie.commonPrefixSearchWithValue(c, begin);
                ++begin;
            }
            // 之�?�调用仅在缓存用完的时候调用一次
            if (entryList.size() == 0 && begin < c.length)
            {
                entryList = trie.commonPrefixSearchWithValue(c, begin);
                ++begin;
            }
            if (entryList.size() == 0)
            {
                return null;
            }
            Map.Entry<String, CoreDictionary.Attribute> result = entryList.getFirst();
            entryList.removeFirst();
            offset = begin - 1;
            return result;
        }
    }

    /**
     * 获�?��?典对应的trie树
     *
     * @return
     * @deprecated 谨慎�?作，有�?�能废弃此接�?�
     */
    public static BinTrie<CoreDictionary.Attribute> getTrie()
    {
        return trie;
    }

    /**
     * 解�?一段文本（目�?采用了BinTrie+DAT的混�?�储存形�?，此方法�?�以统一两个数�?�结构）
     * @param text         文本
     * @param processor    处�?�器
     */
    public static void parseText(char[] text, AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor)
    {
        if (trie != null)
        {
            trie.parseText(text, processor);
        }
        DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher = dat.getSearcher(text, 0);
        while (searcher.next())
        {
            processor.hit(searcher.begin, searcher.begin + searcher.length, searcher.value);
        }
    }

    /**
     * 解�?一段文本（目�?采用了BinTrie+DAT的混�?�储存形�?，此方法�?�以统一两个数�?�结构）
     * @param text         文本
     * @param processor    处�?�器
     */
    public static void parseText(String text, AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor)
    {
        if (trie != null)
        {
            BaseSearcher searcher = CustomDictionary.getSearcher(text);
            int offset;
            Map.Entry<String, CoreDictionary.Attribute> entry;
            while ((entry = searcher.next()) != null)
            {
                offset = searcher.getOffset();
                processor.hit(offset, offset + entry.getKey().length(), entry.getValue());
            }
        }
        DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher = dat.getSearcher(text, 0);
        while (searcher.next())
        {
            processor.hit(searcher.begin, searcher.begin + searcher.length, searcher.value);
        }
    }

    /**
     * 最长匹�?
     *
     * @param text      文本
     * @param processor 处�?�器
     */
    public static void parseLongestText(String text, AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor)
    {
        if (trie != null)
        {
            final int[] lengthArray = new int[text.length()];
            final CoreDictionary.Attribute[] attributeArray = new CoreDictionary.Attribute[text.length()];
            char[] charArray = text.toCharArray();
            DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher = dat.getSearcher(charArray, 0);
            while (searcher.next())
            {
                lengthArray[searcher.begin] = searcher.length;
                attributeArray[searcher.begin] = searcher.value;
            }
            trie.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
            {
                @Override
                public void hit(int begin, int end, CoreDictionary.Attribute value)
                {
                    int length = end - begin;
                    if (length > lengthArray[begin])
                    {
                        lengthArray[begin] = length;
                        attributeArray[begin] = value;
                    }
                }
            });
            for (int i = 0; i < charArray.length;)
            {
                if (lengthArray[i] == 0)
                {
                    ++i;
                }
                else
                {
                    processor.hit(i, i + lengthArray[i], attributeArray[i]);
                    i += lengthArray[i];
                }
            }
        }
        else
            dat.parseLongestText(text, processor);
    }

    /**
     * 热更新（�?新加载）<br>
     * 集群环境（或其他IOAdapter）需�?自行删除缓存文件（路径 = HanLP.Config.CustomDictionaryPath[0] + Predefine.BIN_EXT）
     * @return 是�?�加载�?功
     */
    public static boolean reload()
    {
        String path[] = HanLP.Config.CustomDictionaryPath;
        if (path == null || path.length == 0) return false;
        IOUtil.deleteFile(path[0] + Predefine.BIN_EXT); // 删掉缓存
        return loadMainDictionary(path[0]);
    }
}
