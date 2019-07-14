/** 
 * ?? map to url pair
 * @param map ??
 */
private static String convertMap2Pair(Map<String,String> map){
  if (CommonUtils.isEmpty(map)) {
    return StringUtils.EMPTY;
  }
  StringBuilder sb=new StringBuilder(128);
  for (  Map.Entry<String,String> entry : map.entrySet()) {
    sb.append(getKeyPairs(entry.getKey(),entry.getValue()));
  }
  return sb.toString();
}
