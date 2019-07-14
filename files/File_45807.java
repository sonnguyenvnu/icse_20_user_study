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
import com.alipay.sofa.rpc.client.AbstractLoadBalancer;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;

import java.util.List;
import java.util.Random;

/**
 * è´Ÿè½½å?‡è¡¡éš?æœºç®—æ³•:å…¨éƒ¨åˆ—è¡¨æŒ‰æ?ƒé‡?éš?æœºé€‰æ‹©
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
@Extension("random")
public class RandomLoadBalancer extends AbstractLoadBalancer {

    /**
     * éš?æœº
     */
    private final Random random = new Random();

    /**
     * æž„é€ å‡½æ•°
     *
     * @param consumerBootstrap æœ?åŠ¡æ¶ˆè´¹è€…é…?ç½®
     */
    public RandomLoadBalancer(ConsumerBootstrap consumerBootstrap) {
        super(consumerBootstrap);
    }

    @Override
    public ProviderInfo doSelect(SofaRequest invocation, List<ProviderInfo> providerInfos) {
        ProviderInfo providerInfo = null;
        int size = providerInfos.size(); // æ€»ä¸ªæ•°
        int totalWeight = 0; // æ€»æ?ƒé‡?
        boolean isWeightSame = true; // æ?ƒé‡?æ˜¯å?¦éƒ½ä¸€æ ·
        for (int i = 0; i < size; i++) {
            int weight = getWeight(providerInfos.get(i));
            totalWeight += weight; // ç´¯è®¡æ€»æ?ƒé‡?
            if (isWeightSame && i > 0 && weight != getWeight(providerInfos.get(i - 1))) {
                isWeightSame = false; // è®¡ç®—æ‰€æœ‰æ?ƒé‡?æ˜¯å?¦ä¸€æ ·
            }
        }
        if (totalWeight > 0 && !isWeightSame) {
            // å¦‚æžœæ?ƒé‡?ä¸?ç›¸å?Œä¸”æ?ƒé‡?å¤§äºŽ0åˆ™æŒ‰æ€»æ?ƒé‡?æ•°éš?æœº
            int offset = random.nextInt(totalWeight);
            // å¹¶ç¡®å®šéš?æœºå€¼è?½åœ¨å“ªä¸ªç‰‡æ–­ä¸Š
            for (int i = 0; i < size; i++) {
                offset -= getWeight(providerInfos.get(i));
                if (offset < 0) {
                    providerInfo = providerInfos.get(i);
                    break;
                }
            }
        } else {
            // å¦‚æžœæ?ƒé‡?ç›¸å?Œæˆ–æ?ƒé‡?ä¸º0åˆ™å?‡ç­‰éš?æœº
            providerInfo = providerInfos.get(random.nextInt(size));
        }
        return providerInfo;
    }
}
