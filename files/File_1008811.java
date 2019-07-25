package org.xxpay.service.channel.alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author: dingzhiwei
 * @date: 17/8/21
 * @description:
 */
@RefreshScope
@Service
public class AlipayConfig {

    // 商户appid
    private String app_id;
    // �?钥 pkcs8格�?的
    private String rsa_private_key;
    // �?务器异步通知页�?�路径 需http://或者https://格�?的完整路径，�?能加?id=123这类自定义�?�数，必须外网�?�以正常访问
    @Value("${ali.notify_url}")
    private String notify_url;
    // 页�?�跳转�?�步通知页�?�路径 需http://或者https://格�?的完整路径，�?能加?id=123这类自定义�?�数，必须外网�?�以正常访问 商户�?�以自定义�?�步跳转地�?�
    @Value("${ali.return_url}")
    private String return_url;
    // 请求网关地�?�
    private String url = "https://openapi.alipay.com/gateway.do";

    // 编�?
    public static String CHARSET = "UTF-8";
    // 返回格�?
    public static String FORMAT = "json";
    // 支付�?公钥
    public String alipay_public_key;
    // RSA2
    public static String SIGNTYPE = "RSA2";

    // 是�?�沙箱环境,1:沙箱,0:正�?环境
    private Short isSandbox = 0;

    /**
     * �?始化支付�?�?置
     * @param configParam
     * @return
     */
    public AlipayConfig init(String configParam) {
        Assert.notNull(configParam, "init alipay config error");
        JSONObject paramObj = JSON.parseObject(configParam);
        this.setApp_id(paramObj.getString("appid"));
        this.setRsa_private_key(paramObj.getString("private_key"));
        this.setAlipay_public_key(paramObj.getString("alipay_public_key"));
        this.setIsSandbox(paramObj.getShortValue("isSandbox"));
        if(this.getIsSandbox() == 1) this.setUrl("https://openapi.alipaydev.com/gateway.do");
        return this;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getRsa_private_key() {
        return rsa_private_key;
    }

    public void setRsa_private_key(String rsa_private_key) {
        this.rsa_private_key = rsa_private_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getIsSandbox() {
        return isSandbox;
    }

    public void setIsSandbox(Short isSandbox) {
        this.isSandbox = isSandbox;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public void setAlipay_public_key(String alipay_public_key) {
        this.alipay_public_key = alipay_public_key;
    }
}

