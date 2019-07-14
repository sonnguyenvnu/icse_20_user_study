/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.elasticjob.lite.lifecycle.internal.reg;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import io.elasticjob.lite.reg.zookeeper.ZookeeperConfiguration;
import io.elasticjob.lite.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心工厂.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegistryCenterFactory {
    
    private static final ConcurrentHashMap<HashCode, CoordinatorRegistryCenter> REG_CENTER_REGISTRY = new ConcurrentHashMap<>(); 
    
    /**
     * 创建注册中心.
     *
     * @param connectString 注册中心连接字符串
     * @param namespace 注册中心命�??空间
     * @param digest 注册中心凭�?
     * @return 注册中心对象
     */
    public static CoordinatorRegistryCenter createCoordinatorRegistryCenter(final String connectString, final String namespace, final Optional<String> digest) {
        Hasher hasher =  Hashing.md5().newHasher().putString(connectString, Charsets.UTF_8).putString(namespace, Charsets.UTF_8);
        if (digest.isPresent()) {
            hasher.putString(digest.get(), Charsets.UTF_8);
        }
        HashCode hashCode = hasher.hash();
        CoordinatorRegistryCenter result = REG_CENTER_REGISTRY.get(hashCode);
        if (null != result) {
            return result;
        }
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(connectString, namespace);
        if (digest.isPresent()) {
            zkConfig.setDigest(digest.get());
        }
        result = new ZookeeperRegistryCenter(zkConfig);
        result.init();
        REG_CENTER_REGISTRY.put(hashCode, result);
        return result;
    }
}
