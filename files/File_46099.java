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
package com.alipay.sofa.rpc.registry.local;

import com.alipay.sofa.rpc.client.ProviderGroup;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.common.struct.MapDifference;
import com.alipay.sofa.rpc.common.struct.ScheduledService;
import com.alipay.sofa.rpc.common.struct.ValueDifference;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.event.ConsumerSubEvent;
import com.alipay.sofa.rpc.event.EventBus;
import com.alipay.sofa.rpc.event.ProviderPubEvent;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.listener.ProviderInfoListener;
import com.alipay.sofa.rpc.log.LogCodes;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.registry.Registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Local registry
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
@Extension("local")
public class LocalRegistry extends Registry {

    /**
     * Logger
     */
    private static final Logger                 LOGGER          = LoggerFactory.getLogger(LocalRegistry.class);

    /**
     * 定时加载
     */
    private ScheduledService                    scheduledExecutorService;

    /**
     * 内存里的�?务列表 {service : [provider...]}
     */
    protected Map<String, ProviderGroup>        memoryCache     = new ConcurrentHashMap<String, ProviderGroup>();

    /**
     * 内存�?�生了�?�化，如果为true，则将触�?�写入文件动作
     */
    private boolean                             needBackup      = false;

    /**
     * 是�?�订阅通知（�?�扫�??文件�?�化），默认为true
     * 如果FileRegistry是被动加载（例如作为注册中心备份的）的，建议false，防止�?�?通知
     */
    private boolean                             subscribe       = true;

    /**
     * 订阅者通知列表（key为订阅者关键字，value为ConsumerConfig列表）
     */
    protected Map<String, List<ConsumerConfig>> notifyListeners = new ConcurrentHashMap<String, List<ConsumerConfig>>();

    /**
     * 最�?�一次digest值
     */
    private String                              lastDigest;

    /**
     * 扫�??周期，毫秒
     */
    private int                                 scanPeriod      = 2000;
    /**
     * 输出和备份文件目录
     */
    private String                              regFile;

    /**
     * 注册中心�?置
     *
     * @param registryConfig 注册中心�?置
     */
    protected LocalRegistry(RegistryConfig registryConfig) {
        super(registryConfig);
    }

    @Override
    public void init() {

        if (StringUtils.isNotBlank(regFile)) {
            return;
        }

        this.regFile = registryConfig.getFile();
        if (regFile == null) {
            throw new SofaRpcRuntimeException("File of LocalRegistry is null");
        }
        // 先加载一些
        if (subscribe) {
            doLoadCache();
        }
        // 开始扫�??
        this.scanPeriod = CommonUtils.parseInt(registryConfig.getParameter("registry.local.scan.period"),
            scanPeriod);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    // 如果�?求备份，那么说明内存中为最新的，无需加载
                    doWriteFile();

                    // 订阅�?�化（默认是�?订阅的）
                    // 检查摘�?，如果有有�?�，则自动�?新加载
                    if (subscribe && LocalRegistryHelper.checkModified(regFile, lastDigest)) {
                        doLoadCache();
                    }
                } catch (Throwable e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        };
        //�?�动扫�??线程
        scheduledExecutorService = new ScheduledService("LocalRegistry-Back-Load",
            ScheduledService.MODE_FIXEDDELAY,
            task, //定时load任务
            scanPeriod, // 延迟一个周期
            scanPeriod, // 一个周期循环
            TimeUnit.MILLISECONDS
                ).start();

    }

    protected void doLoadCache() {
        // 加载到内存
        Map<String, ProviderGroup> tempCache = LocalRegistryHelper.loadBackupFileToCache(regFile);
        // 比较旧列表和新列表，通知订阅者�?�化部分
        notifyConsumer(tempCache);

        // 通知完�?存到内存
        memoryCache = tempCache;
        // 如果有文件更新,将上一次更新时间�?�?为当�?时间
        lastDigest = LocalRegistryHelper.calMD5Checksum(regFile);
    }

    /**
     * 写文件
     */
    protected void doWriteFile() {
        if (needBackup) {
            if (LocalRegistryHelper.backup(regFile, memoryCache)) {
                needBackup = false;
            }
        }
    }

    @Override
    public boolean start() {
        return false;
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
        if (!config.isRegister()) { // 注册中心�?注册或者�?务�?注册
            return;
        }
        List<ServerConfig> serverConfigs = config.getServer();
        if (CommonUtils.isNotEmpty(serverConfigs)) {
            for (ServerConfig server : serverConfigs) {
                String serviceName = LocalRegistryHelper.buildListDataId(config, server.getProtocol());
                ProviderInfo providerInfo = LocalRegistryHelper.convertProviderToProviderInfo(config, server);
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
     * 注册�?��?��?务信�?�
     *
     * @param appName      应用�??
     * @param serviceName  �?务关键字
     * @param providerInfo �?务�??供者数�?�
     */
    protected void doRegister(String appName, String serviceName, ProviderInfo providerInfo) {
        if (LOGGER.isInfoEnabled(appName)) {
            LOGGER.infoWithApp(appName, LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_PUB, serviceName));
        }
        //{service : [provider...]}
        ProviderGroup oldGroup = memoryCache.get(serviceName);
        if (oldGroup != null) { // 存在�?的key
            oldGroup.add(providerInfo);
        } else { // 没有�?的key，第一次加入
            List<ProviderInfo> news = new ArrayList<ProviderInfo>();
            news.add(providerInfo);
            memoryCache.put(serviceName, new ProviderGroup(news));
        }
        // 备份到文件 改为定时写
        needBackup = true;
        doWriteFile();

        if (subscribe) {
            notifyConsumerListeners(serviceName, memoryCache.get(serviceName));
        }
    }

    @Override
    public void unRegister(ProviderConfig config) {
        String appName = config.getAppName();
        if (!registryConfig.isRegister()) { // 注册中心�?注册
            if (LOGGER.isInfoEnabled(appName)) {
                LOGGER.infoWithApp(appName, LogCodes.getLog(LogCodes.INFO_REGISTRY_IGNORE));
            }
            return;
        }
        if (!config.isRegister()) { // �?务�?注册
            return;
        }
        List<ServerConfig> serverConfigs = config.getServer();
        if (CommonUtils.isNotEmpty(serverConfigs)) {
            for (ServerConfig server : serverConfigs) {
                String serviceName = LocalRegistryHelper.buildListDataId(config, server.getProtocol());
                ProviderInfo providerInfo = LocalRegistryHelper.convertProviderToProviderInfo(config, server);
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
     * �??注册�?务信�?�
     *
     * @param serviceName  �?务关键字
     * @param providerInfo �?务�??供者数�?�
     */
    protected void doUnRegister(String serviceName, ProviderInfo providerInfo) {
        //{service : [provider...]}
        ProviderGroup oldGroup = memoryCache.get(serviceName);
        if (oldGroup != null) { // 存在�?的key
            oldGroup.remove(providerInfo);
        } else {
            return;
        }
        // 备份到文件 改为定时写
        needBackup = true;
        doWriteFile();

        if (subscribe) {
            notifyConsumerListeners(serviceName, memoryCache.get(serviceName));
        }
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
        String key = LocalRegistryHelper.buildListDataId(config, config.getProtocol());
        List<ConsumerConfig> listeners = notifyListeners.get(key);
        if (listeners == null) {
            listeners = new ArrayList<ConsumerConfig>();
            notifyListeners.put(key, listeners);
        }
        listeners.add(config);
        // 返回已�?加载到内存的列表（�?�能�?是最新的)
        ProviderGroup group = memoryCache.get(key);
        if (group == null) {
            group = new ProviderGroup();
            memoryCache.put(key, group);
        }

        if (EventBus.isEnable(ConsumerSubEvent.class)) {
            ConsumerSubEvent event = new ConsumerSubEvent(config);
            EventBus.post(event);
        }

        return Collections.singletonList(group);
    }

    @Override
    public void unSubscribe(ConsumerConfig config) {
        String key = LocalRegistryHelper.buildListDataId(config, config.getProtocol());
        // �?�消注册订阅关系，监�?�文件修改�?�化
        List<ConsumerConfig> listeners = notifyListeners.get(key);
        if (listeners != null) {
            listeners.remove(config);
            if (listeners.size() == 0) {
                notifyListeners.remove(key);
            }
        }
    }

    @Override
    public void batchUnSubscribe(List<ConsumerConfig> configs) {
        // �?支�?批�?�??注册，那就一个个�?��?�
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
        // 销�?�?备份一下
        // LocalRegistryHelper.backup(regFile, memoryCache);
        try {
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
                scheduledExecutorService = null;
            }
        } catch (Throwable t) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(t.getMessage(), t);
            }
        }
    }

    /**
     * Notify consumer.
     *
     * @param newCache the new cache
     */
    void notifyConsumer(Map<String, ProviderGroup> newCache) {
        Map<String, ProviderGroup> oldCache = memoryCache;
        // 比较两个map的差异
        MapDifference<String, ProviderGroup> difference =
                new MapDifference<String, ProviderGroup>(newCache, oldCache);
        // 新的有，旧的没有，通知
        Map<String, ProviderGroup> onlynew = difference.entriesOnlyOnLeft();
        for (Map.Entry<String, ProviderGroup> entry : onlynew.entrySet()) {
            notifyConsumerListeners(entry.getKey(), entry.getValue());
        }
        // 旧的有，新的没有，全部干掉
        Map<String, ProviderGroup> onlyold = difference.entriesOnlyOnRight();
        for (Map.Entry<String, ProviderGroup> entry : onlyold.entrySet()) {
            notifyConsumerListeners(entry.getKey(), new ProviderGroup());
        }

        // 新旧都有，而且有�?�化
        Map<String, ValueDifference<ProviderGroup>> changed = difference.entriesDiffering();
        for (Map.Entry<String, ValueDifference<ProviderGroup>> entry : changed.entrySet()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} has differente", entry.getKey());
            }
            ValueDifference<ProviderGroup> differentValue = entry.getValue();
            ProviderGroup innew = differentValue.leftValue();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("new(right) is {}", innew);
            }
            // �?�通知�?�化部分内容
            notifyConsumerListeners(entry.getKey(), innew);
        }
    }

    private void notifyConsumerListeners(String serviceName, ProviderGroup providerGroup) {
        List<ConsumerConfig> consumerConfigs = notifyListeners.get(serviceName);
        if (consumerConfigs != null) {
            for (ConsumerConfig config : consumerConfigs) {
                ProviderInfoListener listener = config.getProviderInfoListener();
                if (listener != null) {
                    listener.updateProviders(providerGroup); // 更新分组
                }
            }
        }
    }
}
