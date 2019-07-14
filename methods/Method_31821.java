/** 
 * Gets the duration of this time interval in milliseconds. <p> The duration is equal to the end millis minus the start millis.
 * @return the duration of the time interval in milliseconds
 * @throws ArithmeticException if the duration exceeds the capacity of a long
 */
public long toDurationMillis(){
  return FieldUtils.safeSubtract(getEndMillis(),getStartMillis());
}
