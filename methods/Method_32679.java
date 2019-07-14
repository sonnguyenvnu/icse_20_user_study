/** 
 * Returns a copy of this time plus the specified number of seconds. <p> This time instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> TimeOfDay added = dt.plusSeconds(6); TimeOfDay added = dt.plus(Period.seconds(6)); TimeOfDay added = dt.withFieldAdded(DurationFieldType.seconds(), 6); </pre>
 * @param seconds  the amount of seconds to add, may be negative
 * @return the new time plus the increased seconds
 * @since 1.1
 */
public TimeOfDay plusSeconds(int seconds){
  return withFieldAdded(DurationFieldType.seconds(),seconds);
}
