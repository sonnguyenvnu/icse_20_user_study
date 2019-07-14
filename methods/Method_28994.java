/** 
 * ????
 * @param lastMinute
 */
private List<Map<String,Object>> collectReportCostTimeData(String lastMinute){
  try {
    Map<CostTimeDetailStatKey,AtomicLongMap<Integer>> map=UsefulDataCollector.getCostTimeLastMinute(lastMinute);
    if (map == null || map.isEmpty()) {
      return Collections.emptyList();
    }
    List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    for (    Entry<CostTimeDetailStatKey,AtomicLongMap<Integer>> entry : map.entrySet()) {
      CostTimeDetailStatKey costTimeDetailStatKey=entry.getKey();
      AtomicLongMap<Integer> statMap=entry.getValue();
      CostTimeDetailStatModel model=UsefulDataCollector.generateCostTimeDetailStatKey(statMap);
      Map<String,Object> tempMap=new HashMap<String,Object>();
      tempMap.put(ClientReportConstant.COST_COUNT,model.getTotalCount());
      tempMap.put(ClientReportConstant.COST_COMMAND,costTimeDetailStatKey.getCommand());
      tempMap.put(ClientReportConstant.COST_HOST_PORT,costTimeDetailStatKey.getHostPort());
      tempMap.put(ClientReportConstant.COST_TIME_90_MAX,model.getNinetyPercentMax());
      tempMap.put(ClientReportConstant.COST_TIME_99_MAX,model.getNinetyNinePercentMax());
      tempMap.put(ClientReportConstant.COST_TIME_100_MAX,model.getHundredMax());
      tempMap.put(ClientReportConstant.COST_TIME_MEAN,model.getMean());
      tempMap.put(ClientReportConstant.COST_TIME_MEDIAN,model.getMedian());
      tempMap.put(ClientReportConstant.CLIENT_DATA_TYPE,ClientCollectDataTypeEnum.COST_TIME_DISTRI_TYPE.getValue());
      list.add(tempMap);
    }
    return list;
  }
 catch (  Exception e) {
    UsefulDataCollector.collectException(e,"",System.currentTimeMillis(),ClientExceptionType.CLIENT_EXCEPTION_TYPE);
    logger.error("collectReportCostTimeData:" + e.getMessage(),e);
    return Collections.emptyList();
  }
}
