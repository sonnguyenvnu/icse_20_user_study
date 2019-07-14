/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public boolean deleteCalendar(String calName) throws SchedulerException {
  invoke("deleteCalendar",new Object[]{calName},new String[]{String.class.getName()});
  return true;
}
