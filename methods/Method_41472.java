/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public void startDelayed(int seconds) throws SchedulerException {
  sched.startDelayed(seconds);
}
