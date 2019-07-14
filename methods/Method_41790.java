@Override public void storeTrigger(OperableTrigger newTrigger,boolean replaceExisting) throws ObjectAlreadyExistsException, JobPersistenceException {
  try {
    realJobStore.storeTrigger(newTrigger,replaceExisting);
  }
 catch (  RejoinException e) {
    throw new JobPersistenceException("Storing trigger failed due to client rejoin",e);
  }
}
