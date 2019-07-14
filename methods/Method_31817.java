/** 
 * Is this time interval after the specified millisecond instant. <p> Intervals are inclusive of the start instant and exclusive of the end.
 * @param millisInstant  the instant to compare to,millisecond instant from 1970-01-01T00:00:00Z
 * @return true if this time interval is after the instant
 */
public boolean isAfter(long millisInstant){
  return (getStartMillis() > millisInstant);
}
