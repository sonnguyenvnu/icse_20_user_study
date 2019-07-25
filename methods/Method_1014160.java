@Override public boolean reschedule(AbstractInstant newTime){
  try {
    Trigger trigger=newTrigger().startAt(newTime.toDate()).build();
    Date nextTriggerTime=scheduler.rescheduleJob(triggerKey,trigger);
    if (nextTriggerTime == null) {
      logger.debug("Scheduling a new job job '{}' because the original has already run",jobKey.toString());
      JobDetail job=newJob(TimerExecutionJob.class).withIdentity(jobKey).usingJobData(dataMap).build();
      TimerImpl.scheduler.scheduleJob(job,trigger);
    }
    this.triggerKey=trigger.getKey();
    this.cancelled=false;
    this.terminated=false;
    return true;
  }
 catch (  SchedulerException e) {
    logger.warn("An error occurred while rescheduling the job '{}': {}",jobKey.toString(),e.getMessage());
    return false;
  }
}
