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
package com.alipay.sofa.rpc.client;

import com.alipay.sofa.rpc.bootstrap.ConsumerBootstrap;
import com.alipay.sofa.rpc.common.struct.OrderedComparator;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.ExtensionClass;
import com.alipay.sofa.rpc.ext.ExtensionLoader;
import com.alipay.sofa.rpc.ext.ExtensionLoaderFactory;
import com.alipay.sofa.rpc.ext.ExtensionLoaderListener;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Chain of routers
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class RouterChain {

    /**
     * LOGGER
     */
    private static final Logger                              LOGGER                = LoggerFactory
                                                                                       .getLogger(RouterChain.class);

    /**
     * �?务端自动激活的 {"alias":ExtensionClass}
     */
    private final static Map<String, ExtensionClass<Router>> PROVIDER_AUTO_ACTIVES = Collections
                                                                                       .synchronizedMap(new ConcurrentHashMap<String, ExtensionClass<Router>>());

    /**
     * 调用端自动激活的 {"alias":ExtensionClass}
     */
    private final static Map<String, ExtensionClass<Router>> CONSUMER_AUTO_ACTIVES = Collections
                                                                                       .synchronizedMap(new ConcurrentHashMap<String, ExtensionClass<Router>>());

    /**
     * 扩展加载器
     */
    private final static ExtensionLoader<Router>             EXTENSION_LOADER      = buildLoader();

    private static ExtensionLoader<Router> buildLoader() {
        return ExtensionLoaderFactory.getExtensionLoader(Router.class, new ExtensionLoaderListener<Router>() {
            @Override
            public void onLoad(ExtensionClass<Router> extensionClass) {
                Class<? extends Router> implClass = extensionClass.getClazz();
                // 读�?�自动加载的类列表。
                AutoActive autoActive = implClass.getAnnotation(AutoActive.class);
                if (autoActive != null) {
                    String alias = extensionClass.getAlias();
                    if (autoActive.providerSide()) {
                        PROVIDER_AUTO_ACTIVES.put(alias, extensionClass);
                    }
                    if (autoActive.consumerSide()) {
                        CONSUMER_AUTO_ACTIVES.put(alias, extensionClass);
                    }
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Extension of interface " + Router.class + ", " + implClass + "(" + alias +
                            ") will auto active");
                    }
                }
            }
        });
    }

    /**
     * 调用链
     */
    private final List<Router> routers;

    public RouterChain(List<Router> actualRouters, ConsumerBootstrap consumerBootstrap) {
        this.routers = new ArrayList<Router>();
        if (CommonUtils.isNotEmpty(actualRouters)) {
            for (Router router : actualRouters) {
                if (router.needToLoad(consumerBootstrap)) {
                    router.init(consumerBootstrap);
                    routers.add(router);
                }
            }
        }
    }

    /**
     * 筛选Provider
     *
     * @param request       本次调用（�?�以得到类�??，方法�??，方法�?�数，�?�数值等）
     * @param providerInfos providers（<b>当�?�?�用</b>的�?务Provider列表）
     * @return 路由匹�?的�?务Provider列表
     */
    public List<ProviderInfo> route(SofaRequest request, List<ProviderInfo> providerInfos) {
        for (Router router : routers) {
            providerInfos = router.route(request, providerInfos);
        }
        return providerInfos;
    }

    /**
     * 构建Router链
     *
     * @param consumerBootstrap �?务端订阅者�?置
     * @return 路由链
     */
    public static RouterChain buildConsumerChain(ConsumerBootstrap consumerBootstrap) {
        ConsumerConfig<?> consumerConfig = consumerBootstrap.getConsumerConfig();
        List<Router> customRouters = consumerConfig.getRouterRef() == null ? new ArrayList<Router>()
            : new CopyOnWriteArrayList<Router>(consumerConfig.getRouterRef());
        // 先解�?是�?�有特殊处�?�
        HashSet<String> excludes = parseExcludeRouter(customRouters);

        // 准备数�?�：用户通过别�??的方�?注入的router，需�?解�?
        List<ExtensionClass<Router>> extensionRouters = new ArrayList<ExtensionClass<Router>>();
        List<String> routerAliases = consumerConfig.getRouter();
        if (CommonUtils.isNotEmpty(routerAliases)) {
            for (String routerAlias : routerAliases) {
                if (startsWithExcludePrefix(routerAlias)) { // 排除用的特殊字符
                    excludes.add(routerAlias.substring(1));
                } else {
                    extensionRouters.add(EXTENSION_LOADER.getExtensionClass(routerAlias));
                }
            }
        }
        // 解�?自动加载的router
        if (!excludes.contains(StringUtils.ALL) && !excludes.contains(StringUtils.DEFAULT)) { // �?了-*和-default表示�?加载内置
            for (Map.Entry<String, ExtensionClass<Router>> entry : CONSUMER_AUTO_ACTIVES.entrySet()) {
                if (!excludes.contains(entry.getKey())) {
                    extensionRouters.add(entry.getValue());
                }
            }
        }
        excludes = null; // �?需�?了
        // 按order从�?到大排�?
        if (extensionRouters.size() > 1) {
            Collections.sort(extensionRouters, new OrderedComparator<ExtensionClass>());
        }
        List<Router> actualRouters = new ArrayList<Router>();
        for (ExtensionClass<Router> extensionRouter : extensionRouters) {
            Router actualRoute = extensionRouter.getExtInstance();
            actualRouters.add(actualRoute);
        }
        // 加入自定义的过滤器
        actualRouters.addAll(customRouters);
        return new RouterChain(actualRouters, consumerBootstrap);
    }

    /**
     * 判断是�?�需�?排除系统过滤器
     *
     * @param customRouters 自定义Router
     * @return 是�?�排除
     */
    private static HashSet<String> parseExcludeRouter(List<Router> customRouters) {
        HashSet<String> excludeKeys = new HashSet<String>();
        if (CommonUtils.isNotEmpty(customRouters)) {
            for (Router router : customRouters) {
                if (router instanceof ExcludeRouter) {
                    // 存在需�?排除的过滤器
                    ExcludeRouter excludeRouter = (ExcludeRouter) router;
                    String excludeName = excludeRouter.getExcludeName();
                    if (StringUtils.isNotEmpty(excludeName)) {
                        String excludeRouterName = startsWithExcludePrefix(excludeName) ? excludeName.substring(1)
                            : excludeName;
                        if (StringUtils.isNotEmpty(excludeRouterName)) {
                            excludeKeys.add(excludeRouterName);
                        }
                    }
                    customRouters.remove(router);
                }
            }
        }
        if (!excludeKeys.isEmpty()) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Find exclude routers: {}", excludeKeys);
            }
        }
        return excludeKeys;
    }

    private static boolean startsWithExcludePrefix(String excludeName) {
        char c = excludeName.charAt(0);
        return c == '-' || c == '!';
    }
}
