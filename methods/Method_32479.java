/** 
 * Converts this object to a DateTime using the specified zone. <p> When the time zone is applied, the local date-time may be affected by daylight saving. In a daylight saving gap, when the local time does not exist, this method will throw an exception. In a daylight saving overlap, when the same local time occurs twice, this method returns the first occurrence of the local time.
 * @param zone time zone to apply, or default if null
 * @return a DateTime using the same millis
 * @throws IllegalInstantException if the local time does not exist when the time zone is applied
 */
public DateTime toDateTime(DateTimeZone zone){
  zone=DateTimeUtils.getZone(zone);
  Chronology chrono=iChronology.withZone(zone);
  return new DateTime(getYear(),getMonthOfYear(),getDayOfMonth(),getHourOfDay(),getMinuteOfHour(),getSecondOfMinute(),getMillisOfSecond(),chrono);
}
