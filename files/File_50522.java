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

package org.dromara.hmily.demo.springcloud.inventory.service.impl;

import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.dromara.hmily.demo.springcloud.inventory.dto.InventoryDTO;
import org.dromara.hmily.demo.springcloud.inventory.entity.InventoryDO;
import org.dromara.hmily.demo.springcloud.inventory.mapper.InventoryMapper;
import org.dromara.hmily.demo.springcloud.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * InventoryServiceImpl.
 *
 * @author xiaoyu
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final InventoryMapper inventoryMapper;

    @Autowired(required = false)
    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    /**
     * 扣�?库存�?作.
     * 这一个tcc接�?�
     *
     * @param inventoryDTO 库存DTO对象
     * @return true
     */
    @Override
    @Hmily(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public Boolean decrease(InventoryDTO inventoryDTO) {
        LOGGER.info("==========springcloud调用扣�?库存decrease===========");
        inventoryMapper.decrease(inventoryDTO);
        return true;
    }

    /**
     * 获�?�商�?库存信�?�.
     *
     * @param productId 商�?id
     * @return InventoryDO
     */
    @Override
    public InventoryDO findByProductId(String productId) {
        return inventoryMapper.findByProductId(productId);
    }

    @Override
    @Hmily(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    @Transactional
    public Boolean mockWithTryException(InventoryDTO inventoryDTO) {
        //这里是模拟异常所以就直接抛出异常了
        throw new HmilyRuntimeException("库存扣�?异常�?");
    }

    @Override
    @Hmily(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    @Transactional(rollbackFor = Exception.class)
    public Boolean mockWithTryTimeout(InventoryDTO inventoryDTO) {
        try {
            //模拟延迟 当�?线程暂�?�10秒
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("==========springcloud调用扣�?库存mockWithTryTimeout===========");
        final int decrease = inventoryMapper.decrease(inventoryDTO);
        if (decrease != 1) {
            throw new HmilyRuntimeException("库存�?足");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmMethodTimeout(InventoryDTO inventoryDTO) {
        try {
            //模拟延迟 当�?线程暂�?�11秒
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("==========Springcloud调用扣�?库存确认方法===========");
        inventoryMapper.decrease(inventoryDTO);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmMethodException(InventoryDTO inventoryDTO) {
        LOGGER.info("==========Springcloud调用扣�?库存确认方法===========");
        final int decrease = inventoryMapper.decrease(inventoryDTO);
        if (decrease != 1) {
            throw new HmilyRuntimeException("库存�?足");
        }
        return true;
        // throw new TccRuntimeException("库存扣�?确认异常�?");
    }


    public Boolean confirmMethod(InventoryDTO inventoryDTO) {
        LOGGER.info("==========Springcloud调用扣�?库存确认方法===========");
        final int rows = inventoryMapper.confirm(inventoryDTO);
        return true;
    }

    public Boolean cancelMethod(InventoryDTO inventoryDTO) {
        LOGGER.info("==========Springcloud调用扣�?库存�?�消方法===========");
        int rows = inventoryMapper.cancel(inventoryDTO);
        return true;
    }

}
