package org.nutz.mvc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionChain;
import org.nutz.mvc.ActionContext;

/**
 * 根�?� HTTP 请求的方法 (GET|POST|PUT|DELETE) �?�调用�?应的动作链
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class ActionInvoker {

    private static final Log log = Logs.get();
    
    private ActionChain defaultChain;

    private Map<String, ActionChain> chainMap;

    public ActionInvoker() {
        chainMap = new HashMap<String, ActionChain>();
    }

    /**
     * 增加 ActionChain
     * 
     * @param httpMethod
     *            HTTP 的请求方法 (GET|POST|PUT|DELETE),如果为空，则会抛错
     * @param chain
     *            动作链
     */
    public void addChain(String httpMethod, ActionChain chain) {
        if (Strings.isBlank(httpMethod))
            throw Lang.makeThrow("chain need a valid HTTP Method, but is is '%s'", httpMethod);
        ActionChain old = chainMap.put(httpMethod.toUpperCase(), chain);
        if (old != null) {
            log.warnf("Duplicate @At mapping with same HttpMethod");
        }
    }

    public void setDefaultChain(ActionChain defaultChain) {
        this.defaultChain = defaultChain;
    }

    /**
     * 根�?�动作链上下文对象，调用一个相应的动作链
     * 
     * @param ac
     *            动作链上下文
     * @return true- �?功的找到一个动作链并执行。 false- 没有找到动作链
     */
    public boolean invoke(ActionContext ac) {
        ActionChain chain = getActionChain(ac);
        if (chain == null) {
            if (log.isDebugEnabled())
                log.debugf("Not chain for req (path=%s, method=%s)", ac.getPath(), ac.getRequest().getMethod());
            return false;
        }
        chain.doChain(ac);
        return ac.getBoolean(ActionContext.AC_DONE, true);
    }

    public ActionChain getActionChain(ActionContext ac) {
        String httpMethod = "";
        if (!chainMap.isEmpty()) {
            HttpServletRequest req = ac.getRequest();
            httpMethod = Strings.sNull(req.getMethod(), "GET").toUpperCase();
            ActionChain chain = chainMap.get(httpMethod);
            // 找到了特殊HTTP方法的处�?�动作链
            if (null != chain) {
                return chain;
            }
        }
        // 这个 URL 所有的HTTP方法用统一的动作链处�?�
        if (null != defaultChain) {
            return defaultChain;
        }
        if (chainMap.size() != 0 && log.isDebugEnabled()) {
            log.debugf("Path=[%s] available methods%s but request [%s], using the wrong http method?", ac.getPath(), chainMap.keySet(), httpMethod);
        }
        // �?�则将认为�?能处�?�
        return null;
    }
}
