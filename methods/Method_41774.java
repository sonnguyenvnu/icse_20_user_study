/** 
 * <p> Called during creation of the <code>Scheduler</code> in order to give the <code>SchedulerPlugin</code> a chance to initialize. </p>
 * @throws SchedulerConfigException if there is an error initializing.
 */
public void initialize(String name,final Scheduler scheduler,ClassLoadHelper classLoadHelper) throws SchedulerException {
  getLog().info("Registering Quartz shutdown hook.");
  Thread t=new Thread("Quartz Shutdown-Hook " + scheduler.getSchedulerName()){
    @Override public void run(){
      getLog().info("Shutting down Quartz...");
      try {
        scheduler.shutdown(isCleanShutdown());
      }
 catch (      SchedulerException e) {
        getLog().info("Error shutting down Quartz: " + e.getMessage(),e);
      }
    }
  }
;
  Runtime.getRuntime().addShutdownHook(t);
}
