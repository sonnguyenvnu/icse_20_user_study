/** 
 * Returns a copy of this datetime with the specified duration taken away. <p> If the amount is zero or null, then <code>this</code> is returned. This datetime instance is immutable and unaffected by this method call.
 * @param duration  the duration, in millis, to reduce this instant by
 * @return a copy of this datetime with the duration taken away
 * @throws ArithmeticException if the new datetime exceeds the capacity of a long
 */
public DateTime minus(long duration){
  return withDurationAdded(duration,-1);
}
