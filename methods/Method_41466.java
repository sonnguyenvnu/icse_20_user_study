/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public Date rescheduleJob(TriggerKey triggerKey,Trigger newTrigger) throws SchedulerException {
  try {
    return getRemoteScheduler().rescheduleJob(triggerKey,newTrigger);
  }
 catch (  RemoteException re) {
    throw invalidateHandleCreateException("Error communicating with remote scheduler.",re);
  }
}
