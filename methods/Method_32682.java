/** 
 * Returns a copy of this time minus the specified number of hours. <p> This time instance is immutable and unaffected by this method call. <p> The following three lines are identical in effect: <pre> TimeOfDay subtracted = dt.minusHours(6); TimeOfDay subtracted = dt.minus(Period.hours(6)); TimeOfDay subtracted = dt.withFieldAdded(DurationFieldType.hours(), -6); </pre>
 * @param hours  the amount of hours to subtract, may be negative
 * @return the new time minus the increased hours
 * @since 1.1
 */
public TimeOfDay minusHours(int hours){
  return withFieldAdded(DurationFieldType.hours(),FieldUtils.safeNegate(hours));
}
