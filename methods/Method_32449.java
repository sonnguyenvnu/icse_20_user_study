/** 
 * Creates a new interval with the same start and end, but a different chronology.
 * @param chronology  the chronology to use, null means ISO default
 * @return an interval with a different chronology
 */
public Interval withChronology(Chronology chronology){
  if (getChronology() == chronology) {
    return this;
  }
  return new Interval(getStartMillis(),getEndMillis(),chronology);
}
