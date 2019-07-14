/** 
 * Returns a copy of this datetime with the specified time, retaining the date fields. <p> If the time is already the time passed in, then <code>this</code> is returned. <p> To set a single field use the properties, for example: <pre> LocalDateTime set = dt.hourOfDay().setCopy(6); </pre>
 * @param hourOfDay  the hour of the day
 * @param minuteOfHour  the minute of the hour
 * @param secondOfMinute  the second of the minute
 * @param millisOfSecond  the millisecond of the second
 * @return a copy of this datetime with a different time
 * @throws IllegalArgumentException if any value if invalid
 */
public LocalDateTime withTime(int hourOfDay,int minuteOfHour,int secondOfMinute,int millisOfSecond){
  Chronology chrono=getChronology();
  long instant=getLocalMillis();
  instant=chrono.hourOfDay().set(instant,hourOfDay);
  instant=chrono.minuteOfHour().set(instant,minuteOfHour);
  instant=chrono.secondOfMinute().set(instant,secondOfMinute);
  instant=chrono.millisOfSecond().set(instant,millisOfSecond);
  return withLocalMillis(instant);
}
