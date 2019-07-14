/** 
 * Gets the index of the field in this period.
 * @param type  the type to check, may be null which returns -1
 * @return the index of -1 if not supported
 */
public int indexOf(DurationFieldType type){
  return getPeriodType().indexOf(type);
}
