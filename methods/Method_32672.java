/** 
 * Gets an array of the field type of each of the fields that this partial supports. <p> The fields are returned largest to smallest, Hour, Minute, Second, Millis.
 * @return the array of field types (cloned), largest to smallest
 */
public DateTimeFieldType[] getFieldTypes(){
  return (DateTimeFieldType[])FIELD_TYPES.clone();
}
