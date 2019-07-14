/** 
 * Checks the specified chronology before storing it, potentially altering it. This method must not access any instance variables. <p> This implementation converts nulls to ISOChronology in the default zone.
 * @param chronology  the chronology to use, may be null
 * @return the chronology to store in this datetime, not null
 */
protected Chronology checkChronology(Chronology chronology){
  return DateTimeUtils.getChronology(chronology);
}
