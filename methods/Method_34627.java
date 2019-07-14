/** 
 * When set any client thread blocking on get() will immediately be unblocked and receive the exception.
 * @throws IllegalStateException if called more than once or after setResponse.
 * @param e received exception that gets set on the initial command
 */
@Override public void setException(Exception e){
  if (!isTerminated()) {
    subject.onError(e);
  }
 else {
    throw new IllegalStateException("Response has already terminated so exception can not be set",e);
  }
}
