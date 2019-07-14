/** 
 * <p> Delete the identified <code>Calendar</code> from the Scheduler. </p>
 * @return true if the Calendar was found and deleted.
 * @throws SchedulerException if there is an internal Scheduler error.
 */
public boolean deleteCalendar(String calName) throws SchedulerException {
  validateState();
  return resources.getJobStore().removeCalendar(calName);
}
