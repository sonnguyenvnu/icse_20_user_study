public void scheduleJob(JobDetail jobDetail,Set<? extends Trigger> triggersForJob,boolean replace) throws SchedulerException {
  Map<JobDetail,Set<? extends Trigger>> triggersAndJobs=new HashMap<JobDetail,Set<? extends Trigger>>();
  triggersAndJobs.put(jobDetail,triggersForJob);
  scheduleJobs(triggersAndJobs,replace);
}
