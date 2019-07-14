/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/25 20:53</create-date>
 *
 * <copyright file="MaxEntDependencyModelMaker.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.dependency.model;

import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLLoader;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.corpus.dictionary.DictionaryMaker;
import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.*;
import java.util.*;

/**
 * 最大熵模型构建工具，训练暂时�?使用自己的代�?，借用opennlp训练。本maker�?�生�?训练文件
 *
 * @author hankcs
 */
public class MaxEntDependencyModelMaker
{
    public static boolean makeModel(String corpusLoadPath, String modelSavePath) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(modelSavePath)));
        LinkedList<CoNLLSentence> sentenceList = CoNLLLoader.loadSentenceList(corpusLoadPath);
        int id = 1;
        for (CoNLLSentence sentence : sentenceList)
        {
            System.out.printf("%d / %d...", id++, sentenceList.size());
            String[][] edgeArray = sentence.getEdgeArray();
            CoNLLWord[] word = sentence.getWordArrayWithRoot();
            for (int i = 0; i < word.length; ++i)
            {
                for (int j = 0; j < word.length; ++j)
                {
                    if (i == j) continue;
                    // 这就是一个边的实例，从i出�?�，到j，当然它�?�能存在也�?�能�?存在，�?存在�?�null照样是一个实例
                    List<String> contextList = new LinkedList<String>();
                    // 先生�?i和j的原�?特�?
                    contextList.addAll(generateSingleWordContext(word, i, "i"));
                    contextList.addAll(generateSingleWordContext(word, j, "j"));
                    // 然�?�生�?二元组的特�?
                    contextList.addAll(generateUniContext(word, i, j));
                    // 将特�?字符串化
                    for (String f : contextList)
                    {
                        bw.write(f);
                        bw.write(' ');
                    }
                    // 事件�??称为�?存关系
                    bw.write("" + edgeArray[i][j]);
                    bw.newLine();
                }
            }
            System.out.println("done.");
        }
        bw.close();
        return true;
    }

    public static Collection<String> generateSingleWordContext(CoNLLWord[] word, int index, String mark)
    {
        Collection<String> context = new LinkedList<String>();
        for (int i = index - 2; i < index + 2 + 1; ++i)
        {
            CoNLLWord w = i >= 0 && i < word.length ? word[i] : CoNLLWord.NULL;
            context.add(w.NAME + mark + (i - index));      // 在尾巴上�?�个标记，�?然特�?冲�?了
            context.add(w.POSTAG + mark + (i - index));
        }

        return context;
    }

    public static Collection<String> generateUniContext(CoNLLWord[] word, int i, int j)
    {
        Collection<String> context = new LinkedList<String>();
        context.add(word[i].NAME + '→' + word[j].NAME);
        context.add(word[i].POSTAG + '→' + word[j].POSTAG);
        context.add(word[i].NAME + '→' + word[j].NAME + (i - j));
        context.add(word[i].POSTAG + '→' + word[j].POSTAG + (i - j));
        CoNLLWord wordBeforeI = i - 1 >= 0 ? word[i - 1] : CoNLLWord.NULL;
        CoNLLWord wordBeforeJ = j - 1 >= 0 ? word[j - 1] : CoNLLWord.NULL;
        context.add(wordBeforeI.NAME + '@' + word[i].NAME + '→' + word[j].NAME);
        context.add(word[i].NAME + '→' + wordBeforeJ.NAME + '@' + word[j].NAME);
        context.add(wordBeforeI.POSTAG + '@' + word[i].POSTAG + '→' + word[j].POSTAG);
        context.add(word[i].POSTAG + '→' + wordBeforeJ.POSTAG + '@' + word[j].POSTAG);
        return context;
    }
}
