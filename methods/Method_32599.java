/** 
 * Gets an array of the field type of each of the fields that this partial supports. <p> The fields are returned largest to smallest.
 * @return the array of field types (cloned), largest to smallest
 */
public DateTimeFieldType[] getFieldTypes(){
  return (DateTimeFieldType[])iTypes.clone();
}
