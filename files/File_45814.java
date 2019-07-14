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
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;

import java.util.List;

/**
 * 直连路由，优先级最高，直连的情况下，就�?走注册中心了
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 * @since 5.2.0
 */
@Extension(value = "directUrl", order = -20000)
@AutoActive(consumerSide = true)
public class DirectUrlRouter extends Router {

    /**
     * 请求路径：直连
     */
    public static final String  RPC_DIRECT_URL_ROUTER = "DIRECT";

    /**
     * �?务消费者�?置
     */
    protected ConsumerBootstrap consumerBootstrap;

    /**
     * �?始化
     *
     * @param consumerBootstrap �?务消费者�?置
     */
    @Override
    public void init(ConsumerBootstrap consumerBootstrap) {
        this.consumerBootstrap = consumerBootstrap;
    }

    /**
     * 是�?�自动加载
     *
     * @param consumerBootstrap 调用对象
     * @return 是�?�加载本过滤器
     */
    @Override
    public boolean needToLoad(ConsumerBootstrap consumerBootstrap) {
        return StringUtils.isNotEmpty(consumerBootstrap.getConsumerConfig().getDirectUrl());
    }

    @Override
    public List<ProviderInfo> route(SofaRequest request, List<ProviderInfo> providerInfos) {
        AddressHolder addressHolder = consumerBootstrap.getCluster().getAddressHolder();
        if (addressHolder != null) {
            List<ProviderInfo> current = addressHolder.getProviderInfos(RpcConstants.ADDRESS_DIRECT_GROUP);
            if (providerInfos != null) {
                providerInfos.addAll(current);
            } else {
                providerInfos = current;
            }
        }
        recordRouterWay(RPC_DIRECT_URL_ROUTER);
        return providerInfos;
    }
}
