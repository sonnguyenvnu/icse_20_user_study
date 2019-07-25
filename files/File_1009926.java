package com.xiaojinzi.component.impl.interceptor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiaojinzi.component.ComponentUtil;
import com.xiaojinzi.component.error.InterceptorNameExistException;
import com.xiaojinzi.component.impl.RouterInterceptor;
import com.xiaojinzi.component.interceptor.IComponentCenterInterceptor;
import com.xiaojinzi.component.interceptor.IComponentHostInterceptor;
import com.xiaojinzi.component.support.RouterInterceptorCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 中央拦截器
 * time   : 2018/12/26
 *
 * @author : xiaojinzi 30212
 * @hide
 */
public class InterceptorCenter implements IComponentCenterInterceptor {

    private InterceptorCenter() {
    }

    /**
     * �?�例对象
     */
    private static volatile InterceptorCenter instance;

    /**
     * 获�?��?�例对象
     *
     * @return
     */
    public static InterceptorCenter getInstance() {
        if (instance == null) {
            synchronized (InterceptorCenter.class) {
                if (instance == null) {
                    instance = new InterceptorCenter();
                }
            }
        }
        return instance;
    }

    /**
     * �?拦截器对象管�?� map
     */
    private Map<String, IComponentHostInterceptor> moduleInterceptorMap = new HashMap<>();

    /**
     * 公共的拦截器列表
     */
    private List<RouterInterceptor> mGlobalInterceptorList = new ArrayList<>();

    /**
     * �?个业务组件的拦截器 name --> Class 映射关系的总的集�?�
     * 这�?拦截器�?是全局拦截器,是�?时�?地使用的拦截器,�? {@link com.xiaojinzi.component.impl.Navigator#interceptorNames(String...)}
     */
    private Map<String, Class<? extends RouterInterceptor>> mInterceptorMap = new HashMap<>();

    /**
     * 是�?�公共的拦截器列表�?�生�?�化
     */
    private boolean isInterceptorListHaveChange = false;

    /**
     * 获�?�全局拦截器
     *
     * @return
     */
    public List<RouterInterceptor> getGlobalInterceptorList() {
        if (isInterceptorListHaveChange) {
            loadAllGlobalInterceptor();
            isInterceptorListHaveChange = false;
        }
        return mGlobalInterceptorList;
    }

    @Override
    public void register(@Nullable IComponentHostInterceptor interceptor) {
        if (interceptor == null) {
            return;
        }
        isInterceptorListHaveChange = true;
        moduleInterceptorMap.put(interceptor.getHost(), interceptor);
        // �?拦截器列表
        Map<String, Class<? extends RouterInterceptor>> childInterceptorMap = interceptor.getInterceptorMap();
        if (childInterceptorMap != null) {
            mInterceptorMap.putAll(childInterceptorMap);
        }
    }

    @Override
    public void register(@NonNull String host) {
        if (moduleInterceptorMap.containsKey(host)) {
            return;
        }
        IComponentHostInterceptor moduleInterceptor = findModuleInterceptor(host);
        register(moduleInterceptor);
    }

    @Override
    public void unregister(@Nullable IComponentHostInterceptor interceptor) {
        if (interceptor == null) {
            return;
        }
        isInterceptorListHaveChange = true;
        // �?拦截器列表
        Map<String, Class<? extends RouterInterceptor>> childInterceptorMap = interceptor.getInterceptorMap();
        if (childInterceptorMap != null) {
            for (Map.Entry<String, Class<? extends RouterInterceptor>> entry : childInterceptorMap.entrySet()) {
                mInterceptorMap.remove(entry.getKey());
                RouterInterceptorCache.removeCache(entry.getValue());
            }
        }
    }

    @Override
    public void unregister(@NonNull String host) {
        IComponentHostInterceptor moduleInterceptor = moduleInterceptorMap.remove(host);
        unregister(moduleInterceptor);
    }

    /**
     * 按顺�?弄好所有全局拦截器
     */
    private void loadAllGlobalInterceptor() {
        mGlobalInterceptorList.clear();
        List<InterceptorBean> totalList = new ArrayList<>();
        // 加载�?�个�?拦截器对象中的拦截器列表
        for (Map.Entry<String, IComponentHostInterceptor> entry : moduleInterceptorMap.entrySet()) {
            List<InterceptorBean> list = entry.getValue().globalInterceptorList();
            totalList.addAll(list);
        }
        // 排�?所有的拦截器对象,按照优先级排�?
        Collections.sort(totalList, new Comparator<InterceptorBean>() {
            @Override
            public int compare(InterceptorBean o1, InterceptorBean o2) {
                if (o1.priority == o2.priority) {
                    return 0;
                } else if (o1.priority > o2.priority) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (InterceptorBean interceptorBean : totalList) {
            mGlobalInterceptorList.add(interceptorBean.interceptor);
        }
    }

    @Nullable
    public IComponentHostInterceptor findModuleInterceptor(String host) {
        String className = ComponentUtil.genHostInterceptorClassName(host);
        try {
            Class<?> clazz = Class.forName(className);
            return (IComponentHostInterceptor) clazz.newInstance();
        } catch (Exception ignore) {
            // ignore
        }
        return null;
    }

    @Nullable
    @Override
    public RouterInterceptor getByName(@Nullable String interceptorName) {
        if (interceptorName == null) {
            return null;
        }
        // 先到缓存中找
        RouterInterceptor result = null;
        // 拿到拦截器的 Class 对象
        Class<? extends RouterInterceptor> interceptorClass = mInterceptorMap.get(interceptorName);
        if (interceptorClass == null) {
            result = null;
        } else {
            result = RouterInterceptorCache.getInterceptorByClass(interceptorClass);
        }
        return result;
    }

    /**
     * �?�拦截器的�??称是�?��?�?的工作
     */
    public void check() {
        Set<String> set = new HashSet<>();
        for (Map.Entry<String, IComponentHostInterceptor> entry : moduleInterceptorMap.entrySet()) {
            IComponentHostInterceptor childInterceptor = entry.getValue();
            if (childInterceptor == null || childInterceptor.getInterceptorNames() == null) {
                continue;
            }
            Set<String> childInterceptorNames = childInterceptor.getInterceptorNames();
            for (String interceptorName : childInterceptorNames) {
                if (set.contains(interceptorName)) {
                    throw new InterceptorNameExistException("the interceptor's name is exist：" + interceptorName);
                }
                set.add(interceptorName);
            }
        }
    }

}
