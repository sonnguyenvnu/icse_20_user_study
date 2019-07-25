package com.xiaojinzi.component.impl.application;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiaojinzi.component.Component;
import com.xiaojinzi.component.ComponentUtil;
import com.xiaojinzi.component.application.IComponentHostApplication;
import com.xiaojinzi.component.application.IComponentModuleApplication;
import com.xiaojinzi.component.impl.RouterCenter;
import com.xiaojinzi.component.impl.interceptor.InterceptorCenter;
import com.xiaojinzi.component.support.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个类必须放在 {@link ComponentUtil#IMPL_OUTPUT_PKG} 包下�?�
 * 这是是管�?��?一个模�?�之�?�?�系的管�?�类,加载模�?�的功能也是这个类负责的
 *
 * @author xiaojinzi 30212
 */
public class ModuleManager implements IComponentModuleApplication {

    /**
     * �?�例对象
     */
    private static volatile ModuleManager instance;

    private ModuleManager() {
    }

    /**
     * 获�?��?�例对象
     *
     * @return
     */
    public static ModuleManager getInstance() {
        if (instance == null) {
            synchronized (ModuleManager.class) {
                if (instance == null) {
                    instance = new ModuleManager();
                }
            }
        }
        return instance;
    }

    private static Map<String, IComponentHostApplication> moduleApplicationMap = new HashMap<>();

    @Override
    public void register(@NonNull IComponentHostApplication moduleApp) {
        moduleApplicationMap.put(moduleApp.getHost(), moduleApp);
        moduleApp.onCreate(Component.getApplication());
    }

    @Override
    public void register(@NonNull String host) {
        if (host == null || "".equals(host)) {
            throw new RuntimeException("the host can't be null or empty");
        }
        if (moduleApplicationMap.containsKey(host)) {
            LogUtil.log("the host '" + host + "' is already load");
            return;
        }
        IComponentHostApplication moduleApplication = findModuleApplication(host);
        if (moduleApplication == null) {
            LogUtil.log("模�?� '" + host + "' 加载失败");
        } else {
            register(moduleApplication);
        }
    }

    public void registerArr(@Nullable String... hosts) {
        if (hosts != null) {
            for (String host : hosts) {
                IComponentHostApplication moduleApplication = findModuleApplication(host);
                if (moduleApplication == null) {
                    LogUtil.log("模�?� '" + host + "' 加载失败");
                } else {
                    register(moduleApplication);
                }
            }
        }
    }

    @Override
    public void unregister(@NonNull IComponentHostApplication moduleApp) {
        moduleApp.onDestory();
    }

    @Override
    public void unregister(@NonNull String host) {
        IComponentHostApplication moduleApp = moduleApplicationMap.remove(host);
        if (moduleApp == null) {
            LogUtil.log("模�?� '" + host + "' �?�载失败");
        } else {
            unregister(moduleApp);
        }
    }

    @Nullable
    public static IComponentHostApplication findModuleApplication(String host) {
        IComponentHostApplication result = null;
        try {
            // 先找正常的
            Class<?> clazz = Class.forName(ComponentUtil.genHostModuleApplicationClassName(host));
            result = (IComponentHostApplication) clazz.newInstance();
        } catch (Exception ignore) {
            // ignore
        }
        if (result == null) {
            try {
                // 找默认的
                Class<?> clazz = Class.forName(ComponentUtil.genDefaultHostModuleApplicationClassName(host));
                result = (IComponentHostApplication) clazz.newInstance();
            } catch (Exception ignore) {
                // ignore
            }
        }
        return result;
    }

    /**
     * 使用者应该在开�?�阶段调用这个函数�?�检查以下的问题：
     * 1.路由表在�?�?�的�?路由表中是�?�有�?�?
     * 2.�?务在�?�?�模�?�中的声明是�?�也有�?�?的�??称
     */
    public void check() {
        RouterCenter.getInstance().check();
        InterceptorCenter.getInstance().check();
    }

}
