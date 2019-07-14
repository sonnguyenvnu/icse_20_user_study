/** 
 * Create a TimeOfDay from the given date (at the zero-second), in the system default TimeZone.
 * @param dateTime The java.util.Date from which to extract Hour and Minute.
 * @param tz The TimeZone from which relate Hour and Minute for the given date.  If null, system defaultTimeZone will be used.
 */
public static TimeOfDay hourAndMinuteFromDate(Date dateTime,TimeZone tz){
  if (dateTime == null)   return null;
  Calendar cal=Calendar.getInstance();
  cal.setTime(dateTime);
  if (tz != null)   cal.setTimeZone(tz);
  return new TimeOfDay(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE));
}
