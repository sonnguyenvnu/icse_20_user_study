/** 
 * Returns a datetime millisecond instant, from from the given instant, hour, minute, second, and millisecond values. The set of given values must refer to a valid datetime, or else an IllegalArgumentException is thrown. <p> The default implementation calls upon separate DateTimeFields to determine the result. Subclasses are encouraged to provide a more efficient implementation.
 * @param instant instant to start from
 * @param hourOfDay hour to use
 * @param minuteOfHour minute to use
 * @param secondOfMinute second to use
 * @param millisOfSecond millisecond to use
 * @return millisecond instant from 1970-01-01T00:00:00Z
 */
public long getDateTimeMillis(long instant,int hourOfDay,int minuteOfHour,int secondOfMinute,int millisOfSecond) throws IllegalArgumentException {
  instant=hourOfDay().set(instant,hourOfDay);
  instant=minuteOfHour().set(instant,minuteOfHour);
  instant=secondOfMinute().set(instant,secondOfMinute);
  return millisOfSecond().set(instant,millisOfSecond);
}
