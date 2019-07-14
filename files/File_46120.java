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
package com.alipay.sofa.rpc.registry.mesh;

import com.alipay.sofa.rpc.client.ProviderGroup;
import com.alipay.sofa.rpc.client.ProviderHelper;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.event.ConsumerSubEvent;
import com.alipay.sofa.rpc.event.EventBus;
import com.alipay.sofa.rpc.event.ProviderPubEvent;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.log.LogCodes;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.registry.Registry;
import com.alipay.sofa.rpc.registry.mesh.client.MeshApiClient;
import com.alipay.sofa.rpc.registry.mesh.model.ApplicationInfoRequest;
import com.alipay.sofa.rpc.registry.mesh.model.MeshConstants;
import com.alipay.sofa.rpc.registry.mesh.model.ProviderMetaInfo;
import com.alipay.sofa.rpc.registry.mesh.model.PublishServiceRequest;
import com.alipay.sofa.rpc.registry.mesh.model.SubscribeServiceRequest;
import com.alipay.sofa.rpc.registry.mesh.model.SubscribeServiceResult;
import com.alipay.sofa.rpc.registry.mesh.model.UnPublishServiceRequest;
import com.alipay.sofa.rpc.registry.mesh.model.UnSubscribeServiceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * mesh registry
 *
 * @author <a href="mailto:zhiyuan.lzy@antfin.com">zhiyuan.lzy</a>
 */
@Extension("mesh")
public class MeshRegistry extends Registry {

    /**
     * Logger
     */
    private static final Logger LOGGER  = LoggerFactory.getLogger(MeshRegistry.class);

    private static final String VERSION = "4.0";

    private MeshApiClient       client;

    /**
     * æ³¨å†Œä¸­å¿ƒé…?ç½®
     *
     * @param registryConfig æ³¨å†Œä¸­å¿ƒé…?ç½®
     */
    protected MeshRegistry(RegistryConfig registryConfig) {
        super(registryConfig);
    }

    //init only once
    private boolean inited;

    //has registed app info
    private boolean registedApp;

    @Override
    public void init() {
        synchronized (MeshRegistry.class) {
            if (!inited) {
                String address = registryConfig.getAddress();
                client = new MeshApiClient(address);
                inited = true;
            }
        }
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void register(ProviderConfig config) {
        String appName = config.getAppName();
        if (!registryConfig.isRegister()) {
            if (LOGGER.isInfoEnabled(appName)) {
                LOGGER.infoWithApp(appName, LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
            }
            return;
        }
        if (!config.isRegister()) { // æ³¨å†Œä¸­å¿ƒä¸?æ³¨å†Œæˆ–è€…æœ?åŠ¡ä¸?æ³¨å†Œ
            return;
        }
        List<ServerConfig> serverConfigs = config.getServer();
        if (CommonUtils.isNotEmpty(serverConfigs)) {
            for (ServerConfig server : serverConfigs) {
                String serviceName = MeshRegistryHelper.buildMeshKey(config, server.getProtocol());
                ProviderInfo providerInfo = MeshRegistryHelper.convertProviderToProviderInfo(config, server);
                if (LOGGER.isInfoEnabled(appName)) {
                    LOGGER.infoWithApp(appName, LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_PUB_START, serviceName));
                }
                doRegister(appName, serviceName, providerInfo);

                if (LOGGER.isInfoEnabled(appName)) {
                    LOGGER.infoWithApp(appName, LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_PUB_OVER, serviceName));
                }
            }
            if (EventBus.isEnable(ProviderPubEvent.class)) {
                ProviderPubEvent event = new ProviderPubEvent(config);
                EventBus.post(event);
            }

        }
    }

    /**
     * æ³¨å†Œå?•æ?¡æœ?åŠ¡ä¿¡æ?¯
     *
     * @param appName      åº”ç”¨å??
     * @param serviceName  æœ?åŠ¡å…³é”®å­—
     * @param providerInfo æœ?åŠ¡æ??ä¾›è€…æ•°æ?®
     */
    protected void doRegister(String appName, String serviceName, ProviderInfo providerInfo) {

        registerAppInfoOnce(appName);

        if (LOGGER.isInfoEnabled(appName)) {
            LOGGER.infoWithApp(appName, LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_PUB, serviceName));
        }

        PublishServiceRequest publishServiceRequest = new PublishServiceRequest();
        publishServiceRequest.setServiceName(serviceName);
        ProviderMetaInfo providerMetaInfo = new ProviderMetaInfo();
        providerMetaInfo.setProtocol(providerInfo.getProtocolType());
        providerMetaInfo.setSerializeType(providerInfo.getSerializationType());
        providerMetaInfo.setAppName(appName);
        providerMetaInfo.setVersion(VERSION);
        publishServiceRequest.setProviderMetaInfo(providerMetaInfo);

        client.publishService(publishServiceRequest);
    }

    @Override
    public void unRegister(ProviderConfig config) {
        String appName = config.getAppName();
        if (!registryConfig.isRegister()) { // æ³¨å†Œä¸­å¿ƒä¸?æ³¨å†Œ
            if (LOGGER.isInfoEnabled(appName)) {
                LOGGER.infoWithApp(appName, LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
            }
            return;
        }
        if (!config.isRegister()) { // æœ?åŠ¡ä¸?æ³¨å†Œ
            return;
        }
        List<ServerConfig> serverConfigs = config.getServer();
        if (CommonUtils.isNotEmpty(serverConfigs)) {
            for (ServerConfig server : serverConfigs) {
                String serviceName = MeshRegistryHelper.buildMeshKey(config, server.getProtocol());
                ProviderInfo providerInfo = MeshRegistryHelper.convertProviderToProviderInfo(config, server);
                try {
                    doUnRegister(serviceName, providerInfo);
                    if (LOGGER.isInfoEnabled(appName)) {
                        LOGGER.infoWithApp(appName,
                            LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_UNPUB, serviceName, "1"));
                    }
                } catch (Exception e) {
                    LOGGER.errorWithApp(appName, LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_UNPUB, serviceName, "0"),
                        e);
                }
            }
        }
    }

    /**
     * å??æ³¨å†Œæœ?åŠ¡ä¿¡æ?¯
     *
     * @param serviceName  æœ?åŠ¡å…³é”®å­—
     * @param providerInfo æœ?åŠ¡æ??ä¾›è€…æ•°æ?®
     */
    protected void doUnRegister(String serviceName, ProviderInfo providerInfo) {

        UnPublishServiceRequest unPublishServiceRequest = new UnPublishServiceRequest();
        unPublishServiceRequest.setServiceName(serviceName);
        client.unPublishService(unPublishServiceRequest);

    }

    @Override
    public void batchUnRegister(List<ProviderConfig> configs) {
        for (ProviderConfig config : configs) {
            String appName = config.getAppName();
            try {
                unRegister(config);
            } catch (Exception e) {
                LOGGER.errorWithApp(appName, "Error when batch unregistry", e);
            }
        }
    }

    @Override
    public List<ProviderGroup> subscribe(ConsumerConfig config) {
        final String appName = config.getAppName();

        registerAppInfoOnce(appName);

        String key = MeshRegistryHelper.buildMeshKey(config, config.getProtocol());
        SubscribeServiceRequest subscribeRequest = new SubscribeServiceRequest();
        subscribeRequest.setServiceName(key);
        SubscribeServiceResult subscribeServiceResult = client.subscribeService(subscribeRequest);

        if (subscribeServiceResult == null || !subscribeServiceResult.isSuccess()) {
            throw new RuntimeException("regist consumer occors error," + subscribeRequest);

        }

        List<ProviderGroup> providerGroups = new ArrayList<ProviderGroup>();

        ProviderGroup providerGroup = new ProviderGroup();

        List<ProviderInfo> providerInfos = new ArrayList<ProviderInfo>();

        String url = fillProtocolAndVersion(subscribeServiceResult, client.getHost(), "");

        ProviderInfo providerInfo = ProviderHelper.toProviderInfo(url);
        providerInfos.add(providerInfo);
        providerGroup.setProviderInfos(providerInfos);

        providerGroups.add(providerGroup);

        if (EventBus.isEnable(ConsumerSubEvent.class)) {
            ConsumerSubEvent event = new ConsumerSubEvent(config);
            EventBus.post(event);
        }

        return providerGroups;
    }

    protected void registerAppInfoOnce(String appName) {
        synchronized (MeshRegistry.class) {
            if (!registedApp) {
                ApplicationInfoRequest applicationInfoRequest = buildApplicationRequest(appName);
                boolean registed = client.registeApplication(applicationInfoRequest);
                if (!registed) {
                    throw new RuntimeException("registe application occors error," + applicationInfoRequest);
                } else {
                    registedApp = true;
                }
            }
        }
    }

    /**
     * can be extended
     *
     * @param appName
     * @return
     */
    protected ApplicationInfoRequest buildApplicationRequest(String appName) {
        ApplicationInfoRequest applicationInfoRequest = new ApplicationInfoRequest();
        applicationInfoRequest.setAppName(appName);
        return applicationInfoRequest;
    }

    protected String fillProtocolAndVersion(SubscribeServiceResult subscribeServiceResult, String targetURL,
                                            String serviceName) {

        final List<String> datas = subscribeServiceResult.getDatas();

        if (datas == null) {
            targetURL = targetURL + ":" + MeshConstants.TCP_PORT;
        } else {
            for (String data : subscribeServiceResult.getDatas()) {
                String param = data.substring(data.indexOf("?"));
                targetURL = targetURL + ":" + MeshConstants.TCP_PORT;
                targetURL = targetURL + param;
                break;
            }
        }
        return targetURL;
    }

    @Override
    public void unSubscribe(ConsumerConfig config) {
        String key = MeshRegistryHelper.buildMeshKey(config, config.getProtocol());
        UnSubscribeServiceRequest unsubscribeRequest = new UnSubscribeServiceRequest();

        unsubscribeRequest.setServiceName(key);
        client.unSubscribeService(unsubscribeRequest);
    }

    @Override
    public void batchUnSubscribe(List<ConsumerConfig> configs) {
        // ä¸?æ”¯æŒ?æ‰¹é‡?å??æ³¨å†Œï¼Œé‚£å°±ä¸€ä¸ªä¸ªæ?¥å?§
        for (ConsumerConfig config : configs) {
            String appName = config.getAppName();
            try {
                unSubscribe(config);
            } catch (Exception e) {
                LOGGER.errorWithApp(appName, "Error when batch unSubscribe", e);
            }
        }
    }

    @Override
    public void destroy() {
        // é”€æ¯?å‰?å¤‡ä»½ä¸€ä¸‹
        client = null;
    }
}
