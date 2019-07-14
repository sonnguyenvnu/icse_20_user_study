/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public void shutdown() throws SchedulerException {
  String schedulerName=getSchedulerName();
  invoke("shutdown",new Object[]{},new String[]{});
  SchedulerRepository.getInstance().remove(schedulerName);
}
