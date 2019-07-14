/** 
 * Obtains a  {@code DateMidnight} set to the current system millisecond timeusing the specified chronology. The constructed object will have a local time of midnight.
 * @param chronology  the chronology, not null
 * @return the current date, not null
 * @since 2.0
 */
public static DateMidnight now(Chronology chronology){
  if (chronology == null) {
    throw new NullPointerException("Chronology must not be null");
  }
  return new DateMidnight(chronology);
}
