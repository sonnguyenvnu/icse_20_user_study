/** 
 * Sets the duration of this time interval, preserving the start instant.
 * @param duration  new duration for interval
 * @throws IllegalArgumentException if the end is before the start
 * @throws ArithmeticException if the end instant exceeds the capacity of a long
 */
public void setDurationAfterStart(long duration){
  setEndMillis(FieldUtils.safeAdd(getStartMillis(),duration));
}
