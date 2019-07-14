/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/9/9 14:46</create-date>
 *
 * <copyright file="NRDictionaryMaker.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dictionary;

import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;
import com.hankcs.hanlp.corpus.tag.NR;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.utility.Predefine;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * nrè¯?å…¸ï¼ˆè¯?å…¸+ngramè½¬ç§»+è¯?æ€§è½¬ç§»çŸ©é˜µï¼‰åˆ¶ä½œå·¥å…·
 * @author hankcs
 */
public class NRDictionaryMaker extends CommonDictionaryMaker
{

    public NRDictionaryMaker(EasyDictionary dictionary)
    {
        super(dictionary);
    }

    @Override
    protected void addToDictionary(List<List<IWord>> sentenceList)
    {
        if (verbose)
            System.out.println("å¼€å§‹åˆ¶ä½œè¯?å…¸");
        // å°†é?žAçš„è¯?è¯­ä¿?å­˜ä¸‹æ?¥
        for (List<IWord> wordList : sentenceList)
        {
            for (IWord word : wordList)
            {
                if (!word.getLabel().equals(NR.A.toString()))
                {
                    dictionaryMaker.add(word);
                }
            }
        }
        // åˆ¶ä½œNGramè¯?å…¸
        for (List<IWord> wordList : sentenceList)
        {
            IWord pre = null;
            for (IWord word : wordList)
            {
                if (pre != null)
                {
                    nGramDictionaryMaker.addPair(pre, word);
                }
                pre = word;
            }
        }
    }

    @Override
    protected void roleTag(List<List<IWord>> sentenceList)
    {
        if (verbose)
            System.out.println("å¼€å§‹æ ‡æ³¨è§’è‰²");
        int i = 0;
        for (List<IWord> wordList : sentenceList)
        {
            if (verbose)
            {
                System.out.println(++i + " / " + sentenceList.size());
                System.out.println("åŽŸå§‹è¯­æ–™ " + wordList);
            }
            // å…ˆæ ‡æ³¨Aå’ŒK
            IWord pre = new Word("##å§‹##", "begin");
            ListIterator<IWord> listIterator = wordList.listIterator();
            while (listIterator.hasNext())
            {
                IWord word = listIterator.next();
                if (!word.getLabel().equals(Nature.nr.toString()))
                {
                    word.setLabel(NR.A.toString());
                }
                else
                {
                    if (!pre.getLabel().equals(Nature.nr.toString()) && !pre.getValue().equals(Predefine.TAG_BIGIN))
                    {
                        pre.setLabel(NR.K.toString());
                    }
                }
                pre = word;
            }
            if (verbose) System.out.println("æ ‡æ³¨é?žå‰? " + wordList);
            // ç„¶å?Žæ ‡æ³¨LM
            IWord next = new Word("##æœ«##", "end");
            while (listIterator.hasPrevious())
            {
                IWord word = listIterator.previous();
                if (word.getLabel().equals(Nature.nr.toString()))
                {
                    String label = next.getLabel();
                    if (label.equals("A")) next.setLabel("L");
                    else if (label.equals("K")) next.setLabel("M");
                }
                next = word;
            }
            if (verbose) System.out.println("æ ‡æ³¨ä¸­å?Ž " + wordList);
            // æ‹†åˆ†å??å­—
            listIterator = wordList.listIterator();
            while (listIterator.hasNext())
            {
                IWord word = listIterator.next();
                if (word.getLabel().equals(Nature.nr.toString()))
                {
                    switch (word.getValue().length())
                    {
                        case 2:
                            if (word.getValue().startsWith("å¤§")
                                    || word.getValue().startsWith("è€?")
                                    || word.getValue().startsWith("å°?")
                                    )
                            {
                                listIterator.add(new Word(word.getValue().substring(1, 2), NR.B.toString()));
                                word.setValue(word.getValue().substring(0, 1));
                                word.setLabel(NR.F.toString());
                            }
                            else if (word.getValue().endsWith("å“¥")
                                    || word.getValue().endsWith("å…¬")
                                    || word.getValue().endsWith("å§?")
                                    || word.getValue().endsWith("è€?")
                                    || word.getValue().endsWith("æŸ?")
                                    || word.getValue().endsWith("å«‚")
                                    || word.getValue().endsWith("æ°?")
                                    || word.getValue().endsWith("æ€»")
                                    )

                            {
                                listIterator.add(new Word(word.getValue().substring(1, 2), NR.G.toString()));
                                word.setValue(word.getValue().substring(0, 1));
                                word.setLabel(NR.B.toString());
                            }
                            else
                            {
                                listIterator.add(new Word(word.getValue().substring(1, 2), NR.E.toString()));
                                word.setValue(word.getValue().substring(0, 1));
                                word.setLabel(NR.B.toString());
                            }
                            break;
                        case 3:
                            listIterator.add(new Word(word.getValue().substring(1, 2), NR.C.toString()));
                            listIterator.add(new Word(word.getValue().substring(2, 3), NR.D.toString()));
                            word.setValue(word.getValue().substring(0, 1));
                            word.setLabel(NR.B.toString());
                            break;
                        default:
                            word.setLabel(NR.A.toString()); // é?žä¸­å›½äººå??
                    }
                }
            }
            if (verbose) System.out.println("å§“å??æ‹†åˆ† " + wordList);
            // ä¸Šæ–‡æˆ?è¯?
            listIterator = wordList.listIterator();
            pre = new Word("##å§‹##", "begin");
            while (listIterator.hasNext())
            {
                IWord word = listIterator.next();
                if (word.getLabel().equals(NR.B.toString()))
                {
                    String combine = pre.getValue() + word.getValue();
                    if (dictionary.contains(combine))
                    {
                        pre.setValue(combine);
                        pre.setLabel("U");
                        listIterator.remove();
                    }
                }
                pre = word;
            }
            if (verbose) System.out.println("ä¸Šæ–‡æˆ?è¯? " + wordList);
            // å¤´éƒ¨æˆ?è¯?
            next = new Word("##æœ«##", "end");
            while (listIterator.hasPrevious())
            {
                IWord word = listIterator.previous();
                if (word.getLabel().equals(NR.B.toString()))
                {
                    String combine = word.getValue() + next.getValue();
                    if (dictionary.contains(combine))
                    {
                        next.setValue(combine);
                        next.setLabel(next.getLabel().equals(NR.C.toString()) ? NR.X.toString() : NR.Y.toString());
                        listIterator.remove();
                    }
                }
                next = word;
            }
            if (verbose) System.out.println("å¤´éƒ¨æˆ?è¯? " + wordList);
            // å°¾éƒ¨æˆ?è¯?
            pre = new Word("##å§‹##", "begin");
            while (listIterator.hasNext())
            {
                IWord word = listIterator.next();
                if (word.getLabel().equals(NR.D.toString()))
                {
                    String combine = pre.getValue() + word.getValue();
                    if (dictionary.contains(combine))
                    {
                        pre.setValue(combine);
                        pre.setLabel(NR.Z.toString());
                        listIterator.remove();
                    }
                }
                pre = word;
            }
            if (verbose) System.out.println("å°¾éƒ¨æˆ?è¯? " + wordList);
            // ä¸‹æ–‡æˆ?è¯?
            next = new Word("##æœ«##", "end");
            while (listIterator.hasPrevious())
            {
                IWord word = listIterator.previous();
                if (word.getLabel().equals(NR.D.toString()))
                {
                    String combine = word.getValue() + next.getValue();
                    if (dictionary.contains(combine))
                    {
                        next.setValue(combine);
                        next.setLabel(NR.V.toString());
                        listIterator.remove();
                    }
                }
                next = word;
            }
            if (verbose) System.out.println("å¤´éƒ¨æˆ?è¯? " + wordList);
            LinkedList<IWord> wordLinkedList = (LinkedList<IWord>) wordList;
            wordLinkedList.addFirst(new Word(Predefine.TAG_BIGIN, "S"));
            wordLinkedList.addLast(new Word(Predefine.TAG_END, "A"));
            if (verbose) System.out.println("æ·»åŠ é¦–å°¾ " + wordList);
        }
    }
}
