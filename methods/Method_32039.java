/** 
 * Create a ZonedChronology for any chronology, overriding any time zone it may already have.
 * @param base base chronology to wrap
 * @param zone the time zone
 * @throws IllegalArgumentException if chronology or time zone is null
 */
public static ZonedChronology getInstance(Chronology base,DateTimeZone zone){
  if (base == null) {
    throw new IllegalArgumentException("Must supply a chronology");
  }
  base=base.withUTC();
  if (base == null) {
    throw new IllegalArgumentException("UTC chronology must not be null");
  }
  if (zone == null) {
    throw new IllegalArgumentException("DateTimeZone must not be null");
  }
  return new ZonedChronology(base,zone);
}
