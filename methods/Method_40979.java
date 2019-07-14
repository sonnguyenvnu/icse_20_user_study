public void execute(Thread thread){
  Work work=new org.quartz.commonj.DelegatingWork(thread);
  try {
    this.workManager.schedule(work);
  }
 catch (  Exception e) {
    log.error("Error attempting to schedule QuartzSchedulerThread: " + e.getMessage(),e);
  }
}
