/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.common.utils;

import com.alipay.sofa.rpc.common.cache.ReflectCache;

/**
 * <p>类型转�?�工具类</p>
 * <p>调用端时将类�??述转�?�为字符串传输。�?务端将字符串转�?�为具体的类</p>
 * <pre>
 *     �?�?传递的时候值为�?�阅读格�?，而�?是jvm格�?（[Lxxx;）：
 *     普通：java.lang.String�?java.lang.String[]
 *     基本类型：int�?int[]
 *     内部类：com.alipay.example.Inner�?com.alipay.example.Inner[]
 *     匿�??类：com.alipay.example.Xxx$1�?com.alipay.example.Xxx$1[]
 *     本地类：com.alipay.example.Xxx$1Local�?com.alipay.example.Xxx$1Local[]
 *     �?员类：com.alipay.example.Xxx$Member�?com.alipay.example.Xxx$Member[]
 * �?�时Class.forName的时候�?�会解�?出Class。
 * </pre>
 * <p>
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class ClassTypeUtils {

    /**
     * Class[]转String[]
     *
     * @param typeStrs 对象�??述[]
     * @return Class[]
     */
    public static Class[] getClasses(String[] typeStrs) throws RuntimeException {
        if (CommonUtils.isEmpty(typeStrs)) {
            return new Class[0];
        } else {
            Class[] classes = new Class[typeStrs.length];
            for (int i = 0; i < typeStrs.length; i++) {
                classes[i] = getClass(typeStrs[i]);
            }
            return classes;
        }
    }

    /**
     * String转Class
     *
     * @param typeStr 对象�??述
     * @return Class[]
     */
    public static Class getClass(String typeStr) {
        Class clazz = ReflectCache.getClassCache(typeStr);
        if (clazz == null) {
            if ("void".equals(typeStr)) {
                clazz = void.class;
            } else if ("boolean".equals(typeStr)) {
                clazz = boolean.class;
            } else if ("byte".equals(typeStr)) {
                clazz = byte.class;
            } else if ("char".equals(typeStr)) {
                clazz = char.class;
            } else if ("double".equals(typeStr)) {
                clazz = double.class;
            } else if ("float".equals(typeStr)) {
                clazz = float.class;
            } else if ("int".equals(typeStr)) {
                clazz = int.class;
            } else if ("long".equals(typeStr)) {
                clazz = long.class;
            } else if ("short".equals(typeStr)) {
                clazz = short.class;
            } else {
                String jvmName = canonicalNameToJvmName(typeStr);
                clazz = ClassUtils.forName(jvmName);
            }
            ReflectCache.putClassCache(typeStr, clazz);
        }
        return clazz;
    }

    /**
     * 通用�??述转JVM�??述
     *
     * @param canonicalName 例如 int[]
     * @return JVM�??述 例如 [I;
     */
    public static String canonicalNameToJvmName(String canonicalName) {
        boolean isArray = canonicalName.endsWith("[]");
        if (isArray) {
            String t = ""; // 计数，看上几维数组
            while (isArray) {
                canonicalName = canonicalName.substring(0, canonicalName.length() - 2);
                t += "[";
                isArray = canonicalName.endsWith("[]");
            }
            if ("boolean".equals(canonicalName)) {
                canonicalName = t + "Z";
            } else if ("byte".equals(canonicalName)) {
                canonicalName = t + "B";
            } else if ("char".equals(canonicalName)) {
                canonicalName = t + "C";
            } else if ("double".equals(canonicalName)) {
                canonicalName = t + "D";
            } else if ("float".equals(canonicalName)) {
                canonicalName = t + "F";
            } else if ("int".equals(canonicalName)) {
                canonicalName = t + "I";
            } else if ("long".equals(canonicalName)) {
                canonicalName = t + "J";
            } else if ("short".equals(canonicalName)) {
                canonicalName = t + "S";
            } else {
                canonicalName = t + "L" + canonicalName + ";";
            }
        }
        return canonicalName;
    }

    /**
     * Class[]转String[] <br>
     * 注�?，得到的String�?�能�?能直接用于Class.forName，请使用getClasses(String[])�??�?�获�?�
     *
     * @param types Class[]
     * @return 对象�??述
     * @see #getClasses(String[]) 
     */
    public static String[] getTypeStrs(Class[] types) {
        return getTypeStrs(types, false);
    }

    /**
     * Class[]转String[] <br>
     * 注�?，得到的String�?�能�?能直接用于Class.forName，请使用getClasses(String[])�??�?�获�?�
     *
     * @param types Class[]
     * @param javaStyle JDK自带格�?，例如 int[], true的�?返回 [I; false的�?返回int[]            
     * @return 对象�??述
     * @see #getClasses(String[])
     */
    public static String[] getTypeStrs(Class[] types, boolean javaStyle) {
        if (CommonUtils.isEmpty(types)) {
            return StringUtils.EMPTY_STRING_ARRAY;
        } else {
            String[] strings = new String[types.length];
            for (int i = 0; i < types.length; i++) {
                strings[i] = javaStyle ? types[i].getName() : getTypeStr(types[i]);
            }
            return strings;
        }
    }

    /**
     * Class转String<br>
     * 注�?，得到的String�?�能�?能直接用于Class.forName，请使用getClass(String)�??�?�获�?�
     *
     * @param clazz Class
     * @return 对象
     * @see #getClass(String)
     */
    public static String getTypeStr(Class clazz) {
        String typeStr = ReflectCache.getTypeStrCache(clazz);
        if (typeStr == null) {
            if (clazz.isArray()) {
                String name = clazz.getName(); // 原始�??字：[Ljava.lang.String;
                typeStr = jvmNameToCanonicalName(name); // java.lang.String[]
            } else {
                typeStr = clazz.getName();
            }
            ReflectCache.putTypeStrCache(clazz, typeStr);
        }
        return typeStr;
    }

    /**
     * JVM�??述转通用�??述
     *
     * @param jvmName 例如 [I;
     * @return 通用�??述 例如 int[]
     */
    public static String jvmNameToCanonicalName(String jvmName) {
        boolean isArray = jvmName.charAt(0) == '[';
        if (isArray) {
            String cnName = StringUtils.EMPTY; // 计数，看上几维数组
            int i = 0;
            for (; i < jvmName.length(); i++) {
                if (jvmName.charAt(i) != '[') {
                    break;
                }
                cnName += "[]";
            }
            String componentType = jvmName.substring(i, jvmName.length());
            if ("Z".equals(componentType)) {
                cnName = "boolean" + cnName;
            } else if ("B".equals(componentType)) {
                cnName = "byte" + cnName;
            } else if ("C".equals(componentType)) {
                cnName = "char" + cnName;
            } else if ("D".equals(componentType)) {
                cnName = "double" + cnName;
            } else if ("F".equals(componentType)) {
                cnName = "float" + cnName;
            } else if ("I".equals(componentType)) {
                cnName = "int" + cnName;
            } else if ("J".equals(componentType)) {
                cnName = "long" + cnName;
            } else if ("S".equals(componentType)) {
                cnName = "short" + cnName;
            } else {
                cnName = componentType.substring(1, componentType.length() - 1) + cnName; // 对象的 去掉L
            }
            return cnName;
        }
        return jvmName;
    }
}
