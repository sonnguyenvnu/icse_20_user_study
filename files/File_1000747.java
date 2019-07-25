package org.nutz.mvc.adaptor.injector;

import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.mvc.adaptor.ParamInjector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 将整个请求的�?�数表转�?��?一个 Map
 * <ul>
 * <li>如果请求的�?�数为空，则为 Map 添加一个 null 值。
 * <li>如果请求�?�数为一个数组，则为 Map 添加一个数组
 * <li>默认为 Map 添加一个字符串型的值
 * </ul>
 *
 * For Issue 96
 *
 * @author zozoh(zozohtnt@gmail.com)
 * @author stotem(stotem@163.com)
 */
public class MapPairInjector implements ParamInjector {
    private Type type;

    public MapPairInjector(Type type) {
        this.type = type;
    }
    @SuppressWarnings("unchecked")
    public Object get(ServletContext sc,
                      HttpServletRequest req,
                      HttpServletResponse resp,
                      Object refer) {
        Class<?> clazz = Lang.getTypeClass(type);
        Map<String, Object> map;
        if (clazz.isInterface()) {
            map = new HashMap<String, Object>();
        } else {
            map = (Map<String, Object>) Mirror.me(type).born();
        }
        Enumeration<String> enu = (Enumeration<String>) req.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            String[] vs = req.getParameterValues(name);
            // Null Value
            if (null == vs || vs.length == 0) {
                map.put(name, null);
            }
            // Has Value
            else if (vs.length == 1) {
                map.put(name, vs[0]);
            }
            // Array Value
            else {
                map.put(name, vs);
            }
        }
        return map;
    }

}
