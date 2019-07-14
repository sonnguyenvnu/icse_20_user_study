/** 
 * Returns a copy of this year-month plus the specified number of months. <p> This year-month instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> YearMonth added = ym.plusMonths(6); YearMonth added = ym.plus(Period.months(6)); YearMonth added = ym.withFieldAdded(DurationFieldType.months(), 6); </pre>
 * @param months  the amount of months to add, may be negative
 * @return the new year-month plus the increased months, never null
 */
public YearMonth plusMonths(int months){
  return withFieldAdded(DurationFieldType.months(),months);
}
