/** 
 * Adds a value (which may be negative) to the instant value, wrapping within this field. <p> The value will be added to this field. If the value is too large to be added solely to this field then it wraps. Larger fields are always unaffected. Smaller fields should be unaffected, except where the result would be an invalid value for a smaller field. In this case the smaller field is adjusted to be in range. <p> For example, in the ISO chronology:<br> 2000-08-20 addWrapField six months is 2000-02-20<br> 2000-08-20 addWrapField twenty months is 2000-04-20<br> 2000-08-20 addWrapField minus nine months is 2000-11-20<br> 2001-01-31 addWrapField one month  is 2001-02-28<br> 2001-01-31 addWrapField two months is 2001-03-31<br> <p> The default implementation internally calls set. Subclasses are encouraged to provide a more efficient implementation.
 * @param instant  the milliseconds from 1970-01-01T00:00:00Z to add to
 * @param value  the value to add, in the units of the field
 * @return the updated milliseconds
 */
public long addWrapField(long instant,int value){
  int current=get(instant);
  int wrapped=FieldUtils.getWrappedValue(current,value,getMinimumValue(instant),getMaximumValue(instant));
  return set(instant,wrapped);
}
