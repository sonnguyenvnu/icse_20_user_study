/** 
 * Returns a string representation of the value, even if it's null.
 * @param value The value to describe
 * @return A string representation of the value
 */
protected String asString(T value){
  return value == null ? "" : value.toString();
}
