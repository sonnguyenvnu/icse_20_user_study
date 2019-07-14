/** 
 * ?????
 * @param prefix ??
 * @param sourceMap ??map
 * @param dstMap ??map
 */
public static void flatCopyTo(String prefix,Map<String,Object> sourceMap,Map<String,String> dstMap){
  for (  Map.Entry<String,Object> entry : sourceMap.entrySet()) {
    String key=prefix + entry.getKey();
    Object value=entry.getValue();
    if (value instanceof String) {
      dstMap.put(key,(String)value);
    }
 else     if (value instanceof Number) {
      dstMap.put(key,value.toString());
    }
 else     if (value instanceof Map) {
      flatCopyTo(key + ".",(Map<String,Object>)value,dstMap);
    }
  }
}
