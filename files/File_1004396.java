/*
 * Copyright 2018 Qunar, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package qunar.tc.qmq.producer;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qunar.tc.qmq.MessageSendStateListener;
import qunar.tc.qmq.MessageStore;
import qunar.tc.qmq.ProduceMessage;
import qunar.tc.qmq.base.BaseMessage;
import qunar.tc.qmq.metrics.Metrics;
import qunar.tc.qmq.metrics.MetricsConstants;
import qunar.tc.qmq.metrics.QmqCounter;
import qunar.tc.qmq.metrics.QmqMeter;
import qunar.tc.qmq.tracing.TraceUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author miao.yang susing@gmail.com
 * @date 2013-1-5
 */
class ProduceMessageImpl implements ProduceMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProduceMessageImpl.class);

    private static final int INIT = 0;
    private static final int QUEUED = 1;

    private static final int FINISH = 100;
    private static final int ERROR = -1;
    private static final int BLOCK = -2;

    private static final QmqCounter sendCount = Metrics.counter("qmq_client_send_count");
    private static final QmqCounter sendOkCount = Metrics.counter("qmq_client_send_ok_count");
    private static final QmqMeter sendOkQps = Metrics.meter("qmq_client_send_ok_qps");
    private static final QmqCounter sendErrorCount = Metrics.counter("qmq_client_send_error_count");
    private static final QmqCounter sendFailCount = Metrics.counter("qmq_client_send_fail_count");
    private static final QmqCounter resendCount = Metrics.counter("qmq_client_resend_count");
    private static final QmqCounter enterQueueFail = Metrics.counter("qmq_client_enter_queue_fail");

    /**
     * æœ€å¤šå°?è¯•æ¬¡æ•°
     */
    private int sendTryCount;

    private final BaseMessage base;

    private final QueueSender sender;

    //tracer
    private final Tracer tracer;
    private Span traceSpan;
    private Scope traceScope;

    private MessageSendStateListener sendStateListener;

    private final AtomicInteger state = new AtomicInteger(INIT);
    private final AtomicInteger tries = new AtomicInteger(0);

    private boolean syncSend;
    private MessageStore store;
    private long sequence;

    //å¦‚æžœä½¿ç”¨äº†åˆ†åº“åˆ†è¡¨ç­‰ï¼Œç”¨äºŽä¸´æ—¶è®°å½•åˆ†åº“åˆ†è¡¨çš„è·¯ç”±ä¿¡æ?¯ï¼Œç¡®ä¿?åœ¨å?‘é€?æ¶ˆæ?¯æˆ?åŠŸå?Žèƒ½å¤Ÿæ ¹æ?®è·¯ç”±ä¿¡æ?¯æ‰¾åˆ°æ?’å…¥çš„db
    private transient Object routeKey;

    public ProduceMessageImpl(BaseMessage base, QueueSender sender) {
        this.base = base;
        this.sender = sender;
        this.tracer = GlobalTracer.get();
    }

    @Override
    public void send() {
        sendCount.inc();
        attachTraceData();
        doSend();
    }

    private void doSend() {
        if (state.compareAndSet(INIT, QUEUED)) {
            tries.incrementAndGet();
            if (sendSync()) return;

            try (Scope scope = tracer.buildSpan("Qmq.QueueSender.Send").startActive(false)) {
                traceSpan = scope.span();

                if (sender.offer(this)) {
                    LOGGER.info("è¿›å…¥å?‘é€?é˜Ÿåˆ— {}:{}", getSubject(), getMessageId());
                } else if (store != null) {
                    enterQueueFail.inc();
                    LOGGER.info("å†…å­˜å?‘é€?é˜Ÿåˆ—å·²æ»¡! æ­¤æ¶ˆæ?¯å°†æš‚æ—¶ä¸¢å¼ƒ,ç­‰å¾…è¡¥å?¿æœ?åŠ¡å¤„ç?† {}:{}", getSubject(), getMessageId());
                    failed();
                } else {
                    LOGGER.info("å†…å­˜å?‘é€?é˜Ÿåˆ—å·²æ»¡! æ­¤æ¶ˆæ?¯åœ¨ç”¨æˆ·è¿›ç¨‹é˜»å¡ž,ç­‰å¾…é˜Ÿåˆ—æ¿€æ´» {}:{}", getSubject(), getMessageId());
                    if (sender.offer(this, 50)) {
                        LOGGER.info("è¿›å…¥å?‘é€?é˜Ÿåˆ— {}:{}", getSubject(), getMessageId());
                    } else {
                        enterQueueFail.inc();
                        LOGGER.info("ç”±äºŽæ— æ³•å…¥é˜Ÿ,å?‘é€?å¤±è´¥ï¼?å?–æ¶ˆå?‘é€? {}:{}", getSubject(), getMessageId());
                        onFailed();
                    }
                }
            }

        } else {
            enterQueueFail.inc();
            throw new IllegalStateException("å?Œä¸€æ?¡æ¶ˆæ?¯ä¸?èƒ½è¢«å…¥é˜Ÿä¸¤æ¬¡.");
        }
    }

    private boolean sendSync() {
        if (store != null || !syncSend) return false;
        sender.send(this);
        return true;
    }

    @Override
    public void finish() {
        state.set(FINISH);
        try {
            if (store == null) return;
            if (base.isStoreAtFailed()) return;
            store.finish(this);
        } catch (Exception e) {
            TraceUtil.recordEvent("Qmq.Store.Failed");
        } finally {
            onSuccess();
            closeTrace();
        }
    }

    private void onSuccess() {
        sendOkCount.inc();
        sendOkQps.mark();
        if (sendStateListener == null) return;
        sendStateListener.onSuccess(base);
    }

    @Override
    public void error(Exception e) {
        state.set(ERROR);
        try {
            if (tries.get() < sendTryCount) {
                sendErrorCount.inc();
                LOGGER.info("å?‘é€?å¤±è´¥, é‡?æ–°å?‘é€?. tryCount: {} {}:{}", tries.get(), getSubject(), getMessageId());
                resend();
            } else {
                failed();
            }
        } finally {
            closeTrace();
        }
    }

    @Override
    public void block() {
        try {
            state.set(BLOCK);
            try {
                if (store == null) return;
                store.block(this);
            } catch (Exception e) {
                TraceUtil.recordEvent("Qmq.Store.Failed");
            }
            LOGGER.info("æ¶ˆæ?¯è¢«æ‹’ç»?");
            if (store == null && syncSend) {
                throw new RuntimeException("æ¶ˆæ?¯è¢«æ‹’ç»?ä¸”æ²¡æœ‰storeå?¯æ?¢å¤?,è¯·æ£€æŸ¥åº”ç”¨æŽˆæ?ƒé…?ç½®");
            }
        } finally {
            onFailed();
            closeTrace();
        }
    }

    @Override
    public void failed() {
        state.set(ERROR);
        try {
            sendErrorCount.inc();
            String message = "å?‘é€?å¤±è´¥, å·²å°?è¯•" + tries.get() + "æ¬¡ä¸?å†?å°?è¯•é‡?æ–°å?‘é€?.";
            LOGGER.info(message);
            try {
                if (store == null) return;
                if (base.isStoreAtFailed()) {
                    save();
                }
            } catch (Exception e) {
                TraceUtil.recordEvent("Qmq.Store.Failed");
            }

            if (store == null && syncSend) {
                throw new RuntimeException(message);
            }
        } finally {
            onFailed();
            closeTrace();
        }
    }

    private void onFailed() {
        TraceUtil.recordEvent("send_failed", tracer);
        sendFailCount.inc();
        if (sendStateListener == null) return;
        sendStateListener.onFailed(base);
    }

    private void resend() {
        resendCount.inc();
        traceSpan = null;
        traceScope = null;
        state.set(INIT);
        TraceUtil.recordEvent("retry", tracer);
        doSend();
    }

    @Override
    public String getMessageId() {
        return base.getMessageId();
    }

    @Override
    public String getSubject() {
        return base.getSubject();
    }

    @Override
    public void startSendTrace() {
        if (traceSpan == null) return;
        traceScope = tracer.scopeManager().activate(traceSpan, false);
        attachTraceData();
    }

    @Override
    public void setStore(MessageStore messageStore) {
        this.store = messageStore;
    }

    @Override
    public void save() {
        long start = System.nanoTime();
        try {
            this.sequence = store.insertNew(this);
        } finally {
            Metrics.timer("qmq_client_persistence_time",
                    MetricsConstants.SUBJECT_ARRAY,
                    new String[]{getSubject()}).update(System.nanoTime() - start, TimeUnit.NANOSECONDS);
        }

    }

    @Override
    public long getSequence() {
        return this.sequence;
    }

    @Override
    public void setRouteKey(Object routeKey) {
        this.routeKey = routeKey;
    }

    @Override
    public Object getRouteKey() {
        return this.routeKey;
    }

    private void attachTraceData() {
        TraceUtil.inject(base, tracer);
    }

    private void closeTrace() {
        if (traceScope == null) return;
        traceScope.close();
    }

    @Override
    public BaseMessage getBase() {
        return base;
    }

    public void setSendTryCount(int sendTryCount) {
        this.sendTryCount = sendTryCount;
    }

    public void setSendStateListener(MessageSendStateListener sendStateListener) {
        this.sendStateListener = sendStateListener;
    }

    public void setSyncSend(boolean syncSend) {
        this.syncSend = syncSend;
    }
}
