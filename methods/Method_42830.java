/** 
 * ??????
 * @param paramMap  ????
 * @param paySecret ????
 * @return
 */
public static String getSign(Map<String,Object> paramMap,String paySecret){
  SortedMap<String,Object> smap=new TreeMap<String,Object>(paramMap);
  StringBuffer stringBuffer=new StringBuffer();
  for (  Map.Entry<String,Object> m : smap.entrySet()) {
    Object value=m.getValue();
    if (!"sign".equals(m.getKey()) && value != null && StringUtils.isNotBlank(String.valueOf(value))) {
      stringBuffer.append(m.getKey()).append("=").append(m.getValue()).append("&");
    }
  }
  stringBuffer.delete(stringBuffer.length() - 1,stringBuffer.length());
  String argPreSign=stringBuffer.append("&paySecret=").append(paySecret).toString();
  String signStr=MD5Util.encode(argPreSign).toUpperCase();
  return signStr;
}
