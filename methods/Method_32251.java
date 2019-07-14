/** 
 * Set the specified amount of remainder units to the specified time instant.
 * @param instant  the time instant in millis to update.
 * @param value  value of remainder units to set.
 * @return the updated time instant.
 * @throws IllegalArgumentException if value is too large or too small.
 */
public long set(long instant,int value){
  FieldUtils.verifyValueBounds(this,value,0,iDivisor - 1);
  int divided=getDivided(getWrappedField().get(instant));
  return getWrappedField().set(instant,divided * iDivisor + value);
}
