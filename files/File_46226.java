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
package com.alipay.sofa.rpc.server.bolt;

import com.alipay.remoting.RemotingServer;
import com.alipay.remoting.rpc.RpcServer;
import com.alipay.sofa.rpc.common.cache.ReflectCache;
import com.alipay.sofa.rpc.common.struct.NamedThreadFactory;
import com.alipay.sofa.rpc.config.ConfigUniqueNameGenerator;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.event.EventBus;
import com.alipay.sofa.rpc.event.ServerStartedEvent;
import com.alipay.sofa.rpc.event.ServerStoppedEvent;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.invoke.Invoker;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.server.BusinessPool;
import com.alipay.sofa.rpc.server.Server;
import com.alipay.sofa.rpc.server.SofaRejectedExecutionHandler;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bolt server 
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
@Extension("bolt")
public class BoltServer implements Server {

    private static final Logger    LOGGER     = LoggerFactory.getLogger(BoltServer.class);

    /**
     * 是�?�已�?�?�动
     */
    protected volatile boolean     started;

    /**
     * Bolt�?务端
     */
    protected RemotingServer       remotingServer;

    /**
     * �?务端�?置
     */
    protected ServerConfig         serverConfig;

    /**
     * BoltServerProcessor
     */
    protected BoltServerProcessor  boltServerProcessor;
    /**
     * 业务线程池
     */
    protected ThreadPoolExecutor   bizThreadPool;

    /**
     * Invoker列表，接�?�--> Invoker
     */
    protected Map<String, Invoker> invokerMap = new ConcurrentHashMap<String, Invoker>();

    @Override
    public void init(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        // �?�动线程池
        bizThreadPool = initThreadPool(serverConfig);
        boltServerProcessor = new BoltServerProcessor(this);
    }

    protected ThreadPoolExecutor initThreadPool(ServerConfig serverConfig) {
        ThreadPoolExecutor threadPool = BusinessPool.initPool(serverConfig);
        threadPool.setThreadFactory(new NamedThreadFactory(
            "SEV-BOLT-BIZ-" + serverConfig.getPort(), serverConfig.isDaemon()));
        threadPool.setRejectedExecutionHandler(new SofaRejectedExecutionHandler());
        if (serverConfig.isPreStartCore()) { // �?始化核心线程池
            threadPool.prestartAllCoreThreads();
        }
        return threadPool;
    }

    @Override
    public void start() {
        if (started) {
            return;
        }
        synchronized (this) {
            if (started) {
                return;
            }
            // 生�?Server对象
            remotingServer = initRemotingServer();
            try {
                if (remotingServer.start()) {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("Bolt server has been bind to {}:{}", serverConfig.getBoundHost(),
                            serverConfig.getPort());
                    }
                } else {
                    throw new SofaRpcRuntimeException("Failed to start bolt server, see more detail from bolt log.");
                }
                started = true;

                if (EventBus.isEnable(ServerStartedEvent.class)) {
                    EventBus.post(new ServerStartedEvent(serverConfig, bizThreadPool));
                }

            } catch (SofaRpcRuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new SofaRpcRuntimeException("Failed to start bolt server!", e);
            }
        }
    }

    protected RemotingServer initRemotingServer() {
        // 绑定到端�?�
        RemotingServer remotingServer = new RpcServer(serverConfig.getBoundHost(), serverConfig.getPort());
        remotingServer.registerUserProcessor(boltServerProcessor);
        return remotingServer;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public boolean hasNoEntry() {
        return invokerMap.isEmpty();
    }

    @Override
    public void stop() {
        if (!started) {
            return;
        }
        synchronized (this) {
            if (!started) {
                return;
            }
            // 关闭端�?�，�?关闭线程池
            try {
                remotingServer.stop();
            } catch (IllegalStateException ignore) { // NOPMD
            }
            if (EventBus.isEnable(ServerStoppedEvent.class)) {
                EventBus.post(new ServerStoppedEvent(serverConfig));
            }
            remotingServer = null;
            started = false;
        }
    }

    @Override
    public void registerProcessor(ProviderConfig providerConfig, Invoker instance) {
        // 缓存Invoker对象
        String key = ConfigUniqueNameGenerator.getUniqueName(providerConfig);
        invokerMap.put(key, instance);
        ReflectCache.registerServiceClassLoader(key, providerConfig.getProxyClass().getClassLoader());
        // 缓存接�?�的方法
        for (Method m : providerConfig.getProxyClass().getMethods()) {
            ReflectCache.putOverloadMethodCache(key, m);
        }
    }

    @Override
    public void unRegisterProcessor(ProviderConfig providerConfig, boolean closeIfNoEntry) {
        // �?�消缓存Invoker对象
        String key = ConfigUniqueNameGenerator.getUniqueName(providerConfig);
        invokerMap.remove(key);
        ReflectCache.unRegisterServiceClassLoader(key);
        // 如果最�?�一个需�?关闭，则关闭
        if (closeIfNoEntry && invokerMap.isEmpty()) {
            stop();
        }
    }

    @Override
    public void destroy() {
        if (!started) {
            return;
        }
        int stopTimeout = serverConfig.getStopTimeout();
        if (stopTimeout > 0) { // 需�?等待结�?�时间
            AtomicInteger count = boltServerProcessor.processingCount;
            // 有正在执行的请求 或者 队列里有请求
            if (count.get() > 0 || bizThreadPool.getQueue().size() > 0) {
                long start = RpcRuntimeContext.now();
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("There are {} call in processing and {} call in queue, wait {} ms to end",
                        count, bizThreadPool.getQueue().size(), stopTimeout);
                }
                while ((count.get() > 0 || bizThreadPool.getQueue().size() > 0)
                    && RpcRuntimeContext.now() - start < stopTimeout) { // 等待返回结果
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignore) {
                    }
                }
            } // 关闭�?检查已有请求？
        }

        // 关闭线程池
        bizThreadPool.shutdown();
        stop();
    }

    @Override
    public void destroy(DestroyHook hook) {
        if (hook != null) {
            hook.preDestroy();
        }
        destroy();
        if (hook != null) {
            hook.postDestroy();
        }
    }

    /**
     * 得到业务线程池
     *
     * @return 业务线程池
     */
    public ThreadPoolExecutor getBizThreadPool() {
        return bizThreadPool;
    }

    /**
     * 找到�?务端Invoker
     *
     * @param serviceName �?务�??
     * @return Invoker对象
     */
    public Invoker findInvoker(String serviceName) {
        return invokerMap.get(serviceName);
    }
}
