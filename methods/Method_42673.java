/** 
 * ?????????
 * @param weiXinPrePay
 * @param partnerKey
 * @return
 */
private static void getPrePaySign(WeiXinPrePay weiXinPrePay,String partnerKey){
  Map<String,Object> prePayMap=new HashMap<String,Object>();
  prePayMap.put("appid",weiXinPrePay.getAppid());
  prePayMap.put("mch_id",weiXinPrePay.getMchId());
  prePayMap.put("device_info",weiXinPrePay.getDeviceInfo());
  prePayMap.put("nonce_str",weiXinPrePay.getNonceStr());
  prePayMap.put("body",weiXinPrePay.getBody());
  prePayMap.put("out_trade_no",weiXinPrePay.getOutTradeNo());
  prePayMap.put("total_fee",weiXinPrePay.getTotalFee());
  prePayMap.put("spbill_create_ip",weiXinPrePay.getSpbillCreateIp());
  prePayMap.put("time_start",weiXinPrePay.getTimeStart());
  prePayMap.put("time_expire",weiXinPrePay.getTimeExpire());
  prePayMap.put("notify_url",weiXinPrePay.getNotifyUrl());
  prePayMap.put("trade_type",weiXinPrePay.getTradeType().name());
  if (WeiXinTradeTypeEnum.NATIVE.name().equals(weiXinPrePay.getTradeType())) {
    prePayMap.put("product_id",weiXinPrePay.getProductId());
  }
 else   if (WeiXinTradeTypeEnum.JSAPI.name().equals(weiXinPrePay.getTradeType())) {
    prePayMap.put("openid",weiXinPrePay.getOpenid());
  }
  String argPreSign=getStringByMap(prePayMap) + "&key=" + partnerKey;
  String preSign=MD5Util.encode(argPreSign).toUpperCase();
  weiXinPrePay.setSign(preSign);
}
