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

import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.client.ProviderInfoAttrs;
import com.alipay.sofa.rpc.client.ProviderStatus;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.SystemInfo;
import com.alipay.sofa.rpc.common.Version;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.NetUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.AbstractInterfaceConfig;
import com.alipay.sofa.rpc.config.ConfigUniqueNameGenerator;
import com.alipay.sofa.rpc.config.MethodConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_APP_NAME;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_CONNECTIONS;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_HOST_MACHINE;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_RPC_VERSION;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_SERIALIZATION;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_START_TIME;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_TIMEOUT;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_WARMUP_TIME;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_WARMUP_WEIGHT;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_WARM_UP_END_TIME;
import static com.alipay.sofa.rpc.client.ProviderInfoAttrs.ATTR_WEIGHT;
import static com.alipay.sofa.rpc.common.RpcConstants.PROTOCOL_TYPE_BOLT;
import static com.alipay.sofa.rpc.common.RpcConstants.PROTOCOL_TYPE_TR;
import static com.alipay.sofa.rpc.common.RpcConstants.SERIALIZE_HESSIAN;
import static com.alipay.sofa.rpc.common.RpcConstants.SERIALIZE_HESSIAN2;
import static com.alipay.sofa.rpc.common.RpcConstants.SERIALIZE_JAVA;
import static com.alipay.sofa.rpc.common.RpcConstants.SERIALIZE_PROTOBUF;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.APP_NAME;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.HOST_MACHINE_KEY;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.KEY_TIMEOUT;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.RPC_REMOTING_PROTOCOL;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.RPC_SERVICE_VERSION;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.SERIALIZE_TYPE_KEY;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.SOFA4_RPC_SERVICE_VERSION;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.TIMEOUT;
import static com.alipay.sofa.rpc.registry.sofa.SofaRegistryConstants.WEIGHT_KEY;

/**
 * Created by zhanggeng on 2017/7/5.
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">zhanggeng</a>
 */
public class SofaRegistryHelper {

    private static final Logger LOGGER                     = LoggerFactory.getLogger(SofaRegistryHelper.class);

    /**
     * 注册关键字�?缀
     */
    public static final String  PUBLISHER_PREFIX           = "SofaProvider-";
    /**
     * 订阅关键字�?缀
     */
    public static final String  SUBSCRIBER_PREFIX          = "SofaSubscriber-";

    /**
     * �?务�?�数�?置格�?GroupId 统一为 SOFA.CONFIG
     */
    public static final String  SUBSCRIBER_LIST_GROUP_ID   = "SOFA";

    /**
     * �?务�?�数�?置格�?GroupId 统一为 SOFA.CONFIG
     */
    public static final String  SUBSCRIBER_CONFIG_GROUP_ID = "SOFA.CONFIG";

    /**
     * �?务�??议：�?置覆盖，特殊
     */
    public static final String  PROTOCOL_TYPE_OVERRIDE     = "override";

    /**
     * 构建�?务列表的DataId， 格�?为interface:version[:uniqueId]@protocol
     *
     * @param config   �?置
     * @param protocol �??议
     * @return 返回值
     */
    public static String buildListDataId(AbstractInterfaceConfig config, String protocol) {
        if (RpcConstants.PROTOCOL_TYPE_BOLT.equals(protocol) || RpcConstants.PROTOCOL_TYPE_TR.equals(protocol)) {
            return ConfigUniqueNameGenerator.getUniqueName(config) + "@DEFAULT";
        } else {
            return ConfigUniqueNameGenerator.getUniqueName(config) + "@" + protocol;
        }
    }

    /**
     * Convert provider to url.
     *
     * @param providerConfig the ProviderConfig
     * @return the url list
     */
    public static String convertProviderToUrls(ProviderConfig providerConfig, ServerConfig server) {
        StringBuilder sb = new StringBuilder(200);
        String appName = providerConfig.getAppName();
        String host = server.getVirtualHost(); // 虚拟ip
        if (host == null) {
            host = server.getHost();
            if (NetUtils.isLocalHost(host) || NetUtils.isAnyHost(host)) {
                host = SystemInfo.getLocalHost();
            }
        } else {
            if (LOGGER.isWarnEnabled(appName)) {
                LOGGER.warnWithApp(appName,
                    "Virtual host is specified, host will be change from {} to {} when register",
                    server.getHost(), host);
            }
        }
        Integer port = server.getVirtualPort(); // 虚拟port
        if (port == null) {
            port = server.getPort();
        } else {
            if (LOGGER.isWarnEnabled(appName)) {
                LOGGER.warnWithApp(appName,
                    "Virtual port is specified, host will be change from {} to {} when register",
                    server.getPort(), port);
            }
        }

        String protocol = server.getProtocol();
        sb.append(host).append(":").append(port).append(server.getContextPath());
        //                .append(providerConfig.getInterfaceId())
        sb.append("?").append(ATTR_RPC_VERSION).append("=").append(Version.RPC_VERSION);
        sb.append(getKeyPairs(ATTR_SERIALIZATION, providerConfig.getSerialization()));
        sb.append(getKeyPairs(ATTR_WEIGHT, providerConfig.getWeight()));
        if (providerConfig.getTimeout() > 0) {
            sb.append(getKeyPairs(ATTR_TIMEOUT, providerConfig.getTimeout()));
        }
        sb.append(getKeyPairs(ATTR_APP_NAME, appName));

        //        sb.append(getKeyPairs("delay", providerConfig.getDelay()))
        //                .append(getKeyPairs("timeout", providerConfig.getTimeout()))
        //                .append(getKeyPairs("delay", providerConfig.getDelay()))
        //                .append(getKeyPairs("id", providerConfig.getId()))
        //                .append(getKeyPairs("dynamic", providerConfig.isDynamic()))
        //                .append(getKeyPairs("weight", providerConfig.getWeight()))
        //                .append(getKeyPairs("crossLang", providerConfig.getParameter("crossLang")))
        //                .append(getKeyPairs("accepts", server.getAccepts()));

        // 兼容�?系统，代�?是�?�剥离？
        if (PROTOCOL_TYPE_BOLT.equals(protocol)) {
            sb.append(getKeyPairs(RPC_REMOTING_PROTOCOL, RemotingConstants.PROTOCOL_BOLT)); // p=1
        } else if (PROTOCOL_TYPE_TR.equals(protocol)) {
            sb.append(getKeyPairs(RPC_REMOTING_PROTOCOL, RemotingConstants.PROTOCOL_TR));// p=13
        }
        sb.append(getKeyPairs(RPC_SERVICE_VERSION, SOFA4_RPC_SERVICE_VERSION)); // v=4.0
        sb.append(getKeyPairs(SERIALIZE_TYPE_KEY, providerConfig.getSerialization())); // _SERIALIZETYPE=xx
        sb.append(getKeyPairs(WEIGHT_KEY, providerConfig.getWeight())); // _WEIGHT=100
        if (providerConfig.getTimeout() > 0) {
            sb.append(getKeyPairs(TIMEOUT, providerConfig.getTimeout())); // _TIMEOUT=3000
        }
        sb.append(getKeyPairs(APP_NAME, appName));
        // sb.append(getKeyPairs(SELF_APP_NAME, appName)); //TODO self_app_name
        // sb.append(getKeyPairs(IDLE_TIMEOUT, 27)); //TODO _IDLETIMEOUT
        // sb.append(getKeyPairs(MAX_READ_IDLE, 30)); //TODO _MAXREADIDLETIME

        if (StringUtils.isNotBlank(SystemInfo.getHostMachine())) {
            sb.append(getKeyPairs(HOST_MACHINE_KEY, SystemInfo.getHostMachine()));
        }

        Map<String, MethodConfig> methodConfigs = providerConfig.getMethods();
        if (CommonUtils.isNotEmpty(methodConfigs)) {
            for (Map.Entry<String, MethodConfig> entry : methodConfigs.entrySet()) {
                String methodName = entry.getKey();
                MethodConfig methodConfig = entry.getValue();
                sb.append(getKeyPairs("." + methodName + "." + ATTR_TIMEOUT, methodConfig.getTimeout()));

                // 方法级�?置，�?�能放timeout 
                String key = "[" + methodName + "]";
                String value = "[" + KEY_TIMEOUT + "#" + methodConfig.getTimeout() + "]";
                sb.append(getKeyPairs(key, value));
            }
        }
        sb.append(convertMap2Pair(providerConfig.getParameters()));
        addCommonAttrs(sb);
        return sb.toString();
    }

    /**
     * convert map to url pair
     *
     * @param map
     * @return url paramters
     */
    private static String convertMap2Pair(Map<String, String> map) {

        if (CommonUtils.isEmpty(map)) {
            return StringUtils.EMPTY;
        }

        StringBuilder sb = new StringBuilder(128);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(getKeyPairs(entry.getKey(), entry.getValue()));
        }

        return sb.toString();
    }

    /**
     * Gets key pairs.
     *
     * @param key   the key
     * @param value the value
     * @return the key pairs
     */
    private static String getKeyPairs(String key, Object value) {
        if (value != null) {
            return "&" + key + "=" + value.toString();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 加入一些公共的�?外属性
     *
     * @param sb 属性
     */
    private static void addCommonAttrs(StringBuilder sb) {
        sb.append(getKeyPairs(ATTR_START_TIME, RpcRuntimeContext.now()));
        //sb.append(getKeyPairs("pid", RpcRuntimeContext.PID));
        //sb.append(getKeyPairs("language", "java"));
        //sb.append(getKeyPairs("appPath", RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPAPTH)));
        //sb.append(getKeyPairs("appId", RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPID)));
        //sb.append(getKeyPairs("appInsId", RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPINSID)));
    }

    /**
     * 解�?�?务地�?�列表，从字符串，统一适�?到ProviderInfo对象
     *
     * @param urls 地�?�列表
     * @return 地�?�列表，最多为空，�?会返回null
     */
    public static List<ProviderInfo> parseProviderInfos(List<String> urls) {
        List<ProviderInfo> providers = new ArrayList<ProviderInfo>();
        if (CommonUtils.isNotEmpty(urls)) {
            for (String object : urls) {
                providers.add(parseProviderInfo(object));
            }
        }
        return providers;
    }

    /**
     * 解�?�?置中心的字符串，兼容�?系统的解�?
     *
     * @param originUrl 原始字符串
     * @return ProviderInfo对象
     */
    public static ProviderInfo parseProviderInfo(String originUrl) {
        String url = originUrl;
        String host = null;
        int port = 80;
        String path = null;
        String schema = null;
        int i = url.indexOf("://"); // seperator between schema and body
        if (i > 0) {
            schema = url.substring(0, i); // http
            url = url.substring(i + 3); // 127.0.0.1:8080/xxx/yyy?a=1&b=2&[c]=[ccc]
        }
        Map<String, String> parameters = new HashMap<String, String>();
        i = url.indexOf('?'); // seperator between body and parameters
        if (i >= 0) {
            String[] parts = url.substring(i + 1).split("\\&"); //a=1&b=2&[c]=[ccc]
            for (String part : parts) {
                part = part.trim();
                if (part.length() > 0) {
                    int j = part.indexOf('=');
                    if (j >= 0) {
                        parameters.put(part.substring(0, j), part.substring(j + 1));
                    } else {
                        parameters.put(part, part);
                    }
                }
            }
            url = url.substring(0, i); //  127.0.0.1:8080/xxx/yyy
        }
        i = url.indexOf('/');
        if (i >= 0) {
            path = url.substring(i + 1); // xxx/yyy
            url = url.substring(0, i); // 127.0.0.1:8080
        }
        i = url.indexOf(':');
        if (i >= 0 && i < url.length() - 1) {
            port = Integer.parseInt(url.substring(i + 1)); // 8080
            url = url.substring(0, i); // 127.0.0.1
        }
        if (url.length() > 0) {
            host = url; // 127.0.0.1
        }

        ProviderInfo providerInfo = new ProviderInfo();
        providerInfo.setOriginUrl(originUrl);
        providerInfo.setHost(host);
        if (port != 80) {
            providerInfo.setPort(port);
        }
        if (path != null) {
            providerInfo.setPath(path);
        }
        if (schema != null) {
            providerInfo.setProtocolType(schema);
        }

        // 解�?特殊属性
        // p=1
        String protocolStr = getValue(parameters, RPC_REMOTING_PROTOCOL);
        if (schema == null && protocolStr != null) {
            // 1->bolt 13->tr
            if ((RemotingConstants.PROTOCOL_BOLT + "").equals(protocolStr)) {
                protocolStr = PROTOCOL_TYPE_BOLT;
            } else if ((RemotingConstants.PROTOCOL_TR + "").equals(protocolStr)) {
                protocolStr = PROTOCOL_TYPE_TR;
            }
            try {
                providerInfo.setProtocolType(protocolStr);
            } catch (Exception e) {
                LOGGER.error("protocol is invalid : {}", originUrl);
            }
        }
        // TODO SOFAVERSION v=4.0
        // timeout 
        String timeoutStr = getValue(parameters, ATTR_TIMEOUT, TIMEOUT);
        if (timeoutStr != null) {
            removeOldKeys(parameters, ATTR_TIMEOUT, TIMEOUT);
            try {// 加入动�?
                providerInfo.setDynamicAttr(ATTR_TIMEOUT, Integer.parseInt(timeoutStr));
            } catch (Exception e) {
                LOGGER.error("timeout is invalid : {}", originUrl);
            }
        }
        // serializeType 使用字符传递
        String serializationStr = getValue(parameters, ATTR_SERIALIZATION,
            SERIALIZE_TYPE_KEY);
        if (serializationStr != null) {
            removeOldKeys(parameters, ATTR_SERIALIZATION, SERIALIZE_TYPE_KEY);
            // 1 -> hessian   2->java   4->hessian2  11->protobuf
            if ((RemotingConstants.SERIALIZE_CODE_HESSIAN + "").equals(serializationStr)) {
                serializationStr = SERIALIZE_HESSIAN;
            } else if ((RemotingConstants.SERIALIZE_CODE_JAVA + "").equals(serializationStr)) {
                serializationStr = SERIALIZE_JAVA;
            } else if ((RemotingConstants.SERIALIZE_CODE_HESSIAN2 + "").equals(serializationStr)) {
                serializationStr = SERIALIZE_HESSIAN2;
            } else if ((RemotingConstants.SERIALIZE_CODE_PROTOBUF + "").equals(serializationStr)) {
                serializationStr = SERIALIZE_PROTOBUF;
            }
            providerInfo.setSerializationType(serializationStr);
        }
        // appName
        String appNameStr = getValue(parameters, ATTR_APP_NAME, APP_NAME,
            SofaRegistryConstants.SELF_APP_NAME);
        if (appNameStr != null) {
            removeOldKeys(parameters, APP_NAME, SofaRegistryConstants.SELF_APP_NAME);
            providerInfo.setStaticAttr(ATTR_APP_NAME, appNameStr);
        }
        // connections
        String connections = getValue(parameters, ATTR_CONNECTIONS, SofaRegistryConstants.CONNECTI_NUM);
        if (connections != null) {
            removeOldKeys(parameters, SofaRegistryConstants.CONNECTI_NUM);
            providerInfo.setStaticAttr(ATTR_CONNECTIONS, connections);
        }

        //rpc version
        String rpcVersion = getValue(parameters, ATTR_RPC_VERSION);
        providerInfo.setRpcVersion(CommonUtils.parseInt(rpcVersion, providerInfo.getRpcVersion()));

        // weight
        String weightStr = getValue(parameters, ATTR_WEIGHT, WEIGHT_KEY);
        if (weightStr != null) {
            removeOldKeys(parameters, ATTR_WEIGHT, WEIGHT_KEY);
            try {
                int weight = Integer.parseInt(weightStr);
                providerInfo.setWeight(weight);
                providerInfo.setStaticAttr(ATTR_WEIGHT, weightStr);
            } catch (Exception e) {
                LOGGER.error("weight is invalid : {}", originUrl);
            }
        }
        // warmupTime
        String warmupTimeStr = getValue(parameters, ATTR_WARMUP_TIME, SofaRegistryConstants.WARMUP_TIME_KEY);
        int warmupTime = 0;
        if (warmupTimeStr != null) {
            removeOldKeys(parameters, ATTR_WARMUP_TIME, SofaRegistryConstants.WARMUP_TIME_KEY);
            try {
                warmupTime = Integer.parseInt(warmupTimeStr);
                providerInfo.setStaticAttr(ATTR_WARMUP_TIME, warmupTimeStr);
            } catch (Exception e) {
                LOGGER.error("warmupTime is invalid : {}", originUrl);
            }
        }
        // warmupWeight
        String warmupWeightStr = getValue(parameters, ATTR_WARMUP_WEIGHT,
            SofaRegistryConstants.WARMUP_WEIGHT_KEY);
        int warmupWeight = 0;
        if (warmupWeightStr != null) {
            removeOldKeys(parameters, ATTR_WARMUP_WEIGHT, SofaRegistryConstants.WARMUP_WEIGHT_KEY);
            try {
                warmupWeight = Integer.parseInt(warmupWeightStr);
                providerInfo.setStaticAttr(ATTR_WARMUP_WEIGHT, warmupWeightStr);
            } catch (Exception e) {
                LOGGER.error("warmupWeight is invalid : {}", originUrl);
            }
        }
        // startTime
        String startTimeStr = getValue(parameters, ATTR_START_TIME);
        long startTime = 0L;
        if (startTimeStr != null) {
            try {
                startTime = Long.parseLong(startTimeStr);
            } catch (Exception e) {
                LOGGER.error("startTime is invalid : {}", originUrl);
            }
        }
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
        // 设置预热状�?
        if (StringUtils.isNotBlank(warmupTimeStr) && StringUtils.isNotBlank(warmupWeightStr)) {
            if (warmupTime > 0) {
                providerInfo.setStatus(ProviderStatus.WARMING_UP);
                providerInfo.setDynamicAttr(ATTR_WARMUP_WEIGHT, warmupWeight);
                providerInfo.setDynamicAttr(ATTR_WARM_UP_END_TIME, startTime + warmupTime);
            }
        }
        // 解�?hostMachineName
        String hostMachineName = getValue(parameters, HOST_MACHINE_KEY);
        if (StringUtils.isNotBlank(hostMachineName)) {
            providerInfo.setDynamicAttr(ATTR_HOST_MACHINE, hostMachineName);
        }

        // 解�?方法�?�数
        List<String> methodKeys = new ArrayList<String>();
        Map<String, Object> methodParameters = new HashMap<String, Object>();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getKey().startsWith("[") && entry.getKey().endsWith("]") && entry.getValue().startsWith("[") &&
                entry.getValue().endsWith("]")) { // 认为是方法�?置
                String key = entry.getKey();
                methodKeys.add(key);
                String methodName = key.substring(1, key.length() - 1);
                parseMethodInfo(methodParameters, methodName, entry.getValue());
            }
        }
        for (String methodKey : methodKeys) {
            parameters.remove(methodKey);
        }

        providerInfo.getStaticAttrs().putAll(parameters);
        providerInfo.getDynamicAttrs().putAll(methodParameters);

        providerInfo.setStaticAttr(ProviderInfoAttrs.ATTR_SOURCE, "sofa");
        return providerInfo;
    }

    /**
     * 解�?方法级
     *
     * @param methodParameters 存储的方法列表
     * @param method           方法�??称
     * @param valueStr         �?解�?的值 [xxxx]=[_AUTORECONNECT#false@_TIMEOUT#2000]
     */
    static void parseMethodInfo(Map<String, Object> methodParameters, String method, String valueStr) {
        int idxSplit = valueStr.indexOf('#'); // "#"被认为是MethodSpecial�?置的标志
        if (idxSplit < 0) {
            return;
        }

        int idxLeft = valueStr.indexOf('[');
        int idxRight = valueStr.indexOf(']');

        String parameters = valueStr.substring(idxLeft + 1, idxRight);
        String[] kvs = parameters.split("@");
        if (kvs.length > 0) {
            Map<String, String> tmp = new HashMap<String, String>();
            for (String kvp : kvs) {
                String[] kv = kvp.split("#");
                if (kv.length == 2) {
                    tmp.put(kv[0], kv[1]);
                }
            }
            // timeout特殊处�?�
            String timeout = getValue(tmp, ATTR_TIMEOUT, KEY_TIMEOUT,
                TIMEOUT);
            if (timeout != null) {
                removeOldKeys(tmp, ATTR_TIMEOUT, KEY_TIMEOUT, TIMEOUT);
                try {
                    methodParameters.put("." + method + "." + ATTR_TIMEOUT,
                        Integer.parseInt(timeout));
                } catch (Exception e) {
                    LOGGER.error("method timeout is invalid : {}", timeout);
                }
            }
            // 其它就存起�?�
            for (Map.Entry<String, String> entry : tmp.entrySet()) {
                methodParameters.put("." + method + "." + entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 根�?�多个获�?�属性值，知�?�获�?�到为止
     *
     * @param map  原始map
     * @param keys 多个key
     * @return 属性值
     */
    static String getValue(Map<String, String> map, String... keys) {
        if (CommonUtils.isEmpty(map)) {
            return null;
        }
        for (String key : keys) {
            String val = map.get(key);
            if (val != null) {
                return val;
            }
        }
        return null;
    }

    /**
     * 根�?�多个key删除属性值，全部删掉
     *
     * @param map  原始map
     * @param keys 多个key
     */
    static void removeOldKeys(Map<String, String> map, String... keys) {
        if (CommonUtils.isEmpty(map)) {
            return;
        }
        for (String key : keys) {
            map.remove(key);
        }
    }
}
