/** 
 * Returns a copy of this date with the specified period added. <p> If the amount is zero or null, then <code>this</code> is returned. <p> This method is typically used to add complex period instances. Adding one field is best achieved using methods like  {@link #plusYears(int)}.
 * @param period  the duration to add to this one, null means zero
 * @return a copy of this instance with the period added
 * @throws ArithmeticException if the new datetime exceeds the capacity of a long
 */
public YearMonthDay plus(ReadablePeriod period){
  return withPeriodAdded(period,1);
}
