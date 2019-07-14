/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public boolean isInStandbyMode() throws SchedulerException {
  return (Boolean)getAttribute("StandbyMode");
}
