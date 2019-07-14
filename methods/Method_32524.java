/** 
 * Obtains a  {@code MonthDay} set to the current system millisecond timeusing <code>ISOChronology</code> in the default time zone. The resulting object does not use the zone.
 * @return the current month-day, not null
 * @since 2.0
 */
public static MonthDay now(){
  return new MonthDay();
}
