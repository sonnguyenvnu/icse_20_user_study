package org.nutz.conf;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.el.opt.custom.CustomMake;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutType;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mapl.Mapl;
import org.nutz.repo.org.objectweb.asm.Opcodes;
import org.nutz.resource.NutResource;
import org.nutz.resource.Scans;

/**
 * �?置加载器<br/>
 * 一个通用的�?置加载器, 全局的加载�?置文件, 这样, 在所有地方都�?�以使用这些�?置信�?�了. 规则:<br/>
 * <ul>
 * <li>�?置文件使用JSON格�?.
 * <li>JSON第一层为�?置项键值对, KEY 为�?置项�??称, 值为�?置信�?�.
 * <li>使用文件数组, 或者文件目录的形�?, �?�以加载多个�?置文件
 * <li>�?�以使用 include 关键字�?�引用其它�?置文件, 值以数组形�?.
 * <li>多�?置文件的情况下�?�加载的�?置会覆盖之�?加载的�?置,include引用的�?置会覆盖引用�?的�?置.
 * <li>与JSON 相�?�, �?置项的值你�?�以转�?��?任�?你想�?的类型. 包括泛型, �?�以使用 {@link NutType}
 * </ul>
 * 
 * @author juqkai(juqkai@gmail.com)
 * 
 */
public class NutConf {

    private static final Log log = Logs.get();

    private static final String DEFAULT_CONFIG = "org/nutz/conf/NutzDefaultConfig.js";

    // 所有的�?置信�?�
    private Map<String, Object> map = new HashMap<String, Object>();

    // zozoh �?�利的�?，没必�?用这个�?� ...
    // private static final Lock lock = new ReentrantLock();

    private volatile static NutConf conf;

    private static NutConf me() {
        if (null == conf) {
            synchronized (NutConf.class) {
                if (null == conf)
                    conf = new NutConf();
            }
        }
        return conf;
    }

    private NutConf() {
        // 加载框架自己的一些�?置
        loadResource(DEFAULT_CONFIG);
    }

    public static void load(String... paths) {
        me().loadResource(paths);
        CustomMake.me().init();
    }

    /**
     * 加载资�?
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void loadResource(String... paths) {
        for (String path : paths) {
            List<NutResource> resources = Scans.me().scan(path, "\\.(js|json)$");

            for (NutResource nr : resources) {
                try {
                    Object obj = Json.fromJson(nr.getReader());
                    if (obj instanceof Map) {
                        Map m = (Map) obj;
                        map = (Map) Mapl.merge(map, m);
                        for (Object key : m.keySet()) {
                            if (key.equals("include")) {
                                map.remove("include");
                                List<String> include = (List) m.get("include");
                                loadResource(include.toArray(new String[include.size()]));
                            }
                        }
                    }
                }
                catch (Throwable e) {
                    if (log.isWarnEnabled())
                        log.warn("Fail to load config?! for " + nr.getName(), e);
                }
            }
        }
    }

    /**
     * 读�?�一个�?置项, 并转�?��?相应的类型.
     */
    public static Object get(String key, Type type) {
        return me().getItem(key, type);
    }

    /**
     * 读�?��?置项, 返回Map, List或者 Object. 具体返回什么, 请�?�考 JSON 规则
     */
    public static Object get(String key) {
        return me().getItem(key, null);
    }

    /**
     * 读�?�一个�?置项, 并转�?��?相应的类型.
     * 
     * @param key
     * @param type
     * @return
     */
    private Object getItem(String key, Type type) {
        if (null == map) {
            return null;
        }
        if (null == type) {
            return map.get(key);
        }
        return Mapl.maplistToObj(map.get(key), type);
    }

    /**
     * 清�?�所有�?置信�?�
     */
    public static void clear() {
        conf = null;
    }
    
    /**
     * 是�?��?�用FastClass机制,会�??高�??射的性能,如果需�?热部署,应关闭. 性能影�?低于10%
     */
    public static boolean USE_FASTCLASS = !Lang.isAndroid && Lang.JdkTool.getMajorVersion() <= 8;
    /**
     * 是�?�缓存Mirror,�?�?�FastClass机制使用,会�??高�??射的性能,如果需�?热部署,应关闭.  性能影�?低于10%
     */
    public static boolean USE_MIRROR_CACHE = true;
    /**
     * Map.map2object时的EL支�?,很少会用到,所以默认关闭. 若�?�用, Json.fromJson会有30%左�?�的性能�?�失
     */
    public static boolean USE_EL_IN_OBJECT_CONVERT = false;
    /**
     * 调试Scans类的开关.鉴于Scans已�?�?�常�?�谱,这个开关基本上没用处了
     */
    public static boolean RESOURCE_SCAN_TRACE = false;
    /**
     * 是�?��?许�?�法的Json转义符,属于兼容性�?置
     */
    public static boolean JSON_ALLOW_ILLEGAL_ESCAPE = true;
    /**
     * 若�?许�?�法的Json转义符,是�?�把转义符附加进目标字符串
     */
    public static boolean JSON_APPEND_ILLEGAL_ESCAPE = false;
    /**
     * Aop类是�?��?个Ioc容器都唯一,设置这个开关是因为wendal还�?确定会有什么影�?,暂时关闭状�?.
     */
    public static boolean AOP_USE_CLASS_ID = false;

    public static int AOP_CLASS_LEVEL = Opcodes.V1_6;

    public static boolean HAS_LOCAL_DATE_TIME;
    static {
        try {
            Class.forName("java.time.temporal.TemporalAccessor");
            HAS_LOCAL_DATE_TIME = true;
        }
        catch (Throwable e) {
        }
    }
    
    public static boolean AOP_ENABLED = !"false".equals(System.getProperty("nutz.aop.enable"));
    
    public static void set(String key, Object value) {
        if (value == null)
            me().map.remove(key);
        else
            me().map.put(key, value);
    }
    
    public static Object getOrDefault(String key, Object defaultValue) {
        Object re = me().map.get(key);
        if (re == null)
            return defaultValue;
        return re;
    }

    public static boolean SQLSERVER_USE_NVARCHAR = true;
    
    public static boolean DAO_USE_POJO_INTERCEPTOR = true;
}
