package com.hankcs.hanlp.mining.word;

import java.util.Map;
import java.util.TreeMap;

/**
 * æ??å?–å‡ºæ?¥çš„è¯?è¯­
 * @author hankcs
 */
public class WordInfo
{
    /**
     * å·¦é‚»æŽ¥å­—é›†å?ˆ
     */
    Map<Character, int[]> left;
    /**
     * å?³é‚»æŽ¥å­—é›†å?ˆ
     */
    Map<Character, int[]> right;
    /**
     * è¯?è¯­
     */
    public String text;
    /**
     * è¯?é¢‘
     */
    public int frequency;
    float p;
    float leftEntropy;
    float rightEntropy;
    /**
     * äº’ä¿¡æ?¯
     */
    public float aggregation;
    /**
     * ä¿¡æ?¯ç†µ
     */
    public float entropy;

    WordInfo(String text)
    {
        this.text = text;
        left = new TreeMap<Character, int[]>();
        right = new TreeMap<Character, int[]>();
        aggregation = Float.MAX_VALUE;
    }

    private static void increaseFrequency(char c, Map<Character, int[]> storage)
    {
        int[] freq = storage.get(c);
        if (freq == null)
        {
            freq = new int[]{1};
            storage.put(c, freq);
        }
        else
        {
            ++freq[0];
        }
    }

    private float computeEntropy(Map<Character, int[]> storage)
    {
        float sum = 0;
        for (Map.Entry<Character, int[]> entry : storage.entrySet())
        {
            float p = entry.getValue()[0] / (float) frequency;
            sum -= p * Math.log(p);
        }
        return sum;
    }

    void update(char left, char right)
    {
        ++frequency;
        increaseFrequency(left, this.left);
        increaseFrequency(right, this.right);
    }

    void computeProbabilityEntropy(int length)
    {
        p = frequency / (float) length;
        leftEntropy = computeEntropy(left);
        rightEntropy = computeEntropy(right);
        entropy = Math.min(leftEntropy, rightEntropy);
    }

    void computeAggregation(Map<String, WordInfo> word_cands)
    {
        if (text.length() == 1)
        {
            aggregation = (float) Math.sqrt(p);
            return;
        }
        for (int i = 1; i < text.length(); ++i)
        {
            aggregation = Math.min(aggregation,
                                   p / word_cands.get(text.substring(0, i)).p / word_cands.get(text.substring(i)).p);
        }
    }

    @Override
    public String toString()
    {
        return text;
    }
}
