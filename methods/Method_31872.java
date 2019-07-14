/** 
 * Returns a datetime millisecond instant, formed from the given year, month, day, and millisecond values. The set of given values must refer to a valid datetime, or else an IllegalArgumentException is thrown. <p> The default implementation calls upon separate DateTimeFields to determine the result. Subclasses are encouraged to provide a more efficient implementation.
 * @param year year to use
 * @param monthOfYear month to use
 * @param dayOfMonth day of month to use
 * @param millisOfDay millisecond to use
 * @return millisecond instant from 1970-01-01T00:00:00Z
 */
public long getDateTimeMillis(int year,int monthOfYear,int dayOfMonth,int millisOfDay) throws IllegalArgumentException {
  long instant=year().set(0,year);
  instant=monthOfYear().set(instant,monthOfYear);
  instant=dayOfMonth().set(instant,dayOfMonth);
  return millisOfDay().set(instant,millisOfDay);
}
