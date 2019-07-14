package com.roncoo.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.pay.common.core.utils.DateUtils;
import com.roncoo.pay.controller.common.BaseController;
import com.roncoo.pay.service.CnpPayService;
import com.roncoo.pay.trade.exception.TradeBizException;
import com.roncoo.pay.trade.service.RpTradePaymentManagerService;
import com.roncoo.pay.trade.utils.MerchantApiUtil;
import com.roncoo.pay.trade.utils.WeixinConfigUtil;
import com.roncoo.pay.trade.vo.ProgramPayResultVo;
import com.roncoo.pay.user.entity.RpUserPayConfig;
import com.roncoo.pay.user.exception.UserBizException;
import com.roncoo.pay.user.service.RpUserPayConfigService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/programpay")
public class ProgramPayController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProgramPayController.class);

    @Autowired
    private RpTradePaymentManagerService tradePaymentManagerService;
    @Autowired
    private RpUserPayConfigService userPayConfigService;
    @Autowired
    private CnpPayService cnpPayService;

    private static final String CONTENT_TYPE = "text/text;charset=UTF-8";

    @RequestMapping("/doPay")
    public void initPay(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        Map<String, Object> paramMap = new HashMap<>();
        //获�?�商户传入�?�数
        String payKey = getString_UrlDecode_UTF8("payKey"); // �?业支付KEY
        String openId = getString_UrlDecode_UTF8("openId"); // 用户标识
        String productName = getString_UrlDecode_UTF8("productName"); // 商�?�??称
        String orderNo = getString_UrlDecode_UTF8("orderNo"); // 订�?�编�?�
        String orderPriceStr = getString_UrlDecode_UTF8("orderPrice"); // 订�?�金�? , �?��?:元
        String payWayCode = getString_UrlDecode_UTF8("payWayCode"); // 支付方�?编�? 支付�?: ALIPAY  微信:WEIXIN
        String orderIp = getString_UrlDecode_UTF8("orderIp"); // 下�?�IP
        String orderDateStr = getString_UrlDecode_UTF8("orderDate"); // 订�?�日期
        String orderTimeStr = getString_UrlDecode_UTF8("orderTime"); // 订�?�时间
        String notifyUrl = getString_UrlDecode_UTF8("notifyUrl"); // 异步通知地�?�
        String remark = getString_UrlDecode_UTF8("remark"); // 支付备注

        String field1 = getString_UrlDecode_UTF8("field1"); // 扩展字段1
        String field2 = getString_UrlDecode_UTF8("field2"); // 扩展字段2
        String field3 = getString_UrlDecode_UTF8("field3"); // 扩展字段3
        String field4 = getString_UrlDecode_UTF8("field4"); // 扩展字段4
        String field5 = getString_UrlDecode_UTF8("field5"); // 扩展字段5
        String sign = getString_UrlDecode_UTF8("sign"); // 签�??

        paramMap.put("payKey", payKey);
        paramMap.put("openId", openId);
        paramMap.put("productName", productName);
        paramMap.put("orderNo", orderNo);
        paramMap.put("orderPrice", orderPriceStr);
        paramMap.put("payWayCode", payWayCode);
        paramMap.put("orderIp", orderIp);
        paramMap.put("orderDate", orderDateStr);
        paramMap.put("orderTime", orderTimeStr);
        paramMap.put("notifyUrl", notifyUrl);
        paramMap.put("remark", remark);

        paramMap.put("field1", field1);
        paramMap.put("field2", field2);
        paramMap.put("field3", field3);
        paramMap.put("field4", field4);
        paramMap.put("field5", field5);
        logger.info("�?程�?支付--接收�?�数:{}", paramMap);

        //格�?化时间
        Date orderDate = DateUtils.parseDate(orderDateStr, "yyyyMMdd");
        Date orderTime = DateUtils.parseDate(orderTimeStr, "yyyyMMddHHmmss");

        //获�?�支付�?置
        RpUserPayConfig userPayConfig = userPayConfigService.getByPayKey(payKey);
        if (userPayConfig == null) {
            throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR, "用户支付�?置有误");
        }
        //ip校验
        cnpPayService.checkIp(userPayConfig, httpServletRequest);
        //验签
        if (!MerchantApiUtil.isRightSign(paramMap, userPayConfig.getPaySecret(), sign)) {
            throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订�?�签�??异常");
        }
        //�?�起支付
        BigDecimal orderPrice = BigDecimal.valueOf(Double.valueOf(orderPriceStr));
        ProgramPayResultVo resultVo = tradePaymentManagerService.programPay(payKey, openId, productName, orderNo, orderDate, orderTime, orderPrice, payWayCode, orderIp, notifyUrl, remark, field1, field2, field3, field4, field5);

        String payResultJson = JSONObject.toJSONString(resultVo);
        logger.debug("�?程�?--支付结果==>{}", payResultJson);
        httpServletResponse.setContentType(CONTENT_TYPE);
        write(httpServletResponse, payResultJson);
    }

    @RequestMapping("/authorize")
    @ResponseBody
    public String wxAuthorize(@RequestParam("code") String code) {
        String authUrl = WeixinConfigUtil.xAuthUrl.replace("{APPID}", WeixinConfigUtil.xAppId).replace("{SECRET}", WeixinConfigUtil.xPartnerKey).replace("{JSCODE}", code).replace("{GRANTTYPE}", WeixinConfigUtil.xGrantType);
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(authUrl);
            httpClient.executeMethod(getMethod);
            String result = getMethod.getResponseBodyAsString();
            logger.info("�?程�?code�?��?�结果:{}", result);
            return result;
        } catch (IOException e) {
            logger.info("获�?�openId失败!");
            return null;
        }
    }

}
