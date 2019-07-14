/** 
 * Gets a copy of this instance with the specified period take away. <p> If the amount is zero or null, then <code>this</code> is returned.
 * @param period  the period to reduce this instant by
 * @return a copy of this instance with the period taken away
 * @throws ArithmeticException if the new datetime exceeds the capacity of a long
 */
public Partial minus(ReadablePeriod period){
  return withPeriodAdded(period,-1);
}
