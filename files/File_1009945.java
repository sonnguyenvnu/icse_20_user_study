package com.xiaojinzi.component.support;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiaojinzi.component.Component;
import com.xiaojinzi.component.anno.RouterAnno;
import com.xiaojinzi.component.cache.ClassCache;
import com.xiaojinzi.component.error.CreateInterceptorException;
import com.xiaojinzi.component.impl.RouterInterceptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 支�?缓存自定义拦截器,工具类
 * 目�?就�?�有给 目标页�?�在 {@link RouterAnno#interceptors()}
 * or {@link RouterAnno#interceptorNames()}
 * or {@link com.xiaojinzi.component.impl.Navigator#interceptors(Class[])}
 * or {@link com.xiaojinzi.component.impl.Navigator#interceptorNames(String...)}
 * 写的拦截器�?�缓存
 * <p>
 * time   : 2018/12/03
 *
 * @author : xiaojinzi 30212
 */
public class RouterInterceptorCache {

    private RouterInterceptorCache() {
    }

    /**
     * 内部�?�了缓存,如果缓存中没有就会�??射创建拦截器对象
     */
    @Nullable
    public static synchronized RouterInterceptor getInterceptorByClass(@NonNull Class<? extends RouterInterceptor> tClass) {
        RouterInterceptor t = ClassCache.get(tClass);
        if (t != null) {
            return t;
        }
        try {
            // 创建拦截器
            t = create(tClass);
            if (t == null) {
                throw new InstantiationException("do you write default constructor or a constructor with parameter 'Application' or  a constructor with parameter 'Context' ");
            } else {
                ClassCache.put(tClass, t);
            }
        } catch (Exception e) {
            if (Component.isDebug()) {
                throw new CreateInterceptorException(e);
            }
        }
        return t;
    }

    @Nullable
    private static RouterInterceptor create(@NonNull Class<? extends RouterInterceptor> tClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = tClass.getConstructors();
        if (constructors == null) {
            return null;
        }
        // 这里为什么使用 for 循环而�?是直接获�?�空�?�数的构造函数或者以下有�?个�?�数的构造函数
        // 是因为你获�?�的时候会有异常抛出,三�?情况你得 try{}catch{}三次
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes == null || parameterTypes.length == 0) {
                return (RouterInterceptor) constructor.newInstance();
            }
            if (parameterTypes.length == 1 && parameterTypes[0] == Application.class) {
                return (RouterInterceptor) constructor.newInstance(Component.getApplication());
            }
            if (parameterTypes.length == 1 && parameterTypes[0] == Context.class) {
                return (RouterInterceptor) constructor.newInstance(Component.getApplication());
            }
        }
        return null;
    }

    public static synchronized void removeCache(@NonNull Class<? extends RouterInterceptor> tClass) {
        ClassCache.remove(tClass);
    }

}
