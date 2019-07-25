package com.myimooc.rabbitmq.ha.service;

import com.myimooc.rabbitmq.entity.Order;
import com.myimooc.rabbitmq.ha.constant.Constants;
import com.myimooc.rabbitmq.ha.dao.mapper.BrokerMessageLogMapper;
import com.myimooc.rabbitmq.ha.dao.mapper.OrderMapper;
import com.myimooc.rabbitmq.ha.dao.po.BrokerMessageLogPO;
import com.myimooc.rabbitmq.ha.producer.OrderSender;
import com.myimooc.rabbitmq.ha.util.FastJsonConvertUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <br>
 * æ ‡é¢˜: è®¢å?•æœ?åŠ¡<br>
 * æ??è¿°: è®¢å?•æœ?åŠ¡<br>
 * æ—¶é—´: 2018/09/07<br>
 *
 * @author zc
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;
    @Autowired
    private OrderSender orderSender;

    /**
     * åˆ›å»ºè®¢å?•
     *
     * @param order è®¢å?•
     */
    public void create(Order order) {
        // å½“å‰?æ—¶é—´
        Date orderTime = new Date();
        // ä¸šåŠ¡æ•°æ?®å…¥åº“
        this.orderMapper.insert(order);
        // æ¶ˆæ?¯æ—¥å¿—å…¥åº“
        BrokerMessageLogPO messageLogPO = new BrokerMessageLogPO();
        messageLogPO.setMessageId(order.getMessageId());
        messageLogPO.setMessage(FastJsonConvertUtils.convertObjectToJson(order));
        messageLogPO.setTryCount(0);
        messageLogPO.setStatus(Constants.OrderSendStatus.SENDING);
        messageLogPO.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
        this.brokerMessageLogMapper.insert(messageLogPO);
        // å?‘é€?æ¶ˆæ?¯
        this.orderSender.send(order);
    }
}
