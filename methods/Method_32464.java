/** 
 * Converts this LocalDate to a DateMidnight. <p> As from v1.5, you are recommended to avoid DateMidnight and use {@link #toDateTimeAtStartOfDay()} instead because of the exceptiondetailed below. <p> This method will throw an exception if the time zone switches to Daylight Savings Time at midnight and this LocalDate represents that switchover date. The problem is that there is no such time as midnight on the required date, and as such an exception is thrown. <p> This instance is immutable and unaffected by this method call.
 * @param zone  the zone to get the DateMidnight in, null means default zone
 * @return the DateMidnight instance
 * @deprecated DateMidnight is deprecated
 */
@Deprecated public DateMidnight toDateMidnight(DateTimeZone zone){
  zone=DateTimeUtils.getZone(zone);
  Chronology chrono=getChronology().withZone(zone);
  return new DateMidnight(getYear(),getMonthOfYear(),getDayOfMonth(),chrono);
}
