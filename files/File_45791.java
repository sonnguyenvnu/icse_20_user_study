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
package com.alipay.sofa.rpc.client;

import com.alipay.sofa.rpc.bootstrap.ConsumerBootstrap;
import com.alipay.sofa.rpc.common.struct.ConcurrentHashSet;
import com.alipay.sofa.rpc.common.struct.ListDifference;
import com.alipay.sofa.rpc.common.struct.NamedThreadFactory;
import com.alipay.sofa.rpc.common.struct.ScheduledService;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.ExceptionUtils;
import com.alipay.sofa.rpc.common.utils.NetUtils;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.AsyncRuntime;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.listener.ConsumerStateListener;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.transport.ClientTransport;
import com.alipay.sofa.rpc.transport.ClientTransportConfig;
import com.alipay.sofa.rpc.transport.ClientTransportFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 全部建立长连接，自动维护长连接
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
@Extension("all")
public class AllConnectConnectionHolder extends ConnectionHolder {

    /**
     * slf4j Logger for this class
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(AllConnectConnectionHolder.class);

    /**
     * �?务消费者�?置
     */
    protected ConsumerConfig    consumerConfig;

    /**
     * 构造函数
     *
     * @param consumerBootstrap �?务消费者�?置
     */
    protected AllConnectConnectionHolder(ConsumerBootstrap consumerBootstrap) {
        super(consumerBootstrap);
        this.consumerConfig = consumerBootstrap.getConsumerConfig();
    }

    /**
     * 未�?始化的（从未连接过，例如lazy=true）
     */
    protected ConcurrentMap<ProviderInfo, ClientTransport> uninitializedConnections = new ConcurrentHashMap<ProviderInfo, ClientTransport>();

    /**
     * 存活的客户端列表（�?�?了长连接，且一切正常的）
     */
    protected ConcurrentMap<ProviderInfo, ClientTransport> aliveConnections         = new ConcurrentHashMap<ProviderInfo, ClientTransport>();

    /**
     * 存活但是亚�?�康节点（连续心跳超时，这�?�?��?�心跳，�?�?�请求）
     */
    protected ConcurrentMap<ProviderInfo, ClientTransport> subHealthConnections     = new ConcurrentHashMap<ProviderInfo, ClientTransport>();

    /**
     * 失败待�?试的客户端列表（连上�?�断开的）
     */
    protected ConcurrentMap<ProviderInfo, ClientTransport> retryConnections         = new ConcurrentHashMap<ProviderInfo, ClientTransport>();

    /**
     * last address for registry pushed
     */
    protected Set<ProviderInfo>                            lastAddresses            = new HashSet<ProviderInfo>();

    /**
     * 客户端�?�化provider的�?
     */
    private Lock                                           providerLock             = new ReentrantLock();

    /**
     * Gets retry connections.
     *
     * @return the retry connections
     */
    public ConcurrentMap<ProviderInfo, ClientTransport> getRetryConnections() {
        return retryConnections;
    }

    /**
     * Add alive.
     *
     * @param providerInfo the provider
     * @param transport    the transport
     */
    protected void addAlive(ProviderInfo providerInfo, ClientTransport transport) {
        if (checkState(providerInfo, transport)) {
            aliveConnections.put(providerInfo, transport);
        }
    }

    /**
     * Add retry.
     *
     * @param providerInfo the provider
     * @param transport    the transport
     */
    protected void addRetry(ProviderInfo providerInfo, ClientTransport transport) {
        retryConnections.put(providerInfo, transport);
    }

    /**
     * 从存活丢到�?试列表
     *
     * @param providerInfo Provider
     * @param transport    连接
     */
    protected void aliveToRetry(ProviderInfo providerInfo, ClientTransport transport) {
        providerLock.lock();
        try {
            if (aliveConnections.remove(providerInfo) != null) {
                retryConnections.put(providerInfo, transport);
            }
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 从�?试丢到存活列表
     *
     * @param providerInfo Provider
     * @param transport    连接
     */
    protected void retryToAlive(ProviderInfo providerInfo, ClientTransport transport) {
        providerLock.lock();
        try {
            if (retryConnections.remove(providerInfo) != null) {
                if (checkState(providerInfo, transport)) {
                    aliveConnections.put(providerInfo, transport);
                }
            }
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 检查状�?是�?��?�用
     *
     * @param providerInfo    �?务�??供者信�?�
     * @param clientTransport 客户端长连接
     * @return 状�?是�?��?�用
     */
    protected boolean checkState(ProviderInfo providerInfo, ClientTransport clientTransport) {
        //        Protocol protocol = ProtocolFactory.getProtocol(providerInfo.getProtocolType());
        //        ProtocolNegotiator negotiator = protocol.negotiator();
        //        if (negotiator != null) {
        //            return negotiator.handshake(providerInfo, clientTransport);
        //        } else {
        return true;
        //        }
    }

    /**
     * 从存活丢到亚�?�康列表
     *
     * @param providerInfo Provider
     * @param transport    连接
     */
    protected void aliveToSubHealth(ProviderInfo providerInfo, ClientTransport transport) {
        providerLock.lock();
        try {
            if (aliveConnections.remove(providerInfo) != null) {
                subHealthConnections.put(providerInfo, transport);
            }
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 从亚�?�康丢到存活列表
     *
     * @param providerInfo Provider
     * @param transport    连接
     */
    protected void subHealthToAlive(ProviderInfo providerInfo, ClientTransport transport) {
        providerLock.lock();
        try {
            if (subHealthConnections.remove(providerInfo) != null) {
                if (checkState(providerInfo, transport)) {
                    aliveConnections.put(providerInfo, transport);
                }
            }
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 从亚�?�康丢到�?试列表
     *
     * @param providerInfo Provider
     * @param transport    连接
     */
    protected void subHealthToRetry(ProviderInfo providerInfo, ClientTransport transport) {
        providerLock.lock();
        try {
            if (subHealthConnections.remove(providerInfo) != null) {
                retryConnections.put(providerInfo, transport);
            }
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 删除provider
     *
     * @param providerInfo the provider
     * @return 如果已�?建立连接 ，返回ClientTransport
     */
    protected ClientTransport remove(ProviderInfo providerInfo) {
        providerLock.lock();
        try {
            ClientTransport transport = uninitializedConnections.remove(providerInfo);
            if (transport == null) {
                transport = aliveConnections.remove(providerInfo);
                if (transport == null) {
                    transport = subHealthConnections.remove(providerInfo);
                    if (transport == null) {
                        transport = retryConnections.remove(providerInfo);
                    }
                }
            }
            return transport;
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 通知状�?�?��?�?�?�用,主�?是：<br>
     * 1.注册中心删除，更新节点�?��?��?�?�?�用时<br>
     * 2.连接断线�?�（心跳+调用），如果是�?�用节点为空
     */
    public void notifyStateChangeToUnavailable() {
        final List<ConsumerStateListener> onAvailable = consumerConfig.getOnAvailable();
        if (onAvailable != null) {
            AsyncRuntime.getAsyncThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    // 状�?�?�化通知监�?�器
                    for (ConsumerStateListener listener : onAvailable) {
                        try {
                            listener.onUnavailable(consumerConfig.getConsumerBootstrap().getProxyIns());
                        } catch (Exception e) {
                            LOGGER.errorWithApp(consumerConfig.getAppName(),
                                "Failed to notify consumer state listener when state change to unavailable");
                        }
                    }
                }
            });
        }
    }

    /**
     * 通知状�?�?��?�?�用,主�?是：<br>
     * 1.�?�动�?功�?��?�?�用时<br>
     * 2.注册中心增加，更新节点�?��?��?�?�用时<br>
     * 3.�?连上从一个�?�用节点都没有�?��?有�?�用节点时
     */
    public void notifyStateChangeToAvailable() {
        final List<ConsumerStateListener> onAvailable = consumerConfig.getOnAvailable();
        if (onAvailable != null) {
            AsyncRuntime.getAsyncThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    // 状�?�?�化通知监�?�器
                    for (ConsumerStateListener listener : onAvailable) {
                        try {
                            listener.onAvailable(consumerConfig.getConsumerBootstrap().getProxyIns());
                        } catch (Exception e) {
                            LOGGER.warnWithApp(consumerConfig.getAppName(),
                                "Failed to notify consumer state listener when state change to available");
                        }
                    }
                }
            });
        }
    }

    @Override
    public void init() {
        if (reconThread == null) {
            startReconnectThread();
        }
    }

    @Override
    public void addProvider(ProviderGroup providerGroup) {
        // 忽略了tags属性
        if (!ProviderHelper.isEmpty(providerGroup)) {
            addNode(providerGroup.getProviderInfos());
        }
    }

    @Override
    public void removeProvider(ProviderGroup providerGroup) {
        // 忽略了tags属性
        if (!ProviderHelper.isEmpty(providerGroup)) {
            removeNode(providerGroup.getProviderInfos());
        }
    }

    @Override
    public void updateProviders(ProviderGroup providerGroup) {
        try {
            if (ProviderHelper.isEmpty(providerGroup)) {
                if (CommonUtils.isNotEmpty(currentProviderList())) {
                    if (LOGGER.isInfoEnabled(consumerConfig.getAppName())) {
                        LOGGER.infoWithApp(consumerConfig.getAppName(),
                            "Clear all providers, may be this consumer has been add to blacklist");
                    }
                    closeAllClientTransports(null);
                }
            } else {
                Collection<ProviderInfo> nowall = currentProviderList();
                List<ProviderInfo> oldAllP = providerGroup.getProviderInfos();
                List<ProviderInfo> nowAllP = new ArrayList<ProviderInfo>(nowall);// 当�?全部

                // 比较当�?的和最新的
                ListDifference<ProviderInfo> diff = new ListDifference<ProviderInfo>(oldAllP, nowAllP);
                List<ProviderInfo> needAdd = diff.getOnlyOnLeft(); // 需�?新建
                List<ProviderInfo> needDelete = diff.getOnlyOnRight(); // 需�?删掉
                if (!needAdd.isEmpty()) {
                    addNode(needAdd);
                }
                if (!needDelete.isEmpty()) {
                    removeNode(needDelete);
                }
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled(consumerConfig.getAppName())) {
                LOGGER.errorWithApp(consumerConfig.getAppName(), "update " + consumerConfig.getInterfaceId() +
                    " provider (" + providerGroup
                    + ") from list error:", e);
            }
        }
    }

    @Override
    public void updateAllProviders(List<ProviderGroup> providerGroups) {
        List<ProviderInfo> mergePs = new ArrayList<ProviderInfo>();
        if (CommonUtils.isNotEmpty(providerGroups)) {
            for (ProviderGroup providerGroup : providerGroups) {
                if (!ProviderHelper.isEmpty(providerGroup)) {
                    mergePs.addAll(providerGroup.getProviderInfos());
                }
            }
        }
        updateProviders(new ProviderGroup().addAll(mergePs));
    }

    protected void addNode(List<ProviderInfo> providerInfoList) {

        //first update last all providers
        lastAddresses.addAll(providerInfoList);

        final String interfaceId = consumerConfig.getInterfaceId();
        int providerSize = providerInfoList.size();
        String appName = consumerConfig.getAppName();
        if (LOGGER.isInfoEnabled(appName)) {
            LOGGER.infoWithApp(appName, "Add provider of {}, size is : {}", interfaceId, providerSize);
        }
        if (providerSize > 0) {
            // 多线程建立连接
            int threads = Math.min(10, providerSize); // 最大10个
            final CountDownLatch latch = new CountDownLatch(providerSize);
            ThreadPoolExecutor initPool = new ThreadPoolExecutor(threads, threads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(providerInfoList.size()),
                new NamedThreadFactory("CLI-CONN-" + interfaceId, true));
            int connectTimeout = consumerConfig.getConnectTimeout();
            for (final ProviderInfo providerInfo : providerInfoList) {
                initClientRunnable(initPool, latch, providerInfo);
            }

            try {
                int totalTimeout = ((providerSize % threads == 0) ? (providerSize / threads) : ((providerSize /
                    threads) + 1)) * connectTimeout + 500;
                latch.await(totalTimeout, TimeUnit.MILLISECONDS); // 一直等到�?线程都结�?�
            } catch (InterruptedException e) {
                LOGGER.errorWithApp(appName, "Exception when add provider", e);
            } finally {
                initPool.shutdown(); // 关闭线程池
            }
        }
    }

    /**
     * 线程池建立长连接
     */
    protected void initClientRunnable(ThreadPoolExecutor initPool, final CountDownLatch latch,
                                      final ProviderInfo providerInfo) {
        final ClientTransportConfig config = providerToClientConfig(providerInfo);
        initPool.execute(new Runnable() {
            @Override
            public void run() {
                ClientTransport transport = ClientTransportFactory.getClientTransport(config);
                if (consumerConfig.isLazy()) {
                    uninitializedConnections.put(providerInfo, transport);
                    latch.countDown();
                } else {
                    try {
                        initClientTransport(consumerConfig.getInterfaceId(), providerInfo, transport);
                    } finally {
                        latch.countDown(); // 连上或者抛异常
                    }
                }
            }
        });
    }

    protected void initClientTransport(String interfaceId, ProviderInfo providerInfo, ClientTransport transport) {
        try {
            transport.connect();
            if (doubleCheck(interfaceId, providerInfo, transport)) {
                printSuccess(interfaceId, providerInfo, transport);
                addAlive(providerInfo, transport);
            } else {
                printFailure(interfaceId, providerInfo, transport);
                addRetry(providerInfo, transport);
            }
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled(consumerConfig.getAppName())) {
                LOGGER.debugWithApp(consumerConfig.getAppName(), "Failed to connect " + providerInfo, e);
            }
            printDead(interfaceId, providerInfo, transport, e);
            addRetry(providerInfo, transport);
        }
    }

    public void removeNode(List<ProviderInfo> providerInfos) {

        //first update last all providers
        lastAddresses.removeAll(providerInfos);

        String interfaceId = consumerConfig.getInterfaceId();
        String appName = consumerConfig.getAppName();
        if (LOGGER.isInfoEnabled(appName)) {
            LOGGER.infoWithApp(appName, "Remove provider of {}, size is : {}", interfaceId, providerInfos.size());
        }
        for (ProviderInfo providerInfo : providerInfos) {
            try {
                // 从存活和�?试列表里都删除
                //  判断一个删�?功 就�?走下一个
                ClientTransport transport = remove(providerInfo);
                if (LOGGER.isInfoEnabled(appName)) {
                    LOGGER.infoWithApp(appName, "Remove provider of {}: {} from list success !", interfaceId,
                        providerInfo);
                }
                if (transport != null) {
                    ClientTransportFactory.releaseTransport(transport, consumerConfig.getDisconnectTimeout());
                }
            } catch (Exception e) {
                LOGGER.errorWithApp(appName, "Remove provider of " + consumerConfig.getInterfaceId() + ": " +
                    providerInfo
                    + " from list error:", e);
            }
        }
    }

    @Override
    public ConcurrentMap<ProviderInfo, ClientTransport> getAvailableConnections() {
        return aliveConnections.isEmpty() ? subHealthConnections : aliveConnections;
    }

    @Override
    public List<ProviderInfo> getAvailableProviders() {
        // 存活为空的，那就用亚�?�康的
        ConcurrentMap<ProviderInfo, ClientTransport> map =
                aliveConnections.isEmpty() ? subHealthConnections : aliveConnections;
        return new ArrayList<ProviderInfo>(map.keySet());
    }

    @Override
    public ClientTransport getAvailableClientTransport(ProviderInfo providerInfo) {
        // 先去存活列表
        ClientTransport transport = aliveConnections.get(providerInfo);
        if (transport != null) {
            return transport;
        }
        // �?去亚�?�康列表
        transport = subHealthConnections.get(providerInfo);
        if (transport != null) {
            return transport;
        }
        // 最�?�看看是�?�第一次调用未�?始化
        transport = uninitializedConnections.get(providerInfo);
        if (transport != null) {
            // 未�?始化则�?始化
            synchronized (this) {
                transport = uninitializedConnections.get(providerInfo);
                if (transport != null) {
                    initClientTransport(consumerConfig.getInterfaceId(), providerInfo, transport);
                    uninitializedConnections.remove(providerInfo);
                }
                return getAvailableClientTransport(providerInfo);
            }
        }
        return null;
    }

    @Override
    public boolean isAvailableEmpty() {
        return aliveConnections.isEmpty() && subHealthConnections.isEmpty();
    }

    /**
     * Provider对象得到 ClientTransportConfig
     *
     * @param providerInfo Provider
     * @return ClientTransportConfig
     */
    protected ClientTransportConfig providerToClientConfig(ProviderInfo providerInfo) {
        return new ClientTransportConfig()
            .setConsumerConfig(consumerConfig)
            .setProviderInfo(providerInfo)
            .setContainer(consumerConfig.getProtocol())
            .setConnectTimeout(consumerConfig.getConnectTimeout())
            .setInvokeTimeout(consumerConfig.getTimeout())
            .setDisconnectTimeout(consumerConfig.getDisconnectTimeout())
            .setConnectionNum(consumerConfig.getConnectionNum())
            .setChannelListeners(consumerConfig.getOnConnect());
    }

    /**
     * 获�?�当�?的Provider列表（包括连上和没连上的）
     *
     * @return 当�?的Provider列表 set
     */
    @Override
    public Set<ProviderInfo> currentProviderList() {
        providerLock.lock();
        try {
            ConcurrentHashSet<ProviderInfo> providerInfos = new ConcurrentHashSet<ProviderInfo>();
            providerInfos.addAll(lastAddresses);
            return providerInfos;
        } finally {
            providerLock.unlock();
        }
    }

    @Override
    public void setUnavailable(ProviderInfo providerInfo, ClientTransport transport) {
        providerLock.lock();
        try {
            boolean first = isAvailableEmpty();
            if (aliveConnections.remove(providerInfo) != null) {
                retryConnections.put(providerInfo, transport);
                if (!first && isAvailableEmpty()) { // 原�?��?空，�?��?空
                    notifyStateChangeToUnavailable();
                }
            }
        } finally {
            providerLock.unlock();
        }
    }

    @Override
    public void destroy() {
        destroy(null);
    }

    @Override
    public void destroy(DestroyHook destroyHook) {
        // 关闭�?连线程
        shutdownReconnectThread();
        // 关闭全部长连接
        closeAllClientTransports(destroyHook);
    }

    /**
     * 清空�?务列表
     *
     * @return 带回收的�?务列表
     */
    protected Map<ProviderInfo, ClientTransport> clearProviders() {
        providerLock.lock();
        try {
            // 当�?存活+�?试的
            HashMap<ProviderInfo, ClientTransport> all = new HashMap<ProviderInfo, ClientTransport>(aliveConnections);
            all.putAll(subHealthConnections);
            all.putAll(retryConnections);
            all.putAll(uninitializedConnections);
            subHealthConnections.clear();
            aliveConnections.clear();
            retryConnections.clear();
            uninitializedConnections.clear();
            lastAddresses.clear();
            return all;
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 销�?全部连接
     *
     * @param destroyHook 销�?钩�?
     */
    @Override
    public void closeAllClientTransports(DestroyHook destroyHook) {

        // 清空所有列表,�?让�?调了
        Map<ProviderInfo, ClientTransport> all = clearProviders();
        if (destroyHook != null) {
            try {
                destroyHook.preDestroy();
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled(consumerConfig.getAppName())) {
                    LOGGER.warnWithApp(consumerConfig.getAppName(), e.getMessage(), e);
                }
            }
        }
        // 多线程销�?已�?建立的连接
        int providerSize = all.size();
        if (providerSize > 0) {
            int timeout = consumerConfig.getDisconnectTimeout();
            int threads = Math.min(10, providerSize); // 最大10个
            final CountDownLatch latch = new CountDownLatch(providerSize);
            ThreadPoolExecutor closePool = new ThreadPoolExecutor(threads, threads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(providerSize),
                new NamedThreadFactory("CLI-DISCONN-" + consumerConfig.getInterfaceId(), true));
            for (Map.Entry<ProviderInfo, ClientTransport> entry : all.entrySet()) {
                final ProviderInfo providerInfo = entry.getKey();
                final ClientTransport transport = entry.getValue();
                closePool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ClientTransportFactory.releaseTransport(transport, 0);
                        } catch (Exception e) {
                            if (LOGGER.isWarnEnabled(consumerConfig.getAppName())) {
                                LOGGER.warnWithApp(consumerConfig.getAppName(),
                                    "catch exception but ignore it when close alive client : {}", providerInfo);
                            }
                        } finally {
                            latch.countDown();
                        }
                    }
                });
            }
            try {
                int totalTimeout = ((providerSize % threads == 0) ? (providerSize / threads) : ((providerSize /
                    threads) + 1)) * timeout + 500;
                latch.await(totalTimeout, TimeUnit.MILLISECONDS); // 一直等到
            } catch (InterruptedException e) {
                LOGGER.errorWithApp(consumerConfig.getAppName(), "Exception when close transport", e);
            } finally {
                closePool.shutdown();
            }
        }
    }

    /**
     * 打�?�连接�?功日志
     *
     * @param interfaceId  接�?��??称
     * @param providerInfo �?务端
     * @param transport    连接
     */
    protected void printSuccess(String interfaceId, ProviderInfo providerInfo, ClientTransport transport) {
        if (LOGGER.isInfoEnabled(consumerConfig.getAppName())) {
            LOGGER.infoWithApp(consumerConfig.getAppName(), "Connect to {} provider:{} success ! The connection is "
                + NetUtils.connectToString(transport.remoteAddress(), transport.localAddress())
                , interfaceId, providerInfo);
        }
    }

    /**
     * 打�?�连接失败日志
     *
     * @param interfaceId  接�?��??称
     * @param providerInfo �?务端
     * @param transport    连接
     */
    protected void printFailure(String interfaceId, ProviderInfo providerInfo, ClientTransport transport) {
        if (LOGGER.isInfoEnabled(consumerConfig.getAppName())) {
            LOGGER.infoWithApp(consumerConfig.getAppName(), "Connect to {} provider:{} failure !", interfaceId,
                providerInfo);
        }
    }

    /**
     * 打�?�连�?上日志
     *
     * @param interfaceId  接�?��??称
     * @param providerInfo �?务端
     * @param transport    连接
     * @param e            错误
     */
    protected void printDead(String interfaceId, ProviderInfo providerInfo, ClientTransport transport, Exception e) {
        Throwable cause = e.getCause();
        if (LOGGER.isWarnEnabled(consumerConfig.getAppName())) {
            LOGGER.warnWithApp(consumerConfig.getAppName(),
                "Connect to {} provider:{} failure !! The exception is " + ExceptionUtils.toShortString(e, 1)
                    + (cause != null ? ", cause by " + cause.getMessage() + "." : "."),
                interfaceId, providerInfo);
        }
    }

    /**
     * 获�?�当�?的Provider列表（包括连上和没连上的）
     *
     * @return 当�?的Provider列表 set
     */
    public Map<String, Set<ProviderInfo>> currentProviderMap() {
        providerLock.lock();
        try {
            Map<String, Set<ProviderInfo>> tmp = new LinkedHashMap<String, Set<ProviderInfo>>();
            tmp.put("alive", new HashSet<ProviderInfo>(aliveConnections.keySet()));
            tmp.put("subHealth", new HashSet<ProviderInfo>(subHealthConnections.keySet()));
            tmp.put("retry", new HashSet<ProviderInfo>(retryConnections.keySet()));
            tmp.put("uninitialized", new HashSet<ProviderInfo>(uninitializedConnections.keySet()));
            tmp.put("all", new HashSet<ProviderInfo>(lastAddresses));
            return tmp;
        } finally {
            providerLock.unlock();
        }
    }

    /**
     * 两次验�?检查ClientTransport是�?�存活
     *
     * @param interfaceId 接�?�
     * @param transport   ClientTransport对象
     * @return 是�?�存活
     */
    protected boolean doubleCheck(String interfaceId, ProviderInfo providerInfo, ClientTransport transport) {
        if (transport.isAvailable()) {
            try { // �?�一下下 防止被连上�?�被�?务端踢下线
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore
            }
            if (transport.isAvailable()) { // double check
                return true;
            } else { // �?�能在黑�??�?�里，刚连上就断开了
                if (LOGGER.isWarnEnabled(consumerConfig.getAppName())) {
                    LOGGER.warnWithApp(consumerConfig.getAppName(),
                        "Connection has been closed after connected (in last 100ms)!" +
                            " Maybe connectionNum of provider has been reached limit," +
                            " or your host is in the blacklist of provider {}/{}",
                        interfaceId, transport.getConfig().getProviderInfo());
                }
                providerInfo.setDynamicAttr(ProviderInfoAttrs.ATTR_RC_PERIOD_COEFFICIENT, 5);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * �?连线程
     */
    private volatile ScheduledService reconThread;

    /**
     * �?�动�?连+心跳线程
     */
    protected void startReconnectThread() {
        final String interfaceId = consumerConfig.getInterfaceId();
        // �?�动线程池
        // 默认�?隔10秒�?连
        int reconnect = consumerConfig.getReconnectPeriod();
        if (reconnect > 0) {
            reconnect = Math.max(reconnect, 2000); // 最�?2000
            reconThread = new ScheduledService("CLI-RC-" + interfaceId, ScheduledService.MODE_FIXEDDELAY, new
                Runnable() {
                    @Override
                    public void run() {
                        try {
                            doReconnect();
                        } catch (Throwable e) {
                            LOGGER.errorWithApp(consumerConfig.getAppName(),
                                "Exception when retry connect to provider", e);
                        }
                    }
                }, reconnect, reconnect, TimeUnit.MILLISECONDS).start();
        }
    }

    /**
     * �?试次数标记（针对�?个Provider�?一样）
     */
    private AtomicInteger reconnectFlag = new AtomicInteger();

    /**
     * �?连断开和死亡的节点
     */
    private void doReconnect() {
        String interfaceId = consumerConfig.getInterfaceId();
        String appName = consumerConfig.getAppName();
        int thisTime = reconnectFlag.incrementAndGet();
        boolean print = thisTime % 6 == 0; //是�?�打�?�error,�?6次打�?�一次
        boolean isAliveEmptyFirst = isAvailableEmpty();
        // 检查�?�用连接  todo subHealth
        for (Map.Entry<ProviderInfo, ClientTransport> alive : aliveConnections.entrySet()) {
            ClientTransport connection = alive.getValue();
            if (connection != null && !connection.isAvailable()) {
                aliveToRetry(alive.getKey(), connection);
            }
        }
        for (Map.Entry<ProviderInfo, ClientTransport> entry : getRetryConnections()
            .entrySet()) {
            ProviderInfo providerInfo = entry.getKey();
            int providerPeriodCoefficient = CommonUtils.parseNum((Integer)
                providerInfo.getDynamicAttr(ProviderInfoAttrs.ATTR_RC_PERIOD_COEFFICIENT), 1);
            if (thisTime % providerPeriodCoefficient != 0) {
                continue; // 如果命中�?连周期，则进行�?连
            }
            ClientTransport transport = entry.getValue();
            if (LOGGER.isDebugEnabled(appName)) {
                LOGGER.debugWithApp(appName, "Retry connect to {} provider:{} ...", interfaceId, providerInfo);
            }
            try {
                transport.connect();
                if (doubleCheck(interfaceId, providerInfo, transport)) {
                    providerInfo.setDynamicAttr(ProviderInfoAttrs.ATTR_RC_PERIOD_COEFFICIENT, 1);
                    retryToAlive(providerInfo, transport);
                }
            } catch (Exception e) {
                if (print) {
                    if (LOGGER.isWarnEnabled(appName)) {
                        LOGGER.warnWithApp(appName, "Retry connect to {} provider:{} error ! The exception is " + e
                            .getMessage(), interfaceId, providerInfo);
                    }
                } else {
                    if (LOGGER.isDebugEnabled(appName)) {
                        LOGGER.debugWithApp(appName, "Retry connect to {} provider:{} error ! The exception is " + e
                            .getMessage(), interfaceId, providerInfo);
                    }
                }
            }
        }
        if (isAliveEmptyFirst && !isAvailableEmpty()) { // 原�?�空，�?��?�?空
            notifyStateChangeToAvailable();
        }
    }

    /**
     * 关闭线程
     */
    protected void shutdownReconnectThread() {
        if (reconThread != null) {
            reconThread.shutdown();
            reconThread = null;
        }
    }
}
