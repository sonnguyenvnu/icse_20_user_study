@Override public void initialize(String name,Scheduler scheduler,ClassLoadHelper helper) throws SchedulerException {
  getLog().info("Registering Job Interrupt Monitor Plugin");
  this.name=name;
  this.executor=Executors.newScheduledThreadPool(1);
  scheduler.getContext().put(JOB_INTERRUPT_MONITOR_KEY,this);
  this.scheduler=scheduler;
  this.scheduler.getListenerManager().addTriggerListener(this);
}
