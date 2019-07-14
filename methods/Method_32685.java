/** 
 * Gets the property object for the specified type, which contains many useful methods.
 * @param type  the field type to get the property for
 * @return the property object
 * @throws IllegalArgumentException if the field is null or unsupported
 */
public Property property(DateTimeFieldType type){
  return new Property(this,indexOfSupported(type));
}
