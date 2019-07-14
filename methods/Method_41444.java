public void scheduleJobs(Map<JobDetail,Set<? extends Trigger>> triggersAndJobs,boolean replace) throws SchedulerException {
  throw new SchedulerException("Operation not supported for remote schedulers.");
}
