public void addJob(JobDetail jobDetail,boolean replace,boolean storeNonDurableWhileAwaitingScheduling) throws SchedulerException {
  try {
    getRemoteScheduler().addJob(jobDetail,replace,storeNonDurableWhileAwaitingScheduling);
  }
 catch (  RemoteException re) {
    throw invalidateHandleCreateException("Error communicating with remote scheduler.",re);
  }
}
