@Override public void storeCalendar(String name,Calendar calendar,boolean replaceExisting,boolean updateTriggers) throws ObjectAlreadyExistsException, JobPersistenceException {
  try {
    realJobStore.storeCalendar(name,calendar,replaceExisting,updateTriggers);
  }
 catch (  RejoinException e) {
    throw new JobPersistenceException("Storing calendar failed due to client rejoin",e);
  }
}
