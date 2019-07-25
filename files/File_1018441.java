package com.xiaolyuh.mq.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.xiaolyuh.constants.RabbitConstants;
import com.xiaolyuh.mq.message.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * å»¶è¿Ÿé˜Ÿåˆ—æ¶ˆè´¹
 *
 * @author yuhao.wang
 */
@Service
public class DeadMessageListener {

    private final Logger logger = LoggerFactory.getLogger(DeadMessageListener.class);

    @RabbitListener(queues = RabbitConstants.QUEUE_NAME_DEAD_QUEUE)
    public void process(SendMessage sendMessage, Channel channel, Message message) throws Exception {
        logger.info("[{}]å¤„ç?†å»¶è¿Ÿé˜Ÿåˆ—æ¶ˆæ?¯é˜Ÿåˆ—æŽ¥æ”¶æ•°æ?®ï¼Œæ¶ˆæ?¯ä½“ï¼š{}", RabbitConstants.QUEUE_NAME_SEND_COUPON, JSON.toJSONString(sendMessage));

        System.out.println(message.getMessageProperties().getDeliveryTag());

        try {
            // å?‚æ•°æ ¡éªŒ
            Assert.notNull(sendMessage, "sendMessage æ¶ˆæ?¯ä½“ä¸?èƒ½ä¸ºNULL");

            // TODO å¤„ç?†æ¶ˆæ?¯

            // ç¡®è®¤æ¶ˆæ?¯å·²ç»?æ¶ˆè´¹æˆ?åŠŸ
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error("MQæ¶ˆæ?¯å¤„ç?†å¼‚å¸¸ï¼Œæ¶ˆæ?¯ä½“:{}", message.getMessageProperties().getCorrelationIdString(), JSON.toJSONString(sendMessage), e);

            try {
                // TODO ä¿?å­˜æ¶ˆæ?¯åˆ°æ•°æ?®åº“

                // ç¡®è®¤æ¶ˆæ?¯å·²ç»?æ¶ˆè´¹æˆ?åŠŸ
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception dbe) {
                logger.error("ä¿?å­˜å¼‚å¸¸MQæ¶ˆæ?¯åˆ°æ•°æ?®åº“å¼‚å¸¸ï¼Œæ”¾åˆ°æ­»æ€§é˜Ÿåˆ—ï¼Œæ¶ˆæ?¯ä½“ï¼š{}", JSON.toJSONString(sendMessage), dbe);
                // ç¡®è®¤æ¶ˆæ?¯å°†æ¶ˆæ?¯æ”¾åˆ°æ­»ä¿¡é˜Ÿåˆ—
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        }
    }
}
