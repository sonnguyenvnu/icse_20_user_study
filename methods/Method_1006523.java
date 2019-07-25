/** 
 * ??????
 * @return Map<String, String>
 */
public Map<String,String> build(){
  Map<String,String> map=createMap();
  map.put("sign",PaymentKit.createSign(map,getPaternerKey()));
  return map;
}
