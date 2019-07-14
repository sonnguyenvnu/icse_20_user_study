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
package com.alipay.sofa.rpc.server;

import com.alipay.sofa.rpc.common.struct.NamedThreadFactory;
import com.alipay.sofa.rpc.common.utils.ThreadPoolUtils;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 给用户�?置的自定义业务线程池
 * <p>
 * 
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class UserThreadPool {

    /**
     * 核心线程池
     *
     * @see ThreadPoolExecutor#corePoolSize
     */
    private int                           corePoolSize    = 10;
    /**
     * 最大线程池
     *
     * @see ThreadPoolExecutor#maximumPoolSize
     */
    private int                           maximumPoolSize = 100;
    /**
     * 线程回收时间（毫秒）
     *
     * @see ThreadPoolExecutor#keepAliveTime
     */
    private int                           keepAliveTime   = 300000;
    /**
     * 队列大�?
     *
     * @see ThreadPoolExecutor#getQueue()
     */
    private int                           queueSize       = 0;
    /**
     * 线程�??字
     *
     * @see ThreadPoolExecutor#threadFactory#threadPoolName
     */
    private String                        threadPoolName  = "SofaUserProcessor";
    /**
     * 是�?�关闭核心线程池
     *
     * @see ThreadPoolExecutor#allowCoreThreadTimeOut
     */
    private boolean                       allowCoreThreadTimeOut;
    /**
     * 是�?��?始化核心线程池
     *
     * @see ThreadPoolExecutor#prestartAllCoreThreads
     */
    private boolean                       prestartAllCoreThreads;

    /**
     * 线程池
     */
    transient volatile ThreadPoolExecutor executor;

    /**
     * �?始化线程池
     */
    public void init() {
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
            ThreadPoolUtils.buildQueue(queueSize), new NamedThreadFactory(threadPoolName));
        if (allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }
        if (prestartAllCoreThreads) {
            executor.prestartAllCoreThreads();
        }
    }

    /**
     * 销�?线程池.
     */
    public void destroy() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    /**
     * Gets core pool size.
     *
     * @return the core pool size
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * Sets core pool size.
     *
     * @param corePoolSize the core pool size
     * @return the core pool size
     */
    public UserThreadPool setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    /**
     * Gets maximum pool size.
     *
     * @return the maximum pool size
     */
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    /**
     * Sets maximum pool size.
     *
     * @param maximumPoolSize the maximum pool size
     * @return the maximum pool size
     */
    public UserThreadPool setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    /**
     * Gets queue size.
     *
     * @return the queue size
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * Sets queue size.
     *
     * @param queueSize the queue size
     * @return the queue size
     */
    public UserThreadPool setQueueSize(int queueSize) {
        this.queueSize = queueSize;
        return this;
    }

    /**
     * Gets thread pool name.
     *
     * @return the thread pool name
     */
    public String getThreadPoolName() {
        return threadPoolName;
    }

    /**
     * Sets thread pool name.
     *
     * @param threadPoolName the thread pool name
     * @return the thread pool name
     */
    public UserThreadPool setThreadPoolName(String threadPoolName) {
        this.threadPoolName = threadPoolName;
        return this;
    }

    /**
     * Is allow core thread time out boolean.
     *
     * @return the boolean
     */
    public boolean isAllowCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    /**
     * Sets allow core thread time out.
     *
     * @param allowCoreThreadTimeOut the allow core thread time out
     * @return the allow core thread time out
     */
    public UserThreadPool setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        return this;
    }

    /**
     * Is prestart all core threads boolean.
     *
     * @return the boolean
     */
    public boolean isPrestartAllCoreThreads() {
        return prestartAllCoreThreads;
    }

    /**
     * Sets prestart all core threads.
     *
     * @param prestartAllCoreThreads the prestart all core threads
     * @return the prestart all core threads
     */
    public UserThreadPool setPrestartAllCoreThreads(boolean prestartAllCoreThreads) {
        this.prestartAllCoreThreads = prestartAllCoreThreads;
        return this;
    }

    /**
     * Gets keep alive time.
     *
     * @return the keep alive time
     */
    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    /**
     * Sets keep alive time.
     *
     * @param keepAliveTime the keep alive time
     * @return the keep alive time
     */
    public UserThreadPool setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    /**
     * Gets executor.
     *
     * @return the executor
     */
    public ThreadPoolExecutor getExecutor() {
        if (executor == null) {
            synchronized (this) {
                if (executor == null) {
                    init();
                }
            }
        }
        return executor;
    }
}
