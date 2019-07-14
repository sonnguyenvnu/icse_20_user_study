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

package io.elasticjob.lite.internal.config;

import com.google.common.base.Optional;
import io.elasticjob.lite.config.LiteJobConfiguration;
import io.elasticjob.lite.exception.JobConfigurationException;
import io.elasticjob.lite.exception.JobExecutionEnvironmentException;
import io.elasticjob.lite.internal.storage.JobNodeStorage;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import io.elasticjob.lite.util.env.TimeService;

/**
 * 弹性化分布�?作业�?置�?务.
 * 
 * @author zhangliang
 * @author caohao
 */
public final class ConfigurationService {
    
    private final TimeService timeService;
    
    private final JobNodeStorage jobNodeStorage;
    
    public ConfigurationService(final CoordinatorRegistryCenter regCenter, final String jobName) {
        jobNodeStorage = new JobNodeStorage(regCenter, jobName);
        timeService = new TimeService();
    }
    
    /**
     * 读�?�作业�?置.
     * 
     * @param fromCache 是�?�从缓存中读�?�
     * @return 作业�?置
     */
    public LiteJobConfiguration load(final boolean fromCache) {
        String result;
        if (fromCache) {
            result = jobNodeStorage.getJobNodeData(ConfigurationNode.ROOT);
            if (null == result) {
                result = jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT);
            }
        } else {
            result = jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT);
        }
        return LiteJobConfigurationGsonFactory.fromJson(result);
    }
    
    /**
     * �?久化分布�?作业�?置信�?�.
     * 
     * @param liteJobConfig 作业�?置
     */
    public void persist(final LiteJobConfiguration liteJobConfig) {
        checkConflictJob(liteJobConfig);
        if (!jobNodeStorage.isJobNodeExisted(ConfigurationNode.ROOT) || liteJobConfig.isOverwrite()) {
            jobNodeStorage.replaceJobNode(ConfigurationNode.ROOT, LiteJobConfigurationGsonFactory.toJson(liteJobConfig));
        }
    }
    
    private void checkConflictJob(final LiteJobConfiguration liteJobConfig) {
        Optional<LiteJobConfiguration> liteJobConfigFromZk = find();
        if (liteJobConfigFromZk.isPresent() && !liteJobConfigFromZk.get().getTypeConfig().getJobClass().equals(liteJobConfig.getTypeConfig().getJobClass())) {
            throw new JobConfigurationException("Job conflict with register center. The job '%s' in register center's class is '%s', your job class is '%s'", 
                    liteJobConfig.getJobName(), liteJobConfigFromZk.get().getTypeConfig().getJobClass(), liteJobConfig.getTypeConfig().getJobClass());
        }
    }
    
    private Optional<LiteJobConfiguration> find() {
        if (!jobNodeStorage.isJobNodeExisted(ConfigurationNode.ROOT)) {
            return Optional.absent();
        }
        LiteJobConfiguration result = LiteJobConfigurationGsonFactory.fromJson(jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT));
        if (null == result) {
            // TODO 应该删除整个job node,并�?�仅仅删除config node
            jobNodeStorage.removeJobNodeIfExisted(ConfigurationNode.ROOT);
        }
        return Optional.fromNullable(result);
    }
    
    /**
     * 检查本机与注册中心的时间误差秒数是�?�在�?许范围.
     * 
     * @throws JobExecutionEnvironmentException 本机与注册中心的时间误差秒数�?在�?许范围所抛出的异常
     */
    public void checkMaxTimeDiffSecondsTolerable() throws JobExecutionEnvironmentException {
        int maxTimeDiffSeconds =  load(true).getMaxTimeDiffSeconds();
        if (-1  == maxTimeDiffSeconds) {
            return;
        }
        long timeDiff = Math.abs(timeService.getCurrentMillis() - jobNodeStorage.getRegistryCenterTime());
        if (timeDiff > maxTimeDiffSeconds * 1000L) {
            throw new JobExecutionEnvironmentException(
                    "Time different between job server and register center exceed '%s' seconds, max time different is '%s' seconds.", timeDiff / 1000, maxTimeDiffSeconds);
        }
    }
}
