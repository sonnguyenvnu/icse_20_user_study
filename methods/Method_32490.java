/** 
 * Returns a copy of this datetime with the specified duration taken away. <p> If the amount is zero or null, then <code>this</code> is returned.
 * @param duration  the duration to reduce this instant by
 * @return a copy of this datetime with the duration taken away
 * @throws ArithmeticException if the result exceeds the internal capacity
 */
public LocalDateTime minus(ReadableDuration duration){
  return withDurationAdded(duration,-1);
}
