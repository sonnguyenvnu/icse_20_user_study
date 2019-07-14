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

package io.elasticjob.lite.api.listener;

import io.elasticjob.lite.exception.JobSystemException;
import io.elasticjob.lite.executor.ShardingContexts;
import io.elasticjob.lite.internal.guarantee.GuaranteeService;
import io.elasticjob.lite.util.env.TimeService;
import lombok.Setter;

/**
 * 在分布�?作业中�?�执行一次的监�?�器.
 * 
 * @author zhangliang
 */
public abstract class AbstractDistributeOnceElasticJobListener implements ElasticJobListener {
    
    private final long startedTimeoutMilliseconds;
    
    private final Object startedWait = new Object();
    
    private final long completedTimeoutMilliseconds;
    
    private final Object completedWait = new Object();
    
    @Setter
    private GuaranteeService guaranteeService;
    
    private TimeService timeService = new TimeService();
    
    public AbstractDistributeOnceElasticJobListener(final long startedTimeoutMilliseconds, final long completedTimeoutMilliseconds) {
        if (startedTimeoutMilliseconds <= 0L) {
            this.startedTimeoutMilliseconds = Long.MAX_VALUE;
        } else {
            this.startedTimeoutMilliseconds = startedTimeoutMilliseconds;
        }
        if (completedTimeoutMilliseconds <= 0L) {
            this.completedTimeoutMilliseconds = Long.MAX_VALUE; 
        } else {
            this.completedTimeoutMilliseconds = completedTimeoutMilliseconds;
        }
    }
    
    @Override
    public final void beforeJobExecuted(final ShardingContexts shardingContexts) {
        guaranteeService.registerStart(shardingContexts.getShardingItemParameters().keySet());
        if (guaranteeService.isAllStarted()) {
            doBeforeJobExecutedAtLastStarted(shardingContexts);
            guaranteeService.clearAllStartedInfo();
            return;
        }
        long before = timeService.getCurrentMillis();
        try {
            synchronized (startedWait) {
                startedWait.wait(startedTimeoutMilliseconds);
            }
        } catch (final InterruptedException ex) {
            Thread.interrupted();
        }
        if (timeService.getCurrentMillis() - before >= startedTimeoutMilliseconds) {
            guaranteeService.clearAllStartedInfo();
            handleTimeout(startedTimeoutMilliseconds);
        }
    }
    
    @Override
    public final void afterJobExecuted(final ShardingContexts shardingContexts) {
        guaranteeService.registerComplete(shardingContexts.getShardingItemParameters().keySet());
        if (guaranteeService.isAllCompleted()) {
            doAfterJobExecutedAtLastCompleted(shardingContexts);
            guaranteeService.clearAllCompletedInfo();
            return;
        }
        long before = timeService.getCurrentMillis();
        try {
            synchronized (completedWait) {
                completedWait.wait(completedTimeoutMilliseconds);
            }
        } catch (final InterruptedException ex) {
            Thread.interrupted();
        }
        if (timeService.getCurrentMillis() - before >= completedTimeoutMilliseconds) {
            guaranteeService.clearAllCompletedInfo();
            handleTimeout(completedTimeoutMilliseconds);
        }
    }
    
    private void handleTimeout(final long timeoutMilliseconds) {
        throw new JobSystemException("Job timeout. timeout mills is %s.", timeoutMilliseconds);
    }
    
    /**
     * 分布�?环境中最�?�一个作业执行�?的执行的方法.
     *
     * @param shardingContexts 分片上下文
     */
    public abstract void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts);
    
    /**
     * 分布�?环境中最�?�一个作业执行�?�的执行的方法.
     *
     * @param shardingContexts 分片上下文
     */
    public abstract void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts);
    
    /**
     * 通知任务开始.
     */
    public void notifyWaitingTaskStart() {
        synchronized (startedWait) {
            startedWait.notifyAll();
        }
    }
    
    /**
     * 通知任务结�?�.
     */
    public void notifyWaitingTaskComplete() {
        synchronized (completedWait) {
            completedWait.notifyAll();
        }
    }
}
