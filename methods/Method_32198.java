/** 
 * Get the value of this field from the milliseconds relative to an instant. <p>If the milliseconds is positive, then the instant is treated as a "start instant". If negative, the instant is treated as an "end instant". <p>The default implementation returns <code>Utils.safeToInt(getAsLong(millisDuration, instant))</code>.
 * @param duration  the milliseconds to query, which may be negative
 * @param instant  the start instant to calculate relative to
 * @return the value of the field, in the units of the field, which may benegative
 */
public int getValue(long duration,long instant){
  return FieldUtils.safeToInt(getValueAsLong(duration,instant));
}
