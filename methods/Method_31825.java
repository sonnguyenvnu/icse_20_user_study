/** 
 * Checks whether the field specified is supported by this partial.
 * @param type  the type to check, may be null which returns false
 * @return true if the field is supported
 */
public boolean isSupported(DateTimeFieldType type){
  return (indexOf(type) != -1);
}
