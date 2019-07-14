/** 
 * ?????????
 * @param currentMinuteStamp
 */
public static Map<CostTimeDetailStatKey,AtomicLongMap<Integer>> getCostTimeLastMinute(String currentMinuteStamp){
  Map<CostTimeDetailStatKey,AtomicLongMap<Integer>> result=new HashMap<CostTimeDetailStatKey,AtomicLongMap<Integer>>();
  for (  Entry<CostTimeDetailStatKey,AtomicLongMap<Integer>> entry : DATA_COST_TIME_MAP_ALL.entrySet()) {
    CostTimeDetailStatKey costTimeDetailStatKey=entry.getKey();
    if (costTimeDetailStatKey != null && costTimeDetailStatKey.getCurrentMinute() != null && costTimeDetailStatKey.getCurrentMinute().equals(currentMinuteStamp)) {
      result.put(costTimeDetailStatKey,entry.getValue());
    }
  }
  return result;
}
