/** 
 * Gets an instance of the CopticChronology in the given time zone.
 * @param zone  the time zone to get the chronology in, null is default
 * @return a chronology in the specified time zone
 */
public static CopticChronology getInstance(DateTimeZone zone){
  return getInstance(zone,4);
}
