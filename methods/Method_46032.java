/** 
 * ????????????????leastWindowCount??????? ??????????0???-1
 * @param invocationStats List<InvocationStat>
 * @param leastWindowCount leastWindowCount
 * @return The average exception rate of all invocation statics
 */
private double calculateAverageExceptionRate(List<InvocationStat> invocationStats,long leastWindowCount){
  long sumException=0;
  long sumCall=0;
  for (  InvocationStat invocationStat : invocationStats) {
    long invocationLeastWindowCount=getInvocationLeastWindowCount(invocationStat,ProviderInfoWeightManager.getWeight(invocationStat.getDimension().getProviderInfo()),leastWindowCount);
    if (invocationLeastWindowCount != -1 && invocationStat.getInvokeCount() >= invocationLeastWindowCount) {
      sumException+=invocationStat.getExceptionCount();
      sumCall+=invocationStat.getInvokeCount();
    }
  }
  if (sumCall == 0) {
    return -1;
  }
  return CalculateUtils.divide(sumException,sumCall);
}
