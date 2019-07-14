/** 
 * ???????????
 * @param map ???????
 * @return
 */
public static String getSign(Map<String,String> map,String rsaKey,boolean rsa2){
  List<String> keys=new ArrayList<String>(map.keySet());
  Collections.sort(keys);
  StringBuilder authInfo=new StringBuilder();
  for (int i=0; i < keys.size() - 1; i++) {
    String key=keys.get(i);
    String value=map.get(key);
    authInfo.append(buildKeyValue(key,value,false));
    authInfo.append("&");
  }
  String tailKey=keys.get(keys.size() - 1);
  String tailValue=map.get(tailKey);
  authInfo.append(buildKeyValue(tailKey,tailValue,false));
  String oriSign=AliPaySignTool.sign(authInfo.toString(),rsaKey,rsa2);
  String encodedSign="";
  try {
    encodedSign=URLEncoder.encode(oriSign,"UTF-8");
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
  }
  return "sign=" + encodedSign;
}
