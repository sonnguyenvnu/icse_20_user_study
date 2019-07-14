@Override public boolean deployJobByDelay(JobKey jobKey,TriggerKey triggerKey,Map<String,Object> dataMap,int delaySeconds,boolean replace){
  Assert.isTrue(jobKey != null);
  Assert.isTrue(triggerKey != null);
  Assert.isTrue(delaySeconds > 0);
  try {
    JobDetail jobDetail=clusterScheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      logger.error("JobKey {}:{} is not exist",jobKey.getName(),jobKey.getGroup());
      return false;
    }
    fireSimpleTrigger(triggerKey,jobDetail,replace,dataMap,delaySeconds);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
  return true;
}
