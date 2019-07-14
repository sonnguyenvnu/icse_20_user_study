/** 
 * Returns a copy of this month-day with the specified period added. <p> If the amount is zero or null, then <code>this</code> is returned. <p> This method is typically used to add complex period instances. Adding one field is best achieved using methods like  {@link #plusMonths(int)}.
 * @param period  the duration to add to this one, null means zero
 * @return a copy of this instance with the period added, never null
 * @throws ArithmeticException if the new month-day exceeds the capacity
 */
public MonthDay plus(ReadablePeriod period){
  return withPeriodAdded(period,1);
}
