@Override public void degrade(MeasureResultDetail measureResultDetail){
  InvocationStatDimension statDimension=measureResultDetail.getInvocationStatDimension();
  String appName=statDimension.getAppName();
  if (LOGGER.isInfoEnabled(appName)) {
    String service=statDimension.getService();
    long timeWindow=measureResultDetail.getTimeWindow();
    long windowCount=measureResultDetail.getWindowCount();
    double abnormalRate=measureResultDetail.getAbnormalRate();
    double averageAbnormalRate=measureResultDetail.getAverageAbnormalRate();
    String ip=statDimension.getIp();
    LOGGER.infoWithApp(appName,LogCodes.getLog(LogCodes.INFO_REGULATION_ABNORMAL,timeWindow,service,appName,windowCount,abnormalRate,averageAbnormalRate,ip));
  }
}
