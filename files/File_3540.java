/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/29 14:53</create-date>
 *
 * <copyright file="AbstractBaseSegment.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.seg;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BaseNode;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.other.CharTable;
import com.hankcs.hanlp.dictionary.other.CharType;
import com.hankcs.hanlp.seg.NShort.Path.AtomNode;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;
import com.hankcs.hanlp.utility.Predefine;
import com.hankcs.hanlp.utility.SentencesUtil;
import com.hankcs.hanlp.utility.TextUtility;

import java.util.*;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 分�?器（分�?�?务）<br>
 * 是所有分�?器的基类（Abstract）<br>
 * 分�?器的分�?方法是线程安全的，但�?置方法则�?�?�?
 *
 * @author hankcs
 */
public abstract class Segment
{
    /**
     * 分�?器�?置
     */
    protected Config config;

    /**
     * 构造一个分�?器
     */
    public Segment()
    {
        config = new Config();
    }

    /**
     * 原�?分�?
     *
     * @param charArray
     * @param start     从start开始（包�?�）
     * @param end       到end结�?�（�?包�?�end）
     * @return 一个列表，代表从start到from的所有字构�?的原�?节点
     */
    protected static List<AtomNode> atomSegment(char[] charArray, int start, int end)
    {
        List<AtomNode> atomSegment = new ArrayList<AtomNode>();
        int pCur = start, nCurType, nNextType;
        StringBuilder sb = new StringBuilder();
        char c;

        int[] charTypeArray = new int[end - start];

        // 生�?对应�?�个汉字的字符类型数组
        for (int i = 0; i < charTypeArray.length; ++i)
        {
            c = charArray[i + start];
            charTypeArray[i] = CharType.get(c);

            if (c == '.' && i + start < (charArray.length - 1) && CharType.get(charArray[i + start + 1]) == CharType.CT_NUM)
                charTypeArray[i] = CharType.CT_NUM;
            else if (c == '.' && i + start < (charArray.length - 1) && charArray[i + start + 1] >= '0' && charArray[i + start + 1] <= '9')
                charTypeArray[i] = CharType.CT_SINGLE;
            else if (charTypeArray[i] == CharType.CT_LETTER)
                charTypeArray[i] = CharType.CT_SINGLE;
        }

        // 根�?�字符类型数组中的内容完�?原�?切割
        while (pCur < end)
        {
            nCurType = charTypeArray[pCur - start];

            if (nCurType == CharType.CT_CHINESE || nCurType == CharType.CT_INDEX ||
                    nCurType == CharType.CT_DELIMITER || nCurType == CharType.CT_OTHER)
            {
                String single = String.valueOf(charArray[pCur]);
                if (single.length() != 0)
                    atomSegment.add(new AtomNode(single, nCurType));
                pCur++;
            }
            //如果是字符�?数字或者�?��?�跟�?了数字的�?数点“.�?则一直�?�下去。
            else if (pCur < end - 1 && ((nCurType == CharType.CT_SINGLE) || nCurType == CharType.CT_NUM))
            {
                sb.delete(0, sb.length());
                sb.append(charArray[pCur]);

                boolean reachEnd = true;
                while (pCur < end - 1)
                {
                    nNextType = charTypeArray[++pCur - start];

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

        return atomSegment;
    }

    /**
     * 简易原�?分�?，将所有字放到一起作为一个�?
     *
     * @param charArray
     * @param start
     * @param end
     * @return
     */
    protected static List<AtomNode> simpleAtomSegment(char[] charArray, int start, int end)
    {
        List<AtomNode> atomNodeList = new LinkedList<AtomNode>();
        atomNodeList.add(new AtomNode(new String(charArray, start, end - start), CharType.CT_LETTER));
        return atomNodeList;
    }

    /**
     * 快速原�?分�?，希望用这个方法替�?�掉原�?�缓慢的方法
     *
     * @param charArray
     * @param start
     * @param end
     * @return
     */
    protected static List<AtomNode> quickAtomSegment(char[] charArray, int start, int end)
    {
        List<AtomNode> atomNodeList = new LinkedList<AtomNode>();
        int offsetAtom = start;
        int preType = CharType.get(charArray[offsetAtom]);
        int curType;
        while (++offsetAtom < end)
        {
            curType = CharType.get(charArray[offsetAtom]);
            if (curType != preType)
            {
                // 浮点数识别
                if (preType == CharType.CT_NUM && "，,．.".indexOf(charArray[offsetAtom]) != -1)
                {
                    if (offsetAtom+1 < end)
                    {
                        int nextType = CharType.get(charArray[offsetAtom+1]);
                        if (nextType == CharType.CT_NUM)
                        {
                            continue;
                        }
                    }
                }
                atomNodeList.add(new AtomNode(new String(charArray, start, offsetAtom - start), preType));
                start = offsetAtom;
            }
            preType = curType;
        }
        if (offsetAtom == end)
            atomNodeList.add(new AtomNode(new String(charArray, start, offsetAtom - start), preType));

        return atomNodeList;
    }

    /**
     * 使用用户�?典�?�并粗分结果
     * @param vertexList 粗分结果
     * @return �?�并�?�的结果
     */
    protected static List<Vertex> combineByCustomDictionary(List<Vertex> vertexList)
    {
        return combineByCustomDictionary(vertexList, CustomDictionary.dat);
    }

    /**
     * 使用用户�?典�?�并粗分结果
     * @param vertexList 粗分结果
     * @param dat 用户自定义�?典
     * @return �?�并�?�的结果
     */
    protected static List<Vertex> combineByCustomDictionary(List<Vertex> vertexList, DoubleArrayTrie<CoreDictionary.Attribute> dat)
    {
        assert vertexList.size() >= 2 : "vertexList至少包�?� 始##始 和 末##末";
        Vertex[] wordNet = new Vertex[vertexList.size()];
        vertexList.toArray(wordNet);
        // DAT�?�并
        int length = wordNet.length - 1; // 跳过首尾
        for (int i = 1; i < length; ++i)
        {
            int state = 1;
            state = dat.transition(wordNet[i].realWord, state);
            if (state > 0)
            {
                int to = i + 1;
                int end = to;
                CoreDictionary.Attribute value = dat.output(state);
                for (; to < length; ++to)
                {
                    state = dat.transition(wordNet[to].realWord, state);
                    if (state < 0) break;
                    CoreDictionary.Attribute output = dat.output(state);
                    if (output != null)
                    {
                        value = output;
                        end = to + 1;
                    }
                }
                if (value != null)
                {
                    combineWords(wordNet, i, end, value);
                    i = end - 1;
                }
            }
        }
        // BinTrie�?�并
        if (CustomDictionary.trie != null)
        {
            for (int i = 1; i < length; ++i)
            {
                if (wordNet[i] == null) continue;
                BaseNode<CoreDictionary.Attribute> state = CustomDictionary.trie.transition(wordNet[i].realWord.toCharArray(), 0);
                if (state != null)
                {
                    int to = i + 1;
                    int end = to;
                    CoreDictionary.Attribute value = state.getValue();
                    for (; to < length; ++to)
                    {
                        if (wordNet[to] == null) continue;
                        state = state.transition(wordNet[to].realWord.toCharArray(), 0);
                        if (state == null) break;
                        if (state.getValue() != null)
                        {
                            value = state.getValue();
                            end = to + 1;
                        }
                    }
                    if (value != null)
                    {
                        combineWords(wordNet, i, end, value);
                        i = end - 1;
                    }
                }
            }
        }
        vertexList.clear();
        for (Vertex vertex : wordNet)
        {
            if (vertex != null) vertexList.add(vertex);
        }
        return vertexList;
    }

    /**
     * 使用用户�?典�?�并粗分结果，并将用户�?语收集到全�?图中
     * @param vertexList 粗分结果
     * @param wordNetAll 收集用户�?语到全�?图中
     * @return �?�并�?�的结果
     */
    protected static List<Vertex> combineByCustomDictionary(List<Vertex> vertexList, final WordNet wordNetAll)
    {
        return combineByCustomDictionary(vertexList, CustomDictionary.dat, wordNetAll);
    }

    /**
     * 使用用户�?典�?�并粗分结果，并将用户�?语收集到全�?图中
     * @param vertexList 粗分结果
     * @param dat 用户自定义�?典
     * @param wordNetAll 收集用户�?语到全�?图中
     * @return �?�并�?�的结果
     */
    protected static List<Vertex> combineByCustomDictionary(List<Vertex> vertexList, DoubleArrayTrie<CoreDictionary.Attribute> dat, final WordNet wordNetAll)
    {
        List<Vertex> outputList = combineByCustomDictionary(vertexList, dat);
        int line = 0;
        for (final Vertex vertex : outputList)
        {
            final int parentLength = vertex.realWord.length();
            final int currentLine = line;
            if (parentLength >= 3)
            {
                CustomDictionary.parseText(vertex.realWord, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
                {
                    @Override
                    public void hit(int begin, int end, CoreDictionary.Attribute value)
                    {
                        if (end - begin == parentLength) return;
                        wordNetAll.add(currentLine + begin, new Vertex(vertex.realWord.substring(begin, end), value));
                    }
                });
            }
            line += parentLength;
        }
        return outputList;
    }

    /**
     * 将连续的�?语�?�并为一个
     * @param wordNet �?图
     * @param start 起始下标（包�?�）
     * @param end 结�?�下标（�?包�?�）
     * @param value 新的属性
     */
    private static void combineWords(Vertex[] wordNet, int start, int end, CoreDictionary.Attribute value)
    {
        if (start + 1 == end)   // �?优化，如果�?�有一个�?，那就�?需�?�?�并，直接应用新属性
        {
            wordNet[start].attribute = value;
        }
        else
        {
            StringBuilder sbTerm = new StringBuilder();
            for (int j = start; j < end; ++j)
            {
                if (wordNet[j] == null) continue;
                String realWord = wordNet[j].realWord;
                sbTerm.append(realWord);
                wordNet[j] = null;
            }
            String realWord = sbTerm.toString();
            wordNet[start] = new Vertex(realWord, realWord, value);
        }
    }

    /**
     * 将一�?�路径转为最终结果
     *
     * @param vertexList
     * @param offsetEnabled 是�?�计算offset
     * @return
     */
    protected static List<Term> convert(List<Vertex> vertexList, boolean offsetEnabled)
    {
        assert vertexList != null;
        assert vertexList.size() >= 2 : "这�?�路径�?应当短于2" + vertexList.toString();
        int length = vertexList.size() - 2;
        List<Term> resultList = new ArrayList<Term>(length);
        Iterator<Vertex> iterator = vertexList.iterator();
        iterator.next();
        if (offsetEnabled)
        {
            int offset = 0;
            for (int i = 0; i < length; ++i)
            {
                Vertex vertex = iterator.next();
                Term term = convert(vertex);
                term.offset = offset;
                offset += term.length();
                resultList.add(term);
            }
        }
        else
        {
            for (int i = 0; i < length; ++i)
            {
                Vertex vertex = iterator.next();
                Term term = convert(vertex);
                resultList.add(term);
            }
        }
        return resultList;
    }

    /**
     * 将节点转为term
     *
     * @param vertex
     * @return
     */
    static Term convert(Vertex vertex)
    {
        return new Term(vertex.realWord, vertex.guessNature());
    }

    /**
     * �?�并数字
     * @param termList
     */
    protected void mergeNumberQuantifier(List<Vertex> termList, WordNet wordNetAll, Config config)
    {
        if (termList.size() < 4) return;
        StringBuilder sbQuantifier = new StringBuilder();
        ListIterator<Vertex> iterator = termList.listIterator();
        iterator.next();
        int line = 1;
        while (iterator.hasNext())
        {
            Vertex pre = iterator.next();
            if (pre.hasNature(Nature.m))
            {
                sbQuantifier.append(pre.realWord);
                Vertex cur = null;
                while (iterator.hasNext() && (cur = iterator.next()).hasNature(Nature.m))
                {
                    sbQuantifier.append(cur.realWord);
                    iterator.remove();
                    removeFromWordNet(cur, wordNetAll, line, sbQuantifier.length());
                }
                if (cur != null)
                {
                    if ((cur.hasNature(Nature.q) || cur.hasNature(Nature.qv) || cur.hasNature(Nature.qt)))
                    {
                        if (config.indexMode > 0)
                        {
                            wordNetAll.add(line, new Vertex(sbQuantifier.toString(), new CoreDictionary.Attribute(Nature.m)));
                        }
                        sbQuantifier.append(cur.realWord);
                        iterator.remove();
                        removeFromWordNet(cur, wordNetAll, line, sbQuantifier.length());
                    }
                    else
                    {
                        line += cur.realWord.length();   // (cur = iterator.next()).hasNature(Nature.m) 最�?�一个next�?�能�?�?�q�?性
                    }
                }
                if (sbQuantifier.length() != pre.realWord.length())
                {
                    for (Vertex vertex : wordNetAll.get(line + pre.realWord.length()))
                    {
                        vertex.from = null;
                    }
                    pre.realWord = sbQuantifier.toString();
                    pre.word = Predefine.TAG_NUMBER;
                    pre.attribute = new CoreDictionary.Attribute(Nature.mq);
                    pre.wordID = CoreDictionary.M_WORD_ID;
                    sbQuantifier.setLength(0);
                }
            }
            sbQuantifier.setLength(0);
            line += pre.realWord.length();
        }
//        System.out.println(wordNetAll);
    }

    /**
     * 将一个�?语从�?网中彻底抹除
     * @param cur �?语
     * @param wordNetAll �?网
     * @param line 当�?扫�??的行数
     * @param length 当�?缓冲区的长度
     */
    private static void removeFromWordNet(Vertex cur, WordNet wordNetAll, int line, int length)
    {
        LinkedList<Vertex>[] vertexes = wordNetAll.getVertexes();
        // 将其从wordNet中删除
        for (Vertex vertex : vertexes[line + length])
        {
            if (vertex.from == cur)
                vertex.from = null;
        }
        ListIterator<Vertex> iterator = vertexes[line + length - cur.realWord.length()].listIterator();
        while (iterator.hasNext())
        {
            Vertex vertex = iterator.next();
            if (vertex == cur) iterator.remove();
        }
    }

    /**
     * 分�?<br>
     * 此方法是线程安全的
     *
     * @param text 待分�?文本
     * @return �?��?列表
     */
    public List<Term> seg(String text)
    {
        char[] charArray = text.toCharArray();
        if (HanLP.Config.Normalization)
        {
            CharTable.normalization(charArray);
        }
        if (config.threadNumber > 1 && charArray.length > 10000)    // �?文本多线程没�?义，�??而�?�慢了
        {
            List<String> sentenceList = SentencesUtil.toSentenceList(charArray);
            String[] sentenceArray = new String[sentenceList.size()];
            sentenceList.toArray(sentenceArray);
            //noinspection unchecked
            List<Term>[] termListArray = new List[sentenceArray.length];
            final int per = sentenceArray.length / config.threadNumber;
            WorkThread[] threadArray = new WorkThread[config.threadNumber];
            for (int i = 0; i < config.threadNumber - 1; ++i)
            {
                int from = i * per;
                threadArray[i] = new WorkThread(sentenceArray, termListArray, from, from + per);
                threadArray[i].start();
            }
            threadArray[config.threadNumber - 1] = new WorkThread(sentenceArray, termListArray, (config.threadNumber - 1) * per, sentenceArray.length);
            threadArray[config.threadNumber - 1].start();
            try
            {
                for (WorkThread thread : threadArray)
                {
                    thread.join();
                }
            }
            catch (InterruptedException e)
            {
                logger.severe("线程�?�步异常：" + TextUtility.exceptionToString(e));
                return Collections.emptyList();
            }
            List<Term> termList = new LinkedList<Term>();
            if (config.offset || config.indexMode > 0)  // 由于分割了�?��?，所以需�?�?新校正offset
            {
                int sentenceOffset = 0;
                for (int i = 0; i < sentenceArray.length; ++i)
                {
                    for (Term term : termListArray[i])
                    {
                        term.offset += sentenceOffset;
                        termList.add(term);
                    }
                    sentenceOffset += sentenceArray[i].length();
                }
            }
            else
            {
                for (List<Term> list : termListArray)
                {
                    termList.addAll(list);
                }
            }

            return termList;
        }
//        if (text.length() > 10000)  // 针对大文本，先拆�?�?��?，�?�分�?，�?��?内存峰值太大
//        {
//            List<Term> termList = new LinkedList<Term>();
//            if (config.offset || config.indexMode)
//            {
//                int sentenceOffset = 0;
//                for (String sentence : SentencesUtil.toSentenceList(charArray))
//                {
//                    List<Term> termOfSentence = segSentence(sentence.toCharArray());
//                    for (Term term : termOfSentence)
//                    {
//                        term.offset += sentenceOffset;
//                        termList.add(term);
//                    }
//                    sentenceOffset += sentence.length();
//                }
//            }
//            else
//            {
//                for (String sentence : SentencesUtil.toSentenceList(charArray))
//                {
//                    termList.addAll(segSentence(sentence.toCharArray()));
//                }
//            }
//
//            return termList;
//        }
        return segSentence(charArray);
    }

    /**
     * 分�?
     *
     * @param text 待分�?文本
     * @return �?��?列表
     */
    public List<Term> seg(char[] text)
    {
        assert text != null;
        if (HanLP.Config.Normalization)
        {
            CharTable.normalization(text);
        }
        return segSentence(text);
    }

    /**
     * 分�?断�?� 输出�?��?形�?
     *
     * @param text 待分�?�?��?
     * @return �?��?列表，�?个�?��?由一个�?��?列表组�?
     */
    public List<List<Term>> seg2sentence(String text)
    {
        return seg2sentence(text, true);
    }

    /**
     * 分�?断�?� 输出�?��?形�?
     *
     * @param text     待分�?�?��?
     * @param shortest 是�?�断�?�为最细的�?�?�（将逗�?�也视作分隔符）
     * @return �?��?列表，�?个�?��?由一个�?��?列表组�?
     */
    public List<List<Term>> seg2sentence(String text, boolean shortest)
    {
        List<List<Term>> resultList = new LinkedList<List<Term>>();
        {
            for (String sentence : SentencesUtil.toSentenceList(text, shortest))
            {
                resultList.add(segSentence(sentence.toCharArray()));
            }
        }

        return resultList;
    }

    /**
     * 给一个�?��?分�?
     *
     * @param sentence 待分�?�?��?
     * @return �?��?列表
     */
    protected abstract List<Term> segSentence(char[] sentence);

    /**
     * 设为索引模�?
     *
     * @return
     */
    public Segment enableIndexMode(boolean enable)
    {
        config.indexMode = enable ? 2 : 0;
        return this;
    }

    /**
     * 索引模�?下的最�?切分颗粒度（设为1�?�以最�?切分为�?�字）
     *
     * @param minimalLength 三字�?�?�以上的�?语将会被切分为大于等于此长度的�?�?语。默认�?�2。
     * @return
     */
    public Segment enableIndexMode(int minimalLength)
    {
        if (minimalLength < 1) throw new IllegalArgumentException("最�?长度应当大于等于1");
        config.indexMode = minimalLength;

        return this;
    }

    /**
     * 开�?��?性标注
     *
     * @param enable
     * @return
     */
    public Segment enablePartOfSpeechTagging(boolean enable)
    {
        config.speechTagging = enable;
        return this;
    }

    /**
     * 开�?�人�??识别
     *
     * @param enable
     * @return
     */
    public Segment enableNameRecognize(boolean enable)
    {
        config.nameRecognize = enable;
        config.updateNerConfig();
        return this;
    }

    /**
     * 开�?�地�??识别
     *
     * @param enable
     * @return
     */
    public Segment enablePlaceRecognize(boolean enable)
    {
        config.placeRecognize = enable;
        config.updateNerConfig();
        return this;
    }

    /**
     * 开�?�机构�??识别
     *
     * @param enable
     * @return
     */
    public Segment enableOrganizationRecognize(boolean enable)
    {
        config.organizationRecognize = enable;
        config.updateNerConfig();
        return this;
    }

    /**
     * 是�?��?�用用户�?典
     *
     * @param enable
     */
    public Segment enableCustomDictionary(boolean enable)
    {
        config.useCustomDictionary = enable;
        return this;
    }

    /**
     * 是�?�尽�?�能强制使用用户�?典（使用户�?典的优先级尽�?�能高）<br>
     *     警告：具体实现由�?��?类决定，�?�能会破�??分�?器的统计特性（例如，如果用户�?典
     *     �?�有“和�?�?，则“商�?和�?务�?的分�?结果�?�能会被用户�?典的高优先级影�?）。
     * @param enable
     * @return 分�?器本身
     *
     * @since 1.3.5
     */
    public Segment enableCustomDictionaryForcing(boolean enable)
    {
        if (enable)
        {
            enableCustomDictionary(true);
        }
        config.forceCustomDictionary = enable;
        return this;
    }

    /**
     * 是�?��?�用音译人�??识别
     *
     * @param enable
     */
    public Segment enableTranslatedNameRecognize(boolean enable)
    {
        config.translatedNameRecognize = enable;
        config.updateNerConfig();
        return this;
    }

    /**
     * 是�?��?�用日本人�??识别
     *
     * @param enable
     */
    public Segment enableJapaneseNameRecognize(boolean enable)
    {
        config.japaneseNameRecognize = enable;
        config.updateNerConfig();
        return this;
    }

    /**
     * 是�?��?�用�??移�?计算（开�?��?�Term.offset�?会被计算）
     *
     * @param enable
     * @return
     */
    public Segment enableOffset(boolean enable)
    {
        config.offset = enable;
        return this;
    }

    /**
     * 是�?��?�用数�?和数�?�?识别<br>
     *     �?�[二, �??, 一] => [二�??一]，[�??, �?, 元] => [�??�?元]
     * @param enable
     * @return
     */
    public Segment enableNumberQuantifierRecognize(boolean enable)
    {
        config.numberQuantifierRecognize = enable;
        return this;
    }

    /**
     * 是�?��?�用所有的命�??实体识别
     *
     * @param enable
     * @return
     */
    public Segment enableAllNamedEntityRecognize(boolean enable)
    {
        config.nameRecognize = enable;
        config.japaneseNameRecognize = enable;
        config.translatedNameRecognize = enable;
        config.placeRecognize = enable;
        config.organizationRecognize = enable;
        config.updateNerConfig();
        return this;
    }

    class WorkThread extends Thread
    {
        String[] sentenceArray;
        List<Term>[] termListArray;
        int from;
        int to;

        public WorkThread(String[] sentenceArray, List<Term>[] termListArray, int from, int to)
        {
            this.sentenceArray = sentenceArray;
            this.termListArray = termListArray;
            this.from = from;
            this.to = to;
        }

        @Override
        public void run()
        {
            for (int i = from; i < to; ++i)
            {
                termListArray[i] = segSentence(sentenceArray[i].toCharArray());
            }
        }
    }

    /**
     * 开�?�多线程
     * @param enable true表示开�?�[系统CPU核心数]个线程，false表示�?�线程
     * @return
     */
    public Segment enableMultithreading(boolean enable)
    {
        if (enable) config.threadNumber = Runtime.getRuntime().availableProcessors();
        else config.threadNumber = 1;
        return this;
    }

    /**
     * 开�?�多线程
     * @param threadNumber 线程数�?
     * @return
     */
    public Segment enableMultithreading(int threadNumber)
    {
        config.threadNumber = threadNumber;
        return this;
    }
}
