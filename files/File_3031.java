/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/13 13:13</create-date>
 *
 * <copyright file="CommonSynonymDictionary.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dictionary.common;

import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.dependency.CoNll.PosTagCompiler;
import com.hankcs.hanlp.corpus.synonym.Synonym;
import com.hankcs.hanlp.corpus.synonym.SynonymHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
import com.hankcs.hanlp.corpus.synonym.Synonym.Type;
import com.hankcs.hanlp.corpus.util.Precompiler;
import com.hankcs.hanlp.dictionary.CoreBiGramTableDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.hankcs.hanlp.utility.Predefine;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 一个没有指定资�?�?置的通用�?�义�?�?典
 *
 * @author hankcs
 */
public class CommonSynonymDictionary
{
    DoubleArrayTrie<SynonymItem> trie;

    /**
     * �?典中最大的语义ID�?离
     */
    private long maxSynonymItemIdDistance;

    private CommonSynonymDictionary()
    {
    }

    public static CommonSynonymDictionary create(InputStream inputStream)
    {
        CommonSynonymDictionary dictionary = new CommonSynonymDictionary();
        if (dictionary.load(inputStream))
        {
            return dictionary;
        }

        return null;
    }

    public boolean load(InputStream inputStream)
    {
        trie = new DoubleArrayTrie<SynonymItem>();
        TreeMap<String, SynonymItem> treeMap = new TreeMap<String, SynonymItem>();
        String line = null;
        try
        {
            BufferedReader bw = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            ArrayList<Synonym> synonymList = null;
            while ((line = bw.readLine()) != null)
            {
                String[] args = line.split(" ");
                synonymList = Synonym.create(args);
                char type = args[0].charAt(args[0].length() - 1);
                for (Synonym synonym : synonymList)
                {
                    treeMap.put(synonym.realWord, new SynonymItem(synonym, synonymList, type));
                    // 这里�?微�?�个test
                    //assert synonym.getIdString().startsWith(line.split(" ")[0].substring(0, line.split(" ")[0].length() - 1)) : "�?典有问题" + line + synonym.toString();
                }
            }
            bw.close();
            // 获�?�最大语义id
            if (synonymList != null && synonymList.size() > 0)
            {
                maxSynonymItemIdDistance = synonymList.get(synonymList.size() - 1).id - SynonymHelper.convertString2IdWithIndex("Aa01A01", 0) + 1;
            }
            int resultCode = trie.build(treeMap);
            if (resultCode != 0)
            {
                logger.warning("构建" + inputStream + "失败，错误�?" + resultCode);
                return false;
            }
        }
        catch (Exception e)
        {
            logger.warning("读�?�" + inputStream + "失败，�?�能由行" + line + "造�?");
            return false;
        }
        return true;
    }

    public SynonymItem get(String key)
    {
        return trie.get(key);
    }

    /**
     * 获�?�最大id
     * @return 一个长整型的id
     */
    public long getMaxSynonymItemIdDistance()
    {
        return maxSynonymItemIdDistance;
    }

    /**
     * 语义�?离
     *
     * @param a
     * @param b
     * @return
     */
    public long distance(String a, String b)
    {
        SynonymItem itemA = get(a);
        if (itemA == null) return Long.MAX_VALUE / 3;
        SynonymItem itemB = get(b);
        if (itemB == null) return Long.MAX_VALUE / 3;

        return itemA.distance(itemB);
    }

    public String rewriteQuickly(String text)
    {
        assert text != null;
        StringBuilder sbOut = new StringBuilder((int) (text.length() * 1.2));
        String preWord = Predefine.TAG_BIGIN;
        for (int i = 0; i < text.length(); ++i)
        {
            int state = 1;
            state = trie.transition(text.charAt(i), state);
            if (state > 0)
            {
                int start = i;
                int to = i + 1;
                int end = - 1;
                SynonymItem value = null;
                for (; to < text.length(); ++to)
                {
                    state = trie.transition(text.charAt(to), state);
                    if (state < 0) break;
                    SynonymItem output = trie.output(state);
                    if (output != null)
                    {
                        value = output;
                        end = to + 1;
                    }
                }
                if (value != null)
                {
                    Synonym synonym = value.randomSynonym(Type.EQUAL, preWord);
                    if (synonym != null)
                    {
                        sbOut.append(synonym.realWord);
                        preWord = synonym.realWord;
                    }
                    else
                    {
                        preWord = text.substring(start, end);
                        sbOut.append(preWord);
                    }
                    i = end - 1;
                }
                else
                {
                    preWord = String.valueOf(text.charAt(i));
                    sbOut.append(text.charAt(i));
                }
            }
            else
            {
                preWord = String.valueOf(text.charAt(i));
                sbOut.append(text.charAt(i));
            }
        }

        return sbOut.toString();
    }

    public String rewrite(String text)
    {
        List<Term> termList = StandardTokenizer.segment(text.toCharArray());
        StringBuilder sbOut = new StringBuilder((int) (text.length() * 1.2));
        String preWord = Predefine.TAG_BIGIN;
        for (Term term : termList)
        {
            SynonymItem synonymItem = get(term.word);
            Synonym synonym;
            if (synonymItem != null && (synonym = synonymItem.randomSynonym(Type.EQUAL, preWord)) != null)
            {
                sbOut.append(synonym.realWord);
            }
            else sbOut.append(term.word);
            preWord = PosTagCompiler.compile(term.nature.toString(), term.word);
        }
        return sbOut.toString();
    }

    /**
     * �?典中的一个�?�目
     */
    public static class SynonymItem
    {
        /**
         * �?�目的key
         */
        public Synonym entry;
        /**
         * �?�目的value，是key的�?�义�?列表
         */
        public List<Synonym> synonymList;

        /**
         * 这个�?�目的类型，�?�义�?或�?�类�?或�?闭�?
         */
        public Type type;

        public SynonymItem(Synonym entry, List<Synonym> synonymList, Type type)
        {
            this.entry = entry;
            this.synonymList = synonymList;
            this.type = type;
        }

        public SynonymItem(Synonym entry, List<Synonym> synonymList, char type)
        {
            this.entry = entry;
            this.synonymList = synonymList;
            switch (type)
            {
                case '=':
                    this.type = Type.EQUAL;
                    break;
                case '#':
                    this.type = Type.LIKE;
                    break;
                default:
                    this.type = Type.SINGLE;
                    break;
            }
        }

        /**
         * �?机挑一个近义�?
         * @param type 类型
         * @return
         */
        public Synonym randomSynonym(Type type, String preWord)
        {
            ArrayList<Synonym> synonymArrayList = new ArrayList<Synonym>(synonymList);
            ListIterator<Synonym> listIterator = synonymArrayList.listIterator();
            if (type != null) while (listIterator.hasNext())
            {
                Synonym synonym = listIterator.next();
                if (synonym.type != type || (preWord != null && CoreBiGramTableDictionary.getBiFrequency(preWord, synonym.realWord) == 0)) listIterator.remove();
            }
            if (synonymArrayList.size() == 0) return null;
            return synonymArrayList.get((int) (System.currentTimeMillis() % (long)synonymArrayList.size()));
        }

        public Synonym randomSynonym()
        {
            return randomSynonym(null, null);
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder();
            sb.append(entry);
            sb.append(' ');
            sb.append(type);
            sb.append(' ');
            sb.append(synonymList);
            return sb.toString();
        }

        /**
         * 语义�?离
         *
         * @param other
         * @return
         */
        public long distance(SynonymItem other)
        {
            return entry.distance(other.entry);
        }

        /**
         * 创建一个@类型的�?典之外的�?�目
         *
         * @param word
         * @return
         */
        public static SynonymItem createUndefined(String word)
        {
            SynonymItem item = new SynonymItem(new Synonym(word, word.hashCode() * 1000000 + Long.MAX_VALUE / 3), null, Type.UNDEFINED);
            return item;
        }
    }
}
