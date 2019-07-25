/**
 * Copyright (c) 2015-2019, Michael Yang æ?¨ç¦?æµ· (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jboot.app.config;

import io.jboot.app.config.annotation.ConfigModel;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * é…?ç½®ç®¡ç?†ç±»
 */
public class JbootConfigManager {

    //å?¯åŠ¨å?‚æ•°
    private static Map<String, String> argMap;

    //jboot.properties å’Œ jboot-dev.properties ç­‰å†…å®¹
    private Properties mainProperties;

    private ConcurrentHashMap<String, Object> configCache = new ConcurrentHashMap<>();


    private static JbootConfigManager instance;

    public static JbootConfigManager me() {
        if (instance == null) {
            instance = new JbootConfigManager();
        }
        return instance;
    }

    private JbootConfigManager() {
        init();
    }


    private void init() {

        File jbf = new File(Utils.getRootClassPath(), "jboot.properties");
        if (!jbf.exists()) {
            mainProperties = new Properties();
        } else {
            mainProperties = new Prop("jboot.properties").getProperties();
        }

        String mode = getConfigValue("jboot.app.mode");

        if (Utils.isNotBlank(mode)) {
            String p = String.format("jboot-%s.properties", mode);
            if (new File(Utils.getRootClassPath(), p).exists()) {
                mainProperties.putAll(new Prop(p).getProperties());
            }
        }

    }


    public <T> T get(Class<T> clazz) {
        ConfigModel propertyConfig = clazz.getAnnotation(ConfigModel.class);
        if (propertyConfig == null) {
            return get(clazz, null, null);
        }
        return get(clazz, propertyConfig.prefix(), propertyConfig.file());
    }


    /**
     * èŽ·å?–é…?ç½®ä¿¡æ?¯ï¼Œå¹¶åˆ›å»ºå’Œèµ‹å€¼clazzå®žä¾‹
     *
     * @param clazz  æŒ‡å®šçš„ç±»
     * @param prefix é…?ç½®æ–‡ä»¶å‰?ç¼€
     * @param <T>
     * @return
     */
    public <T> T get(Class<T> clazz, String prefix, String file) {

        /**
         * å¼€å?‘æ¨¡å¼?ä¸‹ï¼Œçƒ­åŠ è½½ä¼šå¯¼è‡´ç”±äºŽConfigæ˜¯ä¸?å?Œçš„ ClassLoaderï¼Œ
         * å¦‚æžœèµ°ç¼“å­˜ä¼šClassè½¬åŒ–å¼‚å¸¸
         */
        if (isDevMode()) {
            return createConfigObject(clazz, prefix, file);
        }

        Object configObject = configCache.get(clazz.getName() + prefix);

        if (configObject == null) {
            synchronized (clazz) {
                if (configObject == null) {
                    configObject = createConfigObject(clazz, prefix, file);
                    configCache.put(clazz.getName() + prefix, configObject);
                }
            }
        }

        return (T) configObject;
    }

    public <T> T createConfigObject(Class<T> clazz, String prefix, String file) {

        Object configObject = Utils.newInstance(clazz);
        List<Method> setMethods = Utils.getClassSetMethods(clazz);
        if (setMethods != null) {
            for (Method method : setMethods) {

                String key = buildKey(prefix, method);
                String value = getConfigValue(key);

                if (Utils.isNotBlank(file)) {
                    try {
                        Prop prop = new Prop(file);
                        String filePropValue = getConfigValue(prop.getProperties(), key);
                        if (Utils.isNotBlank(filePropValue)) {
                            value = filePropValue;
                        }
                    } catch (Throwable ex) {
                    }
                }

                try {
                    if (Utils.isNotBlank(value)) {
                        Object val = convert(method.getParameterTypes()[0], value);
                        method.invoke(configObject, val);
                    }
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            }
        }

        return (T) configObject;
    }


    public Object convert(Class<?> type, String s) {
        return Utils.convert(type, s);
    }

    private String buildKey(String prefix, Method method) {
        String key = Utils.firstCharToLowerCase(method.getName().substring(3));
        if (Utils.isNotBlank(prefix)) {
            key = prefix.trim() + "." + key;
        }
        return key;
    }


    public String getConfigValue(String key) {
        return getConfigValue(mainProperties, key);
    }

    /**
     * èŽ·å?–å€¼çš„ä¼˜å…ˆé¡ºåº?ï¼š1ã€?å?¯åŠ¨é…?ç½®  2ã€?çŽ¯å¢ƒå?˜é‡?   3ã€?propertiesé…?ç½®æ–‡ä»¶
     *
     * @param key
     * @return
     */
    public String getConfigValue(Properties properties, String key) {

        //boot arg
        String value = getBootArg(key);
        if (Utils.isNotBlank(value)) {
            return value.trim();
        }

        //env
        value = System.getenv(key);
        if (Utils.isNotBlank(value)) {
            return value.trim();
        }

        //upperCase env
        // æŠŠxxx.xxx.xxx è½¬æ?¢ä¸º XXX_XXX_XXXï¼Œ
        // ä¾‹å¦‚ï¼šjboot.datasource.url è½¬æ?¢ä¸º JBOOT_DATASOURCE_URL
        String tempKey = key.toUpperCase().replace('.', '_');
        value = System.getenv(tempKey);
        if (Utils.isNotBlank(value)) {
            return value.trim();
        }

        //system property
        value = System.getProperty(key);
        if (Utils.isNotBlank(value)) {
            return value.trim();
        }

        //user properties
        value = (String) properties.get(key);
        if (Utils.isNotBlank(value)) {
            return value.trim();
        }

        return null;
    }


    /**
     * èŽ·å?–Jbooté»˜è®¤çš„é…?ç½®ä¿¡æ?¯
     *
     * @return
     */
    public Properties getProperties() {

        Properties properties = new Properties();
        properties.putAll(mainProperties);

        if (System.getenv() != null) {
            for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }

        if (System.getProperties() != null) {
            properties.putAll(System.getProperties());
        }

        if (getBootArgs() != null) {
            for (Map.Entry<String, String> entry : getBootArgs().entrySet()) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }

        return properties;
    }


    /**
     * è§£æž?å?¯åŠ¨å?‚æ•°
     *
     * @param args
     */
    public void parseArgs(String[] args) {
        if (args == null || args.length == 0) {
            return;
        }

        for (String arg : args) {
            int indexOf = arg.indexOf("=");
            if (arg.startsWith("--") && indexOf > 0) {
                String key = arg.substring(2, indexOf);
                String value = arg.substring(indexOf + 1);
                setBootArg(key, value);
            }
        }
    }

    public void setBootArg(String key, Object value) {
        if (argMap == null) {
            argMap = new HashMap<>();
        }
        argMap.put(key, value.toString());
    }

    /**
     * èŽ·å?–å?¯åŠ¨å?‚æ•°
     *
     * @param key
     * @return
     */
    public String getBootArg(String key) {
        if (argMap == null) return null;
        return argMap.get(key);
    }

    public Map<String, String> getBootArgs() {
        return argMap;
    }


    private Boolean devMode = null;

    public boolean isDevMode() {
        if (devMode == null) {
            String appMode = getConfigValue("jboot.app.mode");
            devMode = (null == appMode || "".equals(appMode.trim()) || "dev".equals(appMode));
        }
        return devMode;
    }

}
