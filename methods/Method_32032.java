/** 
 * If this LimitChronology is already UTC, then this is returned. Otherwise, a new instance is returned, with the limits adjusted to the new time zone.
 */
public Chronology withUTC(){
  return withZone(DateTimeZone.UTC);
}
