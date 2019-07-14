/** 
 * Parse tracer key
 * @param tracerMap tracer map
 * @param key       tracer key
 * @param value     tracer value
 */
public static void parseTraceKey(Map<String,String> tracerMap,String key,String value){
  String lowKey=key.substring(PREFIX.length());
  String realKey=TRACER_KEY_MAP.get(lowKey);
  tracerMap.put(realKey == null ? lowKey : realKey,value);
}
