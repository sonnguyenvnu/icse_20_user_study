package com.roncoo.pay.trade.utils.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.roncoo.pay.common.core.utils.StringUtil;
import com.roncoo.pay.reconciliation.utils.alipay.httpClient.HttpProtocolHandler;
import com.roncoo.pay.reconciliation.utils.alipay.httpClient.HttpRequest;
import com.roncoo.pay.reconciliation.utils.alipay.httpClient.HttpResponse;
import com.roncoo.pay.reconciliation.utils.alipay.httpClient.HttpResultType;
import com.roncoo.pay.trade.entity.RoncooPayGoodsDetails;
import com.roncoo.pay.trade.utils.alipay.config.AlipayConfigUtil;
import com.roncoo.pay.trade.utils.alipay.sign.MD5;
import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class AliPayUtil {

    private static final Logger logger = LoggerFactory.getLogger(AliPayUtil.class);

    private AliPayUtil() {

    }

    /**
     * 支付�?被扫(扫�?设备)
     *
     * @param outTradeNo
     * @param authCode
     * @param subject
     * @param amount
     * @param body
     * @param roncooPayGoodsDetailses
     * @return
     */
    public static Map<String, Object> tradePay(String outTradeNo, String authCode, String subject, BigDecimal amount, String body, List<RoncooPayGoodsDetails> roncooPayGoodsDetailses) {
        logger.info("======>支付�?被扫");
        String charset = "UTF-8";
        String format = "json";
        String signType = "RSA2";
        String scene = "bar_code";//支付场景--�?��?支付
        String totalAmount = amount.toString();//订�?�金�?
        String discountableAmount = "0.0";//默认折扣金�?为0,建议由业务系统记录折扣金�?,值传递给支付�?实际支付金�?
        String storeId = "ykt_pay_store_id"; // (必填) 商户门店编�?�，通过门店�?�和商家�?��?��?�以�?置精准到门店的折扣信�?�，详询支付�?技术支�?
        String timeExpress = "5m";// 支付超时，线下扫�?交易定义为5分钟

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigUtil.trade_pay_url, AlipayConfigUtil.app_id, AlipayConfigUtil.mch_private_key, format, charset, AlipayConfigUtil.ali_public_key, signType);

        SortedMap<String, Object> paramMap = new TreeMap<>();
        paramMap.put("out_trade_no", outTradeNo);
        paramMap.put("scene", scene);
        paramMap.put("auth_code", authCode);
        paramMap.put("subject", subject);
        paramMap.put("total_amount", totalAmount);
        paramMap.put("discountable_amount", discountableAmount);
        paramMap.put("body", body);
        paramMap.put("store_id", storeId);
        paramMap.put("timeout_express", timeExpress);

        // 商�?明细列表，需填写购买商�?详细信�?�，
        if (roncooPayGoodsDetailses != null && roncooPayGoodsDetailses.size() > 0) {
            List<SortedMap<String, Object>> goodsList = new ArrayList<>();
            for (RoncooPayGoodsDetails roncooPayGoodsDetails : roncooPayGoodsDetailses) {
                SortedMap<String, Object> goodsMap = new TreeMap<>();
                goodsMap.put("goods_id", roncooPayGoodsDetails.getGoodsId());
                goodsMap.put("goods_name", roncooPayGoodsDetails.getGoodsName());
                goodsMap.put("quantity", roncooPayGoodsDetails.getNums());
                goodsMap.put("price", roncooPayGoodsDetails.getSinglePrice());
                goodsList.add(goodsMap);
            }
            paramMap.put("goods_detail", goodsList);
        }

        SortedMap<String, Object> extendParamsMap = new TreeMap<>();
        extendParamsMap.put("sys_service_provider_id", AlipayConfigUtil.partner);
        paramMap.put("extend_params", extendParamsMap);

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        System.out.println(JSONObject.toJSONString(paramMap));
        request.setBizContent(JSONObject.toJSONString(paramMap));
        try {
            AlipayTradePayResponse response = alipayClient.execute(request);
            JSONObject responseJSON = JSONObject.parseObject(JSONObject.toJSONString(response));
            logger.info("支付�?返回结果:{}", responseJSON);
            return responseJSON;
        } catch (AlipayApiException e) {
            logger.error("支付�?扫�?，支付异常:{}", e);
            JSONObject resultJSON = new JSONObject();
            resultJSON.put("outTradeNo", outTradeNo);
            resultJSON.put("totalAmount", amount);
            resultJSON.put("errorCode", "9999");
            return resultJSON;
        }
    }

    /**
     * 订�?�查询
     *
     * @return
     */
    public static Map<String, Object> tradeQuery(String outTradeNo) {
        logger.info("======>支付�?交易查询");
        String charset = "UTF-8";
        String format = "json";
        String signType = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigUtil.trade_query_url, AlipayConfigUtil.app_id, AlipayConfigUtil.mch_private_key, format, charset, AlipayConfigUtil.ali_public_key, signType);

        SortedMap<String, Object> bizContentMap = new TreeMap<>();
        bizContentMap.put("out_trade_no", outTradeNo);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent(JSONObject.toJSONString(bizContentMap));
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            JSONObject responseJSON = JSONObject.parseObject(JSONObject.toJSONString(response));
            logger.info("支付�?订�?�查询返回结果:{}", responseJSON);
            return responseJSON;
        } catch (AlipayApiException e) {
            logger.error("支付�?交易查询异常:{}", e);
            return null;
        }
    }


    public static Map<String, Object> singleTradeQuery(String outTradeNo) {
        SortedMap<String, String> paramMap = new TreeMap<>();
        paramMap.put("service", "single_trade_query");
        paramMap.put("partner", AlipayConfigUtil.partner);
        paramMap.put("_input_charset", AlipayConfigUtil.input_charset);
        paramMap.put("out_trade_no", outTradeNo);
        paramMap.put("sign", getSign(paramMap, AlipayConfigUtil.key));
        paramMap.put("sign_type", AlipayConfigUtil.sign_type);
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        // 设置编�?集
        request.setCharset(AlipayConfigUtil.input_charset);
        request.setParameters(generatNameValuePair(paramMap));
        request.setUrl("https://mapi.alipay.com/gateway.do?_input_charset=" + AlipayConfigUtil.input_charset);
        String strResult = null;
        try {
            HttpResponse response = httpProtocolHandler.execute(request, "", "");
            if (response == null) {
                return null;
            }
            strResult = response.getStringResult();
        } catch (Exception e) {
            logger.info("支付�?扫�?查询--请求异常�?");
        }
        logger.info("支付�?扫�?查询--返回结果:{}", strResult);
        try {
            Document document = DocumentHelper.parseText(strResult);
            List<Element> tradeList = document.getRootElement().element("response").element("trade").elements();
            SortedMap<String, String> responseMap = new TreeMap<>();
            for (Element ele : tradeList) {
                responseMap.put(ele.getName(), ele.getText());
            }
            String resultSign = getSign(responseMap, AlipayConfigUtil.key);

            String sign = document.getRootElement().element("sign").getText();
            if (resultSign.equals(sign)) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.putAll(responseMap);
                resultMap.put("is_success",document.getRootElement().element("is_success").getText());
                return resultMap;
            } else {
                logger.info("支付�?--订�?�查询验签�?通过:{},返回签�??:[{}],返回报文签�??:[{}]", sign, resultSign);
                return null;
            }

        } catch (DocumentException e) {
            logger.info("支付�?扫�?查询--返回结果解�?失败�?");
            return null;
        }
    }

    /**
     * MAP类型数组转�?��?NameValuePair类型
     *
     * @param properties MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(SortedMap<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }

    private static String getSign(SortedMap<String, String> paramMap, String key) {
        StringBuilder signBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (!"sign".equals(entry.getKey()) && !"sign_type".equals(entry.getKey()) && !StringUtil.isEmpty(entry.getValue())) {
                signBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return MD5.sign(signBuilder.substring(0, signBuilder.length() - 1), key, "UTF-8");
    }
}
