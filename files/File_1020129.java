package com.myimooc.small.advanced.rest;

import java.util.Map;
import java.util.Objects;

import com.myimooc.small.advanced.domain.EventMessage;
import com.myimooc.small.advanced.domain.NewsMessage;
import com.myimooc.small.advanced.domain.TextMessage;
import com.myimooc.small.advanced.util.MessageUtils;
import com.myimooc.small.advanced.util.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * å¤„ç?†æ¶ˆæ?¯è¯·æ±‚ä¸Žå“?åº”
 *
 * @author ZhangCheng on 2017-08-11
 */
@RestController
public class MessageRest {

    private static final Logger logger = LoggerFactory.getLogger(MessageRest.class);
    private static final String KEY_1 = "1";
    private static final String KEY_2 = "2";
    private static final String KEY_3 = "3";
    private static final String KEY_FY = "ç¿»è¯‘";
    private static final String KEY_MENU_CN = "ï¼Ÿ";
    private static final String KEY_MENU_EN = "?";

    /**
     * æŽ¥æ”¶å¾®ä¿¡æœ?åŠ¡å™¨å?‘é€?çš„POSTè¯·æ±‚
     *
     * @throws Exception
     */
    @PostMapping("textmessage")
    public Object textmessage(TextMessage msg) throws Exception {

        logger.info("è¯·æ±‚å?‚æ•°ï¼š{}", msg.toString());

        // æ–‡æœ¬æ¶ˆæ?¯
        if (Objects.equals(MessageUtils.MESSAGE_TEXT, msg.getMsgType())) {
            TextMessage textMessage = new TextMessage();
            // å…³é”®å­— 1
            if (Objects.equals(KEY_1, msg.getContent())) {
                textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.firstMenu());
                return textMessage;
            }
            // å…³é”®å­— 2
            if (Objects.equals(KEY_2, msg.getContent())) {
                NewsMessage newsMessage = MessageUtils.initNewsMessage(msg.getToUserName(), msg.getFromUserName());
                return newsMessage;
            }
            // å…³é”®å­— 3
            if (Objects.equals(KEY_3, msg.getContent())) {
                textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.threeMenu());
                return textMessage;
            }
            // å…³é”®å­— ç¿»è¯‘
            if (msg.getContent().startsWith(KEY_FY)) {
                String word = msg.getContent().replaceAll("^ç¿»è¯‘", "").trim();
                if ("".equals(word)) {
                    textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.threeMenu());
                    return textMessage;
                }
                textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), WeixinUtils.translate(word));
                return textMessage;
            }
            // å…³é”®å­— ï¼Ÿ? è°ƒå‡ºè?œå?•
            if (Objects.equals(KEY_MENU_CN, msg.getContent()) || Objects.equals(KEY_MENU_EN, msg.getContent())) {
                textMessage = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
                return textMessage;
            }

            // é?žå…³é”®å­—
            textMessage.setFromUserName(msg.getToUserName());
            textMessage.setToUserName(msg.getFromUserName());
            textMessage.setMsgType(MessageUtils.MESSAGE_TEXT);
            textMessage.setCreateTime(System.currentTimeMillis() + "");
            textMessage.setContent("æ‚¨å?‘é€?çš„æ¶ˆæ?¯æ˜¯ï¼š" + msg.getContent());
            return textMessage;
        }
        return null;
    }

    /**
     * æŽ¥æ”¶å¾®ä¿¡æœ?åŠ¡å™¨å?‘é€?çš„POSTè¯·æ±‚
     */
    @PostMapping("eventmessage")
    public Object eventmessage(Map<String, String> param) {

        EventMessage msg = new EventMessage();
        BeanUtils.copyProperties(param, msg);
        // äº‹ä»¶æŽ¨é€?
        if (Objects.equals(MessageUtils.MESSAGE_EVENT, msg.getMsgType())) {
            // å…³æ³¨
            if (Objects.equals(MessageUtils.MESSAGE_SUBSCRIBE, msg.getEvent())) {
                TextMessage text = new TextMessage();
                text = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
                return text;
            }
            // è?œå?• ç‚¹å‡»ç±»åž‹
            if (Objects.equals(MessageUtils.MESSAGE_CLICK, msg.getEvent())) {
                TextMessage text = new TextMessage();
                text = MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), MessageUtils.menuText());
                return text;
            }
            // è?œå?• è§†å›¾ç±»åž‹
            if (Objects.equals(MessageUtils.MESSAGE_VIEW, msg.getEvent())) {
                String url = param.get("EventKey");
                return MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), url);
            }
            // è?œå?• æ‰«ç ?äº‹ä»¶
            if (Objects.equals(MessageUtils.MESSAGE_SCANCODE, msg.getEvent())) {
                String key = param.get("EventKey");
                return MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), key);
            }
            // è?œå?• åœ°ç?†ä½?ç½®
            if (Objects.equals(MessageUtils.MESSAGE_LOCATION, msg.getEvent())) {
                String label = param.get("Label");
                return MessageUtils.initText(msg.getToUserName(), msg.getFromUserName(), label);
            }
        }
        return "no message";
    }

}
