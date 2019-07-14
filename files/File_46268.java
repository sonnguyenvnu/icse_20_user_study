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
package com.alipay.sofa.rpc.server.http;

import com.alipay.sofa.rpc.codec.Serializer;
import com.alipay.sofa.rpc.codec.SerializerFactory;
import com.alipay.sofa.rpc.codec.common.StringSerializer;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.cache.ReflectCache;
import com.alipay.sofa.rpc.common.utils.CodecUtils;
import com.alipay.sofa.rpc.common.utils.ExceptionUtils;
import com.alipay.sofa.rpc.config.ProviderConfig;
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
import com.alipay.sofa.rpc.server.AbstractTask;
import com.alipay.sofa.rpc.server.ProviderProxyInvoker;
import com.alipay.sofa.rpc.transport.AbstractByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 * @since 5.4.0
 */
public abstract class AbstractHttpServerTask extends AbstractTask {

    /**
     * Logger for Http2ServerTask
     **/
    private static final Logger           LOGGER = LoggerFactory.getLogger(Http2ServerTask.class);

    protected final SofaRequest           request;
    protected final ChannelHandlerContext ctx;
    protected final HttpServerHandler     serverHandler;

    public AbstractHttpServerTask(HttpServerHandler serverHandler, SofaRequest request, ChannelHandlerContext ctx) {
        this.serverHandler = serverHandler;
        this.request = request;
        this.ctx = ctx;
    }

    @Override
    public void run() {

        // RPC内置上下文
        RpcInternalContext context = RpcInternalContext.getContext();
        context.setProviderSide(true);

        String appName = request.getTargetAppName();
        if (appName == null) {
            // 默认全局appName
            appName = (String) RpcRuntimeContext.get(RpcRuntimeContext.KEY_APPNAME);
        }
        try { // 这个 try-finally 为了�?�?Context一定被清�?�
            Channel channel = ctx.channel();
            context.setRemoteAddress((InetSocketAddress) channel.remoteAddress()); // 远程地�?�
            context.setAttachment(RpcConstants.HIDDEN_KEY_ASYNC_CONTEXT, channel); // 远程返回的通�?�

            if (EventBus.isEnable(ServerReceiveEvent.class)) {
                EventBus.post(new ServerReceiveEvent(request));
            }

            // 开始处�?�
            SofaResponse response = null; // �?应，用于返回
            Throwable throwable = null; // 异常，用于记录
            HttpResponseStatus status = null;
            ProviderConfig providerConfig = null;
            String serviceName = request.getTargetServiceUniqueName();
            Serializer serializer = null;
            if (request.getSerializeType() > 0) {
                serializer = SerializerFactory.getSerializer(request.getSerializeType());
            }

            try { // 这个try-catch �?�?一定有Response
                invoke:
                {
                    // 查找�?务
                    Invoker invoker = serverHandler.getInvokerMap().get(serviceName);
                    if (invoker == null) {
                        throwable = cannotFoundService(appName, serviceName);
                        response = MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
                        status = HttpResponseStatus.NOT_FOUND;
                        break invoke;
                    }
                    if (invoker instanceof ProviderProxyInvoker) {
                        providerConfig = ((ProviderProxyInvoker) invoker).getProviderConfig();
                        // 找到�?务�?�，打�?��?务的appName
                        appName = providerConfig != null ? providerConfig.getAppName() : null;
                    }
                    // 查找方法
                    String methodName = request.getMethodName();
                    Method serviceMethod = serverHandler.getMethod(serviceName, methodName);
                    if (serviceMethod == null) {
                        throwable = cannotFoundServiceMethod(appName, methodName, serviceName);
                        response = MessageBuilder.buildSofaErrorResponse(throwable.getMessage());
                        status = HttpResponseStatus.NOT_FOUND;
                        break invoke;
                    } else {
                        request.setMethod(serviceMethod);
                    }

                    AbstractByteBuf reqData = request.getData();
                    if (reqData != null) {
                        try {
                            Map<String, String> map = new HashMap<String, String>(4);
                            map.put(RemotingConstants.HEAD_TARGET_SERVICE, request.getTargetServiceUniqueName());
                            map.put(RemotingConstants.HEAD_METHOD_NAME, request.getMethodName());
                            map.put(RemotingConstants.HEAD_TARGET_APP, request.getTargetAppName());

                            serializer.decode(reqData, request, map);
                        } catch (Exception e) {
                            LOGGER.errorWithApp(appName, "Server deserialize error, request from "
                                + channel.remoteAddress(), e);
                            response = MessageBuilder.buildSofaErrorResponse("Server deserialize error, "
                                + e.getMessage());
                            break invoke;
                        }
                    } else if (request.getMethodArgs() == null) {
                        request.setMethodArgs(CodecUtils.EMPTY_OBJECT_ARRAY);
                    }

                    // 真正调用
                    response = doInvoke(serviceName, invoker, request);
                }
            } catch (Exception e) {
                // �?务端异常，�?管是啥异常
                LOGGER.errorWithApp(appName, "Server Processor Error!", e);
                throwable = e;
                response = MessageBuilder.buildSofaErrorResponse(e.getMessage());
                status = HttpResponseStatus.INTERNAL_SERVER_ERROR;
            }

            // Response�?为空，代表需�?返回给客户端
            if (response != null) {
                response.setSerializeType(request.getSerializeType());
                // 其它正常请求
                try { // 这个try-catch �?�?一定�?记录tracer
                    if (response.isError()) {
                        ByteBuf content = ctx.alloc().buffer();
                        content.writeBytes(StringSerializer.encode(response.getErrorMsg()));
                        sendRpcError(status == null ? HttpResponseStatus.INTERNAL_SERVER_ERROR : status, content);
                    } else {
                        if (response.getAppResponse() instanceof Throwable) {
                            ByteBuf content = ctx.alloc().buffer();
                            String errorMsg = ExceptionUtils.toString((Throwable) response.getAppResponse());
                            content.writeBytes(StringSerializer.encode(errorMsg));
                            sendAppError(HttpResponseStatus.OK, content);
                        } else {
                            ByteBuf content = ctx.alloc().buffer();
                            if (request.getSerializeType() > 0) {
                                AbstractByteBuf bs = serializer.encode(response, null);
                                content.writeBytes(bs.array());
                            } else {
                                content.writeBytes(StringSerializer.encode(response.getAppResponse().toString()));
                            }
                            sendAppResponse(HttpResponseStatus.OK, content);
                        }
                    }
                } finally {
                    if (EventBus.isEnable(ServerSendEvent.class)) {
                        EventBus.post(new ServerSendEvent(request, response, throwable));
                    }
                }
            }
        } catch (Throwable e) {
            // �?�能有返回时的异常
            if (LOGGER.isErrorEnabled(appName)) {
                LOGGER.errorWithApp(appName, e.getMessage(), e);
            }
        } finally {
            serverHandler.getProcessingCount().decrementAndGet();
            if (EventBus.isEnable(ServerEndHandleEvent.class)) {
                EventBus.post(new ServerEndHandleEvent());
            }
            RpcInvokeContext.removeContext();
            RpcInternalContext.removeAllContext();
        }
    }

    protected SofaResponse doInvoke(String serviceName, Invoker invoker, SofaRequest request) throws SofaRpcException {
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

    /**
     * 返回应用�?应
     *
     * @param status 返回状�?，一般是200
     * @param data   数�?�
     */
    protected abstract void sendAppResponse(HttpResponseStatus status, ByteBuf data);

    /**
     * 返回应用异常（头上带上 error=true）
     *
     * @param status 返回状�?，一般是200
     * @param data   数�?�
     */
    protected abstract void sendAppError(HttpResponseStatus status, ByteBuf data);

    /**
     * 返回框架异常（头上带上 error=true）
     *
     * @param status 返回状�?，一般是500
     * @param data   数�?�
     */
    protected abstract void sendRpcError(HttpResponseStatus status, ByteBuf data);

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
            LogCodes.ERROR_PROVIDER_SERVICE_METHOD_CANNOT_FOUND, methodName, serviceName);
        LOGGER.errorWithApp(appName, errorMsg);
        return new SofaRpcException(RpcErrorType.SERVER_NOT_FOUND_INVOKER, errorMsg);
    }
}
