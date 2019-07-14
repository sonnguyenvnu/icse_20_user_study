/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public boolean unscheduleJob(TriggerKey triggerKey) throws SchedulerException {
  return (Boolean)invoke("unscheduleJob",new Object[]{triggerKey.getName(),triggerKey.getGroup()},new String[]{String.class.getName(),String.class.getName()});
}
