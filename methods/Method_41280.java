protected Calendar retrieveCalendar(Connection conn,String calName) throws JobPersistenceException {
  Calendar cal=(isClustered) ? null : calendarCache.get(calName);
  if (cal != null) {
    return cal;
  }
  try {
    cal=getDelegate().selectCalendar(conn,calName);
    if (!isClustered) {
      calendarCache.put(calName,cal);
    }
    return cal;
  }
 catch (  ClassNotFoundException e) {
    throw new JobPersistenceException("Couldn't retrieve calendar because a required class was not found: " + e.getMessage(),e);
  }
catch (  IOException e) {
    throw new JobPersistenceException("Couldn't retrieve calendar because the BLOB couldn't be deserialized: " + e.getMessage(),e);
  }
catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't retrieve calendar: " + e.getMessage(),e);
  }
}
