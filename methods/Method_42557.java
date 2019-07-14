/** 
 * ?????????????
 * @param sArray ?????
 * @return ?????????????????
 */
public static Map<String,String> paraFilter(Map<String,String> sArray){
  Map<String,String> result=new HashMap<String,String>();
  if (sArray == null || sArray.size() <= 0) {
    return result;
  }
  for (  String key : sArray.keySet()) {
    String value=sArray.get(key);
    if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
      continue;
    }
    result.put(key,value);
  }
  return result;
}
