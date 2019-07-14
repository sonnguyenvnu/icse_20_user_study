/** 
 * Set the specified amount of units to the specified time instant.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to set in
 * @param value  value of units to set.
 * @return the updated time instant.
 * @throws IllegalArgumentException if value is too large or too small.
 */
public long set(long instant,int value){
  FieldUtils.verifyValueBounds(this,value,getMinimumValue(),getMaximumValueForSet(instant,value));
  return instant + (value - get(instant)) * iUnitMillis;
}
