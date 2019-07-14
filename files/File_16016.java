/*
 *  Copyright 2019 http://www.hswebframework.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package org.hswebframework.web;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Web常用工具集，用于获�?�当�?登录用户，请求信�?�等
 *
 * @since 3.0
 */
public class WebUtil {

    /**
     * 将对象转为http请求�?�数:
     * <pre>
     *     {name:"test",org:[1,2,3]} => {"name":"test","org[0]":1,"org[1]":2,"org[2]":3}
     * </pre>
     *
     * @param object
     * @return
     */
    public static Map<String, String> objectToHttpParameters(Object object) {
        return new HttpParameterConverter(object).convert();
    }

    public static Map<String,String> queryStringToMap(String queryString,String charset){
        try {
            Map<String,String> map = new HashMap<>();

            String[] decode = URLDecoder.decode(queryString,charset).split("&");
            for (String keyValue : decode) {
                String[] kv = keyValue.split("[=]",2);
                map.put(kv[0],kv.length>1?kv[1]:"");
            }
            return map;
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
    /**
     * �?试获�?�当�?请求的HttpServletRequest实例
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = String.valueOf(enumeration.nextElement());
            parameters.put(name, request.getParameter(name));
        }
        return parameters;
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    static final String[] ipHeaders = {
            "X-Forwarded-For",
            "X-Real-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP"
    };

    /**
     * 获�?�请求客户端的真实ip地�?�
     *
     * @param request 请求对象
     * @return ip地�?�
     */
    public static String getIpAddr(HttpServletRequest request) {
        for (String ipHeader : ipHeaders) {
            String ip = request.getHeader(ipHeader);
            if (!StringUtils.isEmpty(ip) && !ip.contains("unknown")) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * web应用�?对路径
     *
     * @param request 请求对象
     * @return �?对路径
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return basePath;
    }

}
