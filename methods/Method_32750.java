/** 
 * Returns a copy of this year-month plus the specified number of years. <p> This year-month instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> YearMonth added = ym.plusYears(6); YearMonth added = ym.plus(Period.years(6)); YearMonth added = ym.withFieldAdded(DurationFieldType.years(), 6); </pre>
 * @param years  the amount of years to add, may be negative
 * @return the new year-month plus the increased years, never null
 */
public YearMonth plusYears(int years){
  return withFieldAdded(DurationFieldType.years(),years);
}
