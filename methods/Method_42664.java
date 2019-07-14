/** 
 * ????(????)
 * @param outTradeNo
 * @param body
 * @param totalAmount
 * @param spbillCreateIp
 * @param authCode
 * @return
 */
public static Map<String,Object> micropay(String outTradeNo,String body,BigDecimal totalAmount,String spbillCreateIp,String authCode){
  String nonce_str=getnonceStr();
  Integer total_fee=totalAmount.multiply(BigDecimal.valueOf(100L)).intValue();
  SortedMap<String,Object> paramMap=new TreeMap<>();
  paramMap.put("appid",WeixinConfigUtil.appId);
  paramMap.put("mch_id",WeixinConfigUtil.mch_id);
  paramMap.put("nonce_str",nonce_str);
  paramMap.put("sign_type","MD5");
  paramMap.put("body",body);
  paramMap.put("out_trade_no",outTradeNo);
  paramMap.put("total_fee",total_fee);
  paramMap.put("fee_type","CNY");
  paramMap.put("spbill_create_ip",spbillCreateIp);
  paramMap.put("auth_code",authCode);
  paramMap.put("sign",getSign(paramMap,WeixinConfigUtil.partnerKey));
  String data=mapToXml(paramMap);
  logger.info("????????:{}",data);
  Map<String,Object> resultMap=WeiXinPayUtils.httpXmlRequest("https://api.mch.weixin.qq.com/pay/micropay","POST",data);
  logger.info("????????:{}",resultMap);
  if (resultMap == null || resultMap.isEmpty()) {
    return null;
  }
  SortedMap<String,Object> responseMap=new TreeMap<>();
  responseMap.putAll(resultMap);
  String resultSign=getSign(responseMap,WeixinConfigUtil.partnerKey);
  if (resultSign.equals(resultMap.get("sign"))) {
    resultMap.put("verify","YES");
  }
 else {
    logger.info("????????,??????:{},????:{}",resultSign,resultMap.get("sign"));
    resultMap.put("verify","NO");
  }
  return resultMap;
}
