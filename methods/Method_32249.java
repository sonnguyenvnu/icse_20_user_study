/** 
 * Get the value of this field from the milliseconds.
 * @param duration  the milliseconds to query, which may be negative
 * @param instant  ignored
 * @return the value of the field, in the units of the field, which may benegative
 */
public long getValueAsLong(long duration,long instant){
  return duration / iUnitMillis;
}
