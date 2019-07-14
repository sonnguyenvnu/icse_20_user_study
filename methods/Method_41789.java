@Override public void storeJobAndTrigger(JobDetail newJob,OperableTrigger newTrigger) throws ObjectAlreadyExistsException, JobPersistenceException {
  try {
    realJobStore.storeJobAndTrigger(newJob,newTrigger);
  }
 catch (  RejoinException e) {
    throw new JobPersistenceException("Storing job and trigger failed due to client rejoin",e);
  }
}
