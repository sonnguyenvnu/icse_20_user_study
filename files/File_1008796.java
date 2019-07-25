package org.xxpay.shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xxpay.common.constant.PayConstant;
import org.xxpay.common.util.*;
import org.xxpay.shop.dao.model.GoodsOrder;
import org.xxpay.shop.service.GoodsOrderService;
import org.xxpay.shop.util.Constant;
import org.xxpay.shop.util.OAuth2RequestParamHelper;
import org.xxpay.shop.util.vx.WxApi;
import org.xxpay.shop.util.vx.WxApiClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/goods")
public class GoodsOrderController {

    private final static MyLog _log = MyLog.getLog(GoodsOrderController.class);

    @Autowired
    private GoodsOrderService goodsOrderService;

    static final String mchId = "20001223";
    // 加签key
    static final String reqKey = "M86l522AV6q613Ii4W6u8K48uW8vM1N6bFgyv769220MdYe9u37N4y7rI5mQ";
    // 验签key
    static final String resKey = "Hpcl522AV6q613KIi46u6g6XuW8vM1N8bFgyv769770MdYe9u37M4y7rIpl8";
    //static final String baseUrl = "http://api.xxpay.org/api";
    static final String baseUrl = "http://127.0.0.1:3020/api";
    //static final String notifyUrl = "http://shop.xxpay.org/goods/payNotify";
    static final String notifyUrl = "http://127.0.0.1:8081/goods/payNotify";
    private AtomicLong seq = new AtomicLong(0L);
    private final static String QR_PAY_URL = "http://shop.xxpay.org/goods/qrPay.html";
    static final String AppID = "wx077cb62e341f8a5c";
    static final String AppSecret = "e663ea068f3e4f952f143de1432a35c2";
    private final static String GetOpenIdURL = "http://shop.xxpay.org/goods/getOpenId";
    private final static String GetOpenIdURL2 = "http://shop.xxpay.org/goods/getOpenId2";


    @RequestMapping(value = "/buy/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public String buy(@PathVariable("goodsId") String goodsId) {
        if(!"G_0001".equals(goodsId)) {
            return "fail";
        }
        String goodsOrderId = String.format("%s%s%06d", "G", DateUtil.getSeqString(), (int) seq.getAndIncrement() % 1000000);
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setGoodsOrderId(goodsOrderId);
        goodsOrder.setGoodsId(goodsId);
        goodsOrder.setGoodsName("XXPAY�??助商�?G_0001");
        goodsOrder.setAmount(1l);
        goodsOrder.setUserId("xxpay_000001");
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_INIT);
        int result = goodsOrderService.addGoodsOrder(goodsOrder);
        _log.info("�?�入商�?订�?�,返回:{}", result);
        return result+"";
    }

    @RequestMapping(value = "/pay/{goodsOrderId}", method = RequestMethod.GET)
    @ResponseBody
    public String pay(@PathVariable("goodsOrderId") String goodsOrderId) {
        GoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(goodsOrderId);
        if(goodsOrder == null) return "fail";
        int status = goodsOrder.getStatus();
        if(status != Constant.GOODS_ORDER_STATUS_INIT) {
            return "fail_001";
        }
        JSONObject paramMap = new JSONObject();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("mchOrderNo", goodsOrderId);           // 商户订�?��?�
        paramMap.put("channelId", "ALIPAY_WAP");             // 支付渠�?�ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", goodsOrder.getAmount());                          // 支付金�?,�?��?分
        paramMap.put("currency", "cny");                    // �?�?, cny-人民�?
        paramMap.put("clientIp", "114.112.124.236");        // 用户地�?�,IP或手机�?�
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", goodsOrder.getGoodsName());
        paramMap.put("body", goodsOrder.getGoodsName());
        paramMap.put("notifyUrl", notifyUrl);         // 回调URL
        paramMap.put("param1", "");                         // 扩展�?�数1
        paramMap.put("param2", "");                         // 扩展�?�数2
        paramMap.put("extra", "{\"productId\":\"120989823\",\"openId\":\"o2RvowBf7sOVJf8kJksUEMceaDqo\"}");  // 附加�?�数

        String reqSign = PayDigestUtil.getSign(paramMap, reqKey);
        paramMap.put("sign", reqSign);   // 签�??
        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求支付中心下�?�接�?�,请求数�?�:" + reqData);
        String url = baseUrl + "/pay/create_order?";
        String result = XXPayUtil.call4Post(url + reqData);
        System.out.println("请求支付中心下�?�接�?�,�?应数�?�:" + result);
        Map retMap = JSON.parseObject(result);
        if("SUCCESS".equals(retMap.get("retCode"))) {
            // 验签
            String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下�?�验签�?功=========");
            }else {
                System.err.println("=========支付中心下�?�验签失败=========");
                return null;
            }
        }
        String payOrderId = retMap.get("payOrderId").toString();

        goodsOrder = new GoodsOrder();
        goodsOrder.setGoodsOrderId(goodsOrderId);
        goodsOrder.setPayOrderId(payOrderId);
        goodsOrder.setChannelId("ALIPAY_WAP");
        int ret = goodsOrderService.update(goodsOrder);
        _log.info("修改商�?订�?�,返回:{}", ret);
        return result+"";
    }

    private Map createPayOrder(GoodsOrder goodsOrder, Map<String, Object> params) {
        JSONObject paramMap = new JSONObject();
        paramMap.put("mchId", mchId);                       // 商户ID
        paramMap.put("mchOrderNo", goodsOrder.getGoodsOrderId());           // 商户订�?��?�
        paramMap.put("channelId", params.get("channelId"));             // 支付渠�?�ID, WX_NATIVE,ALIPAY_WAP
        paramMap.put("amount", goodsOrder.getAmount());                          // 支付金�?,�?��?分
        paramMap.put("currency", "cny");                    // �?�?, cny-人民�?
        paramMap.put("clientIp", "114.112.124.236");        // 用户地�?�,IP或手机�?�
        paramMap.put("device", "WEB");                      // 设备
        paramMap.put("subject", goodsOrder.getGoodsName());
        paramMap.put("body", goodsOrder.getGoodsName());
        paramMap.put("notifyUrl", notifyUrl);         // 回调URL
        paramMap.put("param1", "");                         // 扩展�?�数1
        paramMap.put("param2", "");                         // 扩展�?�数2

        JSONObject extra = new JSONObject();
        extra.put("openId", params.get("openId"));
        paramMap.put("extra", extra.toJSONString());  // 附加�?�数

        String reqSign = PayDigestUtil.getSign(paramMap, reqKey);
        paramMap.put("sign", reqSign);   // 签�??
        String reqData = "params=" + paramMap.toJSONString();
        System.out.println("请求支付中心下�?�接�?�,请求数�?�:" + reqData);
        String url = baseUrl + "/pay/create_order?";
        String result = XXPayUtil.call4Post(url + reqData);
        System.out.println("请求支付中心下�?�接�?�,�?应数�?�:" + result);
        Map retMap = JSON.parseObject(result);
        if("SUCCESS".equals(retMap.get("retCode"))) {
            // 验签
            String checkSign = PayDigestUtil.getSign(retMap, resKey, "sign", "payParams");
            String retSign = (String) retMap.get("sign");
            if(checkSign.equals(retSign)) {
                System.out.println("=========支付中心下�?�验签�?功=========");
            }else {
                System.err.println("=========支付中心下�?�验签失败=========");
                return null;
            }
        }
        return retMap;
    }

    @RequestMapping("/openQrPay.html")
    public String openQrPay(ModelMap model) {
        return "openQrPay";
    }

    @RequestMapping("/qrPay.html")
    public String qrPay(ModelMap model, HttpServletRequest request, Long amount) {
        String logPrefix = "�?二维�?扫�?支付】";
        String view = "qrPay";
        _log.info("====== 开始接收二维�?扫�?支付请求 ======");
        String ua = request.getHeader("User-Agent");
        String goodsId = "G_0001";
        _log.info("{}接收�?�数:goodsId={},amount={},ua={}", logPrefix, goodsId, amount, ua);
        String client = "alipay";
        String channelId = "ALIPAY_WAP";
        if(StringUtils.isBlank(ua)) {
            String errorMessage = "User-Agent为空�?";
            _log.info("{}信�?�：{}", logPrefix, errorMessage);
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }else {
            if(ua.contains("Alipay")) {
                client = "alipay";
                channelId = "ALIPAY_WAP";
            }else if(ua.contains("MicroMessenger")) {
                client = "wx";
                channelId = "WX_JSAPI";
            }
        }
        if(client == null) {
            String errorMessage = "请用微信或支付�?扫�?";
            _log.info("{}信�?�：{}", logPrefix, errorMessage);
            model.put("result", "failed");
            model.put("resMsg", errorMessage);
            return view;
        }
        // 先�?�入订�?�数�?�
        GoodsOrder goodsOrder = null;
        Map<String, String> orderMap = null;
        if ("alipay".equals(client)) {
            _log.info("{}{}扫�?下�?�", logPrefix, "支付�?");
            Map params = new HashMap<>();
            params.put("channelId", channelId);
            // 下�?�
            goodsOrder = createGoodsOrder(goodsId, amount);
            orderMap = createPayOrder(goodsOrder, params);
        }else if("wx".equals(client)){
            _log.info("{}{}扫�?", logPrefix, "微信");
            // 判断是�?�拿到openid，如果没有则去获�?�
            String openId = request.getParameter("openId");
            if (StringUtils.isNotBlank(openId)) {
                _log.info("{}openId：{}", logPrefix, openId);
                Map params = new HashMap<>();
                params.put("channelId", channelId);
                params.put("openId", openId);
                goodsOrder = createGoodsOrder(goodsId, amount);
                // 下�?�
                orderMap = createPayOrder(goodsOrder, params);
            }else {
                String redirectUrl = QR_PAY_URL + "?amount=" + amount;
                String url = GetOpenIdURL2 + "?redirectUrl=" + redirectUrl;
                _log.info("跳转URL={}", url);
                return "redirect:" + url;
            }
        }
        model.put("goodsOrder", goodsOrder);
        model.put("amount", AmountUtil.convertCent2Dollar(goodsOrder.getAmount()+""));
        if(orderMap != null) {
            model.put("orderMap", orderMap);
            String payOrderId = orderMap.get("payOrderId");
            GoodsOrder go = new GoodsOrder();
            go.setGoodsOrderId(goodsOrder.getGoodsOrderId());
            go.setPayOrderId(payOrderId);
            go.setChannelId(channelId);
            int ret = goodsOrderService.update(go);
            _log.info("修改商�?订�?�,返回:{}", ret);
        }
        model.put("client", client);
        return view;
    }

    GoodsOrder createGoodsOrder(String goodsId, Long amount) {
        // 先�?�入订�?�数�?�
        String goodsOrderId =  String.format("%s%s%06d", "G", DateUtil.getSeqString(), (int) seq.getAndIncrement() % 1000000);
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setGoodsOrderId(goodsOrderId);
        goodsOrder.setGoodsId(goodsId);
        goodsOrder.setGoodsName("XXPAY�??助商�?G_0001");
        goodsOrder.setAmount(amount);
        goodsOrder.setUserId("xxpay_000001");
        goodsOrder.setStatus(Constant.GOODS_ORDER_STATUS_INIT);
        int result = goodsOrderService.addGoodsOrder(goodsOrder);
        _log.info("�?�入商�?订�?�,返回:{}", result);
        return goodsOrder;
    }

    /**
     * 获�?�code
     * @return
     */
    @RequestMapping("/getOpenId")
    public void getOpenId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        _log.info("进入获�?�用户openID页�?�");
        String redirectUrl = request.getParameter("redirectUrl");
        String code = request.getParameter("code");
        String openId = "";
        if(!StringUtils.isBlank(code)){//如果request中包括code，则是微信回调
            try {
                openId = WxApiClient.getOAuthOpenId(AppID, AppSecret, code);
                _log.info("调用微信返回openId={}", openId);
            } catch (Exception e) {
                _log.error(e, "调用微信查询openId异常");
            }
            if(redirectUrl.indexOf("?") > 0) {
                redirectUrl += "&openId=" + openId;
            }else {
                redirectUrl += "?openId=" + openId;
            }
            response.sendRedirect(redirectUrl);
        }else{//oauth获�?�code
            String redirectUrl4Vx = GetOpenIdURL + "?redirectUrl=" + redirectUrl;
            String state = OAuth2RequestParamHelper.prepareState(request);
            String url = WxApi.getOAuthCodeUrl(AppID, redirectUrl4Vx, "snsapi_base", state);
            _log.info("跳转URL={}", url);
            response.sendRedirect(url);
        }
    }

    /**
     * 获�?�code
     * @return
     */
    @RequestMapping("/getOpenId2")
    public void getOpenId2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        _log.info("进入获�?�用户openID页�?�");
        String redirectUrl = request.getParameter("redirectUrl");
        String code = request.getParameter("code");
        String openId = "";
        if(!StringUtils.isBlank(code)){//如果request中包括code，则是微信回调
            try {
                openId = WxApiClient.getOAuthOpenId(AppID, AppSecret, code);
                _log.info("调用微信返回openId={}", openId);
            } catch (Exception e) {
                _log.error(e, "调用微信查询openId异常");
            }
            if(redirectUrl.indexOf("?") > 0) {
                redirectUrl += "&openId=" + openId;
            }else {
                redirectUrl += "?openId=" + openId;
            }
            response.sendRedirect(redirectUrl);
        }else{//oauth获�?�code
            //http://www.abc.com/xxx/get-weixin-code.html?appid=XXXX&scope=snsapi_base&state=hello-world&redirect_uri=http%3A%2F%2Fwww.xyz.com%2Fhello-world.html
            String redirectUrl4Vx = GetOpenIdURL2 + "?redirectUrl=" + redirectUrl;
            String url = String.format("http://www.xiaoshuding.com/get-weixin-code.html?appid=%s&scope=snsapi_base&state=hello-world&redirect_uri=%s", AppID, WxApi.urlEnodeUTF8(redirectUrl4Vx));
            _log.info("跳转URL={}", url);
            response.sendRedirect(url);
        }
    }

    /**
     * 接收支付中心通知
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/payNotify")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        _log.info("====== 开始处�?�支付中心通知 ======");
        Map<String,Object> paramMap = request2payResponseMap(request, new String[]{
                "payOrderId","mchId","mchOrderNo","channelId","amount","currency","status", "clientIp",
                "device",  "subject", "channelOrderNo", "param1",
                "param2","paySuccTime","backType","sign"
        });
        _log.info("支付中心通知请求�?�数,paramMap={}", paramMap);
        if (!verifyPayResponse(paramMap)) {
            String errorMessage = "verify request param failed.";
            _log.warn(errorMessage);
            outResult(response, "fail");
            return;
        }
        String payOrderId = (String) paramMap.get("payOrderId");
        String mchOrderNo = (String) paramMap.get("mchOrderNo");
        String resStr;
        try {
            GoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(mchOrderNo);
            if(goodsOrder != null && goodsOrder.getStatus() == Constant.GOODS_ORDER_STATUS_COMPLETE) {
                outResult(response, "success");
                return;
            }
            // 执行业务逻辑
            int ret = goodsOrderService.updateStatus4Success(mchOrderNo);
            // ret返回结果
            // 等于1表示处�?��?功,返回支付中心success
            // 其他值,返回支付中心fail,让�?�?��?通知
            if(ret == 1) {
                ret = goodsOrderService.updateStatus4Complete(mchOrderNo);
                if(ret == 1) {
                    resStr = "success";
                }else {
                    resStr = "fail";
                }
            }else {
                resStr = "fail";
            }
        }catch (Exception e) {
            resStr = "fail";
            _log.error(e, "执行业务异常,payOrderId=%s.mchOrderNo=%s", payOrderId, mchOrderNo);
        }
        _log.info("�?应支付中心通知结果:{},payOrderId={},mchOrderNo={}", resStr, payOrderId, mchOrderNo);
        outResult(response, resStr);
        _log.info("====== 支付中心通知处�?�完�? ======");
    }

    @RequestMapping("/notify_test")
    public void notifyTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        outResult(response, "success");
    }

    @RequestMapping("/toAliPay.html")
    @ResponseBody
    public String toAliPay(HttpServletRequest request, Long amount, String channelId) {
        String logPrefix = "�?支付�?支付】";
        _log.info("====== 开始接收支付�?支付请求 ======");
        String goodsId = "G_0001";
        _log.info("{}接收�?�数:goodsId={},amount={},channelId={}", logPrefix, goodsId, amount, channelId);
        // 先�?�入订�?�数�?�
        Map params = new HashMap<>();
        params.put("channelId", channelId);
        // 下�?�
        GoodsOrder goodsOrder = createGoodsOrder(goodsId, amount);
        Map<String, String> orderMap = createPayOrder(goodsOrder, params);
        if(orderMap != null && "success".equalsIgnoreCase(orderMap.get("resCode"))) {
            String payOrderId = orderMap.get("payOrderId");
            GoodsOrder go = new GoodsOrder();
            go.setGoodsOrderId(goodsOrder.getGoodsOrderId());
            go.setPayOrderId(payOrderId);
            go.setChannelId(channelId);
            int ret = goodsOrderService.update(go);
            _log.info("修改商�?订�?�,返回:{}", ret);
        }
        if(PayConstant.PAY_CHANNEL_ALIPAY_MOBILE.equalsIgnoreCase(channelId)) return orderMap.get("payParams");
        return orderMap.get("payUrl");
    }

    void outResult(HttpServletResponse response, String content) {
        response.setContentType("text/html");
        PrintWriter pw;
        try {
            pw = response.getWriter();
            pw.print(content);
            _log.error("response xxpay complete.");
        } catch (IOException e) {
            _log.error(e, "response xxpay write exception.");
        }
    }

    public Map<String, Object> request2payResponseMap(HttpServletRequest request, String[] paramArray) {
        Map<String, Object> responseMap = new HashMap<>();
        for (int i = 0;i < paramArray.length; i++) {
            String key = paramArray[i];
            String v = request.getParameter(key);
            if (v != null) {
                responseMap.put(key, v);
            }
        }
        return responseMap;
    }

    public boolean verifyPayResponse(Map<String,Object> map) {
        String mchId = (String) map.get("mchId");
        String payOrderId = (String) map.get("payOrderId");
        String mchOrderNo = (String) map.get("mchOrderNo");
        String amount = (String) map.get("amount");
        String sign = (String) map.get("sign");

        if (StringUtils.isEmpty(mchId)) {
            _log.warn("Params error. mchId={}", mchId);
            return false;
        }
        if (StringUtils.isEmpty(payOrderId)) {
            _log.warn("Params error. payOrderId={}", payOrderId);
            return false;
        }
        if (StringUtils.isEmpty(amount) || !NumberUtils.isNumber(amount)) {
            _log.warn("Params error. amount={}", amount);
            return false;
        }
        if (StringUtils.isEmpty(sign)) {
            _log.warn("Params error. sign={}", sign);
            return false;
        }

        // 验�?签�??
        if (!verifySign(map)) {
            _log.warn("verify params sign failed. payOrderId={}", payOrderId);
            return false;
        }

        // 根�?�payOrderId查询业务订�?�,验�?订�?�是�?�存在
        GoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(mchOrderNo);
        if(goodsOrder == null) {
            _log.warn("业务订�?��?存在,payOrderId={},mchOrderNo={}", payOrderId, mchOrderNo);
            return false;
        }
        // 核对金�?
        if(goodsOrder.getAmount() != Long.parseLong(amount)) {
            _log.warn("支付金�?�?一致,dbPayPrice={},payPrice={}", goodsOrder.getAmount(), amount);
            return false;
        }
        return true;
    }

    public boolean verifySign(Map<String, Object> map) {
        String mchId = (String) map.get("mchId");
        if(!this.mchId.equals(mchId)) return false;
        String localSign = PayDigestUtil.getSign(map, resKey, "sign");
        String sign = (String) map.get("sign");
        return localSign.equalsIgnoreCase(sign);
    }

}
