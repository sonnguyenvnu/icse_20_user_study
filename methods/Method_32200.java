/** 
 * Get the millisecond duration of this field from its value, which is approximate if this field is imprecise.
 * @param value  the value of the field, which may be negative
 * @return the milliseconds that the field represents, which may benegative
 */
public long getMillis(long value){
  return FieldUtils.safeMultiply(value,getUnitMillis());
}
