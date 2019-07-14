/** 
 * Gets the index of the first fields to have the specified duration, throwing an exception if the field is unsupported.
 * @param type  the type to check, not null
 * @return the index of the field
 * @throws IllegalArgumentException if the field is null or not supported
 */
protected int indexOfSupported(DurationFieldType type){
  int index=indexOf(type);
  if (index == -1) {
    throw new IllegalArgumentException("Field '" + type + "' is not supported");
  }
  return index;
}
