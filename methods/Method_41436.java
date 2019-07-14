/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
@SuppressWarnings("unchecked") public List<JobExecutionContext> getCurrentlyExecutingJobs() throws SchedulerException {
  throw new SchedulerException("Operation not supported for remote schedulers.");
}
