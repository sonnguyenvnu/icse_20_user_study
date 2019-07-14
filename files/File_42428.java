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
package com.roncoo.pay.common.core.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * String字符串工具类.
 * @company：广州领课网络科技有�?公�?�（龙果学院 www.roncoo.com）.
 * @author zenghao
 */
public final class StringUtil {

    private static final Log LOG = LogFactory.getLog(StringUtil.class);

    /**
     * �?有构造方法,将该工具类设为�?�例模�?.
     */
    private StringUtil() {
    }

    /**
     * 函数功能说明 ： 判断字符串是�?�为空 . 修改者�??字： 修改日期： 修改内容：
     * 
     * @�?�数： @param str
     * @�?�数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 函数功能说明 ： 判断对象数组是�?�为空. 修改者�??字： 修改日期： 修改内容：
     * 
     * @�?�数： @param obj
     * @�?�数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object[] obj) {
        return null == obj || 0 == obj.length;
    }

    /**
     * 函数功能说明 ： 判断对象是�?�为空. 修改者�??字： 修改日期： 修改内容：
     * 
     * @�?�数： @param obj
     * @�?�数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number) ? false : false;
    }

    /**
     * 函数功能说明 ： 判断集�?�是�?�为空. 修改者�??字： 修改日期： 修改内容：
     * 
     * @�?�数： @param obj
     * @�?�数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(List<?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 判断Map集�?�是�?�为空. 修改者�??字： 修改日期： 修改内容：
     * 
     * @�?�数： @param obj
     * @�?�数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 获得文件�??的�?�缀�??. 修改者�??字： 修改日期： 修改内容：
     * 
     * @�?�数： @param fileName
     * @�?�数： @return
     * @return String
     * @throws
     */
    public static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获�?�去掉横线的长度为32的UUID串.
     * 
     * @author WuShuicheng.
     * @return uuid.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获�?�带横线的长度为36的UUID串.
     * 
     * @author WuShuicheng.
     * @return uuid.
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 验�?一个字符串是�?�完全由纯数字组�?的字符串，当字符串为空时也返回false.
     * 
     * @author WuShuicheng .
     * @param str
     *            �?判断的字符串 .
     * @return true or false .
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            return str.matches("\\d*");
        }
    }

    /**
     * 计算采用utf-8编�?方�?时字符串所�?�字节数
     * 
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                // 汉字采用utf-8编�?时�?�3个字节
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                LOG.error(e);
            }
        }
        return size;
    }

    /**
     * 函数功能说明 ： 截�?�字符串拼接in查询�?�数. 修改者�??字： 修改日期： 修改内容：
     * 
     * @�?�数： @param ids
     * @�?�数： @return
     * @return String
     * @throws
     */
    public static List<String> getInParam(String param) {
        boolean flag = param.contains(",");
        List<String> list = new ArrayList<String>();
        if (flag) {
            list = Arrays.asList(param.split(","));
        } else {
            list.add(param);
        }
        return list;
    }

    /**
     * 判断对象是�?�为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        if (obj != null && obj.toString() != null && !"".equals(obj.toString().trim())) {
            return true;
        } else {
            return false;
        }
    }

}
