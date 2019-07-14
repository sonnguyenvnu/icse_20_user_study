/** 
 * Gets the Chronology in the UTC time zone.
 * @return the chronology in UTC
 */
public Chronology withUTC(){
  return withZone(DateTimeZone.UTC);
}
