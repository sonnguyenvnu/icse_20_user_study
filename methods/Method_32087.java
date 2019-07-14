/** 
 * Returns a copy of this date minus the specified number of years. <p> This datetime instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> DateTime subtracted = dt.minusYears(6); DateTime subtracted = dt.minus(Period.years(6)); DateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), -6); </pre>
 * @param years  the amount of years to subtract, may be negative
 * @return the new datetime minus the increased years
 * @since 1.1
 */
public DateMidnight minusYears(int years){
  if (years == 0) {
    return this;
  }
  long instant=getChronology().years().subtract(getMillis(),years);
  return withMillis(instant);
}
