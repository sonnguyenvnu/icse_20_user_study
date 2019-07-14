/** 
 * <p> Called during creation of the <code>Scheduler</code> in order to give the <code>SchedulerPlugin</code> a chance to initialize. </p>
 * @throws SchedulerConfigException if there is an error initializing.
 */
public void initialize(String pname,Scheduler scheduler,ClassLoadHelper classLoadHelper) throws SchedulerException {
  this.name=pname;
  scheduler.getListenerManager().addTriggerListener(this,EverythingMatcher.allTriggers());
}
