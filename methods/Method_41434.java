/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public void shutdown(boolean waitForJobsToComplete) throws SchedulerException {
  throw new SchedulerException("Operation not supported for remote schedulers.");
}
