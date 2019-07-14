/** 
 * Returns a copy of this datetime with the specified time, retaining the date fields. <p> If the new time is invalid due to the time-zone, the time will be adjusted. <p> This instance is immutable and unaffected by this method call.
 * @param time  the local time
 * @return a copy of this datetime with a different time
 * @throws IllegalArgumentException if the time-of-day is invalid for the date
 * @throws NullPointerException if the time is null
 */
public DateTime withTime(LocalTime time){
  return withTime(time.getHourOfDay(),time.getMinuteOfHour(),time.getSecondOfMinute(),time.getMillisOfSecond());
}
