protected Scheduler instantiate(QuartzSchedulerResources rsrcs,QuartzScheduler qs){
  Scheduler scheduler=new StdScheduler(qs);
  return scheduler;
}
