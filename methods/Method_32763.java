/** 
 * Returns a copy of this date with the specified period taken away. <p> If the amount is zero or null, then <code>this</code> is returned. <p> This method is typically used to subtract complex period instances. Subtracting one field is best achieved using methods like  {@link #minusYears(int)}.
 * @param period  the period to reduce this instant by
 * @return a copy of this instance with the period taken away
 * @throws ArithmeticException if the new datetime exceeds the capacity of a long
 */
public YearMonthDay minus(ReadablePeriod period){
  return withPeriodAdded(period,-1);
}
