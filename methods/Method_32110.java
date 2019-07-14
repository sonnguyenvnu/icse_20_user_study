/** 
 * Gets the property object for the specified type, which contains many useful methods.
 * @param type  the field type to get the chronology for
 * @return the property object
 * @throws IllegalArgumentException if the field is null or unsupported
 */
public Property property(DateTimeFieldType type){
  if (type == null) {
    throw new IllegalArgumentException("The DateTimeFieldType must not be null");
  }
  DateTimeField field=type.getField(getChronology());
  if (field.isSupported() == false) {
    throw new IllegalArgumentException("Field '" + type + "' is not supported");
  }
  return new Property(this,field);
}
