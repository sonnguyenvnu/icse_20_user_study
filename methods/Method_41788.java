@Override public void storeJob(JobDetail newJob,boolean replaceExisting) throws ObjectAlreadyExistsException, JobPersistenceException {
  try {
    realJobStore.storeJob(newJob,replaceExisting);
  }
 catch (  RejoinException e) {
    throw new JobPersistenceException("Storing job failed due to client rejoin",e);
  }
}
