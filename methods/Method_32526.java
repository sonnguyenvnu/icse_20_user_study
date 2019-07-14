/** 
 * Obtains a  {@code MonthDay} set to the current system millisecond timeusing the specified chronology. The resulting object does not use the zone.
 * @param chronology  the chronology, not null
 * @return the current month-day, not null
 * @since 2.0
 */
public static MonthDay now(Chronology chronology){
  if (chronology == null) {
    throw new NullPointerException("Chronology must not be null");
  }
  return new MonthDay(chronology);
}
