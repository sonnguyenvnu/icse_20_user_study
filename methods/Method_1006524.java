/** 
 * ??????
 * @param signType
 * @return Map<String, String>
 * @throws Exception 
 */
public Map<String,String> build(SignType signType) throws Exception {
  Map<String,String> map=createMap();
  if (SignType.MD5 == signType) {
    map.put("sign_type","MD5");
  }
 else {
    map.put("sign_type","HMAC-SHA256");
  }
  map.put("sign",PaymentKit.createSign(map,getPaternerKey(),signType));
  return map;
}
