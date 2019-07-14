/** 
 * ?????
 * @param appStats
 * @param statName
 * @param startDate
 * @param endDate
 * @return
 */
private String assembleMutilDateAppStatsJsonMinute(List<AppStats> appStats,String statName,Date startDate,Date endDate){
  if (appStats == null || appStats.isEmpty()) {
    return "[]";
  }
  Map<String,List<HighchartPoint>> map=new HashMap<String,List<HighchartPoint>>();
  Date currentDate=DateUtils.addDays(endDate,-1);
  int diffDays=0;
  while (currentDate.getTime() >= startDate.getTime()) {
    List<HighchartPoint> list=new ArrayList<HighchartPoint>();
    for (    AppStats stat : appStats) {
      try {
        HighchartPoint highchartPoint=HighchartPoint.getFromAppStats(stat,statName,currentDate,diffDays);
        if (highchartPoint == null) {
          continue;
        }
        list.add(highchartPoint);
      }
 catch (      ParseException e) {
        logger.info(e.getMessage(),e);
      }
    }
    String formatDate=DateUtil.formatDate(currentDate,"yyyy-MM-dd");
    map.put(formatDate,list);
    currentDate=DateUtils.addDays(currentDate,-1);
    diffDays++;
  }
  net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(map);
  return jsonObject.toString();
}
