/** 
 * Get the value of this field from the milliseconds, which is approximate if this field is imprecise.
 * @param duration  the milliseconds to query, which may be negative
 * @return the value of the field, in the units of the field, which may benegative
 */
public long getValueAsLong(long duration){
  return duration / getUnitMillis();
}
