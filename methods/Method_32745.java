/** 
 * Obtains a  {@code YearMonth} set to the current system millisecond timeusing the specified chronology. The resulting object does not use the zone.
 * @param chronology  the chronology, not null
 * @return the current year-month, not null
 * @since 2.0
 */
public static YearMonth now(Chronology chronology){
  if (chronology == null) {
    throw new NullPointerException("Chronology must not be null");
  }
  return new YearMonth(chronology);
}
