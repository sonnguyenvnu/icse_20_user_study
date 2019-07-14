/** 
 * Returns a new period minus the specified number of seconds taken away. <p> This period instance is immutable and unaffected by this method call.
 * @param seconds  the amount of seconds to take away, may be negative
 * @return the new period minus the increased seconds
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period minusSeconds(int seconds){
  return plusSeconds(-seconds);
}
