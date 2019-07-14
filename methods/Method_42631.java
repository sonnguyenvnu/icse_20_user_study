/** 
 * ?????????????
 * @param sArray ?????
 * @return ?????????????????
 */
public static Map<String,String> paraFilter(Map<String,String> sArray){
  Map<String,String> result=new HashMap<String,String>();
  if (sArray == null || sArray.isEmpty()) {
    return result;
  }
  for (  String key : sArray.keySet()) {
    String value=sArray.get(key);
    if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key) || "sign_type".equalsIgnoreCase(key)) {
      continue;
    }
    result.put(key,value);
  }
  return result;
}
