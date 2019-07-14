/** 
 * Creates a new interval with the specified end millisecond instant.
 * @param endInstant  the end instant for the new interval
 * @return an interval with the start from this interval and the specified end
 * @throws IllegalArgumentException if the resulting interval has end before start
 */
public Interval withEndMillis(long endInstant){
  if (endInstant == getEndMillis()) {
    return this;
  }
  return new Interval(getStartMillis(),endInstant,getChronology());
}
