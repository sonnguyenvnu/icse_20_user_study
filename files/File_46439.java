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
package com.codingapi.txlcn.tc.aspect.weave;

import com.codingapi.txlcn.tc.aspect.DTXInfo;
import com.codingapi.txlcn.tc.core.DTXLocalContext;
import com.codingapi.txlcn.tc.core.DTXServiceExecutor;
import com.codingapi.txlcn.tc.core.TxTransactionInfo;
import com.codingapi.txlcn.tc.core.context.TCGlobalContext;
import com.codingapi.txlcn.tc.core.context.TxContext;
import com.codingapi.txlcn.tracing.TracingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/11/29
 *
 * @author ujued
 */
@Component
@Slf4j
public class DTXLogicWeaver {

    private final DTXServiceExecutor transactionServiceExecutor;

    private final TCGlobalContext globalContext;

    @Autowired
    public DTXLogicWeaver(DTXServiceExecutor transactionServiceExecutor, TCGlobalContext globalContext) {
        this.transactionServiceExecutor = transactionServiceExecutor;
        this.globalContext = globalContext;
    }

    public Object runTransaction(DTXInfo dtxInfo, BusinessCallback business) throws Throwable {

        if (Objects.isNull(DTXLocalContext.cur())) {
            DTXLocalContext.getOrNew();
        } else {
            return business.call();
        }

        log.debug("<---- TxLcn start ---->");
        DTXLocalContext dtxLocalContext = DTXLocalContext.getOrNew();
        TxContext txContext;
        // ---------- �?�?�?个模�?�在一个DTX下�?�会有一个TxContext ---------- //
        if (globalContext.hasTxContext()) {
            // 有事务上下文的获�?�父上下文
            txContext = globalContext.txContext();
            dtxLocalContext.setInGroup(true);
            log.debug("Unit[{}] used parent's TxContext[{}].", dtxInfo.getUnitId(), txContext.getGroupId());
        } else {
            // 没有的开�?�本地事务上下文
            txContext = globalContext.startTx();
        }

        // 本地事务调用
        if (Objects.nonNull(dtxLocalContext.getGroupId())) {
            dtxLocalContext.setDestroy(false);
        }

        dtxLocalContext.setUnitId(dtxInfo.getUnitId());
        dtxLocalContext.setGroupId(txContext.getGroupId());
        dtxLocalContext.setTransactionType(dtxInfo.getTransactionType());

        // 事务�?�数
        TxTransactionInfo info = new TxTransactionInfo();
        info.setBusinessCallback(business);
        info.setGroupId(txContext.getGroupId());
        info.setUnitId(dtxInfo.getUnitId());
        info.setPointMethod(dtxInfo.getBusinessMethod());
        info.setPropagation(dtxInfo.getTransactionPropagation());
        info.setTransactionInfo(dtxInfo.getTransactionInfo());
        info.setTransactionType(dtxInfo.getTransactionType());
        info.setTransactionStart(txContext.isDtxStart());

        //LCN事务处�?�器
        try {
            return transactionServiceExecutor.transactionRunning(info);
        } finally {
            // 线程执行业务完毕清�?�本地数�?�
            if (dtxLocalContext.isDestroy()) {
                // 通知事务执行完毕
                synchronized (txContext.getLock()) {
                    txContext.getLock().notifyAll();
                }

                // TxContext生命周期是？ 和事务组一样（�?与具体模�?�相关的）
                if (!dtxLocalContext.isInGroup()) {
                    globalContext.destroyTx();
                }

                DTXLocalContext.makeNeverAppeared();
                TracingContext.tracing().destroy();
            }
            log.debug("<---- TxLcn end ---->");
        }
    }
}
