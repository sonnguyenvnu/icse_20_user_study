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

package io.elasticjob.lite.internal.schedule;

import io.elasticjob.lite.api.listener.ElasticJobListener;
import io.elasticjob.lite.config.LiteJobConfiguration;
import io.elasticjob.lite.internal.config.ConfigurationService;
import io.elasticjob.lite.internal.election.LeaderService;
import io.elasticjob.lite.internal.instance.InstanceService;
import io.elasticjob.lite.internal.listener.ListenerManager;
import io.elasticjob.lite.internal.monitor.MonitorService;
import io.elasticjob.lite.internal.reconcile.ReconcileService;
import io.elasticjob.lite.internal.server.ServerService;
import io.elasticjob.lite.internal.sharding.ExecutionService;
import io.elasticjob.lite.internal.sharding.ShardingService;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;

import java.util.List;

/**
 * ä¸ºè°ƒåº¦å™¨æ??ä¾›å†…éƒ¨æœ?åŠ¡çš„é—¨é?¢ç±».
 * 
 * @author zhangliang
 */
public final class SchedulerFacade {
    
    private final String jobName;
    
    private final ConfigurationService configService;
    
    private final LeaderService leaderService;
    
    private final ServerService serverService;
    
    private final InstanceService instanceService;
    
    private final ShardingService shardingService;
    
    private final ExecutionService executionService;
    
    private final MonitorService monitorService;
    
    private final ReconcileService reconcileService;
    
    private ListenerManager listenerManager;
    
    public SchedulerFacade(final CoordinatorRegistryCenter regCenter, final String jobName) {
        this.jobName = jobName;
        configService = new ConfigurationService(regCenter, jobName);
        leaderService = new LeaderService(regCenter, jobName);
        serverService = new ServerService(regCenter, jobName);
        instanceService = new InstanceService(regCenter, jobName);
        shardingService = new ShardingService(regCenter, jobName);
        executionService = new ExecutionService(regCenter, jobName);
        monitorService = new MonitorService(regCenter, jobName);
        reconcileService = new ReconcileService(regCenter, jobName);
    }
    
    public SchedulerFacade(final CoordinatorRegistryCenter regCenter, final String jobName, final List<ElasticJobListener> elasticJobListeners) {
        this.jobName = jobName;
        configService = new ConfigurationService(regCenter, jobName);
        leaderService = new LeaderService(regCenter, jobName);
        serverService = new ServerService(regCenter, jobName);
        instanceService = new InstanceService(regCenter, jobName);
        shardingService = new ShardingService(regCenter, jobName);
        executionService = new ExecutionService(regCenter, jobName);
        monitorService = new MonitorService(regCenter, jobName);
        reconcileService = new ReconcileService(regCenter, jobName);
        listenerManager = new ListenerManager(regCenter, jobName, elasticJobListeners);
    }
    
    /**
     * èŽ·å?–ä½œä¸šè§¦å?‘ç›‘å?¬å™¨.
     *
     * @return ä½œä¸šè§¦å?‘ç›‘å?¬å™¨
     */
    public JobTriggerListener newJobTriggerListener() {
        return new JobTriggerListener(executionService, shardingService);
    }
    
    /**
     * æ›´æ–°ä½œä¸šé…?ç½®.
     *
     * @param liteJobConfig ä½œä¸šé…?ç½®
     * @return æ›´æ–°å?Žçš„ä½œä¸šé…?ç½®
     */
    public LiteJobConfiguration updateJobConfiguration(final LiteJobConfiguration liteJobConfig) {
        configService.persist(liteJobConfig);
        return configService.load(false);
    }
    
    /**
     * æ³¨å†Œä½œä¸šå?¯åŠ¨ä¿¡æ?¯.
     * 
     * @param enabled ä½œä¸šæ˜¯å?¦å?¯ç”¨
     */
    public void registerStartUpInfo(final boolean enabled) {
        listenerManager.startAllListeners();
        leaderService.electLeader();
        serverService.persistOnline(enabled);
        instanceService.persistOnline();
        shardingService.setReshardingFlag();
        monitorService.listen();
        if (!reconcileService.isRunning()) {
            reconcileService.startAsync();
        }
    }
    
    /**
     * ç»ˆæ­¢ä½œä¸šè°ƒåº¦.
     */
    public void shutdownInstance() {
        if (leaderService.isLeader()) {
            leaderService.removeLeader();
        }
        monitorService.close();
        if (reconcileService.isRunning()) {
            reconcileService.stopAsync();
        }
        JobRegistry.getInstance().shutdown(jobName);
    }
}
