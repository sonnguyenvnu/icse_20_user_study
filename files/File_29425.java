package com.sohu.cache.util;

import java.util.Arrays;

import org.springframework.util.DigestUtils;

/**
 * appkeyè®¡ç®—å·¥å…·
 * 
 * @author leifu
 * @Date 2016-7-9
 * @Time ä¸‹å?ˆ9:23:59
 */
public class AppKeyUtil {

    public static String genSecretKey(long appId) {
        StringBuilder key = new StringBuilder();
        // ç›¸å…³å?‚æ•°
        key.append(appId).append(ConstUtils.APP_SECRET_BASE_KEY);
        // è½¬æˆ?char[]
        char[] strs = key.toString().toCharArray();
        // æŽ’åº?
        Arrays.sort(strs);
        // md5
        return MD5(new String(strs));
    }

    private static String MD5(String s) {
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }
    
    public static void main(String[] args) {
        System.out.println(genSecretKey(10010));
    }

}
