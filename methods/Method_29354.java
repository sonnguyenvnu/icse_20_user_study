@Override public int sendAppDailyEmail(){
  Date endDate=new Date();
  Date startDate=DateUtils.addDays(endDate,-1);
  int successCount=0;
  List<AppDesc> appDescList=appService.getAllAppDesc();
  for (  AppDesc appDesc : appDescList) {
    try {
      boolean result=sendAppDailyEmail(appDesc.getAppId(),startDate,endDate);
      if (result) {
        successCount++;
      }
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
  return successCount;
}
