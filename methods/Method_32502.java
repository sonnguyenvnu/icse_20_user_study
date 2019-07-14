/** 
 * Constructs a LocalTime from a <code>java.util.Calendar</code> using exactly the same field values. <p> Each field is queried from the Calendar and assigned to the LocalTime. This is useful if you have been using the Calendar as a local time, ignoring the zone. <p> One advantage of this method is that this method is unaffected if the version of the time zone data differs between the JDK and Joda-Time. That is because the local field values are transferred, calculated using the JDK time zone data and without using the Joda-Time time zone data. <p> This factory method ignores the type of the calendar and always creates a LocalTime with ISO chronology. It is expected that you will only pass in instances of <code>GregorianCalendar</code> however this is not validated.
 * @param calendar  the Calendar to extract fields from
 * @return the created LocalTime
 * @throws IllegalArgumentException if the calendar is null
 * @throws IllegalArgumentException if the date is invalid for the ISO chronology
 */
public static LocalTime fromCalendarFields(Calendar calendar){
  if (calendar == null) {
    throw new IllegalArgumentException("The calendar must not be null");
  }
  return new LocalTime(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND),calendar.get(Calendar.MILLISECOND));
}
