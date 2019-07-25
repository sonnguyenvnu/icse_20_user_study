package org.nutz.mvc.adaptor.injector;

import java.lang.reflect.Type;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.mapl.Mapl;
import org.nutz.mvc.adaptor.ParamExtractor;
import org.nutz.mvc.adaptor.ParamInjector;
import org.nutz.mvc.adaptor.Params;

/**
 * 对象导航注入器 默认情况下�?�有使用 @Param("::") 的情况下�?调用这个注入器
 * <p/>
 * 毕竟它在接收到请求时进行注入,会有一定的性能�?�伤
 * 
 * @author juqkai(juqkai@gmail.com)
 * @author wendal(wendal1985@gmail.com)
 * 
 */
public class ObjectNavlPairInjector implements ParamInjector {
    protected Mirror<?> mirror;
    private String prefix;
    private Type type;

    public ObjectNavlPairInjector(String prefix, Type type) {
        prefix = Strings.isBlank(prefix) ? "" : Strings.trim(prefix);
        this.prefix = prefix;
        this.mirror = Mirror.me(type);
        this.type = type;
    }

    public Object get(    ServletContext sc,
                        HttpServletRequest req,
                        HttpServletResponse resp,
                        Object refer) {
        ObjectNaviNode no = new ObjectNaviNode();
        String pre = "";
        if ("".equals(prefix))
            pre = "node.";
        ParamExtractor pe = Params.makeParamExtractor(req, refer);
        for (Object name : pe.keys()) {
            String na = (String) name;
            if (na.startsWith(prefix)) {
                no.put(pre + na, pe.extractor(na));
            }
        }
        Object model = no.get();
        return Mapl.maplistToObj(model, type);
//        return no.inject(mirror);
    }

}
