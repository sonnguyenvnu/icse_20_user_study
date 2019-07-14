@Override public boolean deployJobByCron(JobKey jobKey,TriggerKey triggerKey,Map<String,Object> dataMap,String cron,boolean replace){
  Assert.isTrue(jobKey != null);
  Assert.isTrue(triggerKey != null);
  Assert.isTrue(CronExpression.isValidExpression(cron),"invalid cron = " + cron);
  try {
    JobDetail jobDetail=clusterScheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      logger.error("JobKey {}:{} is not exist",jobKey.getName(),jobKey.getGroup());
      return false;
    }
    fireCronTrigger(triggerKey,jobDetail,cron,replace,dataMap);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
  return true;
}
