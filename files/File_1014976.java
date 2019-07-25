package com.kakarote.crm9.utils;

import cn.hutool.core.date.DateUtil;
import com.jfinal.kit.PropKit;
import com.kakarote.crm9.common.config.JfinalConfig;
import com.kakarote.crm9.common.constant.BaseConstant;
import com.kakarote.crm9.erp.admin.entity.AdminUser;
import com.jfinal.kit.Prop;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Date;

public class BaseUtil {
    private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    /**
     *
     * èŽ·å?–å½“å‰?ç³»ç»Ÿæ˜¯å¼€å?‘å¼€å§‹æ­£å¼?
     * @return trueä»£è¡¨ä¸ºçœŸ
     */
    public static boolean isDevelop() {
        return JfinalConfig.prop.getBoolean("jfinal.devMode",Boolean.TRUE);
    }

    /**
     * èŽ·å?–å½“å‰?æ˜¯å?¦æ˜¯windowsç³»ç»Ÿ
     * @return trueä»£è¡¨ä¸ºçœŸ
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    /**
     * ç­¾å??æ•°æ?®
     *
     * @param key  key
     * @param salt ç›?
     * @return åŠ å¯†å?Žçš„å­—ç¬¦ä¸²
     */
    public static String sign(String key, String salt) {
        return DigestUtils.md5Hex((key + "erp" + salt).getBytes());
    }

    /**
     * éªŒè¯?ç­¾å??æ˜¯å?¦æ­£ç¡®
     *
     * @param key  key
     * @param salt ç›?
     * @param sign ç­¾å??
     * @return æ˜¯å?¦æ­£ç¡® trueä¸ºæ­£ç¡®
     */
    public static boolean verify(String key, String salt, String sign) {
        return sign.equals(sign(key, salt));
    }

    /**
     * èŽ·å?–å½“å‰?å¹´æœˆçš„å­—ç¬¦ä¸²
     *
     * @return yyyyMMdd
     */
    public static String getDate() {
        return DateUtil.format(new Date(), "yyyyMMdd");
    }

    public static String getIpAddress() {
        Prop prop = PropKit.use("config/undertow.txt");
        try {
            if (isDevelop()) {
                return "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + prop.get("undertow.port", "8080") + "/";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpServletRequest request=getRequest();
        /**
         * TODO nginxå??å?‘ä»£ç?†ä¸‹æ‰‹åŠ¨å¢žåŠ ä¸€ä¸ªè¯·æ±‚å¤´ proxy_set_header proxy_url "ä»£ç?†æ˜ å°„è·¯å¾„";
         * å¦‚ location /api/ {
         *     proxy_set_header proxy_url "api"
         *     proxy_redirect off;
         * 	   proxy_set_header Host $host:$server_port;
         *     proxy_set_header X-Real-IP $remote_addr;
         * 	   proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
         * 	   proxy_set_header X-Forwarded-Proto  $scheme;
         * 	   proxy_connect_timeout 60;
         * 	   proxy_send_timeout 120;
         * 	   proxy_read_timeout 120;
         *     proxy_pass http://127.0.0.1:8080/;
         *    }
         */
        String proxy=request.getHeader("proxy_url")!=null?"/"+request.getHeader("proxy_url"):"";
        return "http://" + request.getServerName()+":"+ request.getServerPort()+ request.getContextPath()+proxy+"/";
    }
    public static String getLoginAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

    public static String getLoginAddress() {
        return getLoginAddress(getRequest());
    }

    public static void setRequest(HttpServletRequest request) {
        threadLocal.set(request);
    }

    public static HttpServletRequest getRequest() {
        return threadLocal.get();
    }


    public static AdminUser getUser() {
        return Redis.use().get(getToken());
    }

    public static Long getUserId(){
        return getUser().getUserId();
    }

    public static void removeThreadLocal(){
        threadLocal.remove();
    }

    public static String getToken(){
        return getToken(getRequest());
    }

    public static String getToken(HttpServletRequest request){
        return request.getHeader("Admin-Token") != null ? request.getHeader("Admin-Token") : getCookieValue(request,"Admin-Token");
    }

    public static String getCookieValue(HttpServletRequest request,String name) {
        String cookieValue= "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        return cookieValue;
    }
}
