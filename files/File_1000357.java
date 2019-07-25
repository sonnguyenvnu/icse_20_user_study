package org.nutz.dao;

import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Lang;
import org.nutz.trans.Atom;
import org.nutz.trans.Molecule;

/**
 * 定制 POJO 的字段过滤�?置。
 * <p>
 * 通过这类，�?�以指明当�?线程的 DAO �?作，哪些对象，的哪些字段会被忽略
 * <p>
 * 
 * <pre>
 * 调用例�?:
 * <code>
 * &#064;Table(&quot;jax_pf_fold&quot;)
 * public class JaxFold implements Serializable {
 *     private static final long serialVersionUID = 5990870005316924017L;
 *     &#064;Column
 *     &#064;Name
 *     //字符类型主键,用name�?�表示
 *     private String mldh;
 *     &#064;Column
 *     private String mlmc;
 *     &#064;Column
 *     private String sjmldh;
 *     &#064;Column
 *     private Integer sxh;
 *     &#064;Column
 *     private String bz;
 * }
 * </code>
 * 例�?1,一般表达�?
 * <code>
 * FieldFilter.create(JaxFold.class, &quot;bz|mlmc|mldh&quot;).run(new Atom() {
 *     public void run() {
 *         nutDao.update(fold);
 *     }
 * });
 * </code>
 *  执行的sql是:
 *  UPDATE jax_pf_fold SET sjmldh='235',bz='备注',mlmc='信�?�打�?�' WHERE mldh='2353' 
 *  由于 sjmldh 和 mldh 匹�?因此,也会被认定为�?�与�?作的字段.
 * <br>
 * 例�?2,更严格的正则表达�?
 * <code>
 * FieldFilter.create(JaxFold.class, &quot;&circ;(bz|mlmc|mldh)$&quot;).run(new Atom() {
 *     public void run() {
 *         nutDao.update(fold);
 *     }
 * });
 * </code>
 *  执行的sql是:
 *  UPDATE jax_pf_fold SET bz='备注',mlmc='信�?�打�?�',sxh=2343 WHERE mldh='2353'
 * 
 * </pre>
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class FieldFilter {

    private static ThreadLocal<FieldFilter> FF = new ThreadLocal<FieldFilter>();

    /**
     * 创建一个过滤器
     * 
     * @param type
     *            POJO 类型
     * @param locked
     *            正则表达�?，哪些字段会被忽略，语法请�?�看 Pattern 类的 JavaDoc
     * @return 字段过滤器
     */
    public static FieldFilter locked(Class<?> type, String locked) {
        return create(type, null, locked, true);
    }

    /**
     * 创建一个过滤器
     * 
     * @param type
     *            POJO 类型
     * @param ignoreNull
     *            是�?�忽略 NULL 值字段
     * @return 字段过滤器
     */
    public static FieldFilter create(Class<?> type, boolean ignoreNull) {
        return create(type, null, null, ignoreNull);
    }

    /**
     * 创建一个默认�?忽略 NULL 值字段的过滤器
     * 
     * @param type
     *            POJO 类型
     * @param actived
     *            正则表达�?，哪些字段会被�?作，语法请�?�看 Pattern 类的 JavaDoc
     * @return 字段过滤器
     * 
     * @see java.util.regex.Pattern
     */
    public static FieldFilter create(Class<?> type, String actived) {
        return create(type, actived, null, false);
    }

    /**
     * 创建一个过滤器
     * 
     * @param type
     *            POJO 类型
     * @param actived
     *            正则表达�?，哪些字段会被�?作，语法请�?�看 Pattern 类的 JavaDoc
     * @param ignoreNull
     *            是�?�忽略 NULL 值字段
     * @return 字段过滤器
     * 
     * @see java.util.regex.Pattern
     */
    public static FieldFilter create(Class<?> type, String actived, boolean ignoreNull) {
        return create(type, actived, null, ignoreNull);
    }

    /**
     * 创建一个过滤器
     * 
     * @param type
     *            POJO 类型
     * @param actived
     *            正则表达�?，哪些字段会被�?作，语法请�?�看 Pattern 类的 JavaDoc
     * @param locked
     *            正则表达�?，哪些字段会被忽略，语法请�?�看 Pattern 类的 JavaDoc
     * @param ignoreNull
     *            是�?�忽略 NULL 值字段
     * @return 字段过滤器
     * 
     * @see java.util.regex.Pattern
     */
    public static FieldFilter create(    Class<?> type,
                                        String actived,
                                        String locked,
                                        boolean ignoreNull) {
        return create(type, FieldMatcher.make(actived, locked, ignoreNull));
    }

    /**
     * 创建一个过滤器
     * 
     * @param type
     *            POJO 类型
     * @param mathcer
     *            字段匹�?器
     * @return 字段过滤器
     */
    public static FieldFilter create(Class<?> type, FieldMatcher mathcer) {
        FieldFilter ff = new FieldFilter();
        ff.set(type, mathcer);
        return ff;
    }

    private FieldFilter() {
        map = new HashMap<Class<?>, FieldMatcher>();
    }

    private Map<Class<?>, FieldMatcher> map;

    /**
     * 为自身增加一个 POJO 的字段过滤设置
     * 
     * @param type
     *            POJO 类型
     * @param ignoreNull
     *            是�?�忽略 NULL 值字段
     * @return 自身
     */
    public FieldFilter set(Class<?> type, boolean ignoreNull) {
        map.put(type, FieldMatcher.make(null, null, ignoreNull));
        return this;
    }

    /**
     * 为自身增加一个 POJO 的字段过滤设置
     * 
     * @param type
     *            POJO 类型
     * @param actived
     *            正则表达�?，哪些字段会被�?作，语法请�?�看 Pattern 类的 JavaDoc
     * @return 自身
     */
    public FieldFilter set(Class<?> type, String actived) {
        map.put(type, FieldMatcher.make(actived, null, false));
        return this;
    }

    /**
     * 为自身增加一个 POJO 的字段过滤设置
     * 
     * @param type
     *            POJO 类型
     * @param actived
     *            正则表达�?，哪些字段会被�?作，语法请�?�看 Pattern 类的 JavaDoc
     * @param ignoreNull
     *            是�?�忽略 NULL 值字段
     * @return 自身
     */
    public FieldFilter set(Class<?> type, String actived, boolean ignoreNull) {
        map.put(type, FieldMatcher.make(actived, null, ignoreNull));
        return this;
    }

    /**
     * 为自身增加一个 POJO 的字段过滤设置
     * 
     * @param type
     *            POJO 类型
     * @param actived
     *            正则表达�?，哪些字段会被�?作，语法请�?�看 Pattern 类的 JavaDoc
     * @param locked
     *            正则表达�?，哪些字段会被忽略，语法请�?�看 Pattern 类的 JavaDoc
     * @param ignoreNull
     *            是�?�忽略 NULL 值字段
     * @return 自身
     */
    public FieldFilter set(Class<?> type, String actived, String locked, boolean ignoreNull) {
        map.put(type, FieldMatcher.make(actived, locked, ignoreNull));
        return this;
    }

    /**
     * 为自身增加一个 POJO 的字段过滤设置
     * 
     * @param type
     *            POJO 类型
     * @param matcher
     *            字段匹�?器
     * @return 自身
     */
    public FieldFilter set(Class<?> type, FieldMatcher matcher) {
        map.put(type, matcher);
        return this;
    }

    /**
     * 移除一个 POJO 的字段过滤设置
     * 
     * @param type
     *            POJO 类型
     * @return 自身
     */
    public FieldFilter remove(Class<?> type) {
        map.remove(type);
        return this;
    }

    /**
     * 根�?� POJO 的类型，从 ThreadLocal 中获�?�字段过滤器
     * 
     * @param type
     *            POJO 的类型
     * @return 字段过滤器
     */
    public static FieldMatcher get(Class<?> type) {
        FieldFilter ff = FF.get();
        if (null == ff)
            return null;
        return ff.map.get(type);
    }

    /**
     * @return 内部的文件类型与过滤器的映射表
     */
    public Map<Class<?>, FieldMatcher> map() {
        return map;
    }

    /**
     * �?行模�?�函数
     * 
     * @param atom
     *            �?行原�?
     */
    public void run(Atom atom) {
        FF.set(this);
        try {
            atom.run();
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            FF.remove();
        }
    }

    public <T> T run(Molecule<T> m) {
    	run((Atom)m);
    	return m.getObj();
    }
}
