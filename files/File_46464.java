/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codingapi.txlcn.tc.core;


import com.codingapi.txlcn.tc.core.transaction.tcc.control.TccTransactionCleanService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 分布�?事务远程调用控制对象
 * �?�?�?�?推�??用户业务使用，API�?�更性大，使用�?当有�?�能造�?事务�?程出错 �?�?�?
 * <p>
 * Created by lorne on 2017/6/5.
 */
@Data
@Slf4j
public class DTXLocalContext {

    private final static ThreadLocal<DTXLocalContext> currentLocal = new InheritableThreadLocal<>();

    /**
     * 事务类型
     */
    private String transactionType;

    /**
     * 事务组
     */
    private String groupId;

    /**
     * 事务�?�元
     */
    private String unitId;

    /**
     * 业务相关资�?
     */
    private Object resource;


    ////////////////////////// volatile ///////////////////////////////

    /**
     * 是�?�需�?销�?。什么时候需�?？一个请求下�?�，这个模�?�有两个Unit被执行，那么被调方是�?能销�?的，�?�能有上层调用方销�?
     */
    private boolean destroy = true;

    /**
     * �?�事务组标识
     */
    private boolean inGroup;

    /**
     * �?外的附加值
     */
    private Object attachment;

    /**
     * 系统分布�?事务状�?
     */
    private int sysTransactionState = 1;

    /**
     * 用户分布�?事务状�?
     */
    private int userTransactionState = -1;

    /**
     * 是�?�代�?�资�?
     */
    private boolean proxy;

    /**
     * 是�?�是刚刚创建的DTXLocal. �?是特别了解这个�?�?时，�?�?轻易�?作这个值。
     *
     * @see TccTransactionCleanService#clear(java.lang.String, int, java.lang.String, java.lang.String)
     */
    @Deprecated
    private boolean justNow;

    //////// private     ///////////////////////
    /**
     * 临时值
     */
    private boolean proxyTmp;


    private boolean isProxyTmp() {
        return proxyTmp;
    }

    private void setProxyTmp(boolean proxyTmp) {
        this.proxyTmp = proxyTmp;
    }
    ///////   end      /////////////////////////


    /**
     * 获�?�当�?线程�?��?。�?推�??用此方法，会产生NullPointerException
     *
     * @return 当�?线程�?��?
     */
    public static DTXLocalContext cur() {
        return currentLocal.get();
    }

    /**
     * 获�?�或新建一个线程�?��?。
     *
     * @return 当�?线程�?��?
     */
    public static DTXLocalContext getOrNew() {
        if (currentLocal.get() == null) {
            currentLocal.set(new DTXLocalContext());
        }
        return currentLocal.get();
    }

    /**
     * 设置代�?�资�?
     */
    public static void makeProxy() {
        if (currentLocal.get() != null) {
            cur().proxyTmp = cur().proxy;
            cur().proxy = true;
        }
    }

    /**
     * 设置�?代�?�资�?
     */
    public static void makeUnProxy() {
        if (currentLocal.get() != null) {
            cur().proxyTmp = cur().proxy;
            cur().proxy = false;
        }
    }

    /**
     * 撤销到上一步的资�?代�?�状�?
     */
    public static void undoProxyStatus() {
        if (currentLocal.get() != null) {
            cur().proxy = cur().proxyTmp;
        }
    }

    /**
     * 清�?�线程�?��?
     */
    public static void makeNeverAppeared() {
        if (currentLocal.get() != null) {
            log.debug("clean thread local[{}]: {}", DTXLocalContext.class.getSimpleName(), cur());
            currentLocal.set(null);
        }
    }

    /**
     * 事务状�?
     * @param userDtxState state
     * @return 1 commit 0 rollback
     */
    public static int transactionState(int userDtxState) {
        DTXLocalContext dtxLocalContext = Objects.requireNonNull(currentLocal.get(), "DTX can't be null.");
        return userDtxState == 1 ? dtxLocalContext.sysTransactionState : userDtxState;
    }
}
