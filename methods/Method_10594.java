/** 
 * ??????????
 * @param map ??????
 * @return
 */
public static String buildOrderParam(Map<String,String> map){
  List<String> keys=new ArrayList<String>(map.keySet());
  StringBuilder sb=new StringBuilder();
  for (int i=0; i < keys.size() - 1; i++) {
    String key=keys.get(i);
    String value=map.get(key);
    sb.append(buildKeyValue(key,value,true));
    sb.append("&");
  }
  String tailKey=keys.get(keys.size() - 1);
  String tailValue=map.get(tailKey);
  sb.append(buildKeyValue(tailKey,tailValue,true));
  return sb.toString();
}
