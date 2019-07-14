protected List<String> getCalendarNames(Connection conn) throws JobPersistenceException {
  try {
    return getDelegate().selectCalendars(conn);
  }
 catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't obtain trigger groups: " + e.getMessage(),e);
  }
}
