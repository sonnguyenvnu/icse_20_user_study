package com.xiaojinzi.component;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiaojinzi.component.support.Inject;
import com.xiaojinzi.component.support.LogUtil;
import com.xiaojinzi.component.support.Utils;

/**
 * 组件化类,需�?被�?始化
 * 组件化的�?置类,�?�以拿到 Application
 * time   : 2018/08/09
 *
 * @author : xiaojinzi 30212
 */
public class Component {

    /**
     * 是�?�是 debug 状�?
     */
    private static boolean isDebug = false;

    /**
     * 全局的 Application
     */
    private static Application application = null;

    /**
     * 默认的 scheme
     */
    private static String defaultScheme = "router";

    private Component() {
    }

    /**
     * �?始化
     *
     * @param application App 的 Application
     * @param isDebug     是�?�是debug模�?
     */
    public static void init(@NonNull Application application, boolean isDebug) {
        init(application, isDebug, null);
    }

    /**
     * �?始化
     *
     * @param application App 的 Application
     * @param isDebug     是�?�是debug模�?
     */
    public static void init(@NonNull Application application, boolean isDebug, @Nullable String defaultScheme) {
        if (application == null) {
            throw new NullPointerException("the Application is null");
        }
        Component.application = application;
        Component.isDebug = isDebug;
        if (defaultScheme != null && !defaultScheme.isEmpty()) {
            Component.defaultScheme = defaultScheme;
        }
        // 注册
        application.registerActivityLifecycleCallbacks(new ComponentLifecycleCallback());
    }

    /**
     * 返回是�?�是 debug 状�?
     *
     * @return
     */
    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * 获�?� Application
     *
     * @return Application
     */
    @NonNull
    public static Application getApplication() {
        if (application == null) {
            throw new NullPointerException("the Application is null,do you call Component.init(Application application,boolean isDebug)?");
        }
        return application;
    }

    /**
     * 获�?�默认的 scheme
     *
     * @return
     */
    public static String getDefaultScheme() {
        return defaultScheme;
    }

    /**
     * 找到实现类,执行注入
     *
     * @param target
     */
    public static void inject(@NonNull Object target) {
        Utils.checkNullPointer(target, "target");
        String injectClassName = target.getClass().getName() + ComponentConstants.INJECT_SUFFIX;
        try {
            Class<?> targetInjectClass = Class.forName(injectClassName);
            Inject inject = (Inject) targetInjectClass.newInstance();
            inject.inject(target);
        } catch (Exception ignore) {
            LogUtil.log(target.getClass().getName(), "field inject fail");
        }
    }

}
