/** 
 * <p> Resume (un-pause) the <code> {@link Trigger}</code> with the given name. </p> <p> If the <code>Trigger</code> missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 */
public void resumeTrigger(TriggerKey triggerKey) throws SchedulerException {
  validateState();
  resources.getJobStore().resumeTrigger(triggerKey);
  notifySchedulerThread(0L);
  notifySchedulerListenersResumedTrigger(triggerKey);
}
