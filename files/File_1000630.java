package org.nutz.lang.segment;

import java.io.File;
import java.util.Map;

import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;
import org.nutz.lang.util.NutMap;

/**
 * 代�?片段的帮助函数
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class Segments {

    /**
     * 根�?�一个对象填充所有片段的�?��?符
     * 
     * @param seg
     *            片段
     * @param obj
     *            对象
     * @return 填充�?�的片段对象
     */
    public static Segment fill(Segment seg, Object obj) {
        if (null == obj || null == seg)
            return seg;
        return seg.setBy(obj);
    }

    /**
     * 根�?�一个文件生�?一个代�?片段
     * 
     * @param f
     *            文件
     * @return 片段对象
     */
    public static Segment read(File f) {
        String txt = Files.read(f);
        return new CharSegment(txt);
    }

    /**
     * 根�?�字符串片段，将上下文对象替�?�对应�?��?符。未赋值的�?��?符维�?原样
     * <p>
     * 比如：
     * 
     * @param seg
     *            片段对象
     * @param context
     *            上下文对象
     * @return 替�?��?�的字符串
     */
    public static String replace(Segment seg, Context context) {
        if (null == seg)
            return null;

        // 增加缺失的�?��?符�?�
        for (String key : seg.keys())
            if (!context.has(key))
                context.set(key, "${" + key + "}");

        return seg.render(context).toString();
    }

    /**
     * 根�?�字符串片段，将上下文对象替�?�对应�?��?符。未赋值的�?��?符维�?原样
     * 
     * @param pattern
     *            字符串片段
     * @param context
     *            上下文对象
     * @return 替�?��?�的字符串
     */
    public static String replace(String pattern, Context context) {
        if (null == pattern)
            return null;
        if (null == context)
            return pattern;
        return replace(new CharSegment(pattern), context);
    }

    /**
     * @see #replace(Segment, Context)
     */
    public static String replace(String pattern, Map<String, Object> context) {
        return replace(pattern, Lang.context(new NutMap(context)));
    }

    /**
     * 根�?�一段字符串生�?一个代�?片段
     * 
     * @param str
     *            字符串
     * @return 片段对象
     */
    public static Segment create(String str) {
        return new CharSegment(str);
    }
}
