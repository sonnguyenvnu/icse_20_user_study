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
package com.alipay.sofa.rpc.bootstrap;

import com.alipay.sofa.rpc.client.ClientProxyInvoker;
import com.alipay.sofa.rpc.client.Cluster;
import com.alipay.sofa.rpc.codec.SerializerFactory;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.config.ConfigUniqueNameGenerator;
import com.alipay.sofa.rpc.context.BaggageResolver;
import com.alipay.sofa.rpc.context.RpcInternalContext;
import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.core.invoke.SendableResponseCallback;
import com.alipay.sofa.rpc.core.invoke.SofaResponseCallback;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.message.ResponseFuture;

import static com.alipay.sofa.rpc.common.RpcConstants.HIDDEN_KEY_INVOKE_CONTEXT;
import static com.alipay.sofa.rpc.common.RpcConstants.HIDDEN_KEY_PINPOINT;
import static com.alipay.sofa.rpc.common.RpcConstants.INTERNAL_KEY_APP_NAME;
import static com.alipay.sofa.rpc.common.RpcConstants.INTERNAL_KEY_PROTOCOL_NAME;
import static com.alipay.sofa.rpc.common.RpcConstants.INTERNAL_KEY_RESULT_CODE;

/**
 * 默认调用端代�?�执行器
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public class DefaultClientProxyInvoker extends ClientProxyInvoker {

    /**
     * 缓存接�?��??
     */
    protected String serviceName;

    /**
     * 缓存�?列化类型
     */
    protected Byte   serializeType;

    /**
     * 构造执行链
     *
     * @param bootstrap 调用端�?置
     */
    public DefaultClientProxyInvoker(ConsumerBootstrap bootstrap) {
        super(bootstrap);
        cacheCommonData();
    }

    protected void cacheCommonData() {
        // 缓存数�?�
        this.serviceName = ConfigUniqueNameGenerator.getServiceName(consumerConfig);
        this.serializeType = parseSerializeType(consumerConfig.getSerialization());
    }

    protected Byte parseSerializeType(String serialization) {
        Byte serializeType = SerializerFactory.getCodeByAlias(serialization);
        if (serializeType == null) {
            throw new SofaRpcRuntimeException("Unsupported serialization type: " + serialization);
        }
        return serializeType;
    }

    @Override
    protected void decorateRequest(SofaRequest request) {
        // 公共的设置
        super.decorateRequest(request);

        // 缓存是为了加快速度
        request.setTargetServiceUniqueName(serviceName);
        request.setSerializeType(serializeType == null ? 0 : serializeType);

        if (!consumerConfig.isGeneric()) {
            // 找到调用类型， generic的时候类型在filter里进行判断
            request.setInvokeType(consumerConfig.getMethodInvokeType(request.getMethodName()));
        }

        RpcInvokeContext invokeCtx = RpcInvokeContext.peekContext();
        RpcInternalContext internalContext = RpcInternalContext.getContext();
        if (invokeCtx != null) {
            // 如果用户设置了调用级别回调函数
            SofaResponseCallback responseCallback = invokeCtx.getResponseCallback();
            if (responseCallback != null) {
                request.setSofaResponseCallback(responseCallback);
                invokeCtx.setResponseCallback(null); // 一次性用完
                invokeCtx.put(RemotingConstants.INVOKE_CTX_IS_ASYNC_CHAIN,
                    isSendableResponseCallback(responseCallback));
            }
            // 如果用户设置了调用级别超时时间
            Integer timeout = invokeCtx.getTimeout();
            if (timeout != null) {
                request.setTimeout(timeout);
                invokeCtx.setTimeout(null);// 一次性用完
            }
            // 如果用户指定了调用的URL
            String targetURL = invokeCtx.getTargetURL();
            if (targetURL != null) {
                internalContext.setAttachment(HIDDEN_KEY_PINPOINT, targetURL);
                invokeCtx.setTargetURL(null);// 一次性用完
            }
            // 如果用户指定了�?传数�?�
            if (RpcInvokeContext.isBaggageEnable()) {
                // 需�?�?传
                BaggageResolver.carryWithRequest(invokeCtx, request);
                internalContext.setAttachment(HIDDEN_KEY_INVOKE_CONTEXT, invokeCtx);
            }
        }
        if (RpcInternalContext.isAttachmentEnable()) {
            internalContext.setAttachment(INTERNAL_KEY_APP_NAME, consumerConfig.getAppName());
            internalContext.setAttachment(INTERNAL_KEY_PROTOCOL_NAME, consumerConfig.getProtocol());
        }

        // �?外属性通过HEAD传递给�?务端
        request.addRequestProp(RemotingConstants.HEAD_APP_NAME, consumerConfig.getAppName());
        request.addRequestProp(RemotingConstants.HEAD_PROTOCOL, consumerConfig.getProtocol());
    }

    @Override
    protected void decorateResponse(SofaResponse response) {
        // 公共的设置
        super.decorateResponse(response);
        // 上下文内转外
        RpcInternalContext context = RpcInternalContext.getContext();
        ResponseFuture future = context.getFuture();
        RpcInvokeContext invokeCtx = null;
        if (future != null) {
            invokeCtx = RpcInvokeContext.getContext();
            invokeCtx.setFuture(future);
        }
        if (RpcInvokeContext.isBaggageEnable()) {
            BaggageResolver.pickupFromResponse(invokeCtx, response, true);
        }
        // bad code
        if (RpcInternalContext.isAttachmentEnable()) {
            String resultCode = (String) context.getAttachment(INTERNAL_KEY_RESULT_CODE);
            if (resultCode != null) {
                if (invokeCtx == null) {
                    invokeCtx = RpcInvokeContext.getContext();
                }
                invokeCtx.put(RemotingConstants.INVOKE_CTX_RPC_RESULT_CODE, resultCode);
            }
        }
    }

    /**
     * 是�?�是异步Callback，如果跨classloader下�?能直接使用instanceof
     *
     * @param callback SofaResponseCallback
     * @return 是�?�异步Callback
     */
    protected boolean isSendableResponseCallback(SofaResponseCallback callback) {
        return callback instanceof SendableResponseCallback;
    }

    @Override
    public Cluster setCluster(Cluster newCluster) {
        Cluster old = super.setCluster(newCluster);
        cacheCommonData();
        return old;
    }

    @Override
    public String toString() {
        return consumerConfig != null ? ConfigUniqueNameGenerator.getServiceName(consumerConfig) : super.toString();
    }
}
