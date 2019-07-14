/** 
 * Is this time interval before the specified millisecond instant. <p> Intervals are inclusive of the start instant and exclusive of the end.
 * @param millisInstant  the instant to compare to,millisecond instant from 1970-01-01T00:00:00Z
 * @return true if this time interval is before the instant
 */
public boolean isBefore(long millisInstant){
  return (getEndMillis() <= millisInstant);
}
