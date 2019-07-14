/** 
 * Returns a copy of this datetime with the specified duration added. <p> If the amount is zero or null, then <code>this</code> is returned.
 * @param duration  the duration to add to this one, null means zero
 * @return a copy of this datetime with the duration added
 * @throws ArithmeticException if the result exceeds the internal capacity
 */
public LocalDateTime plus(ReadableDuration duration){
  return withDurationAdded(duration,1);
}
