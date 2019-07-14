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
package com.alipay.sofa.rpc.client.router;

import com.alipay.sofa.rpc.bootstrap.ConsumerBootstrap;
import com.alipay.sofa.rpc.client.AddressHolder;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.client.Router;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;

import java.util.List;

/**
 * 从mesh获�?�路由,�?�为 bolt 开�?�。
 * <p>
 *
 * @author <a href="mailto:zhiyuan.lzy@antfin.com">zhiyuan.lzy</a>
 */
@Extension(value = "mesh", order = -19000)
@AutoActive(consumerSide = true)
public class MeshRouter extends Router {

    /**
     * 路由路径：注册中心
     *
     * @since 5.5.0
     */
    public static final String  RPC_MESH_ROUTER = "MESH";

    /**
     * �?务消费者�?置
     */
    protected ConsumerBootstrap consumerBootstrap;

    @Override
    public void init(ConsumerBootstrap consumerBootstrap) {
        this.consumerBootstrap = consumerBootstrap;
    }

    @Override
    public boolean needToLoad(ConsumerBootstrap consumerBootstrap) {
        ConsumerConfig consumerConfig = consumerBootstrap.getConsumerConfig();
        // �?是直连，且从注册中心订阅�?置
        final boolean isDirect = StringUtils.isNotBlank(consumerConfig.getDirectUrl());
        final List<RegistryConfig> registrys = consumerConfig.getRegistry();
        boolean isMesh = false;

        if (registrys != null) {
            for (RegistryConfig registry : registrys) {
                if (registry.getProtocol().equalsIgnoreCase(RpcConstants.REGISTRY_PROTOCOL_MESH)) {
                    isMesh = true;
                    break;
                }
            }
        }

        boolean isBolt = consumerConfig.getProtocol().equalsIgnoreCase(RpcConstants.PROTOCOL_TYPE_BOLT);

        return !isDirect && isMesh && isBolt;
    }

    @Override
    public List<ProviderInfo> route(SofaRequest request, List<ProviderInfo> providerInfos) {
        AddressHolder addressHolder = consumerBootstrap.getCluster().getAddressHolder();
        if (addressHolder != null) {
            List<ProviderInfo> current = addressHolder.getProviderInfos(RpcConstants.ADDRESS_DEFAULT_GROUP);
            if (providerInfos != null) {
                providerInfos.addAll(current);
            } else {
                providerInfos = current;
            }
        }
        recordRouterWay(RPC_MESH_ROUTER);
        return providerInfos;
    }

}
