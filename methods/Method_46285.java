/** 
 * ?????
 * @param prefix    ??
 * @param sourceMap ??map
 * @param headers   ??map
 */
protected void flatCopyTo(String prefix,Map<String,Object> sourceMap,HttpHeaders headers){
  for (  Map.Entry<String,Object> entry : sourceMap.entrySet()) {
    String key=prefix + entry.getKey();
    Object value=entry.getValue();
    if (value instanceof String) {
      addToHeader(headers,key,(CharSequence)value);
    }
 else     if (value instanceof Number) {
      addToHeader(headers,key,value.toString());
    }
 else     if (value instanceof Map) {
      flatCopyTo(key + ".",(Map<String,Object>)value,headers);
    }
  }
}
