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
package com.alipay.sofa.rpc.bootstrap;

import com.alipay.sofa.rpc.common.RpcConfigs;
import com.alipay.sofa.rpc.common.RpcOptions;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.ext.ExtensionClass;
import com.alipay.sofa.rpc.ext.ExtensionLoader;
import com.alipay.sofa.rpc.ext.ExtensionLoaderFactory;

/**
 * 辅助工具类，更方便的使用�?�布方法
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class Bootstraps {

    /**
     * �?�布一个�?务
     *
     * @param providerConfig �?务�?�布者�?置
     * @param <T>            接�?�类型
     * @return �?�布�?�动类
     */
    public static <T> ProviderBootstrap<T> from(ProviderConfig<T> providerConfig) {
        String bootstrap = providerConfig.getBootstrap();
        if (StringUtils.isEmpty(bootstrap)) {
            // Use default provider bootstrap
            bootstrap = RpcConfigs.getStringValue(RpcOptions.DEFAULT_PROVIDER_BOOTSTRAP);
            providerConfig.setBootstrap(bootstrap);
        }
        ProviderBootstrap providerBootstrap = ExtensionLoaderFactory.getExtensionLoader(ProviderBootstrap.class)
            .getExtension(bootstrap, new Class[] { ProviderConfig.class }, new Object[] { providerConfig });
        return (ProviderBootstrap<T>) providerBootstrap;
    }

    /**
     * 引用一个�?务
     *
     * @param consumerConfig �?务消费者�?置
     * @param <T>            接�?�类型
     * @return 引用�?�动类
     */
    public static <T> ConsumerBootstrap<T> from(ConsumerConfig<T> consumerConfig) {
        String bootstrap = consumerConfig.getBootstrap();
        ConsumerBootstrap consumerBootstrap;
        if (StringUtils.isNotEmpty(bootstrap)) {
            consumerBootstrap = ExtensionLoaderFactory.getExtensionLoader(ConsumerBootstrap.class)
                .getExtension(bootstrap,
                    new Class[] { ConsumerConfig.class },
                    new Object[] { consumerConfig });
        } else {
            // default is same with protocol
            bootstrap = consumerConfig.getProtocol();
            ExtensionLoader extensionLoader = ExtensionLoaderFactory.getExtensionLoader(ConsumerBootstrap.class);
            ExtensionClass<ConsumerBootstrap> extensionClass = extensionLoader.getExtensionClass(bootstrap);
            if (extensionClass == null) {
                // if not exist, use default consumer bootstrap
                bootstrap = RpcConfigs.getStringValue(RpcOptions.DEFAULT_CONSUMER_BOOTSTRAP);
                consumerConfig.setBootstrap(bootstrap);
                consumerBootstrap = ExtensionLoaderFactory.getExtensionLoader(ConsumerBootstrap.class)
                    .getExtension(bootstrap, new Class[] { ConsumerConfig.class }, new Object[] { consumerConfig });
            } else {
                consumerConfig.setBootstrap(bootstrap);
                consumerBootstrap = extensionClass.getExtInstance(
                    new Class[] { ConsumerConfig.class }, new Object[] { consumerConfig });
            }
        }
        return (ConsumerBootstrap<T>) consumerBootstrap;
    }
}
