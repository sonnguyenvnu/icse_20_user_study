/** 
 * Checks the specified instant before storing it, potentially altering it. This method must not access any instance variables. <p> This implementation simply returns the instant.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
 * @param chronology  the chronology to use, not null
 * @return the instant to store in this datetime
 */
protected long checkInstant(long instant,Chronology chronology){
  return instant;
}
