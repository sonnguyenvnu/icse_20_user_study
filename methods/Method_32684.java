/** 
 * Returns a copy of this time minus the specified number of millis. <p> This time instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> TimeOfDay subtracted = dt.minusMillis(6); TimeOfDay subtracted = dt.minus(Period.millis(6)); TimeOfDay subtracted = dt.withFieldAdded(DurationFieldType.millis(), -6); </pre>
 * @param millis  the amount of millis to subtract, may be negative
 * @return the new time minus the increased millis
 * @since 1.1
 */
public TimeOfDay minusMillis(int millis){
  return withFieldAdded(DurationFieldType.millis(),FieldUtils.safeNegate(millis));
}
