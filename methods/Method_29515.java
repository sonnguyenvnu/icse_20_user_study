private String assembleJson(List<AppCommandStats> appCommandStatsList,Integer addDay){
  if (appCommandStatsList == null || appCommandStatsList.isEmpty()) {
    return "[]";
  }
  List<SimpleChartData> list=new ArrayList<SimpleChartData>();
  for (  AppCommandStats stat : appCommandStatsList) {
    try {
      SimpleChartData chartData=SimpleChartData.getFromAppCommandStats(stat,addDay);
      list.add(chartData);
    }
 catch (    ParseException e) {
      logger.info(e.getMessage(),e);
    }
  }
  JSONArray jsonArray=JSONArray.fromObject(list);
  return jsonArray.toString();
}
