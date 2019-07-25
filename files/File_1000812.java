package org.nutz.resource;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletContext;

import org.nutz.castor.Castors;
import org.nutz.lang.Encoding;
import org.nutz.lang.Lang;
import org.nutz.lang.Stopwatch;
import org.nutz.lang.Streams;
import org.nutz.lang.util.ClassTools;
import org.nutz.lang.util.Disks;
import org.nutz.lang.util.FileVisitor;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.impl.ErrorResourceLocation;
import org.nutz.resource.impl.FileResource;
import org.nutz.resource.impl.FileSystemResourceLocation;
import org.nutz.resource.impl.JarResource;
import org.nutz.resource.impl.JarResourceLocation;
import org.nutz.resource.impl.ResourceLocation;
import org.nutz.resource.impl.SimpleResource;
import org.nutz.resource.impl.WebClassesResourceLocation;

/**
 * 资�?扫�??的帮助函数集
 * 
 * @author zozoh(zozohtnt@gmail.com)
 * @author wendal(wendal1985@gmail.com)
 */
public class Scans {

    private static final String FLT_CLASS = "^.+[.]class$";

    private static final Log log = Logs.get();

    private static Scans me = new Scans();

    private Map<String, ResourceLocation> locations = new LinkedHashMap<String, ResourceLocation>();

    // 通过/META-INF/MANIFEST.MF等标记文件,获知所有jar文件的路径
    protected String[] referPaths = new String[]{    "META-INF/MANIFEST.MF",
                                        "log4j.properties",
                                        ".nutz.resource.mark"};

    /**
     * 在Web环境中使用Nutz的任何功能,都应该先调用这个方法,以�?始化资�?扫�??器
     * <p/>
     * 调用一次就�?�以了
     */
	public Scans init(final ServletContext sc) {
	    Stopwatch sw = Stopwatch.begin();
        // 获�?�classes文件夹的路径, 优先级为125
	    String classesPath = sc.getRealPath("/WEB-INF/classes");
	    if (classesPath == null)
	        addResourceLocation(new WebClassesResourceLocation(sc));
	    else {
	        ResourceLocation rc = ResourceLocation.file(new File(classesPath));
	        if (rc instanceof FileSystemResourceLocation)
	            ((FileSystemResourceLocation)rc).priority = 125;
	        addResourceLocation(rc);
	    }

        // 获�?�lib文件夹中的全部jar, 优先级是50
        Set<String> jars = sc.getResourcePaths("/WEB-INF/lib/");
        if (jars != null) {// 这个文件夹�?一定存在,尤其是Maven的WebApp项目
            for (String path : jars) {
                if (!path.endsWith(".jar"))
                    continue;
                try {
                    addResourceLocation(new JarResourceLocation(sc.getResource(path)));
                }
                catch (Exception e) {
                    log.debug("parse jar fail >> " + e.getMessage());
                }
            }
        }
        sw.stop();
        printLocations(sw);
        return this;
    }

    public List<NutResource> loadResource(String regex, String... paths) {
        List<NutResource> list = new LinkedList<NutResource>();
        for (String path : paths) {
            list.addAll(scan(path, regex));
        }
        // 如果找�?到?
        if (list.size() < 1 && paths.length > 0)
            throw Lang.makeThrow(    RuntimeException.class,
                                    "folder or file like '%s' no found in %s",
                                    regex,
                                    Castors.me().castToString(paths));
        return list;
    }

    public void registerLocation(Class<?> klass) {
        if (klass == null)
            return;
        try {
            registerLocation(klass.getProtectionDomain().getCodeSource().getLocation());
        }
        catch (Throwable e) { // Android上会死
            String classFile = klass.getName().replace('.', '/') + ".class";
            URL url = ClassTools.getClassLoader().getResource(classFile);
            if (url != null) { // 基本上�?�?�能为null
                String str = url.toString();
                try {
                    str = URLDecoder.decode(str, Encoding.UTF8);
                }
                catch (UnsupportedEncodingException e1) {
                    throw Lang.impossible();
                }
                str = str.substring(0, str.length() - classFile.length());
                try {
                    registerLocation(new URL(str));
                }
                catch (Throwable e2) {
                    if (log.isInfoEnabled())
                        log.info("Fail to registerLocation --> " + str, e);
                }
            }
        }
    }

    public void registerLocation(URL url) {
        if (url == null)
            return;
        addResourceLocation(makeResourceLocation(url));
    }

    protected ResourceLocation makeResourceLocation(URL url) {
        try {
            String str = url.toString();
            if (str.endsWith(".jar")) {
                return new JarResourceLocation(url);
            } else if (str.contains("jar!")) {
            	if (str.startsWith("jar:file:")) {
            		str = str.substring("jar:file:".length());
            	}
                return ResourceLocation.jar(str.substring(0, str.lastIndexOf("jar!") + 3));
            } else if (str.startsWith("file:")) {
                return ResourceLocation.file(new File(url.getFile()));
            } else {
                if (str.startsWith("jar:file:"))
                    return ResourceLocation.jar(str.substring(str.indexOf('!')));
                if (log.isDebugEnabled())
                    log.debug("Unkown URL " + url);
                //return ResourceLocation.file(new File(url.toURI()));
            }
        }
        catch (Throwable e) {
            if (log.isInfoEnabled())
                log.info("Fail to registerLocation --> " + url, e);
        }
        return ErrorResourceLocation.make(url);
    }

    public List<NutResource> scan(String src) {
        return scan(src, null);
    }

    /**
     * 在�?盘目录或者 CLASSPATH(包括 jar) 中�?�索资�?
     * <p/>
     * <b>核心方法</b>
     * 
     * @param src
     *            起始路径
     * @param regex
     *            资�?�??需�?匹�?的正则表达�?
     * @return 资�?列表
     */
    public List<NutResource> scan(String src, String regex) {
        if (src.isEmpty())
            throw new RuntimeException("emtry src is NOT allow");
        if ("/".equals(src))
            throw new RuntimeException("root path is NOT allow");
        List<NutResource> list = new ArrayList<NutResource>();
        Pattern pattern = regex == null ? null : Pattern.compile(regex);
        // 先看看是�?是文件系统上一个具体的文件
        if (src.startsWith("~/"))
            src = Disks.normalize(src);
        File srcFile = new File(src);
        if (srcFile.exists()) {
            if (srcFile.isDirectory()) {
                Disks.visitFile(srcFile,
                                new ResourceFileVisitor(list, src, 250),
                                new ResourceFileFilter(pattern));
            } else {
                list.add(new FileResource(src, srcFile).setPriority(250));
            }
        }
        for (ResourceLocation location : locations.values()) {
            location.scan(src, pattern, list);
        }
        // 如果啥都没找到,那么,用增强扫�??
        if (list.isEmpty()) {
            try {
                Enumeration<URL> enu = ClassTools.getClassLoader().getResources(src);
                if (enu != null && enu.hasMoreElements()) {
                    while (enu.hasMoreElements()) {
                        try {
                            URL url = enu.nextElement();
                            ResourceLocation loc = makeResourceLocation(url);
                            if (url.toString().contains("jar!"))
                                loc.scan(src, pattern, list);
                            else
                                loc.scan("", pattern, list);
                        }
                        catch (Throwable e) {
                            if (log.isTraceEnabled())
                                log.trace("", e);
                        }
                    }
                }
            }
            catch (Throwable e) {
                if (log.isDebugEnabled())
                    log.debug("Fail to run deep scan!", e);
            }
            // �?然是空?
            if (list.isEmpty() && !src.endsWith("/")) {
                try {
                    ClassLoader classLoader = getClass().getClassLoader();
                    InputStream tmp = classLoader.getResourceAsStream(src + "/");
                    if (tmp != null) {
                        tmp.close();
                    } else {
                        InputStream ins = classLoader.getResourceAsStream(src);
                        if (ins != null) {
                            list.add(new SimpleResource(src, src, ins));
                        }
                    }
                }
                catch (Exception e) {
                }
            }
        }
        List<NutResource> _list = new ArrayList<NutResource>();
        OUT: for (NutResource nr : list) {
            Iterator<NutResource> it = _list.iterator();
            while (it.hasNext()) {
                NutResource nr2 = it.next();
                if (nr.equals(nr2)) {
                    if (nr.priority > nr2.priority) {
                        it.remove();
                    } else {
                        continue OUT;
                    }
                }
            }
            _list.add(nr);
        }
        list = _list;
        Collections.sort(list);
        if (log.isDebugEnabled())
            log.debugf("Found %s resource by src( %s ) , regex( %s )", list.size(), src, regex);
        return list;
    }

    public List<Class<?>> scanPackage(Class<?> classZ) {
        return scanPackage(classZ.getPackage().getName(), FLT_CLASS);
    }

    public List<Class<?>> scanPackage(Class<?> classZ, String regex) {
        return scanPackage(classZ.getPackage().getName(), regex);
    }

    /**
     * �?�索并返回给定包下所有的类（递归）
     * 
     * @param pkg
     *            包�??或者包路径
     */
    public List<Class<?>> scanPackage(String pkg) {
        return scanPackage(pkg, FLT_CLASS);
    }

    /**
     * �?�索给定包下所有的类（递归），并返回所有符�?�正则�?�??述的类
     * 
     * @param pkg
     *            包�??或者包路径
     * @param regex
     *            正则表达�?，请注�?你需�?匹�?的�??称为 'xxxx.class' 而�?仅仅是类�??，从而�?�?选出的对象都是类文件
     */
    public List<Class<?>> scanPackage(String pkg, String regex) {
        String packagePath = pkg.replace('.', '/').replace('\\', '/');
        if (!packagePath.endsWith("/"))
            packagePath += "/";
        return rs2class(pkg, scan(packagePath, regex));
    }

    public static boolean isInJar(File file) {
        return isInJar(file.getAbsolutePath());
    }

    public static boolean isInJar(String filePath) {
        return filePath.contains(".jar!");
    }

    public static NutResource makeJarNutResource(File file) {
        return makeJarNutResource(file.getAbsolutePath());
    }

    public static NutResource makeJarNutResource(String filePath) {
        JarEntryInfo jeInfo = new JarEntryInfo(filePath);
        try {
            ZipInputStream zis = makeZipInputStream(jeInfo.getJarPath());
            ZipEntry ens = null;
            while (null != (ens = zis.getNextEntry())) {
                if (ens.isDirectory())
                    continue;
                if (jeInfo.getEntryName().equals(ens.getName())) {
                    return makeJarNutResource(jeInfo.getJarPath(), ens.getName(), "");
                }
            }
        }
        catch (IOException e) {}
        return null;
    }

    public static NutResource makeJarNutResource(    final String jarPath,
                                                    final String entryName,
                                                    final String base) throws IOException {
        NutResource nutResource = new JarResource(jarPath, entryName);
        if (entryName.equals(base))
            nutResource.setName(entryName);
        else
            nutResource.setName(entryName.substring(base.length()));
        nutResource.setSource(jarPath + ":" + entryName);
        return nutResource;
    }

    public static ZipInputStream makeZipInputStream(String jarPath) throws MalformedURLException,
            IOException {
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream(jarPath));
        }
        catch (IOException e) {
            zis = new ZipInputStream(new URL(jarPath).openStream());
        }
        return zis;
    }
    
    public static final Scans me() {
        return me;
    }

    /**
     * 将一组 NutResource 转�?��? class 对象
     * 
     * @param packagePath
     *            包�?缀
     * @param list
     *            列表
     * @return 类对象列表
     */
    private static List<Class<?>> rs2class(String pkg, List<NutResource> list) {
        Set<Class<?>> re = new LinkedHashSet<Class<?>>(list.size());
        if (!list.isEmpty()) {
            for (NutResource nr : list) {
                if (!nr.getName().endsWith(".class") || nr.getName().endsWith("package-info.class")) {
                    continue;
                }
                // Class快速载入
                String className = pkg + "." + nr.getName().substring(0, nr.getName().length() - 6).replaceAll("[/\\\\]", ".");
                try {
                	Class<?> klass = Lang.loadClass(className);
                    re.add(klass);
					continue;
				}
				catch (Throwable e) {}
                // 失败了? �?试终�?方法,当然了,慢多了
                InputStream in = null;
                try {
                    in = nr.getInputStream();
                    className = ClassTools.getClassName(in);
                    if (className == null) {
                        if (log.isInfoEnabled())
                            log.infof("Resource can't map to Class, Resource %s", nr);
                        continue;
                    }
                    Class<?> klass = Lang.loadClass(className);
                    re.add(klass);
                }
                catch (Throwable e) {
                    if (log.isInfoEnabled())
                        log.info("Resource can't map to Class, Resource " + nr.getName());
                }
                finally {
                    Streams.safeClose(in);
                }
            }
        }
        return new ArrayList<Class<?>>(re);
    }

    public static class ResourceFileFilter implements FileFilter {
        public boolean accept(File f) {
            if (f.isDirectory()) {
                String fnm = f.getName().toLowerCase();
                // 忽略 SVN 和 CVS 文件,还有Git文件
                if (".svn".equals(fnm) || ".cvs".equals(fnm) || ".git".equals(fnm))
                    return false;
                return true;
            }
            if (f.isHidden())
                return false;
            return pattern == null || pattern.matcher(f.getName()).find();
        }

        private Pattern pattern;

        public ResourceFileFilter(Pattern pattern) {
            super();
            this.pattern = pattern;
        }
    }

    public static class ResourceFileVisitor implements FileVisitor {
        public void visit(File f) {
            list.add(new FileResource(base, f).setPriority(priority));
        }

        String base;
        List<NutResource> list;
        int priority;

        public ResourceFileVisitor(List<NutResource> list, String base, int priority) {
            super();
            this.list = list;
            this.base = base;
            this.priority = priority;
        }
    }

    protected Scans() {
        if (Lang.isAndroid) {
            if (log.isInfoEnabled())
                log.info("Running in Android , so nothing I can scan , just disable myself");
            return;
        }
        Stopwatch sw = Stopwatch.begin();
        // 当�?文件夹
        try {
            FileSystemResourceLocation rc = new FileSystemResourceLocation(new File(".").getAbsoluteFile().getCanonicalFile());
            rc.priority = 200;
            addResourceLocation(rc);
        } catch (Throwable e) {
        }
        // 推测一下nutz自身所在的�?置
        //registerLocation(Nutz.class);
        ClassLoader cloader = ClassTools.getClassLoader();
        for (String referPath : referPaths) {
            try {
                Enumeration<URL> urls = cloader.getResources(referPath);
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    String url_str = url.toString();
                    if (url_str.contains("jar!")) {
                        String tmp = url_str.substring(0, url_str.lastIndexOf("jar!") + 3);
                        if (tmp.startsWith("jar:"))
                            tmp = tmp.substring("jar:".length());
                        if (tmp.startsWith("file:/"))
                            tmp = tmp.substring("file:/".length());
                        if (tmp.contains("tomcat"))
                            continue;
                        if (tmp.contains("Java"))
                            continue;
                        //jars.add(tmp);
                    }
                    else
                        registerLocation(new URL(url_str.substring(0, url_str.length() - referPath.length())));
                }
            }
            catch (IOException e) {}
        }
        // 把ClassPath也扫�??一下
        try {
            String classpath = System.getProperties().getProperty("java.class.path");
            String[] paths = classpath.split(System.getProperties().getProperty("path.separator"));
            for (String pathZ : paths) {
                if (pathZ.endsWith(".jar"))
                    addResourceLocation(ResourceLocation.jar(pathZ));
                else
                    addResourceLocation(ResourceLocation.file(new File(pathZ)));
            }
        }
        catch (Throwable e) {
        }
        sw.stop();
        printLocations(sw);
    }
    
    
    public void addResourceLocation(ResourceLocation loc) {
        locations.put(loc.id(), loc);
    }
    
    protected void printLocations(Stopwatch sw) {
        if (log.isDebugEnabled()) {
            log.debugf("Locations count=%d time use %sms", locations.size(), sw.du());
        }
        if (log.isTraceEnabled()) {
            StringBuilder sb = new StringBuilder();
            for (ResourceLocation rc : locations.values()) {
                sb.append('\t').append(rc.toString()).append("\r\n");
            }
            log.trace("Locations for Scans:\n" + sb);
        }
    }
}
