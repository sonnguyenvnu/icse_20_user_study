/** 
 * ??????API
 * @param mch_id ???
 * @param partnerKey ??
 * @return {String}
 */
public static String getsignkey(String mch_id,String partnerKey){
  Map<String,String> map=new HashMap<String,String>();
  String nonce_str=String.valueOf(System.currentTimeMillis());
  map.put("mch_id",mch_id);
  map.put("nonce_str",nonce_str);
  map.put("sign",PaymentKit.createSign(map,partnerKey));
  return doPost(GETSINGKEY,map);
}
