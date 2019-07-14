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
package com.alipay.sofa.rpc.message;

import com.alipay.sofa.rpc.context.RpcRuntimeContext;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author <a href="mailto:zhanggeng.zg@antfin.com">GengZhang</a>
 * @since 5.4.0
 */
public abstract class AbstractResponseFuture<V> implements ResponseFuture<V> {

    protected static final CancellationException CANCELLATION_CAUSE = new CancellationException();

    /**
     * 返回的结果
     */
    protected volatile Object                    result;

    /**
     * 异常
     */
    protected volatile Throwable                 cause;

    /**
     * 用户设置的超时时间
     */
    protected final int                          timeout;
    /**
     * Future生�?时间
     */
    protected final long                         genTime            = RpcRuntimeContext.now();
    /**
     * Future已�?��?时间
     */
    protected volatile long                      sentTime;
    /**
     * Future完�?的时间
     */
    protected volatile long                      doneTime;

    /**
     * 构造函数
     */
    public AbstractResponseFuture(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        try {
            return get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new ExecutionException(e);
        }
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long realTimeOut = unit.toMillis(timeout);
        long remainTime = realTimeOut - (sentTime - genTime); // 剩余时间
        if (remainTime <= 0) { // 没有剩余时间�?等待
            if (isDone()) { // 直接看是�?�已�?返回
                return getNow();
            }
        } else { // 等待剩余时间
            if (await(remainTime, TimeUnit.MILLISECONDS)) {
                return getNow();
            }
        }
        this.setDoneTime();
        throw clientTimeoutException();
    }

    protected TimeoutException clientTimeoutException() {
        return new TimeoutException();
    }

    /**
     * 解�?结果，拿到返回值
     * 
     * @return do return self
     * @throws ExecutionException 执行异常
     */
    protected abstract V getNow() throws ExecutionException;

    /**
     * 异步的情况下，如果返回，将释放内容
     *
     * @param result 返回值
     */
    protected abstract void releaseIfNeed(Object result);

    protected boolean await(long timeout, TimeUnit unit)
        throws InterruptedException {
        return await0(unit.toNanos(timeout), true);
    }

    private boolean await0(long timeoutNanos, boolean interruptable) throws InterruptedException {
        if (isDone()) {
            return true;
        }
        if (timeoutNanos <= 0) {
            return isDone();
        }
        if (interruptable && Thread.interrupted()) {
            throw new InterruptedException(toString());
        }
        long startTime = System.nanoTime();
        long waitTime = timeoutNanos;
        boolean interrupted = false;
        try {
            synchronized (this) {
                if (isDone()) {
                    return true;
                }
                incWaiters();
                try {
                    for (;;) {
                        try {
                            wait(waitTime / 1000000, (int) (waitTime % 1000000));
                        } catch (InterruptedException e) {
                            if (interruptable) {
                                throw e;
                            } else {
                                interrupted = true;
                            }
                        }

                        if (isDone()) {
                            return true;
                        } else {
                            waitTime = timeoutNanos - (System.nanoTime() - startTime);
                            if (waitTime <= 0) {
                                return isDone();
                            }
                        }
                    }
                } finally {
                    decWaiters();
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private short waiters;

    private boolean hasWaiters() {
        return waiters > 0;
    }

    private void incWaiters() {
        if (waiters == Short.MAX_VALUE) {
            throw new IllegalStateException("too many waiters: " + this);
        }
        waiters++;
    }

    private void decWaiters() {
        waiters--;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean res = this.cancle0(mayInterruptIfRunning);
        notifyListeners();
        return res;
    }

    private boolean cancle0(boolean mayInterruptIfRunning) {
        if (isDone()) {
            return false;
        }
        synchronized (this) {
            if (isDone()) {
                return false;
            }
            this.cause = CANCELLATION_CAUSE;
            this.setDoneTime();
            if (hasWaiters()) {
                notifyAll();
            }
        }
        return true;
    }

    /**
     * 设置正常返回结果
     *
     * @param result 正常返回值
     */
    public void setSuccess(V result) {
        if (this.isCancelled()) {
            this.releaseIfNeed(result);
        }
        if (setSuccess0(result)) {
            notifyListeners();
            return;
        }
        throw new IllegalStateException("complete already: " + this);
    }

    protected boolean setSuccess0(V result) {
        if (isDone()) {
            return false;
        }
        synchronized (this) {
            // Allow only once.
            if (isDone()) {
                return false;
            }
            if (this.result == null) {
                this.result = result;
            }
            this.setDoneTime();
            if (hasWaiters()) {
                notifyAll();
            }
        }
        return true;
    }

    /**
     * 设置异常
     *
     * @param cause 异常类型
     */
    public void setFailure(Throwable cause) {
        if (this.isCancelled()) {
            this.releaseIfNeed(result);
            return;
        }
        if (setFailure0(cause)) {
            notifyListeners();
            return;
        }
        throw new IllegalStateException("complete already: " + this, cause);
    }

    private boolean setFailure0(Throwable cause) {
        if (isDone()) {
            return false;
        }
        synchronized (this) {
            if (isDone()) {
                return false;
            }
            this.cause = cause;
            this.setDoneTime();
            if (hasWaiters()) {
                notifyAll();
            }
        }
        return true;
    }

    @Override
    public boolean isCancelled() {
        return cause == CANCELLATION_CAUSE;
    }

    @Override
    public boolean isDone() {
        return result != null || cause != null;
    }

    /**
     * notify all listener.
     */
    public abstract void notifyListeners();

    /**
     * 设置已�?��?时间
     */
    public void setSentTime() {
        this.sentTime = RpcRuntimeContext.now();
    }

    /**
     * 记录结�?�时间
     */
    protected void setDoneTime() {
        if (doneTime == 0L) {
            doneTime = RpcRuntimeContext.now();
        }
    }

    /**
     * 查看future耗时
     *
     * @return 耗时
     */
    public long getElapsedTime() {
        return doneTime - genTime;
    }
}
