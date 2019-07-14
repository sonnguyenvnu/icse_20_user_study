/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-03-30 下�?�7:42</create-date>
 *
 * <copyright file="AbstractLexicalAnalyzer.java">
 * Copyright (c) 2018, Han He. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Han He. Please contact Han He to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.tokenizer.lexical;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BaseNode;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.CompoundWord;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.other.CharTable;
import com.hankcs.hanlp.dictionary.other.CharType;
import com.hankcs.hanlp.model.perceptron.tagset.NERTagSet;
import com.hankcs.hanlp.recognition.nr.JapanesePersonRecognition;
import com.hankcs.hanlp.recognition.nr.TranslatedPersonRecognition;
import com.hankcs.hanlp.seg.CharacterBasedSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.seg.common.Vertex;
import com.hankcs.hanlp.seg.common.WordNet;
import com.hankcs.hanlp.utility.Predefine;

import java.util.*;

/**
 * �?法分�?器基类（中文分�?�?�?性标注和命�??实体识别）
 *
 * @author hankcs
 */
public class AbstractLexicalAnalyzer extends CharacterBasedSegment implements LexicalAnalyzer
{
    protected Segmenter segmenter;
    protected POSTagger posTagger;
    protected NERecognizer neRecognizer;
    /**
     * 字符类型表
     */
    protected static byte[] typeTable;
    /**
     * 是�?�执行规则分�?（英文数字标点等的规则预处�?�）。规则永远是丑陋的，默认关闭。
     */
    protected boolean enableRuleBasedSegment = false;

    static
    {
        typeTable = new byte[CharType.type.length];
        System.arraycopy(CharType.type, 0, typeTable, 0, typeTable.length);
        for (char c : Predefine.CHINESE_NUMBERS.toCharArray())
        {
            typeTable[c] = CharType.CT_CHINESE;
        }
        typeTable[CharTable.convert('·')] = CharType.CT_CHINESE;
    }

    protected AbstractLexicalAnalyzer()
    {
        config.translatedNameRecognize = false;
        config.japaneseNameRecognize = false;
    }

    public AbstractLexicalAnalyzer(Segmenter segmenter)
    {
        this();
        this.segmenter = segmenter;
    }

    public AbstractLexicalAnalyzer(Segmenter segmenter, POSTagger posTagger)
    {
        this();
        this.segmenter = segmenter;
        this.posTagger = posTagger;
    }

    public AbstractLexicalAnalyzer(Segmenter segmenter, POSTagger posTagger, NERecognizer neRecognizer)
    {
        this();
        this.segmenter = segmenter;
        this.posTagger = posTagger;
        this.neRecognizer = neRecognizer;
        if (posTagger != null)
        {
            config.speechTagging = true;
            if (neRecognizer != null)
            {
                config.ner = true;
            }
        }
    }

    /**
     * 分�?
     *
     * @param sentence      文本
     * @param normalized    正规化�?�的文本
     * @param wordList      储存�?��?列表
     * @param attributeList 储存用户�?典中的�?性，设为null表示�?查询用户�?典
     */
    protected void segment(final String sentence, final String normalized, final List<String> wordList, final List<CoreDictionary.Attribute> attributeList)
    {
        if (attributeList != null)
        {
            final int[] offset = new int[]{0};
            CustomDictionary.parseLongestText(sentence, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
            {
                @Override
                public void hit(int begin, int end, CoreDictionary.Attribute value)
                {
                    if (begin != offset[0])
                    {
                        segmentAfterRule(sentence.substring(offset[0], begin), normalized.substring(offset[0], begin), wordList);
                    }
                    while (attributeList.size() < wordList.size())
                        attributeList.add(null);
                    wordList.add(sentence.substring(begin, end));
                    attributeList.add(value);
                    assert wordList.size() == attributeList.size() : "�?语列表与属性列表�?等长";
                    offset[0] = end;
                }
            });
            if (offset[0] != sentence.length())
            {
                segmentAfterRule(sentence.substring(offset[0]), normalized.substring(offset[0]), wordList);
            }
        }
        else
        {
            segmentAfterRule(sentence, normalized, wordList);
        }
    }

    @Override
    public void segment(final String sentence, final String normalized, final List<String> wordList)
    {
        if (config.useCustomDictionary)
        {
            final int[] offset = new int[]{0};
            CustomDictionary.parseLongestText(sentence, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>()
            {
                @Override
                public void hit(int begin, int end, CoreDictionary.Attribute value)
                {
                    if (begin != offset[0])
                    {
                        segmentAfterRule(sentence.substring(offset[0], begin), normalized.substring(offset[0], begin), wordList);
                    }
                    wordList.add(sentence.substring(begin, end));
                    offset[0] = end;
                }
            });
            if (offset[0] != sentence.length())
            {
                segmentAfterRule(sentence.substring(offset[0]), normalized.substring(offset[0]), wordList);
            }
        }
        else
        {
            segmentAfterRule(sentence, normalized, wordList);
        }
    }

    /**
     * 中文分�?
     *
     * @param sentence
     * @return
     */
    public List<String> segment(String sentence)
    {
        return segment(sentence, CharTable.convert(sentence));
    }

    @Override
    public String[] recognize(String[] wordArray, String[] posArray)
    {
        return neRecognizer.recognize(wordArray, posArray);
    }

    @Override
    public String[] tag(String... words)
    {
        return posTagger.tag(words);
    }

    @Override
    public String[] tag(List<String> wordList)
    {
        return posTagger.tag(wordList);
    }

    @Override
    public NERTagSet getNERTagSet()
    {
        return neRecognizer.getNERTagSet();
    }

    @Override
    public Sentence analyze(final String sentence)
    {
        if (sentence.isEmpty())
        {
            return new Sentence(Collections.<IWord>emptyList());
        }
        final String normalized = CharTable.convert(sentence);
        List<String> wordList = new LinkedList<String>();
        List<CoreDictionary.Attribute> attributeList = segmentWithAttribute(sentence, normalized, wordList);

        String[] wordArray = new String[wordList.size()];
        int offset = 0;
        int id = 0;
        for (String word : wordList)
        {
            wordArray[id] = normalized.substring(offset, offset + word.length());
            ++id;
            offset += word.length();
        }

        List<IWord> termList = new ArrayList<IWord>(wordList.size());
        if (posTagger != null)
        {
            String[] posArray = tag(wordArray);
            if (neRecognizer != null)
            {
                String[] nerArray = neRecognizer.recognize(wordArray, posArray);
                overwriteTag(attributeList, posArray);
                wordList.toArray(wordArray);

                List<Word> result = new LinkedList<Word>();
                result.add(new Word(wordArray[0], posArray[0]));
                String prePos = posArray[0];

                NERTagSet tagSet = getNERTagSet();
                for (int i = 1; i < nerArray.length; i++)
                {
                    if (nerArray[i].charAt(0) == tagSet.B_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR || nerArray[i].charAt(0) == tagSet.O_TAG_CHAR)
                    {
                        termList.add(result.size() > 1 ? new CompoundWord(result, prePos) : result.get(0));
                        result = new ArrayList<Word>();
                    }
                    result.add(new Word(wordArray[i], posArray[i]));
                    if (nerArray[i].charAt(0) == tagSet.O_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR)
                    {
                        prePos = posArray[i];
                    }
                    else
                    {
                        prePos = NERTagSet.posOf(nerArray[i]);
                    }
                }
                if (result.size() != 0)
                {
                    termList.add(result.size() > 1 ? new CompoundWord(result, prePos) : result.get(0));
                }
            }
            else
            {
                overwriteTag(attributeList, posArray);
                wordList.toArray(wordArray);
                for (int i = 0; i < wordArray.length; i++)
                {
                    termList.add(new Word(wordArray[i], posArray[i]));
                }
            }
        }
        else
        {
            wordList.toArray(wordArray);
            for (String word : wordArray)
            {
                termList.add(new Word(word, null));
            }
        }

        return new Sentence(termList);
    }

    private void overwriteTag(List<CoreDictionary.Attribute> attributeList, String[] posArray)
    {
        int id;
        if (attributeList != null)
        {
            id = 0;
            for (CoreDictionary.Attribute attribute : attributeList)
            {
                if (attribute != null)
                    posArray[id] = attribute.nature[0].toString();
                ++id;
            }
        }
    }

    /**
     * 这个方法会查询用户�?典
     *
     * @param sentence
     * @param normalized
     * @return
     */
    public List<String> segment(final String sentence, final String normalized)
    {
        final List<String> wordList = new LinkedList<String>();
        segment(sentence, normalized, wordList);
        return wordList;
    }

    /**
     * 分�?时查询到一个用户�?典中的�?语，此处控制是�?�接�?�它
     *
     * @param begin 起始�?置
     * @param end   终止�?置
     * @param value �?性
     * @return true 表示接�?�
     * @deprecated 自1.6.7起废弃，强制模�?下为最长匹�?，�?�则按分�?结果�?�并
     */
    protected boolean acceptCustomWord(int begin, int end, CoreDictionary.Attribute value)
    {
        return config.forceCustomDictionary || (end - begin >= 4 && !value.hasNatureStartsWith("nr") && !value.hasNatureStartsWith("ns") && !value.hasNatureStartsWith("nt"));
    }

    @Override
    protected List<Term> roughSegSentence(char[] sentence)
    {
        return null;
    }

    @Override
    protected List<Term> segSentence(char[] sentence)
    {
        if (sentence.length == 0)
        {
            return Collections.emptyList();
        }
        String original = new String(sentence);
        CharTable.normalization(sentence);
        String normalized = new String(sentence);
        List<String> wordList = new LinkedList<String>();
        List<CoreDictionary.Attribute> attributeList;
        attributeList = segmentWithAttribute(original, normalized, wordList);
        List<Term> termList = new ArrayList<Term>(wordList.size());
        int offset = 0;
        for (String word : wordList)
        {
            Term term = new Term(word, null);
            term.offset = offset;
            offset += term.length();
            termList.add(term);
        }
        if (config.speechTagging)
        {
            if (posTagger != null)
            {
                String[] wordArray = new String[wordList.size()];
                offset = 0;
                int id = 0;
                for (String word : wordList)
                {
                    wordArray[id] = normalized.substring(offset, offset + word.length());
                    ++id;
                    offset += word.length();
                }
                String[] posArray = tag(wordArray);
                Iterator<Term> iterator = termList.iterator();
                Iterator<CoreDictionary.Attribute> attributeIterator = attributeList == null ? null : attributeList.iterator();
                for (int i = 0; i < posArray.length; i++)
                {
                    if (attributeIterator != null && attributeIterator.hasNext())
                    {
                        CoreDictionary.Attribute attribute = attributeIterator.next();
                        if (attribute != null)
                        {
                            iterator.next().nature = attribute.nature[0]; // 使用�?典中的�?性覆盖�?性标注器的结果
                            continue;
                        }
                    }
                    iterator.next().nature = Nature.create(posArray[i]);
                }

                if (config.ner && neRecognizer != null)
                {
                    List<Term> childrenList = null;
                    if (config.isIndexMode())
                    {
                        childrenList = new LinkedList<Term>();
                        iterator = termList.iterator();
                    }
                    termList = new ArrayList<Term>(termList.size());
                    String[] nerArray = recognize(wordArray, posArray);
                    wordList.toArray(wordArray);
                    StringBuilder result = new StringBuilder();
                    result.append(wordArray[0]);
                    if (childrenList != null)
                    {
                        childrenList.add(iterator.next());
                    }
                    if (attributeList != null)
                    {
                        attributeIterator = attributeList.iterator();
                        for (int i = 0; i < wordArray.length && attributeIterator.hasNext(); i++)
                        {
                            CoreDictionary.Attribute attribute = attributeIterator.next();
                            if (attribute != null)
                                posArray[i] = attribute.nature[0].toString();
                        }
                    }
                    String prePos = posArray[0];
                    offset = 0;

                    for (int i = 1; i < nerArray.length; i++)
                    {
                        NERTagSet tagSet = getNERTagSet();
                        if (nerArray[i].charAt(0) == tagSet.B_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR || nerArray[i].charAt(0) == tagSet.O_TAG_CHAR)
                        {
                            Term term = new Term(result.toString(), Nature.create(prePos));
                            term.offset = offset;
                            offset += term.length();
                            termList.add(term);
                            if (childrenList != null)
                            {
                                if (childrenList.size() > 1)
                                {
                                    for (Term shortTerm : childrenList)
                                    {
                                        if (shortTerm.length() >= config.indexMode)
                                        {
                                            termList.add(shortTerm);
                                        }
                                    }
                                }
                                childrenList.clear();
                            }
                            result.setLength(0);
                        }
                        result.append(wordArray[i]);
                        if (childrenList != null)
                        {
                            childrenList.add(iterator.next());
                        }
                        if (nerArray[i].charAt(0) == tagSet.O_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR)
                        {
                            prePos = posArray[i];
                        }
                        else
                        {
                            prePos = NERTagSet.posOf(nerArray[i]);
                        }
                    }
                    if (result.length() != 0)
                    {
                        Term term = new Term(result.toString(), Nature.create(prePos));
                        term.offset = offset;
                        termList.add(term);
                        if (childrenList != null)
                        {
                            if (childrenList.size() > 1)
                            {
                                for (Term shortTerm : childrenList)
                                {
                                    if (shortTerm.length() >= config.indexMode)
                                    {
                                        termList.add(shortTerm);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                for (Term term : termList)
                {
                    CoreDictionary.Attribute attribute = CoreDictionary.get(term.word);
                    if (attribute != null)
                    {
                        term.nature = attribute.nature[0];
                    }
                    else
                    {
                        term.nature = Nature.n;
                    }
                }
            }
        }
        if (config.translatedNameRecognize || config.japaneseNameRecognize)
        {
            List<Vertex> vertexList = toVertexList(termList, true);
            WordNet wordNetOptimum = new WordNet(sentence, vertexList);
            WordNet wordNetAll = wordNetOptimum;
            if (config.translatedNameRecognize)
            {
                TranslatedPersonRecognition.recognition(vertexList, wordNetOptimum, wordNetAll);
            }
            if (config.japaneseNameRecognize)
            {
                JapanesePersonRecognition.recognition(vertexList, wordNetOptimum, wordNetAll);
            }
            termList = convert(vertexList, config.offset);
        }
        return termList;
    }

    /**
     * CT_CHINESE区间交给统计分�?，�?�则视作整个�?��?
     *
     * @param sentence
     * @param normalized
     * @param start
     * @param end
     * @param preType
     * @param wordList
     */
    private void pushPiece(String sentence, String normalized, int start, int end, byte preType, List<String> wordList)
    {
        if (preType == CharType.CT_CHINESE)
        {
            segmenter.segment(sentence.substring(start, end), normalized.substring(start, end), wordList);
        }
        else
        {
            wordList.add(sentence.substring(start, end));
        }
    }

    /**
     * 丑陋的规则系统
     *
     * @param sentence
     * @param normalized
     * @param wordList
     */
    protected void segmentAfterRule(String sentence, String normalized, List<String> wordList)
    {
        if (!enableRuleBasedSegment)
        {
            segmenter.segment(sentence, normalized, wordList);
            return;
        }
        int start = 0;
        int end = start;
        byte preType = typeTable[normalized.charAt(end)];
        byte curType;
        while (++end < normalized.length())
        {
            curType = typeTable[normalized.charAt(end)];
            if (curType != preType)
            {
                if (preType == CharType.CT_NUM)
                {
                    // 浮点数识别
                    if ("，,．.".indexOf(normalized.charAt(end)) != -1)
                    {
                        if (end + 1 < normalized.length())
                        {
                            if (typeTable[normalized.charAt(end + 1)] == CharType.CT_NUM)
                            {
                                continue;
                            }
                        }
                    }
                    else if ("年月日时分秒".indexOf(normalized.charAt(end)) != -1)
                    {
                        preType = curType; // 交给统计分�?
                        continue;
                    }
                }
                pushPiece(sentence, normalized, start, end, preType, wordList);
                start = end;
            }
            preType = curType;
        }
        if (end == normalized.length())
            pushPiece(sentence, normalized, start, end, preType, wordList);
    }

    /**
     * 返回用户�?典中的attribute的分�?
     *
     * @param original
     * @param normalized
     * @param wordList
     * @return
     */
    private List<CoreDictionary.Attribute> segmentWithAttribute(String original, String normalized, List<String> wordList)
    {
        List<CoreDictionary.Attribute> attributeList;
        if (config.useCustomDictionary)
        {
            if (config.forceCustomDictionary)
            {
                attributeList = new LinkedList<CoreDictionary.Attribute>();
                segment(original, normalized, wordList, attributeList);
            }
            else
            {
                segmentAfterRule(original, normalized, wordList);
                attributeList = combineWithCustomDictionary(wordList);
            }
        }
        else
        {
            segmentAfterRule(original, normalized, wordList);
            attributeList = null;
        }
        return attributeList;
    }

    /**
     * 使用用户�?典�?�并粗分结果
     *
     * @param vertexList 粗分结果
     * @return �?�并�?�的结果
     */
    protected static List<CoreDictionary.Attribute> combineWithCustomDictionary(List<String> vertexList)
    {
        String[] wordNet = new String[vertexList.size()];
        vertexList.toArray(wordNet);
        CoreDictionary.Attribute[] attributeArray = new CoreDictionary.Attribute[wordNet.length];
        // DAT�?�并
        DoubleArrayTrie<CoreDictionary.Attribute> dat = CustomDictionary.dat;
        int length = wordNet.length;
        for (int i = 0; i < length; ++i)
        {
            int state = 1;
            state = dat.transition(wordNet[i], state);
            if (state > 0)
            {
                int to = i + 1;
                int end = to;
                CoreDictionary.Attribute value = dat.output(state);
                for (; to < length; ++to)
                {
                    state = dat.transition(wordNet[to], state);
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
                    combineWords(wordNet, i, end, attributeArray, value);
                    i = end - 1;
                }
            }
        }
        // BinTrie�?�并
        if (CustomDictionary.trie != null)
        {
            for (int i = 0; i < length; ++i)
            {
                if (wordNet[i] == null) continue;
                BaseNode<CoreDictionary.Attribute> state = CustomDictionary.trie.transition(wordNet[i], 0);
                if (state != null)
                {
                    int to = i + 1;
                    int end = to;
                    CoreDictionary.Attribute value = state.getValue();
                    for (; to < length; ++to)
                    {
                        if (wordNet[to] == null) continue;
                        state = state.transition(wordNet[to], 0);
                        if (state == null) break;
                        if (state.getValue() != null)
                        {
                            value = state.getValue();
                            end = to + 1;
                        }
                    }
                    if (value != null)
                    {
                        combineWords(wordNet, i, end, attributeArray, value);
                        i = end - 1;
                    }
                }
            }
        }
        vertexList.clear();
        List<CoreDictionary.Attribute> attributeList = new LinkedList<CoreDictionary.Attribute>();
        for (int i = 0; i < wordNet.length; i++)
        {
            if (wordNet[i] != null)
            {
                vertexList.add(wordNet[i]);
                attributeList.add(attributeArray[i]);
            }
        }
        return attributeList;
    }

    /**
     * 将连续的�?语�?�并为一个
     *
     * @param wordNet �?图
     * @param start   起始下标（包�?�）
     * @param end     结�?�下标（�?包�?�）
     * @param value   新的属性
     */
    private static void combineWords(String[] wordNet, int start, int end, CoreDictionary.Attribute[] attributeArray, CoreDictionary.Attribute value)
    {
        if (start + 1 != end)   // �?优化，如果�?�有一个�?，那就�?需�?�?�并，直接应用新属性
        {
            StringBuilder sbTerm = new StringBuilder();
            for (int j = start; j < end; ++j)
            {
                if (wordNet[j] == null) continue;
                sbTerm.append(wordNet[j]);
                wordNet[j] = null;
            }
            wordNet[start] = sbTerm.toString();
        }
        attributeArray[start] = value;
    }

    /**
     * 是�?�执行规则分�?（英文数字标点等的规则预处�?�）。规则永远是丑陋的，默认关闭。
     *
     * @param enableRuleBasedSegment 是�?�激活
     * @return �?法分�?器对象
     */
    public AbstractLexicalAnalyzer enableRuleBasedSegment(boolean enableRuleBasedSegment)
    {
        this.enableRuleBasedSegment = enableRuleBasedSegment;
        return this;
    }
}
