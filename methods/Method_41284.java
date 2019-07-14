protected int getNumberOfCalendars(Connection conn) throws JobPersistenceException {
  try {
    return getDelegate().selectNumCalendars(conn);
  }
 catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't obtain number of calendars: " + e.getMessage(),e);
  }
}
