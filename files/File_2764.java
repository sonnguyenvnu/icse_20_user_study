/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/8 19:01</create-date>
 *
 * <copyright file="Document.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.document;

import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.CompoundWord;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;
import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * @author hankcs
 */
public class Document implements Serializable
{
    public List<Sentence> sentenceList;

    public Document(List<Sentence> sentenceList)
    {
        this.sentenceList = sentenceList;
    }

    public static Document create(String param)
    {
        Pattern pattern = Pattern.compile(".+?((ã€‚/w)|(ï¼?/w )|(ï¼Ÿ/w )|\\n|$)");
        Matcher matcher = pattern.matcher(param);
        List<Sentence> sentenceList = new LinkedList<Sentence>();
        while (matcher.find())
        {
            String single = matcher.group();
            Sentence sentence = Sentence.create(single);
            if (sentence == null)
            {
                logger.warning("ä½¿ç”¨" + single + "æž„å»ºå?¥å­?å¤±è´¥");
                return null;
            }
            sentenceList.add(sentence);
        }
        return new Document(sentenceList);
    }

    /**
     * èŽ·å?–å?•è¯?åº?åˆ—
     *
     * @return
     */
    public List<IWord> getWordList()
    {
        List<IWord> wordList = new LinkedList<IWord>();
        for (Sentence sentence : sentenceList)
        {
            wordList.addAll(sentence.wordList);
        }
        return wordList;
    }

    public List<Word> getSimpleWordList()
    {
        List<IWord> wordList = getWordList();
        List<Word> simpleWordList = new LinkedList<Word>();
        for (IWord word : wordList)
        {
            if (word instanceof CompoundWord)
            {
                simpleWordList.addAll(((CompoundWord) word).innerList);
            }
            else
            {
                simpleWordList.add((Word) word);
            }
        }

        return simpleWordList;
    }

    /**
     * èŽ·å?–ç®€å?•çš„å?¥å­?åˆ—è¡¨ï¼Œå…¶ä¸­å¤?å?ˆè¯?ä¼šè¢«æ‹†åˆ†ä¸ºç®€å?•è¯?
     *
     * @return
     */
    public List<List<Word>> getSimpleSentenceList()
    {
        List<List<Word>> simpleList = new LinkedList<List<Word>>();
        for (Sentence sentence : sentenceList)
        {
            List<Word> wordList = new LinkedList<Word>();
            for (IWord word : sentence.wordList)
            {
                if (word instanceof CompoundWord)
                {
                    for (Word inner : ((CompoundWord) word).innerList)
                    {
                        wordList.add(inner);
                    }
                }
                else
                {
                    wordList.add((Word) word);
                }
            }
            simpleList.add(wordList);
        }

        return simpleList;
    }

    /**
     * èŽ·å?–å¤?æ?‚å?¥å­?åˆ—è¡¨ï¼Œå?¥å­?ä¸­çš„æ¯?ä¸ªå?•è¯?æœ‰å?¯èƒ½æ˜¯å¤?å?ˆè¯?ï¼Œæœ‰å?¯èƒ½æ˜¯ç®€å?•è¯?
     *
     * @return
     */
    public List<List<IWord>> getComplexSentenceList()
    {
        List<List<IWord>> complexList = new LinkedList<List<IWord>>();
        for (Sentence sentence : sentenceList)
        {
            complexList.add(sentence.wordList);
        }

        return complexList;
    }

    /**
     * èŽ·å?–ç®€å?•çš„å?¥å­?åˆ—è¡¨
     *
     * @param spilt å¦‚æžœä¸ºçœŸï¼Œå…¶ä¸­å¤?å?ˆè¯?ä¼šè¢«æ‹†åˆ†ä¸ºç®€å?•è¯?
     * @return
     */
    public List<List<Word>> getSimpleSentenceList(boolean spilt)
    {
        List<List<Word>> simpleList = new LinkedList<List<Word>>();
        for (Sentence sentence : sentenceList)
        {
            List<Word> wordList = new LinkedList<Word>();
            for (IWord word : sentence.wordList)
            {
                if (word instanceof CompoundWord)
                {
                    if (spilt)
                    {
                        for (Word inner : ((CompoundWord) word).innerList)
                        {
                            wordList.add(inner);
                        }
                    }
                    else
                    {
                        wordList.add(((CompoundWord) word).toWord());
                    }
                }
                else
                {
                    wordList.add((Word) word);
                }
            }
            simpleList.add(wordList);
        }

        return simpleList;
    }

    /**
     * èŽ·å?–ç®€å?•çš„å?¥å­?åˆ—è¡¨ï¼Œå…¶ä¸­å¤?å?ˆè¯?çš„æ ‡ç­¾å¦‚æžœæ˜¯setä¸­æŒ‡å®šçš„è¯?ä¼šè¢«æ‹†åˆ†ä¸ºç®€å?•è¯?
     *
     * @param labelSet
     * @return
     */
    public List<List<Word>> getSimpleSentenceList(Set<String> labelSet)
    {
        List<List<Word>> simpleList = new LinkedList<List<Word>>();
        for (Sentence sentence : sentenceList)
        {
            List<Word> wordList = new LinkedList<Word>();
            for (IWord word : sentence.wordList)
            {
                if (word instanceof CompoundWord)
                {
                    if (labelSet.contains(word.getLabel()))
                    {
                        for (Word inner : ((CompoundWord) word).innerList)
                        {
                            wordList.add(inner);
                        }
                    }
                    else
                    {
                        wordList.add(((CompoundWord) word).toWord());
                    }
                }
                else
                {
                    wordList.add((Word) word);
                }
            }
            simpleList.add(wordList);
        }

        return simpleList;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentenceList)
        {
            sb.append(sentence);
            sb.append(' ');
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static Document create(File file)
    {
        IOUtil.LineIterator lineIterator = new IOUtil.LineIterator(file.getAbsolutePath());
        List<Sentence> sentenceList = new LinkedList<Sentence>();
        for (String line : lineIterator)
        {
            line = line.trim();
            if (line.isEmpty()) continue;
            Sentence sentence = Sentence.create(line);
            if (sentence == null)
            {
                logger.warning("ä½¿ç”¨ " + line + " åˆ›å»ºå?¥å­?å¤±è´¥");
                return null;
            }
            sentenceList.add(sentence);
        }
        return new Document(sentenceList);
    }
}
