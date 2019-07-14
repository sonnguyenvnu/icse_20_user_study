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

package io.elasticjob.lite.internal.sharding;

import io.elasticjob.lite.config.LiteJobConfiguration;
import io.elasticjob.lite.executor.ShardingContexts;
import io.elasticjob.lite.internal.config.ConfigurationService;
import io.elasticjob.lite.internal.schedule.JobRegistry;
import io.elasticjob.lite.internal.storage.JobNodeStorage;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 执行作业的�?务.
 * 
 * @author zhangliang
 * @author caohao
 */
public final class ExecutionService {
    
    private final String jobName;
    
    private final JobNodeStorage jobNodeStorage;
    
    private final ConfigurationService configService;
    
    public ExecutionService(final CoordinatorRegistryCenter regCenter, final String jobName) {
        this.jobName = jobName;
        jobNodeStorage = new JobNodeStorage(regCenter, jobName);
        configService = new ConfigurationService(regCenter, jobName);
    }
        
    /**
     * 注册作业�?�动信�?�.
     * 
     * @param shardingContexts 分片上下文
     */
    public void registerJobBegin(final ShardingContexts shardingContexts) {
        JobRegistry.getInstance().setJobRunning(jobName, true);
        if (!configService.load(true).isMonitorExecution()) {
            return;
        }
        for (int each : shardingContexts.getShardingItemParameters().keySet()) {
            jobNodeStorage.fillEphemeralJobNode(ShardingNode.getRunningNode(each), "");
        }
    }
    
    /**
     * 注册作业完�?信�?�.
     * 
     * @param shardingContexts 分片上下文
     */
    public void registerJobCompleted(final ShardingContexts shardingContexts) {
        JobRegistry.getInstance().setJobRunning(jobName, false);
        if (!configService.load(true).isMonitorExecution()) {
            return;
        }
        for (int each : shardingContexts.getShardingItemParameters().keySet()) {
            jobNodeStorage.removeJobNodeIfExisted(ShardingNode.getRunningNode(each));
        }
    }
    
    /**
     * 清除全部分片的�?行状�?.
     */
    public void clearAllRunningInfo() {
        clearRunningInfo(getAllItems());
    }
    
    /**
     * 清除分�?分片项的�?行状�?.
     * 
     * @param items 需�?清�?�的分片项列表
     */
    public void clearRunningInfo(final List<Integer> items) {
        for (int each : items) {
            jobNodeStorage.removeJobNodeIfExisted(ShardingNode.getRunningNode(each));
        }
    }
    
    /**
     * 判断分片项中是�?�还有执行中的作业.
     *
     * @param items 需�?判断的分片项列表
     * @return 分片项中是�?�还有执行中的作业
     */
    public boolean hasRunningItems(final Collection<Integer> items) {
        LiteJobConfiguration jobConfig = configService.load(true);
        if (null == jobConfig || !jobConfig.isMonitorExecution()) {
            return false;
        }
        for (int each : items) {
            if (jobNodeStorage.isJobNodeExisted(ShardingNode.getRunningNode(each))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断是�?�还有执行中的作业.
     *
     * @return 是�?�还有执行中的作业
     */
    public boolean hasRunningItems() {
        return hasRunningItems(getAllItems());
    }
    
    private List<Integer> getAllItems() {
        int shardingTotalCount = configService.load(true).getTypeConfig().getCoreConfig().getShardingTotalCount();
        List<Integer> result = new ArrayList<>(shardingTotalCount);
        for (int i = 0; i < shardingTotalCount; i++) {
            result.add(i);
        }
        return result;
    }
    
    /**
     * 如果当�?分片项�?在�?行则设置任务被错过执行的标记.
     * 
     * @param items 需�?设置错过执行的任务分片项
     * @return 是�?�错过本次执行
     */
    public boolean misfireIfHasRunningItems(final Collection<Integer> items) {
        if (!hasRunningItems(items)) {
            return false;
        }
        setMisfire(items);
        return true;
    }
    
    /**
     * 设置任务被错过执行的标记.
     *
     * @param items 需�?设置错过执行的任务分片项
     */
    public void setMisfire(final Collection<Integer> items) {
        for (int each : items) {
            jobNodeStorage.createJobNodeIfNeeded(ShardingNode.getMisfireNode(each));
        }
    }
    
    /**
     * 获�?�标记被错过执行的任务分片项.
     * 
     * @param items 需�?获�?�标记被错过执行的任务分片项
     * @return 标记被错过执行的任务分片项
     */
    public List<Integer> getMisfiredJobItems(final Collection<Integer> items) {
        List<Integer> result = new ArrayList<>(items.size());
        for (int each : items) {
            if (jobNodeStorage.isJobNodeExisted(ShardingNode.getMisfireNode(each))) {
                result.add(each);
            }
        }
        return result;
    }
    
    /**
     * 清除任务被错过执行的标记.
     * 
     * @param items 需�?清除错过执行的任务分片项
     */
    public void clearMisfire(final Collection<Integer> items) {
        for (int each : items) {
            jobNodeStorage.removeJobNodeIfExisted(ShardingNode.getMisfireNode(each));
        }
    }
    
    /**
     * 获�?��?用的任务分片项.
     *
     * @param items 需�?获�?��?用的任务分片项
     * @return �?用的任务分片项
     */
    public List<Integer> getDisabledItems(final List<Integer> items) {
        List<Integer> result = new ArrayList<>(items.size());
        for (int each : items) {
            if (jobNodeStorage.isJobNodeExisted(ShardingNode.getDisabledNode(each))) {
                result.add(each);
            }
        }
        return result;
    }
}
