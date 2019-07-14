/** 
 * Sets the duration of this time interval, preserving the end instant.
 * @param duration  new duration for interval
 * @throws IllegalArgumentException if the end is before the start
 * @throws ArithmeticException if the start instant exceeds the capacity of a long
 */
public void setDurationBeforeEnd(long duration){
  setStartMillis(FieldUtils.safeAdd(getEndMillis(),-duration));
}
