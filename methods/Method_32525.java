/** 
 * Obtains a  {@code MonthDay} set to the current system millisecond timeusing <code>ISOChronology</code> in the specified time zone. The resulting object does not use the zone.
 * @param zone  the time zone, not null
 * @return the current month-day, not null
 * @since 2.0
 */
public static MonthDay now(DateTimeZone zone){
  if (zone == null) {
    throw new NullPointerException("Zone must not be null");
  }
  return new MonthDay(zone);
}
