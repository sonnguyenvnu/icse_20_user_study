/** 
 * <p> Retrieve the given <code> {@link org.quartz.Trigger}</code>. </p>
 * @param calName The name of the <code>Calendar</code> to be retrieved.
 * @return The desired <code>Calendar</code>, or null if there is no match.
 */
@Override public Calendar retrieveCalendar(String calName) throws JobPersistenceException {
  lock();
  try {
    Calendar cw=calendarsByName.get(calName);
    return (Calendar)(cw == null ? null : cw.clone());
  }
  finally {
    unlock();
  }
}
