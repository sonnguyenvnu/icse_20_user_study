/** 
 * Returns a copy of this date plus the specified number of days. <p> This date instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> YearMonthDay added = dt.plusDays(6); YearMonthDay added = dt.plus(Period.days(6)); YearMonthDay added = dt.withFieldAdded(DurationFieldType.days(), 6); </pre>
 * @param days  the amount of days to add, may be negative
 * @return the new date plus the increased days
 * @since 1.1
 */
public YearMonthDay plusDays(int days){
  return withFieldAdded(DurationFieldType.days(),days);
}
