package com.zone.weixin4j.service.impl;

import com.zone.weixin4j.dispatcher.WeixinMessageDispatcher;
import com.zone.weixin4j.exception.HttpResponseException;
import com.zone.weixin4j.exception.MessageInterceptorException;
import com.zone.weixin4j.exception.WeixinException;
import com.zone.weixin4j.request.WeixinRequest;
import com.zone.weixin4j.response.SingleResponse;
import com.zone.weixin4j.response.WeixinResponse;
import com.zone.weixin4j.service.WxService;
import com.zone.weixin4j.socket.WeixinResponseEncoder;
import com.zone.weixin4j.type.EncryptType;
import com.zone.weixin4j.util.AesToken;
import com.zone.weixin4j.util.MessageUtil;
import com.zone.weixin4j.util.ServerToolkits;
import com.zone.weixin4j.xml.EncryptMessageHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yz on 2017/3/14.
 * WxServiceImpl
 */

@Component
@DependsOn({"weiXin4jContextAware", "weixinMessageDispatcher"})
public class WxServiceImpl implements WxService {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private WeixinMessageDispatcher messageDispatcher;

    @Autowired
    private WeixinResponseEncoder weixinResponseEncoder;

    /**
     * å¤„ç?†Request
     *
     * @throws WeixinException
     * @throws HttpResponseException
     */
    @Override
    public WeixinResponse processRequest(String uri, String encrypt_type, String echostr, String timestamp, String nonce, String signature, String msg_signature, String messageContent, AesToken aesToken, HttpServletRequest request) throws WeixinException, HttpResponseException, MessageInterceptorException {
        EncryptType encryptType = !StringUtils.isEmpty(encrypt_type) ? EncryptType.valueOf(encrypt_type.toUpperCase()) : EncryptType.RAW;
        String encryptContent = null;
        if (!ServerToolkits.isBlank(messageContent) && encryptType == EncryptType.AES) {
            if (ServerToolkits.isBlank(aesToken.getAesKey())) {
                logger.error("EncodingAESKey not be empty in safety(AES) mode");
            }
            EncryptMessageHandler encryptHandler = EncryptMessageHandler.parser(messageContent);
            encryptContent = encryptHandler.getEncryptContent();
            /**
             * ä¼?ä¸šå?·ç¬¬ä¸‰æ–¹å¥—ä»¶ â•®ï¼ˆâ•¯_â•°ï¼‰â•­
             */
            if (aesToken.getWeixinId().startsWith("tj")) {
                aesToken = new AesToken(encryptHandler.getToUserName(), aesToken.getToken(), aesToken.getAesKey());
            }
            messageContent = MessageUtil.aesDecrypt(aesToken.getWeixinId(), aesToken.getAesKey(), encryptContent);
        }
        WeixinRequest weixinRequest = new WeixinRequest(uri, encryptType, echostr, timestamp, nonce, signature, msg_signature, messageContent, encryptContent, aesToken).setHttpServletRequest(request);

        if (aesToken == null || (ServerToolkits.isBlank(weixinRequest.getSignature()) && ServerToolkits.isBlank(weixinRequest.getMsgSignature()))) {
            throw new HttpResponseException(HttpResponseException.HttpResponseStatus.BAD_REQUEST);
        }

        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.toString())) {
            return doGet(weixinRequest);
        } else if (request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())) {
            return doPost(weixinRequest);
        } else {
            return otherwise(weixinRequest);
        }
    }

    /**
     * å¤„ç?†Getè¯·æ±‚
     *
     * @throws WeixinException
     * @throws HttpResponseException
     */
    protected WeixinResponse doGet(WeixinRequest request) throws WeixinException, HttpResponseException {
        if (!ServerToolkits.isBlank(request.getSignature()) && MessageUtil.signature(request.getAesToken().getToken(), request.getTimeStamp(), request.getNonce()).equals(request.getSignature())) {
            return new SingleResponse(request.getEchoStr());
        }
        // XMLæ¶ˆæ?¯ç­¾å??éªŒè¯?
        if (!ServerToolkits.isBlank(request.getMsgSignature()) && MessageUtil.signature(request.getAesToken().getToken(), request.getTimeStamp(), request.getNonce(), request.getEchoStr()).equals(request.getMsgSignature())) {
            return new SingleResponse(MessageUtil.aesDecrypt(null, request.getAesToken().getAesKey(), request.getEchoStr()));
        }
        throw new HttpResponseException(HttpResponseException.HttpResponseStatus.FORBIDDEN);
    }

    /**
     * å¤„ç?†Postè¯·æ±‚
     *
     * @throws WeixinException
     * @throws HttpResponseException
     */
    protected WeixinResponse doPost(WeixinRequest request) throws HttpResponseException, MessageInterceptorException, WeixinException {
        // URLå?‚æ•°ç­¾å??éªŒè¯?
        if (!ServerToolkits.isBlank(request.getSignature()) && !MessageUtil.signature(request.getAesToken().getToken(), request.getTimeStamp(), request.getNonce()).equals(request.getSignature())) {
            throw new HttpResponseException(HttpResponseException.HttpResponseStatus.FORBIDDEN);
        }
        // XMLæ¶ˆæ?¯ç­¾å??éªŒè¯?
        if (request.getEncryptType() == EncryptType.AES && !MessageUtil.signature(request.getAesToken().getToken(), request.getTimeStamp(), request.getNonce(), request.getEncryptContent()).equals(request.getMsgSignature())) {
            throw new HttpResponseException(HttpResponseException.HttpResponseStatus.FORBIDDEN);
        }
        return messageDispatcher.doDispatch(request);
    }

    protected WeixinResponse otherwise(WeixinRequest weixinRequest) throws HttpResponseException {
        throw new HttpResponseException(HttpResponseException.HttpResponseStatus.METHOD_NOT_ALLOWED);
    }

    public String transferResponse(WeixinResponse weixinResponse) throws WeixinException {
        if(weixinResponse instanceof SingleResponse){
            return weixinResponse.toContent();
        } else {
            return weixinResponseEncoder.encode(weixinResponse);
        }
    }

}
