/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public boolean isShutdown() throws SchedulerException {
  throw new SchedulerException("Operation not supported for remote schedulers.");
}
