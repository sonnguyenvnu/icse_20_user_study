package com.sohu.tv.cachecloud.client.basic.util;

import java.util.List;

/**
 * 简�?�字符串类
 * �?少common-lang�?赖
 * 
 * @author leifu
 * @Date 2015年2月10日
 * @Time 上�?�9:47:29
 */
public class StringUtil {
    
    /**
     * 列表转为字符串(用逗�?�分隔)
     * @param list
     * @param separator
     * @return
     */
    public static String simpleListJoinToStrWithSeparator(List<String> list, String separator) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder finalEmailStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                finalEmailStr.append(separator);
            }
            finalEmailStr.append(list.get(i));
        }
        return finalEmailStr.toString();
    }

    /**
     * 判断字符串是�?�为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
