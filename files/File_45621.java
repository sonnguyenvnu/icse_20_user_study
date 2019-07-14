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
package com.alipay.sofa.rpc.filter;

import com.alipay.sofa.rpc.bootstrap.ConsumerBootstrap;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.client.ProviderInfoAttrs;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.context.RpcInternalContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;

/**
 * <p>æ‰§è¡ŒçœŸæ­£çš„è°ƒç”¨è¿‡ç¨‹ï¼Œä½¿ç”¨clientå?‘é€?æ•°æ?®ç»™server</p>
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class ConsumerInvoker extends FilterInvoker {

    /**
     * The Client.
     */
    private ConsumerBootstrap consumerBootstrap;

    /**
     * Instantiates a new Consumer invoke filter.
     *
     * @param consumerBootstrap æœ?åŠ¡å™¨å?¯åŠ¨ç?€é…?ç½®
     */
    public ConsumerInvoker(ConsumerBootstrap consumerBootstrap) {
        super(consumerBootstrap.getConsumerConfig());
        this.consumerBootstrap = consumerBootstrap;
    }

    @Override
    public SofaResponse invoke(SofaRequest sofaRequest) throws SofaRpcException {
        // è®¾ç½®ä¸‹æœ?åŠ¡å™¨åº”ç”¨
        ProviderInfo providerInfo = RpcInternalContext.getContext().getProviderInfo();
        String appName = providerInfo.getStaticAttr(ProviderInfoAttrs.ATTR_APP_NAME);
        if (StringUtils.isNotEmpty(appName)) {
            sofaRequest.setTargetAppName(appName);
        }

        // ç›®å‰?å?ªæ˜¯é€šè¿‡clientå?‘é€?ç»™æœ?åŠ¡ç«¯
        return consumerBootstrap.getCluster().sendMsg(providerInfo, sofaRequest);
    }

}
