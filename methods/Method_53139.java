private synchronized InvocationStatisticsCalculator getMethodStatistics(String method){
  InvocationStatisticsCalculator methodStats=METHOD_STATS_MAP.get(method);
  if (methodStats == null) {
    methodStats=new InvocationStatisticsCalculator(method,HISTORY_SIZE);
    METHOD_STATS_MAP.put(method,methodStats);
  }
  return methodStats;
}
