/** 
 * Set an exception if a response is not yet received otherwise skip it
 * @param e synthetic error to set on initial command when no actual response is available
 */
public void setExceptionIfResponseNotReceived(Exception e){
  if (!valueSet.get() && !isTerminated()) {
    subject.onError(e);
  }
}
