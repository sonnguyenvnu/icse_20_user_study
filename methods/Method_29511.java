/** 
 * AppStats?????json?
 */
private String assembleAppStatsJson(List<AppStats> appStats,String statName){
  if (appStats == null || appStats.isEmpty()) {
    return "[]";
  }
  List<SimpleChartData> list=new ArrayList<SimpleChartData>();
  for (  AppStats stat : appStats) {
    try {
      SimpleChartData chartData=SimpleChartData.getFromAppStats(stat,statName);
      list.add(chartData);
    }
 catch (    ParseException e) {
      logger.info(e.getMessage(),e);
    }
  }
  JSONArray jsonArray=JSONArray.fromObject(list);
  return jsonArray.toString();
}
