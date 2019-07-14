/** 
 * <p> Calls the equivalent method on the 'proxied' <code>QuartzScheduler</code>. </p>
 */
public boolean checkExists(TriggerKey triggerKey) throws SchedulerException {
  return (Boolean)invoke("checkExists",new Object[]{triggerKey},new String[]{TriggerKey.class.getName()});
}
