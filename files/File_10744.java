package com.vondear.rxtool;

import android.os.Build;
import android.text.Html;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author vondear
 * @date 2016/1/24
 * 编�?解�?相关工具类
 */
public class RxEncodeTool {

    /**
     * URL编�?
     * <p>若想自己指定字符集,�?�以使用{@link #urlEncode(String input, String charset)}方法</p>
     *
     * @param input �?编�?的字符
     * @return 编�?为UTF-8的字符串
     */
    public static String urlEncode(String input) {
        return urlEncode(input, "UTF-8");
    }

    /**
     * URL编�?
     * <p>若系统�?支�?指定的编�?字符集,则直接将input原样返回</p>
     *
     * @param input   �?编�?的字符
     * @param charset 字符集
     * @return 编�?为字符集的字符串
     */
    public static String urlEncode(String input, String charset) {
        try {
            return URLEncoder.encode(input, charset);
        } catch (UnsupportedEncodingException e) {
            return input;
        }
    }

    /**
     * URL解�?
     * <p>若想自己指定字符集,�?�以使用 {@link #urlDecode(String input, String charset)}方法</p>
     *
     * @param input �?解�?的字符串
     * @return URL解�?�?�的字符串
     */
    public static String urlDecode(String input) {
        return urlDecode(input, "UTF-8");
    }

    /**
     * URL解�?
     * <p>若系统�?支�?指定的解�?字符集,则直接将input原样返回</p>
     *
     * @param input   �?解�?的字符串
     * @param charset 字符集
     * @return URL解�?为指定字符集的字符串
     */
    public static String urlDecode(String input, String charset) {
        try {
            return URLDecoder.decode(input, charset);
        } catch (UnsupportedEncodingException e) {
            return input;
        }
    }

    /**
     * Base64编�?
     *
     * @param input �?编�?的字符串
     * @return Base64编�?�?�的字符串
     */
    public static byte[] base64Encode(String input) {
        return base64Encode(input.getBytes());
    }

    /**
     * Base64编�?
     *
     * @param input �?编�?的字节数组
     * @return Base64编�?�?�的字符串
     */
    public static byte[] base64Encode(byte[] input) {
        return Base64.encode(input, Base64.NO_WRAP);
    }

    /**
     * Base64编�?
     *
     * @param input �?编�?的字节数组
     * @return Base64编�?�?�的字符串
     */
    public static String base64Encode2String(byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * Base64解�?
     *
     * @param input �?解�?的字符串
     * @return Base64解�?�?�的字符串
     */
    public static byte[] base64Decode(String input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64解�?
     *
     * @param input �?解�?的字符串
     * @return Base64解�?�?�的字符串
     */
    public static byte[] base64Decode(byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64URL安全编�?
     * <p>将Base64中的URL�?�法字符�?,/=转为其他字符, �?RFC3548</p>
     *
     * @param input �?Base64URL安全编�?的字符串
     * @return Base64URL安全编�?�?�的字符串
     */
    public static byte[] base64UrlSafeEncode(String input) {
        return Base64.encode(input.getBytes(), Base64.URL_SAFE);
    }

    /**
     * Html编�?
     *
     * @param input �?Html编�?的字符串
     * @return Html编�?�?�的字符串
     */
    public static String htmlEncode(String input) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return Html.escapeHtml(input);
        } else {
            // �?�照Html.escapeHtml()中代�?
            StringBuilder out = new StringBuilder();
            for (int i = 0, len = input.length(); i < len; i++) {
                char c = input.charAt(i);
                if (c == '<') {
                    out.append("&lt;");
                } else if (c == '>') {
                    out.append("&gt;");
                } else if (c == '&') {
                    out.append("&amp;");
                } else if (c >= 0xD800 && c <= 0xDFFF) {
                    if (c < 0xDC00 && i + 1 < len) {
                        char d = input.charAt(i + 1);
                        if (d >= 0xDC00 && d <= 0xDFFF) {
                            i++;
                            int codepoint = 0x010000 | (int) c - 0xD800 << 10 | (int) d - 0xDC00;
                            out.append("&#").append(codepoint).append(";");
                        }
                    }
                } else if (c > 0x7E || c < ' ') {
                    out.append("&#").append((int) c).append(";");
                } else if (c == ' ') {
                    while (i + 1 < len && input.charAt(i + 1) == ' ') {
                        out.append("&nbsp;");
                        i++;
                    }
                    out.append(' ');
                } else {
                    out.append(c);
                }
            }
            return out.toString();
        }
    }

    /**
     * Html解�?
     *
     * @param input 待解�?的字符串
     * @return Html解�?�?�的字符串
     */
    public static String htmlDecode(String input) {
        return Html.fromHtml(input).toString();
    }
}
