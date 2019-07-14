/** 
 * AppCommandGroup?????json?
 */
private String assembleGroupJson(List<AppCommandGroup> appCommandGroupList){
  if (appCommandGroupList == null || appCommandGroupList.isEmpty()) {
    return "[]";
  }
  List<SimpleChartData> list=new ArrayList<SimpleChartData>();
  for (  AppCommandGroup appCommandGroup : appCommandGroupList) {
    SimpleChartData chartData=SimpleChartData.getFromAppCommandGroup(appCommandGroup);
    list.add(chartData);
  }
  JSONArray jsonArray=JSONArray.fromObject(list);
  return jsonArray.toString();
}
