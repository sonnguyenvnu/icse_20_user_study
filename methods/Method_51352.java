/** 
 * Checks the value for an error.
 * @param value Value to check
 * @return A diagnostic error message, or null if there's no problem
 */
protected String valueErrorFor(T value){
  return value != null || defaultHasNullValue() ? null : "missing value";
}
