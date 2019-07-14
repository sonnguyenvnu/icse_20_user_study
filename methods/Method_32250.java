/** 
 * Get the millisecond duration of this field from its value.
 * @param value  the value of the field, which may be negative
 * @param instant  ignored
 * @return the milliseconds that the field represents, which may benegative
 */
public long getMillis(long value,long instant){
  return FieldUtils.safeMultiply(value,iUnitMillis);
}
