/** 
 * Emit a response that should be OnNexted to an Observer
 * @param response response to emit to initial command
 */
@Override public void emitResponse(T response){
  if (!isTerminated()) {
    subject.onNext(response);
    valueSet.set(true);
  }
 else {
    throw new IllegalStateException("Response has already terminated so response can not be set : " + response);
  }
}
