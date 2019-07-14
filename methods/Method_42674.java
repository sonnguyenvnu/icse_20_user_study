/** 
 * ??Map???????????
 * @param map
 * @return
 */
public static String getStringByMap(Map<String,Object> map){
  SortedMap<String,Object> smap=new TreeMap<String,Object>(map);
  StringBuffer sb=new StringBuffer();
  for (  Map.Entry<String,Object> m : smap.entrySet()) {
    sb.append(m.getKey()).append("=").append(m.getValue()).append("&");
  }
  sb.delete(sb.length() - 1,sb.length());
  return sb.toString();
}
