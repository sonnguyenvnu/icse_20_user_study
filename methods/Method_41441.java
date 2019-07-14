/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public boolean deleteJob(JobKey jobKey) throws SchedulerException {
  return (Boolean)invoke("deleteJob",new Object[]{jobKey.getName(),jobKey.getGroup()},new String[]{String.class.getName(),String.class.getName()});
}
