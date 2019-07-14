/** 
 * Returns a copy of this time with the specified period taken away, wrapping to what would be a new day if required. <p> If the amount is zero or null, then <code>this</code> is returned. <p> This method is typically used to subtract complex period instances. Subtracting one field is best achieved using methods like  {@link #minusHours(int)}.
 * @param period  the period to reduce this instant by
 * @return a copy of this instance with the period taken away
 * @throws ArithmeticException if the new time exceeds capacity
 */
public TimeOfDay minus(ReadablePeriod period){
  return withPeriodAdded(period,-1);
}
