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

package io.elasticjob.lite.internal.storage;

import io.elasticjob.lite.exception.JobSystemException;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import io.elasticjob.lite.reg.exception.RegExceptionHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorTransactionFinal;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.state.ConnectionStateListener;

import java.util.List;

/**
 * 作业节点数�?�访问类.
 * 
 * <p>
 * 作业节点是在普通的节点�?加上作业�??称的�?缀.
 * </p>
 * 
 * @author zhangliang
 */
public final class JobNodeStorage {
    
    private final CoordinatorRegistryCenter regCenter;
    
    private final String jobName;
    
    private final JobNodePath jobNodePath;
    
    public JobNodeStorage(final CoordinatorRegistryCenter regCenter, final String jobName) {
        this.regCenter = regCenter;
        this.jobName = jobName;
        jobNodePath = new JobNodePath(jobName);
    }
    
    /**
     * 判断作业节点是�?�存在.
     * 
     * @param node 作业节点�??称
     * @return 作业节点是�?�存在
     */
    public boolean isJobNodeExisted(final String node) {
        return regCenter.isExisted(jobNodePath.getFullPath(node));
    }
    
    /**
     * 获�?�作业节点数�?�.
     * 
     * @param node 作业节点�??称
     * @return 作业节点数�?�值
     */
    public String getJobNodeData(final String node) {
        return regCenter.get(jobNodePath.getFullPath(node));
    }
    
    /**
     * 直接从注册中心而�?�本地缓存获�?�作业节点数�?�.
     * 
     * @param node 作业节点�??称
     * @return 作业节点数�?�值
     */
    public String getJobNodeDataDirectly(final String node) {
        return regCenter.getDirectly(jobNodePath.getFullPath(node));
    }
    
    /**
     * 获�?�作业节点�?节点�??称列表.
     * 
     * @param node 作业节点�??称
     * @return 作业节点�?节点�??称列表
     */
    public List<String> getJobNodeChildrenKeys(final String node) {
        return regCenter.getChildrenKeys(jobNodePath.getFullPath(node));
    }
    
    /**
     * 如果存在则创建作业节点.
     * 
     * <p>如果作业根节点�?存在表示作业已�?�?�止, �?�?继续创建节点.</p>
     * 
     * @param node 作业节点�??称
     */
    public void createJobNodeIfNeeded(final String node) {
        if (isJobRootNodeExisted() && !isJobNodeExisted(node)) {
            regCenter.persist(jobNodePath.getFullPath(node), "");
        }
    }
    
    private boolean isJobRootNodeExisted() {
        return regCenter.isExisted("/" + jobName);
    }
    
    /**
     * 删除作业节点.
     * 
     * @param node 作业节点�??称
     */
    public void removeJobNodeIfExisted(final String node) {
        if (isJobNodeExisted(node)) {
            regCenter.remove(jobNodePath.getFullPath(node));
        }
    }
        
    /**
     * 填充节点数�?�.
     *
     * @param node 作业节点�??称
     * @param value 作业节点数�?�值
     */
    public void fillJobNode(final String node, final Object value) {
        regCenter.persist(jobNodePath.getFullPath(node), value.toString());
    }
    
    /**
     * 填充临时节点数�?�.
     * 
     * @param node 作业节点�??称
     * @param value 作业节点数�?�值
     */
    public void fillEphemeralJobNode(final String node, final Object value) {
        regCenter.persistEphemeral(jobNodePath.getFullPath(node), value.toString());
    }
    
    /**
     * 更新节点数�?�.
     * 
     * @param node 作业节点�??称
     * @param value 作业节点数�?�值
     */
    public void updateJobNode(final String node, final Object value) {
        regCenter.update(jobNodePath.getFullPath(node), value.toString());
    }
    
    /**
     * 替�?�作业节点数�?�.
     * 
     * @param node 作业节点�??称
     * @param value 待替�?�的数�?�
     */
    public void replaceJobNode(final String node, final Object value) {
        regCenter.persist(jobNodePath.getFullPath(node), value.toString());
    }

    /**
     * 在事务中执行�?作.
     * 
     * @param callback 执行�?作的回调
     */
    public void executeInTransaction(final TransactionExecutionCallback callback) {
        try {
            CuratorTransactionFinal curatorTransactionFinal = getClient().inTransaction().check().forPath("/").and();
            callback.execute(curatorTransactionFinal);
            curatorTransactionFinal.commit();
        //CHECKSTYLE:OFF
        } catch (final Exception ex) {
        //CHECKSTYLE:ON
            RegExceptionHandler.handleException(ex);
        }
    }
    
    /**
     * 在主节点执行�?作.
     * 
     * @param latchNode 分布�?�?使用的作业节点�??称
     * @param callback 执行�?作的回调
     */
    public void executeInLeader(final String latchNode, final LeaderExecutionCallback callback) {
        try (LeaderLatch latch = new LeaderLatch(getClient(), jobNodePath.getFullPath(latchNode))) {
            latch.start();
            latch.await();
            callback.execute();
        //CHECKSTYLE:OFF
        } catch (final Exception ex) {
        //CHECKSTYLE:ON
            handleException(ex);
        }
    }
    
    private void handleException(final Exception ex) {
        if (ex instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        } else {
            throw new JobSystemException(ex);
        }
    }
    
    /**
     * 注册连接状�?监�?�器.
     * 
     * @param listener 连接状�?监�?�器
     */
    public void addConnectionStateListener(final ConnectionStateListener listener) {
        getClient().getConnectionStateListenable().addListener(listener);
    }
    
    private CuratorFramework getClient() {
        return (CuratorFramework) regCenter.getRawClient();
    }
    
    /**
     * 注册数�?�监�?�器.
     * 
     * @param listener 数�?�监�?�器
     */
    public void addDataListener(final TreeCacheListener listener) {
        TreeCache cache = (TreeCache) regCenter.getRawCache("/" + jobName);
        cache.getListenable().addListener(listener);
    }
    
    /**
     * 获�?�注册中心当�?时间.
     * 
     * @return 注册中心当�?时间
     */
    public long getRegistryCenterTime() {
        return regCenter.getRegistryCenterTime(jobNodePath.getFullPath("systemTime/current"));
    }
}
