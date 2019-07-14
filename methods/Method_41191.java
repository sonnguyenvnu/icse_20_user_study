/** 
 * Build a <code> {@link java.util.Calendar}</code> with the current time. The new Calendar will use the <code>BaseCalendar</code> time zone if it is not <code>null</code>.
 */
protected java.util.Calendar createJavaCalendar(){
  return (getTimeZone() == null) ? java.util.Calendar.getInstance() : java.util.Calendar.getInstance(getTimeZone());
}
