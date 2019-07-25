package org.nutz.json;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.nutz.castor.Castors;
import org.nutz.lang.util.NutMap;

/**
 * �??述Json输出的格�?
 *
 * @author zozoh(zozohtnt@gmail.com)
 * @author Wendal(wendal1985@gmail.com)
 * @author 有心猴(belialofking@163.com)
 *
 */
public class JsonFormat extends NutMap {

    private static final long serialVersionUID = 1L;
    private static char DEFAULT_SEPARATOR = '\"';

    /**
     * 紧凑模�? -- 无�?�行,忽略null值
     */
    public static JsonFormat compact() {
        return new JsonFormat(true).setIgnoreNull(true);
    }

    /**
     * 全部输出模�? -- �?�行,�?忽略null值
     */
    public static JsonFormat full() {
        return new JsonFormat(false).setIgnoreNull(false);
    }

    /**
     * 一般模�? -- �?�行,但忽略null值
     */
    public static JsonFormat nice() {
        return new JsonFormat(false).setIgnoreNull(true);
    }

    /**
     * 为了打�?�出�?�容易看，把�??字去掉引�?�
     */
    public static JsonFormat forLook() {
        return new JsonFormat(false).setQuoteName(false).setIgnoreNull(true);
    }

    /**
     * �?�?�行,�?忽略空值
     */
    public static JsonFormat tidy() {
        return new JsonFormat(true).setIgnoreNull(false);
    }

    /**
     * 获得一个Json输出格�?，默认格�?如下: <br>
     * <li>使用紧凑模�?输出
     * <li>缩进时用的字符串为『&nbsp;&nbsp;&nbsp;�?（三个空格）
     * <li>给字段添加�?�引�?�
     * <li>分隔符为『"�?
     *
     */
    public JsonFormat() {
        this(true);
    }

    /**
     * 获得一个Json输出格�?，默认格�?如下: <br>
     * <li>缩进时用的字符串为『&nbsp;&nbsp;&nbsp;�?（三个空格）
     * <li>给字段添加�?�引�?�
     * <li>分隔符为『"�?
     *
     * @param compact
     *            true: 使用紧凑模�?输出，false: �?使用紧凑模�?输出
     */
    public JsonFormat(boolean compact) {
        setCompact(compact);
    }

    public static class Function {
    	/**
    	 * 是�?�忽略 JsonShape 注解
    	 */
    	public static String ignoreJsonShape = "ignoreJsonShape";
        /**
         * 缩进时用的字符串
         */
        public static String indentBy = "indentBy";
        /**
         * 是�?�使用紧凑模�?输出
         */
        public static String compact = "compact";
        /**
         * 是�?�给字段添加�?�引�?�
         */
        public static String quoteName = "quoteName";
        /**
         * 是�?�忽略null值
         */
        public static String ignoreNull = "ignoreNull";
        /**
         * 仅输出的字段的正则表达�?
         */
        public static String actived = "actived";
        /**
         * �?输出的字段的正则表达�?
         */
        public static String locked = "locked";
        /**
         * 用到的类型转�?�器
         */
        public static String castors = "castors";
        /**
         * 分隔符
         */
        public static String separator = "separator";
        /**
         * 是�?�自动将值应用Unicode编�?
         */
        public static String autoUnicode = "autoUnicode";
        /**
         * unicode编�?用大写还是�?写
         */
        public static String unicodeLower = "unicodeLower";
        /**
         * 日期格�?
         */
        public static String dateFormat = "dateFormat";
        /**
         * 数字格�?
         */
        public static String numberFormat = "numberFormat";
        /**
         * �?�到空值的时候写入字符串
         */
        public static String nullAsEmtry = "nullAsEmtry";
        public static String nullListAsEmpty = "nullListAsEmpty";
        public static String nullStringAsEmpty = "nullStringAsEmpty";
        public static String nullBooleanAsFalse = "nullBooleanAsFalse";
        public static String nullNumberAsZero = "nullNumberAsZero";
        public static String timeZone = "timeZone";
        public static String locale = "locale";
        public static String dateFormatRaw = "dateFormatRaw";
    }

    @JsonField(ignore = true)
    private Castors castors;

    /**
     * 判断该字段是�?�是指定输出方�?中的字段
     *
     * @param name
     *            字段�??
     * @return true: 该字段在忽略字段中，false: 该字段�?在忽略字段中
     */
    public boolean ignore(String name) {
        if (null != getActived())
            return !getActived().matcher(name).find();
        if (null != getLocked())
            return getLocked().matcher(name).find();
        return false;
    }

    // ===================================================================
    // getter setter

    /**
     * Json输出格�?的紧凑模�?
     *
     * @return true: 使用紧凑模�?输出，false: �?使用紧凑模�?输出
     */
    public boolean isCompact() {
        return getBoolean(Function.compact, false);
    }

    /**
     * 设置Json输出格�?的紧凑模�?
     *
     * @param compact
     *            true: 使用紧凑模�?输出，false: �?使用紧凑模�?输出
     * @return 该Json输出格�?
     */
    public JsonFormat setCompact(boolean compact) {
        put(Function.compact, compact);
        return this;
    }

    @Deprecated
    public int getIndent() {
        return 0;
    }

    @Deprecated
    public JsonFormat setIndent(int indent) {
        return this;
    }

    @Deprecated
    public JsonFormat increaseIndent() {
        return this;
    }

    @Deprecated
    public JsonFormat decreaseIndent() {
        return this;
    }

    /**
     * Json输出格�?的缩进时用的字符串
     *
     * @return 缩进时用的字符串
     */
    public String getIndentBy() {
        return getString(Function.indentBy, "   ");
    }
    /**
     * 设置忽略 JsonShape 注解
     * @return
     */
    public JsonFormat ignoreJsonShape() {
		put(Function.ignoreJsonShape,true);
		return this;
	}
    
    public boolean isIgnoreJsonShape() {
		return getBoolean(Function.ignoreJsonShape);
	}

    /**
     * 设置Json输出格�?的缩进时用的字符串
     *
     * @param indentBy
     *            设置缩进时用的字符串
     * @return 该Json输出格�?
     */
    public JsonFormat setIndentBy(String indentBy) {
        put(Function.indentBy, indentBy);
        return this;
    }

    /**
     * Json输出格�?的给字段添加�?�引�?�
     *
     * @return 是�?�给字段添加�?�引�?�
     */
    public boolean isQuoteName() {
        return getBoolean(Function.quoteName, true); // 默认为true
    }

    /**
     * 设置Json输出格�?的给字段添加�?�引�?�
     *
     * @param quoteName
     *            true: 给字段添加�?�引�?�，false: �?给字段添加�?�引�?�
     * @return 该Json输出格�?
     */
    public JsonFormat setQuoteName(boolean quoteName) {
        put(Function.quoteName, quoteName);
        return this;
    }

    /**
     * Json输出格�?的忽略null值
     *
     * @return 是�?�忽略null的值
     */
    public boolean isIgnoreNull() {
        return getBoolean(Function.ignoreNull, false);
    }

    /**
     * 设置Json输出格�?中是�?�忽略null
     *
     * @param ignoreNull
     *            true: 忽略null的值，false: �?忽略null的值
     * @return 该Json输出格�?
     */
    public JsonFormat setIgnoreNull(boolean ignoreNull) {
        put(Function.ignoreNull, ignoreNull);
        return this;
    }

    /**
     * 设置Json输出格�?中输出的字段
     *
     * @param regex
     *            输出的字段的正则表达�?
     * @return 该Json输出格�?
     */
    public JsonFormat setActived(String regex) {
        put(Function.actived, Pattern.compile(regex));
        return this;
    }

    /**
     * 设置Json输出格�?中�?输出的字段
     *
     * @param regex
     *            �?输出的字段的正则表达�?
     * @return 该Json输出格�?
     */
    public JsonFormat setLocked(String regex) {
        put(Function.locked, Pattern.compile(regex));
        return this;
    }

    public Castors getCastors() {
        return castors == null ? Castors.me() : castors;
    }

    public JsonFormat setCastors(Castors castors) {
        this.castors = castors;
        return this;
    }

    /**
     * 设置Json输出格�?的分隔符
     *
     * @param separator
     *            分隔符
     * @return 该Json输出格�?
     */
    public JsonFormat setSeparator(char separator) {
        put(Function.separator, separator);
        return this;
    }

    /**
     * Json输出格�?的分隔符
     *
     * @return 分隔符
     */
    public char getSeparator() {
        Character separator = getAs(Function.separator, Character.class);
        if (separator != null)
            return separator;
        return DEFAULT_SEPARATOR;
    }

    /**
     * 设置Json输出格�?的自动将值应用unicode编�?
     *
     * @param autoUnicode
     *            true: 自动将值应用unicode编�?，false: �?自动将值应用unicode编�?
     * @return 该Json输出格�?
     */
    public JsonFormat setAutoUnicode(boolean autoUnicode) {
        put(Function.autoUnicode, autoUnicode);
        return this;
    }

    /**
     * Json输出格�?的自动unicode编�?
     *
     * @return true: 自动将值应用unicode编�?，false: �?自动将值应用unicode编�?
     */
    public boolean isAutoUnicode() {
        return getBoolean(Function.autoUnicode, false);
    }

    /**
     * Json输出格�?的unicode编�?
     *
     * @return true: unicode编�?用大写，false: unicode编�?用�?写
     */
    public boolean isUnicodeLower() {
        return getBoolean(Function.unicodeLower, false);
    }

    /**
     * 设置Json输出格�?的unicode编�?大�?写规则
     *
     * @param unicodeLower
     *            true: unicode编�?用大写，false: unicode编�?用�?写
     * @return 该Json输出格�?
     */
    public JsonFormat setUnicodeLower(boolean unicodeLower) {
        put(Function.unicodeLower, unicodeLower);
        return this;
    }

    /**
     * 设置Json输出格�?的设置日期格�?
     *
     * @param df
     *            日期格�?
     * @return 该Json输出格�?
     */
    public JsonFormat setDateFormat(String df) {
        if (df == null) {
            remove(Function.dateFormat);
        } else if (DATEFORMAT_TIMESTAMP.equals(df)) {
            put(Function.dateFormat, new TimeStampDateFormat());
        } else {
            put(Function.dateFormat, new SimpleDateFormat(df));
            put(Function.dateFormatRaw, df);
        }
        return this;
    }

    /**
     * 设置Json输出格�?的日期格�?
     *
     * @param df
     *            日期格�?
     * @return 该Json输出格�?
     */
    public JsonFormat setDateFormat(DateFormat df) {
        put(Function.dateFormat, df);
        return this;
    }

    /**
     * Json输出格�?的日期格�?
     *
     * @return 日期格�?
     */
    public DateFormat getDateFormat() {
        DateFormat df = getAs(Function.dateFormat, DateFormat.class);
        return df == null ? null : (DateFormat) df.clone();
    }

    /**
     * Json输出格�?的数字格�?
     *
     * @return 数字格�?
     */
    public NumberFormat getNumberFormat() {
        NumberFormat nf = getAs(Function.numberFormat, NumberFormat.class);
        return nf == null ? null : (NumberFormat) nf.clone();
    }

    /**
     * 设置Json输出格�?的数字格�?
     *
     * @param numberFormat
     *            数字格�?
     * @return 该Json输出格�?
     */
    public JsonFormat setNumberFormat(NumberFormat numberFormat) {
        put(Function.numberFormat, numberFormat);
        return this;
    }

    /**
     * 生�?一个该Json输出格�?的副本
     *
     * @return 该Json输出格�?的副本
     */
    @Override
    public JsonFormat clone() {
        JsonFormat jf = new JsonFormat();
        jf.putAll(this);
        return jf;
    }

    public static String DATEFORMAT_TIMESTAMP = "timestamp";

    public Pattern getActived() {
        return getAs(Function.actived, Pattern.class);
    }

    public JsonFormat setActived(Pattern actived) {
        put(Function.actived, actived);
        return this;
    }

    public Pattern getLocked() {
        return getAs(Function.locked, Pattern.class);
    }

    public JsonFormat setLocked(Pattern locked) {
        put(Function.locked, locked);
        return this;
    }

    public boolean isNullAsEmtry() {
        return getBoolean(Function.nullAsEmtry, false);
    }

    public JsonFormat setNullAsEmtry(boolean nullAsEmtry) {
        put(Function.nullAsEmtry, nullAsEmtry);
        return this;
    }

    public TimeZone getTimeZone() {
        return getAs(Function.timeZone, TimeZone.class);
    }

    public JsonFormat setTimeZone(TimeZone timeZone) {
        put(Function.timeZone, timeZone);
        return this;
    }

    public boolean isNullListAsEmpty() {
        return getBoolean(Function.nullListAsEmpty, false);
    }

    public JsonFormat setNullListAsEmpty(boolean nullListAsEmpty) {
        put(Function.nullListAsEmpty, nullListAsEmpty);
        return this;
    }

    public boolean isNullStringAsEmpty() {
        return getBoolean(Function.nullStringAsEmpty, false);
    }

    public JsonFormat setNullStringAsEmpty(boolean nullStringAsEmpty) {
        put(Function.nullStringAsEmpty, nullStringAsEmpty);
        return this;
    }

    public boolean isNullBooleanAsFalse() {
        return getBoolean(Function.nullBooleanAsFalse, false);
    }

    public JsonFormat setNullBooleanAsFalse(boolean nullBooleanAsFalse) {
        put(Function.nullBooleanAsFalse, nullBooleanAsFalse);
        return this;
    }

    public boolean isNullNumberAsZero() {
        return getBoolean(Function.nullNumberAsZero, false);
    }

    public JsonFormat setNullNumberAsZero(boolean nullNumberAsZero) {
        put(Function.nullNumberAsZero, nullNumberAsZero);
        return this;
    }
    
    public JsonFormat setLocale(String locale) {
        put(Function.locale, locale);
        return this;
    }
    
    public String getLocale() {
        return getString(Function.locale);
    }
    
    public String getDateFormatRaw() {
        return getString(Function.dateFormatRaw);
    }
}
