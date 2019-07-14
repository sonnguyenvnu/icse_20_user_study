/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public Date rescheduleJob(TriggerKey triggerKey,Trigger newTrigger) throws SchedulerException {
  return sched.rescheduleJob(triggerKey,newTrigger);
}
