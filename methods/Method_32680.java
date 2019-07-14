/** 
 * Returns a copy of this time plus the specified number of millis. <p> This time instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> TimeOfDay added = dt.plusMillis(6); TimeOfDay added = dt.plus(Period.millis(6)); TimeOfDay added = dt.withFieldAdded(DurationFieldType.millis(), 6); </pre>
 * @param millis  the amount of millis to add, may be negative
 * @return the new time plus the increased millis
 * @since 1.1
 */
public TimeOfDay plusMillis(int millis){
  return withFieldAdded(DurationFieldType.millis(),millis);
}
