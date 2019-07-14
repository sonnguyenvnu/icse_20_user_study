/** 
 * Obtains a  {@code LocalDate} set to the current system millisecond timeusing <code>ISOChronology</code> in the specified time zone.
 * @param zone  the time zone, not null
 * @return the current date-time, not null
 * @since 2.0
 */
public static LocalDate now(DateTimeZone zone){
  if (zone == null) {
    throw new NullPointerException("Zone must not be null");
  }
  return new LocalDate(zone);
}
