/** 
 * Add to the component of the specified time instant, wrapping around within that component if necessary.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to add to
 * @param amount  the amount of units to add (can be negative).
 * @return the updated time instant.
 */
public long addWrapField(long instant,int amount){
  int thisValue=get(instant);
  int wrappedValue=FieldUtils.getWrappedValue(thisValue,amount,getMinimumValue(),getMaximumValue());
  return instant + (wrappedValue - thisValue) * getUnitMillis();
}
