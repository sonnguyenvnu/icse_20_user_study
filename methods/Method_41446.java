/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public Date rescheduleJob(TriggerKey triggerKey,Trigger newTrigger) throws SchedulerException {
  throw new SchedulerException("Operation not supported for remote schedulers.");
}
