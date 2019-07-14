/** 
 * Returns a copy of this datetime minus the specified number of months. <p> The calculation will do its best to only change the month field retaining the same day of month. However, in certain circumstances, it may be necessary to alter smaller fields. For example, 2007-05-31 minus one month cannot result in 2007-04-31, so the day of month is adjusted to 2007-04-30. <p> The following three lines are identical in effect: <pre> DateTime subtracted = dt.minusMonths(6); DateTime subtracted = dt.minus(Period.months(6)); DateTime subtracted = dt.withFieldAdded(DurationFieldType.months(), -6); </pre> <p> This datetime instance is immutable and unaffected by this method call.
 * @param months  the amount of months to subtract, may be negative
 * @return the new datetime minus the increased months
 * @since 1.1
 */
public DateTime minusMonths(int months){
  if (months == 0) {
    return this;
  }
  long instant=getChronology().months().subtract(getMillis(),months);
  return withMillis(instant);
}
