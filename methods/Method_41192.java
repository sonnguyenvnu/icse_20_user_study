/** 
 * Returns the start of the given day as a <code> {@link java.util.Calendar}</code>. This calculation will take the <code>BaseCalendar</code> time zone into account if it is not <code>null</code>.
 * @param timeInMillis A time containing the desired date for thestart-of-day time
 * @return A <code>{@link java.util.Calendar}</code> set to the start of the given day.
 */
protected java.util.Calendar getStartOfDayJavaCalendar(long timeInMillis){
  java.util.Calendar startOfDay=createJavaCalendar(timeInMillis);
  startOfDay.set(java.util.Calendar.HOUR_OF_DAY,0);
  startOfDay.set(java.util.Calendar.MINUTE,0);
  startOfDay.set(java.util.Calendar.SECOND,0);
  startOfDay.set(java.util.Calendar.MILLISECOND,0);
  return startOfDay;
}
