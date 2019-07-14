package com.hankcs.hanlp.utility;


import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.corpus.document.sentence.word.Word;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.hankcs.hanlp.dictionary.other.CharType.*;

/**
 * 文本工具类
 */
public class TextUtility
{

    public static int charType(char c)
    {
        return charType(String.valueOf(c));
    }

    /**
     * 判断字符类型
     * @param str
     * @return
     */
    public static int charType(String str)
    {
        if (str != null && str.length() > 0)
        {
            if (Predefine.CHINESE_NUMBERS.contains(str)) return CT_CNUM;
            byte[] b;
            try
            {
                b = str.getBytes("GBK");
            }
            catch (UnsupportedEncodingException e)
            {
                b = str.getBytes();
                e.printStackTrace();
            }
            byte b1 = b[0];
            byte b2 = b.length > 1 ? b[1] : 0;
            int ub1 = getUnsigned(b1);
            int ub2 = getUnsigned(b2);
            if (ub1 < 128)
            {
                if (ub1 < 32) return CT_DELIMITER; // NON PRINTABLE CHARACTERS
                if (' ' == b1) return CT_OTHER;
                if ('\n' == b1) return CT_DELIMITER;
                if ("*\"!,.?()[]{}+=/\\;:|".indexOf((char) b1) != -1)
                    return CT_DELIMITER;
                if ("0123456789".indexOf((char)b1) != -1)
                    return CT_NUM;
                return CT_SINGLE;
            }
            else if (ub1 == 162)
                return CT_INDEX;
            else if (ub1 == 163 && ub2 > 175 && ub2 < 186)
                return CT_NUM;
            else if (ub1 == 163
                    && (ub2 >= 193 && ub2 <= 218 || ub2 >= 225
                    && ub2 <= 250))
                return CT_LETTER;
            else if (ub1 == 161 || ub1 == 163)
                return CT_DELIMITER;
            else if (ub1 >= 176 && ub1 <= 247)
                return CT_CHINESE;

        }
        return CT_OTHER;
    }

    /**
     * 是�?�全是中文
     * @param str
     * @return
     */
    public static boolean isAllChinese(String str)
    {
        return str.matches("[\\u4E00-\\u9FA5]+");
    }
    /**
     * 是�?�全部�?是中文
     * @param sString
     * @return
     */
    public static boolean isAllNonChinese(byte[] sString)
    {
        int nLen = sString.length;
        int i = 0;

        while (i < nLen)
        {
            if (getUnsigned(sString[i]) < 248 && getUnsigned(sString[i]) > 175)
                return false;
            if (sString[i] < 0)
                i += 2;
            else
                i += 1;
        }
        return true;
    }

    /**
     * 是�?�全是�?�字节
     * @param str
     * @return
     */
    public static boolean isAllSingleByte(String str)
    {
        assert str != null;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) >128)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 把表示数字�?�义的字符串转�?整形
     *
     * @param str �?转�?�的字符串
     * @return 如果是有�?义的整数，则返回此整数值。�?�则，返回-1。
     */
    public static int cint(String str)
    {
        if (str != null)
            try
            {
                int i = new Integer(str).intValue();
                return i;
            }
            catch (NumberFormatException e)
            {

            }

        return -1;
    }
    /**
     * 是�?�全是数字
     * @param str
     * @return
     */
    public static boolean isAllNum(String str)
    {
        if (str == null)
            return false;

        int i = 0;
        /** 判断开头是�?�是+-之类的符�?� */
        if ("±+-＋�?—".indexOf(str.charAt(0)) != -1)
            i++;
        /** 如果是全角的�?１２３４５６７８９ 字符* */
        while (i < str.length() && "�?１２３４５６７８９".indexOf(str.charAt(i)) != -1)
            i++;
        // Get middle delimiter such as .
        if (i > 0 && i < str.length())
        {
            char ch = str.charAt(i);
            if ("·∶:，,．.�?/".indexOf(ch) != -1)
            {// 98．1％
                i++;
                while (i < str.length() && "�?１２３４５６７８９".indexOf(str.charAt(i)) != -1)
                    i++;
            }
        }
        if (i >= str.length())
            return true;

        /** 如果是�?�角的0123456789字符* */
        while (i < str.length() && "0123456789".indexOf(str.charAt(i)) != -1)
            i++;
        // Get middle delimiter such as .
        if (i > 0 && i < str.length())
        {
            char ch = str.charAt(i);
            if (',' == ch || '.' == ch || '/' == ch  || ':' == ch || "∶·，．�?".indexOf(ch) != -1)
            {// 98．1％
                i++;
                while (i < str.length() && "0123456789".indexOf(str.charAt(i)) != -1)
                    i++;
            }
        }

        if (i < str.length())
        {
            if ("百�?�万亿佰仟%％‰".indexOf(str.charAt(i)) != -1)
                i++;
        }
        if (i >= str.length())
            return true;

        return false;
    }

    /**
     * 是�?�全是�?�?�
     * @param sString
     * @return
     */
    public static boolean isAllIndex(byte[] sString)
    {
        int nLen = sString.length;
        int i = 0;

        while (i < nLen - 1 && getUnsigned(sString[i]) == 162)
        {
            i += 2;
        }
        if (i >= nLen)
            return true;
        while (i < nLen && (sString[i] > 'A' - 1 && sString[i] < 'Z' + 1)
                || (sString[i] > 'a' - 1 && sString[i] < 'z' + 1))
        {// single
            // byte
            // number
            // char
            i += 1;
        }

        if (i < nLen)
            return false;
        return true;

    }

    /**
     * 是�?�全为英文
     *
     * @param text
     * @return
     */
    public static boolean isAllLetter(String text)
    {
        for (int i = 0; i < text.length(); ++i)
        {
            char c = text.charAt(i);
            if ((((c < 'a' || c > 'z')) && ((c < 'A' || c > 'Z'))))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * 是�?�全为英文或字�?
     *
     * @param text
     * @return
     */
    public static boolean isAllLetterOrNum(String text)
    {
        for (int i = 0; i < text.length(); ++i)
        {
            char c = text.charAt(i);
            if ((((c < 'a' || c > 'z')) && ((c < 'A' || c > 'Z')) && ((c < '0' || c > '9'))))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * 是�?�全是分隔符
     * @param sString
     * @return
     */
    public static boolean isAllDelimiter(byte[] sString)
    {
        int nLen = sString.length;
        int i = 0;

        while (i < nLen - 1 && (getUnsigned(sString[i]) == 161 || getUnsigned(sString[i]) == 163))
        {
            i += 2;
        }
        if (i < nLen)
            return false;
        return true;
    }

    /**
     * 是�?�全是中国数字
     * @param word
     * @return
     */
    public static boolean isAllChineseNum(String word)
    {// 百分之五点六的人早上八点�??八分起床

        String chineseNum = "零○一二两三四五六七八�?�??廿百�?�万亿壹贰�??肆�?陆柒�?�玖拾佰仟∶·．�?点";//
        String prefix = "几数上第";
        String surfix = "几多余�?��?�?";
        boolean round = false;

        if (word == null)
            return false;

        char[] temp = word.toCharArray();
        for (int i = 0; i < temp.length; i++)
        {
            if (word.startsWith("分之", i))// 百分之五
            {
                i += 1;
                continue;
            }
            char tchar = temp[i];
            if (i == 0 && prefix.indexOf(tchar) != -1)
            {
                round = true;
            }
            else if (i == temp.length-1 && !round && surfix.indexOf(tchar) != -1)
            {
                round = true;
            }
            else if (chineseNum.indexOf(tchar) == -1)
                return false;
        }
        return true;
    }


    /**
     * 得到字符集的字符在字符串中出现的次数
     *
     * @param charSet
     * @param word
     * @return
     */
    public static int getCharCount(String charSet, String word)
    {
        int nCount = 0;

        if (word != null)
        {
            String temp = word + " ";
            for (int i = 0; i < word.length(); i++)
            {
                String s = temp.substring(i, i + 1);
                if (charSet.indexOf(s) != -1)
                    nCount++;
            }
        }

        return nCount;
    }


    /**
     * 获�?�字节对应的无符�?�整型数
     *
     * @param b
     * @return
     */
    public static int getUnsigned(byte b)
    {
        if (b > 0)
            return (int) b;
        else
            return (b & 0x7F + 128);
    }

    /**
     * 判断字符串是�?�是年份
     *
     * @param snum
     * @return
     */
    public static boolean isYearTime(String snum)
    {
        if (snum != null)
        {
            int len = snum.length();
            String first = snum.substring(0, 1);

            // 1992年, 98年,06年
            if (isAllSingleByte(snum)
                    && (len == 4 || len == 2 && (cint(first) > 4 || cint(first) == 0)))
                return true;
            if (isAllNum(snum) && (len >= 3 || len == 2 && "�?５６７８９".indexOf(first) != -1))
                return true;
            if (getCharCount("零○一二三四五六七八�?壹贰�??肆�?陆柒�?�玖", snum) == len && len >= 2)
                return true;
            if (len == 4 && getCharCount("�?�仟零○", snum) == 2)// 二仟零二年
                return true;
            if (len == 1 && getCharCount("�?�仟", snum) == 1)
                return true;
            if (len == 2 && getCharCount("甲乙丙�?戊己庚辛壬癸", snum) == 1
                    && getCharCount("�?丑寅�?�辰巳�?�未申酉戌亥", snum.substring(1)) == 1)
                return true;
        }
        return false;
    }

    /**
     * 判断一个字符串的所有字符是�?�在�?�一个字符串集�?�中
     *
     * @param aggr 字符串集�?�
     * @param str  需�?判断的字符串
     * @return
     */
    public static boolean isInAggregate(String aggr, String str)
    {
        if (aggr != null && str != null)
        {
            str += "1";
            for (int i = 0; i < str.length(); i++)
            {
                String s = str.substring(i, i + 1);
                if (aggr.indexOf(s) == -1)
                    return false;
            }
            return true;
        }

        return false;
    }

    /**
     * 判断该字符串是�?�是�?�角字符
     *
     * @param str
     * @return
     */
    public static boolean isDBCCase(String str)
    {
        if (str != null)
        {
            str += " ";
            for (int i = 0; i < str.length(); i++)
            {
                String s = str.substring(i, i + 1);
                int length = 0;
                try
                {
                    length = s.getBytes("GBK").length;
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                    length = s.getBytes().length;
                }
                if (length != 1)
                    return false;
            }

            return true;
        }

        return false;
    }

    /**
     * 判断该字符串是�?�是全角字符
     *
     * @param str
     * @return
     */
    public static boolean isSBCCase(String str)
    {
        if (str != null)
        {
            str += " ";
            for (int i = 0; i < str.length(); i++)
            {
                String s = str.substring(i, i + 1);
                int length = 0;
                try
                {
                    length = s.getBytes("GBK").length;
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                    length = s.getBytes().length;
                }
                if (length != 2)
                    return false;
            }

            return true;
        }

        return false;
    }

    /**
     * 判断是�?�是一个连字符（分隔符）
     *
     * @param str
     * @return
     */
    public static boolean isDelimiter(String str)
    {
        if (str != null && ("-".equals(str) || "�?".equals(str)))
            return true;
        else
            return false;
    }

    public static boolean isUnknownWord(String word)
    {
        if (word != null && word.indexOf("未##") == 0)
            return true;
        else
            return false;
    }

    /**
     * 防止频率为0�?�生除零错误
     *
     * @param frequency
     * @return
     */
    public static double nonZero(double frequency)
    {
        if (frequency == 0) return 1e-3;

        return frequency;
    }

    /**
     * 转�?�long型为char数组
     *
     * @param x
     */
    public static char[] long2char(long x)
    {
        char[] c = new char[4];
        c[0] = (char) (x >> 48);
        c[1] = (char) (x >> 32);
        c[2] = (char) (x >> 16);
        c[3] = (char) (x);
        return c;
    }

    /**
     * 转�?�long类型为string
     *
     * @param x
     * @return
     */
    public static String long2String(long x)
    {
        char[] cArray = long2char(x);
        StringBuilder sbResult = new StringBuilder(cArray.length);
        for (char c : cArray)
        {
            sbResult.append(c);
        }
        return sbResult.toString();
    }

    /**
     * 将异常转为字符串
     *
     * @param e
     * @return
     */
    public static String exceptionToString(Exception e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * 判断�?个字符是�?�为汉字
     *
     * @param c 需�?判断的字符
     * @return 是汉字返回true，�?�则返回false
     */
    public static boolean isChinese(char c)
    {
        String regex = "[\\u4e00-\\u9fa5]";
        return String.valueOf(c).matches(regex);
    }

    /**
     * 统计 keyword 在 srcText 中的出现次数
     *
     * @param keyword
     * @param srcText
     * @return
     */
    public static int count(String keyword, String srcText)
    {
        int count = 0;
        int leng = srcText.length();
        int j = 0;
        for (int i = 0; i < leng; i++)
        {
            if (srcText.charAt(i) == keyword.charAt(j))
            {
                j++;
                if (j == keyword.length())
                {
                    count++;
                    j = 0;
                }
            }
            else
            {
                i = i - j;// should rollback when not match
                j = 0;
            }
        }

        return count;
    }

    /**
     * 简�?�好用的写String方�?
     *
     * @param s
     * @param out
     * @throws IOException
     */
    public static void writeString(String s, DataOutputStream out) throws IOException
    {
        out.writeInt(s.length());
        for (char c : s.toCharArray())
        {
            out.writeChar(c);
        }
    }

    /**
     * 判断字符串是�?�为空（null和空格）
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs)
    {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(cs.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static String join(String delimiter, Collection<String> stringCollection)
    {
        StringBuilder sb = new StringBuilder(stringCollection.size() * (16 + delimiter.length()));
        for (String str : stringCollection)
        {
            sb.append(str).append(delimiter);
        }

        return sb.toString();
    }

    public static String combine(String... termArray)
    {
        StringBuilder sbSentence = new StringBuilder();
        for (String word : termArray)
        {
            sbSentence.append(word);
        }
        return sbSentence.toString();
    }

    public static String join(Iterable<? extends CharSequence> s, String delimiter)
    {
        Iterator<? extends CharSequence> iter = s.iterator();
        if (!iter.hasNext()) return "";
        StringBuilder buffer = new StringBuilder(iter.next());
        while (iter.hasNext()) buffer.append(delimiter).append(iter.next());
        return buffer.toString();
    }

    public static String combine(Sentence sentence)
    {
        StringBuilder sb = new StringBuilder(sentence.wordList.size() * 3);
        for (IWord word : sentence.wordList)
        {
            sb.append(word.getValue());
        }

        return sb.toString();
    }

    public static String combine(List<Word> wordList)
    {
        StringBuilder sb = new StringBuilder(wordList.size() * 3);
        for (IWord word : wordList)
        {
            sb.append(word.getValue());
        }

        return sb.toString();
    }
}
