/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public void pauseJob(JobKey jobKey) throws SchedulerException {
  invoke("pauseJob",new Object[]{jobKey.getName(),jobKey.getGroup()},new String[]{String.class.getName(),String.class.getName()});
}
