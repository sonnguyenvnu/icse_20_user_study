/** 
 * Obtains a  {@code YearMonth} set to the current system millisecond timeusing <code>ISOChronology</code> in the specified time zone. The resulting object does not use the zone.
 * @param zone  the time zone, not null
 * @return the current year-month, not null
 * @since 2.0
 */
public static YearMonth now(DateTimeZone zone){
  if (zone == null) {
    throw new NullPointerException("Zone must not be null");
  }
  return new YearMonth(zone);
}
