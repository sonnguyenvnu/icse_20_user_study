/** 
 * ????
 * @return
 */
public static Map<String,Object> tradeQuery(String outTradeNo){
  logger.info("======>???????");
  String charset="UTF-8";
  String format="json";
  String signType="RSA2";
  AlipayClient alipayClient=new DefaultAlipayClient(AlipayConfigUtil.trade_query_url,AlipayConfigUtil.app_id,AlipayConfigUtil.mch_private_key,format,charset,AlipayConfigUtil.ali_public_key,signType);
  SortedMap<String,Object> bizContentMap=new TreeMap<>();
  bizContentMap.put("out_trade_no",outTradeNo);
  AlipayTradeQueryRequest request=new AlipayTradeQueryRequest();
  request.setBizContent(JSONObject.toJSONString(bizContentMap));
  try {
    AlipayTradeQueryResponse response=alipayClient.execute(request);
    JSONObject responseJSON=JSONObject.parseObject(JSONObject.toJSONString(response));
    logger.info("???????????:{}",responseJSON);
    return responseJSON;
  }
 catch (  AlipayApiException e) {
    logger.error("?????????:{}",e);
    return null;
  }
}
