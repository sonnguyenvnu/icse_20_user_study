package com.hope.shiro.filter;

import com.hope.model.beans.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

/**
 * 自定义过滤器
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调�?熊猫(https://aodeng.cc)
 * @微信公众�?�:低调�?熊猫
 * @create:2018-10-29 10:51
 **/
public class KickoutSessionControlFilter extends AccessControlFilter {
    private static final Logger log = LoggerFactory.getLogger(KickoutSessionControlFilter.class);

    /**
     * 踢出�?�到的地�?�
     **/
    private String kickoutUrl;

    /**
     * 踢出之�?登录的/之�?�登录的用户 默认踢出之�?登录的用户
     **/
    private Boolean kickoutAfter = false;

    /**
     * �?�一个账�?�最大会�?数 默认5
     **/
    private int maxSession = 5;

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            /***
             * 如果没有登录，进行之�?�的�?程
             */
            return true;
        }

        Session session = subject.getSession();
        SysUser sysuser = (SysUser) subject.getPrincipal();
        String username = sysuser.getUsername();
        Serializable sessionId = session.getId();

        //读�?�缓存 没有就存入
        Deque<Serializable> deque = cache.get(username);

        //如果此用户没有session队列，说明没有登录过，缓存中没有，new一个空队列
        if (null == deque) {
            deque = new LinkedList<Serializable>();
        }

        //如果队列里没有sessionId,且用户没有被踢出，加入队列
        if (!deque.contains(sessionId) && null == session.getAttribute("kickout")) {
            //将sessionId加入队列
            deque.push(sessionId);
            //将用户sessionId队列缓存
            cache.put(username, deque);
        }

        //如果队列中sessionId数�?超过最大会�?数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;

            //踢出之�?登录的/之�?�登录的用户 默认踢出之�?登录的用户
            if (kickoutAfter) {
                kickoutSessionId = deque.removeFirst();
                //踢出�?�更新缓存队列
                cache.put(username, deque);
            } else {
                kickoutSessionId = deque.removeLast();
                cache.put(username, deque);
            }
            try {
                //获�?�被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (null != kickoutSession) {
                    //设置会�?的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {
                //�?�对异常，我们选择忽略
            }
        }

        //如果被踢出了，直接退出，然�?��?定�?�到踢出�?�的地�?�
        if (null != (Boolean) session.getAttribute("kickout") && (Boolean) session.getAttribute("kickout") == true) {
            //会�?被踢出了
            try {
                //退出登录
                subject.logout();
            } catch (Exception e) {
                //�?�对异常，我们选择忽略
            }
            saveRequest(request);
            Map<String, String> resultMap = new HashMap<String, String>(2);
            //判断是�?是Ajax请求
            if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
                resultMap.put("user_status", "300");
                resultMap.put("message", "强制退出，您已�?在其他地方登录，请�?新登录!");
                //输出json串
                out(response, resultMap);
            } else {
                //�?定�?�
                WebUtils.issueRedirect(request, response, kickoutUrl);
            }
        }
        return false;
    }

    private void out(ServletResponse response, Map<String, String> map) throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println();
            out.close();
        } catch (Exception e) {
            //�?�对异常，我们选择忽略
            log.info("[KickoutSessionFilter.class 输出JSON异常，�?�以忽略。]-[{}]", new Date());
        }
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(Boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro_redis_cache");
    }
}
