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
package com.codingapi.txlcn.tm.support.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.common.exception.TransactionStateException;
import com.codingapi.txlcn.common.exception.TxManagerException;
import com.codingapi.txlcn.tm.support.db.domain.TxException;
import com.codingapi.txlcn.tm.support.db.jpa.TxExceptionRepository;
import com.codingapi.txlcn.tm.support.restapi.ao.WriteTxExceptionDTO;
import com.codingapi.txlcn.tm.support.restapi.vo.ExceptionInfo;
import com.codingapi.txlcn.tm.support.restapi.vo.ExceptionList;
import com.codingapi.txlcn.tm.support.service.TxExceptionService;
import com.codingapi.txlcn.tm.support.txex.TxExceptionListener;
import com.codingapi.txlcn.tm.txmsg.MessageCreator;
import com.codingapi.txlcn.txmsg.RpcClient;
import com.codingapi.txlcn.txmsg.dto.MessageDto;
import com.codingapi.txlcn.txmsg.exception.RpcException;
import com.codingapi.txlcn.txmsg.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 * Date: 2018/12/18
 *
 * @author ujued
 */
@Service
@Slf4j
public class TxExceptionServiceImpl implements TxExceptionService {
    
    private final RpcClient rpcClient;
    
    private final TxExceptionListener txExceptionListener;
    
    private final TxExceptionRepository txExceptionRepository;
    
    @Autowired
    public TxExceptionServiceImpl(RpcClient rpcClient,
                                  TxExceptionListener txExceptionListener,
                                  TxExceptionRepository txExceptionRepository) {
        this.rpcClient = rpcClient;
        this.txExceptionListener = txExceptionListener;
        this.txExceptionRepository = txExceptionRepository;
    }
    
    @Override
    public void writeTxException(WriteTxExceptionDTO writeTxExceptionReq) {
        log.info("write tx_exception. {}", writeTxExceptionReq);
        TxException txException = new TxException();
        txException.setCreateTime(new Date());
        txException.setGroupId(writeTxExceptionReq.getGroupId());
        txException.setTransactionState(writeTxExceptionReq.getTransactionState());
        txException.setUnitId(writeTxExceptionReq.getUnitId());
        txException.setRegistrar(writeTxExceptionReq.getRegistrar());
        txException.setModId(writeTxExceptionReq.getModId());
        txException.setExState((short) 0);
        txException.setRemark(writeTxExceptionReq.getRemark());
        txExceptionRepository.save(txException);
        txExceptionListener.onException(txException);
    }
    
    @Override
    public int transactionState(String groupId) {
        log.debug("transactionState > groupId: {}", groupId);
        List<Integer> lists = txExceptionRepository.getTransactionStateByGroupId(groupId,PageRequest.of(0, 1));
        if (lists.size()<=0) {
            return -1;
        }
        return lists.get(0);
    }
    
    @Override
    @Transactional
    public ExceptionList exceptionList(Integer page, Integer limit, Integer exState, String keyword, Integer registrar) {
        if (Objects.isNull(page) || page <= 0) {
            page = 1;
        }
        if (Objects.isNull(limit) || limit < 1) {
            limit = 10;
        }
        // 组装�?�件�?�数
        Specification<TxException> specification = (Specification<TxException>) (root, cq, cb) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if ((Objects.nonNull(exState) && exState != -2) && (Objects.nonNull(registrar) && registrar != -2)) {
                predicatesList.add(cb.equal(root.get("exState"), exState));
                predicatesList.add(cb.equal(root.get("registrar"), registrar));
            } else if (Objects.nonNull(exState) && exState != -2) {
                predicatesList.add(cb.equal(root.get("exState"), exState));
            } else if (Objects.nonNull(registrar) && registrar != -2) {
                predicatesList.add(cb.equal(root.get("registrar"), registrar));
            }
            Predicate[] predicates = new Predicate[predicatesList.size()];
            return cb.and(predicatesList.toArray(predicates));
        };
        
        Page<TxException> pageTxExceptions = txExceptionRepository.findAll(specification, PageRequest.of(page-1, limit));
        List<TxException> txExceptions = pageTxExceptions.getContent();
        
        List<ExceptionInfo> exceptionInfoList = new ArrayList<>(txExceptions.size());
        for (TxException txException : txExceptions) {
            ExceptionInfo exceptionInfo = new ExceptionInfo();
            BeanUtils.copyProperties(txException, exceptionInfo);
            
            // 如果状�?为解决，决定查下模�?�的日志�?�最终判断异常状�?
            if (txException.getExState() != 1) {
                try {
                    JSONObject transactionInfo = getTransactionInfo(exceptionInfo.getGroupId(), exceptionInfo.getUnitId());
                    exceptionInfo.setTransactionInfo(transactionInfo);
                } catch (TransactionStateException e) {
                    if (e.getCode() == TransactionStateException.NON_ASPECT) {
                        // �?存在异常日志，正常
                        txExceptionRepository.changeExState(txException.getId(), (short) 1);
                        exceptionInfo.setExState((short) 1);
                    }
                }
            }
            exceptionInfoList.add(exceptionInfo);
        }
        ExceptionList exceptionList = new ExceptionList();
        exceptionList.setTotal(pageTxExceptions.getTotalElements());
        exceptionList.setExceptions(exceptionInfoList);
        return exceptionList;
    }
    
    @Override
    public JSONObject getTransactionInfo(String groupId, String unitId) throws TransactionStateException {
        TxException exception = txExceptionRepository.findByGroupIdAndUnitId(groupId, unitId);
        if (Objects.isNull(exception)) {
            throw new TransactionStateException("non exists aspect log", TransactionStateException.NON_ASPECT);
        }
        List<String> remoteKeys = rpcClient.remoteKeys(exception.getModId());
        if (remoteKeys.isEmpty()) {
            throw new TransactionStateException("non mod found", TransactionStateException.NON_MOD);
        }
        try {
            for (String remoteKey : remoteKeys) {
                MessageDto messageDto = rpcClient.request(remoteKey, MessageCreator.getAspectLog(groupId, unitId), 5000);
                if (MessageUtils.statusOk(messageDto)) {
                    return messageDto.loadBean(JSONObject.class);
                }
            }
            throw new TransactionStateException("non exists aspect log", TransactionStateException.NON_ASPECT);
        } catch (RpcException e) {
            throw new TransactionStateException(e, TransactionStateException.RPC_ERR);
        }
    }
    
    @Override
    public void deleteExceptions(List<Long> ids) {
        for (Long id : ids) {
            txExceptionRepository.deleteById(id);
        }
    }
    
    @Override
    public void deleteTransactionInfo(String groupId, String unitId, String modId) throws TxManagerException {
        List<String> remoteKeys = rpcClient.remoteKeys(modId);
        if (remoteKeys.isEmpty()) {
            throw new TxManagerException("�?存在的模�?�");
        }
        try {
            for (String remoteKey : remoteKeys) {
                MessageDto messageDto = rpcClient.request(remoteKey, MessageCreator.deleteAspectLog(groupId, unitId), 5000);
                if (MessageUtils.statusOk(messageDto)) {
                    return;
                }
            }
        } catch (RpcException e) {
            throw new TxManagerException(e.getMessage());
        }
    }
}
