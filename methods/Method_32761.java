/** 
 * Returns a copy of this date plus the specified number of years. <p> This date instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> YearMonthDay added = dt.plusYears(6); YearMonthDay added = dt.plus(Period.years(6)); YearMonthDay added = dt.withFieldAdded(DurationFieldType.years(), 6); </pre>
 * @param years  the amount of years to add, may be negative
 * @return the new date plus the increased years
 * @since 1.1
 */
public YearMonthDay plusYears(int years){
  return withFieldAdded(DurationFieldType.years(),years);
}
