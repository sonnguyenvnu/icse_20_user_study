/** 
 * Returns a copy of this time plus the specified number of hours. <p> This time instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> TimeOfDay added = dt.plusHours(6); TimeOfDay added = dt.plus(Period.hours(6)); TimeOfDay added = dt.withFieldAdded(DurationFieldType.hours(), 6); </pre>
 * @param hours  the amount of hours to add, may be negative
 * @return the new time plus the increased hours
 * @since 1.1
 */
public TimeOfDay plusHours(int hours){
  return withFieldAdded(DurationFieldType.hours(),hours);
}
