/** 
 * <p> Called during creation of the <code>Scheduler</code> in order to give the <code>SchedulerPlugin</code> a chance to initialize. </p> <p> At this point, the Scheduler's <code>JobStore</code> is not yet initialized. </p> <p> If you need direct access your plugin, for example during <code>Job</code> execution, you can have this method explicitly put a reference to this plugin in the <code>Scheduler</code>'s <code>SchedulerContext</code>. </p>
 * @param name       The name by which the plugin is identified.
 * @param scheduler  The scheduler to which the plugin is registered.
 * @param loadHelper The classLoadHelper the <code>SchedulerFactory</code> isactually using
 * @throws SchedulerConfigException if there is an error initializing.
 */
@Override public void initialize(String name,Scheduler scheduler,ClassLoadHelper loadHelper) throws SchedulerException {
  LOGGER.info("this is method MultiSerialJobListener initialize " + scheduler.getSchedulerName());
  this.name=name;
  scheduler.getListenerManager().addJobListener(this,EverythingMatcher.allJobs());
}
