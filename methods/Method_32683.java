/** 
 * Returns a copy of this time minus the specified number of seconds. <p> This time instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> TimeOfDay subtracted = dt.minusSeconds(6); TimeOfDay subtracted = dt.minus(Period.seconds(6)); TimeOfDay subtracted = dt.withFieldAdded(DurationFieldType.seconds(), -6); </pre>
 * @param seconds  the amount of seconds to subtract, may be negative
 * @return the new time minus the increased seconds
 * @since 1.1
 */
public TimeOfDay minusSeconds(int seconds){
  return withFieldAdded(DurationFieldType.seconds(),FieldUtils.safeNegate(seconds));
}
