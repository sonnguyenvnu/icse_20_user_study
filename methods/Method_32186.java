/** 
 * Adds a value (which may be negative) to the partial instant, wrapping within this field. <p> The value will be added to this field. If the value is too large to be added solely to this field then it wraps. Larger fields are always unaffected. Smaller fields should be unaffected, except where the result would be an invalid value for a smaller field. In this case the smaller field is adjusted to be in range. <p> For example, in the ISO chronology:<br> 2000-08-20 addWrapField six months is 2000-02-20<br> 2000-08-20 addWrapField twenty months is 2000-04-20<br> 2000-08-20 addWrapField minus nine months is 2000-11-20<br> 2001-01-31 addWrapField one month  is 2001-02-28<br> 2001-01-31 addWrapField two months is 2001-03-31<br> <p> The default implementation internally calls set. Subclasses are encouraged to provide a more efficient implementation.
 * @param instant  the partial instant
 * @param fieldIndex  the index of this field in the instant
 * @param values  the values of the partial instant which should be updated
 * @param valueToAdd  the value to add, in the units of the field
 * @return the passed in values
 * @throws IllegalArgumentException if the value is invalid
 */
public int[] addWrapField(ReadablePartial instant,int fieldIndex,int[] values,int valueToAdd){
  int current=values[fieldIndex];
  int wrapped=FieldUtils.getWrappedValue(current,valueToAdd,getMinimumValue(instant),getMaximumValue(instant));
  return set(instant,fieldIndex,values,wrapped);
}
