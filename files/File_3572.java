/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/30 10:02</create-date>
 *
 * <copyright file="HiddenMarkovModelSegment.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.seg;

import com.hankcs.hanlp.algorithm.Viterbi;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.*;
import com.hankcs.hanlp.dictionary.other.CharType;
import com.hankcs.hanlp.seg.NShort.Path.*;
import com.hankcs.hanlp.seg.common.Graph;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;
import com.hankcs.hanlp.utility.TextUtility;

import java.util.*;

/**
 * 基于�?语NGram模型的分�?器基类
 *
 * @author hankcs
 */
public abstract class WordBasedSegment extends Segment
{

    public WordBasedSegment()
    {
        super();
    }

    /**
     * 对粗分结果执行一些规则上的�?�并拆分等等，�?�时�?��?新�?网
     *
     * @param linkedArray    粗分结果
     * @param wordNetOptimum �?�并了所有粗分结果的�?网
     */
    protected static void generateWord(List<Vertex> linkedArray, WordNet wordNetOptimum)
    {
        fixResultByRule(linkedArray);

        //--------------------------------------------------------------------
        // 建造新�?网
        wordNetOptimum.addAll(linkedArray);
    }

    /**
     * 通过规则修正一些结果
     *
     * @param linkedArray
     */
    protected static void fixResultByRule(List<Vertex> linkedArray)
    {

        //--------------------------------------------------------------------
        //Merge all seperate continue num into one number
        mergeContinueNumIntoOne(linkedArray);

        //--------------------------------------------------------------------
        //The delimiter "�?�?"
        changeDelimiterPOS(linkedArray);

        //--------------------------------------------------------------------
        //如果�?一个�?是数字，当�?�?以“�?�?或“-�?开始，并且�?止这一个字符，
        //那么将此“�?�?符�?�从当�?�?中分离出�?�。
        //例如 “3 / -4 / 月�?需�?拆分�?“3 / - / 4 / 月�?
        splitMiddleSlashFromDigitalWords(linkedArray);

        //--------------------------------------------------------------------
        //1�?如果当�?�?是数字，下一个�?是“月�?日�?时�?分�?秒�?月份�?中的一个，则�?�并,且当�?�?�?性是时间
        //2�?如果当�?�?是�?�以作为年份的数字，下一个�?是“年�?，则�?�并，�?性为时间，�?�则为数字。
        //3�?如果最�?�一个汉字是"点" ，则认为当�?数字是时间
        //4�?如果当�?串最�?�一个汉字�?是"∶·．�?"和�?�角的'.''/'，那么是数
        //5�?当�?串最�?�一个汉字是"∶·．�?"和�?�角的'.''/'，且长度大于1，那么去掉最�?�一个字符。例如"1."
        checkDateElements(linkedArray);
    }

    static void changeDelimiterPOS(List<Vertex> linkedArray)
    {
        for (Vertex vertex : linkedArray)
        {
            if (vertex.realWord.equals("�?�?") || vertex.realWord.equals("—") || vertex.realWord.equals("-"))
            {
                vertex.confirmNature(Nature.w);
            }
        }
    }

    //====================================================================
    //如果�?一个�?是数字，当�?�?以“�?�?或“-�?开始，并且�?止这一个字符，
    //那么将此“�?�?符�?�从当�?�?中分离出�?�。
    //例如 “3-4 / 月�?需�?拆分�?“3 / - / 4 / 月�?
    //====================================================================
    private static void splitMiddleSlashFromDigitalWords(List<Vertex> linkedArray)
    {
        if (linkedArray.size() < 2)
            return;

        ListIterator<Vertex> listIterator = linkedArray.listIterator();
        Vertex next = listIterator.next();
        Vertex current = next;
        while (listIterator.hasNext())
        {
            next = listIterator.next();
//            System.out.println("current:" + current + " next:" + next);
            Nature currentNature = current.getNature();
            if (currentNature == Nature.nx && (next.hasNature(Nature.q) || next.hasNature(Nature.n)))
            {
                String[] param = current.realWord.split("-", 1);
                if (param.length == 2)
                {
                    if (TextUtility.isAllNum(param[0]) && TextUtility.isAllNum(param[1]))
                    {
                        current = current.copy();
                        current.realWord = param[0];
                        current.confirmNature(Nature.m);
                        listIterator.previous();
                        listIterator.previous();
                        listIterator.set(current);
                        listIterator.next();
                        listIterator.add(Vertex.newPunctuationInstance("-"));
                        listIterator.add(Vertex.newNumberInstance(param[1]));
                    }
                }
            }
            current = next;
        }

//        logger.trace("�?��?�识别�?�：" + Graph.parseResult(linkedArray));
    }

    //====================================================================
    //1�?如果当�?�?是数字，下一个�?是“月�?日�?时�?分�?秒�?月份�?中的一个，则�?�并且当�?�?�?性是时间
    //2�?如果当�?�?是�?�以作为年份的数字，下一个�?是“年�?，则�?�并，�?性为时间，�?�则为数字。
    //3�?如果最�?�一个汉字是"点" ，则认为当�?数字是时间
    //4�?如果当�?串最�?�一个汉字�?是"∶·．�?"和�?�角的'.''/'，那么是数
    //5�?当�?串最�?�一个汉字是"∶·．�?"和�?�角的'.''/'，且长度大于1，那么去掉最�?�一个字符。例如"1."
    //====================================================================
    private static void checkDateElements(List<Vertex> linkedArray)
    {
        if (linkedArray.size() < 2)
            return;
        ListIterator<Vertex> listIterator = linkedArray.listIterator();
        Vertex next = listIterator.next();
        Vertex current = next;
        while (listIterator.hasNext())
        {
            next = listIterator.next();
            if (TextUtility.isAllNum(current.realWord) || TextUtility.isAllChineseNum(current.realWord))
            {
                //===== 1�?如果当�?�?是数字，下一个�?是“月�?日�?时�?分�?秒�?月份�?中的一个，则�?�并且当�?�?�?性是时间
                String nextWord = next.realWord;
                if ((nextWord.length() == 1 && "月日时分秒".contains(nextWord)) || (nextWord.length() == 2 && nextWord.equals("月份")))
                {
                    mergeDate(listIterator, next, current);
                }
                //===== 2�?如果当�?�?是�?�以作为年份的数字，下一个�?是“年�?，则�?�并，�?性为时间，�?�则为数字。
                else if (nextWord.equals("年"))
                {
                    if (TextUtility.isYearTime(current.realWord))
                    {
                        mergeDate(listIterator, next, current);
                    }
                    //===== �?�则当�?�?就是数字了 =====
                    else
                    {
                        current.confirmNature(Nature.m);
                    }
                }
                else
                {
                    //===== 3�?如果最�?�一个汉字是"点" ，则认为当�?数字是时间
                    if (current.realWord.endsWith("点"))
                    {
                        current.confirmNature(Nature.t, true);
                    }
                    else
                    {
                        char[] tmpCharArray = current.realWord.toCharArray();
                        String lastChar = String.valueOf(tmpCharArray[tmpCharArray.length - 1]);
                        //===== 4�?如果当�?串最�?�一个汉字�?是"∶·．�?"和�?�角的'.''/'，那么是数
                        if (!"∶·．�?./".contains(lastChar))
                        {
                            current.confirmNature(Nature.m, true);
                        }
                        //===== 5�?当�?串最�?�一个汉字是"∶·．�?"和�?�角的'.''/'，且长度大于1，那么去掉最�?�一个字符。例如"1."
                        else if (current.realWord.length() > 1)
                        {
                            char last = current.realWord.charAt(current.realWord.length() - 1);
                            current = Vertex.newNumberInstance(current.realWord.substring(0, current.realWord.length() - 1));
                            listIterator.previous();
                            listIterator.previous();
                            listIterator.set(current);
                            listIterator.next();
                            listIterator.add(Vertex.newPunctuationInstance(String.valueOf(last)));
                        }
                    }
                }
            }
            current = next;
        }
//        logger.trace("日期识别�?�：" + Graph.parseResult(linkedArray));
    }

    private static void mergeDate(ListIterator<Vertex> listIterator, Vertex next, Vertex current)
    {
        current = Vertex.newTimeInstance(current.realWord + next.realWord);
        listIterator.previous();
        listIterator.previous();
        listIterator.set(current);
        listIterator.next();
        listIterator.next();
        listIterator.remove();
    }

    /**
     * 将一�?�路径转为最终结果
     *
     * @param vertexList
     * @return
     */
    protected static List<Term> convert(List<Vertex> vertexList)
    {
        return convert(vertexList, false);
    }

    /**
     * 生�?二元�?图
     *
     * @param wordNet
     * @return
     */
    protected static Graph generateBiGraph(WordNet wordNet)
    {
        return wordNet.toGraph();
    }

    /**
     * 原�?分�?
     *
     * @param sSentence
     * @param start
     * @param end
     * @return
     * @deprecated 应该使用字符数组的版本
     */
    private static List<AtomNode> atomSegment(String sSentence, int start, int end)
    {
        if (end < start)
        {
            throw new RuntimeException("start=" + start + " < end=" + end);
        }
        List<AtomNode> atomSegment = new ArrayList<AtomNode>();
        int pCur = 0, nCurType, nNextType;
        StringBuilder sb = new StringBuilder();
        char c;


        //==============================================================================================
        // by zhenyulu:
        //
        // TODO: 使用一系列正则表达�?将�?��?中的完整�?分（百分比�?日期�?电�?邮件�?URL等）预先�??�?�出�?�
        //==============================================================================================

        char[] charArray = sSentence.substring(start, end).toCharArray();
        int[] charTypeArray = new int[charArray.length];

        // 生�?对应�?�个汉字的字符类型数组
        for (int i = 0; i < charArray.length; ++i)
        {
            c = charArray[i];
            charTypeArray[i] = CharType.get(c);

            if (c == '.' && i < (charArray.length - 1) && CharType.get(charArray[i + 1]) == CharType.CT_NUM)
                charTypeArray[i] = CharType.CT_NUM;
            else if (c == '.' && i < (charArray.length - 1) && charArray[i + 1] >= '0' && charArray[i + 1] <= '9')
                charTypeArray[i] = CharType.CT_SINGLE;
            else if (charTypeArray[i] == CharType.CT_LETTER)
                charTypeArray[i] = CharType.CT_SINGLE;
        }

        // 根�?�字符类型数组中的内容完�?原�?切割
        while (pCur < charArray.length)
        {
            nCurType = charTypeArray[pCur];

            if (nCurType == CharType.CT_CHINESE || nCurType == CharType.CT_INDEX ||
                nCurType == CharType.CT_DELIMITER || nCurType == CharType.CT_OTHER)
            {
                String single = String.valueOf(charArray[pCur]);
                if (single.length() != 0)
                    atomSegment.add(new AtomNode(single, nCurType));
                pCur++;
            }
            //如果是字符�?数字或者�?��?�跟�?了数字的�?数点“.�?则一直�?�下去。
            else if (pCur < charArray.length - 1 && ((nCurType == CharType.CT_SINGLE) || nCurType == CharType.CT_NUM))
            {
                sb.delete(0, sb.length());
                sb.append(charArray[pCur]);

                boolean reachEnd = true;
                while (pCur < charArray.length - 1)
                {
                    nNextType = charTypeArray[++pCur];

                    if (nNextType == nCurType)
                        sb.append(charArray[pCur]);
                    else
                    {
                        reachEnd = false;
                        break;
                    }
                }
                atomSegment.add(new AtomNode(sb.toString(), nCurType));
                if (reachEnd)
                    pCur++;
            }
            // 对于所有其它情况
            else
            {
                atomSegment.add(new AtomNode(charArray[pCur], nCurType));
                pCur++;
            }
        }

//        logger.trace("原�?分�?:" + atomSegment);
        return atomSegment;
    }

    /**
     * 将连续的数字节点�?�并为一个
     *
     * @param linkedArray
     */
    private static void mergeContinueNumIntoOne(List<Vertex> linkedArray)
    {
        if (linkedArray.size() < 2)
            return;

        ListIterator<Vertex> listIterator = linkedArray.listIterator();
        Vertex next = listIterator.next();
        Vertex current = next;
        while (listIterator.hasNext())
        {
            next = listIterator.next();
//            System.out.println("current:" + current + " next:" + next);
            if ((TextUtility.isAllNum(current.realWord) || TextUtility.isAllChineseNum(current.realWord)) && (TextUtility.isAllNum(next.realWord) || TextUtility.isAllChineseNum(next.realWord)))
            {
                /////////// 这部分从逻辑上等�?�于current.realWord = current.realWord + next.realWord;
                // 但是current指针被几个路径共享，需�?备份，�?然修改了一处就修改了全局
                current = Vertex.newNumberInstance(current.realWord + next.realWord);
                listIterator.previous();
                listIterator.previous();
                listIterator.set(current);
                listIterator.next();
                listIterator.next();
                /////////// end 这部分
//                System.out.println("before:" + linkedArray);
                listIterator.remove();
//                System.out.println("after:" + linkedArray);
            }
            else
            {
                current = next;
            }
        }

//        logger.trace("数字识别�?�：" + Graph.parseResult(linkedArray));
    }

    /**
     * 生�?一元�?网
     *
     * @param wordNetStorage
     */
    protected void generateWordNet(final WordNet wordNetStorage)
    {
        final char[] charArray = wordNetStorage.charArray;

        // 核心�?典查询
        DoubleArrayTrie<CoreDictionary.Attribute>.Searcher searcher = CoreDictionary.trie.getSearcher(charArray, 0);
        while (searcher.next())
        {
            wordNetStorage.add(searcher.begin + 1, new Vertex(new String(charArray, searcher.begin, searcher.length), searcher.value, searcher.index));
        }
        // 强制用户�?典查询
        if (config.forceCustomDictionary)
        {
            CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
            {
                @Override
                public void hit(int begin, int end, CoreDictionary.Attribute value)
                {
                    wordNetStorage.add(begin + 1, new Vertex(new String(charArray, begin, end - begin), value));
                }
            });
        }
        // 原�?分�?，�?�?图连通
        LinkedList<Vertex>[] vertexes = wordNetStorage.getVertexes();
        for (int i = 1; i < vertexes.length; )
        {
            if (vertexes[i].isEmpty())
            {
                int j = i + 1;
                for (; j < vertexes.length - 1; ++j)
                {
                    if (!vertexes[j].isEmpty()) break;
                }
                wordNetStorage.add(i, quickAtomSegment(charArray, i - 1, j - 1));
                i = j;
            }
            else i += vertexes[i].getLast().realWord.length();
        }
    }

    /**
     * 为了索引模�?修饰结果
     *
     * @param vertexList
     * @param wordNetAll
     */
    protected List<Term> decorateResultForIndexMode(List<Vertex> vertexList, WordNet wordNetAll)
    {
        List<Term> termList = new LinkedList<Term>();
        int line = 1;
        ListIterator<Vertex> listIterator = vertexList.listIterator();
        listIterator.next();
        int length = vertexList.size() - 2;
        for (int i = 0; i < length; ++i)
        {
            Vertex vertex = listIterator.next();
            Term termMain = convert(vertex);
            termList.add(termMain);
            termMain.offset = line - 1;
            if (vertex.realWord.length() > 2)
            {
                // 过长�?所在的行
                int currentLine = line;
                while (currentLine < line + vertex.realWord.length())
                {
                    Iterator<Vertex> iterator = wordNetAll.descendingIterator(currentLine);// 这一行的�?，逆�?�??历�?�?字典�?稳定地由大到�?
                    while (iterator.hasNext())// 这一行的短�?
                    {
                        Vertex smallVertex = iterator.next();
                        if (
                            ((termMain.nature == Nature.mq && smallVertex.hasNature(Nature.q)) ||
                                smallVertex.realWord.length() >= config.indexMode)
                                && smallVertex != vertex // 防止�?�?添加
                                && currentLine + smallVertex.realWord.length() <= line + vertex.realWord.length() // 防止超出边界
                            )
                        {
                            listIterator.add(smallVertex);
                            Term termSub = convert(smallVertex);
                            termSub.offset = currentLine - 1;
                            termList.add(termSub);
                        }
                    }
                    ++currentLine;
                }
            }
            line += vertex.realWord.length();
        }

        return termList;
    }

    /**
     * �?性标注
     *
     * @param vertexList
     */
    protected static void speechTagging(List<Vertex> vertexList)
    {
        Viterbi.compute(vertexList, CoreDictionaryTransformMatrixDictionary.transformMatrixDictionary);
    }
}
