/** 
 * Method for unregistering the current context - should always and only be used by in conjunction with a matching  {@link #register(Object)} to ensure that {@link #getContext()} always returnsthe correct value. Does not call close on the context - that is left up to the caller because he has a reference to the context (having registered it) and only he has knowledge of when the execution actually ended.
 */
public void close(){
  C oldSession=getContext();
  if (oldSession == null) {
    return;
  }
  decrement();
}
