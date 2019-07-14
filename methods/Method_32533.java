/** 
 * Returns a copy of this month-day plus the specified number of days. <p> This month-day instance is immutable and unaffected by this method call. The month will wrap at the end of the year from December to January. <p> If the number of days added requires wrapping past the end of February, the wrapping will be calculated assuming February has 29 days.  <p> The following three lines are identical in effect: <pre> MonthDay added = md.plusDays(6); MonthDay added = md.plus(Period.days(6)); MonthDay added = md.withFieldAdded(DurationFieldType.days(), 6); </pre>
 * @param days  the amount of days to add, may be negative
 * @return the new month-day plus the increased days, never null
 */
public MonthDay plusDays(int days){
  return withFieldAdded(DurationFieldType.days(),days);
}
