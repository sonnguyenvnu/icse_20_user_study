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
package com.alipay.sofa.rpc.transport;

import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.common.SystemInfo;
import com.alipay.sofa.rpc.common.utils.NetUtils;
import com.alipay.sofa.rpc.context.RpcInternalContext;
import com.alipay.sofa.rpc.core.exception.RpcErrorType;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.event.ClientBeforeSendEvent;
import com.alipay.sofa.rpc.event.ClientSyncReceiveEvent;
import com.alipay.sofa.rpc.event.EventBus;
import com.alipay.sofa.rpc.message.ResponseFuture;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Abstract ProxyClientTransport for 3rd protocol, like cxf/resteasy.
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public abstract class AbstractProxyClientTransport extends ClientTransport {

    /**
     * 代�?�类，例如cxf或resteasy生�?的代�?�
     */
    private Object                   proxy;

    /**
     * 是�?�已连接（默认�?�连接，直到连�?上）
     */
    private boolean                  open;

    /**
     * 本地地�?�
     */
    protected InetSocketAddress      localAddress;

    /**
     * 远程地�?�
     */
    protected InetSocketAddress      remoteAddress;

    /**
     * 正在�?��?的调用数�?
     */
    protected volatile AtomicInteger currentRequests = new AtomicInteger(0);

    /**
     * 构造函数
     *
     * @param transportConfig 客户端�?置
     */
    public AbstractProxyClientTransport(ClientTransportConfig transportConfig) {
        super(transportConfig);
        ProviderInfo provider = transportConfig.getProviderInfo();
        try {
            proxy = buildProxy(transportConfig);
        } catch (Exception e) {
            throw new SofaRpcRuntimeException("Fail to build proxy client of consumer!", e);
        }
        // 能telnet通
        open = proxy != null && NetUtils.canTelnet(provider.getHost(), provider.getPort(),
            transportConfig.getConnectTimeout());
        remoteAddress = InetSocketAddress.createUnresolved(provider.getHost(), provider.getPort());
        localAddress = InetSocketAddress.createUnresolved(SystemInfo.getLocalHost(), 0);// 端�?��?准
    }

    /**
     * 构造远程调用代�?�
     *
     * @param transportConfig the transport config
     * @return the object
     * @throws SofaRpcException the exception
     */
    protected abstract Object buildProxy(ClientTransportConfig transportConfig) throws SofaRpcException;

    @Override
    public void connect() {
        ProviderInfo provider = transportConfig.getProviderInfo();
        open = NetUtils.canTelnet(provider.getHost(), provider.getPort(),
            transportConfig.getConnectTimeout());
    }

    @Override
    public void disconnect() {
        open = false;
    }

    @Override
    public void destroy() {
        disconnect();
    }

    @Override
    public boolean isAvailable() {
        return open;
    }

    @Override
    public void setChannel(AbstractChannel channel) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public AbstractChannel getChannel() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public int currentRequests() {
        return 0;
    }

    @Override
    public ResponseFuture asyncSend(SofaRequest message, int timeout) throws SofaRpcException {
        throw new UnsupportedOperationException("Unsupported asynchronous RPC in short connection");
    }

    /**
     * 构造远程调用代�?�
     *
     * @param request 请求dioxide
     * @return the object
     * @throws SofaRpcException the exception
     */
    protected abstract Method getMethod(SofaRequest request) throws SofaRpcException;

    @Override
    public SofaResponse syncSend(SofaRequest request, int timeout) throws SofaRpcException {
        RpcInternalContext context = RpcInternalContext.getContext();
        SofaResponse response = null;
        SofaRpcException throwable = null;
        try {
            beforeSend(context, request);
            if (EventBus.isEnable(ClientBeforeSendEvent.class)) {
                EventBus.post(new ClientBeforeSendEvent(request));
            }
            response = doInvokeSync(request, timeout);
            return response;
        } catch (InvocationTargetException e) {
            throwable = convertToRpcException(e);
            throw throwable;
        } catch (SofaRpcException e) {
            throwable = e;
            throw e;
        } catch (Exception e) {
            throwable = new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,
                "Failed to send message to remote", e);
            throw throwable;
        } finally {
            afterSend(context, request);
            if (EventBus.isEnable(ClientSyncReceiveEvent.class)) {
                EventBus.post(new ClientSyncReceiveEvent(transportConfig.getConsumerConfig(),
                    transportConfig.getProviderInfo(), request, response, throwable));
            }
        }
    }

    /**
     * �?�步调用
     *
     * @param request       请求对象
     * @param timeoutMillis 超时时间（毫秒）
     * @return 返回对象
     * @throws InvocationTargetException �??射调用异常
     * @since 5.2.0
     */
    protected SofaResponse doInvokeSync(SofaRequest request, int timeoutMillis)
        throws InvocationTargetException, IllegalAccessException {
        SofaResponse response = new SofaResponse();
        Method method = getMethod(request);
        if (method == null) {
            throw new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,
                "Not found method :" + request.getInterfaceName() + "." + request.getMethodName());
        }
        Object o = method.invoke(proxy, request.getMethodArgs());
        response.setAppResponse(o);
        return response;
    }

    /**
     * 调用�?设置一些属性
     *
     * @param context RPC上下文
     * @param request 请求对象
     */
    protected void beforeSend(RpcInternalContext context, SofaRequest request) {
        currentRequests.incrementAndGet();
        context.setLocalAddress(localAddress());
    }

    /**
     * 调用�?�设置一些属性（注�?，在异步的情况较多下）
     *
     * @param context RPC上下文
     * @param request 请求对象
     */
    protected void afterSend(RpcInternalContext context, SofaRequest request) {
        currentRequests.decrementAndGet();
    }

    /**
     * 转�?�调用出现的异常为RPC异常
     *
     * @param e 代�?�类包装的原因
     * @return RPC异常
     */
    protected SofaRpcException convertToRpcException(InvocationTargetException e) {
        SofaRpcException exception;
        Throwable ie = e.getCause(); // 包装的原因
        if (ie != null) {
            Throwable realException = ie.getCause(); // 真正的原因
            if (realException != null) {
                if (realException instanceof SocketTimeoutException) {
                    exception = new SofaRpcException(RpcErrorType.CLIENT_TIMEOUT, "Client read timeout!", realException);
                } else if (realException instanceof ConnectException) {
                    open = false;
                    exception = new SofaRpcException(RpcErrorType.CLIENT_NETWORK,
                        "Connect to remote " + transportConfig.getProviderInfo()
                            + " error!", realException);
                } else {
                    exception = new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,
                        "Send message to remote catch error: " + realException.getMessage(), realException);
                }
            } else {
                exception = new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,
                    "Send message to remote catch error: " + ie.getMessage(), ie);
            }
        } else {
            exception = new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,
                "Send message to remote catch error: " + e.getMessage(), e);
        }
        return exception;
    }

    @Override
    public void oneWaySend(SofaRequest message, int timeout) throws SofaRpcException {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void receiveRpcResponse(SofaResponse response) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void handleRpcRequest(SofaRequest request) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public InetSocketAddress remoteAddress() {
        return remoteAddress;
    }

    @Override
    public InetSocketAddress localAddress() {
        return localAddress;
    }
}
