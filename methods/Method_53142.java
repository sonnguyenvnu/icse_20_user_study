public synchronized TabularDataSupport getStatistics(){
  TabularDataSupport apiStatisticsTable=new TabularDataSupport(API_STATISTICS_TYPE);
  for (  InvocationStatistics methodStats : API_STATISTICS.getInvocationStatistics()) {
    Object[] itemValues={methodStats.getName(),methodStats.getCallCount(),methodStats.getErrorCount(),methodStats.getTotalTime(),methodStats.getAverageTime()};
    try {
      CompositeData result=new CompositeDataSupport(METHOD_STATS_TYPE,ITEM_NAMES,itemValues);
      apiStatisticsTable.put(result);
    }
 catch (    OpenDataException e) {
      throw new RuntimeException(e);
    }
  }
  return apiStatisticsTable;
}
