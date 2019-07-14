/** 
 * Gets a copy of this instant with the specified duration added. <p> If the amount is zero or null, then <code>this</code> is returned.
 * @param duration  the duration to add to this one
 * @return a copy of this instant with the duration added
 * @throws ArithmeticException if the new instant exceeds the capacity of a long
 */
public Instant plus(long duration){
  return withDurationAdded(duration,1);
}
