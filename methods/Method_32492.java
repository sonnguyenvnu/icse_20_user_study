/** 
 * Gets the property object for the specified type, which contains many useful methods.
 * @param fieldType  the field type to get the chronology for
 * @return the property object
 * @throws IllegalArgumentException if the field is null or unsupported
 */
public Property property(DateTimeFieldType fieldType){
  if (fieldType == null) {
    throw new IllegalArgumentException("The DateTimeFieldType must not be null");
  }
  if (isSupported(fieldType) == false) {
    throw new IllegalArgumentException("Field '" + fieldType + "' is not supported");
  }
  return new Property(this,fieldType.getField(getChronology()));
}
