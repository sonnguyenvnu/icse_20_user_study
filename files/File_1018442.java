package com.xiaolyuh.mq.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.xiaolyuh.constants.RabbitConstants;
import com.xiaolyuh.mq.message.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * æ­»ä¿¡é˜Ÿåˆ—å¤„ç?†æ¶ˆæ?¯
 *
 * @author yuhao.wang
 */
@Service
public class SendMessageListener {

    private final Logger logger = LoggerFactory.getLogger(SendMessageListener.class);

//    @RabbitListener(queues = RabbitConstants.QUEUE_NAME_DEAD_QUEUE)
    public void process(SendMessage sendMessage, Channel channel, Message message) throws Exception {
        logger.info("[{}]å¤„ç?†æ­»ä¿¡é˜Ÿåˆ—æ¶ˆæ?¯é˜Ÿåˆ—æŽ¥æ”¶æ•°æ?®ï¼Œæ¶ˆæ?¯ä½“ï¼š{}", RabbitConstants.QUEUE_NAME_DEAD_QUEUE, JSON.toJSONString(sendMessage));

        System.out.println(message.getMessageProperties().getDeliveryTag());

        try {
            // å?‚æ•°æ ¡éªŒ
            Assert.notNull(sendMessage, "sendMessage æ¶ˆæ?¯ä½“ä¸?èƒ½ä¸ºNULL");

            // TODO å¤„ç?†æ¶ˆæ?¯

            // ç¡®è®¤æ¶ˆæ?¯å·²ç»?æ¶ˆè´¹æˆ?åŠŸ
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error("MQæ¶ˆæ?¯å¤„ç?†å¼‚å¸¸ï¼Œæ¶ˆæ?¯ä½“:{}", message.getMessageProperties().getCorrelationIdString(), JSON.toJSONString(sendMessage), e);

            // ç¡®è®¤æ¶ˆæ?¯å·²ç»?æ¶ˆè´¹æ¶ˆè´¹å¤±è´¥ï¼Œå°†æ¶ˆæ?¯å?‘ç»™ä¸‹ä¸€ä¸ªæ¶ˆè´¹è€…
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
