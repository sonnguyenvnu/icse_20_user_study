@Override public Job newJob(TriggerFiredBundle bundle,Scheduler scheduler) throws SchedulerException {
  Map<String,Object> data=bundle.getJobDetail().getJobDataMap();
  String jobId=(String)data.get(JOB_ID_KEY);
  if (null == jobId || bundle.getJobDetail().getJobClass() != DynamicJob.class) {
    return defaultFactory.newJob(bundle,scheduler);
  }
  return context -> scheduleJobExecutor.doExecuteJob(jobId,data);
}
