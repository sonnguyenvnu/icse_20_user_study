/** 
 * Set an ISE if a response is not yet received otherwise skip it
 * @param e A pre-generated exception.  If this is null an ISE will be created and returned
 * @param exceptionMessage The message for the ISE
 */
public Exception setExceptionIfResponseNotReceived(Exception e,String exceptionMessage){
  Exception exception=e;
  if (!valueSet.get() && !isTerminated()) {
    if (e == null) {
      exception=new IllegalStateException(exceptionMessage);
    }
    setExceptionIfResponseNotReceived(exception);
  }
  return exception;
}
