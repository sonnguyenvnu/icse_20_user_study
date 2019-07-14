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
package com.alipay.sofa.rpc.registry.sofa;

import com.alipay.sofa.registry.client.api.ConfigDataObserver;
import com.alipay.sofa.registry.client.api.SubscriberDataObserver;
import com.alipay.sofa.registry.client.api.model.ConfigData;
import com.alipay.sofa.registry.client.api.model.UserData;
import com.alipay.sofa.rpc.client.ProviderGroup;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.client.ProviderInfoAttrs;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.listener.ProviderInfoListener;
import com.alipay.sofa.rpc.log.LogCodes;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryHelper.PROTOCOL_TYPE_OVERRIDE;

/**
 * �?留了订阅列表，一个订阅的dataId，对应一个SofaRegistrySubscribeCallback，对应多个Consumer订阅的ProviderListener。
 * <p>
 * Created by zhanggeng on 2017/7/6.
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">zhanggeng</a>
 */
public class SofaRegistrySubscribeCallback implements SubscriberDataObserver, ConfigDataObserver {

    /**
     * Logger
     */
    private static final Logger                             LOGGER                = LoggerFactory
                                                                                      .getLogger(SofaRegistrySubscribeCallback.class);

    public static final String                              CONFIG_SEPARATOR      = "#";

    //registry constants
    public static final String                              DEFAULT_ZONE          = "DEFAULT_ZONE";

    /**
     * 一个dataId对应多个Listeners
     */
    ConcurrentHashMap<ConsumerConfig, ProviderInfoListener> providerInfoListeners = new ConcurrentHashMap<ConsumerConfig, ProviderInfoListener>();

    /**
     * �?次订阅到的�?务数�?�
     */
    private UserData                                        lastUserData;

    /**
     * �?次订阅到的�?置数�?�
     */
    private ConfigData                                      lastConfigData;

    public SofaRegistrySubscribeCallback() {
    }

    /**
     * 是�?��?�时拿到�?务列表和�?务�?�数<br/>
     * 0.都没拿到<br/>
     * 1.拿到�?务列表<br/>
     * 2.拿到�?务�?�数<br/>
     * 3 或者 null ：都拿到过
     * 本次�?�使用provider
     */
    AtomicBoolean[] flag = new AtomicBoolean[] { new AtomicBoolean(), new AtomicBoolean() };

    @Override
    public void handleData(String dataId, UserData userData) {

        if (dataId == null) {
            return;
        }

        this.lastUserData = userData;

        printUserData(dataId, userData);
        if (flag != null) {
            flag[0].compareAndSet(false, true);
        }

        if (canNotify()) {
            flag = null; // 已�?没作用了
            composeAndNotify(userData, lastConfigData);
        }
    }

    /**
     * 标记为空或者标记等于三，代表�?务列表和�?务�?�数都拿到过，本次�?��?provider有，就继续走
     *
     * @return 是�?�拿到过�?务列表和�?务�?�数
     */
    private boolean canNotify() {
        return flag == null || (flag[0].get());
    }

    /**
     * �?�独通知�?个Listener
     *
     * @param dataId   订阅关键字
     * @param config   �?务端订阅者�?置
     * @param listener �?务列表监�?�器
     */
    void handleDataToListener(String dataId, ConsumerConfig config, ProviderInfoListener listener) {
        if (!canNotify()) {
            return;
        }
        if (lastUserData != null) {
            ComposeUserData composeUserData = composeUserAndConfigData(lastUserData, lastConfigData);
            notifyToListener(listener, composeUserData);
        }
    }

    protected List<String> flatUserData(UserData userData) {
        List<String> result = new ArrayList<String>();
        Map<String, List<String>> zoneData = userData.getZoneData();

        for (Map.Entry<String, List<String>> entry : zoneData.entrySet()) {
            result.addAll(entry.getValue());
        }

        return result;
    }

    protected List<ProviderInfo> flatComposeData(ComposeUserData userData) {
        List<ProviderInfo> result = new ArrayList<ProviderInfo>();

        Map<String, List<ProviderInfo>> zoneData = userData.getZoneData();

        for (Map.Entry<String, List<ProviderInfo>> entry : zoneData.entrySet()) {
            result.addAll(entry.getValue());
        }

        return result;
    }

    /**
     * merge data
     *
     * @param userData
     * @param configData
     * @return
     */
    private ComposeUserData composeUserAndConfigData(UserData userData, ConfigData configData) {

        ComposeUserData result = new ComposeUserData();

        Map<String, List<ProviderInfo>> zoneData = new HashMap<String, List<ProviderInfo>>();
        if (userData == null) {
            return result;
        } else {
            result.setLocalZone(userData.getLocalZone());

            final Map<String, List<String>> listZoneData = userData.getZoneData();
            final String[] configDatas = StringUtils.split(
                configData == null ? StringUtils.EMPTY : configData.getData(), CONFIG_SEPARATOR);
            final List<String> attrData = Arrays.asList(configDatas);
            for (String key : listZoneData.keySet()) {
                final List<ProviderInfo> providerInfos = mergeProviderInfo(listZoneData.get(key), attrData);
                zoneData.put(key, providerInfos);
            }

            result.setZoneData(zoneData);

        }

        return result;
    }

    /**
     * merge url
     *
     * @param userDatas
     * @param configDatas
     * @return
     */
    List<ProviderInfo> mergeProviderInfo(List<String> userDatas, List<String> configDatas) {
        // 是�?�自己缓存�?算�?�的结果？？ TODO
        List<ProviderInfo> providers = SofaRegistryHelper.parseProviderInfos(userDatas);
        // 交�?�比较
        if (CommonUtils.isNotEmpty(providers) && CommonUtils.isNotEmpty(configDatas)) {
            List<ProviderInfo> override = SofaRegistryHelper.parseProviderInfos(configDatas);
            Iterator<ProviderInfo> iterator = providers.iterator();
            while (iterator.hasNext()) {
                ProviderInfo origin = iterator.next();
                for (ProviderInfo over : override) {
                    if (PROTOCOL_TYPE_OVERRIDE.equals(over.getProtocolType()) &&
                        StringUtils.equals(origin.getHost(), over.getHost()) && origin.getPort() == over.getPort()) {
                        // host 和 port 相�?� 认为是一个地�?�
                        if (over.getWeight() != origin.getWeight()) {
                            origin.setWeight(over.getWeight());
                        }
                        if (CommonUtils.isTrue(over.getAttr(ProviderInfoAttrs.ATTR_DISABLED))) {
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("Provider is disabled by override. {}", origin.toUrl());
                            }
                            iterator.remove(); // �?用 删掉
                        }
                    }
                }
            }
        }
        return providers;
    }

    /**
     * 增加监�?�器，一个dataId增加多的ConsumerConfig的listener
     *
     * @param dataId         �?置Id
     * @param consumerConfig �?务调用者信�?�
     * @param listener       �?务列表监�?�器
     */
    void addProviderInfoListener(String dataId, ConsumerConfig consumerConfig,
                                 ProviderInfoListener listener) {
        providerInfoListeners.put(consumerConfig, listener);

        // �?�一个key�?�?订阅多次，�??醒用户需�?检查一下是�?�是代�?问题
        if (LOGGER.isWarnEnabled(consumerConfig.getAppName()) && providerInfoListeners.size() > 5) {
            LOGGER.warnWithApp(consumerConfig.getAppName(),
                "Duplicate to add provider listener of {} " +
                    "more than 5 times, now is {}, please check it",
                dataId, providerInfoListeners.size());
        }
    }

    /**
     * 删除监�?�器
     *
     * @param dataId         �?置Id
     * @param consumerConfig �?务调用者信�?�
     */
    void remove(String dataId, ConsumerConfig consumerConfig) {
        providerInfoListeners.remove(consumerConfig);
    }

    /**
     * 得到监�?�器数�?（如果为0，就�?�以删除了）
     *
     * @return 数�?
     */
    public int getListenerNum() {
        return providerInfoListeners.size();
    }

    /**
     * 获得 Sofa Runtime 的日志对象，打�?�出获得�?置中心地�?�
     */
    private void printUserData(String dataId, UserData userData) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (userData != null && userData.getZoneData() != null) {
            Map<String, List<String>> oneUserData = userData.getZoneData();
            for (Map.Entry<String, List<String>> entry : oneUserData.entrySet()) {
                sb.append("  --- ").append(entry.getKey()).append("\n");
                for (String provider : entry.getValue()) {
                    sb.append("   >>> ").append((String) provider).append("\n");
                    ++count;
                }
            }
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(LogCodes.getLog(LogCodes.INFO_ROUTE_REGISTRY_URLS_HANDLE,
                dataId, count, sb.toString()));
        }
    }

    /**
     * 获得 Sofa Runtime 的日志对象，打�?�出获得Config Data 信�?�
     */
    private void printConfigData(String dataId, ConfigData configData) {

        StringBuilder sb = new StringBuilder();
        int count = 0;

        if (configData != null && StringUtils.isNotBlank(configData.getData())) {
            final String[] split = StringUtils.split(configData.getData(), CONFIG_SEPARATOR);
            List<String> dataList = Arrays.asList(split);
            for (String provider : dataList) {
                sb.append("  >>> ").append(provider).append("\n");
                count++;
            }
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(LogCodes.getLiteLog(
                "Receive RPC config info: service[{0}]\n  usable config info[{1}]\n{2}",
                dataId, count, sb.toString()));
        }
    }

    @Override
    public void handleData(String dataId, ConfigData configData) {

        if (dataId == null) {
            return;
        }

        this.lastConfigData = configData;

        printConfigData(dataId, configData);
        if (flag != null) {
            flag[1].compareAndSet(false, true);
        }
        if (canNotify()) {
            flag = null; // 已�?没作用了
            composeAndNotify(lastUserData, configData);
        }
    }

    private void composeAndNotify(UserData userData, ConfigData configData) {
        // 下�?�了新的�?置
        ComposeUserData mergedResult = composeUserAndConfigData(userData, configData);
        notifyToListener(mergedResult);
    }

    private void notifyToListener(ComposeUserData mergedResult) {
        // 通知所有订阅者
        for (Map.Entry<ConsumerConfig, ProviderInfoListener> entry : providerInfoListeners.entrySet()) {
            notifyToListener(entry.getValue(), mergedResult);
        }
    }

    // 更新一个 listener 的数�?�
    private void notifyToListener(ProviderInfoListener listener, ComposeUserData mergedResult) {

        if ("".equalsIgnoreCase(mergedResult.getLocalZone()) ||
            DEFAULT_ZONE.equalsIgnoreCase(mergedResult.getLocalZone())) {
            listener.updateProviders(new ProviderGroup(flatComposeData(mergedResult)));
        } else {
            final Map<String, List<ProviderInfo>> zoneData = mergedResult.getZoneData();

            List<ProviderGroup> result = new ArrayList<ProviderGroup>();

            for (Map.Entry<String, List<ProviderInfo>> dataEntry : zoneData.entrySet()) {

                //localZone 的特殊放到 default 分组一份.为了在目标 zone �?�?�用的情况下兜底
                if (dataEntry.getKey().equalsIgnoreCase(mergedResult.getLocalZone())) {
                    result.add(new ProviderGroup(dataEntry.getValue()));
                }
                //其他 zone 的正常放
                result.add(new ProviderGroup(dataEntry.getKey(), dataEntry.getValue()));

            }

            listener.updateAllProviders(result);

        }
    }
}
