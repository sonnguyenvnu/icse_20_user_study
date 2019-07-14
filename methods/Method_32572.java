/** 
 * Sets the duration of this time interval, preserving the start instant.
 * @param duration  new duration for interval, null means zero length
 * @throws IllegalArgumentException if the end is before the start
 * @throws ArithmeticException if the end instant exceeds the capacity of a long
 */
public void setDurationAfterStart(ReadableDuration duration){
  long durationMillis=DateTimeUtils.getDurationMillis(duration);
  setEndMillis(FieldUtils.safeAdd(getStartMillis(),durationMillis));
}
