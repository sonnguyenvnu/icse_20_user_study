/** 
 * ?????XML
 * @param weiXinPrePay
 * @param partnerKey
 * @return
 */
public static String getPrePayXml(WeiXinPrePay weiXinPrePay,String partnerKey){
  getPrePaySign(weiXinPrePay,partnerKey);
  StringBuilder sb=new StringBuilder();
  sb.append("<xml><appid>").append(weiXinPrePay.getAppid()).append("</appid>");
  sb.append("<body>").append(weiXinPrePay.getBody()).append("</body>");
  sb.append("<device_info>").append(weiXinPrePay.getDeviceInfo()).append("</device_info>");
  sb.append("<mch_id>").append(weiXinPrePay.getMchId()).append("</mch_id>");
  sb.append("<nonce_str>").append(weiXinPrePay.getNonceStr()).append("</nonce_str>");
  sb.append("<notify_url>").append(weiXinPrePay.getNotifyUrl()).append("</notify_url>");
  if (WeiXinTradeTypeEnum.NATIVE.name().equals(weiXinPrePay.getTradeType())) {
    sb.append("<product_id>").append(weiXinPrePay.getProductId()).append("</product_id>");
  }
 else   if (WeiXinTradeTypeEnum.JSAPI.name().equals(weiXinPrePay.getTradeType())) {
    sb.append("<openid>").append(weiXinPrePay.getOpenid()).append("</openid>");
  }
  sb.append("<out_trade_no>").append(weiXinPrePay.getOutTradeNo()).append("</out_trade_no>");
  sb.append("<spbill_create_ip>").append(weiXinPrePay.getSpbillCreateIp()).append("</spbill_create_ip>");
  sb.append("<time_start>").append(weiXinPrePay.getTimeStart()).append("</time_start>");
  sb.append("<time_expire>").append(weiXinPrePay.getTimeExpire()).append("</time_expire>");
  sb.append("<total_fee>").append(weiXinPrePay.getTotalFee()).append("</total_fee>");
  sb.append("<trade_type>").append(weiXinPrePay.getTradeType().name()).append("</trade_type>");
  sb.append("<sign>").append(weiXinPrePay.getSign()).append("</sign>");
  sb.append("</xml>");
  return sb.toString();
}
