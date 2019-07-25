package com.github.vole.message.listener;

import com.github.vole.common.constants.MqQueueConstant;
import com.github.vole.message.handler.SmsMessageHandler;
import com.github.vole.message.template.MobileMsgTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ç›‘å?¬æœ?åŠ¡çŠ¶æ€?æ”¹å?˜å?‘é€?è¯·æ±‚
 */
@Slf4j
@Component
@RabbitListener(queues = MqQueueConstant.MOBILE_SERVICE_STATUS_CHANGE)
public class MobileServiceChangeReceiveListener {
    @Autowired
    private Map<String, SmsMessageHandler> messageHandlerMap;


    @RabbitHandler
    public void receive(MobileMsgTemplate mobileMsgTemplate) {
        long startTime = System.currentTimeMillis();
        log.info("æ¶ˆæ?¯ä¸­å¿ƒæŽ¥æ”¶åˆ°çŸ­ä¿¡å?‘é€?è¯·æ±‚-> æ‰‹æœºå?·ï¼š{} -> ä¿¡æ?¯ä½“ï¼š{} ", mobileMsgTemplate.getMobile(), mobileMsgTemplate.getContext());
        String channel = mobileMsgTemplate.getChannel();
        SmsMessageHandler messageHandler = messageHandlerMap.get(channel);
        if (messageHandler == null) {
            log.error("æ²¡æœ‰æ‰¾åˆ°æŒ‡å®šçš„è·¯ç”±é€šé?“ï¼Œä¸?è¿›è¡Œå?‘é€?å¤„ç?†å®Œæ¯•ï¼?");
            return;
        }

        messageHandler.execute(mobileMsgTemplate);
        long useTime = System.currentTimeMillis() - startTime;
        log.info("è°ƒç”¨ {} çŸ­ä¿¡ç½‘å…³å¤„ç?†å®Œæ¯•ï¼Œè€—æ—¶ {}æ¯«ç§’", mobileMsgTemplate.getType(), useTime);
    }
}
