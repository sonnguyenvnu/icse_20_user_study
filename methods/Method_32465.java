/** 
 * Converts this object to a DateTime using a LocalTime to fill in the missing fields. <p> The resulting chronology is determined by the chronology of this LocalDate plus the time zone. The chronology of the time must match. <p> If the time is null, this method delegates to  {@link #toDateTimeAtCurrentTime(DateTimeZone)}and the following documentation does not apply. <p> When the time zone is applied, the local date-time may be affected by daylight saving. In a daylight saving gap, when the local time does not exist, this method will throw an exception. In a daylight saving overlap, when the same local time occurs twice, this method returns the first occurrence of the local time. <p> This instance is immutable and unaffected by this method call.
 * @param time  the time of day to use, null uses current time
 * @param zone  the zone to get the DateTime in, null means default
 * @return the DateTime instance
 * @throws IllegalArgumentException if the chronology of the time does not match
 * @throws IllegalInstantException if the local time does not exist when the time zone is applied
 */
public DateTime toDateTime(LocalTime time,DateTimeZone zone){
  if (time == null) {
    return toDateTimeAtCurrentTime(zone);
  }
  if (getChronology() != time.getChronology()) {
    throw new IllegalArgumentException("The chronology of the time does not match");
  }
  Chronology chrono=getChronology().withZone(zone);
  return new DateTime(getYear(),getMonthOfYear(),getDayOfMonth(),time.getHourOfDay(),time.getMinuteOfHour(),time.getSecondOfMinute(),time.getMillisOfSecond(),chrono);
}
