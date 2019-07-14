/** 
 * ?????
 * @return
 */
public static Map<String,Object> appletPay(String outTradeNo,String body,BigDecimal totalAmount,String spbillCreateIp,String notifyUrl,String openid,List<RoncooPayGoodsDetails> goodsDetails){
  String nonce_str=getnonceStr();
  Integer totalFee=totalAmount.multiply(BigDecimal.valueOf(100L)).intValue();
  String tradeType="JSAPI";
  SortedMap<String,Object> paramMap=new TreeMap<>();
  paramMap.put("appid",WeixinConfigUtil.xAppId);
  paramMap.put("mch_id",WeixinConfigUtil.xMchId);
  paramMap.put("nonce_str",nonce_str);
  paramMap.put("sign_type","MD5");
  paramMap.put("body",body);
  paramMap.put("out_trade_no",outTradeNo);
  paramMap.put("total_fee",totalFee);
  paramMap.put("spbill_create_ip",spbillCreateIp);
  paramMap.put("notify_url",notifyUrl);
  paramMap.put("trade_type",tradeType);
  paramMap.put("openid",openid);
  if (goodsDetails != null && !goodsDetails.isEmpty()) {
    List<SortedMap<String,Object>> goodList=new ArrayList<>();
    for (    RoncooPayGoodsDetails goodsDetail : goodsDetails) {
      SortedMap<String,Object> goodsDetailMap=new TreeMap<>();
      goodsDetailMap.put("goods_id",goodsDetail.getGoodsId());
      goodsDetailMap.put("quantity",goodsDetail.getNums());
      goodsDetailMap.put("goods_name",goodsDetail.getGoodsName());
      goodsDetailMap.put("price",goodsDetail.getSinglePrice());
      goodList.add(goodsDetailMap);
    }
    JSONObject goodsDetailJson=new JSONObject();
    goodsDetailJson.put("goods_detail",goodList);
    paramMap.put("detail",goodsDetailJson.toJSONString());
  }
  paramMap.put("sign",getSign(paramMap,WeixinConfigUtil.xPayKey));
  String data=mapToXml(paramMap);
  logger.info("??????????????:{}",data);
  Map<String,Object> resultMap=WeiXinPayUtils.httpXmlRequest("https://api.mch.weixin.qq.com/pay/unifiedorder","POST",data);
  logger.info("??????????????:{}",resultMap);
  if (resultMap == null || resultMap.isEmpty()) {
    return null;
  }
  SortedMap<String,Object> responseMap=new TreeMap<>();
  responseMap.putAll(resultMap);
  String resultSign=getSign(responseMap,WeixinConfigUtil.xPayKey);
  if (resultSign.equals(resultMap.get("sign"))) {
    resultMap.put("verify","YES");
  }
 else {
    logger.info("????????,??????:{},????:{}",resultSign,resultMap.get("sign"));
    resultMap.put("verify","NO");
  }
  return resultMap;
}
