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

package org.dromara.hmily.demo.springcloud.order.service.impl;

import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.dromara.hmily.demo.springcloud.order.client.AccountClient;
import org.dromara.hmily.demo.springcloud.order.client.InventoryClient;
import org.dromara.hmily.demo.springcloud.order.dto.AccountDTO;
import org.dromara.hmily.demo.springcloud.order.dto.InventoryDTO;
import org.dromara.hmily.demo.springcloud.order.entity.Order;
import org.dromara.hmily.demo.springcloud.order.enums.OrderStatusEnum;
import org.dromara.hmily.demo.springcloud.order.mapper.OrderMapper;
import org.dromara.hmily.demo.springcloud.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * PaymentServiceImpl.
 *
 * @author xiaoyu
 */
@Service
@SuppressWarnings("all")
public class PaymentServiceImpl implements PaymentService {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final OrderMapper orderMapper;

    private final AccountClient accountClient;

    private final InventoryClient inventoryClient;

    @Autowired(required = false)
    public PaymentServiceImpl(OrderMapper orderMapper,
                              AccountClient accountClient,
                              InventoryClient inventoryClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
        this.inventoryClient = inventoryClient;
    }

    @Override
    @Hmily(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void makePayment(Order order) {
        order.setStatus(OrderStatusEnum.PAYING.getCode());
        orderMapper.update(order);
        //æ£€æŸ¥æ•°æ?®
        final BigDecimal accountInfo = accountClient.findByUserId(order.getUserId());

        final Integer inventoryInfo = inventoryClient.findByProductId(order.getProductId());

        if (accountInfo.compareTo(order.getTotalAmount()) < 0) {
            throw new HmilyRuntimeException("ä½™é¢?ä¸?è¶³ï¼?");
        }

        if (inventoryInfo < order.getCount()) {
            throw new HmilyRuntimeException("åº“å­˜ä¸?è¶³ï¼?");
        }

        //æ‰£é™¤ç”¨æˆ·ä½™é¢?

        //è¿›å…¥æ‰£å‡?åº“å­˜æ“?ä½œ
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        inventoryClient.decrease(inventoryDTO);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        LOGGER.debug("===========æ‰§è¡Œspringcloudæ‰£å‡?èµ„é‡‘æŽ¥å?£==========");
        accountClient.payment(accountDTO);
    }

    @Override
    @Hmily(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryException(Order order) {
        LOGGER.debug("===========æ‰§è¡Œspringcloud  mockPaymentInventoryWithTryException æ‰£å‡?èµ„é‡‘æŽ¥å?£==========");
        order.setStatus(OrderStatusEnum.PAYING.getCode());
        orderMapper.update(order);
        //æ‰£é™¤ç”¨æˆ·ä½™é¢?
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        accountClient.payment(accountDTO);
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        inventoryClient.mockWithTryException(inventoryDTO);
        return "success";
    }

    @Override
    @Hmily(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryTimeout(Order order) {
        LOGGER.debug("===========æ‰§è¡Œspringcloud  mockPaymentInventoryWithTryTimeout æ‰£å‡?èµ„é‡‘æŽ¥å?£==========");
        order.setStatus(OrderStatusEnum.PAYING.getCode());
        orderMapper.update(order);
        //æ‰£é™¤ç”¨æˆ·ä½™é¢?
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        accountClient.payment(accountDTO);
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        inventoryClient.mockWithTryTimeout(inventoryDTO);
        return "success";
    }

    public void confirmOrderStatus(Order order) {
        order.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
        orderMapper.update(order);
        LOGGER.info("=========è¿›è¡Œè®¢å?•confirmæ“?ä½œå®Œæˆ?================");
    }

    public void cancelOrderStatus(Order order) {
        order.setStatus(OrderStatusEnum.PAY_FAIL.getCode());
        orderMapper.update(order);
        LOGGER.info("=========è¿›è¡Œè®¢å?•cancelæ“?ä½œå®Œæˆ?================");
    }

}
