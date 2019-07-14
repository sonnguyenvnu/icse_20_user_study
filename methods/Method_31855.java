/** 
 * Sets the value of a field in this period.
 * @param field  the field to set
 * @param value  the value to set
 * @throws IllegalArgumentException if field is is null or not supported.
 */
protected void setField(DurationFieldType field,int value){
  setFieldInto(iValues,field,value);
}
