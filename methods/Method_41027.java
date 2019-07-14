/** 
 * <p> Schedule the given <code> {@link org.quartz.Trigger}</code> with the <code>Job</code> identified by the <code>Trigger</code>'s settings. </p>
 * @throws SchedulerException if the indicated Job does not exist, or the Trigger cannot be added to the Scheduler, or there is an internal Scheduler error.
 */
public Date scheduleJob(Trigger trigger) throws SchedulerException {
  validateState();
  if (trigger == null) {
    throw new SchedulerException("Trigger cannot be null");
  }
  OperableTrigger trig=(OperableTrigger)trigger;
  trig.validate();
  Calendar cal=null;
  if (trigger.getCalendarName() != null) {
    cal=resources.getJobStore().retrieveCalendar(trigger.getCalendarName());
    if (cal == null) {
      throw new SchedulerException("Calendar not found: " + trigger.getCalendarName());
    }
  }
  Date ft=trig.computeFirstFireTime(cal);
  if (ft == null) {
    throw new SchedulerException("Based on configured schedule, the given trigger '" + trigger.getKey() + "' will never fire.");
  }
  resources.getJobStore().storeTrigger(trig,false);
  notifySchedulerThread(trigger.getNextFireTime().getTime());
  notifySchedulerListenersSchduled(trigger);
  return ft;
}
