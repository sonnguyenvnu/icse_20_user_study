/** 
 * ????
 * @param appDailyData
 */
private void fillAppDailyData(AppDailyData appDailyData){
  appDailyData.setAppId(appDailyData.getAppDetailVO().getAppDesc().getAppId());
  appDailyData.setDate(appDailyData.getStartDate());
  Map<String,Long> valueSizeDistributeCountMap=appDailyData.getValueSizeDistributeCountMap();
  long bigKeyTimes=0;
  StringBuffer bigKeyInfo=new StringBuffer();
  for (  Entry<String,Long> entry : valueSizeDistributeCountMap.entrySet()) {
    String key=entry.getKey();
    long times=entry.getValue();
    bigKeyInfo.append(key + ":" + times + "\n");
  }
  appDailyData.setBigKeyInfo(bigKeyInfo.toString());
  appDailyData.setBigKeyTimes(bigKeyTimes);
}
