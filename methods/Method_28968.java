/** 
 * ??Integer-Long??????????????????
 * @param statMap
 * @param percent
 * @return
 */
private static int fillCostTimeDetailStatModel(CostTimeDetailStatModel model,AtomicLongMap<Integer> statMap,double percent){
  int wrongResultValue=0;
  if (percent > 100 || percent < 0) {
    return wrongResultValue;
  }
  if (statMap == null || statMap.isEmpty()) {
    return wrongResultValue;
  }
  Map<Integer,Long> sortKeyMap=new TreeMap<Integer,Long>(statMap.asMap());
  Long totalSize=model.getTotalCount();
  if (totalSize <= 0) {
    for (    Long count : sortKeyMap.values()) {
      totalSize+=count;
    }
    model.setTotalCount(totalSize);
  }
  return getPercentValue(totalSize,sortKeyMap,percent);
}
