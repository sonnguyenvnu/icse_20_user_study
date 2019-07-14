/** 
 * Returns a copy of this time plus the specified number of minutes. <p> This time instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> TimeOfDay added = dt.plusMinutes(6); TimeOfDay added = dt.plus(Period.minutes(6)); TimeOfDay added = dt.withFieldAdded(DurationFieldType.minutes(), 6); </pre>
 * @param minutes  the amount of minutes to add, may be negative
 * @return the new time plus the increased minutes
 * @since 1.1
 */
public TimeOfDay plusMinutes(int minutes){
  return withFieldAdded(DurationFieldType.minutes(),minutes);
}
