package org.xxpay.dubbo.service.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ScheduledMessage;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.xxpay.common.constant.PayConstant;
import org.xxpay.common.util.MyLog;
import org.xxpay.common.util.RpcUtil;
import org.xxpay.dal.dao.model.TransOrder;
import org.xxpay.dubbo.api.service.IPayChannel4AliService;
import org.xxpay.dubbo.api.service.IPayChannel4WxService;
import org.xxpay.dubbo.service.BaseNotify4MchTrans;
import org.xxpay.dubbo.service.BaseService;
import org.xxpay.dubbo.service.BaseService4TransOrder;

import javax.jms.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 业务通知MQ实现
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-10-30
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@Component
public class Mq4TransNotify extends BaseService4TransOrder {

    @Autowired
    private Queue transNotifyQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private IPayChannel4WxService payChannel4WxService;

    @Autowired
    private IPayChannel4AliService payChannel4AliService;

    @Autowired
    private BaseNotify4MchTrans baseNotify4MchTrans;

    private static final MyLog _log = MyLog.getLog(Mq4TransNotify.class);

    public void send(String msg) {
        _log.info("�?��?MQ消�?�:msg={}", msg);
        this.jmsTemplate.convertAndSend(this.transNotifyQueue, msg);
    }

    /**
     * �?��?延迟消�?�
     * @param msg
     * @param delay
     */
    public void send(String msg, long delay) {
        _log.info("�?��?MQ延时消�?�:msg={},delay={}", msg, delay);
        jmsTemplate.send(this.transNotifyQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage tm = session.createTextMessage(msg);
                tm.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                tm.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 1*1000);
                tm.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 1);
                return tm;
            }
        });
    }

    @JmsListener(destination = MqConfig.TRANS_NOTIFY_QUEUE_NAME)
    public void receive(String msg) {
        _log.info("处�?�转账任务.msg={}", msg);
        JSONObject msgObj = JSON.parseObject(msg);
        String transOrderId = msgObj.getString("transOrderId");
        String channelName = msgObj.getString("channelName");
        TransOrder transOrder = baseSelectTransOrder(transOrderId);
        if(transOrder == null) {
            _log.warn("查询转账订�?�为空,�?能转账.transOrderId={}", transOrderId);
            return;
        }
        if(transOrder.getStatus() != PayConstant.TRANS_STATUS_INIT) {
            _log.warn("转账状�?�?是�?始({})或失败({}),�?能转账.transOrderId={}", PayConstant.TRANS_STATUS_INIT, PayConstant.TRANS_STATUS_FAIL, transOrderId);
            return;
        }
        int result = this.baseUpdateStatus4Ing(transOrderId, "");
        if(result != 1) {
            _log.warn("更改转账为转账中({})失败,�?能转账.transOrderId={}", PayConstant.TRANS_STATUS_TRANING, transOrderId);
            return;
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("transOrder", transOrder);
        String jsonParam = RpcUtil.createBaseParam(paramMap);
        Map resultMap;
        if(PayConstant.CHANNEL_NAME_WX.equalsIgnoreCase(channelName)) {
            resultMap = payChannel4WxService.doWxTransReq(jsonParam);
        }else if(PayConstant.CHANNEL_NAME_ALIPAY.equalsIgnoreCase(channelName)) {
            resultMap = payChannel4AliService.doAliTransReq(jsonParam);
        }else {
            _log.warn("�?支�?的转账渠�?�,�?�止转账处�?�.transOrderId={},channelName={}", transOrderId, channelName);
            return;
        }
        if(!RpcUtil.isSuccess(resultMap)) {
            _log.warn("�?�起转账返回异常,�?�止转账处�?�.transOrderId={}", transOrderId);
            return;
        }
        Map bizResult = (Map) resultMap.get("bizResult");
        Boolean isSuccess = false;
        if(bizResult.get("isSuccess") != null) isSuccess = Boolean.parseBoolean(bizResult.get("isSuccess").toString());
        if(isSuccess) {
            // 更新转账状�?为�?功
            String channelOrderNo = bizResult.get("channelOrderNo") == null ? "" : bizResult.get("channelOrderNo").toString();
            result = baseUpdateStatus4Success(transOrderId, channelOrderNo);
            _log.info("更新转账订�?�状�?为�?功({}),transOrderId={},返回结果:{}", PayConstant.TRANS_STATUS_SUCCESS, transOrderId, result);
            // �?��?商户通知
            baseNotify4MchTrans.doNotify(transOrder, true);
        }else {
            // 更新转账状�?为�?功
            String channelErrCode = bizResult.get("channelErrCode") == null ? "" : bizResult.get("channelErrCode").toString();
            String channelErrMsg = bizResult.get("channelErrMsg") == null ? "" : bizResult.get("channelErrMsg").toString();
            result = baseUpdateStatus4Fail(transOrderId, channelErrCode, channelErrMsg);
            _log.info("更新转账订�?�状�?为失败({}),transOrderId={},返回结果:{}", PayConstant.TRANS_STATUS_FAIL, transOrderId, result);
            // �?��?商户通知
            baseNotify4MchTrans.doNotify(transOrder, true);
        }

    }
}
