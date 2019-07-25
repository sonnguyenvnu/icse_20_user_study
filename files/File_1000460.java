package org.nutz.dao;

import java.util.Map;

import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.segment.Segment;
import org.nutz.lang.util.Context;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 将一个�?�考对象存入 ThreadLocal
 * <p>
 * Nutz.Dao 将在构造 SQL 时，�?�考这个对象。如何�?�考，请�?�看 '@Table' 关于 “动�?表�??的赋值规则�? 的�??述
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class TableName {

    private static final Log log = Logs.get();

    private static final ThreadLocal<Object> object = new ThreadLocal<Object>();

    /**
     * 代�?模�?�，这个模�?��?�?了，在 atom 中�?行的 POJO 的动�?表�??，都会被�?�考对象所影�?
     * 
     * @param refer
     *            �?�考对象
     * @param atom
     *            你的业务逻辑
     */
    public static void run(Object refer, Runnable atom) {
        if (null != atom) {
            if (log.isTraceEnabled())
                log.tracef("TableName.run: [%s]->[%s]", object, object.get());

            Object old = get();
            set(refer);
            try {
                atom.run();
            }
            catch (Exception e) {
                throw Lang.wrapThrow(e);
            }
            finally {
                set(old);
                if (log.isTraceEnabled())
                    log.tracef("TableName.finally: [%s]->[%s]", object, object.get());
            }
        }
    }

    /**
     * @return 当�?线程中的动�?表�??�?�考对象
     */
    public static Object get() {
        return object.get();
    }

    /**
     * 为当�?线程设置动�?表�??�?�考对象
     * 
     * @param obj
     *            �?�考对象
     * @return 旧的动�?表�??�?�考对象
     */
    public static Object set(Object obj) {
        Object re = get();
        object.set(obj);
        return re;
    }

    /**
     * 清除当�?线程的动�?表�??�?�考对象
     */
    public static void clear() {
        set(null);
    }

    /**
     * 根�?�当�?线程的�?�考对象，渲染一个动�?表�??
     * 
     * @param tableName
     *            动�?表�??
     * @return 渲染�?�的表�??
     */
    public static String render(Segment tableName) {
        Object obj = get();
        if (null == obj || !tableName.hasKey())
            return tableName.toString();

        Context context = Lang.context();
        if (isPrimitive(obj)) {
            for (String key : tableName.keys())
                context.set(key, obj);
        } else if (obj instanceof Context) {
            for (String key : tableName.keys())
                context.set(key, ((Context) obj).get(key));
        } else if (obj instanceof Map<?, ?>) {
            for (String key : tableName.keys())
                context.set(key, ((Map<?, ?>) obj).get(key));
        } else {
            Mirror<?> mirror = Mirror.me(obj);
            for (String key : tableName.keys())
                context.set(key, mirror.getValue(obj, key));
        }
        return tableName.render(context).toString();
    }

    public static boolean isPrimitive(Object obj) {
        return obj instanceof CharSequence || obj instanceof Number || obj.getClass().isPrimitive();
    }
}
