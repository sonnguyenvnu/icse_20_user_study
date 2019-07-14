/** 
 * Returns a new period minus the specified number of millis taken away. <p> This period instance is immutable and unaffected by this method call.
 * @param millis  the amount of millis to take away, may be negative
 * @return the new period minus the increased millis
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period minusMillis(int millis){
  return plusMillis(-millis);
}
