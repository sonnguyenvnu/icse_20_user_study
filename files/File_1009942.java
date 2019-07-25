package com.xiaojinzi.component.impl.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiaojinzi.component.service.IServiceLoad;

import java.util.HashMap;
import java.util.Map;

/**
 * �?务的容器,使用这个�?务容器你需�?判断获�?�到的�?务是�?�为空,对于使用者�?�说还是比较�?方便的
 * 建议使用 Service 扩展的版本 RxService
 *
 * @author xiaojinzi 30212
 */
public class Service {

    private Service() {
    }

    /**
     * Service 的集�?�
     */
    private static Map<Class, IServiceLoad<?>> map = new HashMap<>();

    /**
     * 你�?�以注册一个�?务,�?务的�?始化�?�以是 懒加载的
     *
     * @param tClass
     * @param iServiceLoad
     * @param <T>
     */
    public static <T> void register(@NonNull Class<T> tClass, @NonNull IServiceLoad<? extends T> iServiceLoad) {
        map.put(tClass, iServiceLoad);
    }

    @Nullable
    public static <T> T unregister(@NonNull Class<T> tClass) {
        return (T) map.remove(tClass);
    }

    @Nullable
    public static <T> T get(@NonNull Class<T> tClass) {
        if (map.get(tClass) == null) {
            return null;
        } else {
            return (T) map.get(tClass).get();
        }
    }

}
