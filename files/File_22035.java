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

package io.elasticjob.lite.internal.instance;

import io.elasticjob.lite.internal.schedule.JobRegistry;
import io.elasticjob.lite.internal.storage.JobNodePath;

/**
 * �?行实例节点路径.
 * 
 * @author zhangliang
 */
public final class InstanceNode {
    
    /**
     * �?行实例信�?�根节点.
     */
    public static final String ROOT = "instances";
    
    private static final String INSTANCES = ROOT + "/%s";
    
    private final String jobName;
    
    private final JobNodePath jobNodePath;
    
    public InstanceNode(final String jobName) {
        this.jobName = jobName;
        jobNodePath = new JobNodePath(jobName);
    }
    
    /**
     * 获�?�作业�?行实例全路径.
     *
     * @return 作业�?行实例全路径
     */
    public String getInstanceFullPath() {
        return jobNodePath.getFullPath(InstanceNode.ROOT);
    }
    
    /**
     * 判断给定路径是�?�为作业�?行实例路径.
     *
     * @param path 待判断的路径
     * @return 是�?�为作业�?行实例路径
     */
    public boolean isInstancePath(final String path) {
        return path.startsWith(jobNodePath.getFullPath(InstanceNode.ROOT));
    }
    
    boolean isLocalInstancePath(final String path) {
        return path.equals(jobNodePath.getFullPath(String.format(INSTANCES, JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId())));
    }
    
    String getLocalInstanceNode() {
        return String.format(INSTANCES, JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId());
    }
}
