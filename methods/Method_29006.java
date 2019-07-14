/** 
 * ??????????
 * @param currentMinuteStamp
 */
public static Map<ValueLengthModel,Long> getValueLengthLastMinute(String currentMinuteStamp){
  AtomicLongMap<ValueLengthModel> map=DATA_VALUE_LENGTH_DISTRIBUTE_MAP_ALL.get(currentMinuteStamp);
  if (map == null || map.isEmpty()) {
    return Collections.emptyMap();
  }
  return map.asMap();
}
