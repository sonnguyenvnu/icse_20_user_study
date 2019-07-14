/** 
 * Returns a copy of this datetime with the specified time, retaining the date fields. <p> If the time is already the time passed in, then <code>this</code> is returned. <p> To set a single field use the properties, for example: <pre> DateTime set = dt.hourOfDay().setCopy(6); </pre> <p> If the new time is invalid due to the time-zone, the time will be adjusted. <p> This instance is immutable and unaffected by this method call.
 * @param hourOfDay  the hour of the day
 * @param minuteOfHour  the minute of the hour
 * @param secondOfMinute  the second of the minute
 * @param millisOfSecond  the millisecond of the second
 * @return a copy of this datetime with a different time
 * @throws IllegalArgumentException if any value if invalid
 */
public DateTime withTime(int hourOfDay,int minuteOfHour,int secondOfMinute,int millisOfSecond){
  Chronology chrono=getChronology();
  long localInstant=chrono.withUTC().getDateTimeMillis(getYear(),getMonthOfYear(),getDayOfMonth(),hourOfDay,minuteOfHour,secondOfMinute,millisOfSecond);
  return withMillis(chrono.getZone().convertLocalToUTC(localInstant,false,getMillis()));
}
