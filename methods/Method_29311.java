private boolean fireSimpleTrigger(TriggerKey triggerKey,JobDetail jobDetail,boolean replace,Map<String,Object> dataMap,int delaySeconds){
  try {
    boolean isExists=clusterScheduler.checkExists(triggerKey);
    if (isExists) {
      if (replace) {
        logger.warn("replace trigger={}:{} ",triggerKey.getName(),triggerKey.getGroup());
        clusterScheduler.unscheduleJob(triggerKey);
      }
 else {
        logger.info("exist trigger={}:{} ",triggerKey.getName(),triggerKey.getGroup());
        return false;
      }
    }
    Date startAtDate=new Date(System.currentTimeMillis() + delaySeconds * 1000);
    Trigger trigger=TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobDetail).withSchedule(simpleSchedule().withIntervalInSeconds(1).withRepeatCount(0)).startAt(startAtDate).build();
    if (dataMap != null && dataMap.size() > 0) {
      trigger.getJobDataMap().putAll(dataMap);
    }
    clusterScheduler.scheduleJob(trigger);
  }
 catch (  SchedulerException e) {
    logger.error(e.getMessage(),e);
    return false;
  }
  return true;
}
