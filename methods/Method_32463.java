/** 
 * Converts this LocalDate to a full datetime using the specified time zone setting the date fields from this instance and the time fields from the current time. <p> This method uses the chronology from this instance plus the time zone specified. <p> This method will throw an exception if the datetime that would be created does not exist when the time zone is taken into account. <p> This instance is immutable and unaffected by this method call.
 * @param zone  the zone to use, null means default zone
 * @return this date as a datetime with the time as the current time
 */
public DateTime toDateTimeAtCurrentTime(DateTimeZone zone){
  zone=DateTimeUtils.getZone(zone);
  Chronology chrono=getChronology().withZone(zone);
  long instantMillis=DateTimeUtils.currentTimeMillis();
  long resolved=chrono.set(this,instantMillis);
  return new DateTime(resolved,chrono);
}
