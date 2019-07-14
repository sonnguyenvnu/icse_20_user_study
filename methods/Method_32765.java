/** 
 * Converts this YearMonthDay to a full datetime at midnight using the specified time zone. <p> This method uses the chronology from this instance plus the time zone specified.
 * @param zone  the zone to use, null means default
 * @return this date as a datetime at midnight
 */
public DateTime toDateTimeAtMidnight(DateTimeZone zone){
  Chronology chrono=getChronology().withZone(zone);
  return new DateTime(getYear(),getMonthOfYear(),getDayOfMonth(),0,0,0,0,chrono);
}
