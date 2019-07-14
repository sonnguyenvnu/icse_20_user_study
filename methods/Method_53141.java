@Override public synchronized Map<String,String> getMethodLevelSummariesAsString(){
  Map<String,String> summariesMap=new HashMap<String,String>();
  Collection<InvocationStatisticsCalculator> allMethodStats=METHOD_STATS_MAP.values();
  for (  InvocationStatisticsCalculator methodStats : allMethodStats) {
    summariesMap.put(methodStats.getName(),methodStats.toString());
  }
  return summariesMap;
}
