/** 
 * Returns a copy of this datetime minus the specified number of years. <p> The calculation will do its best to only change the year field retaining the same month of year. However, in certain circumstances, it may be necessary to alter smaller fields. For example, 2008-02-29 minus one year cannot result in 2007-02-29, so the day of month is adjusted to 2007-02-28. <p> The following three lines are identical in effect: <pre> DateTime subtracted = dt.minusYears(6); DateTime subtracted = dt.minus(Period.years(6)); DateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), -6); </pre> <p> This datetime instance is immutable and unaffected by this method call.
 * @param years  the amount of years to subtract, may be negative
 * @return the new datetime minus the increased years
 * @since 1.1
 */
public DateTime minusYears(int years){
  if (years == 0) {
    return this;
  }
  long instant=getChronology().years().subtract(getMillis(),years);
  return withMillis(instant);
}
