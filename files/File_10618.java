package com.vondear.rxfeature.module.wechat.pay;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.vondear.rxtool.interfaces.OnSuccessAndErrorListener;
import com.vondear.rxtool.view.RxToast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static com.vondear.rxtool.RxConstants.WX_TOTAL_ORDER;

/**
 *
 * @author Vondear
 * @date 2017/4/17
 */

public class WechatPayTools {
    /**
     * 商户�?�起生�?预付�?�请求
     *
     * @return
     */
    public static String wechatPayUnifyOrder(final Context mContext, final String appid, final String mch_id, final String wx_private_key, WechatModel wechatModel, final OnSuccessAndErrorListener OnSuccessAndErrorListener) {
        String nonce_str = getRandomStringByLength(8);//�?机�?
        String body = wechatModel.getDetail();//商�?�??述
        String out_trade_no = wechatModel.getOut_trade_no();//商�?订�?��?�
        String product_id = wechatModel.getOut_trade_no();//商�?编�?�
        String total_fee = wechatModel.getMoney();//总金�? 分
        String time_start = getCurrTime();//交易起始时间(订�?�生�?时间�?�必须)
        String trade_type = "APP";//App支付
        String notify_url = "https://github.com/vondear/RxTools";//"http://" + "域�??" + "/" + "项目�??" + "回调地�?�.do";//回调函数
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", appid);
        params.put("mch_id", mch_id);
        params.put("device_info", "WEB"); //设备�?�
        params.put("nonce_str", nonce_str);
        params.put("body", body);//商�?�??述
        params.put("out_trade_no", out_trade_no);
        params.put("product_id", product_id);
        params.put("total_fee", total_fee);
        params.put("time_start", time_start);
        params.put("trade_type", trade_type);
        params.put("notify_url", notify_url);
        String sign = "";//签�??(该签�??本应使用微信商户平�?�的API�?书中的密匙key,但此处使用的是微信公众�?�的密匙APP_SECRET)
        sign = getSign(params, wx_private_key);
        //�?�数xml化
        String xmlParams = parseString2Xml(params, sign);
        //判断返回�?
        final String[] jsonStr = {""};

        OkGo.<String>post(WX_TOTAL_ORDER)
                .upString(xmlParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String s = response.body();
                        Log.d("微信统一下�?�", s);
                        jsonStr[0] = s;

                        Map<String, String> mapXml = null;
                        try {
                            mapXml = getMapFromXML(s);
                        } catch (ParserConfigurationException | IOException | SAXException e) {
                            e.printStackTrace();
                        }
                        String time = getCurrTime();

                        SortedMap<String, String> params = new TreeMap<String, String>();
                        params.put("appid", appid);
                        params.put("noncestr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
                        params.put("package", "Sign=WechatPay");
                        params.put("partnerid", mch_id);
                        params.put("prepayid", mapXml.get("prepay_id"));
                        params.put("timestamp", time);

                        wechatPayApp(mContext, appid, mch_id, wx_private_key, params, OnSuccessAndErrorListener);
                    }
                });

        if (!jsonStr[0].contains("FAIL") && jsonStr[0].trim().length() > 0) {//�?功
            return "success";
        } else {//失败
            return "fail";
        }
    }

    public static void wechatPayApp(Context mContext, String appid, String mch_id, String wx_private_key, SortedMap<String, String> params, OnSuccessAndErrorListener onRxHttp) {
        String sign = getSign(params, wx_private_key);

        WechatPayModel wechatPayModel = new WechatPayModel(appid, mch_id, params.get("prepayid"), "Sign=WechatPay", params.get("noncestr"), params.get("timestamp"), sign);
        String pay_param = new Gson().toJson(wechatPayModel);
        WechatPayTools.doWXPay(mContext, appid, pay_param, onRxHttp);
    }

    public static void wechatPayApp(Context mContext, String app_id, String partner_id, String wx_private_key, String prepay_id, OnSuccessAndErrorListener onRxHttp) {
        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", app_id);
        params.put("noncestr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        params.put("package", "Sign=WechatPay");
        params.put("partnerid", partner_id);
        params.put("prepayid", prepay_id);
        params.put("timestamp", getCurrTime());

        String sign = getSign(params, wx_private_key);

        WechatPayModel wechatPayModel = new WechatPayModel(app_id, partner_id, prepay_id, "Sign=WechatPay", params.get("noncestr"), params.get("timestamp"), sign);
        String pay_param = new Gson().toJson(wechatPayModel);
        WechatPayTools.doWXPay(mContext, app_id, pay_param, onRxHttp);
    }

    /**
     * �?�数进行XML化
     *
     * @param map,sign
     * @return
     */
    public static String parseString2Xml(Map<String, String> map, String sign) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = map.entrySet();
        Iterator iterator = es.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append("<" + k + ">" + v + "</" + k + ">");
        }
        sb.append("<sign>" + sign + "</sign>");
        sb.append("</xml>");
        return sb.toString();
    }


    /**
     * 获�?�签�?? md5加密(微信支付必须用MD5加密)
     * 获�?�支付签�??
     *
     * @param params
     * @return
     */
    public static String getSign(SortedMap<String, String> params, String wx_private_key) {
        String sign = null;
        StringBuffer sb = new StringBuffer();
        Set es = params.entrySet();
        Iterator iterator = es.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + wx_private_key);
        sign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return sign;
    }

    /**
     * 获�?�一定长度的�?机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获�?�当�?时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    //xml解�?
    public static Map<String, String> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        //这里用Dom的方�?解�?回包的最主�?目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(xmlString.getBytes());
        Document document = builder.parse(is);
        //获�?�到document里�?�的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, String> map = new HashMap<String, String>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }


    public static void doWXPay(Context mContext, String wx_appid, String pay_param, final OnSuccessAndErrorListener onRxHttpString) {
        WechatPay.init(mContext, wx_appid);//�?在支付�?调用
        WechatPay.getInstance().doPay(pay_param, new WechatPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                RxToast.success("微信支付�?功");
                onRxHttpString.onSuccess("微信支付�?功");
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case WechatPay.NO_OR_LOW_WX:
                        RxToast.error("未安装微信或微信版本过低");
                        onRxHttpString.onError("未安装微信或微信版本过低");
                        break;

                    case WechatPay.ERROR_PAY_PARAM:
                        RxToast.error("�?�数错误");
                        onRxHttpString.onError("�?�数错误");
                        break;

                    case WechatPay.ERROR_PAY:
                        RxToast.error("支付失败");
                        onRxHttpString.onError("支付失败");
                        break;
                }
            }

            @Override
            public void onCancel() {
                RxToast.error("支付�?�消");
                onRxHttpString.onError("支付�?�消");
            }
        });
    }
}
