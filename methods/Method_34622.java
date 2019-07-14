/** 
 * When set any client thread blocking on get() will immediately be unblocked and receive the single-valued response.
 * @throws IllegalStateException if called more than once or after setException.
 * @param response response to give to initial command
 */
@Override public void setResponse(T response){
  if (!isTerminated()) {
    subject.onNext(response);
    valueSet.set(true);
    subject.onCompleted();
  }
 else {
    throw new IllegalStateException("Response has already terminated so response can not be set : " + response);
  }
}
