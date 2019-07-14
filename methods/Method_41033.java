/** 
 * <p> Remove (delete) the <code> {@link org.quartz.Trigger}</code> with the given name, and store the new given one - which must be associated with the same job. </p>
 * @param newTrigger The new <code>Trigger</code> to be stored.
 * @return <code>null</code> if a <code>Trigger</code> with the givenname & group was not found and removed from the store, otherwise the first fire time of the newly scheduled trigger.
 */
public Date rescheduleJob(TriggerKey triggerKey,Trigger newTrigger) throws SchedulerException {
  validateState();
  if (triggerKey == null) {
    throw new IllegalArgumentException("triggerKey cannot be null");
  }
  if (newTrigger == null) {
    throw new IllegalArgumentException("newTrigger cannot be null");
  }
  OperableTrigger trig=(OperableTrigger)newTrigger;
  Trigger oldTrigger=getTrigger(triggerKey);
  if (oldTrigger == null) {
    return null;
  }
 else {
    trig.setJobKey(oldTrigger.getJobKey());
  }
  trig.validate();
  Calendar cal=null;
  if (newTrigger.getCalendarName() != null) {
    cal=resources.getJobStore().retrieveCalendar(newTrigger.getCalendarName());
  }
  Date ft=trig.computeFirstFireTime(cal);
  if (ft == null) {
    throw new SchedulerException("Based on configured schedule, the given trigger will never fire.");
  }
  if (resources.getJobStore().replaceTrigger(triggerKey,trig)) {
    notifySchedulerThread(newTrigger.getNextFireTime().getTime());
    notifySchedulerListenersUnscheduled(triggerKey);
    notifySchedulerListenersSchduled(newTrigger);
  }
 else {
    return null;
  }
  return ft;
}
