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

import com.alipay.sofa.rpc.client.ProviderInfo;
import com.alipay.sofa.rpc.common.RpcConfigs;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.common.RpcOptions;
import com.alipay.sofa.rpc.common.struct.StopWatch;
import com.alipay.sofa.rpc.common.utils.NetUtils;
import com.alipay.sofa.rpc.message.ResponseFuture;

import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于ThreadLocal的内部使用的上下文传递。一般存在于：客户端请求线程�?�?务端业务线程池�?客户端异步线程<br>
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class RpcInternalContext implements Cloneable {

    /**
     * 是�?��?许�?�带上下文附件，关闭�?��?�能传递"."开头的key，"_" 开头的Key将�?被�?�?和传递。<br>
     * 在性能测试等场景�?�能关闭此传递功能。
     */
    private static final boolean                                ATTACHMENT_ENABLE = RpcConfigs
                                                                                      .getBooleanValue(RpcOptions.CONTEXT_ATTACHMENT_ENABLE);

    /**
     * The constant LOCAL.
     */
    private static final ThreadLocal<RpcInternalContext>        LOCAL             = new ThreadLocal<RpcInternalContext>();

    /**
     * The constant DEQUE_LOCAL.
     */
    private static final ThreadLocal<Deque<RpcInternalContext>> DEQUE_LOCAL       = new ThreadLocal<Deque<RpcInternalContext>>();

    /**
     * 设置上下文
     *
     * @param context RPC内置上下文
     */
    public static void setContext(RpcInternalContext context) {
        LOCAL.set(context);
    }

    /**
     * 得到上下文，为空则自动创建
     *
     * @return RPC内置上下文
     */
    public static RpcInternalContext getContext() {
        RpcInternalContext context = LOCAL.get();
        if (context == null) {
            context = new RpcInternalContext();
            LOCAL.set(context);
        }
        return context;
    }

    /**
     * 查看上下文，为空�?自动创建
     *
     * @return RPC内置上下文
     */
    public static RpcInternalContext peekContext() {
        return LOCAL.get();
    }

    /**
     * 清�?�上下文
     */
    public static void removeContext() {
        LOCAL.remove();
    }

    /**
     * 上下文往下放一层（例如�?务端B接到A的请求�?��?作为C的客户端调用，调用�?这里就先把放A-B的上下文存起�?�）
     */
    public static void pushContext() {
        RpcInternalContext context = LOCAL.get();
        if (context != null) {
            Deque<RpcInternalContext> deque = DEQUE_LOCAL.get();
            if (deque == null) {
                deque = new ArrayDeque<RpcInternalContext>();
                DEQUE_LOCAL.set(deque);
            }
            deque.push(context);
            LOCAL.set(null);
        }
    }

    /**
     * 上下文往上�?�一层（例如�?务端B接到A的请求�?��?作为C的客户端调用，调用完毕�?�这里就先把放A-B的上下文�?�起�?�）
     */
    public static void popContext() {
        Deque<RpcInternalContext> deque = DEQUE_LOCAL.get();
        if (deque != null) {
            RpcInternalContext context = deque.peek();
            if (context != null) {
                LOCAL.set(deque.pop());
            }
        }
    }

    /**
     * 清�?�全部上下文
     */
    public static void removeAllContext() {
        LOCAL.remove();
        DEQUE_LOCAL.remove();
    }

    /**
     * 是�?�开�?�附件传递功能
     *
     * @return 是�?�开�?�附件传递功能
     */
    public static boolean isAttachmentEnable() {
        return ATTACHMENT_ENABLE;
    }

    /**
     * Instantiates a new Rpc context.
     */
    protected RpcInternalContext() {
    }

    /**
     * The Future.
     */
    private ResponseFuture<?>   future;

    /**
     * The Local address.
     */
    private InetSocketAddress   localAddress;

    /**
     * The Remote address.
     */
    private InetSocketAddress   remoteAddress;

    /**
     * 附带属性功能，�?�循�?使用�?清�?�的原则。Key必须为 "_" 和 "."开头<br>
     * 如果关闭了 {@link #ATTACHMENT_ENABLE} 功能，"_" 开头的Key将�?被�?�?和传递。
     *
     * @see #ATTACHMENT_ENABLE
     */
    private Map<String, Object> attachments = new ConcurrentHashMap<String, Object>();

    /**
     * The Stopwatch
     */
    private StopWatch           stopWatch   = new StopWatch();

    /**
     * The Provider side.
     */
    private Boolean             providerSide;

    /**
     * �?调用的�?务端信�?�
     */
    private ProviderInfo        providerInfo;

    /**
     * Is provider side.
     *
     * @return the boolean
     */
    public boolean isProviderSide() {
        return providerSide != null && providerSide;
    }

    /**
     * Sets provider side.
     *
     * @param isProviderSide the is provider side
     * @return the provider side
     */
    public RpcInternalContext setProviderSide(Boolean isProviderSide) {
        this.providerSide = isProviderSide;
        return this;
    }

    /**
     * Is consumer side.
     *
     * @return the boolean
     */
    public boolean isConsumerSide() {
        return providerSide != null && !providerSide;
    }

    /**
     * get future.
     *
     * @param <T> the type parameter
     * @return future future
     */
    @SuppressWarnings("unchecked")
    public <T> ResponseFuture<T> getFuture() {
        return (ResponseFuture<T>) future;
    }

    /**
     * set future.
     *
     * @param future the future
     * @return RpcInternalContext future
     */
    public RpcInternalContext setFuture(ResponseFuture<?> future) {
        this.future = future;
        return this;
    }

    /**
     * set local address.
     *
     * @param address the address
     * @return context local address
     */
    public RpcInternalContext setLocalAddress(InetSocketAddress address) {
        this.localAddress = address;
        return this;
    }

    /**
     * set local address.
     *
     * @param host the host
     * @param port the port
     * @return context local address
     */
    @Deprecated
    public RpcInternalContext setLocalAddress(String host, int port) {
        if (host == null) {
            return this;
        }
        if (port < 0 || port > 0xFFFF) {
            port = 0;
        }
        // �??�?检查是�?�为空，防止createUnresolved抛出异常，�?�耗性能
        this.localAddress = InetSocketAddress.createUnresolved(host, port);
        return this;
    }

    /**
     * 本地地�?�InetSocketAddress
     *
     * @return local address
     */
    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    /**
     * set remote address.
     *
     * @param address the address
     * @return context remote address
     */
    public RpcInternalContext setRemoteAddress(InetSocketAddress address) {
        this.remoteAddress = address;
        return this;
    }

    /**
     * set remote address.
     *
     * @param host the host
     * @param port the port
     * @return context remote address
     */
    public RpcInternalContext setRemoteAddress(String host, int port) {
        if (host == null) {
            return this;
        }
        if (port < 0 || port > 0xFFFF) {
            port = 0;
        }
        // �??�?检查是�?�为空，防止createUnresolved抛出异常，�?�耗性能
        this.remoteAddress = InetSocketAddress.createUnresolved(host, port);
        return this;
    }

    /**
     * 远程地�?�InetSocketAddress
     *
     * @return remote address
     */
    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    /**
     * 远程IP地�?�
     *
     * @return remote host name
     */
    public String getRemoteHostName() {
        return NetUtils.toIpString(remoteAddress);
    }

    /**
     * get attachment.
     *
     * @param key the key
     * @return attachment attachment
     */
    public Object getAttachment(String key) {
        return key == null ? null : attachments.get(key);
    }

    /**
     * set attachment.
     *
     * @param key   the key
     * @param value the value
     * @return context attachment
     */
    public RpcInternalContext setAttachment(String key, Object value) {
        if (key == null) {
            return this;
        }
        if (!ATTACHMENT_ENABLE) {
            // 未开�?�附件传递功能，�?�能传递�?�?key("." 开头的Key）
            if (!isHiddenParamKey(key)) {
                return this;
            }
        } else {
            if (!isValidInternalParamKey(key)) { // 打开附件传递功能，�?�能传 "_" 和 "." 开头的Key
                throw new IllegalArgumentException("key must start with" + RpcConstants.INTERNAL_KEY_PREFIX
                    + " or " + RpcConstants.HIDE_KEY_PREFIX);
            }
        }
        if (value == null) {
            attachments.remove(key);
        } else {
            attachments.put(key, value);
        }
        return this;
    }

    /**
     * remove attachment.
     *
     * @param key the key
     * @return Old value
     */
    public Object removeAttachment(String key) {
        return attachments.remove(key);
    }

    /**
     * get attachments.
     *
     * @return attachments attachments
     */
    public Map<String, Object> getAttachments() {
        return attachments;
    }

    /**
     * key�?能以点和下划线开头
     *
     * @param attachments the attachments
     * @return context attachments
     */
    public RpcInternalContext setAttachments(Map<String, Object> attachments) {
        if (attachments != null && attachments.size() > 0) {
            for (Map.Entry<String, Object> entry : attachments.entrySet()) {
                setAttachment(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    /**
     * Clear attachments.
     *
     * @return the rpc internal context
     */
    public RpcInternalContext clearAttachments() {
        if (attachments != null && attachments.size() > 0) {
            attachments.clear();
        }
        return this;
    }

    /**
     * Gets stop watch.
     *
     * @return the stop watch
     */
    public StopWatch getStopWatch() {
        return stopWatch;
    }

    /**
     * Clear context for next user
     */
    public void clear() {
        this.setRemoteAddress(null).setLocalAddress(null).setFuture(null).setProviderSide(null)
            .setProviderInfo(null);
        this.attachments = new ConcurrentHashMap<String, Object>();
        this.stopWatch.reset();
    }

    /**
     * Sets provider info.
     *
     * @param providerInfo the provider info
     * @return the provider info
     */
    public RpcInternalContext setProviderInfo(ProviderInfo providerInfo) {
        this.providerInfo = providerInfo;
        return this;
    }

    /**
     * Gets provider info.
     *
     * @return the provider info
     */
    public ProviderInfo getProviderInfo() {
        return providerInfo;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
            "future=" + future +
            ", localAddress=" + localAddress +
            ", remoteAddress=" + remoteAddress +
            ", attachments=" + attachments +
            ", stopWatch=" + stopWatch +
            ", providerSide=" + providerSide +
            ", providerInfo=" + providerInfo +
            '}';
    }

    @Override
    public RpcInternalContext clone() {
        try {
            return (RpcInternalContext) super.clone();
        } catch (Exception e) {
            RpcInternalContext context = new RpcInternalContext();
            context.future = this.future;
            context.localAddress = this.localAddress;
            context.remoteAddress = this.remoteAddress;
            context.stopWatch = this.stopWatch.clone();
            context.providerSide = this.providerSide;
            context.providerInfo = this.providerInfo;
            context.attachments.putAll(this.attachments);
            return context;
        }
    }

    /**
     * �?�法的内置key，以"_"或者"."开头
     *
     * @param key �?�数key
     * @return 是�?��?�法
     */
    public static boolean isValidInternalParamKey(String key) {
        char c = key.charAt(0);
        return c == RpcConstants.INTERNAL_KEY_PREFIX || c == RpcConstants.HIDE_KEY_PREFIX;
    }

    /**
     * 是�?�"."开头的�?�?key
     *
     * @param key �?�数key
     * @return 是�?��?�?key
     */
    static boolean isHiddenParamKey(String key) {
        char c = key.charAt(0);
        return c == RpcConstants.HIDE_KEY_PREFIX;
    }
}
