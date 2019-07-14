/** 
 * Creates a new interval with the specified start millisecond instant.
 * @param startInstant  the start instant for the new interval
 * @return an interval with the end from this interval and the specified start
 * @throws IllegalArgumentException if the resulting interval has end before start
 */
public Interval withStartMillis(long startInstant){
  if (startInstant == getStartMillis()) {
    return this;
  }
  return new Interval(startInstant,getEndMillis(),getChronology());
}
