package info.xiaomo.core.untils;

import org.apache.commons.lang3.StringUtils;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 这是个正则表达�?应用类，用�?�匹�?和替�?�字串用的
 *
 * @author xiaomo
 */

public class RegExUtil {

    /**
     * 用户�??
     */
    public static final String USER_NAME = "^[a-zA-Z\\u4E00-\\u9FA5][a-zA-Z0-9_\\u4E00-\\u9FA5]{1,11}$";

    /**
     * 密�?
     */
    public static final String USER_PASSWORD = "^.{6,32}$";

    /**
     * 邮箱
     */
    public static final String EMAIL = "^\\w+([-+.]*\\w+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$";

    /**
     * 手机�?�
     */
    public static final String PHONE = "^1[34578]\\d{9}$";

    /**
     * 手机�?�或者邮箱
     */
    public static final String EMAIL_OR_PHONE = EMAIL + "|" + PHONE;

    /**
     * URL路径
     */
    public static final String URL = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})(:[\\d]+)?([\\/\\w\\.-]*)*\\/?$";

    /**
     * 身份�?校验，�?级校验，具体规则有一套算法
     */
    public static final String ID_CARD = "^\\d{15}$|^\\d{17}([0-9]|X)$";

    /**
     * 编译传入正则表达�?和字符串去匹�?,忽略大�?写
     *
     * @param regex        regex
     * @param beTestString beTestString
     */
    public static boolean match(String regex, String beTestString) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(beTestString);
        return matcher.matches();
    }

    /**
     * 编译传入正则表达�?在字符串中寻找，如果匹�?到则为true
     *
     * @param regex        regex
     * @param beTestString beTestString
     */
    public static boolean find(String regex, String beTestString) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(beTestString);
        return matcher.find();
    }

    /**
     * 编译传入正则表达�?在字符串中寻找，如果找到返回第一个结果<br/>
     * 找�?到返回null
     *
     * @param regex         regex
     * @param beFoundString beFoundString
     */
    public static String findResult(String regex, String beFoundString) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(beFoundString);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * �?�?手机�?�中间4�?
     *
     * @param phone phone
     * @return String
     */
    public static String encodePhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return "";
        }
        if (match(PHONE, phone)) {
            String begin = phone.substring(0, 3);
            String end = phone.substring(7, phone.length());
            return begin + "****" + end;
        }
        return phone;
    }

    /**
     * �?求大�?写都匹�?正则表达�?
     *
     * @param pattern 正则表达�?模�?
     * @param str     �?匹�?的字串
     * @return boolean值
     * @since 1.0
     */
    public static boolean ereg(String pattern, String str) throws PatternSyntaxException {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 匹�?且替�?�字串
     *
     * @param pattern 正则表达�?模�?
     * @param newstr  �?替�?�匹�?到的新字串
     * @param str     原始字串
     * @return 匹�?�?�的字符串
     * @since 1.0
     */

    public static String eregReplace(String pattern, String newstr, String str) throws PatternSyntaxException {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.replaceAll(newstr);
    }

    /**
     * 主�?用于模�?�中模�?�标记分�?函数 把查找到的元素加到vector中
     *
     * @param pattern 为正则表达�?模�?
     * @param str     原始字串
     * @return vector
     * @since 1.0
     */
    public static Vector<String> splitTags2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector<String> vector = new Vector<>();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while (m.find()) {
            vector.add(eregReplace("(\\[\\#)|(\\#\\])", "", m.group()));
        }
        return vector;
    }

    /**
     * 模�?�标记分�?函数
     * 功能主�?是把查找到的元素加到vector中
     *
     * @param pattern 为正则表达�?模�?
     * @param str     原始字串
     * @since 1.0
     */
    public static String[] splitTags(String pattern, String str) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        String[] array = new String[m.groupCount()];
        int i = 0;
        while (m.find()) {
            array[i] = eregReplace("(\\[\\#)|(\\#\\])", "", m.group());
            i++;
        }
        return array;
    }


    /**
     * 匹�?所有符�?�模�?�?求的字串并加到矢�?vector数组中
     *
     * @param pattern 为正则表达�?模�?
     * @param str     原始字串
     * @return vector
     * @since 1.0
     */
    public static Vector<String> regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector<String> vector = new Vector<>();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while (m.find()) {
            vector.add(m.group());
        }
        return vector;
    }

    /**
     * 匹�?所有符�?�模�?�?求的字串并加到字符串数组中
     *
     * @param pattern 为正则表达�?模�?
     * @param str     原始字串
     * @return array
     * @since 1.0
     */
    public static String[] regMatchAll2Array(String pattern, String str) throws PatternSyntaxException {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        String[] array = new String[m.groupCount()];
        int i = 0;
        while (m.find()) {
            array[i] = m.group();
            i++;
        }
        return array;
    }

    /**
     * 转义正则表达�?字符(之所以需�?将\和$字符用escapeDollarBackslash方法的方�?是因为用repalceAll是�?行的，简�?�的试试"$".repalceAll("\\$","\\\\$")你会�?�现这个调用会导致数组越界错误)
     *
     * @param original 为正则表达�?模�?
     * @return array
     * @since 1.0
     */
    public static String escapeDollarBackslash(String original) {
        StringBuilder buffer = new StringBuilder(original.length());
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c == '\\' || c == '$') {
                buffer.append("\\").append(c);
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * �??�?�指定字串的函数
     * 功能主�?是把查找到的元素
     *
     * @param pattern 为正则表达�?模�?
     * @param str     原始字串
     * @since 1.0
     */
    public static String fetchStr(String pattern, String str) {
        String returnValue = null;
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                returnValue = m.group();
            }
            return returnValue;
        } catch (PatternSyntaxException e) {
            return returnValue;
        }
    }


    public static void main(String[] args) {
        System.out.println(ereg(ID_CARD, "420325199210211911"));
    }
}
