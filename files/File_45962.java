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
package com.alipay.sofa.rpc.bootstrap;

import com.alipay.sofa.rpc.common.struct.NamedThreadFactory;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.ExceptionUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.invoke.Invoker;
import com.alipay.sofa.rpc.listener.ConfigListener;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.registry.Registry;
import com.alipay.sofa.rpc.registry.RegistryFactory;
import com.alipay.sofa.rpc.server.ProviderProxyInvoker;
import com.alipay.sofa.rpc.server.Server;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Default provider bootstrap.
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
@Extension("sofa")
public class DefaultProviderBootstrap<T> extends ProviderBootstrap<T> {

    /**
     * slf4j Logger for this class
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultProviderBootstrap.class);

    /**
     * æž„é€ å‡½æ•°
     *
     * @param providerConfig æœ?åŠ¡å?‘å¸ƒè€…é…?ç½®
     */
    protected DefaultProviderBootstrap(ProviderConfig<T> providerConfig) {
        super(providerConfig);
    }

    /**
     * æ˜¯å?¦å·²å?‘å¸ƒ
     */
    protected transient volatile boolean                        exported;

    /**
     * æœ?åŠ¡ç«¯Invokerå¯¹è±¡
     */
    protected transient Invoker                                 providerProxyInvoker;

    /**
     * å?‘å¸ƒçš„æœ?åŠ¡é…?ç½®
     */
    protected final static ConcurrentMap<String, AtomicInteger> EXPORTED_KEYS = new ConcurrentHashMap<String, AtomicInteger>();

    /**
     * å»¶è¿ŸåŠ è½½çš„çº¿ç¨‹å??å·¥åŽ‚
     */
    private final ThreadFactory                                 factory       = new NamedThreadFactory(
                                                                                  "DELAY-EXPORT",
                                                                                  true);

    @Override
    public void export() {
        if (providerConfig.getDelay() > 0) { // å»¶è¿ŸåŠ è½½,å?•ä½?æ¯«ç§’
            Thread thread = factory.newThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(providerConfig.getDelay());
                    } catch (Throwable ignore) { // NOPMD
                    }
                    doExport();
                }
            });
            thread.start();
        } else {
            doExport();
        }
    }

    private void doExport() {
        if (exported) {
            return;
        }

        // æ£€æŸ¥å?‚æ•°
        checkParameters();

        String appName = providerConfig.getAppName();

        //key  is the protocol of server,for concurrent safe
        Map<String, Boolean> hasExportedInCurrent = new ConcurrentHashMap<String, Boolean>();
        // å°†å¤„ç?†å™¨æ³¨å†Œåˆ°server
        List<ServerConfig> serverConfigs = providerConfig.getServer();
        for (ServerConfig serverConfig : serverConfigs) {
            String protocol = serverConfig.getProtocol();

            String key = providerConfig.buildKey() + ":" + protocol;

            if (LOGGER.isInfoEnabled(appName)) {
                LOGGER.infoWithApp(appName, "Export provider config : {} with bean id {}", key, providerConfig.getId());
            }

            // æ³¨æ„?å?Œä¸€interfaceï¼Œå?Œä¸€uniqueIdï¼Œä¸?å?Œserveræƒ…å†µ
            AtomicInteger cnt = EXPORTED_KEYS.get(key); // è®¡æ•°å™¨
            if (cnt == null) { // æ²¡æœ‰å?‘å¸ƒè¿‡
                cnt = CommonUtils.putToConcurrentMap(EXPORTED_KEYS, key, new AtomicInteger(0));
            }
            int c = cnt.incrementAndGet();
            hasExportedInCurrent.put(serverConfig.getProtocol(), true);
            int maxProxyCount = providerConfig.getRepeatedExportLimit();
            if (maxProxyCount > 0) {
                if (c > maxProxyCount) {
                    decrementCounter(hasExportedInCurrent);
                    // è¶…è¿‡æœ€å¤§æ•°é‡?ï¼Œç›´æŽ¥æŠ›å‡ºå¼‚å¸¸
                    throw new SofaRpcRuntimeException("Duplicate provider config with key " + key
                        + " has been exported more than " + maxProxyCount + " times!"
                        + " Maybe it's wrong config, please check it."
                        + " Ignore this if you did that on purpose!");
                } else if (c > 1) {
                    if (LOGGER.isInfoEnabled(appName)) {
                        LOGGER.infoWithApp(appName, "Duplicate provider config with key {} has been exported!"
                            + " Maybe it's wrong config, please check it."
                            + " Ignore this if you did that on purpose!", key);
                    }
                }
            }

        }

        try {
            // æž„é€ è¯·æ±‚è°ƒç”¨å™¨
            providerProxyInvoker = new ProviderProxyInvoker(providerConfig);
            // åˆ?å§‹åŒ–æ³¨å†Œä¸­å¿ƒ
            if (providerConfig.isRegister()) {
                List<RegistryConfig> registryConfigs = providerConfig.getRegistry();
                if (CommonUtils.isNotEmpty(registryConfigs)) {
                    for (RegistryConfig registryConfig : registryConfigs) {
                        RegistryFactory.getRegistry(registryConfig); // æ??å‰?åˆ?å§‹åŒ–Registry
                    }
                }
            }
            // å°†å¤„ç?†å™¨æ³¨å†Œåˆ°server
            for (ServerConfig serverConfig : serverConfigs) {
                try {
                    Server server = serverConfig.buildIfAbsent();
                    // æ³¨å†Œè¯·æ±‚è°ƒç”¨å™¨
                    server.registerProcessor(providerConfig, providerProxyInvoker);
                    if (serverConfig.isAutoStart()) {
                        server.start();
                    }

                } catch (SofaRpcRuntimeException e) {
                    throw e;
                } catch (Exception e) {
                    LOGGER.errorWithApp(appName, "Catch exception when register processor to server: "
                        + serverConfig.getId(), e);
                }
            }

            // æ³¨å†Œåˆ°æ³¨å†Œä¸­å¿ƒ
            providerConfig.setConfigListener(new ProviderAttributeListener());
            register();
        } catch (Exception e) {
            decrementCounter(hasExportedInCurrent);

            if (e instanceof SofaRpcRuntimeException) {
                throw (SofaRpcRuntimeException) e;
            } else {
                throw new SofaRpcRuntimeException("Build provider proxy error!", e);
            }
        }

        // è®°å½•ä¸€äº›ç¼“å­˜æ•°æ?®
        RpcRuntimeContext.cacheProviderConfig(this);
        exported = true;
    }

    /**
     * decrease counter
     *
     * @param hasExportedInCurrent
     */
    private void decrementCounter(Map<String, Boolean> hasExportedInCurrent) {
        //once error, we decrementAndGet the counter
        for (Map.Entry<String, Boolean> entry : hasExportedInCurrent.entrySet()) {
            String protocol = entry.getKey();
            String key = providerConfig.buildKey() + ":" + protocol;
            AtomicInteger cnt = EXPORTED_KEYS.get(key); // è®¡æ•°å™¨
            if (cnt != null && cnt.get() > 0) {
                cnt.decrementAndGet();
            }
        }
    }

    /**
     * for check fields and parameters of consumer config
     */
    protected void checkParameters() {
        // æ£€æŸ¥æ³¨å…¥çš„refæ˜¯å?¦æŽ¥å?£å®žçŽ°ç±»
        Class proxyClass = providerConfig.getProxyClass();
        String key = providerConfig.buildKey();
        T ref = providerConfig.getRef();
        if (!proxyClass.isInstance(ref)) {
            throw ExceptionUtils.buildRuntime("provider.ref",
                ref == null ? "null" : ref.getClass().getName(),
                "This is not an instance of " + providerConfig.getInterfaceId()
                    + " in provider config with key " + key + " !");
        }
        // server ä¸?èƒ½ä¸ºç©º
        if (CommonUtils.isEmpty(providerConfig.getServer())) {
            throw ExceptionUtils.buildRuntime("server", "NULL", "Value of \"server\" is not specified in provider" +
                " config with key " + key + " !");
        }
        checkMethods(proxyClass);
    }

    /**
     * æ£€æŸ¥æ–¹æ³•ï¼Œä¾‹å¦‚æ–¹æ³•å??ã€?å¤šæ€?ï¼ˆé‡?è½½ï¼‰æ–¹æ³•
     *
     * @param itfClass æŽ¥å?£ç±»
     */
    protected void checkMethods(Class<?> itfClass) {
        ConcurrentHashMap<String, Boolean> methodsLimit = new ConcurrentHashMap<String, Boolean>();
        for (Method method : itfClass.getMethods()) {
            String methodName = method.getName();
            if (methodsLimit.containsKey(methodName)) {
                // é‡?å??çš„æ–¹æ³•
                if (LOGGER.isWarnEnabled(providerConfig.getAppName())) {
                    LOGGER.warnWithApp(providerConfig.getAppName(), "Method with same name \"" + itfClass.getName()
                        + "." + methodName + "\" exists ! The usage of overloading method in rpc is deprecated.");
                }
            }
            // åˆ¤æ–­æœ?åŠ¡ä¸‹æ–¹æ³•çš„é»‘ç™½å??å?•
            Boolean include = methodsLimit.get(methodName);
            if (include == null) {
                include = inList(providerConfig.getInclude(), providerConfig.getExclude(), methodName); // æ£€æŸ¥æ˜¯å?¦åœ¨é»‘ç™½å??å?•ä¸­
                methodsLimit.putIfAbsent(methodName, include);
            }
        }
        providerConfig.setMethodsLimit(methodsLimit);
    }

    @Override
    public void unExport() {
        if (!exported) {
            return;
        }
        synchronized (this) {
            if (!exported) {
                return;
            }
            String appName = providerConfig.getAppName();

            List<ServerConfig> serverConfigs = providerConfig.getServer();
            for (ServerConfig serverConfig : serverConfigs) {
                String protocol = serverConfig.getProtocol();
                String key = providerConfig.buildKey() + ":" + protocol;
                if (LOGGER.isInfoEnabled(appName)) {
                    LOGGER.infoWithApp(appName, "Unexport provider config : {} {}", key, providerConfig.getId() != null
                        ? "with bean id " + providerConfig.getId() : "");
                }
            }

            // å?–æ¶ˆæ³¨å†Œåˆ°æ³¨å†Œä¸­å¿ƒ
            unregister();

            providerProxyInvoker = null;

            // å?–æ¶ˆå°†å¤„ç?†å™¨æ³¨å†Œåˆ°server
            if (serverConfigs != null) {
                for (ServerConfig serverConfig : serverConfigs) {
                    Server server = serverConfig.getServer();
                    if (server != null) {
                        try {
                            server.unRegisterProcessor(providerConfig, serverConfig.isAutoStart());
                        } catch (Exception e) {
                            if (LOGGER.isWarnEnabled(appName)) {
                                LOGGER.warnWithApp(appName, "Catch exception when unRegister processor to server: " +
                                    serverConfig.getId()
                                    + ", but you can ignore if it's called by JVM shutdown hook", e);
                            }
                        }
                    }
                }
            }

            providerConfig.setConfigListener(null);

            // æ¸…é™¤ç¼“å­˜çŠ¶æ€?
            for (ServerConfig serverConfig : serverConfigs) {
                String protocol = serverConfig.getProtocol();
                String key = providerConfig.buildKey() + ":" + protocol;
                AtomicInteger cnt = EXPORTED_KEYS.get(key);
                if (cnt != null && cnt.decrementAndGet() <= 0) {
                    EXPORTED_KEYS.remove(key);
                }
            }

            RpcRuntimeContext.invalidateProviderConfig(this);
            exported = false;
        }
    }

    /**
     * æ³¨å†Œæœ?åŠ¡
     */
    protected void register() {
        if (providerConfig.isRegister()) {
            List<RegistryConfig> registryConfigs = providerConfig.getRegistry();
            if (registryConfigs != null) {
                for (RegistryConfig registryConfig : registryConfigs) {
                    Registry registry = RegistryFactory.getRegistry(registryConfig);
                    registry.init();
                    registry.start();
                    try {
                        registry.register(providerConfig);
                    } catch (SofaRpcRuntimeException e) {
                        throw e;
                    } catch (Throwable e) {
                        String appName = providerConfig.getAppName();
                        if (LOGGER.isWarnEnabled(appName)) {
                            LOGGER.warnWithApp(appName, "Catch exception when register to registry: "
                                + registryConfig.getId(), e);
                        }
                    }
                }
            }
        }
    }

    /**
     * å??æ³¨å†Œæœ?åŠ¡
     */
    protected void unregister() {
        if (providerConfig.isRegister()) {
            List<RegistryConfig> registryConfigs = providerConfig.getRegistry();
            if (registryConfigs != null) {
                for (RegistryConfig registryConfig : registryConfigs) {
                    Registry registry = RegistryFactory.getRegistry(registryConfig);
                    try {
                        registry.unRegister(providerConfig);
                    } catch (Exception e) {
                        String appName = providerConfig.getAppName();
                        if (LOGGER.isWarnEnabled(appName)) {
                            LOGGER.warnWithApp(appName, "Catch exception when unRegister from registry: " +
                                registryConfig.getId()
                                + ", but you can ignore if it's called by JVM shutdown hook", e);
                        }
                    }
                }
            }
        }
    }

    /**
     * Provideré…?ç½®å?‘ç”Ÿå?˜åŒ–ç›‘å?¬å™¨
     */
    private class ProviderAttributeListener implements ConfigListener {

        @Override
        public void configChanged(Map newValue) {
        }

        @Override
        public synchronized void attrUpdated(Map newValueMap) {
            String appName = providerConfig.getAppName();
            // å?¯ä»¥æ”¹å?˜çš„é…?ç½® ä¾‹å¦‚tag concurrentsç­‰
            Map<String, String> newValues = (Map<String, String>) newValueMap;
            Map<String, String> oldValues = new HashMap<String, String>();
            boolean reexport = false;

            // TODO å?¯èƒ½éœ€è¦?å¤„ç?†ServerConfigçš„é…?ç½®å?˜åŒ–
            try { // æ£€æŸ¥æ˜¯å?¦æœ‰å?˜åŒ–
                  // æ˜¯å?¦è¿‡æ»¤map?
                for (Map.Entry<String, String> entry : newValues.entrySet()) {
                    String newValue = entry.getValue();
                    String oldValue = providerConfig.queryAttribute(entry.getKey());
                    boolean changed = oldValue == null ? newValue != null : !oldValue.equals(newValue);
                    if (changed) {
                        oldValues.put(entry.getKey(), oldValue);
                    }
                    reexport = reexport || changed;
                }
            } catch (Exception e) {
                LOGGER.errorWithApp(appName, "Catch exception when provider attribute compare", e);
                return;
            }

            // éœ€è¦?é‡?æ–°å?‘å¸ƒ
            if (reexport) {
                try {
                    if (LOGGER.isInfoEnabled(appName)) {
                        LOGGER.infoWithApp(appName, "Reexport service {}", providerConfig.buildKey());
                    }
                    unExport();
                    // change attrs
                    for (Map.Entry<String, String> entry : newValues.entrySet()) {
                        providerConfig.updateAttribute(entry.getKey(), entry.getValue(), true);
                    }
                    export();
                } catch (Exception e) {
                    LOGGER.errorWithApp(appName, "Catch exception when provider attribute changed", e);
                    //rollback old attrs
                    for (Map.Entry<String, String> entry : oldValues.entrySet()) {
                        providerConfig.updateAttribute(entry.getKey(), entry.getValue(), true);
                    }
                    export();
                }
            }

        }
    }

    /**
     * æŽ¥å?£å?¯ä»¥æŒ‰æ–¹æ³•å?‘å¸ƒ,ä¸?åœ¨é»‘å??å?•é‡Œä¸”åœ¨ç™½å??å?•é‡Œ,*ç®—åœ¨ç™½å??å?•
     *
     * @param includeMethods åŒ…å?«çš„æ–¹æ³•åˆ—è¡¨
     * @param excludeMethods ä¸?åŒ…å?«çš„æ–¹æ³•åˆ—è¡¨
     * @param methodName     æ–¹æ³•å??
     * @return æ–¹æ³•
     */
    protected boolean inList(String includeMethods, String excludeMethods, String methodName) {
        //åˆ¤æ–­æ˜¯å?¦åœ¨ç™½å??å?•ä¸­
        if (!StringUtils.ALL.equals(includeMethods)) {
            if (!inMethodConfigs(includeMethods, methodName)) {
                return false;
            }
        }
        //åˆ¤æ–­æ˜¯å?¦åœ¨é»‘ç™½å?•ä¸­
        if (inMethodConfigs(excludeMethods, methodName)) {
            return false;
        }
        //é»˜è®¤è¿˜æ˜¯è¦?å?‘å¸ƒ
        return true;

    }

    /**
     * å?¦åˆ™å­˜åœ¨method configs å­—ç¬¦ä¸²ä¸­
     *
     * @param methodConfigs
     * @param methodName
     * @return
     */
    private boolean inMethodConfigs(String methodConfigs, String methodName) {
        String[] excludeMethodCollections = StringUtils.splitWithCommaOrSemicolon(methodConfigs);
        for (String excludeMethodName : excludeMethodCollections) {
            boolean exist = StringUtils.equals(excludeMethodName, methodName);
            if (exist) {
                return true;
            }
        }
        return false;
    }
}
