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
package com.alipay.sofa.rpc.tracer.sofatracer;

import com.alipay.common.tracer.core.SofaTracer;
import com.alipay.common.tracer.core.appender.encoder.SpanEncoder;
import com.alipay.common.tracer.core.appender.self.SelfLog;
import com.alipay.common.tracer.core.configuration.SofaTracerConfiguration;
import com.alipay.common.tracer.core.context.span.SofaTracerSpanContext;
import com.alipay.common.tracer.core.context.trace.SofaTraceContext;
import com.alipay.common.tracer.core.holder.SofaTraceContextHolder;
import com.alipay.common.tracer.core.reporter.facade.Reporter;
import com.alipay.common.tracer.core.reporter.stat.SofaTracerStatisticReporter;
import com.alipay.common.tracer.core.span.LogData;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.TracerCompatibleConstants;
import com.alipay.sofa.rpc.common.utils.ExceptionUtils;
import com.alipay.sofa.rpc.common.utils.NetUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.context.RpcInternalContext;
import com.alipay.sofa.rpc.core.exception.RpcErrorType;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.tracer.Tracer;
import com.alipay.sofa.rpc.tracer.sofatracer.code.TracerResultCode;
import com.alipay.sofa.rpc.tracer.sofatracer.factory.ReporterFactory;
import com.alipay.sofa.rpc.tracer.sofatracer.log.digest.RpcClientDigestSpanJsonEncoder;
import com.alipay.sofa.rpc.tracer.sofatracer.log.digest.RpcServerDigestSpanJsonEncoder;
import com.alipay.sofa.rpc.tracer.sofatracer.log.stat.RpcClientStatJsonReporter;
import com.alipay.sofa.rpc.tracer.sofatracer.log.stat.RpcServerStatJsonReporter;
import com.alipay.sofa.rpc.tracer.sofatracer.log.tags.RpcSpanTags;
import com.alipay.sofa.rpc.tracer.sofatracer.log.type.RpcTracerLogEnum;
import io.opentracing.tag.Tags;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * SofaTracer
 *
 * @author <a href=mailto:guanchao.ygc@antfin.com>GuanChao Yang</a>
 */
@Extension("sofaTracer")
public class RpcSofaTracer extends Tracer {

    /***
     * tracer 类型
     */
    public static final String RPC_TRACER_TYPE = "RPC_TRACER";

    /***
     * �?�生错误时用于标示错误�?信�?�
     */
    public static final String ERROR_SOURCE    = "rpc";

    private SofaTracer         sofaTracer;

    public RpcSofaTracer() {
        //构造 client 的日志打�?�实例
        SpanEncoder<SofaTracerSpan> clientEncoder = getClientSpanEncoder();
        SofaTracerStatisticReporter clientStats = generateClientStatReporter(RpcTracerLogEnum.RPC_CLIENT_STAT);
        Reporter clientReporter = generateReporter(clientStats, RpcTracerLogEnum.RPC_CLIENT_DIGEST, clientEncoder);

        //构造 server 的日志打�?�实例
        SpanEncoder<SofaTracerSpan> serverEncoder = getServerSpanEncoder();
        SofaTracerStatisticReporter serverStats = generateServerStatReporter(RpcTracerLogEnum.RPC_SERVER_STAT);
        Reporter serverReporter = generateReporter(serverStats, RpcTracerLogEnum.RPC_SERVER_DIGEST, serverEncoder);

        //构造 RPC 的 tracer 实例
        sofaTracer = new SofaTracer.Builder(RPC_TRACER_TYPE)
            .withClientReporter(clientReporter).withServerReporter(serverReporter)
            .build();
    }

    protected SpanEncoder<SofaTracerSpan> getClientSpanEncoder() {
        return new RpcClientDigestSpanJsonEncoder();
    }

    protected SpanEncoder<SofaTracerSpan> getServerSpanEncoder() {
        return new RpcServerDigestSpanJsonEncoder();
    }

    protected SofaTracerStatisticReporter generateClientStatReporter(RpcTracerLogEnum statRpcTracerLogEnum) {
        //构造统计实例
        String statLog = statRpcTracerLogEnum.getDefaultLogName();
        String statRollingPolicy = SofaTracerConfiguration.getRollingPolicy(statRpcTracerLogEnum.getRollingKey());
        String statLogReserveConfig = SofaTracerConfiguration.getLogReserveConfig(statRpcTracerLogEnum
            .getLogReverseKey());
        //client
        return new RpcClientStatJsonReporter(statLog, statRollingPolicy, statLogReserveConfig);
    }

    protected SofaTracerStatisticReporter generateServerStatReporter(RpcTracerLogEnum statRpcTracerLogEnum) {
        //构造统计实例
        String statLog = statRpcTracerLogEnum.getDefaultLogName();
        String statRollingPolicy = SofaTracerConfiguration.getRollingPolicy(statRpcTracerLogEnum.getRollingKey());
        String statLogReserveConfig = SofaTracerConfiguration.getLogReserveConfig(statRpcTracerLogEnum
            .getLogReverseKey());
        //server
        return new RpcServerStatJsonReporter(statLog, statRollingPolicy, statLogReserveConfig);
    }

    protected Reporter generateReporter(SofaTracerStatisticReporter statReporter,
                                        RpcTracerLogEnum digestRpcTracerLogEnum,
                                        SpanEncoder<SofaTracerSpan> spanEncoder) {
        //构造摘�?实例
        String digestLog = digestRpcTracerLogEnum.getDefaultLogName();
        String digestRollingPolicy = SofaTracerConfiguration.getRollingPolicy(digestRpcTracerLogEnum.getRollingKey());
        String digestLogReserveConfig = SofaTracerConfiguration.getLogReserveConfig(digestRpcTracerLogEnum
            .getLogReverseKey());
        //构造实例
        Reporter reporter = ReporterFactory.build(digestLog, digestRollingPolicy,
            digestLogReserveConfig, spanEncoder, statReporter);
        return reporter;
    }

    @Override
    public void startRpc(SofaRequest request) {
        //客户端的�?�动
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        SofaTracerSpan serverSpan = sofaTraceContext.pop();

        SofaTracerSpan clientSpan = (SofaTracerSpan) this.sofaTracer.buildSpan(request.getInterfaceName())
            .asChildOf(serverSpan)
            .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_CLIENT)
            .start();

        if (RpcInternalContext.isAttachmentEnable()) {
            RpcInternalContext context = RpcInternalContext.getContext();
            clientSpan
                .setTag(RpcSpanTags.LOCAL_APP, (String) context.getAttachment(RpcConstants.INTERNAL_KEY_APP_NAME));
            clientSpan.setTag(RpcSpanTags.PROTOCOL,
                (String) context.getAttachment(RpcConstants.INTERNAL_KEY_PROTOCOL_NAME));
            SofaTracerSpanContext spanContext = clientSpan.getSofaTracerSpanContext();
            if (spanContext != null) {
                context.setAttachment(RpcConstants.INTERNAL_KEY_TRACE_ID, spanContext.getTraceId());
                context.setAttachment(RpcConstants.INTERNAL_KEY_SPAN_ID, spanContext.getSpanId());
            }
        }

        clientSpan.setTag(RpcSpanTags.SERVICE, request.getTargetServiceUniqueName());
        clientSpan.setTag(RpcSpanTags.METHOD, request.getMethodName());
        clientSpan.setTag(RpcSpanTags.CURRENT_THREAD_NAME, Thread.currentThread().getName());

        //需�?主动缓存自己的 serverSpan,原因是:asChildOf 关注的是 spanContext
        clientSpan.setParentSofaTracerSpan(serverSpan);
        //push
        sofaTraceContext.push(clientSpan);
    }

    @Override
    public void clientBeforeSend(SofaRequest request) {
        //客户端的�?�动
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        //获�?�并�?弹出
        SofaTracerSpan clientSpan = sofaTraceContext.getCurrentSpan();
        if (clientSpan == null) {
            SelfLog.warn("ClientSpan is null.Before call interface=" + request.getInterfaceName() + ",method=" +
                request.getMethodName());
            return;
        }
        SofaTracerSpanContext sofaTracerSpanContext = clientSpan.getSofaTracerSpanContext();
        //获�?� RPC 上下文
        RpcInternalContext rpcInternalContext = RpcInternalContext.getContext();
        ProviderInfo providerInfo;
        if ((providerInfo = rpcInternalContext.getProviderInfo()) != null &&
            providerInfo.getRpcVersion() >= 50100) { // 版本>5.1.0
            //新调用新:缓存在 Request 中
            String serializedSpanContext = sofaTracerSpanContext.serializeSpanContext();
            request.addRequestProp(RemotingConstants.NEW_RPC_TRACE_NAME, serializedSpanContext);
        } else {
            //新调用�?
            Map<String, String> oldTracerContext = new HashMap<String, String>();
            oldTracerContext.put(TracerCompatibleConstants.TRACE_ID_KEY, sofaTracerSpanContext.getTraceId());
            oldTracerContext.put(TracerCompatibleConstants.RPC_ID_KEY, sofaTracerSpanContext.getSpanId());
            // 将采样标记解�?并传递
            oldTracerContext.put(TracerCompatibleConstants.SAMPLING_MARK,
                String.valueOf(sofaTracerSpanContext.isSampled()));
            //业务
            oldTracerContext.put(TracerCompatibleConstants.PEN_ATTRS_KEY,
                sofaTracerSpanContext.getBizSerializedBaggage());
            //系统
            oldTracerContext.put(TracerCompatibleConstants.PEN_SYS_ATTRS_KEY,
                sofaTracerSpanContext.getSysSerializedBaggage());
            Map<String, Object> attachments = rpcInternalContext.getAttachments();
            oldTracerContext.put(TracerCompatibleConstants.CALLER_APP_KEY,
                getEmptyStringIfNull(attachments, RpcSpanTags.REMOTE_APP));
            oldTracerContext.put(TracerCompatibleConstants.CALLER_ZONE_KEY,
                getEmptyStringIfNull(attachments, RpcSpanTags.REMOTE_ZONE));
            oldTracerContext.put(TracerCompatibleConstants.CALLER_IDC_KEY,
                getEmptyStringIfNull(attachments, RpcSpanTags.REMOTE_IDC));
            oldTracerContext.put(TracerCompatibleConstants.CALLER_IP_KEY,
                getEmptyStringIfNull(attachments, RpcSpanTags.REMOTE_IP));
            request.addRequestProp(RemotingConstants.RPC_TRACE_NAME, oldTracerContext);
        }

        // 异步callback�?�步
        if (request.isAsync()) {
            //异步,这个时候除了缓存spanContext clientBeforeSendRequest() rpc 已�?调用
            //还需�?这个时候需�?还原回父 span
            //弹出;�?弹出的�?当�?线程就会一直是client了
            clientSpan = sofaTraceContext.pop();
            if (clientSpan != null) {
                // Record client send event
                clientSpan.log(LogData.CLIENT_SEND_EVENT_VALUE);
            }
            //将当�? span 缓存在 request 中,注�?:这个�?�是缓存�?需�?�?列化到�?务端
            rpcInternalContext.setAttachment(RpcConstants.INTERNAL_KEY_TRACER_SPAN, clientSpan);
            if (clientSpan != null && clientSpan.getParentSofaTracerSpan() != null) {
                //restore parent
                sofaTraceContext.push(clientSpan.getParentSofaTracerSpan());
            }
        } else {
            // Record client send event
            clientSpan.log(LogData.CLIENT_SEND_EVENT_VALUE);
        }

    }

    private String getEmptyStringIfNull(Map map, String key) {
        if (map == null || map.size() <= 0) {
            return StringUtils.EMPTY;
        }
        Object valueObject = map.get(key);
        String valueStr = null;
        try {
            valueStr = (String) valueObject;
        } catch (Throwable throwable) {
            return StringUtils.EMPTY;
        }
        return StringUtils.isBlank(valueStr) ? StringUtils.EMPTY : valueStr;
    }

    @Override
    public void clientReceived(SofaRequest request, SofaResponse response, Throwable exceptionThrow) {
        //客户端的�?�动
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        SofaTracerSpan clientSpan = sofaTraceContext.pop();
        if (clientSpan == null) {
            return;
        }
        // Record client receive event
        clientSpan.log(LogData.CLIENT_RECV_EVENT_VALUE);
        //rpc 上下文
        RpcInternalContext context = null;
        if (RpcInternalContext.isAttachmentEnable()) {
            context = RpcInternalContext.getContext();

            if (!clientSpan.getTagsWithStr().containsKey(RpcSpanTags.ROUTE_RECORD)) {
                clientSpan.setTag(RpcSpanTags.ROUTE_RECORD,
                    (String) context.getAttachment(RpcConstants.INTERNAL_KEY_ROUTER_RECORD));
            }
            clientSpan.setTag(RpcSpanTags.REQ_SERIALIZE_TIME,
                (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SERIALIZE_TIME));
            clientSpan.setTag(RpcSpanTags.RESP_DESERIALIZE_TIME,
                (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_DESERIALIZE_TIME));
            clientSpan.setTag(RpcSpanTags.RESP_SIZE,
                (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE));
            clientSpan.setTag(RpcSpanTags.REQ_SIZE, (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE));
            clientSpan.setTag(RpcSpanTags.CLIENT_CONN_TIME,
                (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_CONN_CREATE_TIME));

            Long ce = (Long) context.getAttachment(RpcConstants.INTERNAL_KEY_CLIENT_ELAPSE);
            if (ce != null) {
                clientSpan.setTag(RpcSpanTags.CLIENT_ELAPSE_TIME, ce);
            }

            InetSocketAddress address = context.getLocalAddress();
            if (address != null) {
                clientSpan.setTag(RpcSpanTags.LOCAL_IP, NetUtils.toIpString(address));
                clientSpan.setTag(RpcSpanTags.LOCAL_PORT, address.getPort());
            }
        }

        Throwable throwableShow = exceptionThrow;
        // 区分出�?�个异常信�?�
        String resultCode = StringUtils.EMPTY;
        //当�?应用或者目标应用
        String errorSourceApp = StringUtils.EMPTY;
        String tracerErrorCode = StringUtils.EMPTY;

        if (throwableShow != null) {
            // 客户端异常
            if (throwableShow instanceof SofaRpcException) {
                SofaRpcException exception = (SofaRpcException) throwableShow;
                //摘�?打�?�
                int errorType = exception.getErrorType();
                switch (errorType) {
                    case RpcErrorType.CLIENT_TIMEOUT:
                        resultCode = TracerResultCode.RPC_RESULT_TIMEOUT_FAILED;
                        //filter 已�?存放
                        errorSourceApp = clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
                        tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_TIMEOUT_ERROR;
                        break;
                    case RpcErrorType.CLIENT_ROUTER:
                        resultCode = TracerResultCode.RPC_RESULT_ROUTE_FAILED;
                        errorSourceApp = clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
                        tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_ADDRESS_ROUTE_ERROR;
                        break;
                    case RpcErrorType.CLIENT_SERIALIZE:
                    case RpcErrorType.CLIENT_DESERIALIZE:
                        resultCode = TracerResultCode.RPC_RESULT_RPC_FAILED;
                        errorSourceApp = clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
                        tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_SERIALIZE_ERROR;
                        break;
                    default:
                        resultCode = TracerResultCode.RPC_RESULT_RPC_FAILED;
                        errorSourceApp = ExceptionUtils.isServerException(exception) ?
                            clientSpan.getTagsWithStr().get(RpcSpanTags.REMOTE_APP) : clientSpan.getTagsWithStr().get(
                                RpcSpanTags.LOCAL_APP);
                        tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
                        break;
                }
            } else {
                // 这里是客户端的未知异常，目�?�?会走到这里
                resultCode = TracerResultCode.RPC_RESULT_RPC_FAILED;
                errorSourceApp = clientSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
                tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
            }

        } else if (response != null) {
            // �?务端rpc异常
            if (response.isError()) {
                errorSourceApp = clientSpan.getTagsWithStr().get(RpcSpanTags.REMOTE_APP);
                tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
                resultCode = TracerResultCode.RPC_RESULT_RPC_FAILED;
                //客户端�?务端�?�打�?�
                throwableShow = new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR, response.getErrorMsg());
            } else {
                Object ret = response.getAppResponse();
                //for server throw exception ,but this class can not be found in current
                if (ret instanceof Throwable ||
                    "true".equals(response.getResponseProp(RemotingConstants.HEAD_RESPONSE_ERROR))) {
                    errorSourceApp = clientSpan.getTagsWithStr().get(RpcSpanTags.REMOTE_APP);
                    // 业务异常
                    resultCode = TracerResultCode.RPC_RESULT_BIZ_FAILED;
                    tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_BIZ_ERROR;
                } else {
                    resultCode = TracerResultCode.RPC_RESULT_SUCCESS;
                }
            }
        }
        if (throwableShow != null) {
            Map<String, String> contextMap = new HashMap<String, String>();
            this.generateClientErrorContext(contextMap, request, clientSpan);
            clientSpan.reportError(tracerErrorCode, contextMap,
                throwableShow,
                errorSourceApp,
                ERROR_SOURCE);
        }
        clientSpan.setTag(RpcSpanTags.RESULT_CODE, resultCode);
        //finish client
        clientSpan.finish();
        if (context != null) {
            context.setAttachment(RpcConstants.INTERNAL_KEY_RESULT_CODE, resultCode);
        }
        //client span
        if (clientSpan.getParentSofaTracerSpan() != null) {
            //restore parent
            sofaTraceContext.push(clientSpan.getParentSofaTracerSpan());
        }
    }

    private void generateClientErrorContext(Map<String, String> context, SofaRequest request, SofaTracerSpan clientSpan) {
        Map<String, String> tagsWithStr = clientSpan.getTagsWithStr();
        //记录的上下文信�?�
        context.put("serviceName", tagsWithStr.get(RpcSpanTags.SERVICE));
        context.put("methodName", tagsWithStr.get(RpcSpanTags.METHOD));
        context.put("protocol", tagsWithStr.get(RpcSpanTags.PROTOCOL));
        context.put("invokeType", tagsWithStr.get(RpcSpanTags.INVOKE_TYPE));
        context.put("targetUrl", tagsWithStr.get(RpcSpanTags.REMOTE_IP));
        context.put("targetApp", tagsWithStr.get(RpcSpanTags.REMOTE_APP));
        context.put("targetZone", tagsWithStr.get(RpcSpanTags.REMOTE_ZONE));
        context.put("targetIdc", tagsWithStr.get(RpcSpanTags.REMOTE_IDC));
        context.put("paramTypes",
            com.alipay.common.tracer.core.utils.StringUtils.arrayToString(request.getMethodArgSigs(), '|', "", ""));
        context.put("targetCity", tagsWithStr.get(RpcSpanTags.REMOTE_CITY));
        context.put("uid", tagsWithStr.get(RpcSpanTags.USER_ID));
    }

    @Override
    public void serverReceived(SofaRequest request) {

        Map<String, String> tags = new HashMap<String, String>();
        //server tags 必须设置
        tags.put(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER);

        String spanStrs = (String) request.getRequestProp(RemotingConstants.NEW_RPC_TRACE_NAME);
        SofaTracerSpanContext spanContext = null;
        if (StringUtils.isBlank(spanStrs)) {
            //�?
            Object oldInstanceMap = request.getRequestProp(RemotingConstants.RPC_TRACE_NAME);
            spanContext = this.saveSpanContextAndTags(tags, oldInstanceMap);
        } else {
            //新
            spanContext = SofaTracerSpanContext.deserializeFromString(spanStrs);
        }
        if (spanContext == null) {
            SelfLog.error("SpanContext created error when server received and root SpanContext created.");
            spanContext = SofaTracerSpanContext.rootStart();
        }

        SofaTracerSpan serverSpan = new SofaTracerSpan(this.sofaTracer, System.currentTimeMillis(),
            request.getInterfaceName()
            , spanContext, tags);
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        // Record server receive event
        serverSpan.log(LogData.SERVER_RECV_EVENT_VALUE);
        //放到线程上下文
        sofaTraceContext.push(serverSpan);
        //rpc 上下文
        if (RpcInternalContext.isAttachmentEnable()) {
            RpcInternalContext context = RpcInternalContext.getContext();
            context.setAttachment(RpcConstants.INTERNAL_KEY_TRACE_ID, spanContext.getTraceId());
            context.setAttachment(RpcConstants.INTERNAL_KEY_SPAN_ID, spanContext.getSpanId());
        }
    }

    private SofaTracerSpanContext saveSpanContextAndTags(Map<String, String> tags, Object oldInstanceMap) {
        if (oldInstanceMap instanceof Map) {
            try {
                Map<String, String> contextMap = (Map<String, String>) oldInstanceMap;
                String traceId = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.TRACE_ID_KEY);
                String rpcId = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.RPC_ID_KEY);
                String bizBaggage = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.PEN_ATTRS_KEY);
                String sysBaggage = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.PEN_SYS_ATTRS_KEY);
                String callerApp = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.CALLER_APP_KEY);
                String callerZone = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.CALLER_ZONE_KEY);
                String callerIdc = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.CALLER_IDC_KEY);
                String callerIp = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.CALLER_IP_KEY);
                SofaTracerSpanContext spanContext = new SofaTracerSpanContext(traceId, rpcId);
                //解�?采样标记
                spanContext.setSampled(parseSampled(contextMap, spanContext));
                spanContext.deserializeBizBaggage(bizBaggage);
                spanContext.deserializeSysBaggage(sysBaggage);
                //tags
                tags.put(RpcSpanTags.REMOTE_APP, callerApp);
                tags.put(RpcSpanTags.REMOTE_ZONE, callerZone);
                tags.put(RpcSpanTags.REMOTE_IDC, callerIdc);
                tags.put(RpcSpanTags.REMOTE_IP, callerIp);
                return spanContext;
            } catch (Throwable throwable) {
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean parseSampled(Map<String, String> contextMap, SofaTracerSpanContext spanContext) {
        // 新版本中tracer标记�?在 baggage 中,兼容�?版本
        String oldSampledMark = spanContext.getSysBaggage().get(
            TracerCompatibleConstants.SAMPLING_MARK);
        // 默认�?会设置采样标记，�?�默认采样
        if (StringUtils.isBlank(oldSampledMark) || "true".equals(oldSampledMark)) {
            return true;
        }
        // 除显示获�?� tracer 上下文中的采样标记之外，默认全部采样
        String sampledStr = this.getEmptyStringIfNull(contextMap, TracerCompatibleConstants.SAMPLING_MARK);
        return StringUtils.isNotBlank(sampledStr) ? Boolean.valueOf(sampledStr) : true;
    }

    @Override
    public void serverSend(SofaRequest request, SofaResponse response, Throwable exception) {
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        SofaTracerSpan serverSpan = sofaTraceContext.pop();
        if (serverSpan == null) {
            return;
        }
        // Record server send event
        serverSpan.log(LogData.SERVER_SEND_EVENT_VALUE);

        RpcInternalContext context = RpcInternalContext.getContext();
        serverSpan.setTag(RpcSpanTags.RESP_SERIALIZE_TIME,
            (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SERIALIZE_TIME));
        serverSpan.setTag(RpcSpanTags.REQ_DESERIALIZE_TIME,
            (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_DESERIALIZE_TIME));
        serverSpan.setTag(RpcSpanTags.RESP_SIZE, (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_RESP_SIZE));
        serverSpan.setTag(RpcSpanTags.REQ_SIZE, (Number) context.getAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE));
        //当�?线程�??
        serverSpan.setTag(RpcSpanTags.CURRENT_THREAD_NAME, Thread.currentThread().getName());

        Throwable throwableShow = exception;
        String tracerErrorCode = StringUtils.EMPTY;
        String errorSourceApp = StringUtils.EMPTY;
        String resultCode = StringUtils.EMPTY;
        if (throwableShow != null) {
            //当�?�?��?务端应用
            errorSourceApp = serverSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
            // 结果�?（00=�?功/01=业务异常/02=RPC逻辑错误）
            // �?会业务异常
            resultCode = TracerResultCode.RPC_RESULT_RPC_FAILED;
            tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
        } else if (response != null) {
            // 判断是�?�是业务异常
            if (response.isError()) {
                errorSourceApp = serverSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
                resultCode = TracerResultCode.RPC_RESULT_RPC_FAILED;
                tracerErrorCode = TracerResultCode.RPC_ERROR_TYPE_UNKNOWN_ERROR;
                //改�?�打�?�的 throwable
                throwableShow = new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR, response.getErrorMsg());
            } else {
                Object ret = response.getAppResponse();
                if (ret instanceof Throwable) {
                    throwableShow = (Throwable) ret;
                    errorSourceApp = serverSpan.getTagsWithStr().get(RpcSpanTags.LOCAL_APP);
                    // 业务异常
                    resultCode = TracerResultCode.RPC_RESULT_BIZ_FAILED;
                    tracerErrorCode = TracerResultCode.RPC_RESULT_BIZ_FAILED;
                } else {
                    resultCode = TracerResultCode.RPC_RESULT_SUCCESS;
                }
            }
        }
        if (throwableShow != null) {
            // 打�?�错误
            // result code
            Map<String, String> errorContext = new HashMap<String, String>();
            //记录的上下文信�?�
            this.generateServerErrorContext(errorContext, request, serverSpan);
            //report
            serverSpan.reportError(tracerErrorCode, errorContext, throwableShow,
                errorSourceApp, ERROR_SOURCE);
        }
        // 结果�?（00=�?功/01=业务异常/02=RPC逻辑错误）
        serverSpan.setTag(RpcSpanTags.RESULT_CODE, resultCode);
        serverSpan.finish();
    }

    private void generateServerErrorContext(Map<String, String> context, SofaRequest request,
                                            SofaTracerSpan serverSpan) {
        //tags
        Map<String, String> tagsWithStr = serverSpan.getTagsWithStr();
        context.put("serviceName", tagsWithStr.get(RpcSpanTags.SERVICE));
        context.put("methodName", tagsWithStr.get(RpcSpanTags.METHOD));
        context.put("protocol", tagsWithStr.get(RpcSpanTags.PROTOCOL));
        context.put("invokeType", tagsWithStr.get(RpcSpanTags.INVOKE_TYPE));

        context.put("callerUrl", tagsWithStr.get(RpcSpanTags.REMOTE_IP));
        context.put("callerApp", tagsWithStr.get(RpcSpanTags.REMOTE_APP));
        context.put("callerZone", tagsWithStr.get(RpcSpanTags.REMOTE_ZONE));
        context.put("callerIdc", tagsWithStr.get(RpcSpanTags.REMOTE_IDC));
        //paramTypes
        if (request != null) {
            context.put("paramTypes", com.alipay.common.tracer.core.utils.StringUtils
                .arrayToString(request.getMethodArgSigs(), '|', "", ""));
        }
    }

    @Override
    public void clientAsyncAfterSend(SofaRequest request) {
        //do nothing
    }

    @Override
    public void clientAsyncReceivedPrepare() {
        //新的线程
        RpcInternalContext rpcInternalContext = RpcInternalContext.getContext();
        SofaTracerSpan clientSpan = (SofaTracerSpan)
                rpcInternalContext.getAttachment(RpcConstants.INTERNAL_KEY_TRACER_SPAN);
        if (clientSpan == null) {
            return;
        }
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        sofaTraceContext.push(clientSpan);
    }

    @Override
    public void checkState() {
        RpcInternalContext rpcInternalContext = RpcInternalContext.getContext();
        //tracer 上下文
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        if (rpcInternalContext.isConsumerSide()) {
            //客户端 tracer 堆栈中最多有 1 个(客户端 span 完毕,�?务端 span 压栈所以最多一个)
            if (sofaTraceContext.getThreadLocalSpanSize() > 1) {
                SelfLog.error("Pay attention,stack size error.Tracer consumer stack size more than one.");
                SelfLog.flush();
            }
        } else if (rpcInternalContext.isProviderSide()) {
            //�?务端 tracer 堆栈中应该为 0 个
            if (sofaTraceContext.getThreadLocalSpanSize() > 0) {
                SelfLog.error("Pay attention,stack size error.Tracer provider stack size more than zero.");
                SelfLog.flush();
            }
        }
    }

    @Override
    public void profile(String profileApp, String code, String message) {
        //注�? profile 需�?打�?� traceId 等信�?�,所以�?打开 tracer �?�?�以
        SofaTraceContext sofaTraceContext = SofaTraceContextHolder.getSofaTraceContext();
        SofaTracerSpan sofaTracerSpan = sofaTraceContext.getCurrentSpan();
        if (sofaTracerSpan != null) {
            sofaTracerSpan.profile(profileApp, code, message);
        }
    }

    public SofaTracer getSofaTracer() {
        return sofaTracer;
    }
}
