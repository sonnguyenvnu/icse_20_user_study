/** 
 * ????
 * @param outTradeNo
 * @return
 */
public static Map<String,Object> orderQuery(String outTradeNo,String appId,String MchId,String partnerKey){
  Random random=new Random();
  Map<String,Object> paramMap=new HashMap<>();
  paramMap.put("appid",appId);
  paramMap.put("mch_id",MchId);
  String nonce_str="";
  for (int i=0; i < 31; i++) {
    nonce_str+=random.nextInt(10);
  }
  paramMap.put("nonce_str",nonce_str);
  paramMap.put("out_trade_no",outTradeNo);
  String signStr=getStringByMap(paramMap) + "&key=" + partnerKey;
  paramMap.put("sign",MD5Util.encode(signStr).toUpperCase());
  Set<String> ks=paramMap.keySet();
  StringBuilder sb=new StringBuilder("<xml>");
  for (  String key : ks) {
    sb.append("<" + key + ">" + paramMap.get(key) + "</" + key + ">");
  }
  sb.append("</xml>");
  Map<String,Object> resultMap=httpXmlRequest("https://api.mch.weixin.qq.com/pay/orderquery","POST",sb.toString());
  return resultMap;
}
