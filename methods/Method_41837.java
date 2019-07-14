/** 
 * <p> Get the number of <code> {@link org.quartz.Calendar}</code> s that are stored in the <code>JobsStore</code>. </p>
 */
@Override public int getNumberOfCalendars() throws JobPersistenceException {
  lock();
  try {
    return calendarsByName.size();
  }
  finally {
    unlock();
  }
}
