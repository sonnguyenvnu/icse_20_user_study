package com.zhisheng.common.utils;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PropertiesUtil {
    /**
     * 默认属性集�?�（文件在Constants中�?置）
     */
    public static Properties defaultProp = null;
    /**
     * 所有读�?�过的属性集�?�
     * 文件�?? <-> 属性集�?�
     */
    public static Map<String, Properties> allProps = new HashMap<>();

    // �?始化默认的属性集�?�
    static {
        if (defaultProp == null && isExistProperties("application.properties")) {
            defaultProp = loadProperties("application.properties");
            allProps.put("application.properties", defaultProp);
        }
    }

    /**
     * 读�?�属性文件，并将读出�?�的属性集�?�添加到�?allProps】当中
     * 如果该属性文件之�?已读�?�过，则直接从�?allProps】获得
     */
    public static Properties getProperties(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            return defaultProp;
        } else {
            Properties prop = allProps.get(fileName);
            if (prop == null) {
                prop = loadProperties(fileName);
                allProps.put(fileName, prop);
            }

            return prop;
        }
    }

    /**
     * 解�?属性文件，将文件中的所有属性都读�?�到�?Properties】当中
     */
    protected static Properties loadProperties(String fileName) {
        Properties prop = new Properties();
        InputStream ins = null;
        ins = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (ins == null) {
            System.err.println("Can not find the resource!");
        } else {
            try {
                prop.load(ins);
            } catch (IOException e) {
                System.err.println("An error occurred when reading from the input stream, " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("The input stream contains a malformed Unicode escape sequence, " + e.getMessage());
            }
        }
        return prop;
    }

    public static Boolean isExistProperties(String filePath) {
        URL resource = PropertiesUtil.class.getClassLoader().getResource(filePath);
        return resource != null;
    }

    /**
     * 从指定的属性文件中获�?��?一属性值
     * 如果属性文件�?存在该属性则返回 null
     */
    public static String getProperty(String fileName, String name) {
        return getProperties(fileName).getProperty(name);
    }

    /**
     * 从默认的属性文件中获�?��?一属性值
     * 如果属性文件�?存在该属性则返回 null
     */
    public static String getProperty(String name) {
        return getProperties(null).getProperty(name);
    }

    /**
     * @param properties
     * @return
     */
    public static Map<String, String> getPropertyMap(Properties properties) {
        return properties.values()
                .stream()
                .collect(Collectors.toMap(k -> k.toString(), v -> v.toString()));
    }

    /**
     * @return
     */
    public static Map<String, String> getPropertyMap() {
        Map<String, String> allPropsMap = new HashMap<>();
        allProps.values().forEach(props -> {
            final Map<String, String> mapProperties = getPropertyMap(props);
            allPropsMap.putAll(mapProperties);
        });
        return allPropsMap;
    }

    /**
     * 获�?�所有的 key
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<String> getkeys(String fileName) throws IOException {
        Properties prop = new Properties();
        //获�?�输入�?
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        //加载进去
        prop.load(in);
        Set keyValue = prop.keySet();
        List<String> list = new ArrayList<>();
        for (Iterator it = keyValue.iterator(); it.hasNext();)
        {
            String key = (String) it.next();
            list.add(key);
        }
        return list;
    }

    //test
    public static void main(String[] args) throws IOException {
        List<String> keys = getkeys("application.properties");
        System.out.println(keys);
        for (String key:keys) {
            String value = PropertiesUtil.getProperty("application.properties", key);
            System.out.println(value);
        }
        loadProperties("application.properties").keySet().forEach(it -> System.out.println(it));
        getkeys("application.properties").forEach(it -> System.out.println(it));
        getProperties("application.properties").keySet().forEach(it-> System.out.println(it));
    }
}
