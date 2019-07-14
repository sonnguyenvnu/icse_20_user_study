/** 
 * Gets the printed form of the exception and its stack trace.
 */
public String getTrace(){
  return Throwables.getStacktrace(getException());
}
