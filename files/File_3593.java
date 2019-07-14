package com.hankcs.hanlp.summary;


import com.hankcs.hanlp.algorithm.MaxHeap;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.*;

/**
 * 基于TextRank算法的关键字�??�?�，适用于�?�文档
 *
 * @author hankcs
 */
public class TextRankKeyword extends KeywordExtractor
{
    /**
     * 阻尼系数（Ｄ�?�?�?ｉｎｇＦ�?ｃｔ�?ｒ），一般�?�值为0.85
     */
    final static float d = 0.85f;
    /**
     * 最大迭代次数
     */
    public static int max_iter = 200;
    final static float min_diff = 0.001f;

    public TextRankKeyword(Segment defaultSegment)
    {
        super(defaultSegment);
    }

    public TextRankKeyword()
    {
    }

    /**
     * �??�?�关键�?
     *
     * @param document 文档内容
     * @param size     希望�??�?�几个关键�?
     * @return 一个列表
     */
    public static List<String> getKeywordList(String document, int size)
    {
        TextRankKeyword textRankKeyword = new TextRankKeyword();

        return textRankKeyword.getKeywords(document, size);
    }

    /**
     * �??�?�关键�?
     *
     * @param content
     * @return
     * @deprecated 请使用 {@link KeywordExtractor#getKeywords(java.lang.String)}
     */
    public List<String> getKeyword(String content)
    {
        return getKeywords(content);
    }

    /**
     * 返回全部分�?结果和对应的rank
     *
     * @param content
     * @return
     */
    public Map<String, Float> getTermAndRank(String content)
    {
        assert content != null;
        List<Term> termList = defaultSegment.seg(content);
        return getTermAndRank(termList);
    }

    /**
     * 返回分数最高的�?size个分�?结果和对应的rank
     *
     * @param content
     * @param size
     * @return
     */
    public Map<String, Float> getTermAndRank(String content, int size)
    {
        Map<String, Float> map = getTermAndRank(content);
        Map<String, Float> result = top(size, map);

        return result;
    }

    private Map<String, Float> top(int size, Map<String, Float> map)
    {
        Map<String, Float> result = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> entry : new MaxHeap<Map.Entry<String, Float>>(size, new Comparator<Map.Entry<String, Float>>()
        {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        }).addAll(map.entrySet()).toList())
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * 使用已�?分好的�?�?�计算rank
     *
     * @param termList
     * @return
     */
    public Map<String, Float> getTermAndRank(List<Term> termList)
    {
        List<String> wordList = new ArrayList<String>(termList.size());
        for (Term t : termList)
        {
            if (shouldInclude(t))
            {
                wordList.add(t.word);
            }
        }
//        System.out.println(wordList);
        Map<String, Set<String>> words = new TreeMap<String, Set<String>>();
        Queue<String> que = new LinkedList<String>();
        for (String w : wordList)
        {
            if (!words.containsKey(w))
            {
                words.put(w, new TreeSet<String>());
            }
            // �?�?�度O(n-1)
            if (que.size() >= 5)
            {
                que.poll();
            }
            for (String qWord : que)
            {
                if (w.equals(qWord))
                {
                    continue;
                }
                //既然是邻居,那么关系是相互的,�??历一�??�?��?�
                words.get(w).add(qWord);
                words.get(qWord).add(w);
            }
            que.offer(w);
        }
//        System.out.println(words);
        Map<String, Float> score = new HashMap<String, Float>();
        //�?�?�TF�?�设置�?值
        for (Map.Entry<String, Set<String>> entry : words.entrySet())
        {
            score.put(entry.getKey(), sigMoid(entry.getValue().size()));
        }
        for (int i = 0; i < max_iter; ++i)
        {
            Map<String, Float> m = new HashMap<String, Float>();
            float max_diff = 0;
            for (Map.Entry<String, Set<String>> entry : words.entrySet())
            {
                String key = entry.getKey();
                Set<String> value = entry.getValue();
                m.put(key, 1 - d);
                for (String element : value)
                {
                    int size = words.get(element).size();
                    if (key.equals(element) || size == 0) continue;
                    m.put(key, m.get(key) + d / size * (score.get(element) == null ? 0 : score.get(element)));
                }
                max_diff = Math.max(max_diff, Math.abs(m.get(key) - (score.get(key) == null ? 0 : score.get(key))));
            }
            score = m;
            if (max_diff <= min_diff) break;
        }

        return score;
    }

    /**
     * sigmoid函数
     *
     * @param value
     * @return
     */
    public static float sigMoid(float value)
    {
        return (float) (1d / (1d + Math.exp(-value)));
    }

    @Override
    public List<String> getKeywords(List<Term> termList, int size)
    {
        Set<Map.Entry<String, Float>> entrySet = top(size, getTermAndRank(termList)).entrySet();
        List<String> result = new ArrayList<String>(entrySet.size());
        for (Map.Entry<String, Float> entry : entrySet)
        {
            result.add(entry.getKey());
        }
        return result;
    }
}
