package com.github.vole.message.handler;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.vole.common.constants.CommonConstant;
import com.github.vole.common.validate.Assert;
import com.github.vole.message.config.SmsAliyunPropertiesConfig;
import com.github.vole.message.template.MobileMsgTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * é˜¿é‡Œå¤§é±¼çŸ­æ?¯æœ?åŠ¡å¤„ç?†
 */
@Slf4j
@Component(CommonConstant.ALIYUN_SMS)
public class SmsAliyunMessageHandler extends AbstractMessageHandler {
    @Autowired
    private SmsAliyunPropertiesConfig smsAliyunPropertiesConfig;
    private static final String PRODUCT = "Dysmsapi";
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * æ•°æ?®æ ¡éªŒ
     *
     * @param mobileMsgTemplate æ¶ˆæ?¯
     */
    @Override
    public void check(MobileMsgTemplate mobileMsgTemplate) {
        Assert.isBlank(mobileMsgTemplate.getMobile(), "æ‰‹æœºå?·ä¸?èƒ½ä¸ºç©º");
        Assert.isBlank(mobileMsgTemplate.getContext(), "çŸ­ä¿¡å†…å®¹ä¸?èƒ½ä¸ºç©º");
    }

    /**
     * ä¸šåŠ¡å¤„ç?†
     *
     * @param mobileMsgTemplate æ¶ˆæ?¯
     */
    @Override
    public boolean process(MobileMsgTemplate mobileMsgTemplate) {
        //å?¯è‡ªåŠ©è°ƒæ•´è¶…æ—¶æ—¶é—´
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //åˆ?å§‹åŒ–acsClient,æš‚ä¸?æ”¯æŒ?regionåŒ–
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAliyunPropertiesConfig.getAccessKey(), smsAliyunPropertiesConfig.getSecretKey());
        try {
            DefaultProfile.addEndpoint("cn-hou", "cn-hangzhou", PRODUCT, DOMAIN);
        } catch (ClientException e) {
            log.error("åˆ?å§‹åŒ–SDK å¼‚å¸¸", e);
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //ç»„è£…è¯·æ±‚å¯¹è±¡-å…·ä½“æ??è¿°è§?æŽ§åˆ¶å?°-æ–‡æ¡£éƒ¨åˆ†å†…å®¹
        SendSmsRequest request = new SendSmsRequest();
        //å¿…å¡«:å¾…å?‘é€?æ‰‹æœºå?·
        request.setPhoneNumbers(mobileMsgTemplate.getMobile());

        //å¿…å¡«:çŸ­ä¿¡ç­¾å??-å?¯åœ¨çŸ­ä¿¡æŽ§åˆ¶å?°ä¸­æ‰¾åˆ°
        request.setSignName(mobileMsgTemplate.getSignName());

        //å¿…å¡«:çŸ­ä¿¡æ¨¡æ?¿-å?¯åœ¨çŸ­ä¿¡æŽ§åˆ¶å?°ä¸­æ‰¾åˆ°
        request.setTemplateCode(smsAliyunPropertiesConfig.getChannels().get(mobileMsgTemplate.getTemplate()));

        //å?¯é€‰:æ¨¡æ?¿ä¸­çš„å?˜é‡?æ›¿æ?¢JSONä¸²,å¦‚æ¨¡æ?¿å†…å®¹ä¸º"äº²çˆ±çš„${name},æ‚¨çš„éªŒè¯?ç ?ä¸º${code}"
        request.setTemplateParam(mobileMsgTemplate.getContext());
        request.setOutId(mobileMsgTemplate.getMobile());

        //hint æ­¤å¤„å?¯èƒ½ä¼šæŠ›å‡ºå¼‚å¸¸ï¼Œæ³¨æ„?catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            log.info("çŸ­ä¿¡å?‘é€?å®Œæ¯•ï¼Œæ‰‹æœºå?·ï¼š{}ï¼Œè¿”å›žçŠ¶æ€?ï¼š{}", mobileMsgTemplate.getMobile(), sendSmsResponse.getCode());
        } catch (ClientException e) {
            log.error("å?‘é€?å¼‚å¸¸");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * å¤±è´¥å¤„ç?†
     *
     * @param mobileMsgTemplate æ¶ˆæ?¯
     */
    @Override
    public void fail(MobileMsgTemplate mobileMsgTemplate) {
        log.error("çŸ­ä¿¡å?‘é€?å¤±è´¥ -> ç½‘å…³ï¼š{} -> æ‰‹æœºå?·ï¼š{}", mobileMsgTemplate.getType(), mobileMsgTemplate.getMobile());
    }
}
