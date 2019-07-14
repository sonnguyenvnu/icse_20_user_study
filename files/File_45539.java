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
package com.alipay.sofa.rpc.config;

import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.bootstrap.Bootstraps;
import com.alipay.sofa.rpc.bootstrap.ConsumerBootstrap;
import com.alipay.sofa.rpc.client.Router;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.annotation.Unstable;
import com.alipay.sofa.rpc.common.utils.ClassUtils;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.ExceptionUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.core.invoke.SofaResponseCallback;
import com.alipay.sofa.rpc.listener.ChannelListener;
import com.alipay.sofa.rpc.listener.ConsumerStateListener;
import com.alipay.sofa.rpc.listener.ProviderInfoListener;

import java.io.Serializable;
import java.util.List;

import static com.alipay.sofa.rpc.common.RpcConfigs.getBooleanValue;
import static com.alipay.sofa.rpc.common.RpcConfigs.getIntValue;
import static com.alipay.sofa.rpc.common.RpcConfigs.getStringValue;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_ADDRESS_HOLDER;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_ADDRESS_WAIT;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_CHECK;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_CLUSTER;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_CONCURRENTS;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_CONNECTION_HOLDER;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_CONNECTION_NUM;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_CONNECT_TIMEOUT;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_DISCONNECT_TIMEOUT;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_HEARTBEAT_PERIOD;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_INJVM;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_INVOKE_TYPE;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_LAZY;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_LOAD_BALANCER;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_RECONNECT_PERIOD;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_REPEATED_REFERENCE_LIMIT;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_RETRIES;
import static com.alipay.sofa.rpc.common.RpcOptions.CONSUMER_STICKY;
import static com.alipay.sofa.rpc.common.RpcOptions.DEFAULT_PROTOCOL;

/**
 * �?务消费者�?置
 * 
 * @param <T> the type parameter
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class ConsumerConfig<T> extends AbstractInterfaceConfig<T, ConsumerConfig<T>> implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long                       serialVersionUID   = 4244077707655448146L;

    /**
     * 调用的�??议
     */
    protected String                                protocol           = getStringValue(DEFAULT_PROTOCOL);

    /**
     * 直连调用地�?�
     */
    protected String                                directUrl;

    /**
     * 是�?�泛化调用
     */
    protected boolean                               generic;

    /**
     * 是�?�异步调用
     */
    protected String                                invokeType         = getStringValue(CONSUMER_INVOKE_TYPE);

    /**
     * 连接超时时间
     */
    protected int                                   connectTimeout     = getIntValue(CONSUMER_CONNECT_TIMEOUT);

    /**
     * 关闭超时时间（如果还有请求，会等待请求结�?�或者超时）
     */
    protected int                                   disconnectTimeout  = getIntValue(CONSUMER_DISCONNECT_TIMEOUT);

    /**
     * 集群处�?�，默认是failover
     */
    protected String                                cluster            = getStringValue(CONSUMER_CLUSTER);

    /**
     * The ConnectionHolder 连接管�?�器
     */
    protected String                                connectionHolder   = getStringValue(CONSUMER_CONNECTION_HOLDER);

    /**
     * 地�?�管�?�器
     */
    protected String                                addressHolder      = getStringValue(CONSUMER_ADDRESS_HOLDER);

    /**
     * 负载�?�衡
     */
    protected String                                loadBalancer       = getStringValue(CONSUMER_LOAD_BALANCER);

    /**
     * 是�?�延迟建立长连接（第一次调用时新建，注�?此�?�数�?�能和check冲�?，开�?�check�?�lazy自动失效）
     *
     * @see ConsumerConfig#check
     */
    protected boolean                               lazy               = getBooleanValue(CONSUMER_LAZY);

    /**
     * 粘滞连接，一个断开�?选下一个
     * change transport when current is disconnected
     */
    protected boolean                               sticky             = getBooleanValue(CONSUMER_STICKY);

    /**
     * 是�?�jvm内部调用（provider和consumer�?置在�?�一个jvm内，则走本地jvm内部，�?走远程）
     */
    protected boolean                               inJVM              = getBooleanValue(CONSUMER_INJVM);

    /**
     * 是�?�强�?赖（�?�没有�?务节点就�?�动失败，注�?此�?�数�?�能和lazy冲�?，开�?�check�?�lazy自动失效)
     *
     * @see ConsumerConfig#lazy
     */
    protected boolean                               check              = getBooleanValue(CONSUMER_CHECK);

    /**
     * 长连接个数，�?是所有的框架都支�?一个地�?�多个长连接
     */
    protected int                                   connectionNum      = getIntValue(CONSUMER_CONNECTION_NUM);

    /**
     * Consumer给Provider�?�心跳的间隔
     */
    protected int                                   heartbeatPeriod    = getIntValue(CONSUMER_HEARTBEAT_PERIOD);

    /**
     * Consumer给Provider�?连的间隔
     */
    protected int                                   reconnectPeriod    = getIntValue(CONSUMER_RECONNECT_PERIOD);

    /**
     * 路由�?置别�??
     */
    protected List<String>                          router;

    /**
     * 路由规则引用，多个用英文逗�?�隔开。List<Router>
     */
    protected transient List<Router>                routerRef;

    /**
     * 返回值之�?的listener,处�?�结果或者异常
     */
    protected transient SofaResponseCallback        onReturn;

    /**
     * 连接事件监�?�器实例，连接或者断开时触�?�
     */
    @Unstable
    protected transient List<ChannelListener>       onConnect;

    /**
     * 客户端状�?�?�化监�?�器实例，状�?�?�用和�?�?�以时触�?�
     */
    @Unstable
    protected transient List<ConsumerStateListener> onAvailable;

    /**
     * �?�动器
     */
    protected String                                bootstrap;

    /**
     * 等待地�?�获�?�时间(毫秒)，-1表示等到拿到地�?��?置
     */
    protected int                                   addressWait        = getIntValue(CONSUMER_ADDRESS_WAIT);

    /**
     * �?�一个�?务（接�?��??议uniqueId相�?�）的最大引用次数，防止由于代�?bug导致�?�?引用，�?次引用都会生�?一个代�?�类对象，-1表示�?检查
     *
     * @since 5.2.0
     */
    protected int                                   repeatedReferLimit = getIntValue(CONSUMER_REPEATED_REFERENCE_LIMIT);

    /*-------- 下�?�是方法级�?�覆盖�?置 --------*/
    /**
     * 客户端调用超时时间(毫秒)
     */
    protected int                                   timeout            = -1;

    /**
     * The Retries. 失败�?��?试次数
     */
    protected int                                   retries            = getIntValue(CONSUMER_RETRIES);

    /**
     * 接�?�下�?方法的最大�?�并行执行请求数，�?置-1关闭并�?�过滤器，等于0表示开�?�过滤但是�?�?制
     */
    protected int                                   concurrents        = getIntValue(CONSUMER_CONCURRENTS);

    /*---------- �?�数�?置项结�?� ------------*/

    /**
     * �?务消费者�?�动类
     */
    private transient ConsumerBootstrap<T>          consumerBootstrap;

    /**
     * �?务列表的listener
     */
    private transient volatile ProviderInfoListener providerInfoListener;

    /**
     * Build key.
     *
     * @return the string
     */
    @Override
    public String buildKey() {
        return protocol + "://" + interfaceId + ":" + uniqueId;
    }

    /**
     * Gets proxy class.
     *
     * @return the proxyClass
     */
    @Override
    public Class<?> getProxyClass() {
        if (proxyClass != null) {
            return proxyClass;
        }
        if (generic) {
            return GenericService.class;
        }
        try {
            if (StringUtils.isNotBlank(interfaceId)) {
                this.proxyClass = ClassUtils.forName(interfaceId);
                if (!proxyClass.isInterface()) {
                    throw ExceptionUtils.buildRuntime("consumer.interface",
                        interfaceId, "interfaceId must set interface class, not implement class");
                }
            } else {
                throw ExceptionUtils.buildRuntime("consumer.interface",
                    "null", "interfaceId must be not null");
            }
        } catch (RuntimeException t) {
            throw new IllegalStateException(t.getMessage(), t);
        }
        return proxyClass;
    }

    /**
     * Gets protocol.
     *
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets protocol.
     *
     * @param protocol the protocol
     * @return the protocol
     */
    public ConsumerConfig<T> setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /**
     * Gets directUrl.
     *
     * @return the directUrl
     */
    public String getDirectUrl() {
        return directUrl;
    }

    /**
     * Sets directUrl.
     *
     * @param directUrl the directUrl
     * @return the directUrl
     */
    public ConsumerConfig<T> setDirectUrl(String directUrl) {
        this.directUrl = directUrl;
        return this;
    }

    /**
     * Is generic boolean.
     *
     * @return the boolean
     */
    public boolean isGeneric() {
        return generic;
    }

    /**
     * Sets generic.
     *
     * @param generic the generic
     * @return the generic
     */
    public ConsumerConfig<T> setGeneric(boolean generic) {
        this.generic = generic;
        return this;
    }

    /**
     * Gets invoke type.
     *
     * @return the invoke type
     */
    public String getInvokeType() {
        return invokeType;
    }

    /**
     * Sets invoke type.
     *
     * @param invokeType the invoke type
     * @return the invoke type
     */
    public ConsumerConfig<T> setInvokeType(String invokeType) {
        this.invokeType = invokeType;
        return this;
    }

    /**
     * Gets connect timeout.
     *
     * @return the connect timeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets connect timeout.
     *
     * @param connectTimeout the connect timeout
     * @return the connect timeout
     */
    public ConsumerConfig<T> setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * Gets disconnect timeout.
     *
     * @return the disconnect timeout
     */
    public int getDisconnectTimeout() {
        return disconnectTimeout;
    }

    /**
     * Sets disconnect timeout.
     *
     * @param disconnectTimeout the disconnect timeout
     * @return the disconnect timeout
     */
    public ConsumerConfig<T> setDisconnectTimeout(int disconnectTimeout) {
        this.disconnectTimeout = disconnectTimeout;
        return this;
    }

    /**
     * Gets cluster.
     *
     * @return the cluster
     */
    public String getCluster() {
        return cluster;
    }

    /**
     * Sets cluster.
     *
     * @param cluster the cluster
     * @return the cluster
     */
    public ConsumerConfig<T> setCluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    /**
     * Gets retries.
     *
     * @return the retries
     */
    public int getRetries() {
        return retries;
    }

    /**
     * Sets retries.
     *
     * @param retries the retries
     * @return the retries
     */
    public ConsumerConfig<T> setRetries(int retries) {
        this.retries = retries;
        return this;
    }

    /**
     * Gets connection holder.
     *
     * @return the connection holder
     */
    public String getConnectionHolder() {
        return connectionHolder;
    }

    /**
     * Sets connection holder.
     *
     * @param connectionHolder the connection holder
     * @return the connection holder
     */
    public ConsumerConfig<T> setConnectionHolder(String connectionHolder) {
        this.connectionHolder = connectionHolder;
        return this;
    }

    /**
     * Gets address holder.
     *
     * @return the address holder
     */
    public String getAddressHolder() {
        return addressHolder;
    }

    /**
     * Sets address holder.
     *
     * @param addressHolder the address holder
     * @return the address holder
     */
    public ConsumerConfig setAddressHolder(String addressHolder) {
        this.addressHolder = addressHolder;
        return this;
    }

    /**
     * Gets load balancer.
     *
     * @return the load balancer
     */
    public String getLoadBalancer() {
        return loadBalancer;
    }

    /**
     * Sets load balancer.
     *
     * @param loadBalancer the load balancer
     * @return the load balancer
     */
    public ConsumerConfig<T> setLoadBalancer(String loadBalancer) {
        this.loadBalancer = loadBalancer;
        return this;
    }

    /**
     * Is lazy boolean.
     *
     * @return the boolean
     */
    public boolean isLazy() {
        return lazy;
    }

    /**
     * Sets lazy.
     *
     * @param lazy the lazy
     * @return the lazy
     */
    public ConsumerConfig<T> setLazy(boolean lazy) {
        this.lazy = lazy;
        return this;
    }

    /**
     * Is sticky boolean.
     *
     * @return the boolean
     */
    public boolean isSticky() {
        return sticky;
    }

    /**
     * Sets sticky.
     *
     * @param sticky the sticky
     * @return the sticky
     */
    public ConsumerConfig<T> setSticky(boolean sticky) {
        this.sticky = sticky;
        return this;
    }

    /**
     * Is in jvm boolean.
     *
     * @return the boolean
     */
    public boolean isInJVM() {
        return inJVM;
    }

    /**
     * Sets in jvm.
     *
     * @param inJVM the in jvm
     * @return the in jvm
     */
    public ConsumerConfig<T> setInJVM(boolean inJVM) {
        this.inJVM = inJVM;
        return this;
    }

    /**
     * Is check boolean.
     *
     * @return the boolean
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * Sets check.
     *
     * @param check the check
     * @return the check
     */
    public ConsumerConfig<T> setCheck(boolean check) {
        this.check = check;
        return this;
    }

    /**
     * Gets connectionNum.
     *
     * @return the connectionNum
     */
    public int getConnectionNum() {
        return connectionNum;
    }

    /**
     * Sets connectionNum.
     *
     * @param connectionNum the connectionNum
     * @return the connectionNum
     */
    public ConsumerConfig<T> setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
        return this;
    }

    /**
     * Gets heartbeatPeriod.
     *
     * @return the heartbeatPeriod
     */
    public int getHeartbeatPeriod() {
        return heartbeatPeriod;
    }

    /**
     * Sets heartbeatPeriod.
     *
     * @param heartbeatPeriod the heartbeatPeriod
     * @return the heartbeatPeriod
     */
    public ConsumerConfig<T> setHeartbeatPeriod(int heartbeatPeriod) {
        this.heartbeatPeriod = heartbeatPeriod;
        return this;
    }

    /**
     * Gets reconnectPeriod.
     *
     * @return the reconnectPeriod
     */
    public int getReconnectPeriod() {
        return reconnectPeriod;
    }

    /**
     * Sets reconnectPeriod.
     *
     * @param reconnectPeriod the reconnectPeriod
     * @return the reconnectPeriod
     */
    public ConsumerConfig<T> setReconnectPeriod(int reconnectPeriod) {
        this.reconnectPeriod = reconnectPeriod;
        return this;
    }

    /**
     * Gets router.
     *
     * @return the router
     */
    public List<String> getRouter() {
        return router;
    }

    /**
     * Sets router.
     *
     * @param router the router
     * @return the router
     */
    public ConsumerConfig setRouter(List<String> router) {
        this.router = router;
        return this;
    }

    /**
     * Gets routerRef.
     *
     * @return the routerRef
     */
    public List<Router> getRouterRef() {
        return routerRef;
    }

    /**
     * Sets routerRef.
     *
     * @param routerRef the routerRef
     * @return the routerRef
     */
    public ConsumerConfig<T> setRouterRef(List<Router> routerRef) {
        this.routerRef = routerRef;
        return this;
    }

    /**
     * Gets on return.
     *
     * @return the on return
     */
    public SofaResponseCallback getOnReturn() {
        return onReturn;
    }

    /**
     * Sets on return.
     *
     * @param onReturn the on return
     * @return the on return
     */
    public ConsumerConfig<T> setOnReturn(SofaResponseCallback onReturn) {
        this.onReturn = onReturn;
        return this;
    }

    /**
     * Gets on connect.
     *
     * @return the on connect
     */
    public List<ChannelListener> getOnConnect() {
        return onConnect;
    }

    /**
     * Sets on connect.
     *
     * @param onConnect the on connect
     * @return the on connect
     */
    public ConsumerConfig<T> setOnConnect(List<ChannelListener> onConnect) {
        this.onConnect = onConnect;
        return this;
    }

    /**
     * Gets on available.
     *
     * @return the on available
     */
    public List<ConsumerStateListener> getOnAvailable() {
        return onAvailable;
    }

    /**
     * Sets on available.
     *
     * @param onAvailable the on available
     * @return the on available
     */
    public ConsumerConfig<T> setOnAvailable(List<ConsumerStateListener> onAvailable) {
        this.onAvailable = onAvailable;
        return this;
    }

    /**
     * Gets timeout.
     *
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Sets timeout.
     *
     * @param timeout the timeout
     * @return the timeout
     */
    public ConsumerConfig<T> setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Gets concurrents.
     *
     * @return the concurrents
     */
    public int getConcurrents() {
        return concurrents;
    }

    /**
     * Sets concurrents.
     *
     * @param concurrents the concurrents
     * @return the concurrents
     */
    public ConsumerConfig<T> setConcurrents(int concurrents) {
        this.concurrents = concurrents;
        return this;
    }

    /**
     * Gets bootstrap.
     *
     * @return the bootstrap
     */
    public String getBootstrap() {
        return bootstrap;
    }

    /**
     * Sets bootstrap.
     *
     * @param bootstrap the bootstrap
     * @return the bootstrap
     */
    public ConsumerConfig<T> setBootstrap(String bootstrap) {
        this.bootstrap = bootstrap;
        return this;
    }

    /**
     * Gets address wait.
     *
     * @return the address wait
     */
    public int getAddressWait() {
        return addressWait;
    }

    /**
     * Sets address wait.
     *
     * @param addressWait the address wait
     * @return the address wait
     */
    public ConsumerConfig<T> setAddressWait(int addressWait) {
        this.addressWait = addressWait;
        return this;
    }

    /**
     * Gets max proxy count.
     *
     * @return the max proxy count
     */
    public int getRepeatedReferLimit() {
        return repeatedReferLimit;
    }

    /**
     * Sets max proxy count.
     *
     * @param repeatedReferLimit the max proxy count
     * @return the max proxy count
     */
    public ConsumerConfig<T> setRepeatedReferLimit(int repeatedReferLimit) {
        this.repeatedReferLimit = repeatedReferLimit;
        return this;
    }

    @Override
    public boolean hasTimeout() {
        if (timeout > 0) {
            return true;
        }
        if (CommonUtils.isNotEmpty(methods)) {
            for (MethodConfig methodConfig : methods.values()) {
                if (methodConfig.getTimeout() != null && methodConfig.getTimeout() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是�?�有并�?�控制需求，有就打开过滤器
     * �?置-1关闭并�?�过滤器，等于0表示开�?�过滤但是�?�?制
     *
     * @return 是�?��?置了concurrents boolean
     */
    @Override
    public boolean hasConcurrents() {
        if (concurrents > 0) {
            return true;
        }
        if (CommonUtils.isNotEmpty(methods)) {
            for (MethodConfig methodConfig : methods.values()) {
                if (methodConfig.getConcurrents() != null
                    && methodConfig.getConcurrents() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 得到方法的�?试次数，默认接�?��?置
     *
     * @param methodName 方法�??
     * @return 方法的�?试次数 method retries
     */
    public int getMethodRetries(String methodName) {
        return (Integer) getMethodConfigValue(methodName, RpcConstants.CONFIG_KEY_RETRIES,
            getRetries());
    }

    /**
     * Gets the timeout corresponding to the method name
     *
     * @param methodName the method name
     * @return the time out
     */
    public int getMethodTimeout(String methodName) {
        return (Integer) getMethodConfigValue(methodName, RpcConstants.CONFIG_KEY_TIMEOUT,
            getTimeout());
    }

    /**
     * 得到方法�??对应的自定义�?�数列表
     *
     * @param methodName 方法�??，�?支�?�?载
     * @return method onReturn
     */
    public SofaResponseCallback getMethodOnreturn(String methodName) {
        return (SofaResponseCallback) getMethodConfigValue(methodName, RpcConstants.CONFIG_KEY_ONRETURN,
            getOnReturn());
    }

    /**
     * Gets the call type corresponding to the method name
     *
     * @param methodName the method name
     * @return the call type
     */
    public String getMethodInvokeType(String methodName) {
        return (String) getMethodConfigValue(methodName, RpcConstants.CONFIG_KEY_INVOKE_TYPE,
            getInvokeType());
    }

    /**
     * 引用�?务
     *
     * @return �?务代�?�类 t
     */
    public T refer() {
        if (consumerBootstrap == null) {
            consumerBootstrap = Bootstraps.from(this);
        }
        return consumerBootstrap.refer();
    }

    /**
     * �?�消引用�?务
     */
    public void unRefer() {
        if (consumerBootstrap != null) {
            consumerBootstrap.unRefer();
        }
    }

    /**
     * 得到�?务消费这�?�动器
     *
     * @return �?务消费这�?�动器 consumer bootstrap
     */
    public ConsumerBootstrap<T> getConsumerBootstrap() {
        return consumerBootstrap;
    }

    /**
     * Sets serialization.
     *
     * @param serialization the serialization
     * @return the serialization
     */
    @Override
    public ConsumerConfig<T> setSerialization(String serialization) {
        this.serialization = serialization;
        return this;
    }

    /**
     * Gets provider info listener.
     *
     * @return the provider info listener
     */
    public ProviderInfoListener getProviderInfoListener() {
        return providerInfoListener;
    }

    /**
     * Sets provider info listener.
     *
     * @param providerInfoListener the provider info listener
     * @return the provider info listener
     */
    public ConsumerConfig<T> setProviderInfoListener(ProviderInfoListener providerInfoListener) {
        this.providerInfoListener = providerInfoListener;
        return this;
    }
}
