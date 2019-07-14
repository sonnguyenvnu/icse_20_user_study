/** 
 * Creates a new Period instance with the valueToAdd added to the specified field. <p> This period instance is immutable and unaffected by this method call.
 * @param field  the field to set, not null
 * @param value  the value to add
 * @return the new period instance
 * @throws IllegalArgumentException if the field type is null or unsupported
 */
public Period withFieldAdded(DurationFieldType field,int value){
  if (field == null) {
    throw new IllegalArgumentException("Field must not be null");
  }
  if (value == 0) {
    return this;
  }
  int[] newValues=getValues();
  super.addFieldInto(newValues,field,value);
  return new Period(newValues,getPeriodType());
}
