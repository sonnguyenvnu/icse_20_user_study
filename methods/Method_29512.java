private String assembleMutilDateAppCommandJsonMinute(List<AppCommandStats> appCommandStats,Date startDate,Date endDate){
  if (appCommandStats == null || appCommandStats.isEmpty()) {
    return "[]";
  }
  Map<String,List<HighchartPoint>> map=new HashMap<String,List<HighchartPoint>>();
  Date currentDate=DateUtils.addDays(endDate,-1);
  int diffDays=0;
  while (currentDate.getTime() >= startDate.getTime()) {
    List<HighchartPoint> list=new ArrayList<HighchartPoint>();
    for (    AppCommandStats stat : appCommandStats) {
      try {
        HighchartPoint highchartPoint=HighchartPoint.getFromAppCommandStats(stat,currentDate,diffDays);
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
