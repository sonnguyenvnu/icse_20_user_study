/** 
 * Checks whether the field specified is supported by this period.
 * @param type  the type to check, may be null which returns false
 * @return true if the field is supported
 */
public boolean isSupported(DurationFieldType type){
  return (indexOf(type) >= 0);
}
