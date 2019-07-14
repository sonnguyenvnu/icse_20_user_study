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
package com.alipay.sofa.rpc.codec.bolt;

import java.util.HashMap;
import java.util.Map;

import com.alipay.remoting.DefaultCustomSerializer;
import com.alipay.remoting.InvokeContext;
import com.alipay.remoting.exception.DeserializationException;
import com.alipay.remoting.exception.SerializationException;
import com.alipay.remoting.rpc.RequestCommand;
import com.alipay.remoting.rpc.ResponseCommand;
import com.alipay.remoting.rpc.protocol.RpcProtocol;
import com.alipay.remoting.rpc.protocol.RpcRequestCommand;
import com.alipay.remoting.rpc.protocol.RpcResponseCommand;
import com.alipay.sofa.rpc.codec.Serializer;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.cache.ReflectCache;
import com.alipay.sofa.rpc.common.utils.ClassUtils;
import com.alipay.sofa.rpc.common.utils.CodecUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.context.RpcInternalContext;
import com.alipay.sofa.rpc.core.request.RequestBase;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.transport.AbstractByteBuf;
import com.alipay.sofa.rpc.transport.ByteArrayWrapperByteBuf;

/**
 * Sofa RPC BOLT �??议的对象�?列化/�??�?列化自定义类
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 * @author <a href=mailto:hongwei.yhw@antfin.com>HongWei Yi</a>
 */
public class SofaRpcSerialization extends DefaultCustomSerializer {

    protected SimpleMapSerializer mapSerializer;

    public SofaRpcSerialization() {
        init();
    }

    /**
     * Init this custom serializer
     */
    protected void init() {
        mapSerializer = new SimpleMapSerializer();
    }

    @Override
    public <Response extends ResponseCommand> boolean serializeHeader(Response response)
        throws SerializationException {
        if (response instanceof RpcResponseCommand) {
            RpcInternalContext.getContext().getStopWatch().tick();

            Object responseObject = ((RpcResponseCommand) response).getResponseObject();
            if (responseObject instanceof SofaResponse) {
                SofaResponse sofaResponse = (SofaResponse) responseObject;
                if (sofaResponse.isError() || sofaResponse.getAppResponse() instanceof Throwable) {
                    sofaResponse.addResponseProp(RemotingConstants.HEAD_RESPONSE_ERROR, StringUtils.TRUE);
                }
                response.setHeader(mapSerializer.encode(sofaResponse.getResponseProps()));
            }
            return true;
        }
        return false;
    }

    @Override
    public <Request extends RequestCommand> boolean serializeHeader(Request request, InvokeContext invokeContext)
        throws SerializationException {
        if (request instanceof RpcRequestCommand) {
            RpcInternalContext.getContext().getStopWatch().tick();

            RpcRequestCommand requestCommand = (RpcRequestCommand) request;
            Object requestObject = requestCommand.getRequestObject();
            String service = getTargetServiceName(requestObject);
            if (StringUtils.isNotEmpty(service)) {
                Map<String, String> header = new HashMap<String, String>(16);
                header.put(RemotingConstants.HEAD_SERVICE, service);
                putRequestMetadataToHeader(requestObject, header);
                requestCommand.setHeader(mapSerializer.encode(header));
            }
            return true;
        }
        return false;
    }

    protected void putRequestMetadataToHeader(Object requestObject, Map<String, String> header) {
        if (requestObject instanceof RequestBase) {
            RequestBase requestBase = (RequestBase) requestObject;
            header.put(RemotingConstants.HEAD_METHOD_NAME, requestBase.getMethodName());
            header.put(RemotingConstants.HEAD_TARGET_SERVICE, requestBase.getTargetServiceUniqueName());

            if (requestBase instanceof SofaRequest) {
                SofaRequest sofaRequest = (SofaRequest) requestBase;
                header.put(RemotingConstants.HEAD_TARGET_APP, sofaRequest.getTargetAppName());
                Map<String, Object> requestProps = sofaRequest.getRequestProps();
                if (requestProps != null) {
                    // <String, Object> 转�?平化 <String, String>
                    CodecUtils.flatCopyTo("", requestProps, header);
                }
            }
        }
    }

    /**
     * Get target service name from request
     *
     * @param request Request object
     * @return service name
     */
    protected String getTargetServiceName(Object request) {
        if (request instanceof RequestBase) {
            RequestBase requestBase = (RequestBase) request;
            return requestBase.getTargetServiceUniqueName();
        }

        return null;
    }

    @Override
    public <Request extends RequestCommand> boolean deserializeHeader(Request request)
        throws DeserializationException {
        if (request instanceof RpcRequestCommand) {
            RpcInternalContext.getContext().getStopWatch().tick();

            RpcRequestCommand requestCommand = (RpcRequestCommand) request;
            if (requestCommand.getRequestHeader() != null) {
                // 代表已�?�??�?解�?过了，例如使用自定义业务线程池的时候，bolt会�??�?解�?�?�长Header的数�?�
                return true;
            }
            byte[] header = requestCommand.getHeader();
            // 解�?头部
            Map<String, String> headerMap = mapSerializer.decode(header);
            requestCommand.setRequestHeader(headerMap);

            return true;
        }
        return false;
    }

    @Override
    public <Response extends ResponseCommand> boolean deserializeHeader(Response response, InvokeContext invokeContext)
        throws DeserializationException {
        if (response instanceof RpcResponseCommand) {
            RpcInternalContext.getContext().getStopWatch().tick();

            RpcResponseCommand responseCommand = (RpcResponseCommand) response;
            byte[] header = responseCommand.getHeader();
            responseCommand.setResponseHeader(mapSerializer.decode(header));
            return true;
        }
        return false;
    }

    @Override
    public <Request extends RequestCommand> boolean serializeContent(Request request, InvokeContext invokeContext)
        throws SerializationException {
        if (request instanceof RpcRequestCommand) {
            RpcRequestCommand requestCommand = (RpcRequestCommand) request;
            Object requestObject = requestCommand.getRequestObject();
            byte serializerCode = requestCommand.getSerializer();
            try {
                Map<String, String> header = (Map<String, String>) requestCommand.getRequestHeader();
                if (header == null) {
                    header = new HashMap<String, String>();
                }
                putKV(header, RemotingConstants.HEAD_GENERIC_TYPE,
                    (String) invokeContext.get(RemotingConstants.HEAD_GENERIC_TYPE));

                Serializer rpcSerializer = com.alipay.sofa.rpc.codec.SerializerFactory
                    .getSerializer(serializerCode);
                AbstractByteBuf byteBuf = rpcSerializer.encode(requestObject, header);
                request.setContent(byteBuf.array());
                return true;
            } catch (Exception ex) {
                throw new SerializationException(ex.getMessage(), ex);
            } finally {
                recordSerializeRequest(requestCommand, invokeContext);
            }
        }
        return false;
    }

    /**
     * 客户端记录�?列化请求的耗时和
     *
     * @param requestCommand 请求对象
     */
    protected void recordSerializeRequest(RequestCommand requestCommand, InvokeContext invokeContext) {
        if (!RpcInternalContext.isAttachmentEnable()) {
            return;
        }
        RpcInternalContext context = null;
        if (invokeContext != null) {
            // 客户端异步调用的情况下，上下文会放在InvokeContext中传递
            context = invokeContext.get(RemotingConstants.INVOKE_CTX_RPC_CTX);
        }
        if (context == null) {
            context = RpcInternalContext.getContext();
        }
        int cost = context.getStopWatch().tick().read();
        int requestSize = RpcProtocol.getRequestHeaderLength()
            + requestCommand.getClazzLength()
            + requestCommand.getContentLength()
            + requestCommand.getHeaderLength();
        // 记录请求�?列化大�?和请求�?列化耗时
        context.setAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE, requestSize);
        context.setAttachment(RpcConstants.INTERNAL_KEY_REQ_SERIALIZE_TIME, cost);
    }

    @Override
    public <Request extends RequestCommand> boolean deserializeContent(Request request)
        throws DeserializationException {
        if (request instanceof RpcRequestCommand) {
            RpcRequestCommand requestCommand = (RpcRequestCommand) request;
            Object header = requestCommand.getRequestHeader();
            if (!(header instanceof Map)) {
                throw new DeserializationException("Head of request is null or is not map");
            }
            Map<String, String> headerMap = (Map<String, String>) header;
            byte[] content = requestCommand.getContent();
            if (content == null || content.length == 0) {
                throw new DeserializationException("Content of request is null");
            }
            try {
                String service = headerMap.get(RemotingConstants.HEAD_SERVICE);
                ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

                ClassLoader serviceClassLoader = ReflectCache.getServiceClassLoader(service);
                try {
                    Thread.currentThread().setContextClassLoader(serviceClassLoader);

                    Serializer rpcSerializer = com.alipay.sofa.rpc.codec.SerializerFactory
                        .getSerializer(requestCommand.getSerializer());
                    Object sofaRequest = ClassUtils.forName(requestCommand.getRequestClass()).newInstance();
                    rpcSerializer.decode(new ByteArrayWrapperByteBuf(requestCommand.getContent()),
                        sofaRequest, headerMap);
                    requestCommand.setRequestObject(sofaRequest);
                } finally {
                    Thread.currentThread().setContextClassLoader(oldClassLoader);
                }

                return true;
            } catch (Exception ex) {
                throw new DeserializationException(ex.getMessage(), ex);
            } finally {
                recordDeserializeRequest(requestCommand);
            }
        }
        return false;
    }

    /**
     * �?务端记录�??�?列化请求的大�?和耗时
     *
     * @param requestCommand 请求对象
     */
    private void recordDeserializeRequest(RequestCommand requestCommand) {
        if (!RpcInternalContext.isAttachmentEnable()) {
            return;
        }
        RpcInternalContext context = RpcInternalContext.getContext();
        int cost = context.getStopWatch().tick().read();
        int requestSize = RpcProtocol.getRequestHeaderLength()
            + requestCommand.getClazzLength()
            + requestCommand.getContentLength()
            + requestCommand.getHeaderLength();
        // 记录请求�??�?列化大�?和请求�??�?列化耗时
        context.setAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE, requestSize);
        context.setAttachment(RpcConstants.INTERNAL_KEY_REQ_DESERIALIZE_TIME, cost);
    }

    @Override
    public <Response extends ResponseCommand> boolean serializeContent(Response response)
        throws SerializationException {
        if (response instanceof RpcResponseCommand) {
            RpcResponseCommand responseCommand = (RpcResponseCommand) response;
            byte serializerCode = response.getSerializer();
            try {
                Serializer rpcSerializer = com.alipay.sofa.rpc.codec.SerializerFactory.getSerializer(serializerCode);
                AbstractByteBuf byteBuf = rpcSerializer.encode(responseCommand.getResponseObject(), null);
                responseCommand.setContent(byteBuf.array());
                return true;
            } catch (Exception ex) {
                throw new SerializationException(ex.getMessage(), ex);
            } finally {
                recordSerializeResponse(responseCommand);
            }
        }
        return false;
    }

    /**
     * �?务端记录�?列化�?应的大�?和耗时
     *
     * @param responseCommand �?应体
     */
    private void recordSerializeResponse(RpcResponseCommand responseCommand) {
        if (!RpcInternalContext.isAttachmentEnable()) {
            return;
        }
        RpcInternalContext context = RpcInternalContext.getContext();
        int cost = context.getStopWatch().tick().read();
        int respSize = RpcProtocol.getResponseHeaderLength()
            + responseCommand.getClazzLength()
            + responseCommand.getContentLength()
            + responseCommand.getHeaderLength();
        // 记录�?应�?列化大�?和请求�?列化耗时
        context.setAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE, respSize);
        context.setAttachment(RpcConstants.INTERNAL_KEY_RESP_SERIALIZE_TIME, cost);
    }

    @Override
    public <Response extends ResponseCommand> boolean deserializeContent(Response response, InvokeContext invokeContext)
        throws DeserializationException {
        if (response instanceof RpcResponseCommand) {
            RpcResponseCommand responseCommand = (RpcResponseCommand) response;
            byte serializer = response.getSerializer();
            byte[] content = responseCommand.getContent();
            if (content == null || content.length == 0) {
                return false;
            }
            try {
                Object sofaResponse = ClassUtils.forName(responseCommand.getResponseClass()).newInstance();

                Map<String, String> header = (Map<String, String>) responseCommand.getResponseHeader();
                if (header == null) {
                    header = new HashMap<String, String>();
                }
                putKV(header, RemotingConstants.HEAD_TARGET_SERVICE,
                    (String) invokeContext.get(RemotingConstants.HEAD_TARGET_SERVICE));
                putKV(header, RemotingConstants.HEAD_METHOD_NAME,
                    (String) invokeContext.get(RemotingConstants.HEAD_METHOD_NAME));
                putKV(header, RemotingConstants.HEAD_GENERIC_TYPE,
                    (String) invokeContext.get(RemotingConstants.HEAD_GENERIC_TYPE));

                Serializer rpcSerializer = com.alipay.sofa.rpc.codec.SerializerFactory.getSerializer(serializer);
                rpcSerializer.decode(new ByteArrayWrapperByteBuf(responseCommand.getContent()), sofaResponse, header);

                responseCommand.setResponseObject(sofaResponse);
                return true;
            } catch (Exception ex) {
                throw new DeserializationException(ex.getMessage(), ex);
            } finally {
                recordDeserializeResponse(responseCommand, invokeContext);
            }
        }

        return false;
    }

    protected void putKV(Map<String, String> map, String key, String value) {
        if (map != null && key != null && value != null) {
            map.put(key, value);
        }
    }

    /**
     * 客户端记录�?应�??�?列化大�?和�?应�??�?列化耗时
     *
     * @param responseCommand �?应体
     */
    private void recordDeserializeResponse(RpcResponseCommand responseCommand, InvokeContext invokeContext) {
        if (!RpcInternalContext.isAttachmentEnable()) {
            return;
        }
        RpcInternalContext context = null;
        if (invokeContext != null) {
            // 客户端异步调用的情况下，上下文会放在InvokeContext中传递
            context = invokeContext.get(RemotingConstants.INVOKE_CTX_RPC_CTX);
        }
        if (context == null) {
            context = RpcInternalContext.getContext();
        }
        int cost = context.getStopWatch().tick().read();
        int respSize = RpcProtocol.getResponseHeaderLength()
            + responseCommand.getClazzLength()
            + responseCommand.getContentLength()
            + responseCommand.getHeaderLength();
        // 记录�?应�??�?列化大�?和�?应�??�?列化耗时
        context.setAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE, respSize);
        context.setAttachment(RpcConstants.INTERNAL_KEY_RESP_DESERIALIZE_TIME, cost);
    }
}
