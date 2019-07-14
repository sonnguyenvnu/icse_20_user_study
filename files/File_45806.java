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
package com.alipay.sofa.rpc.client.lb;

import com.alipay.sofa.rpc.bootstrap.ConsumerBootstrap;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.common.SystemInfo;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;

import java.util.ArrayList;
import java.util.List;

/**
 * 本机优先的�?机算法
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
@Extension("localPref")
public class LocalPreferenceLoadBalancer extends RandomLoadBalancer {

    /**
     * 构造函数
     *
     * @param consumerBootstrap �?务消费者�?置
     */
    public LocalPreferenceLoadBalancer(ConsumerBootstrap consumerBootstrap) {
        super(consumerBootstrap);
    }

    @Override
    public ProviderInfo doSelect(SofaRequest invocation, List<ProviderInfo> providerInfos) {
        String localhost = SystemInfo.getLocalHost();
        if (StringUtils.isEmpty(localhost)) {
            return super.doSelect(invocation, providerInfos);
        }
        List<ProviderInfo> localProviderInfo = new ArrayList<ProviderInfo>();
        for (ProviderInfo providerInfo : providerInfos) { // 解�?IP，看是�?�和本地一致
            if (localhost.equals(providerInfo.getHost())) {
                localProviderInfo.add(providerInfo);
            }
        }
        if (CommonUtils.isNotEmpty(localProviderInfo)) { // 命中本机的�?务端
            return super.doSelect(invocation, localProviderInfo);
        } else { // 没有命中本机上的�?务端
            return super.doSelect(invocation, providerInfos);
        }
    }
}
