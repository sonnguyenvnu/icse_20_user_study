/** 
 * Gets a the printed form of the exception, with a trimmed version of the stack trace. This method will attempt to filter out frames of the stack trace that are below the test method call.
 */
public String getTrimmedTrace(){
  return Throwables.getTrimmedStackTrace(getException());
}
