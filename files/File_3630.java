/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-11-10 10:36 AM</create-date>
 *
 * <copyright file="PipeLexicalAnalyzer.java">
 * Copyright (c) 2018, Han He. All Rights Reserved, http://www.hankcs.com/
 * See LICENSE file in the project root for full license information.
 * </copyright>
 */
package com.hankcs.hanlp.tokenizer.pipe;

import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.tokenizer.lexical.LexicalAnalyzer;

import java.util.List;
import java.util.ListIterator;

/**
 * è¯?æ³•åˆ†æž?å™¨ç®¡é?“ã€‚çº¦å®šå°†IWordçš„labelè®¾ä¸ºé?žnullè¡¨ç¤ºæœ¬çº§ç®¡é?“å·²ç»?å¤„ç?†
 *
 * @author hankcs
 */
public class LexicalAnalyzerPipe implements Pipe<List<IWord>, List<IWord>>
{
    /**
     * ä»£ç?†çš„è¯?æ³•åˆ†æž?å™¨
     */
    protected LexicalAnalyzer analyzer;

    public LexicalAnalyzerPipe(LexicalAnalyzer analyzer)
    {
        this.analyzer = analyzer;
    }

    @Override
    public List<IWord> flow(List<IWord> input)
    {
        ListIterator<IWord> listIterator = input.listIterator();
        while (listIterator.hasNext())
        {
            IWord wordOrSentence = listIterator.next();
            if (wordOrSentence.getLabel() != null)
                continue; // è¿™æ˜¯åˆ«çš„ç®¡é?“å·²ç»?å¤„ç?†è¿‡çš„å?•è¯?ï¼Œè·³è¿‡
            listIterator.remove(); // å?¦åˆ™æ˜¯å?¥å­?
            String sentence = wordOrSentence.getValue();
            for (IWord word : analyzer.analyze(sentence))
            {
                listIterator.add(word);
            }
        }
        return input;
    }
}
