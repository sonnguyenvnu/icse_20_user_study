/** 
 * ???????
 * @param paramMap
 * @return
 */
public static String getParamStr(Map<String,Object> paramMap){
  SortedMap<String,Object> smap=new TreeMap<String,Object>(paramMap);
  StringBuffer stringBuffer=new StringBuffer();
  for (  Map.Entry<String,Object> m : smap.entrySet()) {
    Object value=m.getValue();
    if (value != null && StringUtils.isNotBlank(String.valueOf(value))) {
      stringBuffer.append(m.getKey()).append("=").append(value).append("&");
    }
  }
  stringBuffer.delete(stringBuffer.length() - 1,stringBuffer.length());
  return stringBuffer.toString();
}
