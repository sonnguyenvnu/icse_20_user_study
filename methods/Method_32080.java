/** 
 * Obtains a  {@code DateMidnight} set to the current system millisecond timeusing <code>ISOChronology</code> in the default time zone. The constructed object will have a local time of midnight.
 * @return the current date, not null
 * @since 2.0
 */
public static DateMidnight now(){
  return new DateMidnight();
}
