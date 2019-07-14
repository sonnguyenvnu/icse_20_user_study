/** 
 * Obtains a  {@code MutableDateTime} set to the current system millisecond timeusing <code>ISOChronology</code> in the default time zone.
 * @return the current date-time, not null
 * @since 2.0
 */
public static MutableDateTime now(){
  return new MutableDateTime();
}
