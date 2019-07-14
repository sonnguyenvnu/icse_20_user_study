/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
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
package com.roncoo.pay.trade.dao.impl;

import com.roncoo.pay.common.core.dao.impl.BaseDaoImpl;
import com.roncoo.pay.trade.dao.RpTradePaymentOrderDao;
import com.roncoo.pay.trade.entity.RpTradePaymentOrder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>åŠŸèƒ½è¯´æ˜Ž:å•†æˆ·æ”¯ä»˜è®¢å?•,daoå±‚å®žçŽ°ç±»</b>
 * @author  Peter
 * <a href="http://www.roncoo.com">é¾™æžœå­¦é™¢(www.roncoo.com)</a>
 */
@Repository("rpTradePaymentOrderDao")
public class RpTradePaymentOrderDaoImpl extends BaseDaoImpl<RpTradePaymentOrder> implements RpTradePaymentOrderDao{


    /**
     * æ ¹æ?®å•†æˆ·ç¼–å?·å?Šå•†æˆ·è®¢å?•å?·èŽ·å?–æ”¯ä»˜è®¢å?•ä¿¡æ?¯
     * @param merchantNo    å•†æˆ·ç¼–å?·
     * @param merchantOrderNo   å•†æˆ·è®¢å?•å?·
     * @return
     */
    @Override
    public RpTradePaymentOrder selectByMerchantNoAndMerchantOrderNo(String merchantNo, String merchantOrderNo) {
        Map<String , Object> paramMap = new HashMap<String , Object>();
        paramMap.put("merchantNo",merchantNo);
        paramMap.put("merchantOrderNo",merchantOrderNo);
        return super.getBy(paramMap);
    }

}
