/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/8 0:52</create-date>
 *
 * <copyright file="MutualInformationPhraseExactor.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.mining.phrase;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.occurrence.Occurrence;
import com.hankcs.hanlp.corpus.occurrence.PairFrequency;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;

import java.util.LinkedList;
import java.util.List;

import static com.hankcs.hanlp.corpus.tag.Nature.nx;
import static com.hankcs.hanlp.corpus.tag.Nature.t;

/**
 * 利用互信�?�和左�?�熵的短语�??�?�器
 * @author hankcs
 */
public class MutualInformationEntropyPhraseExtractor implements IPhraseExtractor
{
    @Override
    public List<String> extractPhrase(String text, int size)
    {
        List<String> phraseList = new LinkedList<String>();
        Occurrence occurrence = new Occurrence();
        Filter[] filterChain = new Filter[]
                {
                        CoreStopWordDictionary.FILTER,
                        new Filter()
                        {
                            @Override
                            public boolean shouldInclude(Term term)
                            {
                                if (term.nature == t || term.nature == nx)
                                    return false;
                                return true;
                            }
                        }
                };
        for (List<Term> sentence : NotionalTokenizer.seg2sentence(text, filterChain))
        {
            if (HanLP.Config.DEBUG)
            {
                System.out.println(sentence);
            }
            occurrence.addAll(sentence);
        }
        occurrence.compute();
        if (HanLP.Config.DEBUG)
        {
            System.out.println(occurrence);
            for (PairFrequency phrase : occurrence.getPhraseByMi())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→') + "\tmi=" + phrase.mi + " , ") ;
            }
            System.out.println();
            for (PairFrequency phrase : occurrence.getPhraseByLe())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→') + "\tle=" + phrase.le + " , ");
            }
            System.out.println();
            for (PairFrequency phrase : occurrence.getPhraseByRe())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→') + "\tre=" + phrase.re + " , ");
            }
            System.out.println();
            for (PairFrequency phrase : occurrence.getPhraseByScore())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→') + "\tscore=" + phrase.score + " , ");
            }
            System.out.println();
        }

        for (PairFrequency phrase : occurrence.getPhraseByScore())
        {
            if (phraseList.size() == size) break;
            phraseList.add(phrase.first + phrase.second);
        }
        return phraseList;
    }

    /**
     * 一�?��?�??�?�
     * @param text
     * @param size
     * @return
     */
    public static List<String> extract(String text, int size)
    {
        IPhraseExtractor extractor = new MutualInformationEntropyPhraseExtractor();
        return extractor.extractPhrase(text, size);
    }

//    public static void main(String[] args)
//    {
//        MutualInformationEntropyPhraseExtractor extractor = new MutualInformationEntropyPhraseExtractor();
//        String text = "算法工程师\n" +
//                "算法（Algorithm）是一系列解决问题的清晰指令，也就是说，能够对一定规范的输入，在有�?时间内获得所�?求的输出。如果一个算法有缺陷，或�?适�?�于�?个问题，执行这个算法将�?会解决这个问题。�?�?�的算法�?�能用�?�?�的时间�?空间或效率�?�完�?�?�样的任务。一个算法的优劣�?�以用空间�?�?�度与时间�?�?�度�?�衡�?。算法工程师就是利用算法处�?�事物的人。\n" +
//                "\n" +
//                "1�?��?简介\n" +
//                "算法工程师是一个�?�常高端的�?��?；\n" +
//                "专业�?求：计算机�?电�?�?通信�?数学等相关专业；\n" +
//                "学历�?求：本科�?�其以上的学历，大多数是硕士学历�?�其以上；\n" +
//                "语言�?求：英语�?求是熟练，基本上能阅读国外专业书刊；\n" +
//                "必须掌�?�计算机相关知识，熟练使用仿真工具MATLAB等，必须会一门编程语言。\n" +
//                "\n" +
//                "2研究方�?�\n" +
//                "视频算法工程师�?图�?处�?�算法工程师�?音频算法工程师 通信基带算法工程师\n" +
//                "\n" +
//                "3目�?国内外状况\n" +
//                "目�?国内从事算法研究的工程师�?少，但是高级算法工程师�?�很少，是一个�?�常紧缺的专业工程师。算法工程师根�?�研究领域�?�分主�?有音频/视频算法处�?��?图�?技术方�?�的二维信�?�算法处�?�和通信物�?�层�?雷达信�?�处�?��?生物医学信�?�处�?�等领域的一维信�?�算法处�?�。\n" +
//                "在计算机音视频和图形图�?技术等二维信�?�算法处�?�方�?�目�?比较先进的视频处�?�算法：机器视觉�?为此类算法研究的核心；�?�外还有2D转3D算法(2D-to-3D conversion)，去隔行算法(de-interlacing)，�?动估计�?动补�?�算法(Motion estimation/Motion Compensation)，去噪算法(Noise Reduction)，缩放算法(scaling)，�?化处�?�算法(Sharpness)，超分辨率算法(Super Resolution),手势识别(gesture recognition),人脸识别(face recognition)。\n" +
//                "在通信物�?�层等一维信�?�领域目�?常用的算法：无线领域的RRM�?RTT，传�?领域的调制解调�?信�?��?�衡�?信�?�检测�?网络优化�?信�?�分解等。\n" +
//                "�?�外数�?�挖掘�?互�?�网�?�索算法也�?为当今的热门方�?�。\n" +
//                "算法工程师�?�?往人工智能方�?��?�展。";
////        System.out.println(text);
//        List<String> phraseList = extractor.extractPhrase(text, 10);
//        System.out.println(phraseList);
//    }
}
