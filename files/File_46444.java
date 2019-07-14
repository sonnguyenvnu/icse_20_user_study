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
package com.codingapi.txlcn.tc.core.checking;

import com.codingapi.txlcn.common.exception.*;
import com.codingapi.txlcn.logger.TxLogger;
import com.codingapi.txlcn.tc.txmsg.TMReporter;
import com.codingapi.txlcn.tc.core.template.TransactionCleanTemplate;
import com.codingapi.txlcn.txmsg.params.TxExceptionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 分布�?事务异常处�?�器类
 * Date: 2018/12/20
 *
 * @author ujued
 * @see DTXExceptionHandler
 */
@Component
@Slf4j
public class DefaultDTXExceptionHandler implements DTXExceptionHandler {

    private static final TxLogger txLogger = TxLogger.newLogger(DefaultDTXExceptionHandler.class);

    private final TransactionCleanTemplate transactionCleanTemplate;

    private final TMReporter tmReporter;

    @Autowired
    public DefaultDTXExceptionHandler(TransactionCleanTemplate transactionCleanTemplate, TMReporter tmReporter) {
        this.transactionCleanTemplate = transactionCleanTemplate;
        this.tmReporter = tmReporter;
    }

    @Override
    public void handleCreateGroupBusinessException(Object params, Throwable ex) throws TransactionException {
        throw new TransactionException(ex);
    }

    @Override
    public void handleCreateGroupMessageException(Object params, Throwable ex) throws TransactionException {
        throw new TransactionException(ex);
    }

    @Override
    public void handleJoinGroupBusinessException(Object params, Throwable ex) throws TransactionException {
        List paramList = (List) params;
        String groupId = (String) paramList.get(0);
        String unitId = (String) paramList.get(1);
        String unitType = (String) paramList.get(2);
        try {
            transactionCleanTemplate.clean(groupId, unitId, unitType, 0);
        } catch (TransactionClearException e) {
            txLogger.error(groupId, unitId, "join group", "clean [{}]transaction fail.", unitType);
        }
        throw new TransactionException(ex);
    }

    @Override
    public void handleJoinGroupMessageException(Object params, Throwable ex) throws TransactionException {
        throw new TransactionException(ex);
    }

    @Override
    public void handleNotifyGroupBusinessException(Object params, Throwable ex) {
        List paramList = (List) params;
        String groupId = (String) paramList.get(0);
        int state = (int) paramList.get(1);
        String unitId = (String) paramList.get(2);
        String transactionType = (String) paramList.get(3);

        //用户强制回滚.
        if (ex instanceof UserRollbackException) {
            state = 0;
        }
        if ((ex.getCause() != null && ex.getCause() instanceof UserRollbackException)) {
            state = 0;
        }

        // 结�?�事务
        try {
            transactionCleanTemplate.clean(groupId, unitId, transactionType, state);
        } catch (TransactionClearException e) {
            txLogger.error(groupId, unitId, "notify group", "{} > clean transaction error.", transactionType);
        }
    }

    @Override
    public void handleNotifyGroupMessageException(Object params, Throwable ex) {
        // 当0 时候
        List paramList = (List) params;
        String groupId = (String) paramList.get(0);
        int state = (int) paramList.get(1);
        if (state == 0) {
            handleNotifyGroupBusinessException(params, ex);
            return;
        }

        // 按状�?正常结�?�事务（切�?�补�?�记录将�?留）
        // TxManager 存在请求异常或者�?应异常两�?情况。当请求异常时这里的业务需�?补�?�，当�?应异常的时候需�?�?�状�?的事务清�?�。
        // 请求异常时
        //     �?�与放会根�?�上报补�?�记录�?�事务的�??交。
        // �?应异常时
        //     �?�与�??会正常�??交事务，本地业务�??示事务。

        // 该两�?情况下补�?�信�?��?��?�以忽略,�?�直接把本地补�?�记录数�?�删除。


        String unitId = (String) paramList.get(2);
        String transactionType = (String) paramList.get(3);
        try {
            transactionCleanTemplate.cleanWithoutAspectLog(groupId, unitId, transactionType, state);
        } catch (TransactionClearException e) {
            txLogger.error(groupId, unitId, "notify group", "{} > cleanWithoutAspectLog transaction error.", transactionType);
        }

        // 上报Manager，上报直到�?功.
        tmReporter.reportTransactionState(groupId, null, TxExceptionParams.NOTIFY_GROUP_ERROR, state);
    }
}
