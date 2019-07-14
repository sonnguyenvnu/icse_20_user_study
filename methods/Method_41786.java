@Override public void schedulerStarted() throws SchedulerException {
  try {
    realJobStore.schedulerStarted();
  }
 catch (  RejoinException e) {
    throw new JobPersistenceException("Scheduler start failed due to client rejoin",e);
  }
}
