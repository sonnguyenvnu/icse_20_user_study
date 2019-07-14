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
package com.codingapi.txlcn.tc.support;

import com.codingapi.txlcn.tc.core.DTXLocalControl;
import com.codingapi.txlcn.tc.core.DTXPropagationState;
import com.codingapi.txlcn.tc.core.TransactionCleanService;
import com.codingapi.txlcn.tc.support.resouce.TransactionResourceProxy;
import com.codingapi.txlcn.tc.txmsg.RpcExecuteService;
import com.codingapi.txlcn.txmsg.LCNCmdType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Description: BeanName 获�?�工具类
 * Company: CodingApi
 * Date: 2018/12/10
 *
 * @author lorne
 */
@Component
@Slf4j
public class TxLcnBeanHelper {

    /**
     * DTXLocalControl bean �??称格�?
     * control_%s_%s
     * transaction:�?缀 %s:事务类型（lcn,tcc,txc） %s:事务状�?(starting,running)
     */
    private static final String CONTROL_BEAN_NAME_FORMAT = "control_%s_%s";


    /**
     * message bean �??称格�?
     * rpc_%s_%s
     * message:�?缀 %s:事务类型（lcn,tcc,txc） %s:事务业务(commit,rollback)
     */
    private static final String RPC_BEAN_NAME_FORMAT = "rpc_%s_%s";


    /**
     * transaction bean �??称格�?
     * transaction_%s_%s
     * transaction:�?缀 %s:事务类型（lcn,tcc,txc)
     */
    private static final String TRANSACTION_BEAN_NAME_FORMAT = "transaction_%s";

    /**
     * Transaction Clean Service
     * %s: transaction type
     */
    private static final String TRANSACTION_CLEAN_SERVICE_NAME_FORMAT = "%sTransactionCleanService";


    private final ApplicationContext spring;

    @Autowired
    public TxLcnBeanHelper(ApplicationContext spring) {
        this.spring = spring;
    }


    private String getControlBeanName(String transactionType, DTXPropagationState lcnTransactionState) {
        return String.format(CONTROL_BEAN_NAME_FORMAT, transactionType, lcnTransactionState.getCode());
    }

    private String getRpcBeanName(String transactionType, LCNCmdType cmdType) {
        if (transactionType != null) {
            String name = String.format(RPC_BEAN_NAME_FORMAT, transactionType, cmdType.getCode());
            log.debug("getRpcBeanName->{}", name);
            return name;
        } else {
            String name = String.format(RPC_BEAN_NAME_FORMAT.replaceFirst("_%s", ""), cmdType.getCode());
            log.debug("getRpcBeanName->{}", name);
            return name;
        }
    }


    public TransactionResourceProxy loadTransactionResourceProxy(String beanName) {
        String name = String.format(TRANSACTION_BEAN_NAME_FORMAT, beanName);
        return spring.getBean(name, TransactionResourceProxy.class);
    }


    private DTXLocalControl loadDTXLocalControl(String beanName) {
        return spring.getBean(beanName, DTXLocalControl.class);
    }

    public DTXLocalControl loadDTXLocalControl(String transactionType, DTXPropagationState lcnTransactionState) {
        return loadDTXLocalControl(getControlBeanName(transactionType, lcnTransactionState));
    }

    public RpcExecuteService loadRpcExecuteService(String transactionType, LCNCmdType cmdType) {
        return loadRpcExecuteService(getRpcBeanName(transactionType, cmdType));
    }

    private RpcExecuteService loadRpcExecuteService(String beanName) {
        return spring.getBean(beanName, RpcExecuteService.class);
    }

    public TransactionCleanService loadTransactionCleanService(String transactionType) {
        return spring.getBean(String.format(TRANSACTION_CLEAN_SERVICE_NAME_FORMAT, transactionType), TransactionCleanService.class);
    }
}
