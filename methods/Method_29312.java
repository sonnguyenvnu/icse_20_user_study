private boolean fireCronTrigger(TriggerKey triggerKey,JobDetail jobDetail,String cron,boolean replace,Map<String,Object> dataMap){
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
    Date startDate=DateUtils.addSeconds(new Date(),20);
    Trigger trigger=TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobDetail).withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing()).startAt(startDate).build();
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
