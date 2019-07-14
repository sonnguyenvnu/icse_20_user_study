/** 
 * ?????????? pid
 * @param app_id target_id
 * @return
 */
public static Map<String,String> buildOrderParamMap(String app_id,boolean rsa2,String out_trade_no,String name,String price,String detail){
  Map<String,String> keyValues=new HashMap<String,String>();
  keyValues.put("app_id",app_id);
  keyValues.put("biz_content","{\"timeout_express\":\"30m\"," + "\"product_code\":\"QUICK_MSECURITY_PAY\"," + "\"total_amount\":\"" + price + "\"," + "\"subject\":\"" + detail + "\"," + "\"body\":\"" + name + "\"," + "\"out_trade_no\":\"" + out_trade_no + "\"}");
  keyValues.put("charset","utf-8");
  keyValues.put("method","alipay.trade.app.pay");
  keyValues.put("sign_type",rsa2 ? "RSA2" : "RSA");
  keyValues.put("timestamp","2016-07-29 16:55:53");
  keyValues.put("version","1.0");
  return keyValues;
}
