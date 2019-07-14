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

import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Date;

/**
 * �??射工具类
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class ReflectUtils {

    /**
     * 是�?�默认类型，基本类型+string+date
     *
     * @param clazz the cls
     * @return the boolean
     */
    public static boolean isPrimitives(Class<?> clazz) {
        if (clazz.isArray()) { // 数组，检查数组类型
            return isPrimitiveType(clazz.getComponentType());
        }
        return isPrimitiveType(clazz);
    }

    private static boolean isPrimitiveType(Class<?> clazz) {
        return clazz.isPrimitive() // 基本类型
            // 基本类型的对象
            ||
            Boolean.class == clazz
            || Character.class == clazz
            || Number.class.isAssignableFrom(clazz)
            // string 或者 date
            || String.class == clazz
            || Date.class.isAssignableFrom(clazz);
    }

    /**
     * 得到类所在地�?�，�?�以是文件，也�?�以是jar包
     *
     * @param cls the cls
     * @return the code base
     */
    public static String getCodeBase(Class<?> cls) {

        if (cls == null) {
            return null;
        }
        ProtectionDomain domain = cls.getProtectionDomain();
        if (domain == null) {
            return null;
        }
        CodeSource source = domain.getCodeSource();
        if (source == null) {
            return null;
        }
        URL location = source.getLocation();
        if (location == null) {
            return null;
        }
        return location.getFile();
    }

    /**
     * 加载Method方法
     *
     * @param clazzName  类�??
     * @param methodName 方法�??
     * @param argsType   �?�数列表
     * @return Method对象
     */
    public static Method getMethod(String clazzName, String methodName, String[] argsType) {
        Class clazz = ClassUtils.forName(clazzName);
        Class[] classes = ClassTypeUtils.getClasses(argsType);
        return getMethod(clazz, methodName, classes);
    }

    /**
     * 加载Method方法
     *
     * @param clazz      类
     * @param methodName 方法�??
     * @param argsType   �?�数列表
     * @return Method对象
     * @since 5.4.0
     */
    public static Method getMethod(Class clazz, String methodName, Class... argsType) {
        try {
            return clazz.getMethod(methodName, argsType);
        } catch (NoSuchMethodException e) {
            throw new SofaRpcRuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 得到set方法
     *
     * @param clazz         类
     * @param property      属性
     * @param propertyClazz 属性
     * @return Method 方法对象
     */
    public static Method getPropertySetterMethod(Class clazz, String property, Class propertyClazz) {
        String methodName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
        try {
            return clazz.getMethod(methodName, propertyClazz);
        } catch (NoSuchMethodException e) {
            throw new SofaRpcRuntimeException("No setter method for " + clazz.getName() + "#" + property, e);
        }
    }

    /**
     * 得到get/is方法
     *
     * @param clazz    类
     * @param property 属性
     * @return Method 方法对象
     */
    public static Method getPropertyGetterMethod(Class clazz, String property) {
        String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
        Method method;
        try {
            method = clazz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            try {
                methodName = "is" + property.substring(0, 1).toUpperCase() + property.substring(1);
                method = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e1) {
                throw new SofaRpcRuntimeException("No getter method for " + clazz.getName() + "#" + property, e);
            }
        }
        return method;
    }

    protected static boolean isBeanPropertyReadMethod(Method method) {
        return method != null
            && Modifier.isPublic(method.getModifiers())
            && !Modifier.isStatic(method.getModifiers())
            && method.getReturnType() != void.class
            && method.getDeclaringClass() != Object.class
            && method.getParameterTypes().length == 0
            && (method.getName().startsWith("get") || method.getName().startsWith("is"))
            // 排除就�?�get和is的方法
            && (!"get".equals(method.getName()) && !"is".equals(method.getName()));
    }

    protected static String getPropertyNameFromBeanReadMethod(Method method) {
        if (isBeanPropertyReadMethod(method)) {
            if (method.getName().startsWith("get")) {
                return method.getName().substring(3, 4).toLowerCase()
                    + method.getName().substring(4);
            }
            if (method.getName().startsWith("is")) {
                return method.getName().substring(2, 3).toLowerCase()
                    + method.getName().substring(3);
            }
        }
        return null;
    }

    protected static boolean isBeanPropertyWriteMethod(Method method) {
        return method != null
            && Modifier.isPublic(method.getModifiers())
            && !Modifier.isStatic(method.getModifiers())
            && method.getDeclaringClass() != Object.class
            && method.getParameterTypes().length == 1
            && method.getName().startsWith("set")
            // 排除就�?�set的方法
            && !"set".equals(method.getName());
    }

    protected static boolean isPublicInstanceField(Field field) {
        return Modifier.isPublic(field.getModifiers())
            && !Modifier.isStatic(field.getModifiers())
            && !Modifier.isFinal(field.getModifiers())
            && !field.isSynthetic();
    }
}
