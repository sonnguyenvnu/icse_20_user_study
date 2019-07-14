/** 
 * ??????
 * @param mchId
 * @param merchantSecret
 * @return
 */
public static Map<String,Object> getCertificates(String mchId,String merchantSecret){
  logger.info("????--??????");
  SortedMap<String,Object> paramMap=new TreeMap<>();
  paramMap.put("mch_id",mchId);
  paramMap.put("nonce_str",createNonceStr());
  paramMap.put("sign_type","HMAC-SHA256");
  paramMap.put("sign",sha256Sign(paramMap,merchantSecret));
  String data=mapToXml(paramMap);
  logger.info("????--??????-????:\r\n{}",data);
  String result=requestPost(data,"https://api.mch.weixin.qq.com/risk/getcertficates");
  logger.info("????--??????-????:\r\n{}",result);
  Map<String,Object> resultMap=xmlToMap(result);
  logger.info("????--??????-????:{}",resultMap);
  return resultMap;
}
