package org.nutz.lang.util;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;

import org.nutz.lang.Encoding;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

/**
 * �?盘�?作的帮助函数集�?�
 * 
 * @author zozoh(zozohtnt@gmail.com)
 * @author bonyfish(mc02cxj@gmail.com)
 */
public abstract class Disks {

    /**
     * 一个 Vistor 模�?的目录深层�??历
     * 
     * @param f
     *            �?�??历的目录或者文件，如果是目录，深层�??历，�?�则，�?�访问一次文件
     * @param fv
     *            对文件�?进行的�?作
     * @param filter
     *            �??历目录时，哪些文件应该被忽略
     * @return �??历的文件个数
     */
    public static int visitFile(File f, FileVisitor fv, FileFilter filter) {
        int re = 0;
        if (f.isFile()) {
            fv.visit(f);
            re++;
        } else if (f.isDirectory()) {
            File[] fs = null == filter ? f.listFiles() : f.listFiles(filter);
            if (fs != null)
                for (File theFile : fs)
                    re += visitFile(theFile, fv, filter);
        }
        return re;
    }

    /**
     * 一个 Vistor 模�?的目录深层�??历, 包�?�目录也会返回
     * 
     * @param f
     *            �?�??历的目录或者文件，如果是目录，深层�??历，�?�则，�?�访问一次文件
     * @param fv
     *            对文件�?进行的�?作
     * @param filter
     *            �??历目录时，哪些文件应该被忽略
     * @return �??历的文件（目录）个数
     */
    public static int visitFileWithDir(File f, FileVisitor fv, FileFilter filter) {
        int re = 0;
        fv.visit(f);
        re++;
        if (f.isDirectory()) {
            File[] fs = null == filter ? f.listFiles() : f.listFiles(filter);
            if (fs != null)
                for (File theFile : fs)
                    re += visitFileWithDir(theFile, fv, filter);
        }
        return re;
    }

    /**
     * 将两个文件对象比较，得出相对路径
     * 
     * @param base
     *            基础文件对象
     * @param file
     *            相对文件对象
     * @return 相对于基础文件对象的相对路径
     */
    public static String getRelativePath(File base, File file) {
        String pathBase = base.getAbsolutePath();
        if (base.isDirectory())
            pathBase += "/";

        String pathFile = file.getAbsolutePath();
        if (file.isDirectory())
            pathFile += "/";

        return getRelativePath(pathBase, pathFile);
    }

    /**
     * 将两个路径比较，得出相对路径
     * 
     * @param base
     *            基础路径，以 '/' 结�?�，表示目录
     * @param path
     *            相对文件路径，以 '/' 结�?�，表示目录
     * @return 相对于基础路径对象的相对路径
     */
    public static String getRelativePath(String base, String path) {
        return getRelativePath(base, path, "./");
    }

    /**
     * 将两个路径比较，得出相对路径
     * 
     * @param base
     *            基础路径，以 '/' 结�?�，表示目录
     * @param path
     *            相对文件路径，以 '/' 结�?�，表示目录
     * @param equalPath
     *            如果两个路径相等，返回什么，通常为 "./"。 你也�?�以用 "" 或者 "." 或者�?便什么字符串�?�表示
     * 
     * @return 相对于基础路径对象的相对路径
     */
    public static String getRelativePath(String base, String path, String equalPath) {
        // 如果两个路径相等
        if (base.equals(path) || "./".equals(path) || ".".equals(path)) {
            return equalPath;
        }

        // 开始判断
        String[] bb = Strings.splitIgnoreBlank(getCanonicalPath(base), "[\\\\/]");
        String[] ff = Strings.splitIgnoreBlank(getCanonicalPath(path), "[\\\\/]");
        int len = Math.min(bb.length, ff.length);
        int pos = 0;
        for (; pos < len; pos++)
            if (!bb[pos].equals(ff[pos]))
                break;

        // �?明路径是相等的
        if (len == pos && bb.length == ff.length) {
            return equalPath;
        }

        // 开始查找�?�?�
        int dir = 1;
        if (base.endsWith("/"))
            dir = 0;

        StringBuilder sb = new StringBuilder(Strings.dup("../", bb.length - pos - dir));
        sb.append(Lang.concat(pos, ff.length - pos, '/', ff));
        if (path.endsWith("/"))
            sb.append('/');
        return sb.toString();
    }

    /**
     * 获�?�两个路径从头部开始计算的交集
     * 
     * @param ph0
     *            路径1
     * @param ph1
     *            路径2
     * @param dft
     *            如果两个路径完全没有相交，那么返回什么
     * @return 两个路径的交集
     */
    public static String getIntersectPath(String ph0, String ph1, String dft) {
        // 木�?�能有交集
        if (null == ph0 || null == ph1)
            return dft;

        String[] ss0 = Strings.splitIgnoreBlank(ph0, "[\\\\/]");
        String[] ss1 = Strings.splitIgnoreBlank(ph1, "[\\\\/]");

        int pos = 0;
        int len = Math.min(ss0.length, ss1.length);
        for (; pos < len; pos++) {
            if (!ss0[pos].equals(ss1[pos]))
                break;
        }

        // 木有交集
        if (pos == 0)
            return dft;

        // 得到
        String re = Lang.concat(0, pos, "/", ss0).toString();

        // 需�?补全�?��?�的 "/" �?�
        if (ph0.endsWith("/") && ph1.endsWith("/"))
            return re + "/";

        return re;
    }

    /**
     * 整�?�路径。 将会�?�并路径中的 ".."
     * 
     * @param path
     *            路径
     * @return 整�?��?�的路径
     */
    public static String getCanonicalPath(String path) {
        if (Strings.isBlank(path))
            return path;
        String[] pa = Strings.splitIgnoreBlank(path, "[\\\\/]");
        LinkedList<String> paths = new LinkedList<String>();
        for (String s : pa) {
            if ("..".equals(s)) {
                if (paths.size() > 0)
                    paths.removeLast();
                continue;
            }
            if (".".equals(s)) {
                // pass
            } else {
                paths.add(s);
            }
        }

        StringBuilder sb = Lang.concat("/", paths);
        if (path.startsWith("/"))
            sb.insert(0, '/');
        if (path.endsWith("/"))
            sb.append('/');
        return sb.toString();
    }

    /**
     * @return 当�?账户的主目录全路径
     */
    public static String home() {
        return System.getProperty("user.home");
    }

    /**
     * @param path
     *            相对用户主目录的路径
     * @return 相对用户主目录的全路径
     */
    public static String home(String path) {
        return home() + path;
    }

    /**
     * 获�?�一个路径的�?对路径。如果该路径�?存在，则返回null
     * 
     * @param path
     *            路径
     * @return �?对路径
     */
    public static String absolute(String path) {
        return absolute(path, ClassTools.getClassLoader(), Encoding.defaultEncoding());
    }

    /**
     * 获�?�一个路径的�?对路径。如果该路径�?存在，则返回null
     * 
     * @param path
     *            路径
     * @param klassLoader
     *            �?�考 ClassLoader
     * @param enc
     *            路径编�?方�?
     * @return �?对路径
     */
    public static String absolute(String path, ClassLoader klassLoader, String enc) {
        path = normalize(path, enc);
        if (Strings.isEmpty(path))
            return null;

        File f = new File(path);
        if (!f.exists()) {
            URL url = null;
            try {
                url = klassLoader.getResource(path);
                if (null == url)
                    url = Thread.currentThread().getContextClassLoader().getResource(path);
                if (null == url)
                    url = ClassLoader.getSystemResource(path);
            }
            catch (Throwable e) {}
            if (null != url)
                return normalize(url.getPath(), Encoding.UTF8);// 通过URL获�?�String,一律使用UTF-8编�?进行解�?
            return null;
        }
        return path;
    }

    /**
     * 让路径�?��?正常路径，将 ~ 替�?��?用户主目录
     * 
     * @param path
     *            路径
     * @return 正常化�?�的路径
     */
    public static String normalize(String path) {
        return normalize(path, Encoding.defaultEncoding());
    }

    /**
     * 让路径�?��?正常路径，将 ~ 替�?��?用户主目录
     * 
     * @param path
     *            路径
     * @param enc
     *            路径编�?方�?
     * @return 正常化�?�的路径
     */
    public static String normalize(String path, String enc) {
        if (Strings.isEmpty(path))
            return null;
        if (path.charAt(0) == '~')
            path = Disks.home() + path.substring(1);
        try {
            return URLDecoder.decode(path, enc);
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * �??历文件夹下以特定�?�缀结尾的文件(�?包括文件夹,�?包括.开头的文件)
     * 
     * @param path
     *            根路径
     * @param regex
     *            文件�??的正则表达�?
     * @param deep
     *            是�?�深层�??历
     * @param fv
     *            你所�??供的访问器,当然就是你自己的逻辑咯
     */
    public static final void visitFile(String path,
                                       final String regex,
                                       final boolean deep,
                                       final FileVisitor fv) {
        File d = Files.findFile(path);
        if (null == d)
            return;
        visitFile(d, new FileVisitor() {
            @Override
            public void visit(File f) {
                if (f.isDirectory())
                    return;
                fv.visit(f);
            }
        }, new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return deep;
                if (f.isHidden())
                    return false;
                if (Strings.isEmpty(regex))
                    return true;
                return Regex.match(regex, f.getName());
            }
        });
    }

    /**
     * �??历文件夹下以特定�?�缀结尾的文件与文件夹 �?包括.开头的文件
     * 
     * @param path
     *            根路径
     * @param regex
     *            文件�??的正则表达�?
     * @param deep
     *            是�?�深层�??历
     * @param fv
     *            你所�??供的访问器,当然就是你自己的逻辑咯
     */
    public static final void visitFileWithDir(String path,
                                              final String regex,
                                              final boolean deep,
                                              final FileVisitor fv) {
        File d = Files.findFile(path);
        if (null == d)
            return;
        visitFileWithDir(d, new FileVisitor() {
            @Override
            public void visit(File f) {
                fv.visit(f);
            }
        }, new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return deep;
                if (f.isHidden())
                    return false;
                if (Strings.isEmpty(regex))
                    return true;
                return f.getName().matches(regex);
            }
        });
    }

    /**
     * 将多个路径拼�?��?一个路径，他会自动去除�?�?的 "/"
     * 
     * <pre>
     * appendPath("a","b")  => "a/b"
     * appendPath("/a","b/c")  => "/a/b/c"
     * appendPath("/a/","/b/c")  => "/a/b/c"
     * </pre>
     * 
     * @param phs
     *            路径数组
     * @return 拼�?��?�的路径
     */
    public static String appendPath(String... phs) {
        String[] paths = Lang.without(phs, null);
        if (null != paths && paths.length > 0) {
            // zozoh: 嗯下�?�的逻辑木有必�?了�?�
            // if (null == paths[0])
            // paths[0] = "/";
            String str = Lang.concat("/", paths).toString();
            String[] ss = Strings.splitIgnoreBlank(str, "/");
            str = Lang.concat("/", ss).toString();
            if (paths[0].startsWith("/")) {
                return "/" + str;
            }
            return str;
        }
        return null;
    }
}
