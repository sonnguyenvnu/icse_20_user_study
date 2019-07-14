/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>, passing the <code>SchedulingContext</code> associated with this instance. </p>
 */
public void addCalendar(String calName,Calendar calendar,boolean replace,boolean updateTriggers) throws SchedulerException {
  invoke("addCalendar",new Object[]{calName,calendar,replace,updateTriggers},new String[]{String.class.getName(),Calendar.class.getName(),boolean.class.getName(),boolean.class.getName()});
}
