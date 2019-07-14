/** 
 * Sets the duration of this time interval, preserving the end instant.
 * @param duration  new duration for interval, null means zero length
 * @throws IllegalArgumentException if the end is before the start
 * @throws ArithmeticException if the start instant exceeds the capacity of a long
 */
public void setDurationBeforeEnd(ReadableDuration duration){
  long durationMillis=DateTimeUtils.getDurationMillis(duration);
  setStartMillis(FieldUtils.safeAdd(getEndMillis(),-durationMillis));
}
