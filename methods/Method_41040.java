/** 
 * <p> Add (register) the given <code>Calendar</code> to the Scheduler. </p>
 * @throws SchedulerException if there is an internal Scheduler error, or a Calendar with the same name already exists, and <code>replace</code> is <code>false</code>.
 */
public void addCalendar(String calName,Calendar calendar,boolean replace,boolean updateTriggers) throws SchedulerException {
  validateState();
  resources.getJobStore().storeCalendar(calName,calendar,replace,updateTriggers);
}
