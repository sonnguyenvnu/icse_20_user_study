/** 
 * ?????
 * @param appStats
 * @param statNameList
 * @param startDate
 * @return
 */
private String assembleMutiStatAppStatsJsonMinute(List<AppStats> appStats,List<String> statNameList,Date startDate){
  if (appStats == null || appStats.isEmpty()) {
    return "[]";
  }
  Map<String,List<HighchartPoint>> map=new HashMap<String,List<HighchartPoint>>();
  for (  String statName : statNameList) {
    List<HighchartPoint> list=new ArrayList<HighchartPoint>();
    for (    AppStats stat : appStats) {
      try {
        HighchartPoint highchartPoint=HighchartPoint.getFromAppStats(stat,statName,startDate,0);
        if (highchartPoint == null) {
          continue;
        }
        list.add(highchartPoint);
      }
 catch (      ParseException e) {
        logger.info(e.getMessage(),e);
      }
    }
    map.put(statName,list);
  }
  net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(map);
  return jsonObject.toString();
}
