public static HighchartPoint getFromAppStats(AppStats appStat,String statName,Date currentDate,int diffDays) throws ParseException {
  Date collectDate=getDateTime(appStat.getCollectTime());
  if (!DateUtils.isSameDay(currentDate,collectDate)) {
    return null;
  }
  String date=null;
  try {
    date=DateUtil.formatDate(collectDate,"yyyy-MM-dd HH:mm");
  }
 catch (  Exception e) {
    date=DateUtil.formatDate(collectDate,"yyyy-MM-dd HH");
  }
  long count=0;
  if ("hits".equals(statName)) {
    count=appStat.getHits();
  }
 else   if ("misses".equals(statName)) {
    count=appStat.getMisses();
  }
 else   if ("usedMemory".equals(statName)) {
    count=appStat.getUsedMemory() / 1024 / 1024;
  }
 else   if ("netInput".equals(statName)) {
    count=appStat.getNetInputByte();
  }
 else   if ("netOutput".equals(statName)) {
    count=appStat.getNetOutputByte();
  }
 else   if ("connectedClient".equals(statName)) {
    count=appStat.getConnectedClients();
  }
 else   if ("objectSize".equals(statName)) {
    count=appStat.getObjectSize();
  }
 else   if ("hitPercent".equals(statName)) {
    count=appStat.getHitPercent();
  }
  if (diffDays > 0) {
    collectDate=DateUtils.addDays(collectDate,diffDays);
  }
  return new HighchartPoint(collectDate.getTime(),count,date);
}
