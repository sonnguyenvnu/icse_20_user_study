/** 
 * map?????,????????map
 * @param source ???map??
 * @param prefix ??????,?????
 * @param include ????? ?????key(???????,????????)
 * @param exclude ????? ????key(???????,????????)
 * @param keyMap ??map, ??key--??key (???????,????????)
 * @return ?????map,???map???????
 */
public static Map<String,Object> filter(Map<String,Object> source,String prefix,String include,String exclude,Map<String,String> keyMap){
  LinkedHashMap<String,Object> dst=new LinkedHashMap<String,Object>();
  if (source == null || source.isEmpty())   return dst;
  Pattern includePattern=include == null ? null : Regex.getPattern(include);
  Pattern excludePattern=exclude == null ? null : Regex.getPattern(exclude);
  for (  Entry<String,Object> en : source.entrySet()) {
    String key=en.getKey();
    if (prefix != null) {
      if (key.startsWith(prefix))       key=key.substring(prefix.length());
 else       continue;
    }
    if (includePattern != null && !includePattern.matcher(key).find())     continue;
    if (excludePattern != null && excludePattern.matcher(key).find())     continue;
    if (keyMap != null && keyMap.containsKey(key))     dst.put(keyMap.get(key),en.getValue());
 else     dst.put(key,en.getValue());
  }
  return dst;
}
