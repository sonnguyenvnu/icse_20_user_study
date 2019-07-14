@Override public Calendar retrieveCalendar(String calName) throws JobPersistenceException {
  try {
    return realJobStore.retrieveCalendar(calName);
  }
 catch (  RejoinException e) {
    throw new JobPersistenceException("Calendar retrieval failed due to client rejoin",e);
  }
}
