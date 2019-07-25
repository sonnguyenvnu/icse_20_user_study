package com.kakarote.crm9.utils;

import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sort {
    /**
     * 字�?大�?写标识 capital:大写
     */
    private static final String Letter_flag_capital = "capital";

    /**
     * 排�?的方法
     * @param list 需�?排�?的List集�?�
     * @return
     */
    public Map<String, List<Record>> sort(List<Record> list){
        Map<String,List<Record>> map = new HashMap<>();
        List<Record> arraylist = new ArrayList<>();
        String[] alphatableb =
                {
                        "A", "B", "C", "D", "E", "F", "G", "H", "I",
                        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
                };
        for(String a:alphatableb){
            for(int i=0;i<list.size();i++){//为了排�?都返回大写字�?
                if(a.equals(String2AlphaFirst(list.get(i).getStr("realname"),Letter_flag_capital))){
                    arraylist.add(list.get(i));
                }
            }
            if (arraylist.size() != 0){
                map.put(a,arraylist);
            }

            arraylist=new ArrayList<Record>();
        }
        return map;
    }

    //字�?Z使用了两个标签，这里有２７个值
    //i, u, v都�?�?�声�?, 跟�?�?�?�的字�?
    private char[] chartable =
            {
                    '啊', '芭', '擦', '�?�', '蛾', '�?�', '噶', '哈', '哈',
                    '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然',
                    '撒', '塌', '塌', '塌', '挖', '昔', '压', '�?', '座'
            };

    // 大写字�?匹�?数组
    private char[] alphatableb =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                    'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            };

    // �?写字�?匹�?数组
    private char[] alphatables =
            {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            };

    private int[] table = new int[27];  //�?始化
    {
        for (int i = 0; i < 27; ++i) {
            table[i] = gbValue(chartable[i]);
        }
    }

    /**
     * 主函数,输入字符,得到他的声�?,英文字�?返回对应的大�?写字�?,英文字�?返回对应的大�?写字�?
     * @param ch 字符
     * @param type 大�?写类型标识
     * @return
     */
    public char Char2Alpha(char ch,String type) {
        if (ch >= 'a' && ch <= 'z')
        { return (char) (ch - 'a' + 'A');}//为了按字�?排�?先返回大写字�?

        if (ch >= 'A' && ch <= 'Z')
        { return ch;}
        int gb = gbValue(ch);
        if (gb < table[0])
        {  return '0';}

        int i;
        for (i = 0; i < 26; ++i) {
            if (match(i, gb))
            { break;}
        }

        if (i >= 26){
            return '0';}
        else{
            if(Letter_flag_capital.equals(type)){//大写
                return alphatableb[i];
            }else{//�?写
                return alphatables[i];
            }
        }
    }

    /**
     * 根�?�一个包�?�汉字的字符串返回一个汉字拼音首字�?的字符串
     * @param SourceStr 目标字符串
     * @param type      大�?写类型
     * @return
     */
    public String String2Alpha(String SourceStr,String type) {
        String Result = "";
        int StrLength = SourceStr.length();
        int i;
        try {
            for (i = 0; i < StrLength; i++) {
                Result += Char2Alpha(SourceStr.charAt(i),type);
            }
        } catch (Exception e) {
            Result = "";
        }
        return Result;
    }

    /**
     * 根�?�一个包�?�汉字的字符串返回第一个汉字拼音首字�?的字符串
     * @param SourceStr 目标字符串
     * @param type      大�?写类型
     * @return
     */
    public String String2AlphaFirst(String SourceStr,String type) {
        String Result = "";
        try {
            Result += Char2Alpha(SourceStr.charAt(0),type);
        } catch (Exception e) {
            Result = "";
        }
        return Result;
    }
    private boolean match(int i, int gb) {
        if (gb < table[i])
        { return false;}
        int j = i + 1;

        //字�?Z使用了两个标签
        while (j < 26 && (table[j] == table[i]))
        { ++j;}
        if (j == 26)
        {return gb <= table[j];}
        else
        {return gb < table[j];}
    }

    /**
     * �?�出汉字的编�?
     * @param ch
     * @return
     */
    private int gbValue(char ch) {
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes("GBK");
            if (bytes.length < 2)
            { return 0;}
            return (bytes[0] << 8 & 0xff00) + (bytes[1] &
                    0xff);
        } catch (Exception e) {
            return 0;
        }
    }

}
