/** 
 * Converts this LocalDate to a full datetime at midnight using the specified time zone. <p> This method will throw an exception if the time zone switches to Daylight Savings Time at midnight and this LocalDate represents that switchover date. The problem is that there is no such time as midnight on the required date, and as such an exception is thrown. <p> This method uses the chronology from this instance plus the time zone specified. <p> This instance is immutable and unaffected by this method call.
 * @param zone  the zone to use, null means default zone
 * @return this date as a datetime at midnight
 * @deprecated Use {@link #toDateTimeAtStartOfDay(DateTimeZone)} which won't throw an exception
 */
@Deprecated public DateTime toDateTimeAtMidnight(DateTimeZone zone){
  zone=DateTimeUtils.getZone(zone);
  Chronology chrono=getChronology().withZone(zone);
  return new DateTime(getYear(),getMonthOfYear(),getDayOfMonth(),0,0,0,0,chrono);
}
