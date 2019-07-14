/** 
 * ???app????
 * @param appClientCostTimeStatList
 */
private List<AppClientCostTimeTotalStat> mergeAppClientCostTimeStat(List<AppClientCostTimeStat> appClientCostTimeStatList){
  List<AppClientCostTimeTotalStat> resultList=new ArrayList<AppClientCostTimeTotalStat>();
  Map<String,List<AppClientCostTimeStat>> map=new HashMap<String,List<AppClientCostTimeStat>>();
  for (  AppClientCostTimeStat appClientCostTimeStat : appClientCostTimeStatList) {
    long appId=appClientCostTimeStat.getAppId();
    String command=appClientCostTimeStat.getCommand();
    long collectTime=appClientCostTimeStat.getCollectTime();
    String key=appId + "_" + command + "_" + collectTime;
    if (map.containsKey(key)) {
      map.get(key).add(appClientCostTimeStat);
    }
 else {
      List<AppClientCostTimeStat> list=new ArrayList<AppClientCostTimeStat>();
      list.add(appClientCostTimeStat);
      map.put(key,list);
    }
  }
  for (  Entry<String,List<AppClientCostTimeStat>> entry : map.entrySet()) {
    String key=entry.getKey();
    String[] items=key.split("_");
    long appId=NumberUtils.toLong(items[0]);
    String command=items[1];
    long collectTime=NumberUtils.toLong(items[2]);
    double totalCost=0.0;
    long totalCount=0;
    int median=0;
    int ninetyPercentMax=0;
    int ninetyNinePercentMax=0;
    int hundredMax=0;
    String maxInstanceHost="";
    int maxInstancePort=0;
    long maxInstanceId=0;
    String maxClientIp="";
    double mean=0.0;
    for (    AppClientCostTimeStat appClientCostTimeStat : entry.getValue()) {
      AppClientCostTimeTotalStat appClientCostTimeTotalStat=AppClientCostTimeTotalStat.getFromAppClientCostTimeStat(appClientCostTimeStat);
      totalCost+=appClientCostTimeTotalStat.getTotalCost();
      totalCount+=appClientCostTimeTotalStat.getTotalCount();
      if (appClientCostTimeTotalStat.getMedian() > median) {
        median=appClientCostTimeTotalStat.getMedian();
      }
      if (appClientCostTimeTotalStat.getNinetyPercentMax() > ninetyPercentMax) {
        ninetyPercentMax=appClientCostTimeTotalStat.getNinetyPercentMax();
      }
      if (appClientCostTimeTotalStat.getNinetyNinePercentMax() > ninetyNinePercentMax) {
        ninetyNinePercentMax=appClientCostTimeTotalStat.getNinetyNinePercentMax();
      }
      if (appClientCostTimeTotalStat.getHundredMax() > hundredMax) {
        hundredMax=appClientCostTimeTotalStat.getHundredMax();
        maxInstanceHost=appClientCostTimeTotalStat.getMaxInstanceHost();
        maxInstancePort=appClientCostTimeTotalStat.getMaxInstancePort();
        maxInstanceId=appClientCostTimeTotalStat.getMaxInstanceId();
        maxClientIp=appClientCostTimeTotalStat.getMaxClientIp();
      }
    }
    DecimalFormat df=new DecimalFormat("0.00");
    totalCost=NumberUtils.toDouble(df.format(totalCost));
    if (totalCount > 0) {
      mean=totalCost / totalCount;
      mean=NumberUtils.toDouble(df.format(mean));
    }
    resultList.add(new AppClientCostTimeTotalStat(-1,appId,collectTime,new Date(),command,totalCount,totalCost,median,mean,ninetyPercentMax,ninetyNinePercentMax,hundredMax,maxInstanceHost,maxInstancePort,maxInstanceId,maxClientIp));
  }
  return resultList;
}
