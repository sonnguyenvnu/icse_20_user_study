private void statisticReport(SofaTracerSpan span){
  statReporter.reportStat(span);
  Field statDatas;
  Map<StatKey,StatValues> datas=null;
  try {
    statDatas=getDeclaredField(statReporter,"statDatas");
    statDatas.setAccessible(true);
    datas=(Map<StatKey,StatValues>)statDatas.get(statReporter);
  }
 catch (  IllegalAccessException e) {
    LOGGER.error("statisticReport error",e);
  }
  storeDatas.putAll(datas);
}
