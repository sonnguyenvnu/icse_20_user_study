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
import com.alipay.sofa.rpc.common.struct.PositiveAtomicCounter;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.ext.Extension;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * æŒ‰æ?ƒé‡?çš„è´Ÿè½½å?‡è¡¡è½®è¯¢ç®—æ³•ï¼ŒæŒ‰æ–¹æ³•çº§è¿›è¡Œè½®è¯¢ï¼Œæ€§èƒ½è¾ƒå·®ï¼Œä¸?æŽ¨è??<br>
 *  ä¾‹å¦‚ï¼šæ?ƒé‡?ä¸º1ã€?2ã€?3ã€?4ä¸‰ä¸ªèŠ‚ç‚¹ï¼Œé¡ºåº?ä¸º 1234234344
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
@Extension("weightRoundRobin")
@Deprecated
public class WeightRoundRobinLoadBalancer extends AbstractLoadBalancer {

    private final ConcurrentMap<String, PositiveAtomicCounter> sequences = new ConcurrentHashMap<String, PositiveAtomicCounter>();

    /**
     * æž„é€ å‡½æ•°
     *
     * @param consumerBootstrap æœ?åŠ¡æ¶ˆè´¹è€…é…?ç½®
     */
    public WeightRoundRobinLoadBalancer(ConsumerBootstrap consumerBootstrap) {
        super(consumerBootstrap);
    }

    @Override
    public ProviderInfo doSelect(SofaRequest request, List<ProviderInfo> providerInfos) {
        String key = getServiceKey(request); // æ¯?ä¸ªæ–¹æ³•çº§è‡ªå·±è½®è¯¢ï¼Œäº’ä¸?å½±å“?
        int length = providerInfos.size(); // æ€»ä¸ªæ•°
        int maxWeight = 0; // æœ€å¤§æ?ƒé‡?
        int minWeight = Integer.MAX_VALUE; // æœ€å°?æ?ƒé‡?

        final LinkedHashMap<ProviderInfo, IntegerWrapper> invokerToWeightMap = new LinkedHashMap<ProviderInfo, IntegerWrapper>();
        int weightSum = 0;
        for (ProviderInfo providerInfo : providerInfos) {
            int weight = getWeight(providerInfo);
            maxWeight = Math.max(maxWeight, weight); // ç´¯è®¡æœ€å¤§æ?ƒé‡?
            minWeight = Math.min(minWeight, weight); // ç´¯è®¡æœ€å°?æ?ƒé‡?
            if (weight > 0) {
                invokerToWeightMap.put(providerInfo, new IntegerWrapper(weight));
                weightSum += weight;
            }
        }
        PositiveAtomicCounter sequence = sequences.get(key);
        if (sequence == null) {
            sequences.putIfAbsent(key, new PositiveAtomicCounter());
            sequence = sequences.get(key);
        }
        int currentSequence = sequence.getAndIncrement();
        if (maxWeight > 0 && minWeight < maxWeight) { // æ?ƒé‡?ä¸?ä¸€æ ·
            int mod = currentSequence % weightSum;
            for (int i = 0; i < maxWeight; i++) {
                for (Map.Entry<ProviderInfo, IntegerWrapper> each : invokerToWeightMap.entrySet()) {
                    final ProviderInfo k = each.getKey();
                    final IntegerWrapper v = each.getValue();
                    if (mod == 0 && v.getValue() > 0) {
                        return k;
                    }
                    if (v.getValue() > 0) {
                        v.decrement();
                        mod--;
                    }
                }
            }
        }
        return providerInfos.get(currentSequence % length);
        /*
        for (int i = 0; i < length; i++) {
            int weight = getWeight(providerInfos.get(i));
            maxWeight = Math.max(maxWeight, weight); // ç´¯è®¡æœ€å¤§æ?ƒé‡?
            minWeight = Math.min(minWeight, weight); // ç´¯è®¡æœ€å°?æ?ƒé‡?
        }
        if (maxWeight > 0 && minWeight < maxWeight) { // æ?ƒé‡?ä¸?ä¸€æ ·,ä¸?å†?æŒ‰ç…§ä¹‹å‰?è½®è¯¢é¡ºåº?ï¼Œ
            PositiveAtomicCounter weightSequence = weightSequences.get(key);
            if (weightSequence == null) {
                weightSequences.putIfAbsent(key, new PositiveAtomicCounter());
                weightSequence = weightSequences.get(key);
            }
            int currentWeight = weightSequence.getAndIncrement() % maxWeight;
            List<ProviderInfo> weightInvokers = new ArrayList<ProviderInfo>();
            for (ProviderInfo invoker : providerInfos) { // ç­›é€‰æ?ƒé‡?å¤§äºŽå½“å‰?æ?ƒé‡?åŸºæ•°çš„provider,ä¿?è¯?æ?ƒé‡?å¤§çš„æœ?åŠ¡å“ªæ€•æ˜¯è½®è¯¢ï¼Œè¢«è°ƒç”¨çš„æœºä¼šä¹Ÿæ˜¯æœ€å¤šçš„
                if (getWeight(invoker) > currentWeight) {
                    weightInvokers.add(invoker);
                }
            }
            int weightLength = weightInvokers.size();
            if (weightLength == 1) {
                return weightInvokers.get(0);
            } else if (weightLength > 1) {
                providerInfos = weightInvokers;
                length = providerInfos.size();
            }
        }
        PositiveAtomicCounter sequence = sequences.get(key);
        if (sequence == null) {
            sequences.putIfAbsent(key, new PositiveAtomicCounter());
            sequence = sequences.get(key);
        }
        return providerInfos.get(sequence.getAndIncrement() % length);*/
    }

    private String getServiceKey(SofaRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getTargetAppName()).append("#")
            .append(request.getMethodName());
        return builder.toString();
    }

    private static final class IntegerWrapper {
        public IntegerWrapper(int value) {
            this.value = value;
        }

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void decrement() {
            this.value--;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

}
