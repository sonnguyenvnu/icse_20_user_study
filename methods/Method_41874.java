@Override public void storeJobAndTrigger(JobDetail newJob,OperableTrigger newTrigger) throws JobPersistenceException {
  clusteredJobStore.storeJobAndTrigger(newJob,newTrigger);
}
