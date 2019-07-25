package com.github.vole.message.handler;

import com.alibaba.fastjson.JSONObject;
import com.github.vole.common.utils.httpclient.HttpContacter;
import com.github.vole.common.utils.httpclient.HttpFeedback;
import com.github.vole.message.config.DingTalkPropertiesConfig;
import com.github.vole.message.template.DingTalkMsgTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * å?‘é€?é’‰é’‰æ¶ˆæ?¯é€»è¾‘
 */
@Slf4j
@Component
public class DingTalkMessageHandler {
    @Autowired
    private DingTalkPropertiesConfig dingTalkPropertiesConfig;

    /**
     * ä¸šåŠ¡å¤„ç?†
     *
     * @param text æ¶ˆæ?¯
     */
    public boolean process(String text) {
        String webhook = dingTalkPropertiesConfig.getWebhook();
        if (StringUtils.isBlank(webhook)) {
            log.error("é’‰é’‰é…?ç½®é”™è¯¯ï¼Œwebhookä¸ºç©º");
            return false;
        }

        DingTalkMsgTemplate dingTalkMsgTemplate = new DingTalkMsgTemplate();
        dingTalkMsgTemplate.setMsgtype("text");
        DingTalkMsgTemplate.TextBean textBean = new DingTalkMsgTemplate.TextBean();
        textBean.setContent(text);
        dingTalkMsgTemplate.setText(textBean);
        HttpFeedback result = null;
        try {
            result = HttpContacter.p().doPost(webhook,JSONObject.toJSONString(dingTalkMsgTemplate));
        } catch (Exception e) {
            log.error("é’‰é’‰å?‘é€?å¼‚å¸¸:{}", e.getMessage());
        }
        log.info("é’‰é’‰æ??é†’æˆ?åŠŸ,æŠ¥æ–‡å“?åº”:{}", result.getReceiptStr());
        return true;
    }

}
