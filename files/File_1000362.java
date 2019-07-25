package org.nutz.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.nutz.dao.entity.MappingField;
import org.nutz.lang.Strings;
import org.nutz.lang.util.Regex;

/**
 * 字段匹�?器. 判断顺�? locked--actived-->ignoreNull. 
 * 除locked/actived/ignoreNull之外的属性, 当�?仅用于Cnd.from和Chain.from方法. <p/>
 * ignoreId现在�?�以用于dao.insert了<p/>
 * 
 * @author zozoh(zozohtnt@gmail.com)
 * @author wendal
 */
public class FieldMatcher {

    /**
     * 构建一个字段匹�?器.
     * @param actived 需�?�?留的字段,必须是�?�法的正则表达�?,�?�以为null
     * @param locked  需�?忽略的字段,必须是�?�法的正则表达�?,�?�以为null
     * @param ignoreNull 是�?�忽略空值
     * @return 字段/属性匹�?器
     */
    public static FieldMatcher make(String actived, String locked, boolean ignoreNull) {
        FieldMatcher fm = new FieldMatcher();
        fm.ignoreNull = ignoreNull;
        if (!Strings.isBlank(actived))
            fm.actived = Regex.getPattern(actived);
        if (!Strings.isBlank(locked))
            fm.locked = Regex.getPattern(locked);
        return fm;
    }
    
    /**
     * 仅�?置是�?�忽略@Id标注的属性,然�?�生�?实例
     * @param ignoreId 是�?�忽略@Id标注的属性
     * @return 字段匹�?器实例
     */
    public static FieldMatcher create(boolean ignoreId) {
        FieldMatcher fm = new FieldMatcher();
        fm.ignoreId = ignoreId;
        return fm;
    }
    
    /**
     * 构建一个字段匹�?器.
     * @param actived 需�?�?留的字段,必须是�?�法的正则表达�?,�?�以为null
     * @param locked  需�?忽略的字段,必须是�?�法的正则表达�?,�?�以为null
     * @param ignoreNull 是�?�忽略空值
     * @param ignoreZero 是�?�忽略零值,仅针对数值类型
     * @param ignoreDate 是�?�忽略java.util.Date�?�其�?类的属性
     * @param ignoreId   是�?�忽略@Id标注的属性
     * @param ignoreName 是�?�忽略@Name标注的属性
     * @param ignorePk   是�?�忽略@Pk引用的属性
     * @return 字段/属性匹�?器
     */
    public static FieldMatcher make(String actived, String locked, boolean ignoreNull, 
                                    boolean ignoreZero, boolean ignoreDate, 
                                    boolean ignoreId,
                                    boolean ignoreName,
                                    boolean ignorePk) {
        FieldMatcher fm = make(actived, locked, ignoreNull);
        fm.ignoreZero = ignoreZero;
        fm.ignoreDate = ignoreDate;
        fm.ignoreId = ignoreId;
        fm.ignoreName = ignoreName;
        fm.ignorePk = ignorePk;
        return fm;
    }
    
    /**
     * 构建一个字段匹�?器.
     * @param actived 需�?�?留的字段,必须是�?�法的正则表达�?,�?�以为null
     * @param locked  需�?忽略的字段,必须是�?�法的正则表达�?,�?�以为null
     * @param ignoreNull 是�?�忽略空值
     * @param ignoreZero 是�?�忽略零值,仅针对数值类型
     * @param ignoreDate 是�?�忽略java.util.Date�?�其�?类的属性
     * @param ignoreId   是�?�忽略@Id标注的属性
     * @param ignoreName 是�?�忽略@Name标注的属性
     * @param ignorePk   是�?�忽略@Pk引用的属性
     * @return 字段/属性匹�?器
     */
    public static FieldMatcher make(String actived, String locked, boolean ignoreNull, 
                                    boolean ignoreZero, boolean ignoreDate, 
                                    boolean ignoreId,
                                    boolean ignoreName,
                                    boolean ignorePk,
                                    boolean ignoreBlankStr) {
        FieldMatcher fm = make(actived, locked, ignoreNull);
        fm.ignoreZero = ignoreZero;
        fm.ignoreDate = ignoreDate;
        fm.ignoreId = ignoreId;
        fm.ignoreName = ignoreName;
        fm.ignorePk = ignorePk;
        fm.ignoreBlankStr = ignoreBlankStr;
        return fm;
    }

    /**
     * 哪些字段�?�用
     */
    private Pattern actived;
    /**
     * 哪些字段�?�?�用
     */
    private Pattern locked;
    /**
     * 是�?�忽略空值
     */
    private Boolean ignoreNull = true;
    /**
     * 是�?�忽略空白字符串
     */
    private Boolean ignoreBlankStr;
    /**
     * 是�?�忽略零值
     */
    private Boolean ignoreZero;
    /**
     * 是�?�忽略日期
     */
    private Boolean ignoreDate;
    /**
     * 是�?�忽略@Id标注的属性
     */
    private Boolean ignoreId = true;
    /**
     * 是�?�忽略@Name标注的属性
     */
    private Boolean ignoreName;
    /**
     * 是�?�忽略@Pk标注引用的属性
     */
    private Boolean ignorePk;
    
    private Boolean ignoreFalse;

    /**
     * 匹�?顺�? locked -- actived-- ignoreNull
     * @param str 需�?匹�?的字段�??称
     * @return true,如果�?�用
     */
    public boolean match(String str) {
        if (null != locked && locked.matcher(str).find()) {
            return false;
        }
        if (null != actived && !actived.matcher(str).find()) {
            return false;
        }
        return true;
    }
    
    public boolean match(MappingField mf, Object obj) {
        String fieldName = mf.getName();
        if (null != locked && locked.matcher(fieldName).find()) {
            return false;
        }
        if (null != actived && !actived.matcher(fieldName).find()) {
            return false;
        }
        if (ignoreId != null && ignoreId && mf.isId())
            return false;
        if (ignoreName != null && ignoreName && mf.isName())
            return false;
        if (ignorePk != null && ignorePk && mf.isCompositePk())
            return false;
        Object val = mf.getValue(obj);
        if (val == null) {
            if (ignoreNull != null && ignoreNull)
                return false;
        } else {
            if (ignoreZero != null && ignoreZero
                && val instanceof Number
                && ((Number) val).doubleValue() == 0.0) {
                return false;
            }
            if (ignoreDate != null && ignoreDate && val instanceof Date) {
                return false;
            }
            if (ignoreBlankStr != null && ignoreBlankStr
                && val instanceof CharSequence
                && Strings.isBlank((CharSequence) val)) {
                return false;
            }
            if (val instanceof Boolean && ignoreFalse != null && ignoreFalse && !((Boolean)val))
                return false;
        }
        return true;
    }

    /**
     * 是�?�忽略控制
     * @return true,如果忽略控制
     */
    public boolean isIgnoreNull() {
        return ignoreNull;
    }

    /**
     * 设置是�?�忽略空值
     * @param ignoreNull 是�?�忽略空值属性
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setIgnoreNull(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
        return this;
    }

    /**
     * 获�?��?�用字段/属性的正则表达�?
     * @return 正则表达�?,�?�能为null
     */
    public Pattern getActived() {
        return actived;
    }

    /**
     * 获�?��?�?�用字段/属性的正则表达�?
     * @return 正则表达�?,�?�能为null
     */
    public Pattern getLocked() {
        return locked;
    }

    /**
     * 设置过滤�?�用字段/属性的正则表达�?
     * @param actived 正则表达�?
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setActived(String actived) {
        if (actived != null)
            this.actived = Regex.getPattern(actived);
        else
            this.actived = null;
        return this;
    }

    /**
     * 设置过滤�?�?�用字段/属性的正则表达�?
     * @param locked 正则表达�?
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setLocked(String locked) {
        if (locked != null)
            this.locked = Regex.getPattern(locked);
        else
            this.locked = null;
        return this;
    }

    /**
     * 是�?�忽略零值
     * @return true,如果是的�?,默认为false
     */
    public boolean isIgnoreZero() {
        return ignoreZero;
    }

    /**
     * 设置是�?�忽略零值
     * @param ignoreZero 是�?�忽略零值
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setIgnoreZero(boolean ignoreZero) {
        this.ignoreZero = ignoreZero;
        return this;
    }

    /**
     * 是�?�忽略Date�?�其�?类的属性值
     * @return true,如果需�?忽略的�?
     */
    public boolean isIgnoreDate() {
        return ignoreDate;
    }

    /**
     * 设置是�?�忽略Date�?�其�?类的属性值
     * @param ignoreDate 是�?�忽略
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setIgnoreDate(boolean ignoreDate) {
        this.ignoreDate = ignoreDate;
        return this;
    }

    /**
     * 是�?�忽略@Id标注的属性,默认忽略
     * @return true,如果忽略
     */
    public boolean isIgnoreId() {
        return ignoreId;
    }

    /**
     * 设置是�?�忽略@Id标注的属性
     * @param ignoreId 是�?�忽略
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setIgnoreId(boolean ignoreId) {
        this.ignoreId = ignoreId;
        return this;
    }

    /**
     * 是�?�忽略@Name标注的属性,默认是false
     * @return true,如果忽略的�?
     */
    public boolean isIgnoreName() {
        return ignoreName;
    }

    /**
     * 设置是�?�忽略@Name标注的属性
     * @param ignoreName 是�?�忽略
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setIgnoreName(boolean ignoreName) {
        this.ignoreName = ignoreName;
        return this;
    }

    /**
     * 是�?�忽略@Pk所引用的属性,默认是false
     * @return true,如果忽略的�?
     */
    public boolean isIgnorePk() {
        return ignorePk;
    }

    /**
     * 设置是�?�忽略@Pk所引用的属性
     * @param ignorePk 是�?�忽略
     * @return 原对象,用于链�?调用
     */
    public FieldMatcher setIgnorePk(boolean ignorePk) {
        this.ignorePk = ignorePk;
        return this;
    }

    public boolean isIgnoreBlankStr() {
        return ignoreBlankStr != null && ignoreBlankStr;
    }

    public FieldMatcher setIgnoreBlankStr(boolean ignoreBlankStr) {
        this.ignoreBlankStr = ignoreBlankStr;
        return this;
    }
    
    public void setIgnoreFalse(Boolean ignoreFalse) {
        this.ignoreFalse = ignoreFalse;
    }

    public static FieldMatcher simple(String ...fields) {
        final Set<String> m = new HashSet<String>(Arrays.asList(fields));
        return new FieldMatcher() {
            public boolean match(String str) {
                return m.contains(str);
            }
            public boolean match(MappingField mf, Object obj) {
                return this.match(mf.getName());
            }
        };
    }
}
