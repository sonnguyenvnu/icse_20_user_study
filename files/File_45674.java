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
package com.alipay.sofa.rpc.common.cache;

import com.alipay.sofa.rpc.common.annotation.VisibleForTesting;
import com.alipay.sofa.rpc.common.utils.ClassLoaderUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 业务�?支�?多ClassLoader，需�?缓存ClassLoader或者方法等相关信�?�
 * <p>
 * // TODO 统一的回收实效策略，例如大�?�?制�?时间�?制�?哪些�?�以被回收
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public final class ReflectCache {

    /*----------- ClassLoader Cache ------------*/
    /**
     * 应用对应的ClassLoader
     */
    @VisibleForTesting
    static final ConcurrentMap<String, ClassLoader> APPNAME_CLASSLOADER_MAP = new ConcurrentHashMap<String, ClassLoader>();

    /**
     * �?务对应的ClassLoader
     */
    @VisibleForTesting
    static final ConcurrentMap<String, ClassLoader> SERVICE_CLASSLOADER_MAP = new ConcurrentHashMap<String, ClassLoader>();

    /**
     * 注册�?务所在的ClassLoader
     *
     * @param appName     应用�??
     * @param classloader 应用级别ClassLoader
     */
    public static void registerAppClassLoader(String appName, ClassLoader classloader) {
        APPNAME_CLASSLOADER_MAP.put(appName, classloader);
    }

    /**
     * 得到�?务的自定义ClassLoader
     *
     * @param appName 应用�??
     * @return 应用级别ClassLoader
     */
    public static ClassLoader getAppClassLoader(String appName) {
        ClassLoader appClassLoader = APPNAME_CLASSLOADER_MAP.get(appName);
        if (appClassLoader == null) {
            return ClassLoaderUtils.getCurrentClassLoader();
        } else {
            return appClassLoader;
        }
    }

    /**
     * 注册�?务所在的ClassLoader
     *
     * @param serviceUniqueName �?务唯一�??称
     * @param classloader       �?务级别ClassLoader
     */
    public static void registerServiceClassLoader(String serviceUniqueName, ClassLoader classloader) {
        SERVICE_CLASSLOADER_MAP.put(serviceUniqueName, classloader);
    }

    /**
     * �?�注册�?务的 ClassLoader
     * @param serviceUniqueName
     * @return
     */
    public static ClassLoader unRegisterServiceClassLoader(String serviceUniqueName) {
        return SERVICE_CLASSLOADER_MAP.remove(serviceUniqueName);
    }

    /**
     * 得到�?务的自定义ClassLoader
     *
     * @param serviceUniqueName �?务唯一�??称
     * @return �?务级别ClassLoader
     */
    public static ClassLoader getServiceClassLoader(String serviceUniqueName) {
        ClassLoader appClassLoader = SERVICE_CLASSLOADER_MAP.get(serviceUniqueName);
        if (appClassLoader == null) {
            return ClassLoaderUtils.getCurrentClassLoader();
        } else {
            return appClassLoader;
        }
    }

    /*----------- Class Cache ------------*/
    /**
     * String-->Class 缓存
     */
    @VisibleForTesting
    static final ConcurrentMap<String, WeakHashMap<ClassLoader, Class>> CLASS_CACHE    = new ConcurrentHashMap<String, WeakHashMap<ClassLoader, Class>>();

    /**
     * Class-->String 缓存
     */
    @VisibleForTesting
    static final ConcurrentMap<Class, String>                           TYPE_STR_CACHE = new ConcurrentHashMap<Class, String>();

    /**
     * 放入Class缓存
     *
     * @param typeStr 对象�??述
     * @param clazz   类
     */
    public static void putClassCache(String typeStr, Class clazz) {
        CLASS_CACHE.putIfAbsent(typeStr, new WeakHashMap<ClassLoader, Class>());
        CLASS_CACHE.get(typeStr).put(clazz.getClassLoader(), clazz);
    }

    /**
     * 得到Class缓存
     *
     * @param typeStr 对象�??述
     * @return 类
     */
    public static Class getClassCache(String typeStr) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            return null;
        } else {
            Map<ClassLoader, Class> temp = CLASS_CACHE.get(typeStr);
            return temp == null ? null : temp.get(classLoader);
        }
    }

    /**
     * 放入类�??述缓存
     *
     * @param clazz   类
     * @param typeStr 对象�??述
     */
    public static void putTypeStrCache(Class clazz, String typeStr) {
        TYPE_STR_CACHE.put(clazz, typeStr);
    }

    /**
     * 得到类�??述缓存
     *
     * @param clazz 类
     * @return 类�??述
     */
    public static String getTypeStrCache(Class clazz) {
        return TYPE_STR_CACHE.get(clazz);
    }

    /*----------- Method Cache NOT support overload ------------*/

    /**
     * �?支�?�?载的方法对象缓存 {service:{方法�??:Method}}
     */
    @VisibleForTesting
    static final ConcurrentMap<String, ConcurrentHashMap<String, Method>>   NOT_OVERLOAD_METHOD_CACHE      = new ConcurrentHashMap<String, ConcurrentHashMap<String, Method>>();

    /**
     * �?支�?�?载的方法对象�?�数签�??缓存 {service:{方法�??:对象�?�数签�??}}
     */
    @VisibleForTesting
    static final ConcurrentMap<String, ConcurrentHashMap<String, String[]>> NOT_OVERLOAD_METHOD_SIGS_CACHE = new ConcurrentHashMap<String, ConcurrentHashMap<String, String[]>>();

    /**
     * 往缓存里放入方法
     *
     * @param serviceName �?务�??（�?�接�?��??）
     * @param method      方法
     */
    public static void putMethodCache(String serviceName, Method method) {
        ConcurrentHashMap<String, Method> cache = NOT_OVERLOAD_METHOD_CACHE.get(serviceName);
        if (cache == null) {
            cache = new ConcurrentHashMap<String, Method>();
            ConcurrentHashMap<String, Method> old = NOT_OVERLOAD_METHOD_CACHE.putIfAbsent(serviceName, cache);
            if (old != null) {
                cache = old;
            }
        }
        cache.putIfAbsent(method.getName(), method);
    }

    /**
     * 从缓存里获�?�方法
     *
     * @param serviceName �?务�??（�?�接�?��??）
     * @param methodName  方法�??
     * @return 方法
     */
    public static Method getMethodCache(String serviceName, String methodName) {
        ConcurrentHashMap<String, Method> methods = NOT_OVERLOAD_METHOD_CACHE.get(serviceName);
        return methods == null ? null : methods.get(methodName);
    }

    /**
     * 根�?��?务�??使方法缓存失效
     *
     * @param serviceName �?务�??（�?�接�?��??）
     */
    public static void invalidateMethodCache(String serviceName) {
        NOT_OVERLOAD_METHOD_CACHE.remove(serviceName);
    }

    /**
     * 往缓存里放入方法�?�数签�??
     *
     * @param serviceName �?务�??（�?�接�?��??）
     * @param methodName  方法�??
     * @param argSigs     方法�?�数签�??
     */
    public static void putMethodSigsCache(String serviceName, String methodName, String[] argSigs) {
        ConcurrentHashMap<String, String[]> cacheSigs = NOT_OVERLOAD_METHOD_SIGS_CACHE.get(serviceName);
        if (cacheSigs == null) {
            cacheSigs = new ConcurrentHashMap<String, String[]>();
            ConcurrentHashMap<String, String[]> old = NOT_OVERLOAD_METHOD_SIGS_CACHE
                .putIfAbsent(serviceName, cacheSigs);
            if (old != null) {
                cacheSigs = old;
            }
        }
        cacheSigs.putIfAbsent(methodName, argSigs);
    }

    /**
     * 从缓存里获�?�方法�?�数签�??
     *
     * @param serviceName �?务�??（�?�接�?��??）
     * @param methodName  方法�??
     * @return 方法�?�数签�??
     */
    public static String[] getMethodSigsCache(String serviceName, String methodName) {
        ConcurrentHashMap<String, String[]> methods = NOT_OVERLOAD_METHOD_SIGS_CACHE.get(serviceName);
        return methods == null ? null : methods.get(methodName);
    }

    /**
     * 根�?��?务�??使方法缓存失效
     *
     * @param serviceName �?务�??（�?�接�?��??）
     */
    public static void invalidateMethodSigsCache(String serviceName) {
        NOT_OVERLOAD_METHOD_SIGS_CACHE.remove(serviceName);
    }

    /*----------- Method Cache support overload ------------*/

    /**
     * 方法对象缓存 {service:{方法�??#(�?�数列表):Method}} <br>
     * 用于缓存�?�数列表，�?是按接�?�，是按ServiceUniqueName
     */
    @VisibleForTesting
    final static ConcurrentMap<String, ConcurrentHashMap<String, Method>> OVERLOAD_METHOD_CACHE = new ConcurrentHashMap<String, ConcurrentHashMap<String, Method>>();

    /**
     * 往缓存里放入方法
     *
     * @param serviceName �?务�??（�?�接�?��??）
     * @param method      方法
     */
    public static void putOverloadMethodCache(String serviceName, Method method) {
        ConcurrentHashMap<String, Method> cache = OVERLOAD_METHOD_CACHE.get(serviceName);
        if (cache == null) {
            cache = new ConcurrentHashMap<String, Method>();
            ConcurrentHashMap<String, Method> old = OVERLOAD_METHOD_CACHE.putIfAbsent(serviceName, cache);
            if (old != null) {
                cache = old;
            }
        }
        StringBuilder mSigs = new StringBuilder(128);
        mSigs.append(method.getName());
        for (Class<?> paramType : method.getParameterTypes()) {
            mSigs.append(paramType.getName());
        }
        cache.putIfAbsent(mSigs.toString(), method);
    }

    /**
     * 从缓存里获�?�方法
     *
     * @param serviceName �?务�??（�?�接�?��??）
     * @param methodName  方法�??
     * @param methodSigs  方法�??述
     * @return 方法
     */
    public static Method getOverloadMethodCache(String serviceName, String methodName, String[] methodSigs) {
        ConcurrentHashMap<String, Method> methods = OVERLOAD_METHOD_CACHE.get(serviceName);
        if (methods == null) {
            return null;
        }
        StringBuilder mSigs = new StringBuilder(128);
        mSigs.append(methodName);
        for (String methodSign : methodSigs) {
            mSigs.append(methodSign);
        }
        return methods.get(mSigs.toString());
    }

    /**
     * �?�消缓存�?务的公共方法
     *
     * @param serviceName �?务�??（�?�接�?��??）
     */
    public static void invalidateOverloadMethodCache(String serviceName) {
        OVERLOAD_METHOD_CACHE.remove(serviceName);
    }

    /*----------- Cache Management ------------*/
    /**
     * 清�?�方法
     */
    static void clearAll() {
        CLASS_CACHE.clear();
        TYPE_STR_CACHE.clear();
        APPNAME_CLASSLOADER_MAP.clear();
        SERVICE_CLASSLOADER_MAP.clear();
        NOT_OVERLOAD_METHOD_CACHE.clear();
        NOT_OVERLOAD_METHOD_SIGS_CACHE.clear();
        OVERLOAD_METHOD_CACHE.clear();
    }

}
