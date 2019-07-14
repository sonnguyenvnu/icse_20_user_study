/** 
 * ??Invocation????????Invocation??????????? ???????????0?????????????-1?
 * @param invocationStat InvocationStat
 * @param weight weight
 * @param leastWindowCount original least Window count
 * @return leastWindowCount
 */
private long getInvocationLeastWindowCount(InvocationStat invocationStat,Integer weight,long leastWindowCount){
  InvocationStatDimension statDimension=invocationStat.getDimension();
  Integer originWeight=statDimension.getOriginWeight();
  if (originWeight == 0) {
    LOGGER.errorWithApp(statDimension.getAppName(),"originWeight is 0,but is invoked. service[" + statDimension.getService() + "];ip[" + statDimension.getIp() + "].");
    return -1;
  }
 else   if (weight == null) {
    return leastWindowCount;
  }
 else   if (weight == -1) {
    return -1;
  }
  double rate=CalculateUtils.divide(weight,originWeight);
  long invocationLeastWindowCount=CalculateUtils.multiply(leastWindowCount,rate);
  return invocationLeastWindowCount < LEGAL_LEAST_WINDOW_COUNT ? LEGAL_LEAST_WINDOW_COUNT : invocationLeastWindowCount;
}
