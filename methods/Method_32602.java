/** 
 * Gets a copy of this Partial with the specified field set to a new value. <p> If this partial does not support the field, an exception is thrown. Contrast this behaviour with  {@link #with(DateTimeFieldType,int)}. <p> For example, if the field type is <code>dayOfMonth</code> then the day would be changed in the returned instance if supported.
 * @param fieldType  the field type to set, not null
 * @param value  the value to set
 * @return a copy of this instance with the field set
 * @throws IllegalArgumentException if the value is null or invalid
 */
public Partial withField(DateTimeFieldType fieldType,int value){
  int index=indexOfSupported(fieldType);
  if (value == getValue(index)) {
    return this;
  }
  int[] newValues=getValues();
  newValues=getField(index).set(this,index,newValues,value);
  return new Partial(this,newValues);
}
