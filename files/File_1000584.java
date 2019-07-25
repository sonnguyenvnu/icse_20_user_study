package org.nutz.lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.stream.StringOutputStream;

/**
 * 显示对象的信�?�，为日志以�?�调试�??供帮助的函数集
 * 
 * @author zozoh(zozohtnt@gmail.com)
 * @author wendal(wendal1985@gmail.com)
 * @author bonyfish(mc02cxj@gmail.com)
 */
public abstract class Dumps {

    /**
     * 显示 Matcher 的详细信�?�
     * 
     * @param m
     *            Matcher 对象，必须未执行过 find
     * @return 信�?�
     */
    public static String matcher(Matcher m) {
        if (m.find())
            return matcherFound(m);
        return "No found!";
    }

    /**
     * 显示 Matcher 的详细信�?�
     * 
     * @param m
     *            Matcher 对象，必须执行过 find
     * @return 信�?�
     */
    public static String matcherFound(Matcher m) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d/%d  Regin:%d/%d\n",
                                m.start(),
                                m.end(),
                                m.regionStart(),
                                m.regionEnd()));
        for (int i = 0; i <= m.groupCount(); i++)
            sb.append(String.format("%2d:[%3d,%3d) %s\n", i, m.start(i), m.end(i), m.group(i)));
        return sb.toString();
    }

    /**
     * 显示一个对象所有个 getter 函数返回，以�?� public 的 Field 的值
     * 
     * @param obj
     *            对象
     * @return 信�?�
     */
    public static String obj(Object obj) {
        if (null == obj)
            return "null";
        StringBuilder sb = new StringBuilder(obj.getClass().getName() + "\n\n[Fields:]");
        Mirror<?> mirror = Mirror.me(obj.getClass());
        for (Field f : mirror.getType().getFields())
            if (Modifier.isPublic(f.getModifiers()))
                try {
                    sb.append(String.format("\n\t%10s : %s", f.getName(), f.get(obj)));
                }
                catch (Exception e1) {
                    sb.append(String.format("\n\t%10s : %s", f.getName(), e1.getMessage()));
                }
        sb.append("\n\n[Methods:]");
        for (Method m : mirror.getType().getMethods())
            if (Modifier.isPublic(m.getModifiers()))
                if (m.getName().startsWith("get"))
                    if (m.getParameterTypes().length == 0)
                        try {
                            sb.append(String.format("\n\t%10s : %s", m.getName(), m.invoke(obj)));
                        }
                        catch (Exception e) {
                            sb.append(String.format("\n\t%10s : %s", m.getName(), e.getMessage()));
                        }
        return sb.toString();
    }

    /**
     * 显示 HTTP 内容的�??称空间
     */
    public static class HTTP {

        public static enum MODE {
            ALL, HEADER_ONLY, BODY_ONLY
        }

        /**
         * 详细显示一个 HTTP 请求的全部内容
         * 
         * @param req
         *            请求对象
         * @param ops
         *            内容的输出�?
         * @param mode
         *            显示 HTTP 头信�?�的模�?: MODE.ALL or MODE.HEADER_ONLY
         */
        public static void http(HttpServletRequest req, OutputStream ops, MODE mode) {
            InputStream ins;
            int b;
            try {
                /*
                 * Header
                 */
                if (MODE.ALL == mode || MODE.HEADER_ONLY == mode) {
                    StringBuilder sb = new StringBuilder();
                    Enumeration<?> ens = req.getHeaderNames();
                    while (ens.hasMoreElements()) {
                        String name = ens.nextElement().toString();
                        sb.append(name).append(": ").append(req.getHeader(name)).append("\r\n");
                    }
                    sb.append("\r\n");
                    ins = Lang.ins(sb);
                    while (-1 != (b = ins.read()))
                        ops.write(b);
                }
                /*
                 * Body
                 */
                if (MODE.ALL == mode || MODE.BODY_ONLY == mode) {
                    ins = req.getInputStream();
                    while (-1 != (b = ins.read()))
                        ops.write(b);
                    ins.close();
                }
                ops.flush();
                ops.close();
            }
            catch (IOException e) {
                throw Lang.wrapThrow(e);
            }
        }

        /**
         * 详细显示一个 HTTP 请求的全部内容
         * 
         * @param req
         *            请求对象
         * @param mode
         *            显示 HTTP 头信�?�的模�?: MODE.ALL or MODE.HEADER_ONLY
         * @return 一个文本字符串表示 HTTP 的全部内容
         */
        public static String http(HttpServletRequest req, MODE mode) {
            StringBuilder sb = new StringBuilder();
            OutputStream ops = new StringOutputStream(sb, req.getCharacterEncoding());
            http(req, ops, mode);
            return sb.toString();
        }

        public static void body(HttpServletRequest req, OutputStream ops) {
            http(req, ops, MODE.BODY_ONLY);
        }

        public static String body(HttpServletRequest req) {
            return http(req, MODE.BODY_ONLY);
        }

        public static void header(HttpServletRequest req, OutputStream ops) {
            http(req, ops, MODE.HEADER_ONLY);
        }

        public static String header(HttpServletRequest req) {
            return http(req, MODE.HEADER_ONLY);
        }

        public static void all(HttpServletRequest req, OutputStream ops) {
            http(req, ops, MODE.ALL);
        }

        public static String all(HttpServletRequest req) {
            return http(req, MODE.ALL);
        }
    }

}
