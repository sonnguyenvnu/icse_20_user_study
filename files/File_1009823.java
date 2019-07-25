/*
 * Copyright (C) 2017-2018 Manbang Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wlqq.phantom.communication;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 管�?��?�件或宿主�??供给其他�?�件/宿主调用的功能接�?�，�??供功能模�?�的注册�?�?�载�?查询和获�?�功能。
 */
public class PhantomServiceManager {

    // { service_category -> { service_name -> service_object } }
    private static final HashMap<String, HashMap<String, Object>> CATEGORY_SERVICES_MAP = new HashMap<>();
    private static String sHostPackage;
    private static String sHostVersionName;
    private static int sHostVersionCode;
    private static String sPhantomVersionName;
    private static int sPhantomVersionCode;
    private static boolean sInitialized;

    private PhantomServiceManager() {
    }

    /**
     * �?始化 {@link PhantomServiceManager}，<b>必须</b>在调用该类其他方法之�?调用该方法
     *
     * @param hostPackage        宿主包�??
     * @param hostVersionName    宿主版本�??
     * @param hostVersionCode    宿主版本�?�
     * @param phantomVersionName Phantom 版本�??
     * @param phantomVersionCode Phantom 版本�?�
     */
    public static synchronized void init(String hostPackage,
            String hostVersionName,
            int hostVersionCode,
            String phantomVersionName,
            int phantomVersionCode) {
        if (sInitialized) {
            return;
        }

        sHostPackage = hostPackage;
        sHostVersionName = hostVersionName;
        sHostVersionCode = hostVersionCode;
        sPhantomVersionName = phantomVersionName;
        sPhantomVersionCode = phantomVersionCode;

        sInitialized = true;
    }

    /**
     * 获�?�宿主包�??
     *
     * @return 宿主包�??
     */
    public static String getHostPackage() {
        return sHostPackage;
    }

    /**
     * 获�?�宿主版本�??
     *
     * @return 宿主版本�??
     */
    public static String getHostVersionName() {
        return sHostVersionName;
    }

    /**
     * 获�?�宿主版本�?�
     *
     * @return 宿主版本�?�
     */
    public static int getHostVersionCode() {
        return sHostVersionCode;
    }

    /**
     * 获�?��?�件框架版本�??
     *
     * @return �?�件框架版本�??
     */
    public static String getPhantomVersionName() {
        return sPhantomVersionName;
    }

    /**
     * 获�?��?�件框架版本�?�
     *
     * @return �?�件框架版本�?�
     */
    public static int getPhantomVersionCode() {
        return sPhantomVersionCode;
    }

    /**
     * 注册功能模�?�
     *
     * @param category 功能模�?�所属类别， �?�一个�?�件类别应该一致，通常使用 <b>宿主/�?�件 包�??</b>
     * @param name     功能模�?�的�??字，�?�一类别�?�以有多个功能模�?�
     * @param service  功能模�?�的实现
     * @return 注册�?功返回 true，�?�则返回 false
     */
    public static boolean registerService(String category, String name, Object service) {
        if (null == category || 0 == category.length()
                || null == name || 0 == name.length()
                || null == service) {
            return false;
        }

        synchronized (CATEGORY_SERVICES_MAP) {
            HashMap<String, Object> services = CATEGORY_SERVICES_MAP.get(category);
            if (null == services) {
                services = new HashMap<>();
                CATEGORY_SERVICES_MAP.put(category, services);
            }

            //存在�?务�??称相�?�而类�?�?�的情况�?�?许注册，�?务�??称和�?务类都相�?��?许覆盖之�?的�?务
            Object hasService = services.get(name);
            if (null != hasService && !hasService.getClass().getName().equals(service.getClass().getName())) {
                return false;
            }

            services.put(name, service);
        }

        return true;
    }

    /**
     * 注册功能模�?�
     *
     * @param name    �?务�??字格�? packageName/name, 如果�?务�??称�?加 packageName，则默认被注册�? sHostPackage/name，被认为是宿主�??供的�?务
     * @param service 功能模�?�的实现
     * @return 注册�?功返回 true，�?�则返回 false
     */
    public static boolean registerService(String name, Object service) {
        if (null == name) {
            return false;
        }

        if (!name.contains("/") && null != sHostPackage) {
            return registerService(sHostPackage, name, service);
        }

        String[] items = name.split("/");
        if (items.length != 2) {
            return false;
        }

        return registerService(items[0], items[1], service);
    }

    /**
     * 注册功能模�?�, �?务对象<b>必须</b>使用 {@link PhantomService} 注解
     *
     * @param service service 的实现类必须使用 {@link PhantomService} 注解
     * @return �?功返回 true，�?�则返回 false
     * @throws IllegalArgumentException 若�?务为 <b>null</b> 或没有使用 {@link PhantomService} 注解
     */
    public static boolean registerService(Object service) throws IllegalArgumentException {
        PhantomService serviceTypeAnnotation = checkPhantomServiceAnnotation(service);

        String serviceType = serviceTypeAnnotation.name();
        return registerService(serviceType, service);
    }

    /**
     * 检查�?务对象类是�?�用 {@link PhantomService} 注解
     *
     * @param service �?务对象
     * @return {@link PhantomService} 若�?务使用了 {@link PhantomService} 注解
     * @throws IllegalArgumentException 若�?务为 <b>null</b> 或没有使用 {@link PhantomService} 注解
     */
    private static PhantomService checkPhantomServiceAnnotation(Object service)
            throws IllegalArgumentException {
        if (service == null) {
            throw new IllegalArgumentException("service object must not be null");
        }
        final Class<?> aClass = service.getClass();
        PhantomService serviceTypeAnnotation = aClass.getAnnotation(PhantomService.class);
        if (serviceTypeAnnotation == null) {
            throw new IllegalArgumentException(
                    "service class must has PhantomService annotation: " + aClass.getName());
        } else {
            return serviceTypeAnnotation;
        }
    }

    /**
     * �??注册指定类别的所有�?务
     *
     * @param category 功能模�?�所属类别，通常使用 <b>宿主/�?�件 包�??</b>
     */
    public static void unregisterService(String category) {
        synchronized (CATEGORY_SERVICES_MAP) {
            CATEGORY_SERVICES_MAP.remove(category);
        }
    }

    /**
     * �??注册所有�?务
     */
    public static void unregisterAllService() {
        synchronized (CATEGORY_SERVICES_MAP) {
            CATEGORY_SERVICES_MAP.clear();
        }
    }

    /**
     * 获�?�指定模�?�下的 service 列表
     *
     * @param category 功能模�?�类别，通常使用 <b>宿主/�?�件 包�??</b>
     * @return category 类别下的 service 列表
     */
    public static List<IService> getServices(String category) {
        List<IService> services;

        synchronized (CATEGORY_SERVICES_MAP) {
            HashMap<String, Object> serviceObjectMap = CATEGORY_SERVICES_MAP.get(category);
            if (serviceObjectMap == null) {
                services = new ArrayList<>(0);
            } else {
                services = new ArrayList<>(serviceObjectMap.size());
                for (Object serviceObject : serviceObjectMap.values()) {
                    services.add(new ServiceModule(serviceObject));
                }
            }
        }

        return services;
    }

    /**
     * 获�?�指定类别/�??字下的功能模�?�
     *
     * @param category 功能模�?�类别，通常使用 <b>宿主/�?�件 包�??</b>
     * @param name     功能模�?��??字
     * @return �?功返回功能模�?�实例，�?�则返回 null
     */
    public static IService getService(String category, String name) {
        synchronized (CATEGORY_SERVICES_MAP) {
            final HashMap<String, Object> services = CATEGORY_SERVICES_MAP.get(category);
            if (services == null) {
                return null;
            }

            return new ServiceModule(services.get(name));
        }
    }

    /**
     * 根�?��??字获�?�功能模�?�，返回找到的第一个�??字为 name 的功能模�?�，�?�则返回 null
     *
     * @param name �?务�??字格�? packageName/name, 如果�?�数 name 中�?去了 packageName，则被认为是查找宿主中的�?务 sHostPackage/name
     * @return �?功返回功能模�?�实例，�?�则返回 null
     */
    public static IService getService(String name) {
        if (null == name) {
            return null;
        }

        if (!name.contains("/") && null != sHostPackage) {
            return getService(sHostPackage, name);
        }

        String[] items = name.split("/");
        if (items.length != 2) {
            return null;
        }

        return getService(items[0], items[1]);
    }

    /**
     * 根�?��??字获�?�功能模�?�，返回找到的第一个�??字为 name 的功能模�?�，并转�?�为 type �?�数指定的接�?�类型，�?�则返回 null
     *
     * @param <T>  interface 类型
     * @param name �?务�??字格�? packageName/name，如果�?�数 name 中�?去了 packageName，则被认为是查找宿主中的�?务 sHostPackage/name
     * @param type 返回的对象类型， type 为必须为一个 interface
     * @return �?功返回 type 的一个对象，�?�则返回 null
     */
    public static <T> T getService(String name, Class<T> type) {
        IService service = getService(name);
        return asInterface(service, type);
    }

    /**
     * 是�?�有注册指定�??字的�?务
     *
     * @param name �?务�??字格�? packageName/name，如果�?�数 name 中�?去了 packageName，则被认为是查找宿主中的�?务 sHostPackage/name
     * @return 如何有注册，返回 true；�?�则返回 false
     */
    public static boolean hasService(String name) {
        boolean res = false;

        synchronized (CATEGORY_SERVICES_MAP) {
            Collection<HashMap<String, Object>> allServices = CATEGORY_SERVICES_MAP.values();
            Iterator<HashMap<String, Object>> iter = allServices.iterator();
            while (iter.hasNext() && (!res)) {
                HashMap<String, Object> services = iter.next();
                res = services.containsKey(name);
            }
        }

        return res;
    }

    /**
     * 将功能模�?� service 转�?��?自定义接�?�，方便调用。
     *
     * @param <T>     interface 类型
     * @param service 需�?转�?�的功能模�?�
     * @param type    自定义接�?�
     * @return 自定义接�?�的实现类
     */
    public static <T> T asInterface(IService service, Class<T> type) {
        T res = null;
        if (type.isInterface()) {
            ServiceInvocationHandler handler = new ServiceInvocationHandler(service);
            res = (T) Proxy.newProxyInstance(type.getClassLoader(),
                    new Class[]{type, ServiceInfo.class}, handler);
        }
        return res;
    }

    /**
     * 是�?�有注册指定�??字的�?务
     *
     * @param category 功能模�?�所属类别， �?�一个�?�件类别应该一致，通常使用 <b>宿主/�?�件 包�??</b>
     * @param name     功能模�?�的�??字，�?�一类别�?�以有多个功能模�?�
     * @return 如何有注册，返回 true; �?�则返回 false
     */
    public static boolean hasService(String category, String name) {
        boolean res = false;

        synchronized (CATEGORY_SERVICES_MAP) {
            if (CATEGORY_SERVICES_MAP.containsKey(category)) {
                res = CATEGORY_SERVICES_MAP.get(category).containsKey(name);
            }
        }

        return res;
    }

    private static class ServiceInvocationHandler implements InvocationHandler {
        private final IService mService;

        public ServiceInvocationHandler(IService service) {
            this.mService = service;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            if (null == mService) {
                return null;
            }
            try {
                if (method.getDeclaringClass() == ServiceInfo.class) {
                    Method targetMethod = ServiceInfo.class.getDeclaredMethod(method.getName());
                    return targetMethod.invoke(mService, args);
                }

                return mService.call(method.getName(), args);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


