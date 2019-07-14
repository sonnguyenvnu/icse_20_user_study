/** 
 * Gets a copy of this instance with the specified period added. <p> If the amount is zero or null, then <code>this</code> is returned.
 * @param period  the duration to add to this one, null means zero
 * @return a copy of this instance with the period added
 * @throws ArithmeticException if the new datetime exceeds the capacity of a long
 */
public Partial plus(ReadablePeriod period){
  return withPeriodAdded(period,1);
}
