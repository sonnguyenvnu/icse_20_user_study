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
package com.alipay.sofa.rpc.context;

import com.alipay.sofa.rpc.common.RpcConfigs;
import com.alipay.sofa.rpc.common.RpcOptions;
import com.alipay.sofa.rpc.core.invoke.SofaResponseCallback;
import com.alipay.sofa.rpc.message.ResponseFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 基于ThreadLocal的�?��?�业务开�?�者使用的上下文传递对象
 *
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 */
public class RpcInvokeContext {

    /**
     * 线程上下文�?��?
     */
    protected static final ThreadLocal<RpcInvokeContext> LOCAL = new ThreadLocal<RpcInvokeContext>();

    /**
     * 得到上下文，没有则�?始化
     *
     * @return 调用上下文
     */
    public static RpcInvokeContext getContext() {
        RpcInvokeContext context = LOCAL.get();
        if (context == null) {
            context = new RpcInvokeContext();
            LOCAL.set(context);
        }
        return context;
    }

    /**
     * 查看上下文
     *
     * @return 调用上下文，�?�能为空
     */
    public static RpcInvokeContext peekContext() {
        return LOCAL.get();
    }

    /**
     * 删除上下文
     */
    public static void removeContext() {
        LOCAL.remove();
    }

    /**
     * 设置上下文
     *
     * @param context 调用上下文
     */
    public static void setContext(RpcInvokeContext context) {
        LOCAL.set(context);
    }

    /**
     * 是�?�开�?�上下文�?传功能
     *
     * @since 5.1.2
     */
    private static final boolean BAGGAGE_ENABLE = RpcConfigs.getBooleanValue(RpcOptions.INVOKE_BAGGAGE_ENABLE);

    /**
     * 是�?��?�用RPC�?传功能
     *
     * @return 是�?�
     */
    public static boolean isBaggageEnable() {
        return BAGGAGE_ENABLE;
    }

    /**
     * 用户自定义超时时间，�?�次调用生效
     */
    protected Integer                       timeout;

    /**
     * 用户自定义对方地�?�，�?�次调用生效
     */
    protected String                        targetURL;

    /**
     * 用户自定义对方分组
     */
    protected String                        targetGroup;

    /**
     * 用户自定义Callback，�?�次调用生效
     */
    protected SofaResponseCallback          responseCallback;

    /**
     * The Future.
     */
    protected ResponseFuture<?>             future;

    /**
     * 自定义属性
     */
    protected ConcurrentMap<String, Object> map             = new ConcurrentHashMap<String, Object>();

    /**
     * 请求上的�?传数�?�
     *
     * @since 5.1.2
     */
    protected Map<String, String>           requestBaggage  = BAGGAGE_ENABLE ? new HashMap<String, String>() : null;

    /**
     * �?应上的�?传数�?�
     *
     * @since 5.1.2
     */
    protected Map<String, String>           responseBaggage = BAGGAGE_ENABLE ? new HashMap<String, String>() : null;

    /**
     * 得到调用级别超时时间
     *
     * @return 超时时间
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * 设置调用级别超时时间
     *
     * @param timeout 超时时间
     * @return 当�?
     */
    public RpcInvokeContext setTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 设置一个调用上下文数�?�
     *
     * @param key   Key
     * @param value Value
     */
    public void put(String key, Object value) {
        if (key != null && value != null) {
            map.put(key, value);
        }
    }

    /**
     * 获�?�一个调用上下文数�?�
     *
     * @param key Key
     * @return 值
     */
    public Object get(String key) {
        if (key != null) {
            return map.get(key);
        }
        return null;
    }

    /**
     * 删除一个调用上下文数�?�
     *
     * @param key Key
     * @return 删除�?的值
     */
    public Object remove(String key) {
        if (key != null) {
            return map.remove(key);
        }
        return null;
    }

    /**
     * 放入请求�?传数�?�
     *
     * @param key   Key
     * @param value Value
     */
    public void putRequestBaggage(String key, String value) {
        if (BAGGAGE_ENABLE && key != null && value != null) {
            requestBaggage.put(key, value);
        }
    }

    /**
     * 得到请求�?传数�?�
     *
     * @param key Key
     * @return Value
     */
    public String getRequestBaggage(String key) {
        if (BAGGAGE_ENABLE && key != null) {
            return requestBaggage.get(key);
        }
        return null;
    }

    /**
     * 删除请求�?传数�?�
     *
     * @param key Key
     * @return Value 删掉的值
     */
    public String removeRequestBaggage(String key) {
        if (BAGGAGE_ENABLE && key != null) {
            return requestBaggage.remove(key);
        }
        return null;
    }

    /**
     * 得到全部请求�?传数�?�
     *
     * @return 全部�?应�?传数�?�
     */
    public Map<String, String> getAllRequestBaggage() {
        return requestBaggage;
    }

    /**
     * 设置全部请求�?传数�?�
     * 
     * @param requestBaggage 请求�?传数�?�
     */
    public void putAllRequestBaggage(Map<String, String> requestBaggage) {
        if (BAGGAGE_ENABLE && requestBaggage != null) {
            this.requestBaggage.putAll(requestBaggage);
        }
    }

    /**
     * 放入�?应�?传数�?�
     *
     * @param key   Key
     * @param value Value
     */
    public void putResponseBaggage(String key, String value) {
        if (BAGGAGE_ENABLE && key != null && value != null) {
            responseBaggage.put(key, value);
        }
    }

    /**
     * 得到�?应�?传数�?�
     *
     * @param key Key
     * @return Value
     */
    public String getResponseBaggage(String key) {
        if (BAGGAGE_ENABLE && key != null) {
            return responseBaggage.get(key);
        }
        return null;
    }

    /**
     * 删除�?应�?传数�?�
     *
     * @param key Key
     * @return Value 删掉的值
     */
    public String removeResponseBaggage(String key) {
        if (BAGGAGE_ENABLE && key != null) {
            return responseBaggage.remove(key);
        }
        return null;
    }

    /**
     * 得到全部�?应�?传数�?�
     *
     * @return 全部�?应�?传数�?�
     */
    public Map<String, String> getAllResponseBaggage() {
        return responseBaggage;
    }

    /**
     * 设置全部�?应�?传数�?�
     * 
     * @param responseBaggage �?应�?传数�?�
     */
    public void putAllResponseBaggage(Map<String, String> responseBaggage) {
        if (BAGGAGE_ENABLE && responseBaggage != null) {
            this.responseBaggage.putAll(responseBaggage);
        }
    }

    /**
     * 获�?��?�次请求的指定地�?�
     *
     * @return �?�次请求的指定地�?�
     */
    public String getTargetURL() {
        return targetURL;
    }

    /**
     * 设置�?�次请求的指定地�?�
     *
     * @param targetURL �?�次请求的指定地�?�
     * @return RpcInvokeContext
     */
    public RpcInvokeContext setTargetURL(String targetURL) {
        this.targetURL = targetURL;
        return this;
    }

    /**
     * 获�?��?�次请求的指定分组
     *
     * @return �?�次请求的指定分组
     */
    public String getTargetGroup() {
        return targetGroup;
    }

    /**
     * 设置�?�次请求的指定分组
     *
     * @param targetGroup �?�次请求的指定分组
     * @return RpcInvokeContext
     */
    public RpcInvokeContext setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
        return this;
    }

    /**
     * 获�?��?�次请求的指定回调方法
     *
     * @return �?�次请求的指定回调方法
     */
    public SofaResponseCallback getResponseCallback() {
        return responseCallback;
    }

    /**
     * 设置�?�次请求的指定回调方法
     *
     * @param responseCallback �?�次请求的指定回调方法
     * @return RpcInvokeContext
     */
    public RpcInvokeContext setResponseCallback(SofaResponseCallback responseCallback) {
        this.responseCallback = responseCallback;
        return this;
    }

    /**
     * 得到�?�次请求返回的异步Future对象
     *
     * @param <T> 返回值类型
     * @return 异步Future对象
     */
    @SuppressWarnings("unchecked")
    public <T> ResponseFuture<T> getFuture() {
        return (ResponseFuture<T>) future;
    }

    /**
     * 设置�?�次请求返回的异步Future对象
     *
     * @param future Future对象
     * @return RpcInvokeContext
     */
    public RpcInvokeContext setFuture(ResponseFuture<?> future) {
        this.future = future;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        sb.append("{timeout=").append(timeout);
        sb.append(", targetURL='").append(targetURL).append('\'');
        sb.append(", targetGroup='").append(targetGroup).append('\'');
        sb.append(", responseCallback=").append(responseCallback);
        sb.append(", future=").append(future);
        sb.append(", map=").append(map);
        sb.append(", requestBaggage=").append(requestBaggage);
        sb.append(", responseBaggage=").append(responseBaggage);
        sb.append('}');
        return sb.toString();
    }
}
