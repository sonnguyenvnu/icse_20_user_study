/** 
 * ?????????
 * @param currentMinuteStamp
 */
public static Map<ExceptionModel,Long> getExceptionLastMinute(String currentMinuteStamp){
  AtomicLongMap<ExceptionModel> map=DATA_EXCEPTION_MAP_ALL.get(currentMinuteStamp);
  if (map == null || map.isEmpty()) {
    return Collections.emptyMap();
  }
  return map.asMap();
}
