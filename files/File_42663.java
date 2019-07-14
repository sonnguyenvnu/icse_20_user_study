/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.pay.trade.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <b>åŠŸèƒ½è¯´æ˜Ž:å•†æˆ·APIå·¥å…·ç±»
 * </b>
 * @author  Peter
 * <a href="http://www.roncoo.com">é¾™æžœå­¦é™¢(www.roncoo.com)</a>
 */
public class MerchantApiUtil {


    /**
     * èŽ·å?–å?‚æ•°ç­¾å??
     * @param paramMap  ç­¾å??å?‚æ•°
     * @param paySecret ç­¾å??å¯†é’¥
     * @return
     */
    public static String  getSign (Map<String , Object> paramMap , String paySecret){
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            Object value = m.getValue();
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))){
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());

        String argPreSign = stringBuffer.append("&paySecret=").append(paySecret).toString();
        String signStr = MD5Util.encode(argPreSign).toUpperCase();

        return signStr;
    }

    /**
     * èŽ·å?–å?‚æ•°æ‹¼æŽ¥ä¸²
     * @param paramMap
     * @return
     */
    public static String  getParamStr(Map<String , Object> paramMap){
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            Object value = m.getValue();
            if (value != null && StringUtils.isNotBlank(String.valueOf(value))){
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());

        return stringBuffer.toString();
    }

    /**
     * éªŒè¯?å•†æˆ·ç­¾å??
     * @param paramMap  ç­¾å??å?‚æ•°
     * @param paySecret ç­¾å??ç§?é’¥
     * @param signStr   åŽŸå§‹ç­¾å??å¯†æ–‡
     * @return
     */
    public static boolean isRightSign(Map<String , Object> paramMap , String paySecret ,String signStr){

        if (StringUtils.isBlank(signStr)){
            return false;
        }

        String sign = getSign(paramMap, paySecret);
        if(signStr.equals(sign)){
            return true;
        }else{
            return false;
        }
    }


}
