/** 
 * Obtains a  {@code MutableDateTime} set to the current system millisecond timeusing the specified chronology.
 * @param chronology  the chronology, not null
 * @return the current date-time, not null
 * @since 2.0
 */
public static MutableDateTime now(Chronology chronology){
  if (chronology == null) {
    throw new NullPointerException("Chronology must not be null");
  }
  return new MutableDateTime(chronology);
}
