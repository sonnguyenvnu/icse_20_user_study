/** 
 * Obtains a  {@code LocalTime} set to the current system millisecond timeusing <code>ISOChronology</code> in the default time zone. The resulting object does not use the zone.
 * @return the current time, not null
 * @since 2.0
 */
public static LocalTime now(){
  return new LocalTime();
}
