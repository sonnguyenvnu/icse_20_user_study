package com.hankcs.hanlp.utility;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;

import java.util.LinkedList;
import java.util.List;

/**
 * 文本断�?�
 */
public class SentencesUtil
{
    /**
     * 将文本切割为最细�?的�?��?（逗�?�也视作分隔符）
     *
     * @param content
     * @return
     */
    public static List<String> toSentenceList(String content)
    {
        return toSentenceList(content.toCharArray(), true);
    }

    /**
     * 文本分�?�
     *
     * @param content  文本
     * @param shortest 是�?�切割为最细的�?��?（将逗�?�也视作分隔符）
     * @return
     */
    public static List<String> toSentenceList(String content, boolean shortest)
    {
        return toSentenceList(content.toCharArray(), shortest);
    }

    public static List<String> toSentenceList(char[] chars)
    {
        return toSentenceList(chars, true);
    }

    public static List<String> toSentenceList(char[] chars, boolean shortest)
    {

        StringBuilder sb = new StringBuilder();

        List<String> sentences = new LinkedList<String>();

        for (int i = 0; i < chars.length; ++i)
        {
            if (sb.length() == 0 && (Character.isWhitespace(chars[i]) || chars[i] == ' '))
            {
                continue;
            }

            sb.append(chars[i]);
            switch (chars[i])
            {
                case '.':
                    if (i < chars.length - 1 && chars[i + 1] > 128)
                    {
                        insertIntoList(sb, sentences);
                        sb = new StringBuilder();
                    }
                    break;
                case '…':
                {
                    if (i < chars.length - 1 && chars[i + 1] == '…')
                    {
                        sb.append('…');
                        ++i;
                        insertIntoList(sb, sentences);
                        sb = new StringBuilder();
                    }
                }
                break;
                case '，':
                case ',':
                case ';':
                case '；':
                    if (!shortest)
                    {
                        continue;
                    }
                case ' ':
                case '	':
                case ' ':
                case '。':
                case '!':
                case '�?':
                case '?':
                case '？':
                case '\n':
                case '\r':
                    insertIntoList(sb, sentences);
                    sb = new StringBuilder();
                    break;
            }
        }

        if (sb.length() > 0)
        {
            insertIntoList(sb, sentences);
        }

        return sentences;
    }

    private static void insertIntoList(StringBuilder sb, List<String> sentences)
    {
        String content = sb.toString().trim();
        if (content.length() > 0)
        {
            sentences.add(content);
        }
    }

    /**
     * �?��?中是�?��?�有�?性
     *
     * @param sentence
     * @param nature
     * @return
     */
    public static boolean hasNature(List<Term> sentence, Nature nature)
    {
        for (Term term : sentence)
        {
            if (term.nature == nature)
            {
                return true;
            }
        }

        return false;
    }
}
