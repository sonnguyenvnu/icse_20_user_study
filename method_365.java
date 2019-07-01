private synchronized void _XXXXX_(ManagedRepositoryConfiguration repoConfig) throws SchedulerException {
  if (repoConfig.getRefreshCronExpression() == null) {
    log.warn("Skipping job, no cron expression for {}",repoConfig.getId());
    return;
  }
  if (!repoConfig.isScanned()) {
    log.warn("Skipping job, repository scannable has been disabled for {}",repoConfig.getId());
    return;
  }
  String cronString=repoConfig.getRefreshCronExpression();
  if (!cronValidator.validate(cronString)) {
    log.warn("Cron expression [{}] for repository [{}] is invalid.  Defaulting to hourly.",cronString,repoConfig.getId());
    cronString=CRON_HOURLY;
  }
  JobDataMap jobDataMap=new JobDataMap();
  jobDataMap.put(TASK_QUEUE,repositoryScanningQueue);
  jobDataMap.put(TASK_REPOSITORY,repoConfig.getId());
  JobDetail repositoryJob=JobBuilder.newJob(RepositoryTaskJob.class).withIdentity(REPOSITORY_JOB + ":" + repoConfig.getId(),REPOSITORY_SCAN_GROUP).setJobData(jobDataMap).build();
  try {
    CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity(REPOSITORY_JOB_TRIGGER + ":" + repoConfig.getId(),REPOSITORY_SCAN_GROUP).withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
    jobs.add(REPOSITORY_JOB + ":" + repoConfig.getId());
    scheduler.scheduleJob(repositoryJob,trigger);
  }
 catch (  RuntimeException e) {
    log.error("ParseException in repository scanning cron expression, disabling repository scanning for '{}': {}",repoConfig.getId(),e.getMessage());
  }
}