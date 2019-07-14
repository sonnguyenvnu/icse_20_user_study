/** 
 * Checks a single value for a "missing value" error.
 * @param value Value to check
 * @return A descriptive String of the error or null if there was none
 */
protected String valueErrorFor(V value){
  return value != null || defaultHasNullValue() ? null : "missing value";
}
