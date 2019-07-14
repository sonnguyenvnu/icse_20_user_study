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


import com.codingapi.txlcn.common.exception.TransactionException;
import com.codingapi.txlcn.common.util.Transactions;
import com.codingapi.txlcn.logger.TxLogger;
import com.codingapi.txlcn.tc.core.propagation.DTXPropagationResolver;
import com.codingapi.txlcn.tc.support.TxLcnBeanHelper;
import com.codingapi.txlcn.tc.core.context.TCGlobalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * LCN分布�?事务业务执行器
 * Created by lorne on 2017/6/8.
 */
@Component
@Slf4j
public class DTXServiceExecutor {

    private static final TxLogger txLogger = TxLogger.newLogger(DTXServiceExecutor.class);

    private final TCGlobalContext globalContext;

    private final TxLcnBeanHelper txLcnBeanHelper;

    private final DTXPropagationResolver propagationResolver;

    @Autowired
    public DTXServiceExecutor(TxLcnBeanHelper txLcnBeanHelper, TCGlobalContext globalContext,
                              DTXPropagationResolver propagationResolver) {
        this.txLcnBeanHelper = txLcnBeanHelper;
        this.globalContext = globalContext;
        this.propagationResolver = propagationResolver;
    }

    /**
     * 事务业务执行
     *
     * @param info info
     * @return Object
     * @throws Throwable Throwable
     */
    public Object transactionRunning(TxTransactionInfo info) throws Throwable {

        // 1. 获�?�事务类型
        String transactionType = info.getTransactionType();

        // 2. 获�?�事务传播状�?
        DTXPropagationState propagationState = propagationResolver.resolvePropagationState(info);

        // 2.1 如果�?�?�与分布�?事务立�?�终止
        if (propagationState.isIgnored()) {
            return info.getBusinessCallback().call();
        }

        // 3. 获�?�本地分布�?事务控制器
        DTXLocalControl dtxLocalControl = txLcnBeanHelper.loadDTXLocalControl(transactionType, propagationState);

        // 4. 织入事务�?作
        try {
            // 4.1 记录事务类型到事务上下文
            Set<String> transactionTypeSet = globalContext.txContext(info.getGroupId()).getTransactionTypes();
            transactionTypeSet.add(transactionType);

            dtxLocalControl.preBusinessCode(info);

            // 4.2 业务执行�?
            txLogger.txTrace(
                    info.getGroupId(), info.getUnitId(), "pre business code, unit type: {}", transactionType);

            // 4.3 执行业务
            Object result = dtxLocalControl.doBusinessCode(info);

            // 4.4 业务执行�?功
            txLogger.txTrace(info.getGroupId(), info.getUnitId(), "business success");
            dtxLocalControl.onBusinessCodeSuccess(info, result);
            return result;
        } catch (TransactionException e) {
            txLogger.error(info.getGroupId(), info.getUnitId(), "before business code error");
            throw e;
        } catch (Throwable e) {
            // 4.5 业务执行失败
            txLogger.error(info.getGroupId(), info.getUnitId(), Transactions.TAG_TRANSACTION,
                    "business code error");
            dtxLocalControl.onBusinessCodeError(info, e);
            throw e;
        } finally {
            // 4.6 业务执行完毕
            dtxLocalControl.postBusinessCode(info);
        }
    }


}
