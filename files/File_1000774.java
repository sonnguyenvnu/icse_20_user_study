package org.nutz.mvc.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionChain;
import org.nutz.mvc.ActionChainMaker;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.RequestPath;
import org.nutz.mvc.UrlMapping;
import org.nutz.mvc.annotation.BlankAtException;

public class UrlMappingImpl implements UrlMapping {

    private static final Log log = Logs.get();

    protected Map<String, ActionInvoker> map;// 这个对象有点多余,考虑�?��?AtMap�?�!!

    protected MappingNode<ActionInvoker> root;
    
    protected String prefix;

    public UrlMappingImpl() {
        this.map = new HashMap<String, ActionInvoker>();
        this.root = new MappingNode<ActionInvoker>();
    }
    
    public UrlMappingImpl(String prefix) {
        this();
        this.prefix = prefix;
    }

    public void add(ActionChainMaker maker, ActionInfo ai, NutConfig config) {

        // 检查所有的path
        String[] paths = ai.getPaths();
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            if (Strings.isBlank(path))
                throw new BlankAtException(ai.getModuleType(), ai.getMethod());

            if (path.charAt(0) != '/')
                paths[i] = '/' + path;
        }

        ActionChain chain = maker.eval(config, ai);
        for (String path : ai.getPaths()) {

            // �?试获�?�，看看有没有创建过这个 URL 调用者
            ActionInvoker invoker = map.get(path);

            // 如果没有增加过这个 URL 的调用者，为其创建备忘记录，并加入索引
            if (null == invoker) {
                invoker = new ActionInvoker();
                map.put(path, invoker);
                root.add(path, invoker);
                // 记录一下方法与 url 的映射
                config.getAtMap().addMethod(path, ai.getMethod());
            } else if (!ai.isForSpecialHttpMethod()) {
                log.warnf("Duplicate @At mapping ? path=" + path);
            }

            // 将动作链，根�?�特殊的 HTTP 方法，�?存到调用者内部
            if (ai.isForSpecialHttpMethod()) {
                for (String httpMethod : ai.getHttpMethods())
                    invoker.addChain(httpMethod, chain);
            }
            // �?�则，将其设置为默认动作链
            else {
                invoker.setDefaultChain(chain);
            }
        }

        printActionMapping(ai);

        // TODO 下�?�个IF�?�?�?转�?�到NutLoading中去呢?
        // 记录一个 @At.key
        if (!Strings.isBlank(ai.getPathKey()))
            config.getAtMap().add(ai.getPathKey(), ai.getPaths()[0]);
    }

    public ActionInvoker get(ActionContext ac) {
        RequestPath rp = Mvcs.getRequestPathObject(ac.getRequest());
        String path = rp.getPath();
        if (prefix != null)
            path = path.substring(prefix.length());
        ac.setSuffix(rp.getSuffix());
        ActionInvoker invoker = root.get(ac, path);
        if (invoker != null) {
            ActionChain chain = invoker.getActionChain(ac);
            if (chain != null) {
                if (log.isDebugEnabled()) {
                    log.debugf("Found mapping for [%s] path=%s : %s",
                               ac.getRequest().getMethod(),
                               path,
                               chain);
                }
                return invoker;
            }
        }
        if (log.isDebugEnabled())
            log.debugf("Search mapping for [%s] path=%s : NOT Action match", ac.getRequest().getMethod(), path);
        return null;
    }
    
    public void add(String path, ActionInvoker invoker) {
    	root.add(path, invoker);
    	map.put(path, invoker);
    }
    
    protected void printActionMapping(ActionInfo ai) {
        print(ai);
    }

    protected void print(ActionInfo ai) {

        /*
         * 打�?�基本调试信�?�
         */
        if (log.isDebugEnabled()) {
            // 打�?�路径
            String[] paths = ai.getPaths();
            StringBuilder sb = new StringBuilder();
            if (null != paths && paths.length > 0) {
                sb.append("   '").append(paths[0]).append("'");
                for (int i = 1; i < paths.length; i++)
                    sb.append(", '").append(paths[i]).append("'");
            } else {
                throw Lang.impossible();
            }
            // 打�?�方法�??
            Method method = ai.getMethod();
            String str;
            if (null != method)
                str = genMethodDesc(ai);
            else
                throw Lang.impossible();
            log.debugf("%s >> %50s | @Ok(%-5s) @Fail(%-5s) | by %d Filters | (I:%s/O:%s)",
                       Strings.alignLeft(sb, 30, ' '),
                       str,
                       ai.getOkView(),
                       ai.getFailView(),
                       (null == ai.getFilterInfos() ? 0
                                                   : ai.getFilterInfos().length),
                       ai.getInputEncoding(),
                       ai.getOutputEncoding());
        }
    }
    
    protected String genMethodDesc(ActionInfo ai) {
        Method method = ai.getMethod();
        String prefix = "";
        if (ai.getLineNumber() != null && ai.getLineNumber() > 0) {
            prefix = String.format("(%s.java:%d).%s",
                                 method.getDeclaringClass().getSimpleName(),
                                 ai.getLineNumber(),
                                 method.getName());
        } else {
            prefix = String.format("%s.%s(...)",
                                   method.getDeclaringClass().getSimpleName(),
                                   method.getName());
        }
        return String.format("%-37s : %-10s", 
                             prefix,
                             method.getReturnType().getSimpleName());
    }
}
