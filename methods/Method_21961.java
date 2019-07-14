private Scheduler createScheduler(){
  Scheduler result;
  try {
    StdSchedulerFactory factory=new StdSchedulerFactory();
    factory.initialize(getBaseQuartzProperties());
    result=factory.getScheduler();
    result.getListenerManager().addTriggerListener(schedulerFacade.newJobTriggerListener());
  }
 catch (  final SchedulerException ex) {
    throw new JobSystemException(ex);
  }
  return result;
}
