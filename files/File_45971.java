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
package com.alipay.sofa.rpc.config;

import com.alipay.sofa.rpc.common.utils.StringUtils;

/**
 * 统一的�?置�??称生�?器
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public class ConfigUniqueNameGenerator {

    /**
     * 得到�?务唯一�??称，无需兼容之�?的版本
     * 
     * @param interfaceConfig �?务�??供者或者�?务消费者�?置
     * @return �?务唯一�??称
     * @since 5.4.0
     */
    public static String getServiceName(AbstractInterfaceConfig interfaceConfig) {
        String uniqueId = interfaceConfig.getUniqueId();
        return interfaceConfig.getInterfaceId() + (StringUtils.isEmpty(uniqueId) ? "" : ":" + uniqueId);
    }

    /**
     * 唯一标识UniqueName的产生方法，主�?用于内部找接�?�等，格�?为interface:version[:uniqueId]
     *
     * @param interfaceConfig �?务�??供者或者�?务消费者�?置
     * @return �?置唯一�??字
     */
    public static String getUniqueName(AbstractInterfaceConfig interfaceConfig) {
        // 加上 1.0 是为了兼容之�?的版本
        String version = interfaceConfig.getVersion();
        String uniqueId = interfaceConfig.getUniqueId();
        return interfaceConfig.getInterfaceId()
            + (StringUtils.isEmpty(version) ? ":1.0" : ":" + version)
            + (StringUtils.isEmpty(uniqueId) ? "" : ":" + uniqueId);
    }

    /**
     * 唯一标识UniqueName的产生方法，主�?用于外部�?务�?�现等，格�?为interface:version[:uniqueId]@protocol
     *
     * @param providerConfig �?务端�??供者�?置
     * @param protocol       �??议
     * @return �?置唯一�??字
     */
    public static String getUniqueNameProtocol(ProviderConfig providerConfig, String protocol) {
        if (StringUtils.isNotEmpty(protocol)) {
            return getUniqueName(providerConfig) + "@" + protocol;
        } else {
            return getUniqueName(providerConfig);
        }
    }

    /**
     * 解�?唯一标识UniqueName得到接�?��??
     *
     * @param uniqueName �?务唯一标识
     * @return 接�?��??
     */
    public static String getInterfaceName(String uniqueName) {
        if (StringUtils.isEmpty(uniqueName)) {
            return uniqueName;
        }
        int index = uniqueName.indexOf(':');
        return index < 0 ? uniqueName : uniqueName.substring(0, index);
    }

    /**
     * 唯一标识UniqueName的产生方法，主�?用于外部�?务�?�现等
     *
     * @param consumerConfig �?务端调用者�?置
     * @return �?置唯一�??字
     */
    public static String getUniqueNameProtocol(ConsumerConfig consumerConfig) {
        return getUniqueName(consumerConfig) + "@" + consumerConfig.getProtocol();
    }
}
