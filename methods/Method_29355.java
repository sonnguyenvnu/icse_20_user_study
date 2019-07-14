@Override public boolean sendAppDailyEmail(long appId,Date startDate,Date endDate){
  try {
    AppDailyData appDailyData=generateAppDaily(appId,startDate,endDate);
    if (appDailyData == null) {
      return false;
    }
    fillAppDailyData(appDailyData);
    appDailyDao.save(appDailyData);
    AppDetailVO appDetailVO=appDailyData.getAppDetailVO();
    noticeAppDaily(startDate,appDetailVO,appDailyData);
    return true;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
}
