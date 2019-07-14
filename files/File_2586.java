/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/21 18:59</create-date>
 *
 * <copyright file="MDAGMap.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.collection.MDAG;

import com.hankcs.hanlp.utility.ByteUtil;

import java.util.*;

/**
 * æœ€å¥½ä¸?è¦?æŠŠMDAGå½“mapç”¨ï¼ŒçŽ°åœ¨çš„å®žçŽ°åœ¨keyå?Žé?¢æ”¾ä¸€ä¸ªintï¼Œå¯¼è‡´å?³è¯­è¨€å…¨éƒ¨ä¸?å?Œï¼Œé€€åŒ–ä¸ºbintrie
 * @author hankcs
 */
public class MDAGMap<V> extends AbstractMap<String, V>
{
    ArrayList<V> valueList = new ArrayList<V>();
    MDAGForMap mdag = new MDAGForMap();

    @Override
    public V put(String key, V value)
    {
        V origin = get(key);
        if (origin == null)
        {
            valueList.add(value);
            char[] twoChar = ByteUtil.convertIntToTwoChar(valueList.size() - 1);
            mdag.addString(key + MDAGForMap.DELIMITER + twoChar[0] + twoChar[1]);
        }
        return origin;
    }

    @Override
    public V get(Object key)
    {
        int valueIndex = mdag.getValueIndex(key.toString());
        if (valueIndex != -1)
        {
            return valueList.get(valueIndex);
        }
        return null;
    }

    public V get(String key)
    {
        int valueIndex = mdag.getValueIndex(key);
        if (valueIndex != -1)
        {
            return valueList.get(valueIndex);
        }
        return null;
    }

    @Override
    public Set<Entry<String, V>> entrySet()
    {
        HashSet<String> keySet = mdag.getAllStrings();
        return null;
    }

    @Override
    public Set<String> keySet()
    {
        HashSet<String> stringSet = mdag.getAllStrings();
        LinkedHashSet<String> keySet = new LinkedHashSet<String>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            keySet.add(key.substring(0, key.length() - 3));
        }
        return keySet;
    }

    /**
     * å‰?ç¼€æŸ¥è¯¢
     * @param key
     * @param begin
     * @return
     */
    public LinkedList<Entry<String, V>> commonPrefixSearchWithValue(char[] key, int begin)
    {
        LinkedList<Entry<String, Integer>> valueIndex = mdag.commonPrefixSearchWithValueIndex(key, begin);
        LinkedList<Entry<String, V>> entryList = new LinkedList<Entry<String, V>>();
        for (Entry<String, Integer> entry : valueIndex)
        {
            entryList.add(new SimpleEntry<String, V>(entry.getKey(), valueList.get(entry.getValue())));
        }

        return entryList;
    }

    /**
     * å‰?ç¼€æŸ¥è¯¢
     * @param key
     * @return
     */
    public LinkedList<Entry<String, V>> commonPrefixSearchWithValue(String key)
    {
        return commonPrefixSearchWithValue(key.toCharArray(), 0);
    }

    /**
     * è¿›ä¸€æ­¥é™?ä½Žå†…å­˜ï¼Œæ??é«˜æŸ¥è¯¢é€Ÿåº¦<br>
     *     å‰¯ä½œç”¨æ˜¯ä¸‹æ¬¡æ?’å…¥é€Ÿåº¦ä¼šå?˜æ…¢
     */
    public void simplify()
    {
        mdag.simplify();
    }

    public void unSimplify()
    {
        mdag.unSimplify();
    }

    static class MDAGForMap extends MDAG
    {
        static final char DELIMITER = Character.MIN_VALUE;

        public int getValueIndex(String key)
        {
            if (sourceNode != null)      //if the MDAG hasn't been simplified
            {
                MDAGNode currentNode = sourceNode.transition(key.toCharArray());
                if (currentNode == null) return -1;
                return getValueIndex(currentNode);

            }
            else
            {
                SimpleMDAGNode currentNode = simplifiedSourceNode.transition(mdagDataArray, key.toCharArray());
                if (currentNode == null) return -1;
                return getValueIndex(currentNode);
            }

        }

        private int getValueIndex(SimpleMDAGNode currentNode)
        {
            SimpleMDAGNode targetNode = currentNode.transition(mdagDataArray, DELIMITER);
            if (targetNode == null) return -1;
            // æŽ¥ä¸‹æ?¥åº”è¯¥æ˜¯ä¸€æ?¡å?•é“¾è·¯
            int transitionSetBeginIndex = targetNode.getTransitionSetBeginIndex();
            assert targetNode.getOutgoingTransitionSetSize() == 1 : "ä¸?æ˜¯å?•é“¾ï¼?";
            char high = mdagDataArray[transitionSetBeginIndex].getLetter();
            targetNode = targetNode.transition(mdagDataArray, high);
            assert targetNode.getOutgoingTransitionSetSize() == 1 : "ä¸?æ˜¯å?•é“¾ï¼?";
            transitionSetBeginIndex = targetNode.getTransitionSetBeginIndex();
            char low = mdagDataArray[transitionSetBeginIndex].getLetter();
            return ByteUtil.convertTwoCharToInt(high, low);
        }

        private int getValueIndex(MDAGNode currentNode)
        {
            MDAGNode targetNode = currentNode.transition(DELIMITER);
            if (targetNode == null) return -1;
            // æŽ¥ä¸‹æ?¥åº”è¯¥æ˜¯ä¸€æ?¡å?•é“¾è·¯
            TreeMap<Character, MDAGNode> outgoingTransitions = targetNode.getOutgoingTransitions();
            assert outgoingTransitions.size() == 1 : "ä¸?æ˜¯å?•é“¾ï¼?";
            char high = outgoingTransitions.keySet().iterator().next();
            targetNode = targetNode.transition(high);
            outgoingTransitions = targetNode.getOutgoingTransitions();
            assert outgoingTransitions.size() == 1 : "ä¸?æ˜¯å?•é“¾ï¼?";
            char low = outgoingTransitions.keySet().iterator().next();
            return ByteUtil.convertTwoCharToInt(high, low);
        }

        public LinkedList<Entry<String, Integer>> commonPrefixSearchWithValueIndex(char[] key, int begin)
        {
            LinkedList<Map.Entry<String, Integer>> result = new LinkedList<Map.Entry<String, Integer>>();
            if (sourceNode != null)
            {
                int charCount = key.length;
                MDAGNode currentNode = sourceNode;
                for (int i = 0; i < charCount; ++i)
                {
                    currentNode = currentNode.transition(key[begin + i]);
                    if (currentNode == null) break;
                    {
                        int index = getValueIndex(currentNode);
                        if (index != -1) result.add(new SimpleEntry<String, Integer>(new String(key, begin, i + 1), index));
                    }
                }
            }
            else
            {
                int charCount = key.length;
                SimpleMDAGNode currentNode = simplifiedSourceNode;
                for (int i = 0; i < charCount; ++i)
                {
                    currentNode = currentNode.transition(mdagDataArray, key[begin + i]);
                    if (currentNode == null) break;
                    {
                        int index = getValueIndex(currentNode);
                        if (index != -1) result.add(new SimpleEntry<String, Integer>(new String(key, begin, i + 1), index));
                    }
                }
            }

            return result;
        }
    }
}
