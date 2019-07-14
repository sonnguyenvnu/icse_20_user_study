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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Bean的一些�?作
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class BeanUtils {

    /**
     * 设置属性
     *
     * @param bean  对象
     * @param name  属性�??
     * @param clazz 设置值的类
     * @param value 属性值
     * @param <T>   和值对应的类型
     * @throws Exception 设值异常
     */
    public static <T> void setProperty(Object bean, String name, Class<T> clazz, T value) throws Exception {
        Method method = ReflectUtils.getPropertySetterMethod(bean.getClass(), name, clazz);
        if (method.isAccessible()) {
            method.invoke(bean, value);
        } else {
            try {
                method.setAccessible(true);
                method.invoke(bean, value);
            } finally {
                method.setAccessible(false);
            }
        }
    }

    /**
     * 得到属性的值
     *
     * @param bean  对象
     * @param name  属性�??
     * @param clazz 设置值的类
     * @param <T>   和返回值对应的类型
     * @return 属性值
     * @throws Exception �?�值异常
     */
    public static <T> T getProperty(Object bean, String name, Class<T> clazz) throws Exception {
        Method method = ReflectUtils.getPropertyGetterMethod(bean.getClass(), name);
        if (method.isAccessible()) {
            return (T) method.invoke(bean);
        } else {
            try {
                method.setAccessible(true);
                return (T) method.invoke(bean);
            } finally {
                method.setAccessible(false);
            }
        }
    }

    /**
     * �?制属性到map，�?�以自定义�?缀
     *
     * @param bean   对象
     * @param prefix 放入key的�?缀
     * @param map    �?写入的map
     */
    public static void copyPropertiesToMap(Object bean, String prefix, Map<String, Object> map) {
        Class clazz = bean.getClass();
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            // �?制属性
            Class returnc = method.getReturnType();
            if (ReflectUtils.isBeanPropertyReadMethod(method)) {
                String propertyName = ReflectUtils.getPropertyNameFromBeanReadMethod(method);
                try {
                    if (ReflectUtils.getPropertySetterMethod(clazz, propertyName, returnc) == null) {
                        continue; // 还需�?有set方法
                    }
                } catch (Exception e) {
                    continue;
                }
                Object val;
                try {
                    val = method.invoke(bean);
                } catch (InvocationTargetException e) {
                    throw new SofaRpcRuntimeException("Can't access copy " + propertyName, e.getCause());
                } catch (IllegalAccessException e) {
                    throw new SofaRpcRuntimeException("Can't access copy " + propertyName, e);
                }
                if (val != null) { // 值�?为空，放入缓存
                    map.put(prefix + propertyName, val);
                }
            }
        }
        Field[] fields = bean.getClass().getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (map.containsKey(prefix + fieldName)) {
                continue;
            }
            int m = field.getModifiers();
            if (!Modifier.isStatic(m) && !Modifier.isTransient(m)) {
                Object val = null;
                try {
                    if (field.isAccessible()) {
                        val = field.get(bean);
                    } else {
                        try {
                            field.setAccessible(true);
                            val = field.get(bean);
                        } finally {
                            field.setAccessible(false);
                        }
                    }
                } catch (IllegalAccessException e) {
                    // LOGGER.warn("Can't access field" + fieldName + "when copy value to context", e);
                }
                if (val != null) {
                    map.put(prefix + fieldName, val);
                }
            }
        }
    }

    /**
     * 从一个对象�?制相�?�字段到�?�一个对象，（�?�写有getter/setter方法都有的值）
     *
     * @param src          原始对象
     * @param dst          目标对象
     * @param ignoreFields 忽略的字段
     */
    public static void copyProperties(Object src, Object dst, String... ignoreFields) {
        Class srcClazz = src.getClass();
        Class distClazz = dst.getClass();
        Method[] methods = distClazz.getMethods();
        List<String> ignoreFiledList = Arrays.asList(ignoreFields);
        for (Method dstMethod : methods) { // �??历目标对象的方法
            if (Modifier.isStatic(dstMethod.getModifiers())
                || !ReflectUtils.isBeanPropertyReadMethod(dstMethod)) {
                // �?是static方法， 是getter方法
                continue;
            }
            String propertyName = ReflectUtils.getPropertyNameFromBeanReadMethod(dstMethod);
            if (ignoreFiledList.contains(propertyName)) {
                // 忽略字段
                continue;
            }
            Class dstReturnType = dstMethod.getReturnType();
            try { // �?�时目标字段还需�?有set方法
                Method dstSetterMethod = ReflectUtils.getPropertySetterMethod(distClazz, propertyName, dstReturnType);
                if (dstSetterMethod != null) {
                    // �?检查原始对象方法
                    Method srcGetterMethod = ReflectUtils.getPropertyGetterMethod(srcClazz, propertyName);
                    // 原始字段有getter方法
                    Class srcReturnType = srcGetterMethod.getReturnType();
                    if (srcReturnType.equals(dstReturnType)) { // 原始字段和目标字段返回类型一样
                        Object val = srcGetterMethod.invoke(src); // 从原始对象读�?�值
                        if (val != null) {
                            dstSetterMethod.invoke(dst, val); // 设置到目标对象
                        }
                    }
                }
            } catch (Exception ignore) {
                // ignore 下一循环
            }
        }
    }

    /**
     * 检查一个类的一个对象和�?�一个对象哪些属性被修改了（�?�写有getter/setter方法都有的值）
     *
     * @param src          修改�?对象
     * @param dst          修改�?�对象
     * @param ignoreFields 忽略的字段
     * @param <T>          对象
     * @return             修改过的字段列表
     */
    public static <T> List<String> getModifiedFields(T src, T dst, String... ignoreFields) {
        Class clazz = src.getClass();
        Method[] methods = clazz.getMethods();
        List<String> ignoreFiledList = Arrays.asList(ignoreFields);
        List<String> modifiedFields = new ArrayList<String>();
        for (Method getterMethod : methods) { // �??历目标对象的方法
            if (Modifier.isStatic(getterMethod.getModifiers())
                || !ReflectUtils.isBeanPropertyReadMethod(getterMethod)) {
                // �?是static方法， 是getter方法
                continue;
            }
            String propertyName = ReflectUtils.getPropertyNameFromBeanReadMethod(getterMethod);
            if (ignoreFiledList.contains(propertyName)) {
                // 忽略字段
                continue;
            }
            Class returnType = getterMethod.getReturnType();
            try { // �?�时目标字段还需�?有set方法
                Method setterMethod = ReflectUtils.getPropertySetterMethod(clazz, propertyName, returnType);
                if (setterMethod != null) {
                    Object srcVal = getterMethod.invoke(src); // 原始值
                    Object dstVal = getterMethod.invoke(dst); // 修改�?�值
                    if (srcVal == null) { // 左边为空
                        if (dstVal != null) {
                            modifiedFields.add(propertyName);
                        }
                    } else {
                        if (dstVal == null) { // �?�边为空
                            modifiedFields.add(propertyName);
                        } else {
                            if (!srcVal.equals(dstVal)) { // 都�?为空且�?�?�
                                modifiedFields.add(propertyName);
                            }
                        }
                    }
                }
            } catch (Exception ignore) {
                // ignore 下一循环
            }
        }
        return modifiedFields;
    }
}
