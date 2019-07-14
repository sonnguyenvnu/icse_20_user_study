/** 
 * Does this time interval contain the specified millisecond instant. <p> Non-zero duration intervals are inclusive of the start instant and exclusive of the end. A zero duration interval cannot contain anything.
 * @param millisInstant  the instant to compare to,millisecond instant from 1970-01-01T00:00:00Z
 * @return true if this time interval contains the millisecond
 */
public boolean contains(long millisInstant){
  long thisStart=getStartMillis();
  long thisEnd=getEndMillis();
  return (millisInstant >= thisStart && millisInstant < thisEnd);
}
