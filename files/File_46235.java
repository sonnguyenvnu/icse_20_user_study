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

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.InvokeContext;
import com.alipay.remoting.rpc.protocol.AsyncUserProcessor;
import com.alipay.remoting.rpc.protocol.UserProcessor;
import com.alipay.sofa.rpc.codec.bolt.SofaRpcSerializationRegister;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.SystemInfo;
import com.alipay.sofa.rpc.common.cache.ReflectCache;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.UserThreadPoolManager;
import com.alipay.sofa.rpc.context.RpcInternalContext;
import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.core.exception.RpcErrorType;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.event.EventBus;
import com.alipay.sofa.rpc.event.ServerEndHandleEvent;
import com.alipay.sofa.rpc.event.ServerReceiveEvent;
import com.alipay.sofa.rpc.event.ServerSendEvent;
import com.alipay.sofa.rpc.invoke.Invoker;
import com.alipay.sofa.rpc.log.LogCodes;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.message.MessageBuilder;
import com.alipay.sofa.rpc.server.ProviderProxyInvoker;
import com.alipay.sofa.rpc.server.UserThreadPool;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bolt server processor of bolt server.
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public class BoltServerProcessor extends AsyncUserProcessor<SofaRequest> {

    /**
     * Logger for this class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BoltServerProcessor.class);

    /**
     * �??�?注册�?列化器
     */
    static {
        SofaRpcSerializationRegister.registerCustomSerializer();
    }

    /**
     * bolt server, which saved invoker map
     */
    private final BoltServer    boltServer;

    /**
     * Construct
     *
     * @param boltServer 所在的Server
     */
    public BoltServerProcessor(BoltServer boltServer) {
        this.boltServer = boltServer;
        this.executorSelector = new UserThreadPoolSelector(); // 支�?自定义业务线程池
    }

    /**
     * 当�?Client正在�?��?的调用数�?
     */
    AtomicInteger processingCount = new AtomicInteger(0);

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, SofaRequest request) {
        // RPC内置上下文
        RpcInternalContext context = RpcInternalContext.getContext();
        context.setProviderSide(true);

        String appName = request.getTargetAppName();
        if (appName == null) {
            // 默认全局appName
            appName = (String) RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPNAME);
        }

        // 是�?�链路异步化中
        boolean isAsyncChain = false;
        try { // 这个 try-finally 为了�?�?Context一定被清�?�
            processingCount.incrementAndGet(); // 统计值加1

            context.setRemoteAddress(bizCtx.getRemoteHost(), bizCtx.getRemotePort()); // 远程地�?�
            context.setAttachment(RpcConstants.HIDDEN_KEY_ASYNC_CONTEXT, asyncCtx); // 远程返回的通�?�

            if (RpcInternalContext.isAttachmentEnable()) {
                InvokeContext boltInvokeCtx = bizCtx.getInvokeContext();
                if (boltInvokeCtx != null) {
                    putToContextIfNotNull(boltInvokeCtx, InvokeContext.BOLT_PROCESS_WAIT_TIME,
                        context, RpcConstants.INTERNAL_KEY_PROCESS_WAIT_TIME); // rpc线程池等待时间 Long
                }
            }
            if (EventBus.isEnable(ServerReceiveEvent.class)) {
                EventBus.post(new ServerReceiveEvent(request));
            }

            // 开始处�?�
            SofaResponse response = null; // �?应，用于返回
            Throwable throwable = null; // 异常，用于记录
            ProviderConfig providerConfig = null;
            String serviceName = request.getTargetServiceUniqueName();

            try { // 这个try-catch �?�?一定有Response
                invoke:
                {
                    if (!boltServer.isStarted()) { // �?务端已关闭
                        throwable = new SofaRpcException(RpcErrorType.SERVER_CLOSED, LogCodes.getLog(
                            LogCodes.WARN_PROVIDER_STOPPED, SystemInfo.getLocalHost() + ":" +
                                boltServer.serverConfig.getPort()));
                        response = MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
                        break invoke;
                    }
                    if (bizCtx.isRequestTimeout()) { // 加上丢弃超时的请求的逻辑
                        throwable = clientTimeoutWhenReceiveRequest(appName, serviceName, bizCtx.getRemoteAddress());
                        break invoke;
                    }
                    // 查找�?务
                    Invoker invoker = boltServer.findInvoker(serviceName);
                    if (invoker == null) {
                        throwable = cannotFoundService(appName, serviceName);
                        response = MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
                        break invoke;
                    }
                    if (invoker instanceof ProviderProxyInvoker) {
                        providerConfig = ((ProviderProxyInvoker) invoker).getProviderConfig();
                        // 找到�?务�?�，打�?��?务的appName
                        appName = providerConfig != null ? providerConfig.getAppName() : null;
                    }
                    // 查找方法
                    String methodName = request.getMethodName();
                    Method serviceMethod = ReflectCache.getOverloadMethodCache(serviceName, methodName,
                        request.getMethodArgSigs());
                    if (serviceMethod == null) {
                        throwable = cannotFoundServiceMethod(appName, methodName, serviceName);
                        response = MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
                        break invoke;
                    } else {
                        request.setMethod(serviceMethod);
                    }

                    // 真正调用
                    response = doInvoke(serviceName, invoker, request);

                    if (bizCtx.isRequestTimeout()) { // 加上丢弃超时的�?应的逻辑
                        throwable = clientTimeoutWhenSendResponse(appName, serviceName, bizCtx.getRemoteAddress());
                        break invoke;
                    }
                }
            } catch (Exception e) {
                // �?务端异常，�?管是啥异常
                LOGGER.errorWithApp(appName, "Server Processor Error!", e);
                throwable = e;
                response = MessageBuilder.buildSofaErrorResponse(e.getMessage());
            }

            // Response�?为空，代表需�?返回给客户端
            if (response != null) {
                RpcInvokeContext invokeContext = RpcInvokeContext.peekContext();
                isAsyncChain = CommonUtils.isTrue(invokeContext != null ?
                    (Boolean) invokeContext.remove(RemotingConstants.INVOKE_CTX_IS_ASYNC_CHAIN) : null);
                // 如果是�?务端异步代�?�模�?，特殊处�?�，因为该模�?是在业务代�?自主异步返回的
                if (!isAsyncChain) {
                    // 其它正常请求
                    try { // 这个try-catch �?�?一定�?记录tracer
                        asyncCtx.sendResponse(response);
                    } finally {
                        if (EventBus.isEnable(ServerSendEvent.class)) {
                            EventBus.post(new ServerSendEvent(request, response, throwable));
                        }
                    }
                }
            }
        } catch (Throwable e) {
            // �?�能有返回时的异常
            if (LOGGER.isErrorEnabled(appName)) {
                LOGGER.errorWithApp(appName, e.getMessage(), e);
            }
        } finally {
            processingCount.decrementAndGet();
            if (!isAsyncChain) {
                if (EventBus.isEnable(ServerEndHandleEvent.class)) {
                    EventBus.post(new ServerEndHandleEvent());
                }
            }
            RpcInvokeContext.removeContext();
            RpcInternalContext.removeAllContext();
        }
    }

    private SofaResponse doInvoke(String serviceName, Invoker invoker, SofaRequest request) throws SofaRpcException {
        // 开始调用，先记下当�?的ClassLoader
        ClassLoader rpcCl = Thread.currentThread().getContextClassLoader();
        try {
            // 切�?�线程的ClassLoader到 �?务 自己的ClassLoader
            ClassLoader serviceCl = ReflectCache.getServiceClassLoader(serviceName);
            Thread.currentThread().setContextClassLoader(serviceCl);
            return invoker.invoke(request);
        } finally {
            Thread.currentThread().setContextClassLoader(rpcCl);
        }
    }

    private void putToContextIfNotNull(InvokeContext invokeContext, String oldKey,
                                       RpcInternalContext context, String key) {
        Object value = invokeContext.get(oldKey);
        if (value != null) {
            context.setAttachment(key, value);
        }
    }

    /**
     * 找�?到�?务
     *
     * @param appName     应用
     * @param serviceName �?务
     * @return 找�?到�?务的异常�?应
     */
    private SofaRpcException cannotFoundService(String appName, String serviceName) {
        String errorMsg = LogCodes
            .getLog(LogCodes.ERROR_PROVIDER_SERVICE_CANNOT_FOUND, serviceName);
        LOGGER.errorWithApp(appName, errorMsg);
        return new SofaRpcException(RpcErrorType.SERVER_NOT_FOUND_INVOKER, errorMsg);
    }

    /**
     * 找�?到�?务方法
     *
     * @param appName     应用
     * @param serviceName �?务
     * @param methodName  方法�??
     * @return 找�?到�?务方法的异常
     */
    private SofaRpcException cannotFoundServiceMethod(String appName, String serviceName, String methodName) {
        String errorMsg = LogCodes.getLog(
            LogCodes.ERROR_PROVIDER_SERVICE_METHOD_CANNOT_FOUND, serviceName, methodName);
        LOGGER.errorWithApp(appName, errorMsg);
        return new SofaRpcException(RpcErrorType.SERVER_NOT_FOUND_INVOKER, errorMsg);
    }

    /**
     * 客户端已�?超时了（例如在队列里等待太久了），丢弃这个请求
     *
     * @param appName       应用
     * @param serviceName   �?务
     * @param remoteAddress 远程地�?�
     * @return 丢弃的异常
     */
    private SofaRpcException clientTimeoutWhenReceiveRequest(String appName, String serviceName, String remoteAddress) {
        String errorMsg = LogCodes.getLog(
            LogCodes.ERROR_DISCARD_TIMEOUT_REQUEST, serviceName, remoteAddress);
        if (LOGGER.isWarnEnabled(appName)) {
            LOGGER.warnWithApp(appName, errorMsg);
        }
        return new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR, errorMsg);
    }

    /**
     * 客户端已�?超时了（例如在业务执行时间太长），丢弃这个返回值
     *
     * @param appName       应用
     * @param serviceName   �?务
     * @param remoteAddress 远程地�?�
     * @return 丢弃的异常
     */
    private SofaRpcException clientTimeoutWhenSendResponse(String appName, String serviceName, String remoteAddress) {
        String errorMsg = LogCodes.getLog(
            LogCodes.ERROR_DISCARD_TIMEOUT_RESPONSE, serviceName, remoteAddress);
        if (LOGGER.isWarnEnabled(appName)) {
            LOGGER.warnWithApp(appName, errorMsg);
        }
        return new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR, errorMsg);
    }

    @Override
    public String interest() {
        return SofaRequest.class.getName();
    }

    @Override
    public Executor getExecutor() {
        return boltServer.getBizThreadPool();
    }

    @Override
    public ExecutorSelector getExecutorSelector() {
        return UserThreadPoolManager.hasUserThread() ? executorSelector : null;
    }

    /**
     * Executor Selector
     *
     * @author zhanggeng
     * @since 4.10.0
     */
    public class UserThreadPoolSelector implements UserProcessor.ExecutorSelector {

        @Override
        public Executor select(String requestClass, Object requestHeader) {
            if (SofaRequest.class.getName().equals(requestClass)
                && requestHeader != null) {
                Map<String, String> headerMap = (Map<String, String>) requestHeader;
                try {
                    String service = headerMap.get(RemotingConstants.HEAD_SERVICE);
                    if (service == null) {
                        service = headerMap.get(RemotingConstants.HEAD_TARGET_SERVICE);
                    }
                    if (service != null) {
                        UserThreadPool threadPool = UserThreadPoolManager.getUserThread(service);
                        if (threadPool != null) {
                            Executor executor = threadPool.getExecutor();
                            if (executor != null) {
                                // 存在自定义线程池，且�?为空
                                return executor;
                            }
                        }
                    }
                } catch (Exception e) {
                    if (LOGGER.isWarnEnabled()) {
                        LOGGER.warn(LogCodes.getLog(LogCodes.WARN_DESERIALIZE_HEADER_ERROR), e);
                    }
                }
            }
            return getExecutor();
        }
    }

    @Override
    public boolean timeoutDiscard() {
        final Map<String, String> parameters = boltServer.serverConfig.getParameters();
        if (CommonUtils.isEmpty(parameters)) {
            return false;
        }
        String timeoutDiscard = parameters.get(RpcConstants.TIMEOUT_DISCARD_IN_SERVER);
        return Boolean.parseBoolean(parameters.get(timeoutDiscard));
    }

    @Override
    public boolean processInIOThread() {
        final Map<String, String> parameters = boltServer.serverConfig.getParameters();
        if (CommonUtils.isEmpty(parameters)) {
            return false;
        }
        String processInIOThread = parameters.get(RpcConstants.PROCESS_IN_IOTHREAD);
        return Boolean.parseBoolean(parameters.get(processInIOThread));
    }
}
