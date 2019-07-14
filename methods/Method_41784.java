@Override public int getNumberOfCalendars() throws JobPersistenceException {
  try {
    return realJobStore.getNumberOfCalendars();
  }
 catch (  RejoinException e) {
    throw new JobPersistenceException("Calendar count retrieval failed due to client rejoin",e);
  }
}
