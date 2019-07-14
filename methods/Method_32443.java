/** 
 * Gets a copy of this instant with the specified duration taken away. <p> If the amount is zero or null, then <code>this</code> is returned.
 * @param duration  the duration to reduce this instant by
 * @return a copy of this instant with the duration taken away
 * @throws ArithmeticException if the new instant exceeds the capacity of a long
 */
public Instant minus(long duration){
  return withDurationAdded(duration,-1);
}
