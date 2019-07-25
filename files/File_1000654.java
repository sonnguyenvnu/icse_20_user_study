package org.nutz.lang.tmpl;

import java.util.LinkedList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutBean;
import org.nutz.lang.util.NutMap;

/**
 * �?��?符支�? `${路径<类型:格�?>?默认值}` 的写法
 * <p/>
 * 支�?的类型为:
 * <ul>
 * <li><b>int</b> : %d 格�?化字符串
 * <li><b>long</b> : %d 格�?化字符串
 * <li><b>float</b> : %f 格�?化字符串
 * <li><b>double</b>: %f 格�?化字符串
 * <li><b>boolean</b>: �?�/是 格�?化字符串
 * <li><b>date</b> : yyyyMMdd 格�?化字符串
 * <li><b>string</b>: %s 格�?化字符串。或者 (string::A=Apple,B=Banana,C=Cherry) 表映射数�?�
 * <li><b>json<b> : cqn 输出一段 JSON 文本,c紧凑，q输出引�?�,n忽略null
 * </ul>
 * <p/>
 * 如果没有声明类型，则当�?� "string"
 *
 * @author zozoh(zozohtnt@gmail.com)
 */
public class Tmpl {

    // private static final Pattern _P2 =
    // Pattern.compile("([\\w\\d_.\\[\\]'\"-]+)"
    // + "(<(int|long|boolean|float|double|date|string)( *: *([^>]*))?>)?"
    // + "([?] *(.*) *)?");

    private static final Pattern _P2 = Pattern.compile("([^<>()?]+)"
                                                       + "([<(](int|long|boolean|float|double|date|string|json)?( *: *([^>]*))?[>)])?"
                                                       + "([?] *(.*) *)?");

    /**
     * 解�?模�?�对象
     *
     * @param tmpl
     *            模�?�字符串
     * @return 解�?好的模�?�对象
     *
     * @see #parse(String, Pattern, int, int)
     */
    public static Tmpl parse(String tmpl) {
        if (null == tmpl)
            return null;
        return new Tmpl(tmpl, null, -1, -1, null);
    }

    public static Tmpl parsef(String fmt, Object... args) {
        if (null == fmt)
            return null;
        return new Tmpl(String.format(fmt, args), null, -1, -1, null);
    }

    /**
     * 解�?模�?�对象，并用上下文进行渲染。
     * <p/>
     * 你�?�以通过�?�数 ptn 指定自定义的正则表达�?�?�声明自己的模�?��?��?符形�?。 <br>
     * 默认的模�?��?��?符是 <code>(?&lt;![$])[$][{]([^}]+)[}]</code>
     * <p/>
     * �?�，形�?如 <code>${xxxx}</code> 的会被当�?��?��?符， �?�时 <code>$$</code> �?�以逃逸
     *
     * @param tmpl
     *            模�?�字符串
     * @param ptn
     *            一个正则表达�?，指明�?��?符的形�?。
     * @param groupIndex
     *            指定正则表达�?，哪个匹�?组作为你的�?��?符内容
     * @param escapeIndex
     *            指明了逃逸字符的组，如果为 -1 则表示没有逃逸字符
     * @param getEscapeStr
     *            给定如何显示逃逸字符的回调
     * @return 模�?�对象
     */
    public static Tmpl parse(String tmpl,
                             Pattern ptn,
                             int groupIndex,
                             int escapeIndex,
                             TmplEscapeStr getEscapeStr) {
        if (null == tmpl)
            return null;
        return new Tmpl(tmpl, ptn, groupIndex, escapeIndex, getEscapeStr);
    }

    /**
     * 解�?模�?�对象, 并用上下文进行渲染。
     * 
     * @param tmpl
     *            模�?�字符串
     * @param startChar
     *            �?��?符起始标示符
     * @param leftBrace
     *            左侧括�?�
     * @param rightBrace
     *            �?�侧括�?�
     * @return 模�?�对象
     */
    public static Tmpl parse(String tmpl,
                             final String startChar,
                             String leftBrace,
                             String rightBrace) {
        if (null == tmpl)
            return null;
        String regex = "((?<!["
                       + startChar
                       + "])["
                       + startChar
                       + "]["
                       + ("[".equals(leftBrace) ? "\\[" : leftBrace)
                       + "]([^"
                       + ("]".equals(rightBrace) ? "\\]" : rightBrace)
                       + "]+)["
                       + rightBrace
                       + "])|(["
                       + startChar
                       + "]["
                       + startChar
                       + "])";
        Pattern ptn = Pattern.compile(regex);
        return new Tmpl(tmpl, ptn, 2, 3, new TmplEscapeStr() {
            public String get(Matcher m) {
                return startChar;
            }
        });
    }

    /**
     * 自定义�?��?符的开始字符，左�?�括�?��?�为 "{" 和 "}"
     * 
     * @see #parse(String, String, String, String)
     */
    public static Tmpl parse(String tmpl, final String startChar) {
        return parse(tmpl, startChar, "{", "}");
    }

    /**
     * @see #exec(String, Pattern, int, int, NutBean, boolean)
     */
    public static String exec(String tmpl, NutBean context) {
        return exec(tmpl, null, -1, -1, null, context, true);
    }

    /**
     * @see #exec(String, Pattern, int, int, NutBean, boolean)
     */
    public static String exec(String tmpl, NutBean context, boolean showKey) {
        return exec(tmpl, null, -1, -1, null, context, showKey);
    }

    /**
     * @see #parse(String, Pattern, int, int, TmplEscapeStr)
     */
    public static String exec(String tmpl,
                              Pattern ptn,
                              int groupIndex,
                              int escapeIndex,
                              TmplEscapeStr getEscapeStr,
                              NutBean context,
                              boolean showKey) {
        return parse(tmpl, ptn, groupIndex, escapeIndex, getEscapeStr).render(context, showKey);
    }

    /**
     * @see #parse(String, String, String, String)
     */
    public static String exec(String tmpl,
                              String startChar,
                              String leftBrace,
                              String rightBrace,
                              NutBean context,
                              boolean showKey) {
        return parse(tmpl, startChar, leftBrace, rightBrace).render(context, showKey);
    }

    /**
     * @see #parse(String, String)
     */
    public static String exec(String tmpl, String startChar, NutBean context, boolean showKey) {
        return parse(tmpl, startChar).render(context, showKey);
    }

    private Pattern _P;
    int groupIndex;
    int escapeIndex;
    private TmplEscapeStr getEscapeStr;
    private List<TmplEle> list;
    private List<String> keys;

    private Tmpl() {
        list = new LinkedList<TmplEle>();
        keys = new LinkedList<String>();
    }

    private Tmpl(Pattern ptn, int grpIdx, int escIdx, TmplEscapeStr getEscapeStr) {
        this();
        // 默认的模�?��?��?符
        if (null == ptn) {
            // _P =
            // Pattern.compile("((?<![$])[$][{]([^}]+)[}])|([$]([$][{][^}]+[}]))");
            // this.groupIndex = 2;
            // this.escapeIndex = 4;

            _P = Pattern.compile("((?<![$])[$][{]([^}]+)[}])|([$][$])");
            this.groupIndex = 2;
            this.escapeIndex = 3;
            this.getEscapeStr = new TmplEscapeStr() {
                public String get(Matcher m) {
                    return "$";
                }
            };
        }
        // 自定义的�?��?符
        else {
            _P = ptn;
            this.groupIndex = grpIdx;
            this.escapeIndex = escIdx;
            this.getEscapeStr = getEscapeStr;
            if (null == this.getEscapeStr) {
                this.getEscapeStr = new TmplEscapeStr() {
                    public String get(Matcher m) {
                        return m.group(escapeIndex).substring(0, 1);
                    }
                };
            }
        }
    }

    private Tmpl(String tmpl,
                 Pattern ptn,
                 int groupIndex,
                 int escapeIndex,
                 TmplEscapeStr getEscapeStr) {
        this(ptn, groupIndex, escapeIndex, getEscapeStr);

        // 开始解�?
        Matcher m = _P.matcher(tmpl);
        int lastIndex = 0;

        while (m.find()) {
            int pos = m.start();
            // 看看是�?��?生�?�?��?对象
            if (pos > lastIndex) {
                list.add(new TmplStaticEle(tmpl.substring(lastIndex, pos)));
            }

            // 看看是逃逸呢，还是匹�?上了
            String s_escape = this.escapeIndex > 0 ? m.group(this.escapeIndex) : null;
            String s_match = m.group(this.groupIndex);

            // 如果是逃逸
            if (!Strings.isBlank(s_escape)) {
                String esc_str = this.getEscapeStr.get(m);
                list.add(new TmplStaticEle(esc_str));
            }
            // �?�则分�?键
            else {
                // 如果是 `=` 开头，直接就作为字符串好了
                // 这个特殊处�?�，�?�以用�?�把�?��?符和 El 结�?�起�?�
                // 如果想输出一个�?��?符是 El 表达�?，那么写个 = ， 就会被认为是字符串默认�?留
                // 如果渲染的时候，对 key 进行判断，�?�现是 = 开头的 key，用 El 预先渲染并填入上下文就好了
                // TODO 这个是�?是应该�?�一个 TmplElEle 呢？
                if (s_match.startsWith("=")) {
                    keys.add(s_match);
                    list.add(new TmplStringEle(s_match, null, null));
                }
                // �?�则根�?��?��?符语法解�?一下
                else {
                    Matcher m2 = _P2.matcher(s_match);

                    if (!m2.find())
                        throw Lang.makeThrow("Fail to parse tmpl key '%s'", m.group(1));

                    String key = m2.group(1);
                    String type = Strings.sNull(m2.group(3), "string");
                    String fmt = m2.group(5);
                    String dft = m2.group(7);

                    // 记录键
                    keys.add(key);

                    // 创建元素
                    if ("string".equals(type)) {
                        list.add(new TmplStringEle(key, fmt, dft));
                    }
                    // int
                    else if ("int".equals(type)) {
                        list.add(new TmplIntEle(key, fmt, dft));
                    }
                    // long
                    else if ("long".equals(type)) {
                        list.add(new TmplLongEle(key, fmt, dft));
                    }
                    // boolean
                    else if ("boolean".equals(type)) {
                        list.add(new TmplBooleanEle(key, fmt, dft));
                    }
                    // float
                    else if ("float".equals(type)) {
                        list.add(new TmplFloatEle(key, fmt, dft));
                    }
                    // double
                    else if ("double".equals(type)) {
                        list.add(new TmplDoubleEle(key, fmt, dft));
                    }
                    // date
                    else if ("date".equals(type)) {
                        list.add(new TmplDateEle(key, fmt, dft));
                    }
                    // json
                    else if ("json".equals(type)) {
                        list.add(new TmplJsonEle(key, fmt, dft));
                    }
                    // �?��?�?�能
                    else {
                        throw Lang.impossible();
                    }
                }
            }
            // 记录
            lastIndex = m.end();
        }

        // 最�?�结尾是�?��?加入一个对象
        if (!(lastIndex >= tmpl.length())) {
            list.add(new TmplStaticEle(tmpl.substring(lastIndex)));
        }

    }

    public String render(NutBean context) {
        return render(context, true);
    }

    public String render(NutBean context, boolean showKey) {
        StringBuilder sb = new StringBuilder();
        if (null == context)
            context = new NutMap();
        for (TmplEle ele : list) {
            ele.join(sb, context, showKey);
        }
        return sb.toString();
    }

    public List<String> keys() {
        return this.keys;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TmplEle ele : list) {
            sb.append(ele);
        }
        return sb.toString();
    }

}
