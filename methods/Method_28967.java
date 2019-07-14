/** 
 * ?????
 * @param statMap
 */
private static double getMeanValue(AtomicLongMap<Integer> statMap){
  if (statMap == null || statMap.isEmpty()) {
    return 0;
  }
  Map<Integer,Long> map=statMap.asMap();
  Long totalCount=0L;
  Long totalValue=0L;
  for (  Entry<Integer,Long> entry : map.entrySet()) {
    totalCount+=entry.getValue();
    totalValue+=entry.getKey() * entry.getValue();
  }
  DecimalFormat df=new DecimalFormat("#.00");
  Double result=totalValue * 1.0 / totalCount * 1.0;
  return NumberUtil.toDouble(df.format(result));
}
