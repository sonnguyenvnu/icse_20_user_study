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

package io.elasticjob.lite.internal.guarantee;

import io.elasticjob.lite.internal.config.ConfigurationService;
import io.elasticjob.lite.internal.storage.JobNodeStorage;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;

import java.util.Collection;

/**
 * �?�?分布�?任务全部开始和结�?�状�?的�?务.
 * 
 * @author zhangliang
 */
public final class GuaranteeService {
    
    private final JobNodeStorage jobNodeStorage;
    
    private final ConfigurationService configService;
    
    public GuaranteeService(final CoordinatorRegistryCenter regCenter, final String jobName) {
        jobNodeStorage = new JobNodeStorage(regCenter, jobName);
        configService = new ConfigurationService(regCenter, jobName);
    }
    
    /**
     * 根�?�分片项注册任务开始�?行.
     * 
     * @param shardingItems 待注册的分片项
     */
    public void registerStart(final Collection<Integer> shardingItems) {
        for (int each : shardingItems) {
            jobNodeStorage.createJobNodeIfNeeded(GuaranteeNode.getStartedNode(each));
        }
    }
    
    /**
     * 判断是�?�所有的任务�?��?�动完毕.
     *
     * @return 是�?�所有的任务�?��?�动完毕
     */
    public boolean isAllStarted() {
        return jobNodeStorage.isJobNodeExisted(GuaranteeNode.STARTED_ROOT)
                && configService.load(false).getTypeConfig().getCoreConfig().getShardingTotalCount() == jobNodeStorage.getJobNodeChildrenKeys(GuaranteeNode.STARTED_ROOT).size();
    }
    
    /**
     * 清�?�所有任务�?�动信�?�.
     */
    public void clearAllStartedInfo() {
        jobNodeStorage.removeJobNodeIfExisted(GuaranteeNode.STARTED_ROOT);
    }
    
    /**
     * 根�?�分片项注册任务完�?�?行.
     *
     * @param shardingItems 待注册的分片项
     */
    public void registerComplete(final Collection<Integer> shardingItems) {
        for (int each : shardingItems) {
            jobNodeStorage.createJobNodeIfNeeded(GuaranteeNode.getCompletedNode(each));
        }
    }
    
    /**
     * 判断是�?�所有的任务�?�执行完毕.
     *
     * @return 是�?�所有的任务�?�执行完毕
     */
    public boolean isAllCompleted() {
        return jobNodeStorage.isJobNodeExisted(GuaranteeNode.COMPLETED_ROOT)
                && configService.load(false).getTypeConfig().getCoreConfig().getShardingTotalCount() <= jobNodeStorage.getJobNodeChildrenKeys(GuaranteeNode.COMPLETED_ROOT).size();
    }
    
    /**
     * 清�?�所有任务�?�动信�?�.
     */
    public void clearAllCompletedInfo() {
        jobNodeStorage.removeJobNodeIfExisted(GuaranteeNode.COMPLETED_ROOT);
    }
}
