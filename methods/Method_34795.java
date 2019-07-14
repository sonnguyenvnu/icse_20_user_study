/** 
 * Delegates to the wrapped  {@link HystrixRequestVariableLifecycle}
 * @param value of request variable to allow cleanup activity. <p> If nothing needs to be cleaned up then nothing needs to be done in this method.
 */
@Override public void shutdown(T value){
  lifecycle.shutdown(value);
}
