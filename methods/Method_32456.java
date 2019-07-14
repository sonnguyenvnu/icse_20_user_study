/** 
 * Obtains a  {@code LocalDate} set to the current system millisecond timeusing the specified chronology.
 * @param chronology  the chronology, not null
 * @return the current date-time, not null
 * @since 2.0
 */
public static LocalDate now(Chronology chronology){
  if (chronology == null) {
    throw new NullPointerException("Chronology must not be null");
  }
  return new LocalDate(chronology);
}
