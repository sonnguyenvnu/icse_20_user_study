/** 
 * <p> Return the exception that is the underlying cause of this exception. </p> <p> This may be used to find more detail about the cause of the error. </p>
 * @return the underlying exception, or <code>null</code> if there is notone.
 */
public Throwable getUnderlyingException(){
  return super.getCause();
}
