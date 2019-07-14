/** 
 * Converts this LocalDate to a full datetime at the earliest valid time for the date using the specified time zone. <p> The time will normally be midnight, as that is the earliest time on any given day. However, in some time zones when Daylight Savings Time starts, there is no midnight because time jumps from 11:59 to 01:00. This method handles that situation by returning 01:00 on that date. <p> This method uses the chronology from this instance plus the time zone specified. <p> This instance is immutable and unaffected by this method call.
 * @param zone  the zone to use, null means default zone
 * @return this date as a datetime at the start of the day
 * @since 1.5
 */
public DateTime toDateTimeAtStartOfDay(DateTimeZone zone){
  zone=DateTimeUtils.getZone(zone);
  Chronology chrono=getChronology().withZone(zone);
  long localMillis=getLocalMillis() + 6L * DateTimeConstants.MILLIS_PER_HOUR;
  long instant=zone.convertLocalToUTC(localMillis,false);
  instant=chrono.dayOfMonth().roundFloor(instant);
  return new DateTime(instant,chrono).withEarlierOffsetAtOverlap();
}
