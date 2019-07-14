/** 
 * Sets the value of a field in this period.
 * @param values  the array of values to update
 * @param field  the field to set
 * @param value  the value to set
 * @throws IllegalArgumentException if field is null or not supported.
 */
protected void setFieldInto(int[] values,DurationFieldType field,int value){
  int index=indexOf(field);
  if (index == -1) {
    if (value != 0 || field == null) {
      throw new IllegalArgumentException("Period does not support field '" + field + "'");
    }
  }
 else {
    values[index]=value;
  }
}
